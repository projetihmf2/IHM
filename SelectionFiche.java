import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

class SelectionFiche extends JPanel implements ActionListener {

    private FichierGenealogique genealogique;

    private ArrayList<JButton> tabButtons;

    public SelectionFiche() 
    {
    	this.tabButtons = new ArrayList<JButton> ();
        this.setLayout(new FlowLayout());

        this.add(new JButton("Jean"));

    }

    public void setGenealogique(FichierGenealogique genealogique) {
    	System.out.println("recu");
        this.genealogique = genealogique;
        this.maj();
    }
    
    public FichierGenealogique getGenealogique() {
    	return this.genealogique ;
    }

    public void maj() {

        this.removeAll();
        this.tabButtons.clear();

        for (FicheGenealogique f : genealogique.getListeFiches()) {
            this.tabButtons.add(new JButton(f.getNom()));
        }

        for (JButton but : this.tabButtons) {
            this.add(but);
            but.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
