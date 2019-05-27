import java.awt.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.*;
import org.omg.CORBA.SystemException;
import java.awt.event.*;

public class NouvelleFiche extends JDialog implements ActionListener
{
  private String[] label = {"Nom : ", "Prenom : ", "Date de naissance : ", "Ville de naissance",
                            "Département de naissance : ", "Date de mariage : ", "Ville de mariage : ",
                            "Département de mariage : ", "Date de décès : ", "Ville de décès : ", "Département de décès : "};
  private JTextField[] tabText;
  private JPanel panelInfos;
  private JPanel panelBouton;
  private JButton valider;
  private JButton annuler;
  private PanelPrincipal panelPrincipal;

  public NouvelleFiche(PanelPrincipal panelInfosPrincipal)
  {
    super(panelInfosPrincipal, "Nouvelle Fiche", true);
    this.setResizable(false);
    this.setSize(500,400);

    this.tabText = new JTextField[this.label.length];
    this.panelInfos = new JPanel(new GridLayout(11,2));
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
  public String conformeNaissance()
  {
    String date = "";
    String[] ensDate;
    if( this.tabText[2].getText().charAt(2) == '-' || this.tabText[2].getText().charAt(5) == '-' || this.tabText[5].getText().charAt(2) == '-'
      ||this.tabText[5].getText().charAt(5) == '-' || this.tabText[8].getText().charAt(2) == '-' || this.tabText[8].getText().charAt(5) == '-')
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

  public void creer()
  {
    if(this.tabText[0].getText().equals("") || this.tabText[1].getText().equals(""))
    {
      JOptionPane.showMessageDialog(null, "Le nom et/ou le prénom n'est pas donné.", "Erreur !", JOptionPane.ERROR_MESSAGE);
    }
    else
    {
      FicheGenealogique nouvelleFiche = new FicheGenealogique(this.tabText[0].getText(), this.tabText[1].getText(), this.tabText[2].getText(),this.tabText[3].getText(), this.tabText[4].getText());
      this.panelPrincipal.ajouterFiche(nouvelleFiche);
    }
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

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == this.valider)
    {
      this.creer();
    }
    if(e.getSource() == this.annuler)
    {

    }
  }
}
