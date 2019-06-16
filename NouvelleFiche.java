import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.*;
/*--------------------------------------------------------------------------*/
/*CLASSE QUI CREE UNE NOUVELLE FICHE OU LA MODIFIE ET GERE SES EVENEMENTS   */
/*--------------------------------------------------------------------------*/
public class NouvelleFiche extends JFrame implements ActionListener
{
  //Tableau des noms de label
  private String[] label = {"Nom : ", "Prenom : ", "Date de naissance : ", "Ville de naissance",
  "Département de naissance : ", "Nom du père : ", "Prénom du père : ",
  "Nom de la mère : ", "Prénom de la mère : ", "Date de mariage : ",
  "Ville de mariage : ", "Département de mariage : ", "Date de décès : ",
  "Ville de décès : ", "Département de décès : " };
  //Tableau de JTextField pour les réponses
  private JTextField[]      tabText;
  //Panels
  private JPanel            panelInfos;
  private JPanel            panelBouton;
  //Boutons
  private JButton           btnValider;
  private JButton           btnAnnuler;
  //Le panel de la frame
  private IHM    ihm;
  //Si c'est une modification de fiche
  private boolean           modifFiche;
  //Les différentes fiches
  private FicheGenealogique ficheEnCours;
  private FicheGenealogique nouvelleFiche;

  //CONSTRUCTEURS
  public NouvelleFiche(IHM panelInfosPrincipal, boolean modifFiche)
  {
    this.setTitle("NouvelleFiche");
    this.ihm = panelInfosPrincipal;
    this.modifFiche = modifFiche;
    this.setResizable(false);

    //Création des composants
    this.tabText = new JTextField[this.label.length];
    this.panelBouton = new JPanel();
    this.btnValider = new JButton("Valider");
    this.btnAnnuler = new JButton("Annuler");

    //Vérifie si c'est une modification de fiche et mofie la fenetre e'n conséquence
    if(!this.modifFiche)
    {
      this.setSize(500,300);
      this.panelInfos = new JPanel(new GridLayout(6,2));
      for(int i = 0; i < 5; i++)
      {
        this.creerItem("Label", i);
        this.creerItem("JTextField", i);
      }
    }
    else
    {
      this.setSize(500,400);
      this.panelInfos = new JPanel(new GridLayout(15,2));
      for(int i = 0; i < this.tabText.length; i++)
      {
        this.creerItem("Label", i);
        this.creerItem("JTextField", i);
      }
    }

    //Ajout des composants
    this.btnValider.addActionListener(this);
    this.btnAnnuler.addActionListener(this);
    this.panelBouton.add(this.btnAnnuler);
    this.panelBouton.add(this.btnValider);
    this.add(this.panelInfos, BorderLayout.CENTER);
    this.add(this.panelBouton, BorderLayout.SOUTH);

    //Autre opération necessaire
    this.setVisible(true);
    this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
  }

  public NouvelleFiche(FicheGenealogique fiche, IHM panelInfosPrincipal)
  {
    this(panelInfosPrincipal, true);
    this.ficheEnCours = fiche;
    this.setTitle("Modifier Fiche");

    //Récupération des informations en fonction de la fiche passée en pramètre
    this.tabText[0].setText(fiche.getNom());
    this.tabText[1].setText(fiche.getPrenom());
    this.tabText[2].setText(fiche.getDateDeNaissance());
    this.tabText[3].setText(fiche.getVilleDeNaissance());
    this.tabText[4].setText(fiche.getDeptDeNaissance());

    if(fiche.getPere() != null)
    {
      this.tabText[5].setText(fiche.getPere().getNom());
      this.tabText[6].setText(fiche.getPere().getPrenom());
    }
    if(fiche.getMere() != null)
    {
      this.tabText[7].setText(fiche.getMere().getNom());
      this.tabText[8].setText(fiche.getMere().getPrenom());
    }

    this.tabText[9].setText(fiche.getDateDeMariage());
    this.tabText[10].setText(fiche.getVilleDeMariage());
    this.tabText[11].setText(fiche.getDeptDeMariage());
    this.tabText[12].setText(fiche.getDateDeDeces());
    this.tabText[13].setText(fiche.getVilleDeDeces());
    this.tabText[14].setText(fiche.getDeptDeDeces());
    repaint();
  }

