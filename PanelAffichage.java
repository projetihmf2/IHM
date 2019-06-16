import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
/*----------------------------------------------------------*/
/*CLASSE QUI CREE L'ARBRE ET GERE SES EVENEMENTS            */
/*----------------------------------------------------------*/
class PanelAffichage extends JPanel implements ActionListener, MouseListener
{
	//Variable final
	private final int                          ZOOM_MAX = 5;
	private final double                       ZOOM_MIN = 0.5;

	private 			FicheGenealogique            fiche;
	private 			Rectangle                    rectangleSelection;
	private       int                          nbNiveau;
	private       int                          nbNiveauMax;
	private       double                       zoom;
	//Menu contextuel
	private       JPopupMenu                   popUpMenu;
	//Item du menu contextuel
	private       JMenuItem                    itemZoomer;
	private       JMenuItem                    itemDezoomer;
	//Listes des fiches et des positions des fiches dans l'arbre
	private       ArrayList<int[]>             listePos;
	private       ArrayList<FicheGenealogique> listeFiche;

	private       IHM         					       ihm;

	public PanelAffichage(IHM ihm)
	{
		this.ihm = ihm;
		this.nbNiveauMax = 3;
		this.zoom = 1;

		//Création des composant
		this.popUpMenu    = new JPopupMenu();
		this.itemZoomer   = new JMenuItem("Zoomer");
		this.itemDezoomer = new JMenuItem("Dézoomer");

		//Ajout des composants
		this.popUpMenu.add(this.itemZoomer);
		this.popUpMenu.addSeparator();
		this.popUpMenu.add(this.itemDezoomer);

		//Ajout des évènements
		this.itemZoomer.addActionListener(this);
		this.itemDezoomer.addActionListener(this);
		this.addMouseListener(this);

		//Opération autre
		this.setVisible(true);
	}

	public void paint(Graphics g)
	{
		g.clearRect(0,0,this.getWidth(),this.getHeight());
		if (this.fiche != null)
		{
			// effacement de la fenetre

			int width = (int) (this.ihm.getJScrollPane().getWidth() * this.zoom);
			int height = (int) (this.ihm.getJScrollPane().getHeight() * this.zoom);

			g.clearRect(0, 0, this.ihm.getJScrollPane().getWidth() * this.ZOOM_MAX,
			this.ihm.getJScrollPane().getHeight() * this.ZOOM_MAX);

			// Calcul de nombre d'ancetre maximum
			int nbLongueurLigMax = (int) Math.pow(2, nbNiveau - 1);

			this.setPreferredSize(new Dimension(width, height));

			// Reglage de la longeur et de la largeur en fonction du nombre de composant
			Rectangle.setLongeur(width / (nbLongueurLigMax + 1));
			Rectangle.setLargeur(height / (this.nbNiveau * 2));
			Rectangle.setPoliceZoom(this.zoom);
			Rectangle.setDecalage(25 * zoom);

			//Cree un liste qui contient tout les rectangles
			ArrayList<Rectangle> allRectangle = new ArrayList<Rectangle>();

			int longueur = Rectangle.getLongeur();
			int largeur = Rectangle.getLargeur();
			// parcour de l'ensemble des niveaux
			for (int i = 1; i <= this.nbNiveauMax; i++)
			{
				// creation d'un variable k qui assure la bonne possition des rectanges
				int k = 0;
				for (int j = 1; j <= (int) Math.pow(2, i); j = j + 2)
				{
					k++;
					FicheGenealogique ficheCourante = estPresent(i, k);

					allRectangle.add(new Rectangle(((int) (j * (width / Math.pow(2, i))) - longueur / 2),
					((this.nbNiveau + 1 - i) * height / (this.nbNiveau + 1) - (largeur / 2)),
					ficheCourante));

					if (ficheCourante != null && i + 1 <= this.nbNiveauMax)
					{
						if (ficheCourante.getPere() != null)
						{
							g.drawLine((int) (j * (width / Math.pow(2, i))),
							(this.nbNiveau + 1 - i) * height / (this.nbNiveau + 1) - (largeur / 2),
							(int) ((j + 2 * ((j - 1) / 2)) * (width / Math.pow(2, i + 1))),
							(this.nbNiveau + 1 - (i + 1)) * height / (this.nbNiveau + 1) + (largeur / 2));
						}
						if (ficheCourante.getMere() != null)
						{
							g.drawLine((int) (j * (width / Math.pow(2, i))),
							(this.nbNiveau + 1 - i) * height / (this.nbNiveau + 1) - (largeur / 2),
							(int) ((2 * j + 1) * (width / Math.pow(2, i + 1))),
							(this.nbNiveau + 1 - (i + 1)) * height / (this.nbNiveau + 1) + (largeur / 2));
						}
					}
				}
			}
			for (Rectangle r : allRectangle) { r.peindre(g); }

			this.ihm.getJScrollPane().revalidate();
			this.ihm.revalidate();
		}
	}

