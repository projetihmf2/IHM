import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.FileDialog;
import javax.swing.filechooser.*;
/*----------------------------------------------------------*/
/*CLASSE QUI CREE LE MENU PRINCIPAL ET GERE C'EST EVENEMENT'*/
/*----------------------------------------------------------*/
public class BarreDeMenu extends JMenuBar implements ActionListener
{
	//Menu principal
	private JMenuBar menubMaBarre ;
	//element menu fichier
	private JMenu menuFichier;
	private JMenuItem itemNouveau;
	private JMenuItem itemEnrengister;
	private JMenuItem itemEnrengisterSous;
	private JMenuItem itemCharger;
	private JMenuItem itemQuitter;
	//element menu fiche
	private JMenu menuFiche;
	private JMenuItem itemAjouter;
	private JMenuItem itemSupprimer;
	private JMenuItem itemModifier;
	//element menu  outils
	private JMenu menuOutils;
	private JMenuItem itemAgrandir;
	private JMenuItem itemReduire;
	private JMenuItem itemAugmenter;
	private JMenuItem itemDiminuer;
	//element menu a propos de
	private JMenu menuAPropos;
	private JMenuItem itemAPropos;
	//Bulle de dial
	private JFileChooser choix;
	private FileNameExtensionFilter filter;
	//principal
	private PanelPrincipal panelPrincipal ;

	public BarreDeMenu(PanelPrincipal panelPrincipal)
	{
		//init
		this.menubMaBarre = new JMenuBar();
		this.panelPrincipal=panelPrincipal;
		//menu FICHIER
		this.menuFichier         = new JMenu("Fichier");
		this.itemNouveau         = this.creeItem(this.menuFichier,"Nouveau",true);
		this.itemEnrengister     = this.creeItem(this.menuFichier,"Enrengister",true);
		this.itemEnrengisterSous = this.creeItem(this.menuFichier,"Enrengister Sous",true);
		this.itemCharger         = this.creeItem(this.menuFichier,"Charger",true);
		this.itemQuitter         = this.creeItem(this.menuFichier,"Quitter",false);
		menubMaBarre.add(menuFichier);
		//menu FICHE
		this.menuFiche     = new JMenu("Fiche");
		this.itemAjouter   = this.creeItem(this.menuFiche,"Ajouter",true);
		this.itemSupprimer = this.creeItem(this.menuFiche,"Supprimer",true);
		this.itemModifier  = this.creeItem(this.menuFiche,"Modifier",false);
		this.menubMaBarre.add(menuFiche);
		//menu OUTILS
		this.menuOutils    = new JMenu("Outils");
		this.itemAgrandir  = this.creeItem(this.menuOutils,"Agrandir",true);
		this.itemReduire   = this.creeItem(this.menuOutils,"Reduire",true);
		this.itemAugmenter = this.creeItem(this.menuOutils,"Augmenter",true);
		this.itemDiminuer  = this.creeItem(this.menuOutils,"Diminuer",false);
		this.menubMaBarre.add(this.menuOutils);
		//menu A PROPOS DE
		this.menuAPropos = new JMenu("A Propos");
		this.itemAPropos = this.creeItem(this.menuAPropos,"A propos",false);

		menubMaBarre.add(menuAPropos);
		//ajout au panel
		this.add(menubMaBarre);

		//Initialisation du filechooser
		this.choix = new JFileChooser();
		this.choix.setDialogTitle("Séléctionner un fichier");
		this.choix.setAcceptAllFileFilterUsed(false);
		this.filter = new FileNameExtensionFilter("Fiche généalogique", "txt");
		this.choix.addChoosableFileFilter(filter);
		this.choix.setFileFilter(filter);
		this.choix.setFileSelectionMode(JFileChooser.FILES_ONLY);
	}
	//gere les evenenement
	public void actionPerformed(ActionEvent e)
	{
		//menu fichier
		if(e.getSource() == this.itemNouveau)         this.nouveau();
		if(e.getSource() == this.itemEnrengister)     this.Enrengister();
		if(e.getSource() == this.itemEnrengisterSous) this.EnrengisterSous();
		if(e.getSource() == this.itemCharger)         this.Charger();
		if(e.getSource() == this.itemQuitter)         this.Quitter();
		//menu fiche
		if(e.getSource() == this.itemAjouter)   this.Ajouter();
		if(e.getSource() == this.itemSupprimer) this.Supprimer();
		if(e.getSource() == this.itemModifier)  this.Modifier();
		//menu outils
		if(e.getSource() == this.itemAgrandir)  this.Agrandir();
		if(e.getSource() == this.itemReduire)   this.Reduire();
		if(e.getSource() == this.itemAugmenter) this.Augmenter();
		if(e.getSource() == this.itemDiminuer)  this.Diminuer();
		//menu a propos
		if(e.getSource() == this.itemAPropos) this.aPropos();
	}

