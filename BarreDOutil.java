import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BarreDOutil extends JPanel implements MouseListener {
	private BarreDeMenu menu;
	private JLabel zoom;
	private JLabel deZoom;
	private JLabel monter;
	private JLabel descendre;
	private JLabel lblSauvegarder;
	private JLabel lblCharger;
	private JLabel lblNouveau;
	private Image imgZoom;
	private Image imgDeZoom;
	private Image imgDescendre;
	private Image imgMonter;
	private Image imgSauvegarder;
	private Image imgCharger;
	private Image imgNouveau;

	public BarreDOutil(BarreDeMenu menu) {
		this.menu = menu;
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		// cree les image
		this.imgZoom = this.creeImage("add-searching.png");
		this.imgDeZoom = this.creeImage("delete-searching.png");
		this.imgDescendre = this.creeImage("arrow-down.png");
		this.imgMonter = this.creeImage("arrow-up.png");
		this.imgSauvegarder = this.creeImage("save.png");
		this.imgCharger = this.creeImage("load.png");
		this.imgNouveau = this.creeImage("ajouter.png");
		// cree les label et les ajoute
		this.zoom = this.creeLabel(this.imgZoom);
		this.deZoom = this.creeLabel(this.imgDeZoom);
		this.descendre = this.creeLabel(this.imgDescendre);
		this.monter = this.creeLabel(this.imgMonter);
		this.lblSauvegarder = this.creeLabel(this.imgSauvegarder);
		this.lblCharger = this.creeLabel(this.imgCharger);
		this.lblNouveau = this.creeLabel(this.imgNouveau);

		this.setVisible(true);
	}

	public void mouseExited(MouseEvent e) {
		this.setCursor(Cursor.getDefaultCursor());
	}

	public void mouseEntered(MouseEvent e) {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.lblNouveau) {
			this.menu.nouveau();
		}
		if (e.getSource() == this.lblCharger) {
			this.menu.Charger();
		}
		if (e.getSource() == this.lblSauvegarder) {
			this.menu.Enrengister();
		}
		if (e.getSource() == this.zoom) {
			this.menu.zoom();
		}
		if (e.getSource() == this.deZoom) {
			this.menu.deZoom();
		}
		if (e.getSource() == this.descendre) {
			this.menu.diminuer();
		}
		if (e.getSource() == this.monter) {
			this.menu.augmenter();
		}
	}

	// private

	// facile la creation des Lable image
	private JLabel creeLabel(Image image) {
		JLabel tmp = new JLabel(new ImageIcon(image));
		tmp.addMouseListener(this);
		this.add(tmp);
		return tmp;
	}

	// facilite la creatio d'image
	private Image creeImage(String s) {
		return Toolkit.getDefaultToolkit().getImage("images/" + s);
	}
}
