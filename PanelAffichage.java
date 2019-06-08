import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

class PanelAffichage extends JPanel {

    private FicheGenealogique fiche;
    private Rectangle rectangleSelection;
    private int nbNiveau;

    public PanelAffichage() {
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        if (this.fiche != null) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            int nvHauteur = this.nbNiveau;
            int nbLongueurLigMax = (int) Math.pow(2, nbNiveau - 1);
            int longueur = getWidth() / (nbLongueurLigMax + 1);
            int largeur = getHeight() / (nvHauteur * 2);

            for (int i = 1; i <= nvHauteur; i++) {
                for (int j = 1; j <= (int) Math.pow(2, i); j = j + 2) {
                    g.drawRect((int) (j * (getWidth() / Math.pow(2, i))) - longueur / 2,
                            (int) (nvHauteur + 1 - i) * getHeight() / (nvHauteur + 1) - (largeur / 2), longueur,
                            largeur); // (int
                                      // x,
                                      // int
                                      // y,
                                      // int
                                      // width,
                                      // int
                                      // height)
                    if (i != nvHauteur) {
                        g.drawLine((int) (j * (getWidth() / Math.pow(2, i))),
                                (int) (nvHauteur + 1 - i) * getHeight() / (nvHauteur + 1) - (largeur / 2),
                                (int) ((j + 2 * ((j - 1) / 2)) * (getWidth() / Math.pow(2, i + 1))),
                                (int) (nvHauteur + 1 - (i + 1)) * getHeight() / (nvHauteur + 1) + (largeur / 2));
                        g.drawLine((int) (j * (getWidth() / Math.pow(2, i))),
                                (int) (nvHauteur + 1 - i) * getHeight() / (nvHauteur + 1) - (largeur / 2),
                                (int) ((2 * j + 1) * (getWidth() / Math.pow(2, i + 1))),
                                (int) (nvHauteur + 1 - (i + 1)) * getHeight() / (nvHauteur + 1) + (largeur / 2));
                    }
                }
            }
        }
    }

    public void nouveau(FicheGenealogique fiche) {
        this.fiche = fiche;
        this.nbNiveau = compter(fiche);
        this.repaint();

    }

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
}
