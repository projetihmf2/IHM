import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.filechooser.*;
import java.util.*;
/*----------------------------------------------------------*/
/*CLASSE QUI CREE LE MENU PRINCIPAL ET GERE SES EVENEMENTS  */
/*----------------------------------------------------------*/
public class BarreDeMenu extends JMenuBar implements ActionListener
{
	//Menu principal
	private JMenuBar  menubMaBarre ;
	//elements menu fichier
	private JMenu     menuFichier;
	private JMenuItem itemNouveau;
	private JMenuItem itemEnrengister;
	private JMenuItem itemEnrengisterSous;
	private JMenuItem itemCharger;
	private JMenuItem itemQuitter;
	//element menu fiche
	private JMenu     menuFiche;
	private JMenuItem itemAjouter;
	private JMenuItem itemSupprimer;
	private JMenuItem itemModifier;
	//element menu  outils
	private JMenu     menuOutils;
	private JMenuItem itemAgrandir;
	private JMenuItem itemReduire;
	private JMenuItem itemMonter;
	private JMenuItem itemDescendre;
	//element menu a propos de
	private JMenu     menuAPropos;
	private JMenuItem itemAPropos;
	//Bulle de dial
	private JFileChooser            choix;
	private FileNameExtensionFilter filter;
	//principal
	private IHM ihm ;
	private boolean        anulerModif;

	public BarreDeMenu(IHM ihm)
	{
		//init
		this.menubMaBarre   = new JMenuBar();
		this.ihm = ihm;
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
		this.filter = new FileNameExtensionFilter("Fiche généalogique", "gene");
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
	//Méthode appelée au lancement du programme, permet de créer un nouveau fichier généalogique
	public void nouveau()
	{
		if(this.ihm.getFichier() != null && this.ihm.getFichier().getListeFiches().size() > 0)
		{//Vérification pour ne pas perdre les données déja créer si l'utilisateur n'a pas enregistrer le fichier
			int rep = JOptionPane.showConfirmDialog(null,"Etes vous sûr de vouloir créer un nouveau fichier ?", "Nouveau", JOptionPane.YES_NO_OPTION);
			if(rep == JOptionPane.YES_OPTION)
			{
				this.ihm.setFichier(new FichierGenealogique());
				this.ihm.setTitle("Nouvel Arbre généalogique");
			}
		}
		else
		{
			this.ihm.setFichier(new FichierGenealogique());
			this.ihm.setTitle("Nouvel Arbre généalogique");
		}
	}

	//Méthode pour enregistrer l'arbre actuellement charger
	public void enrengister()
	{
		//si le fichier a été charger
		 if(this.ihm.getFichier().getCharger())
		 {
			int rep = JOptionPane.showConfirmDialog(null,"Etes vous sûr de vouloir enregistrer", "Enregister", JOptionPane.YES_NO_OPTION);
			if(rep == JOptionPane.YES_OPTION)
			{
				this.ihm.getFichier().enregistrerFichier(this.choix.getSelectedFile().getAbsolutePath()) ;
			}
		 }
		 else
		 {
		 	this.enrengisterSous();
		 }
	}

	//Méthode pour enregistrer l'arbre à un endroit donné
	public void enrengisterSous()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Enrengistrer Sous");

		int userSelection = fileChooser.showSaveDialog(this.ihm);

		if (userSelection == JFileChooser.APPROVE_OPTION)
		{
			String chemin = fileChooser.getSelectedFile().getAbsolutePath();
			chemin+=".gene";
			if(chemin != null ) this.ihm.getFichier().enregistrerFichier(chemin);
		}

	}

	//Méthode pour charger un fichier depuis un endroit donné
	public void charger()
	{
		FichierGenealogique fichier = new FichierGenealogique();
		//ouvre la boite de dial
		int reponse = this.choix.showOpenDialog(this);
		//prend le chemin absolue
		if(this.choix.getSelectedFile() != null && reponse != JFileChooser.CANCEL_OPTION) //permet de controler si l'utilisateur anule
		{
			String chemin = this.choix.getSelectedFile().getAbsolutePath();
			if(chemin != null )
			{
				//charge
				fichier.chargerFichier(this.choix.getSelectedFile().getAbsolutePath());
				//modifie la selection
				this.ihm.setFichier(fichier);
				// previent que le fichier a été charger , facilite l'enregistrer
				this.ihm.getFichier().setCharger(true);
			}
			this.ihm.setTitle(this.choix.getSelectedFile().getName());
		}
	}

	//Méthode pour quitter le programme
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
		new NouvelleFiche(this.ihm, false);
		this.ihm.getSelectionFiche().maj();
	}

	public void supprimer()
	{
		//Controle si un fichier est charger ou si c'est un nouveau (Normalement c'est toujours le cas)
		if(this.ihm.getFichier() == null)
		{
			JOptionPane.showMessageDialog(null,"Il n'y a pas de fiches", "Supprimer", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			//Conversion de la liste  des fiches en Object pour les manipuler avec un inputDialog
			ArrayList<FicheGenealogique> liste = this.ihm.getFichier().getListeFiches();
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
						this.ihm.getFichier().supprimerFiche(i);
						this.ihm.getSelectionFiche().maj();
						break; //Sort de la boucle car la taille diminue de 1 et la tache a été effectuée
					}
				}
			}
		this.ihm.getSelectionFiche().maj();
		}
	}

	//Méthode pour modifier une fiche déja créée
	public void modifier()
	{
		if(this.ihm.getFichier() == null)
		{
			JOptionPane.showMessageDialog(null,"Il n'y a pas de fiches", "Modifier", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			ArrayList<FicheGenealogique> liste = this.ihm.getFichier().getListeFiches();
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
						FicheGenealogique ficheActuelle = this.ihm.getFichier().contains(liste.get(i));
						new NouvelleFiche(ficheActuelle, this.ihm);
						this.ihm.getSelectionFiche().maj();
					}
				}
			}
		}
		this.ihm.getSelectionFiche().maj();
	}
	// MENU OUTILS
	//méthode pour zoomer sur l'arbre
	public void zoom()
	{
		this.ihm.getPanelAffichage().zoom();
	}
	//Méthode pour Dézoomer sur l'arbre
	public void deZoom()
	{
		this.ihm.getPanelAffichage().deZoom();
	}
	//Méthode pour afficher un ancètre de plus dans l'arbre
	public void monter()
	{
		this.ihm.getPanelAffichage().monter();
	}
	//Méthode pour afficher un ancètre de moins dans l'arbre
	public void descendre()
	{
		this.ihm.getPanelAffichage().descendre();
	}
	// MENU A PROPOS
	//Renseigne sur le logiciel
	public void aPropos()
	{
		String message = "Logiciel d'arbre généalogique créé par ";
		message += "Romain Loisel, Arthur Baradel \net Raphaël Lefevre \n\nIUT Informatique Groupe F2 - Le Havre";
		JOptionPane.showMessageDialog(null, message, "A propos", JOptionPane.INFORMATION_MESSAGE);
	}
}
