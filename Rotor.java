public class Rotor {
    // stores the rotor configuration (characters in their current order)
    private String rotorValues;
    // stores the character that the rotor started with
    private char startChar;

    // constructor: initializes the rotor with given values and starting character
    public Rotor(String v, char c) {
        this.rotorValues = v;
        this.startChar = c;

        // rotates the rotor until the starting character is at the top
        while (!this.rotate());
    }

    // rotates the rotor one position clockwise
    public boolean rotate() {
        // check if the rotor has completed a full cycle
        boolean temp = false;
        if (charAt(0) == startChar) {
            temp = true;
        }
        // shifts the last character to the front to simulate rotation
        this.rotorValues = rotorValues.substring(rotorValues.length() - 1) + rotorValues.substring(0, rotorValues.length() - 1);
        return temp; // returns true if the rotor is back at its start position
    }

    // returns the index of a given character in the rotor string
    public int indexOf(char c) {
        return rotorValues.indexOf(c);
    }

    // returns the character at a given index in the rotor string
    public char charAt(int idx) {
        return rotorValues.charAt(idx);
    }
}
