import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

class Rectangle extends JPanel {
    private int posX;
    private int posY;
    private int largeur;
    private int longeur;

    public Rectangle(int posX, int posY, int largeur, int longeur) {
        this.posX = posX;
        this.posY = posY;
        this.largeur = largeur;
        this.longeur = longeur;
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(this.posX, this.posY, this.largeur, this.longeur);

        System.out.println("TEST");
    }
}