import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnigmaFrame extends JFrame {

   // input field for rotor starting positions
   private JTextField initPos;
   // dropdowns for selecting rotor numbers
   private JComboBox inner;
   private JComboBox middle;
   private JComboBox out;
   // buttons to encrypt or decrypt
   private JButton encrypt;
   private JButton decrypt;
   // input area for the user’s message
   private JTextArea in;
   // output area to show the result
   private JTextArea output;
   public EnigmaFrame() {
      super();
      // options for rotors 1 through 5
      String[] rot = {"1","2","3","4","5"};
      // set up fields and buttons
      initPos = new JTextField("###", 4);
      inner = new JComboBox(rot);
      middle = new JComboBox(rot);
      out = new JComboBox(rot);
      encrypt = new JButton("Encrypt");
      decrypt = new JButton("Decrypt");
      in = new JTextArea(5, 20);
      output = new JTextArea(5, 20);
      // top panel holds all the settings and buttons
      JPanel top = new JPanel(new FlowLayout());
      top.add(new JLabel("inner"));
      top.add(inner);
      top.add(new JLabel("middle"));
      top.add(middle);
      top.add(new JLabel("out"));
      top.add(out);
      top.add(new JLabel("initial positions"));
      top.add(initPos);
      top.add(encrypt);
      top.add(decrypt);
      this.add(top, BorderLayout.NORTH);
      // center panel holds the message input area
      JPanel center = new JPanel(new FlowLayout());
      center.add(new JLabel("input"));
      center.add(in);
      this.add(center, BorderLayout.CENTER);
      // bottom panel holds the output area
      JPanel bottom = new JPanel(new FlowLayout());
      bottom.add(new JLabel("output"));
      bottom.add(output);
      this.add(bottom, BorderLayout.SOUTH);
      // connect button clicks to the action listener
      EnigmaActionListener eal = new EnigmaActionListener();
      encrypt.addActionListener(eal);
      decrypt.addActionListener(eal);
      // adjust the window to fit everything
      this.pack();
      // close the app when the window is closed
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   // handles what happens when a button is clicked
   private class EnigmaActionListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         // build the input like how it would be entered on the command line
         String[] params = new String[6];
         params[0] = (String)inner.getSelectedItem();
         params[1] = (String)middle.getSelectedItem();
         params[2] = (String)out.getSelectedItem();
         params[3] = initPos.getText().toUpperCase();
         params[4] = e.getActionCommand().equals("Encrypt") ? "encrypt" : "decrypt";
         params[5] = in.getText().toUpperCase().replaceAll("[^A-Z#]", "#");
         try {
            // run the enigma logic and show the result
            String result = Comms.run(params);
            output.setText(result);
         } catch (Exception ex) {
            // if something goes wrong, show the error
            output.setText("error: " + ex.getMessage());
         }
      }
   }
}
