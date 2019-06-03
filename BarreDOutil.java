import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BarreDOutil extends JPanel implements MouseListener
{
  private BarreDeMenu menu;
  private JLabel zoom;
  private JLabel deZoom;
  private JLabel monter;
  private JLabel descendre;
  private JLabel lblSauvegarder;
  private JLabel lblCharger;
  private JLabel lblNouveau;
  private Image  imgZoom;
  private Image  imgDeZoom;
  private Image  imgDescendre;
  private Image  imgMonter;
  private Image  imgSauvegarder;
  private Image  imgCharger;
  private Image  imgNouveau;

	public BarreDOutil(BarreDeMenu menu)
  {
    this.menu = menu;
    this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

    this.imgZoom         = Toolkit.getDefaultToolkit().getImage("Image/add-searching.png");
    this.imgDeZoom       = Toolkit.getDefaultToolkit().getImage("Image/delete-searching.png");
    this.imgDescendre    = Toolkit.getDefaultToolkit().getImage("Image/arrow-down.png");
    this.imgMonter       = Toolkit.getDefaultToolkit().getImage("Image/arrow-up.png");
    this.imgSauvegarder  = Toolkit.getDefaultToolkit().getImage("Image/save.png");
    this.imgCharger      = Toolkit.getDefaultToolkit().getImage("Image/load.png");
    this.imgNouveau      = Toolkit.getDefaultToolkit().getImage("Image/ajouter.png");


    this.zoom        = new JLabel(new ImageIcon(this.imgZoom));
    this.zoom.addMouseListener(this);
    this.deZoom      = new JLabel(new ImageIcon(this.imgDeZoom));
    this.deZoom.addMouseListener(this);
    this.descendre   = new JLabel(new ImageIcon(this.imgDescendre));
    this.descendre.addMouseListener(this);
    this.monter      = new JLabel(new ImageIcon(this.imgMonter));
    this.monter.addMouseListener(this);
    this.lblSauvegarder = new JLabel(new ImageIcon(this.imgSauvegarder));
    this.lblSauvegarder.addMouseListener(this);
    this.lblCharger = new JLabel(new ImageIcon(this.imgCharger));
    this.lblCharger.addMouseListener(this);
    this.lblNouveau = new JLabel(new ImageIcon(this.imgNouveau));
    this.lblNouveau.addMouseListener(this);

    this.add(this.lblNouveau);
    this.add(this.lblCharger);
    this.add(this.lblSauvegarder);
    this.add(this.zoom);
    this.add(this.deZoom);
    this.add(this.descendre);
    this.add(this.monter);

    this.setVisible(true);
	}

  public void mouseExited(MouseEvent e)
  {
    this.setCursor(Cursor.getDefaultCursor());
  }
  public void mouseEntered(MouseEvent e)
  {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  }
  public void mouseReleased(MouseEvent e) { }
  public void mousePressed(MouseEvent e)  { }
  public void mouseClicked(MouseEvent e)
  {
    if(e.getSource() == this.lblNouveau)     { this.menu.nouveau();                   }
    if(e.getSource() == this.lblCharger)     { this.menu.Charger();                   }
    if(e.getSource() == this.lblSauvegarder) { this.menu.Enrengister();               }
    if(e.getSource() == this.zoom)           { System.out.println("Clique Zoom");     }
    if(e.getSource() == this.deZoom)         { System.out.println("Clique Dézoom");  }
    if(e.getSource() == this.descendre)      { System.out.println("Clique Descendre");}
    if(e.getSource() == this.monter)         { System.out.println("Monter");          }
  }
}
