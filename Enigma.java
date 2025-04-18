public class Enigma {
    // this array holds the 3 rotors that will be used for encryption/decryption
    private Rotor[] rotors = new Rotor[3];

    // this stores the 5 predefined rotor configurations
    private String rotorInit[] = {
        "#GNUAHOVBIPWCJQXDKRYELSZFMT",
        "#EJOTYCHMRWAFKPUZDINSXBGLQV",
        "#BDFHJLNPRTVXZACEGIKMOQSUWY",
        "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
        "#TGOWHLIFMCSZYRVXQABUPEJKND"
    };

    // constructor sets up the enigma machine with 3 selected rotors and their starting positions
    public Enigma(int innerIndex, int middleIndex, int outerIndex, String startPos) {
        // choosing rotors based on given indices and setting their initial positions
        rotors[0] = new Rotor(rotorInit[innerIndex - 1], startPos.charAt(0));
        rotors[1] = new Rotor(rotorInit[middleIndex - 1], startPos.charAt(1));
        rotors[2] = new Rotor(rotorInit[outerIndex - 1], startPos.charAt(2));
    }

    // encrypts the given message by passing characters through the rotors
    public String encrypt(String message) {
        char[] holding = new char[message.length()];

        for (int i = 0; i < message.length(); i++) { 
            // step 1: find index of char in the inner rotor
            int num1 = rotors[0].indexOf(message.charAt(i));
            // step 2: map it to the outer rotor
            char topRotorDraft1 = rotors[2].charAt(num1);
            // step 3: find index of that character in the middle rotor
            int num2 = rotors[1].indexOf(topRotorDraft1);
            // step 4: get final encrypted character from the outer rotor
            char topRotorFinal = rotors[2].charAt(num2);

            // after each character, rotate the rotors
            rotate();

            holding[i] = topRotorFinal;
        }

        return new String(holding);
    }

    // decrypts a message by reversing the encryption steps
    public String decrypt(String message) {
        char[] holding = new char[message.length()];

        for (int i = 0; i < message.length(); i++) { 
            // step 1: find index of char in the outer rotor
            int num1 = rotors[2].indexOf(message.charAt(i));
            // step 2: map it to the middle rotor
            char middleRotorDraft = rotors[1].charAt(num1);
            // step 3: find index of that character in the outer rotor
            int num2 = rotors[2].indexOf(middleRotorDraft);
            // step 4: get the original character from the inner rotor
            char finalChar = rotors[0].charAt(num2);

            // after each character, rotate the rotors
            rotate();

            holding[i] = finalChar;
        }

        return new String(holding);
    }

    // rotates the rotors in the correct sequence
    private void rotate() {
        // the first rotor rotates every time
        if (rotors[0].rotate()) {
            // if the first rotor completes a full cycle, the middle rotor rotates
            if (rotors[1].rotate()) {
                // if the middle rotor completes a full cycle, the outer rotor rotates
                rotors[2].rotate();
            }
        }
    }
}
