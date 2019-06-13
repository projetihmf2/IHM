import javax.swing.*;

import java.awt.*;
import java.awt.FileDialog;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.*;
import java.util.*;
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
	private JMenuItem itemMonter;
	private JMenuItem itemDescendre;
	//element menu a propos de
	private JMenu menuAPropos;
	private JMenuItem itemAPropos;
	//Bulle de dial
	private JFileChooser choix;
	private FileNameExtensionFilter filter;
	//principal
	private PanelPrincipal panelPrincipal ;
	private boolean        anulerModif;

	public BarreDeMenu(PanelPrincipal panelPrincipal)
	{
		//init
		this.menubMaBarre = new JMenuBar();
		this.panelPrincipal=panelPrincipal;
		//menu FICHIER
		this.menuFichier         = new JMenu("Fichier");
		this.itemNouveau         = this.creeItem(this.menuFichier,"Nouveau",true);
		this.itemEnrengister     = this.creeItem(this.menuFichier,"Enregister",true);
		this.itemEnrengisterSous = this.creeItem(this.menuFichier,"Enregister Sous",true);
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
		this.itemMonter = this.creeItem(this.menuOutils,"Monter",true);
		this.itemDescendre  = this.creeItem(this.menuOutils,"Descendre",false);
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
		if(e.getSource() == this.itemEnrengister)     this.enrengister();
		if(e.getSource() == this.itemEnrengisterSous) this.enrengisterSous();
		if(e.getSource() == this.itemCharger)         this.charger();
		if(e.getSource() == this.itemQuitter)         this.quitter();
		//menu fiche
		if(e.getSource() == this.itemAjouter)   this.ajouter();
		if(e.getSource() == this.itemSupprimer) this.supprimer();
		if(e.getSource() == this.itemModifier)  this.modifier();
		//menu outils
		if(e.getSource() == this.itemAgrandir)   this.zoom();
		if(e.getSource() == this.itemReduire)    this.deZoom();
		if(e.getSource() == this.itemMonter)     this.monter();
		if(e.getSource() == this.itemDescendre)  this.descendre();
		//menu a propos
		if(e.getSource() == this.itemAPropos) this.aPropos();
	}

	//cree un item et l'ajoute a son pere
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

	public void enrengister()
	{
		//si le fichier a été charger
		 if(this.panelPrincipal.getFichier().getCharger())
		 {
			int rep = JOptionPane.showConfirmDialog(null,"Etes vous sûr de vouloir enregistrer", "Enregister", JOptionPane.YES_NO_OPTION);
			if(rep == JOptionPane.YES_OPTION)
			{
				this.panelPrincipal.getFichier().enregistrerFichier(this.choix.getSelectedFile().getAbsolutePath()) ;
			}
		 }
		 else
		 {
		 	this.enrengisterSous();
		 }
	}

	public void enrengisterSous()
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

	public void charger()
	{
		FichierGenealogique fichier = new FichierGenealogique();
		//ouvre la boite de dial
		this.choix.showOpenDialog(this);
		//prend le chemin absolue
		if(this.choix.getSelectedFile() != null) //permet de controler si l'utilisateur anule
		{
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
	}

	private void quitter()
	{
		//Demande une confirmation avant de quitter
		int choix = JOptionPane.showConfirmDialog(null,"Etes vous sûr de vouloir quitter sans enregistrer ?", "Quitter", JOptionPane.YES_NO_OPTION);
		if(choix == JOptionPane.YES_OPTION)
		System.exit(0);
	}

	// MENU FICHE
	public void ajouter()
	{
		//Créer un nouveau fichier, méthode appelée au lancement
		new NouvelleFiche(this.panelPrincipal);
		this.panelPrincipal.getSelectionFiche().maj();
	}

	public void supprimer()
	{
		//Controle si un fichier est charger ou si c'est un nouveau (Normalement c'est toujours le cas)
		if(this.panelPrincipal.getFichier() == null)
		{
			JOptionPane.showMessageDialog(null,"Il n'y a pas de fiches", "Supprimer", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			//Conversion de la liste  des fiches en Object pour les manipuler avec un inputDialog
			ArrayList<FicheGenealogique> liste = this.panelPrincipal.getFichier().getListeFiches();
			Object[] choix = new Object[liste.size()];
			for(int i = 0; i < liste.size(); i++)
			{
				choix[i] = liste.get(i).getNom() + " " + liste.get(i).getPrenom();
			}
			//Controle si il y a des fiches dans le fichier actuel sinon on ne peut pas supprimer de fiches
			if(choix.length == 0) { JOptionPane.showMessageDialog(null,"Il n'y a pas de fiches", "Supprimer", JOptionPane.WARNING_MESSAGE); }
			else
			{
				//Choix de la liste à supprimer
				String s = (String)JOptionPane.showInputDialog(null,"Fiche à supprimer : ", "Supprimer", JOptionPane.PLAIN_MESSAGE, null, choix,"");
				for(int i = 0; i < choix.length; i++)
				{
					if(s != null && s.equals(liste.get(i).getNom() + " " + liste.get(i).getPrenom()))
					{
						this.panelPrincipal.getFichier().supprimerFiche(i);
						this.panelPrincipal.getSelectionFiche().maj();
						break; //Sort de la boucle car la taille diminue de 1 et la tache a été effectuée
					}
				}
			}
		this.panelPrincipal.getSelectionFiche().maj();
		}
	}

	public void modifier()
	{
		if(this.panelPrincipal.getFichier() == null)
		{
			JOptionPane.showMessageDialog(null,"Il n'y a pas de fiches", "Modifier", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			ArrayList<FicheGenealogique> liste = this.panelPrincipal.getFichier().getListeFiches();
			Object[] choix = new Object[liste.size()];
			for(int i = 0; i < liste.size(); i++)
			{
				choix[i] = liste.get(i).getNom() + " " + liste.get(i).getPrenom();
			}
			if(choix.length == 0) { JOptionPane.showMessageDialog(null,"Il n'y a pas de fiches", "Modifer", JOptionPane.WARNING_MESSAGE); }
			else
			{
				String s = (String)JOptionPane.showInputDialog(null,"Fiche à modifier : ", "Modifier", JOptionPane.PLAIN_MESSAGE, null, choix,"");
				for(int i = 0; i < choix.length; i++)
				{
					if(s != null && s.equals(liste.get(i).getNom() + " " + liste.get(i).getPrenom()))
					{
						FicheGenealogique ficheActuelle = this.panelPrincipal.getFichier().contains(liste.get(i));
						new NouvelleFiche(ficheActuelle, this.panelPrincipal);
						this.panelPrincipal.getSelectionFiche().maj();
					}
				}
			}
		}
		this.panelPrincipal.getSelectionFiche().maj();
	}
	// MENU OUTILS
	public void zoom()
	{
		this.panelPrincipal.getPanelAffichage().zoom();
	}
	public void deZoom()
	{
		this.panelPrincipal.getPanelAffichage().deZoom();
	}
	public void monter()
	{
		this.panelPrincipal.getPanelAffichage().monter();
	}
	public void descendre()
	{
		this.panelPrincipal.getPanelAffichage().descendre();
	}
	// MENU A PROPOS
	public void aPropos()
	{
		String message = "Logiciel d'arbre généalogique créé par ";
		message += "Romain Loisel, Arthur Baradel \net Raphaël Lefevre \n\nIUT Informatique Groupe F2 - Le Havre";
		JOptionPane.showMessageDialog(null, message, "A propos", JOptionPane.INFORMATION_MESSAGE);
	}
}
