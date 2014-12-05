package org.transgalactica.swing.logistics.view.impl;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ComponentFactory2;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class HangarDetailPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ComponentFactory2 componentFactory = PanelBuilder.getDefaultComponentFactory();

	private JTextField localisationField;

	private JTextField nombreEmplacementsField;

	private JTable vaisseauxTable;

	protected HangarDetailPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC, }));

		JComponent hangarTitle = componentFactory.createTitle("Détail du hangar");
		add(hangarTitle, "1, 2, 4, 1");

		JComponent identificationSeparator = componentFactory.createSeparator("Caractéristiques", SwingConstants.LEFT);
		add(identificationSeparator, "1, 4, 4, 1");

		JLabel localisationLabel = componentFactory.createLabel("Localistation :");
		add(localisationLabel, "2, 6, right, default");

		localisationField = new JTextField();
		localisationField.setColumns(30);
		add(localisationField, "4, 6, fill, default");

		JLabel nombreEmplacementsLabel = componentFactory.createLabel("Nombre d'emplacements :");
		add(nombreEmplacementsLabel, "2, 8, right, default");

		nombreEmplacementsField = new JTextField();
		add(nombreEmplacementsField, "4, 8, fill, default");

		JComponent vaisseauxSeparator = componentFactory.createSeparator("Vaisseaux", SwingConstants.LEFT);
		add(vaisseauxSeparator, "1, 10, 4, 1");

		vaisseauxTable = new JTable();
		add(new JScrollPane(vaisseauxTable), "2, 12, 3, 1, fill, fill");
	}
}
