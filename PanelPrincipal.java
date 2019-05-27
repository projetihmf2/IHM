import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PanelPrincipal extends JFrame
{
  private BarreDeMenu menu;
  private BarreDOutil outils;
  private SelectionFiche fiche ;

  public PanelPrincipal()
  {
    this.setTitle("Arbre généalogique");
    this.setSize(1000,800);
    this.setLocation(50,100);

    this.menu = new BarreDeMenu(this);
    this.setJMenuBar(this.menu);

    this.outils = new BarreDOutil();
    this.add(this.outils,BorderLayout.NORTH);
    
    this.fiche = new SelectionFiche();
    this.add(this.fiche,"West");

    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //init le un nouveau doccument au lancement
    this.menu.nouveau();
  }
  

	public void ajouterFiche(FicheGenealogique fiche)
	{
		if(this.fiche.getGenealogique() != null )this.fiche.getGenealogique().addFicheGenealogique(fiche);
	}
	public void setFichier(FichierGenealogique fichier)
	{
		this.fiche.setGenealogique(fichier);
	}

	public FichierGenealogique getFichier()
	{
		return this.fiche.getGenealogique();
	}
	public SelectionFiche getSelectionFiche()
	{
		return this.fiche;
	}
}
