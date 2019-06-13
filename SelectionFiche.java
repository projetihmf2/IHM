import java.awt.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JButton;

class SelectionFiche extends JPanel implements ActionListener,MouseListener {

  private FichierGenealogique genealogique;
  private PanelPrincipal panelPrincipal ;
  private Font font;
  private int  largeur;
  private JPopupMenu popUpMenu;
  private JMenuItem  itemModifier;
  private JMenuItem  itemSupprimer;
  private FicheGenealogique ficheActive;

  public SelectionFiche(PanelPrincipal panelPrincipal , int largeur)
  {
    this.panelPrincipal=panelPrincipal;
    this.setLayout(new FlowLayout());
    this.setBackground(Color.GRAY);
    this.setPreferredSize(new Dimension(150, 10));
    this.font = new Font("Roboto", Font.PLAIN, 14);
    this.setFont(this.font);

    this.popUpMenu = new JPopupMenu();

    this.itemModifier = new JMenuItem("Modifier");
    this.itemModifier.addActionListener(this);


    this.itemSupprimer = new JMenuItem("Supprimer");
    this.itemSupprimer.addActionListener(this);

    this.popUpMenu.add(this.itemModifier);
    this.popUpMenu.addSeparator();
    this.popUpMenu.add(this.itemSupprimer);
  }

  public void setGenealogique(FichierGenealogique genealogique) {
    System.out.println("reçu");
    this.genealogique = genealogique;
    this.maj();
    System.out.println("MAj terminer");
  }

  public FichierGenealogique getGenealogique() {
    return this.genealogique;
  }

  public void maj()
  {
    this.removeAll();
    for (FicheGenealogique f : genealogique.getListeFiches())
    {
      f.setButton(new JButton("<html><b>" + f.getNom() + "<b/><br />" + f.getPrenom() + "<p/><html/>"));
      this.add(f.getBouton());
      f.getBouton().setFont(this.font);
      f.getBouton().addActionListener(this);
      f.getBouton().setPreferredSize(new Dimension(150,40));
      f.getBouton().addMouseListener(this);
    }
    this.updateUI();
  }

  public void actionPerformed(ActionEvent e)
  {
    for (FicheGenealogique fiche : genealogique.getListeFiches())
    {
      if(fiche != null && e.getSource() == fiche.getBouton())
      {
        this.panelPrincipal.nouveau(fiche);
      }
    }
    if(e.getSource() == this.itemSupprimer)
    {
      this.panelPrincipal.getFichier().supprimerFiche(this.ficheActive);
      this.maj();
    }
    if(e.getSource() == this.itemModifier)
    {
      new NouvelleFiche(this.ficheActive, this.panelPrincipal);
    }
  }

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
