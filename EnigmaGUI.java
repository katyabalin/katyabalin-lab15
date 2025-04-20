public class EnigmaGUI {
    public static void main(String[] arg) {
        // set up the main gui window for the enigma machine
        EnigmaFrame ef = new EnigmaFrame();
        // give the frame an internal name
        ef.setName("EnigmaGUI");
        // make the window appear on the screen
        ef.setVisible(true);
    }
}
