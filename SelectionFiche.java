import java.awt.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JButton;

class SelectionFiche extends JPanel implements ActionListener,MouseListener {

  private FichierGenealogique genealogique;
  private IHM ihm ;
  private Font font;
  private int  largeur;
  private JPopupMenu popUpMenu;
  private JMenuItem  itemModifier;
  private JMenuItem  itemSupprimer;
  private FicheGenealogique ficheActive;

  public SelectionFiche(IHM ihm , int largeur)
  {
    this.ihm = ihm;
    this.setLayout(new FlowLayout());
    this.setBackground(Color.GRAY);
    this.setPreferredSize(new Dimension(150, 10));

    //Création des composants
    this.font          = new Font("Roboto", Font.PLAIN, 14);
    this.popUpMenu     = new JPopupMenu();
    this.itemModifier  = new JMenuItem("Modifier");
    this.itemSupprimer = new JMenuItem("Supprimer");

    //Ajout des composants
    this.itemModifier.addActionListener(this);
    this.itemSupprimer.addActionListener(this);
    this.popUpMenu.add(this.itemModifier);
    this.popUpMenu.addSeparator();
    this.popUpMenu.add(this.itemSupprimer);

    //Autre traitement
    this.setFont(this.font);
  }

  //Mise à jour de la liste des fiches et de ses boutons
  public void maj()
  {
    this.removeAll();
    Collections.sort(this.genealogique.getListeFiches());
    for (FicheGenealogique fiche : this.genealogique.getListeFiches())
    {
      fiche.setButton(new JButton("<html><b>" + fiche.getNom() + "<b/><br />" + fiche.getPrenom() + "<p/><html/>"));
      this.add(fiche.getBouton());
      fiche.getBouton().setFont(this.font);
      fiche.getBouton().addActionListener(this);
      fiche.getBouton().setPreferredSize(new Dimension(150,40));
      fiche.getBouton().addMouseListener(this);
    }
    this.updateUI();
  }

  //Donne le fichier en paramètre
  public void setGenealogique(FichierGenealogique genealogique)
  {
    this.genealogique = genealogique;
    this.maj();
  }

  public FichierGenealogique getGenealogique() { return this.genealogique; }

  //EVENEMENTS
  public void actionPerformed(ActionEvent e)
  {
    for (FicheGenealogique fiche : genealogique.getListeFiches())
    {
      if(fiche != null && e.getSource() == fiche.getBouton())
      {
        this.ihm.nouveau(fiche);
      }
    }
    if(e.getSource() == this.itemSupprimer) //Si on choisi de supprimer la fiche via le menu
    {
      this.ihm.getFichier().supprimerFiche(this.ficheActive);
      this.maj();
    }
    if(e.getSource() == this.itemModifier) //Si on choisi de modifier la fiche via le menu
    {
      new NouvelleFiche(this.ficheActive, this.ihm);
    }
  }

  //Évènement d'appartion du menu contextuel
  public void mousePressed( MouseEvent event )
  {
    if ( event.isPopupTrigger() )
    {
      for (FicheGenealogique fiche : genealogique.getListeFiches())
        if(event.getSource() == fiche.getBouton())
          this.ficheActive = fiche;
      this.popUpMenu.show( event.getComponent(), event.getX(), event.getY() );
    }
  }

  public void mouseExited  ( MouseEvent e) {}
  public void mouseEntered ( MouseEvent e) {}
  public void mouseReleased( MouseEvent e) {}
  public void mouseClicked ( MouseEvent e) {}

}
