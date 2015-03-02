package org.transgalactica.batch.salaire.item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.transgalactica.batch.salaire.bo.SalaireTo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * <p>
 * L'affichage Japonais nécessite l'installation des extensions de police du
 * visualiseur PDF. Soit le package <code>poppler-data</code> pour Okular.
 * </p>
 * <p>
 * L'affichage Arabe nécessite l'installation des polices Arabes (Arabeyes).
 * </p>
 * 
 * @author Thierry
 */
public class ITextFicheSalairePdfItemWriter implements ItemWriter<SalaireTo> {

	private static final int HEADER_SPACING = 20;

	private static final int CONTENT_SPACING = 10;

	private static final int HEADER_FONT_SIZE = 18;

	private static final int CONTENT_FONT_SIZE = 12;

	@Inject
	private MessageSource messageSource;

	@Inject
	private ResourceLoader resourceLoader;

	private Date dateCalcul;

	private Resource outputDirectory;

	private Font textFont;

	private Font titleFont;

	private Font emphaseFont;

	private int rundirection = PdfWriter.RUN_DIRECTION_DEFAULT;

	protected ITextFicheSalairePdfItemWriter() {
	}

	@PostConstruct
	public void initialiseFonts() throws DocumentException, IOException {
		BaseFont baseFont = BaseFont
				.createFont(getMessage("font.name"), getMessage("font.encoding"), BaseFont.EMBEDDED);
		textFont = new Font(baseFont, CONTENT_FONT_SIZE);
		titleFont = new Font(baseFont, HEADER_FONT_SIZE);
		emphaseFont = new Font(baseFont, CONTENT_FONT_SIZE, Font.BOLD);
		rundirection = Integer.valueOf(getMessage("font.runDirection"));
	}

	@Override
	public void write(List<? extends SalaireTo> salaires) throws DocumentException, IOException {
		for (SalaireTo salaire : salaires) {
			write(salaire);
		}
	}

	protected void write(SalaireTo salaire) throws DocumentException, IOException {
		Document document = createPdf(salaire);
		document.open();
		addMetaData(document);
		addEntete(document);
		addEmployeInformation(document, salaire);
		addElementsSalaire(document, salaire);
		document.close();
	}

	private Document createPdf(SalaireTo salaire) throws DocumentException, IOException {
		String resourceName = String.format("%1$s_%2$tY-%2$tm.pdf", salaire.getNomEmploye(), dateCalcul);
		Resource resource = outputDirectory.createRelative(resourceName);
		Document document = new Document(PageSize.A5);
		PdfWriter.getInstance(document, new FileOutputStream(resource.getFile()));
		return document;
	}

	private void addMetaData(Document document) {
		document.addTitle(getMessage("message.metadata.titre", dateCalcul));
		document.addAuthor(getMessage("message.metadata.auteur"));
		document.addCreator(getMessage("message.metadata.auteur"));
	}

	private void addEntete(Document document) throws DocumentException, IOException {
		Paragraph logo = new Paragraph();
		logo.setSpacingAfter(HEADER_SPACING);
		Resource logoresource = resourceLoader.getResource(getMessage("image.logo"));
		Assert.notNull(logo);
		logo.add(Image.getInstance(logoresource.getURL()));
		document.add(logo);

		Paragraph titre = new Paragraph(getMessage("message.entete.titre"), titleFont);
		titre.setSpacingAfter(HEADER_SPACING);
		titre.setAlignment(Element.ALIGN_CENTER);

		// TODO plus de support de multicolumnText, pas de RunDirection sur le
		// Paragraph, ColumnText ne fonctionne pas en mode composite ...

		// MultiColumnText ct = new MultiColumnText();
		// ct.addSimpleColumn(document.left(), document.right());
		// ct.setRunDirection(rundirection);
		// ct.addElement(titre);
		//
		// document.add(ct);
		document.add(titre);
	}

	private void addEmployeInformation(Document document, SalaireTo salaire) throws DocumentException {
		Paragraph info = new Paragraph();
		info.setAlignment(Element.ALIGN_LEFT);
		info.setSpacingAfter(CONTENT_SPACING);
		info.setSpacingBefore(CONTENT_SPACING);
		info.add(new Phrase(getMessage("message.info.employe", salaire.getNomEmploye()), textFont));
		info.add(Chunk.NEWLINE);
		info.add(new Phrase(getMessage("message.info.profession", salaire.getTypeEmploye().name()), textFont));
		info.add(Chunk.NEWLINE);
		info.add(new Phrase(getMessage("message.info.periode", dateCalcul), textFont));

		// TODO plus de support de multicolumnText, pas de RunDirection sur le
		// Paragraph, ColumnText ne fonctionne pas en mode composite ...

		// MultiColumnText ct = new MultiColumnText();
		// ct.addSimpleColumn(document.left(), document.right());
		// ct.setRunDirection(rundirection);
		// ct.addElement(info);
		//
		// document.add(ct);
		document.add(info);
	}

	private void addElementsSalaire(Document document, SalaireTo salaire) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 65, 35 });
		table.setExtendLastRow(true);
		table.setRunDirection(rundirection);
		table.setHeaderRows(2);
		table.setFooterRows(1);
		table.getDefaultCell().setPaddingTop(5);

		table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(new Phrase(getMessage("message.element.titreDetail"), textFont));
		table.addCell(new Phrase(getMessage("message.element.titreMontant"), textFont));
		table.getDefaultCell().setBackgroundColor(null);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(new Phrase(getMessage("message.element.total"), emphaseFont));
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(new Phrase(salaire.getSalaire().toString(), emphaseFont));

		// detail des elements de salaire
		table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);

		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(new Phrase(getMessage("message.element.salaire"), textFont));
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(new Phrase(salaire.getSalaireBase().toString(), textFont));

		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(new Phrase(getMessage("message.element.anciennete"), textFont));
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(new Phrase(salaire.getPrimeAnciennete().toString(), textFont));

		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(new Phrase(getMessage("message.element.experience"), textFont));
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(new Phrase(salaire.getPrimeExperience().toString(), textFont));

		document.add(table);
	}

	private String getMessage(String key, Object... params) {
		return messageSource.getMessage(key, params, Locale.getDefault());
	}

	/*
	 * IOC
	 */

	@Required
	public void setOutputDirectory(Resource outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	/**
	 * @param dateCalcul
	 */
	@Required
	public void setDateCalcul(LocalDate dateCalcul) {
		// convert to localized date (format)
		this.dateCalcul = Date.from(dateCalcul.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