  //Permet de savoir si la date rentrée est conforme
  public boolean conformeDate(String date)
  {
    if(!Pattern.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$",date)) //Regex de motif type d'une date de naissance
    {
      JOptionPane.showMessageDialog(null, "La date " + date + " ne correspond pas au format xx/xx/xxxx", "Erreur !", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }

  //Méthode pour créer ou modifier une fiche
  public boolean creer()
  {
    //Si les dates ne sont pas vide on regarde leur conformité avec une regex
    if(!this.tabText[2].getText().equals(""))
    {
      if(!this.conformeDate(this.tabText[2].getText())) return false;
    }
    if(this.modifFiche && !this.tabText[9].getText().equals(""))
    {
      if(!this.conformeDate(this.tabText[9].getText())) return false;
    }
    if(this.modifFiche && !this.tabText[12].getText().equals(""))
    {
      if(!this.conformeDate(this.tabText[12].getText())) return false;
    }
    if(!this.estRempli()) //Si la fiche n'est pas totalement remplie, affiche un message en conséquence
    {
      JOptionPane.showMessageDialog(null, "La fiche n'est pas rempli.", "Erreur !", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    else
    {
      this.nouvelleFiche = new FicheGenealogique(this.tabText[0].getText(), this.tabText[1].getText(), this.tabText[2].getText(),this.tabText[3].getText(), this.tabText[4].getText());
      if(this.nouvelleFiche != null) //Si la fiche a bien été créée
      {
        if(this.modifFiche) //Si c'est une modification de fiche
        { //On recherche les parents qui y sont renseignés en fonction de leur Prénom et nom
          FicheGenealogique pere = this.ihm.getFichier().rechercher(this.tabText[5].getText(), this.tabText[6].getText());
          FicheGenealogique mere = this.ihm.getFichier().rechercher(this.tabText[7].getText(), this.tabText[8].getText());

          if(pere == null && (!this.tabText[5].getText().equals("") || !this.tabText[6].getText().equals(""))) //Si le pere n'a pas été trouvé mais que la case est remplie
          {
            JOptionPane.showMessageDialog(null, "Le pere n'existe pas !", "Père non trouvé", JOptionPane.ERROR_MESSAGE); //On affiche un message pour dire que le pere n'existe pas dans la base
            return false;
          }
          if(mere == null && (!this.tabText[7].getText().equals("") || !this.tabText[8].getText().equals("")))//Pareil pour la mere
          {
            JOptionPane.showMessageDialog(null, "La mere n'existe pas !", "Mère non trouvée", JOptionPane.ERROR_MESSAGE);
            return false;
          }
          if(this.modifFiche && (pere != this.ficheEnCours && mere != this.ficheEnCours)) //Si c'est une modification de fiche et que que le pere et la mere ne sont pas la fiche en cours
          {
            this.actualisation(this.ficheEnCours, pere, mere); //On fait l'affectation
          }
          else if (pere != this.nouvelleFiche && mere != this.nouvelleFiche)
          {
            this.actualisation(this.nouvelleFiche, pere, mere);
          }
        }
        if(!this.modifFiche)
        this.ihm.ajouterFiche(this.nouvelleFiche); //Si ce n'est pas une modification on ajoute la fiche à la base
        this.ihm.getSelectionFiche().maj(); //Et on fait une mise à jour de l'affichage
        return true;
      }
    }
    return false;
  }
  //Créer les item et les ajoutes au panel
  public void creerItem(String type, int i)
  {
    if(type.equals("JTextField"))
    {
      this.tabText[i] = new JTextField("");
      this.panelInfos.add(this.tabText[i]);
    }
    if(type.equals("Label"))
    {
      this.panelInfos.add(new JLabel(this.label[i]));
    }
  }
  //Actualise les informations dans la fiche selon la fiche donnée en parametre et lie les parents à la fiche en parametre
  public void actualisation(FicheGenealogique fiche, FicheGenealogique pere, FicheGenealogique mere)
  {
    if(fiche == this.ficheEnCours)
    {
      this.ficheEnCours.setPere(pere);
      this.ficheEnCours.setMere(mere);
      this.ficheEnCours.setNom(this.tabText[0].getText());
      this.ficheEnCours.setPrenom(this.tabText[1].getText());
      this.ficheEnCours.setDateDeNaissance(this.tabText[2].getText());
      this.ficheEnCours.setVilleDeNaissance(this.tabText[3].getText());
      this.ficheEnCours.setDeptDeNaissance(this.tabText[4].getText());
      this.ficheEnCours.setDateDeMariage(this.tabText[9].getText());
      this.ficheEnCours.setVilleDeMariage(this.tabText[10].getText());
      this.ficheEnCours.setDeptDeMariage(this.tabText[11].getText());
      this.ficheEnCours.setDateDeDeces(this.tabText[12].getText());
      this.ficheEnCours.setVilleDeDeces(this.tabText[13].getText());
      this.ficheEnCours.setDeptDeDeces(this.tabText[14].getText());
    }
    if(fiche == this.nouvelleFiche)
    {
      this.nouvelleFiche.setPere(pere);
      this.nouvelleFiche.setMere(mere);
    }
  }
  //Methode pour savoir si la nouvelle fiche est totalement remplie
  public boolean estRempli()
  {
    return (   !this.tabText[0].getText().equals("") || !this.tabText[1].getText().equals("")
            || !this.tabText[2].getText().equals("") || !this.tabText[3].getText().equals("")
            || !this.tabText[4].getText().equals("")
           );
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == this.btnValider)
    {
      if(this.creer()) //Si on a réussi à créer la fiche
      {
        if(this.modifFiche && !this.ficheEnCours.equals(this.nouvelleFiche)) //Si c'est une modification de fiche
        {                                                                    //et que la nouvelle fiche != à l'ancienne
          this.ihm.getFichier().supprimerFiche(this.ficheEnCours);//On la supprime
        }
        this.ihm.getSelectionFiche().maj(); //Et on fait une mise à jour de l'affichage
        this.dispose();
      }
    }
    if(e.getSource() == this.btnAnnuler)
    {
      this.dispose();
    }
  }
}
