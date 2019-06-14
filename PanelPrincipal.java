import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PanelPrincipal extends JFrame {
	private BarreDeMenu menu;
	private BarreDOutil outils;
	private SelectionFiche fiche;
	private PanelAffichage affichage;
	private JScrollPane rightScrollPane;

	public PanelPrincipal() {
		this.setTitle("Arbre généalogique");
		this.setSize(800, 600);
		this.setLocation(50, 100);
		this.setMinimumSize(new Dimension(800, 600));

		this.menu = new BarreDeMenu(this);
		this.setJMenuBar(this.menu);

		this.outils = new BarreDOutil(this.menu);
		this.add(this.outils, BorderLayout.NORTH);
		this.fiche = new SelectionFiche(this, this.getWidth());
		// this.add(this.fiche, "West");

		this.affichage = new PanelAffichage(this);
		// this.add(this.affichage, "Center");
		this.rightScrollPane = new JScrollPane(this.affichage);

		JScrollPane leftScrollPane = new JScrollPane(this.fiche);
		leftScrollPane.setPreferredSize(new Dimension(200, 0));

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScrollPane, rightScrollPane);
		this.add(splitPane /* , BorderLayout.CENTER */ );
		leftScrollPane.setMinimumSize(new Dimension(200, 0));

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// init le un nouveau doccument au lancement
		this.menu.nouveau();
	}

	public void ajouterFiche(FicheGenealogique fiche) {
		if (this.fiche.getGenealogique() != null)
			this.fiche.getGenealogique().addFicheGenealogique(fiche);
		this.getSelectionFiche().maj();
	}

	public void nouveau(FicheGenealogique fiche) {
		this.affichage.nouveau(fiche);
	}

	public JScrollPane getJScrollPane() {
		return this.rightScrollPane;
	}

	public void setFichier(FichierGenealogique fichier) {
		this.fiche.setGenealogique(fichier);
	}

	public FichierGenealogique getFichier() {
		return this.fiche.getGenealogique();
	}

	public SelectionFiche getSelectionFiche() {
		return this.fiche;
	}

	public PanelAffichage getPanelAffichage() {
		return this.affichage;
	}

}
