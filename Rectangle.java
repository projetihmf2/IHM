import java.awt.*;

import javax.swing.*;

public class Rectangle {
    private int posX;
    private int posY;
    private static int largeur;
    private static int longeur;
    private static int decalage;
    private static Font police = new Font("Roboto", Font.PLAIN, 14);
    private FicheGenealogique fiche;

    public Rectangle(int posX, int posY, FicheGenealogique fiche) {
        this.posX = posX;
        this.posY = posY;
        this.fiche = fiche;

    }

    public void peindre(Graphics g) {
        if (this.fiche != null) {

            g.setColor(Color.BLACK);
            g.setFont(Rectangle.police);
            g.drawRect(this.posX, this.posY, Rectangle.longeur, Rectangle.largeur);
            g.drawString(fiche.getNom(), this.posX + Rectangle.decalage, posY + Rectangle.decalage);
            g.drawString(fiche.getPrenom(), this.posX + Rectangle.decalage, this.posY + Rectangle.decalage * 2);
            g.drawString(fiche.getDateDeNaissance(), this.posX + Rectangle.decalage,
                    this.posY + Rectangle.decalage * 3);

        }

    }

    public static void setLargeur(int largeur) {
        Rectangle.largeur = largeur;
    }

    public static void setLongeur(int longeur) {
        Rectangle.longeur = longeur;
    }

    public static void setDecalage(double decalage) {
        Rectangle.decalage = (int) decalage;
    }

    public static void setPoliceZoom(double zoom) {
        Rectangle.police = new Font("Roboto", Font.PLAIN, (int)( 14 * zoom));
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
