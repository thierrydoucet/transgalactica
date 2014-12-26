package org.transgalactica.swing.logistics.view.impl;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.springframework.stereotype.Component;
import org.transgalactica.management.data.rest.bo.HangarSummaryTo;
import org.transgalactica.swing.logistics.presentationmodel.impl.HangarRecherchePresentationModel;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ComponentFactory2;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@Component
public class HangarRecherchePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ComponentFactory2 componentFactory = PanelBuilder.getDefaultComponentFactory();

	private JTextField localisationField;

	private JTable hangarTable;

	private JButton rechercherButton;

	@Inject
	private HangarRecherchePresentationModel model;

	protected HangarRecherchePanel() {
	}

	@PostConstruct
	protected void initComponents() {

		setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC, }));

		JComponent hangarTitle = componentFactory.createTitle("Liste des Hangars");
		add(hangarTitle, "1, 2, 4, 1");

		JComponent identificationSeparator = componentFactory.createSeparator("Critères de recherche",
				SwingConstants.LEFT);
		add(identificationSeparator, "1, 4, 4, 1");

		JLabel localisationLabel = componentFactory.createLabel("Localistation :");
		add(localisationLabel, "2, 6, right, default");

		localisationField = BasicComponentFactory.createTextField(model.getCriteresModel().getValueModel(
				"localisationHangar"));
		localisationField.setColumns(30);
		add(localisationField, "4, 6, fill, default");

		rechercherButton = new JButton(model.getRechercherAction());
		add(rechercherButton, "4, 8, right, default");

		JComponent vaisseauxSeparator = componentFactory.createSeparator("Résultats de recherche", SwingConstants.LEFT);
		add(vaisseauxSeparator, "1, 10, 4, 1");

		hangarTable = BasicComponentFactory.createTable(model.getResultatsModel(),
				new AbstractTableAdapter<HangarSummaryTo>("Numéro", "Localisation", "nombre d'emplacements") {

					private static final long serialVersionUID = 1L;

					@Override
					public Object getValueAt(int rowIndex, int columnIndex) {
						HangarSummaryTo hangar = getRow(rowIndex);
						switch (columnIndex) {
						case 0:
							return hangar.getNumero();
						case 1:
							return hangar.getLocalisation();
						case 2:
							return hangar.getNombreEmplacements();
						default:
							return null;
						}
					}

				});
		add(new JScrollPane(hangarTable), "2, 12, 3, 1, fill, fill");
	}
}
