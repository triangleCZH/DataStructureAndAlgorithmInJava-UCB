// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/**
 * Class that represents a rotor in the enigma machine.
 *
 * @author
 */
class Rotor {
    // This needs other methods, fields, and constructors
    Rotor(String rotorName, int setting) {
        this.rotorName = rotorName;
        this.setting = setting;

    }

    /**
     * Size of alphabet used for plaintext and ciphertext.
     */
    static final int ALPHABET_SIZE = 26;

    int getCode(String rotorName1) {
        boolean found = false;
        int i = 0;
        while (!found) {
            if (PermutationData.ROTOR_SPECS[i][0].equals(rotorName1)) {
                found = true;
            } else {
                i++;
            }
        }
        return i;
    }

    /**
     * Assuming that P is an integer in the range 0..25, returns the
     * corresponding upper-case letter in the range A..Z.
     */
    static char toLetter(int p) {
        return (char) (p + 'A');
    }

    /**
     * Assuming that C is an upper-case letter in the range A-Z, return the
     * corresponding index in the range 0..25. Inverse of toLetter.
     */
    static int toIndex(char c) {
        return c - 'A';
    }

    int getSetting() {
        return setting;
    }

    public int getRotorCode() {
        return rotorCode;
    }

    public void setRotorCode(int rotorCode) {
        this.rotorCode = rotorCode;
    }

    public String getRotorName() {
        return rotorName;
    }

    public void setRotorName(String rotorName) {
        this.rotorName = rotorName;
    }

    /**
     * Set getSetting() to POSN.
     */
    void set(int posn) {
        assert 0 <= posn && posn < ALPHABET_SIZE;
        setting = posn;
    }

    /**
     * Return the conversion of P (an integer in the range 0..25)
     * according to my permutation.
     */
    int convertForward(int p) {
        p = (p + ALPHABET_SIZE) % 26;
        return (PermutationData.ROTOR_SPECS[getCode(rotorName)][1].charAt(p) - 'A');
    }

    /**
     * Return the conversion of E (an integer in the range 0..25)
     * according to the inverse of my permutation.
     */
    int convertBackward(int e) {
        e = (e + ALPHABET_SIZE) % 26;
        return PermutationData.ROTOR_SPECS[getCode(rotorName)][2].charAt(e) - 'A';
    }

    /**
     * Returns true iff I am positioned to allow the rotor to my left
     * to advance.
     */
    boolean atNotch() {
        return ((this.getCode(rotorName) < 5) && (toLetter(getSetting())
                == (PermutationData.ROTOR_SPECS[getCode(rotorName)][3]).charAt(0)))
                || ((this.getCode(rotorName) >= 5) && (this.getCode(rotorName) < 8)
                &&  ((this.getSetting() == 25) || (this.getSetting() == 12)));
    }


    /**
     * Advance me one position.
     */
    void advance() {
        set((this.getSetting() + 1) % ALPHABET_SIZE);
    }

    /**
     * My current setting (index 0..25, with 0 indicating that 'A'
     * is showing).
     */
    private int setting;
    private String rotorName;
    private int rotorCode;

}
