import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class EnigmaFrame extends JFrame {
   private JTextField initPos;
   private JComboBox inner;
   private JComboBox middle;
   private JComboBox out;
   private JButton encrypt;
   private JButton decrypt;
   private JTextArea in;
   private JTextArea output;

   public EnigmaFrame() {
      super();
      String[] rot = {"1","2","3","4","5"};
      initPos = new JTextField("###",4);
      inner = new JComboBox(rot);
      middle = new JComboBox(rot);
      out  = new JComboBox(rot);
      encrypt = new JButton("Encrypt");
      decrypt = new JButton("Decrypt");
      in = new JTextArea(5,20);
      output = new JTextArea(5,20);
      JPanel top = new JPanel(new FlowLayout());
      top.add(new JLabel("Inner"));
      top.add(inner);
      top.add(new JLabel("Middle"));
      top.add(middle);
      top.add(new JLabel("Out"));
      top.add(out);
      top.add(new JLabel("Initial Positions"));
      top.add(initPos);
      top.add(encrypt);
      top.add(decrypt);
      this.add(top,BorderLayout.NORTH);
      JPanel center = new JPanel(new FlowLayout());
      center.add(new JLabel("Input"));
      center.add(in);
      JPanel bottom = new JPanel(new FlowLayout());
      bottom.add(new JLabel("Output"));
      bottom.add(output);
      this.add(center,BorderLayout.CENTER);
      this.add(bottom,BorderLayout.SOUTH);

      EnigmaActionListener eal = new EnigmaActionListener();
      //initPos.addActionListener(eal);
      //inner.addActionListener(eal);
      //middle.addActionListener(eal);
      //out.addActionListener(eal);
      encrypt.addActionListener(eal);
      decrypt.addActionListener(eal);

      this.pack();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


   }

   private class EnigmaActionListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         String[] params = new String[5];
         params[0] = (String)inner.getSelectedItem();
         params[1] = (String)middle.getSelectedItem();
         params[2] = (String)out.getSelectedItem();
         params[3] = initPos.getText();
         String com = e.getActionCommand();
         if(com.equals("Encrypt"))
            params[4] = "encrypt";
         else
            params[4] = "decrypt";


         // println to output string and input from the string
         ByteArrayInputStream bais = new ByteArrayInputStream(in.getText().getBytes());
         InputStream oldIn = System.in;
         System.setIn(bais);
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         PrintStream ps = new PrintStream(baos);
         PrintStream old = System.out;
         System.setOut(ps);
         Comms.main(params);
         // restore output and input
         System.setIn(oldIn);
         System.out.flush();
         System.setOut(old);
         output.setText(baos.toString());
      }
   }


}

