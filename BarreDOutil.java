import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/*----------------------------------------------------------*/
/*CLASSE QUI CREE LA BARRE D'OUTIL ET GERE SES EVENEMENTS   */
/*----------------------------------------------------------*/
public class BarreDOutil extends JPanel implements MouseListener
{
	//Le menu
	private BarreDeMenu menu;
	//Les différents labels contenant les images
	private JLabel zoom;
	private JLabel deZoom;
	private JLabel monter;
	private JLabel descendre;
	private JLabel lblSauvegarder;
	private JLabel lblCharger;
	private JLabel lblNouveau;

	public BarreDOutil(BarreDeMenu menu) {
		this.menu = menu;
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

		// cree les image
		Image imgZoom = this.creeImage("add-searching.png");
		Image imgDeZoom = this.creeImage("delete-searching.png");
		Image imgDescendre = this.creeImage("arrow-down.png");
		Image imgMonter = this.creeImage("arrow-up.png");
		Image imgSauvegarder = this.creeImage("save.png");
		Image imgCharger = this.creeImage("load.png");
		Image imgNouveau = this.creeImage("ajouter.png");

		// cree les label et les ajoute
		this.zoom = this.creeLabel(imgZoom);
		this.deZoom = this.creeLabel(imgDeZoom);
		this.descendre = this.creeLabel(imgDescendre);
		this.monter = this.creeLabel(imgMonter);
		this.lblSauvegarder = this.creeLabel(imgSauvegarder);
		this.lblCharger = this.creeLabel(imgCharger);
		this.lblNouveau = this.creeLabel(imgNouveau);

		this.setVisible(true);
	}

	//Remet le curseur normal quand la souris n'est plus sur le label
	public void mouseExited(MouseEvent e)
	{
		this.setCursor(Cursor.getDefaultCursor());
	}
	//Change le curseur sur le passage du label
	public void mouseEntered(MouseEvent e)
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e)  {}
	//Action au clique sur les labels correspondant
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.lblNouveau) {
			this.menu.nouveau(); //Créer un nouveau fichier
		}
		if (e.getSource() == this.lblCharger) {
			this.menu.charger(); //Charger un fichier
		}
		if (e.getSource() == this.lblSauvegarder) {
			this.menu.enrengister(); //Enregister le fichier courant
		}
		if (e.getSource() == this.zoom) {
			this.menu.zoom(); //Zoomer sur l'arbre
		}
		if (e.getSource() == this.deZoom) {
			this.menu.deZoom(); //Dézoomer sur l'arbre
		}
		if (e.getSource() == this.descendre) {
			this.menu.descendre(); //Afficher un ancètre de moins
		}
		if (e.getSource() == this.monter) {
			this.menu.monter(); //Afficher un ancètre de plus
		}
	}

	// facilite la création des Lable image
	private JLabel creeLabel(Image image)
	{
		JLabel tmp = new JLabel(new ImageIcon(image));
		tmp.addMouseListener(this);
		this.add(tmp);
		return tmp;
	}

	// facilite la creation d'image
	private Image creeImage(String s)
	{
		return Toolkit.getDefaultToolkit().getImage("images/" + s);
	}
}
