import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

public class Rectangle {
    private int posX;
    private int posY;
    private static int largeur;
    private static int longeur;
    private static int decalage;
    private FicheGenealogique fiche;

    public Rectangle(int posX, int posY, FicheGenealogique fiche) {
        this.posX = posX;
        this.posY = posY;
        this.fiche = fiche;
    }

    public void peindre(Graphics g) {
        if (this.fiche != null) {

            g.setColor(Color.BLACK);
            g.drawRect(this.posX, this.posY, Rectangle.longeur, Rectangle.largeur);
            g.drawString(fiche.getNom() + "\\" + fiche.getPrenom(), posX + Rectangle.decalage,
                    posY + Rectangle.decalage);
        }

    }

    public static void setLargeur(int largeur) {
        Rectangle.largeur = largeur;
    }

    public static void setLongeur(int longeur) {
        Rectangle.longeur = longeur;
    }

    public static void setDecalage(int decalage) {
        Rectangle.decalage = decalage;
    }

    public static int getLargeur() {
        return Rectangle.largeur;
    }

    public static int getLongeur() {
        return Rectangle.longeur;
    }

    public static int getDecalage() {
        return Rectangle.decalage;
    }
}
