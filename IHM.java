import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
/*----------------------------------------------------------*/
/*CLASSE QUI CREE LE PANEL PRINCIPAL ET GERE SES EVENEMENTS */
/*----------------------------------------------------------*/
public class IHM extends JFrame
{
	private BarreDeMenu    menu;
	private BarreDOutil    outils;
	private SelectionFiche fiche;
	private PanelAffichage affichage;
	private JScrollPane    rightScrollPane;

	public IHM()
	{
		this.setTitle("Arbre généalogique");
		this.setSize(800, 600);
		this.setLocation(50, 100);
		this.setMinimumSize(new Dimension(800, 600));

		//Création des composants
		this.menu                  = new BarreDeMenu(this);
		this.outils                = new BarreDOutil(this.menu);
		this.fiche                 = new SelectionFiche(this, this.getWidth());
		this.affichage             = new PanelAffichage(this);
		this.rightScrollPane       = new JScrollPane(this.affichage);
		JScrollPane leftScrollPane = new JScrollPane(this.fiche);
		JSplitPane splitPane       = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScrollPane, rightScrollPane);

		//Ajout
		this.add(this.outils, BorderLayout.NORTH);
		this.add(splitPane);

		//Traitement
		this.setJMenuBar(this.menu);
		leftScrollPane.setPreferredSize(new Dimension(200, 0));
		leftScrollPane.setMinimumSize(new Dimension(200, 0));
		rightScrollPane.setMinimumSize(new Dimension((int)(this.getWidth()/1.4),0));
		rightScrollPane.getVerticalScrollBar().setUnitIncrement(40);
		rightScrollPane.getVerticalScrollBar().setBlockIncrement(40);
		rightScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
		rightScrollPane.getHorizontalScrollBar().setBlockIncrement(40);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Initialise le un nouveau document au lancement
		this.menu.nouveau();
	}

	//Ajoute à l'arbre une fiche passée en paramètre
	public void ajouterFiche(FicheGenealogique fiche)
	{
		if (this.fiche.getGenealogique() != null)
			this.fiche.getGenealogique().addFicheGenealogique(fiche);
		this.getSelectionFiche().maj();
	}

  //Creation de l'arbre lors de l'envoi d'un fiche
	public void nouveau(FicheGenealogique fiche)
	{
		this.affichage.nouveau(fiche);
	}

	//Envoi la fiche
	public void setFichier(FichierGenealogique fichier)
	{
		this.fiche.setGenealogique(fichier);
	}

	public JScrollPane getJScrollPane      () { return this.rightScrollPane;         }
	public FichierGenealogique getFichier  () { return this.fiche.getGenealogique(); }
	public SelectionFiche getSelectionFiche() { return this.fiche;                   }
	public PanelAffichage getPanelAffichage() { return this.affichage;               }
}
