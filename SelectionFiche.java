import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

class SelectionFiche extends JPanel implements ActionListener {

    private FichierGenealogique genealogique;
    private ArrayList<JButton> tabButtons;
    private Font font;
    private int  largeur;

    public SelectionFiche(int largeur)
    {
        //this.largeur = (int)(largeur *0.1);
        this.setLayout(new FlowLayout());
        this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(150, 10));
        this.font = new Font("Roboto", Font.PLAIN, 14);
        this.setFont(this.font);

        this.tabButtons = new ArrayList<JButton>();

    }

    public void setGenealogique(FichierGenealogique genealogique) {
        System.out.println("reçu");
        this.genealogique = genealogique;
        this.maj();
        System.out.println("MAj terminer");
    }

    public FichierGenealogique getGenealogique() {
        return this.genealogique;
    }

    public void maj() {

        for (JButton but : this.tabButtons) {
            this.remove(but);
        }
        this.tabButtons.clear();

        for (FicheGenealogique f : genealogique.getListeFiches()) {

            this.tabButtons.add(new JButton("<html><b>" + f.getNom() + "<b/><br />" + f.getPrenom() + "<p/><html/>"));
        }

        for (JButton but : this.tabButtons) {
            this.add(but);
            but.setFont(this.font);
            but.addActionListener(this);
            but.setPreferredSize(new Dimension(150,40));
        }
        this.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
