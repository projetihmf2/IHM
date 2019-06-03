import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

class PanelAffichage extends JPanel {

    private FicheGenealogique fiche;
    private Rectangle rectangleSelection;

    public PanelAffichage() {
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        this.rec.paint(g);
    }

    public void nouveau(FicheGenealogique fiche) {
        this.clear();
        this.fiche = fiche;

        this.rectangleSelection = new Rectangle(0, 0, fiche);

        // this.add();

    }
}