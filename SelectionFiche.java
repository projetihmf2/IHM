import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

class SelectionFiche extends JPanel implements ActionListener {

    private FichierGenealogique genealogique;
    private PanelPrincipal panelPrincipal ;
    private Font font;
    private int  largeur;

    public SelectionFiche(PanelPrincipal panelPrincipal , int largeur) {
        //this.largeur = (int)(largeur *0.1);
        this.panelPrincipal=panelPrincipal;
        this.setLayout(new FlowLayout());
        this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(150, 10));
        this.font = new Font("Roboto", Font.PLAIN, 14);
        this.setFont(this.font);

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
		this.removeAll();
        for (FicheGenealogique f : genealogique.getListeFiches()) {
			f.setButton(new JButton("<html><b>" + f.getNom() + "<b/><br />" + f.getPrenom() + "<p/><html/>"));
			this.add(f.getBouton());
			f.getBouton().setFont(this.font);
			f.getBouton().addActionListener(this);
			f.getBouton().setPreferredSize(new Dimension(150,40));
        }
        this.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	for (FicheGenealogique fiche : genealogique.getListeFiches()) {
    		if(fiche != null && e.getSource() == fiche.getBouton()){
    			this.panelPrincipal.nouveau(fiche);
    		}
    	}
    }
}
