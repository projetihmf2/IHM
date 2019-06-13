import java.awt.*;

import java.awt.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.awt.event.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.omg.CORBA.SystemException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.omg.CORBA.SystemException;
import javax.swing.*;

public class NouvelleFiche extends JFrame implements ActionListener
{
  private String[] label = {"Nom : ", "Prenom : ", "Date de naissance : ", "Ville de naissance",
  "Département de naissance : ", "Nom du père : ", "Prénom du père : ",
  "Nom de la mère : ", "Prénom de la mère : ", "Date de mariage : ",
  "Ville de mariage : ", "Département de mariage : ", "Date de décès : ",
  "Ville de décès : ", "Département de décès : " };

private JTextField[]      tabText;
private JPanel            panelInfos;
private JPanel            panelBouton;
private JButton           valider;
private JButton           annuler;
private PanelPrincipal    panelPrincipal;
private boolean           modifFiche;
private int               numFiche;
private FicheGenealogique ficheEnCours;
private FicheGenealogique nouvelleFiche;

public NouvelleFiche(PanelPrincipal panelInfosPrincipal)
{
  this.setTitle("NouvelleFiche");
  this.panelPrincipal = panelInfosPrincipal;
  this.setResizable(false);
  this.setSize(500,400);

  this.tabText = new JTextField[this.label.length];
  this.panelInfos = new JPanel(new GridLayout(15,2));
  this.panelBouton = new JPanel();
  this.valider = new JButton("Valider");
  this.valider.addActionListener(this);
  this.annuler = new JButton("Annuler");
  this.annuler.addActionListener(this);
  this.panelBouton.add(this.annuler);
  this.panelBouton.add(this.valider);

  for(int i = 0; i < this.label.length; i++)
  {
    this.creerItem("Label", i);
    this.creerItem("JTextField", i);
  }

  this.add(this.panelInfos, BorderLayout.CENTER);
  this.add(this.panelBouton, BorderLayout.SOUTH);
  this.setVisible(true);
  this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
}
//SUpprimer peut etre le num fiche
public NouvelleFiche(FicheGenealogique fiche, PanelPrincipal panelInfosPrincipal)
{
  this(panelInfosPrincipal);
  this.modifFiche = true;
  this.ficheEnCours = fiche;
  this.setTitle("Modifier Fiche");
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

public String conformeNaissance()
{
  String date = "";
  String[] ensDate;
  if( this.tabText[2].getText().charAt(2) == '-' || this.tabText[2].getText().charAt(5) == '-' || this.tabText[9].getText().charAt(2) == '-'
  ||this.tabText[9].getText().charAt(5) == '-' || this.tabText[14].getText().charAt(2) == '-' || this.tabText[14].getText().charAt(5) == '-')
  {
    ensDate = this.tabText[2].getText().split("-");
    date = ensDate[0] + "/" + ensDate[1] + "/" + ensDate[2];
  }
  else
  {
    date = this.tabText[2].getText();
  }
  return date;
}

public boolean creer()
{
  if(this.tabText[0].getText().equals("") || this.tabText[1].getText().equals(""))
  {
    JOptionPane.showMessageDialog(null, "Le nom et/ou le prénom n'est pas donné.", "Erreur !", JOptionPane.ERROR_MESSAGE);
  }
  else
  {
    this.nouvelleFiche = new FicheGenealogique(this.tabText[0].getText(), this.tabText[1].getText(), this.tabText[2].getText(),this.tabText[3].getText(), this.tabText[4].getText());
    if(this.nouvelleFiche != null)
    {

      FicheGenealogique pere = this.panelPrincipal.getFichier().rechercher(this.tabText[5].getText(), this.tabText[6].getText());
      FicheGenealogique mere = this.panelPrincipal.getFichier().rechercher(this.tabText[7].getText(), this.tabText[8].getText());

      if(pere == null && (!this.tabText[5].getText().equals("") || !this.tabText[6].getText().equals("")))
      {
        JOptionPane.showMessageDialog(null, "Le pere n'existe pas !", "Père non trouvé", JOptionPane.ERROR_MESSAGE);
        return false;
      }
      if(mere == null && (!this.tabText[7].getText().equals("") || !this.tabText[8].getText().equals("")))
      {
        JOptionPane.showMessageDialog(null, "La mere n'existe pas !", "Mère non trouvée", JOptionPane.ERROR_MESSAGE);
        return false;
      }
      if(this.modifFiche && (pere != this.ficheEnCours && mere != this.ficheEnCours))
      {
        this.actualisation(this.ficheEnCours, pere, mere);
      }
      else if (pere != this.nouvelleFiche && mere != this.nouvelleFiche)
      {
        this.actualisation(this.nouvelleFiche, pere, mere);
      }
      if(!this.modifFiche)
        this.panelPrincipal.ajouterFiche(this.nouvelleFiche);
      return true;
    }
  }
  return false;
}

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

public void actionPerformed(ActionEvent e)
{
  if(e.getSource() == this.valider)
  {
    if(this.creer())
    {
      if(this.modifFiche && !this.ficheEnCours.equals(this.nouvelleFiche))
      {
        this.panelPrincipal.getFichier().supprimerFiche(this.ficheEnCours);
      }
      if(this.modifFiche && (this.ficheEnCours.getPere() != this.nouvelleFiche.getPere() || this.ficheEnCours.getMere() != this.nouvelleFiche.getMere()))
      {
        System.out.println("Je suis passé");
      }
      this.panelPrincipal.getSelectionFiche().maj();
      this.dispose();
    }
  }
  if(e.getSource() == this.annuler)
  {
    this.dispose();
  }
}
}