	//Creation de l'arbre lors de l'envoi d'un fiche
	public void nouveau(FicheGenealogique fiche)
	{
		this.fiche = fiche;
		this.nbNiveau = compter(fiche);
		// creation des listes
		this.listeFiche = new ArrayList<FicheGenealogique>();
		this.listePos = new ArrayList<int[]>();
		creeListPos(fiche, 1, 1);
		this.repaint();
	}

	//Regarde si il y a une fiche a cette endroit
	private FicheGenealogique estPresent(int hauteur, int position)
	{
		for (int[] i : this.listePos)
		{
			// si il existe cette position dans la liste
			if (i[0] == hauteur && i[1] == position)
			// retourne la fiche avec cette position
			return this.listeFiche.get(this.listePos.indexOf(i));
		}
		return null;
	}

	//Compte le nombre de niveau max de l'arbre
	private int compter(FicheGenealogique f)
	{
		int nombre1 = 1;
		int nombre2 = 1;
		if (f.getPere() != null) { nombre1 += compter(f.getPere()); }
		if (f.getMere() != null) { nombre2 += compter(f.getMere()); }
		return (nombre1 > nombre2 ? nombre1 : nombre2);
	}

	//Créer la liste des positions où seront situé les fiches0
	private void creeListPos(FicheGenealogique f, int hauteur, int position)
	{
		if (f == null) { return; }

		int[] a = { hauteur, position };
		// ajout du tableau de position
		this.listePos.add(a);
		// ajout de la liste de fiche
		this.listeFiche.add(f);

		if (f.getPere() != null)
		{
			creeListPos(f.getPere(), hauteur + 1, position * 2 - 1);
		}
		if (f.getMere() != null)
		{
			creeListPos(f.getMere(), hauteur + 1, position * 2);
		}
	}

	//Méthode pour dézoomer dans l'arbre
	public void deZoom()
	{
		if (this.zoom > 1)                               { this.zoom--;                 }
		if (this.zoom <= 1 && this.zoom > this.ZOOM_MIN) { this.zoom = this.zoom - 0.1; }
		this.repaint();
	}

	//Méthode pour zoomer dans l'arbre
	public void zoom()
	{
		if (this.zoom < ZOOM_MAX && this.zoom >= 1) { this.zoom++;                 }
		if (this.zoom < 1)                          { this.zoom = this.zoom + 0.1; }
		this.repaint();
	}

	//Méthode pour afficher un ancètre de plus dans l'arbre
	public void monter()
	{
		if (this.nbNiveau > nbNiveauMax) { this.nbNiveauMax++; }
		this.repaint();
	}

	//Méthode pour afficher un anc�tre de moins dans l'arbre
	public void descendre()
	{
		if (this.nbNiveauMax != 1) { this.nbNiveauMax--; }
		this.repaint();
	}

	//Gestion de l'évenement pour le menu contextuel
	public void mousePressed(MouseEvent event)
	{
		if (event.isPopupTrigger())
		{
			this.popUpMenu.show(event.getComponent(), event.getX(), event.getY());
		}
	}
	public void mouseExited(MouseEvent e)  {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e) {}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.itemZoomer)   { this.zoom();   }
		if (e.getSource() == this.itemDezoomer) { this.deZoom(); }
	}
}
