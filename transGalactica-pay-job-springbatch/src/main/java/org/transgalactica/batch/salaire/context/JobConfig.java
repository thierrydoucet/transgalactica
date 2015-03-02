package org.transgalactica.batch.salaire.context;

import java.time.LocalDate;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FormatterLineAggregator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.batch.salaire.item.BasicComputeSalaireItemProcessor;
import org.transgalactica.batch.salaire.item.ITextFicheSalairePdfItemWriter;
import org.transgalactica.management.data.people.bo.EmployeEntity;

import com.google.common.collect.ImmutableMap;

/**
 * Notes d'implementation :
 * <ul>
 * <li>Type de retour des "Items" : https://jira.spring.io/browse/BATCH-2097</li>
 * <li>Declaration des Beans avec <code>destroyMethod=""</code> pour eviter
 * l'auto-discover (cf javadoc)</li>
 * </ul>
 * 
 * @author Thierry
 */
@Configuration
@EnableBatchProcessing
public class JobConfig {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Autowired
	private EntityManagerFactory emf;

	@Bean
	public Job ficheSalaireJob() throws Exception {
		return jobs.get("ficheSalaireJob") //
				.start(computeSalaireStep()) //
				.next(editFicheSalaireStep()).build();
	}

	@Bean
	public Step computeSalaireStep() throws Exception {
		return steps.get("computeSalaireStep") //
				.<EmployeEntity, SalaireTo> chunk(3) //
				.reader(employeEntityItemReader()) //
				.processor(computeSalaireItemProcessor(DATE_CALCUL)) //
				.writer(salaireToItemWriter(COMPUTE_OUTPUT_FILE)).build();
	}

	@Bean(destroyMethod = "")
	public JpaPagingItemReader<EmployeEntity> employeEntityItemReader() throws Exception {
		JpaPagingItemReader<EmployeEntity> reader = new JpaPagingItemReader<>();
		reader.setEntityManagerFactory(emf);
		// fetch join pour s'assurer du chargement des données necessaire lors
		// du process (em fermé)
		reader.setQueryString("select e from AbstractJpaEmployeEntity e left join fetch e.specialites order by id");
		reader.afterPropertiesSet();
		return reader;
	}

	@Bean
	@StepScope
	public BasicComputeSalaireItemProcessor computeSalaireItemProcessor(
			@Value("#{jobParameters['salaire.compute.date']}") LocalDate dateCalcul) {
		BasicComputeSalaireItemProcessor processor = BeanUtils.instantiateClass(BasicComputeSalaireItemProcessor.class);
		processor.setDateCalcul(dateCalcul);
		return processor;
	}

	@Bean(destroyMethod = "")
	@StepScope
	public FlatFileItemWriter<SalaireTo> salaireToItemWriter(
			@Value("#{jobParameters['salaire.compute.output.filename']}") Resource output) throws Exception {

		BeanWrapperFieldExtractor<SalaireTo> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(SALAIRE_FIELD_NAMES);

		FormatterLineAggregator<SalaireTo> lineAggregator = new FormatterLineAggregator<>();
		lineAggregator.setFormat("%s,%tF,%s,%.0f,%.0f,%.0f,%.0f");
		lineAggregator.setFieldExtractor(fieldExtractor);

		FlatFileItemWriter<SalaireTo> writer = new FlatFileItemWriter<>();
		writer.setResource(output);
		writer.setEncoding("UTF-8");
		writer.setLineAggregator(lineAggregator);

		writer.afterPropertiesSet();

		return writer;
	}

	@Bean
	public Step editFicheSalaireStep() throws Exception {
		return steps.get("editFicheSalaireStep") //
				.<SalaireTo, SalaireTo> chunk(3) //
				.reader(salaireToItemReader(COMPUTE_OUTPUT_FILE)) //
				.writer(salairePdfItemWriter(EDIT_OUTPUT_DIR, DATE_CALCUL)).build();
	}

	@Bean(destroyMethod = "")
	@StepScope
	public FlatFileItemReader<SalaireTo> salaireToItemReader(
			@Value("#{jobParameters['salaire.compute.output.filename']}") Resource input) throws Exception {

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(SALAIRE_FIELD_NAMES);

		BeanWrapperFieldSetMapper<SalaireTo> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setBeanFactory(beanFactory);
		fieldSetMapper.setPrototypeBeanName("org.transgalactica.batch.salaire.bo.SalaireTo");
		fieldSetMapper.setCustomEditors(ImmutableMap.of(LocalDate.class, new LocalDatePropertyEditor()));

		BeanWrapperFieldExtractor<SalaireTo> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[] { "nomEmploye", "dateEmbaucheEmploye", "typeEmploye", "salaireBase",
				"primeAnciennete", "primeExperience", "salaire" });

		DefaultLineMapper<SalaireTo> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		FlatFileItemReader<SalaireTo> reader = new FlatFileItemReader<>();
		reader.setResource(input);
		reader.setEncoding("UTF-8");
		reader.setLineMapper(lineMapper);

		reader.afterPropertiesSet();

		return reader;
	}

	@Bean
	@StepScope
	public ITextFicheSalairePdfItemWriter salairePdfItemWriter(
			@Value("#{jobParameters['salaire.edit.output.directory']}") Resource output,
			@Value("#{jobParameters['salaire.compute.date']}") LocalDate dateCalcul) {
		ITextFicheSalairePdfItemWriter writer = BeanUtils.instantiateClass(ITextFicheSalairePdfItemWriter.class);
		writer.setOutputDirectory(output);
		writer.setDateCalcul(dateCalcul);
		return writer;
	}

	private static final String[] SALAIRE_FIELD_NAMES = new String[] { "nomEmploye", "dateEmbaucheEmploye",
			"typeEmploye", "salaireBase", "primeAnciennete", "primeExperience", "salaire" };

	// overidden by expressions
	private final static LocalDate DATE_CALCUL = null;

	private final static Resource COMPUTE_OUTPUT_FILE = null;

	private final static Resource EDIT_OUTPUT_DIR = null;
}
