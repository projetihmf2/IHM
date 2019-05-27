import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestDialogue {

  public static void main(String[] args) {
      JPanel panelTest = new JPanel();
      JTextField txt1 = new JTextField();
      txt1.setColumns(20);
      JTextField txt2 = new JTextField();
      txt2.setColumns(10);
      panelTest.add(txt1);
      panelTest.add(txt2);
      JOptionPane.showMessageDialog(null, panelTest);
      //affichage de la saisie
      System.out.println(txt1.getText());
      System.out.println(txt2.getText());
  }

}
