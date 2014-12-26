package org.transgalactica.swing.logistics.presentationmodel.impl;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;
import org.transgalactica.management.data.rest.bo.HangarSearchCriteria;
import org.transgalactica.management.data.rest.bo.HangarSummaryTo;
import org.transgalactica.swing.logistics.service.HangarService;

import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.list.SelectionInList;

@Component
public class HangarRecherchePresentationModel {

	@Inject
	private BeanFactory beanFactory;

	@Inject
	private HangarService hangarService;

	private BeanAdapter<HangarSearchCriteria> criteres;

	private SelectionInList<HangarSummaryTo> resultats = new SelectionInList<>();

	private Action rechercherAction = new AbstractAction("Rechercher") {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			rechercherHangars();
		}
	};

	protected HangarRecherchePresentationModel() {
	}

	@PostConstruct
	protected void initializeCriteres() {
		this.criteres = new BeanAdapter<HangarSearchCriteria>(beanFactory.getBean(HangarSearchCriteria.class));
	}

	public BeanAdapter<HangarSearchCriteria> getCriteresModel() {
		return criteres;
	}

	public SelectionInList<HangarSummaryTo> getResultatsModel() {
		return resultats;
	}

	public Action getRechercherAction() {
		return rechercherAction;
	}

	protected void rechercherHangars() {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ImageIcon icon = new ImageIcon(ClassLoader
						.getSystemResource("org/transgalactica/swing/style/sablier.gif"));
				rechercherAction.putValue(Action.LARGE_ICON_KEY, icon);
				rechercherAction.putValue(Action.SMALL_ICON, icon);
				rechercherAction.setEnabled(false);
				resultats.setList(null);
			}
		});

		new SwingWorker<List<HangarSummaryTo>, Object>() {
			@Override
			protected List<HangarSummaryTo> doInBackground() {
				return hangarService.rechercherHangars(criteres.getBean());
			}

			@Override
			protected void done() {
				try {
					rechercherAction.putValue(Action.LARGE_ICON_KEY, null);
					rechercherAction.putValue(Action.SMALL_ICON, null);
					rechercherAction.setEnabled(true);
					resultats.setList(get());
				}
				catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
			}
		}.execute();
	}
}
