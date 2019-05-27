import java.awt.*;
import javax.swing.*;

public class BarreDOutil extends JPanel
{
  private JLabel zoom;
  private JLabel deZoom;
  private JLabel monter;
  private JLabel descendre;
  private Image  imgZoom;
  private Image  imgDeZoom;
  private Image  imgDescendre;
  private Image  imgMonter;

	public BarreDOutil()
  {
    this.setLayout(new GridLayout(1,4));

    this.imgZoom      = Toolkit.getDefaultToolkit().getImage("Image/add-searching.png");
    this.imgDeZoom    = Toolkit.getDefaultToolkit().getImage("Image/delete-searching.png");
    this.imgDescendre = Toolkit.getDefaultToolkit().getImage("Image/arrow-down.png");
    this.imgMonter = Toolkit.getDefaultToolkit().getImage("Image/arrow-up.png");

    this.zoom      = new JLabel(new ImageIcon(this.imgZoom));
    this.deZoom    = new JLabel(new ImageIcon(this.imgDeZoom));
    this.descendre = new JLabel(new ImageIcon(this.imgDescendre));
    this.monter    = new JLabel(new ImageIcon(this.imgMonter));

    this.add(this.zoom);
    this.add(this.deZoom);
    this.add(this.descendre);
    this.add(this.monter);
    this.setVisible(true);
	}
}
