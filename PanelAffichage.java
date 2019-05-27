import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

class PanelAffichage extends JPanel {
    private ArrayList<Rectangle> tabRect;

    public PanelAffichage() {
        this.tabRect = new ArrayList<Rectangle>();
        // this.setLayout(new GridLayout(4, 3, 10, 10));
        for (int i = 0; i < 5; i++) {
            this.tabRect.add(new Rectangle(10 * i, 20 * i, 150, 100));
        }

        this.setVisible(true);
    }

    public void paint(Graphics g) {
        for (Rectangle rec : this.tabRect) {
            rec.paint(g);
        }
    }
}