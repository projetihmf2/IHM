import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

public class Rectangle
{
    private int posX;
    private int posY;
    private static int largeur = 125;
    private static int longeur = 80;
    private static final int decalage = 25;
    private FicheGenealogique fiche;

    public Rectangle(int posX, int posY, FicheGenealogique fiche)
    {
        this.posX = posX;
        this.posY = posY;
        this.fiche = fiche;
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(this.posX, this.posY, Rectangle.longeur, Rectangle.largeur);
        g.drawString("Arthur  Baradel", posX + Rectangle.decalage, posY + Rectangle.decalage);

        System.out.println("TEST");
    }
}
