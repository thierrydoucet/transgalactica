package org.transgalactica.swing;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.transgalactica.swing.logistics.view.impl.HangarRecherchePanel;

public class Application {

	protected Application() {
	}

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/org/transgalactica/swing/config/context/beanRefFactory.xml");
		final HangarRecherchePanel panel = context.getBean(HangarRecherchePanel.class);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame dialog = new JFrame();
				dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				URL url = ClassLoader.getSystemResource("org/transgalactica/swing/style/icon_48x48.png");
				dialog.setIconImage(new ImageIcon(url).getImage());
				panel.setBorder(new EmptyBorder(5, 5, 5, 5));
				dialog.setContentPane(panel);
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}
}
