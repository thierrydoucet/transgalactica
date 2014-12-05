package org.transgalactica.swing.logistics.view.impl;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ComponentFactory2;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class VaisseauDetailPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ComponentFactory2 componentFactory = PanelBuilder.getDefaultComponentFactory();

	private JTextField immatriculationField;

	private JTextField modeleField;

	private JTextField vitesseField;

	private JTextField multiplicateurHyperdriveField;

	private JTextField rayonActionField;

	private JTextField nombreDePassagersField;

	private JTextField capaciteDeFretField;

	private JTextField numeroHangarField;

	private JTextField localisationHangarField;

	private JButton detailHangarButton;

	private Action detailHangarAction;

	protected VaisseauDetailPanel() {
		initComponents();
	}

	private void initComponents() {

		setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, }));

		JComponent hangarTitle = componentFactory.createTitle("Détail du vaisseau");
		add(hangarTitle, "1, 2, 4, 1");

		JComponent identificationSeparator = componentFactory.createSeparator("Identification", SwingConstants.LEFT);
		add(identificationSeparator, "1, 4, 4, 1");

		JLabel immatriculationLabel = componentFactory.createLabel("Immatriculation :");
		add(immatriculationLabel, "2, 6, right, default");

		immatriculationField = new JTextField();
		immatriculationField.setColumns(30);
		add(immatriculationField, "4, 6, fill, default");

		JLabel modeleLabel = componentFactory.createLabel("Modèle :");
		add(modeleLabel, "2, 8, right, default");

		modeleField = new JTextField();
		modeleField.setColumns(30);
		add(modeleField, "4, 8, fill, default");

		JComponent caracteristiquesSeparator = componentFactory
				.createSeparator("Caractéristiques", SwingConstants.LEFT);
		add(caracteristiquesSeparator, "1, 10, 4, 1");

		JLabel vitesseLabel = componentFactory.createLabel("Vitesse :");
		add(vitesseLabel, "2, 12, right, default");

		vitesseField = new JTextField();
		add(vitesseField, "4, 12, fill, default");

		JLabel rayonActionLabel = componentFactory.createLabel("Rayon d'action :");
		add(rayonActionLabel, "2, 14, right, default");

		rayonActionField = new JTextField();
		add(rayonActionField, "4, 14, fill, default");

		JLabel nombreDePassagersLabel = componentFactory.createLabel("Nombre de passagers :");
		add(nombreDePassagersLabel, "2, 16, right, default");

		nombreDePassagersField = new JTextField();
		add(nombreDePassagersField, "4, 16, fill, default");

		JLabel capaciteDeFretLabel = componentFactory.createLabel("Capacité de fret :");
		add(capaciteDeFretLabel, "2, 18, right, default");

		capaciteDeFretField = new JTextField();
		add(capaciteDeFretField, "4, 18, fill, default");

		JLabel multiplicateurHyperdriveLabel = componentFactory.createLabel("x Hyperdrive :");
		add(multiplicateurHyperdriveLabel, "2, 20, right, default");

		multiplicateurHyperdriveField = new JTextField();
		add(multiplicateurHyperdriveField, "4, 20, fill, default");

		JComponent hangarSeparator = componentFactory.createSeparator("Hangar", SwingConstants.LEFT);
		add(hangarSeparator, "1, 22, 4, 1");

		JLabel numeroHangarLabel = componentFactory.createReadOnlyLabel("Numéro :");
		add(numeroHangarLabel, "2, 24, right, default");

		numeroHangarField = new JTextField();
		numeroHangarField.setEditable(false);
		add(numeroHangarField, "4, 24, fill, default");

		JLabel localisationHangarLabel = componentFactory.createReadOnlyLabel("Localisation :");
		add(localisationHangarLabel, "2, 26, right, default");

		localisationHangarField = new JTextField();
		localisationHangarField.setEditable(false);
		add(localisationHangarField, "4, 26, fill, default");

		detailHangarButton = new JButton(detailHangarAction);
		add(detailHangarButton, "4, 28, right, default");
	}
}