	//cree un item est l'ajoute a son pere
	private JMenuItem creeItem(JMenu pere , String nom,boolean separateur)
	{
		JMenuItem fils= new JMenuItem(nom);
		fils.addActionListener(this); 	// ajout a la lecture des evenenement
		pere.add(fils);	                // ajout a menu
		if(separateur)pere.addSeparator();
		return fils;
	}

	//MENU FICHIER
	public void nouveau()
	{
		this.panelPrincipal.setFichier(new FichierGenealogique());
		this.panelPrincipal.setTitle("Nouvel Arbre généalogique");
	}

	private void Enrengister()
	{
		//si le fichier a été charger
		 if(this.panelPrincipal.getFichier().getCharger())
		 {
		 	this.panelPrincipal.getFichier().enregistrerFichier(this.choix.getSelectedFile().getAbsolutePath()) ;
		 }
		 else
		 {
		 	this.EnrengisterSous();
		 }
	}

	private void EnrengisterSous()
	{
		// parent component of the dialog
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Enrengistrer Sous");

		int userSelection = fileChooser.showSaveDialog(this.panelPrincipal);

		if (userSelection == JFileChooser.APPROVE_OPTION)
		{
			String chemin = fileChooser.getSelectedFile().getAbsolutePath();
			chemin+=".txt";
			if(chemin != null ) this.panelPrincipal.getFichier().enregistrerFichier(chemin);
		}

	}

	private void Charger()
	{
		FichierGenealogique fichier = new FichierGenealogique();
		//ouvre la boite de dial
		this.choix.showOpenDialog(this);
		//prend le chemin absolue
		String chemin = this.choix.getSelectedFile().getAbsolutePath();
		if(chemin != null )
		{
			//charge
			fichier.chargerFichier(this.choix.getSelectedFile().getAbsolutePath());
			//modifie la selection
			this.panelPrincipal.setFichier(fichier);
			// previent que le fichier a été charger , facilite l'enregistrer
			this.panelPrincipal.getFichier().setCharger(true);
		}
		this.panelPrincipal.setTitle(this.choix.getSelectedFile().getName());

	}

	private void Quitter()
	{
		System.exit(0);
	}

	// MENU FICHE
	private void Ajouter()
	{
		if(this.panelPrincipal.getFichier() == null)
		{
			JOptionPane.showMessageDialog(null,"Il n'y a aucun fichier de charger\nVeuillez en charger un ou en créer un.", "Ajouter", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			new NouvelleFiche(this.panelPrincipal);
			this.panelPrincipal.getSelectionFiche().maj();
		}
	}
	private void Supprimer()
	{
		if(this.panelPrincipal.getFichier() == null)
		{
			JOptionPane.showMessageDialog(null,"Il n'y a pas de fiches", "Supprimer", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			ArrayList<FicheGenealogique> liste = this.panelPrincipal.getFichier().getListeFiches();
			System.out.println(liste);
			Object[] choix = new Object[liste.size()];
			for(int i = 0; i < liste.size(); i++)
			{
				choix[i] = liste.get(i);
			}
			String s = (String)JOptionPane.showInputDialog(null,"Fiche à supprimer : ", "Supprimer", JOptionPane.PLAIN_MESSAGE, null, choix,"");

		}
}
	private void Modifier()
	{
		System.out.println("this.itemModifier");
	}
	// MENU OUTILS
	private void Agrandir()
	{
		System.out.println("this.itemAgrandir");
	}
	private void Reduire()
	{
		System.out.println("this.itemReduire");
	}
	private void Augmenter()
	{
		System.out.println("this.itemAugmenter");
	}
	private void Diminuer()
	{
		System.out.println("this.itemDiminuer");
	}
	// MENU A PROPOS
	private void aPropos()
	{
		JOptionPane.showMessageDialog(null, "Logiciel d'arbre généalogique créeer par Romain Loisel et ... \n\nIUT Informatique Groupe F2", "A propos", JOptionPane.INFORMATION_MESSAGE);
	}

}
