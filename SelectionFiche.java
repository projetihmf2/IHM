import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

class SelectionFiche extends JPanel implements ActionListener {

    private FichierGenealogique genealogique;

    private ArrayList<JButton> tabButtons;
    private Font font;

    public SelectionFiche() {

        this.setLayout(new FlowLayout());
        this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(125, 10));
        this.font = new Font("Courier", Font.PLAIN, 14);
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

            this.tabButtons.add(new JButton(String.format("%-10s", f.getNom())));
        }

        for (JButton but : this.tabButtons) {
            this.add(but);
            but.setFont(this.font);
            but.addActionListener(this);
        }
        this.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
