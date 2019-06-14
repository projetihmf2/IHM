import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


class PanelAffichage extends JPanel implements ActionListener,MouseListener
{

	private FicheGenealogique fiche;
	private Rectangle rectangleSelection;
	private int nbNiveau;

	private JPopupMenu popUpMenu;
	private JMenuItem  itemZoomer;
	private JMenuItem  itemDezoomer;

	private ArrayList<int[]> listePos;
	private ArrayList<FicheGenealogique> listeFiche;

	private int nbNiveauMax;
	private int zoom;

	private PanelPrincipal panelPrincipal;

	public PanelAffichage(PanelPrincipal panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
		this.nbNiveauMax = 3;
		this.zoom = 1;

		this.popUpMenu = new JPopupMenu();

    this.itemZoomer = new JMenuItem("Zoomer");
    this.itemZoomer.addActionListener(this);


    this.itemDezoomer = new JMenuItem("Dézoomer");
    this.itemDezoomer.addActionListener(this);

    this.popUpMenu.add(this.itemZoomer);
    this.popUpMenu.addSeparator();
    this.popUpMenu.add(this.itemDezoomer);

		this.addMouseListener(this);

		this.setVisible(true);
	}

	public void paint(Graphics g) {
		if (this.fiche != null) {
			// effacement de la fenetre

			int width = (int) (this.panelPrincipal.getJScrollPane().getWidth() * this.zoom);
			int height = (int) (this.panelPrincipal.getJScrollPane().getHeight() * this.zoom);

			g.clearRect(0, 0, width, height);

			// Calcul de nombre d'ancetre maximum
			int nbLongueurLigMax = (int) Math.pow(2, nbNiveau - 1);

			this.setPreferredSize(new Dimension(width, height));

			// Reglage de la longeur et de la largeur en fonction du nombre de composant
			Rectangle.setLongeur(width / (nbLongueurLigMax + 1));
			Rectangle.setLargeur(height / (this.nbNiveau * 2));
			Rectangle.setPoliceZoom(this.zoom);
			Rectangle.setDecalage(25 * zoom);

			// Cree un liste qui contient tout les rectangles
			ArrayList<Rectangle> allRectangle = new ArrayList<Rectangle>();

			int longueur = Rectangle.getLongeur();
			int largeur = Rectangle.getLargeur();
			// parcour de l'ensemble des niveaux
			for (int i = 1; i <= this.nbNiveauMax; i++) {
				// creation d'un variable k qui assure la bonne possition des rectanges
				int k = 0;
				for (int j = 1; j <= (int) Math.pow(2, i); j = j + 2) {
					k++;
					FicheGenealogique ficheCourante = estPresent(i, k);

					allRectangle.add(new Rectangle(((int) (j * (width / Math.pow(2, i))) - longueur / 2),
							((int) (this.nbNiveau + 1 - i) * height / (this.nbNiveau + 1) - (largeur / 2)),
							ficheCourante));

					if (ficheCourante != null && i + 1 <= this.nbNiveauMax) {

						if (ficheCourante.getPere() != null)
							g.drawLine((int) (j * (width / Math.pow(2, i))),
									(int) (this.nbNiveau + 1 - i) * height / (this.nbNiveau + 1) - (largeur / 2),
									(int) ((j + 2 * ((j - 1) / 2)) * (width / Math.pow(2, i + 1))),
									(int) (this.nbNiveau + 1 - (i + 1)) * height / (this.nbNiveau + 1) + (largeur / 2));
						if (ficheCourante.getMere() != null)
							g.drawLine((int) (j * (width / Math.pow(2, i))),
									(int) (this.nbNiveau + 1 - i) * height / (this.nbNiveau + 1) - (largeur / 2),
									(int) ((2 * j + 1) * (width / Math.pow(2, i + 1))),
									(int) (this.nbNiveau + 1 - (i + 1)) * height / (this.nbNiveau + 1) + (largeur / 2));
					}
				}
			}
			for (Rectangle r : allRectangle) {
				r.peindre(g);
			}

			this.panelPrincipal.getJScrollPane().revalidate();
			this.panelPrincipal.revalidate();

		}
	}

	// Creation de l'arbre lors de l'envoi d'un fiche
	public void nouveau(FicheGenealogique fiche) {
		this.fiche = fiche;
		this.nbNiveau = compter(fiche);
		// creation des listes
		this.listeFiche = new ArrayList<FicheGenealogique>();
		this.listePos = new ArrayList<int[]>();
		creeListPos(fiche, 1, 1);
		this.repaint();

	}

	// regarde si il y a une fiche a cette endroit
	private FicheGenealogique estPresent(int hauteur, int position) {

		for (int[] i : this.listePos) {
			// si il existe cette position dans la liste
			if (i[0] == hauteur && i[1] == position)
				// retourne la fiche avec cette position
				return this.listeFiche.get(this.listePos.indexOf(i));
		}

		return null;
	}

	// compte le nombre de niveau max de l'arbre
	private int compter(FicheGenealogique f) {
		int nombre1 = 1;
		int nombre2 = 1;
		if (f.getPere() != null) {
			nombre1 += compter(f.getPere());
		}
		if (f.getMere() != null) {
			nombre2 += compter(f.getMere());
		}
		return (nombre1 > nombre2 ? nombre1 : nombre2);
	}

	private void creeListPos(FicheGenealogique f, int hauteur, int position) {
		if (f == null)
			return;

		int[] a = { hauteur, position };
		// ajout du tableau de position
		this.listePos.add(a);
		// ajout de la liste de fiche
		this.listeFiche.add(f);

		if (f.getPere() != null) {
			creeListPos(f.getPere(), hauteur + 1, position * 2 - 1);

		}
		if (f.getMere() != null) {
			creeListPos(f.getMere(), hauteur + 1, position * 2);
		}
	}

	public void deZoom() {
		if (this.zoom > 1)
			this.zoom--;
		this.repaint();
	}

	public void zoom() {
		if (this.zoom < 5)
			this.zoom++;
		this.repaint();
	}

	public void monter() {
		if (this.nbNiveau > nbNiveauMax)
			this.nbNiveauMax++;
		this.repaint();
	}

	public void descendre() {
		if (this.nbNiveauMax != 1)
			this.nbNiveauMax--;
		this.repaint();
	}

	public void mousePressed( MouseEvent event )
  {
    if ( event.isPopupTrigger() )
    {
      this.popUpMenu.show( event.getComponent(), event.getX(), event.getY() );
    }
  }

  public void mouseExited  ( MouseEvent e) {}
  public void mouseEntered ( MouseEvent e) {}
  public void mouseReleased( MouseEvent e) {}
  public void mouseClicked ( MouseEvent e) {}

		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == this.itemZoomer)   { this.zoom();   }
			if(e.getSource() == this.itemDezoomer) { this.deZoom(); }
		}
}
