import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

class PanelAffichage extends JPanel {

	private FicheGenealogique fiche;
	private Rectangle rectangleSelection;
	private int nbNiveau;

	private ArrayList<int[]> listePos;
	private ArrayList<FicheGenealogique> listeFiche;

	// Test zoom et Niveau

	private int nbNiveauMax;
	private int zoom;

	public PanelAffichage() {
		this.nbNiveauMax = 3;
		this.zoom = 1;
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		if (this.fiche != null) {
			// effacement de la fenetre
			g.clearRect(0, 0, this.getWidth(), this.getHeight());

			// Calcul de nombre d'ancetre maximum
			int nbLongueurLigMax = (int) Math.pow(2, nbNiveau - 1);

			// Reglage de la longeur et de la largeur en fonction du nombre de composant
			Rectangle.setLongeur(this.getWidth() / (nbLongueurLigMax + 1));
			Rectangle.setLargeur(this.getHeight() / (this.nbNiveau * 2));
			Rectangle.setDecalage(25);

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

					allRectangle.add(new Rectangle(((int) (j * (getWidth() / Math.pow(2, i))) - longueur / 2),
							((int) (this.nbNiveau + 1 - i) * getHeight() / (this.nbNiveau + 1) - (largeur / 2)),
							ficheCourante));

					if (ficheCourante != null && i + 1 <= this.nbNiveauMax) {

						if (ficheCourante.getPere() != null)
							g.drawLine((int) (j * (getWidth() / Math.pow(2, i))),
									(int) (this.nbNiveau + 1 - i) * getHeight() / (this.nbNiveau + 1) - (largeur / 2),
									(int) ((j + 2 * ((j - 1) / 2)) * (getWidth() / Math.pow(2, i + 1))),
									(int) (this.nbNiveau + 1 - (i + 1)) * getHeight() / (this.nbNiveau + 1)
											+ (largeur / 2));
						if (ficheCourante.getMere() != null)
							g.drawLine((int) (j * (getWidth() / Math.pow(2, i))),
									(int) (this.nbNiveau + 1 - i) * getHeight() / (this.nbNiveau + 1) - (largeur / 2),
									(int) ((2 * j + 1) * (getWidth() / Math.pow(2, i + 1))),
									(int) (this.nbNiveau + 1 - (i + 1)) * getHeight() / (this.nbNiveau + 1)
											+ (largeur / 2));
					}
				}
			}
			for (Rectangle r : allRectangle) {
				r.peindre(g);
			}
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

	public void augmenter() {
		if (this.nbNiveau > nbNiveauMax)
			this.nbNiveauMax++;
		this.repaint();
	}

	public void diminuer() {
		if (this.nbNiveauMax != 1)
			this.nbNiveauMax--;
		this.repaint();
	}

	public void clear() {
		this.fiche = null;
		this.repaint();
	}

}
