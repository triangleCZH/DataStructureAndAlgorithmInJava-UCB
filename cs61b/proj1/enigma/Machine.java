// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/**
 * Class that represents a complete enigma machine.
 *
 * @author
 */
class Machine {
    private Rotor[] rotors = new Rotor[5];

    Machine() {

        for (int i = 0; i < 5; i++) {
            Rotor R = new Rotor("rotors", 12);
            this.rotors[i] = R;

        }
    }

    void buildRotors(Rotor[] rotortry) {
        System.arraycopy(rotortry, 0, rotors, 0, 5);
    }

    void setRotors(String[] sarra) {

        boolean[] rotorUnuse = new boolean[12]; //set unused rotors to true
        for (int p = 0; p < 12; p++) {
            rotorUnuse[p] = true;
        }
        for (int i = 1; i < sarra.length - 1; i++) { //from 1 to 5;
            boolean found = false;
            for (int j = 0; j < 12; j++) {
                if (sarra[i].equals(PermutationData.ROTOR_SPECS[j][0])) {
                    if (!rotorUnuse[j]) {
                        throw new EnigmaException("failed");
                    } else {
                        rotors[i - 1].setRotorName(sarra[i]);
                        rotors[i - 1].setRotorCode(j);
                        rotorUnuse[j] = false;
                        found = true;
                    }
                }
            }
            if (!found) {
                throw new EnigmaException("failed");
            }
        }
        if ((rotors[4].getRotorCode() > 7) || (rotors[3].getRotorCode() > 7)
                || (rotors[2].getRotorCode() > 7) || (rotors[1].getRotorCode() < 8)
                || (rotors[1].getRotorCode() > 9) || (rotors[0].getRotorCode() < 10)) {
            throw new EnigmaException("failed");
        }
        if (sarra[6].length() != 4) {
            throw new EnigmaException("failed");
        }
        for (int k = 0; k < 4; k++) {
            rotors[k + 1].set(Rotor.toIndex(sarra[6].charAt(k)));
        }
    }

    /**
     * Returns the encoding/decoding of MSG, updating the state of
     * the rotors accordingly.
     */
    String convert(String msg) {
        if (msg == null) {
            throw new EnigmaException("failed");
        } else {
            String writer = "";
            char[] chs = msg.toCharArray();
            for (int i = 0; i < msg.length(); i++) {
                if (rotors[3].atNotch()) {
                    rotors[2].advance();
                    rotors[3].advance();
                    rotors[4].advance();
                } else if (rotors[4].atNotch()) {
                    rotors[3].advance();
                    rotors[4].advance();
                } else {
                    rotors[4].advance();
                }
                int inverse = 0;
                char writerChar = chs[i];
                if ((writerChar >= 'a') && (writerChar <= 'z')) {
                    writerChar = Rotor.toLetter(writerChar - 'a');
                } else if (!((writerChar >= 'A') || (writerChar <= 'Z'))) {
                    throw new EnigmaException("failed");
                }
                for (int k = 4; k >= 0; k--) {
                    writerChar = oneConvert(writerChar, k, inverse);
                }
                inverse = 1;
                for (int k = 1; k < 5; k++) {
                    writerChar = oneConvert(writerChar, k, inverse);
                }
                writer += String.valueOf(writerChar);
            }
            return writer;
        }

    }

    char oneConvert(char a, int i, int inverse) {
        if (inverse == 0) {
            if (i > 0) {
                int remainder1 = (Rotor.toIndex(a)
                        + rotors[i].getSetting() + Rotor.ALPHABET_SIZE) % 26;
                int remainder2 = (rotors[i].convertForward(remainder1) - rotors[i].getSetting()
                        + Rotor.ALPHABET_SIZE) % 26;
                return Rotor.toLetter(remainder2);
            } else if (i == 0) {
                int remainder1 = rotors[i].convertForward(Rotor.toIndex(a));
                return Rotor.toLetter(remainder1);
            } else throw new EnigmaException("failed");
        } else {
            if (i > 0) {
                int remainder1 = (Rotor.toIndex(a) + rotors[i].getSetting()
                        + Rotor.ALPHABET_SIZE) % 26;
                int remainder2 = (rotors[i].convertBackward(remainder1) - rotors[i].getSetting()
                        + Rotor.ALPHABET_SIZE) % 26;
                return Rotor.toLetter(remainder2);
            } else throw new EnigmaException("failed");
        }

    }
}
