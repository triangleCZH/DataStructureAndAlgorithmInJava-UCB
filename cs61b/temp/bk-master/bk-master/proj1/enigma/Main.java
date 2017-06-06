// This is a SUGGESTED skeleton file.  Throw it away if you want.
package enigma;

import java.io.*;

/**
 * Enigma simulator.
 *
 * @author
 */
public final class Main {

    // WARNING: Not all methods that have code in them are complete!

    /**
     * Process a sequence of encryptions and decryptions, as
     * specified in the input from the standard input.  Print the
     * results on the standard output. Exits normally if there are
     * no errors in the input; otherwise with code 1.
     */
    public static void main(String[] args) {
        Machine M;
        BufferedReader input = null;
        try {
            input = new BufferedReader(
                    new InputStreamReader(new FileInputStream(args[0])));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file found.");
        }

        String outputFilename;
        if (args.length >= 2) {
            outputFilename = args[1];
        } else {
            outputFilename = "output.txt";
        }

        //buildRotors();//only builds five empty rotors, without any information on it;

        M = null;
        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isConfigurationLine(line)) {
                    M = new Machine(); // just useless line
                    configure(M, line); //            should deal with this configure line
                } else {
                    if (M == null) {
                        throw new EnigmaException("failed");
                    }
                    writeMessageLine(M.convert(standardize(line)), outputFilename);
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /**
     * Return true iff LINE is an Enigma configuration line.
     */
    private static boolean isConfigurationLine(String line) {
        String line1 = line.trim();
        if (line1.isEmpty()) {
            return false;
        }
        if (line.charAt(0) == '*') {
            line = line.trim();
            String[] sarra = line.split(" ");
            for (int p = 1; p < 7; p++) {
                if (sarra[p] == null) {
                    throw new EnigmaException("failed");
                }
            }
            Machine N = new Machine();
            Rotor[] rotorTry = new Rotor[5];
            Rotor R1 = new Rotor("a", 0);
            Rotor R2 = new Rotor("a", 0);
            Rotor R3 = new Rotor("a", 0);
            Rotor R4 = new Rotor("a", 0);
            Rotor R5 = new Rotor("a", 0);
            rotorTry[0] = R1;
            rotorTry[1] = R2;
            rotorTry[2] = R3;
            rotorTry[3] = R4;
            rotorTry[4] = R5;
            boolean[] rotorUnuse = new boolean[12];
            for (int p = 0; p < 12; p++) {
                rotorUnuse[p] = true;
            }
            for (int i = 1; i < sarra.length - 1; i++) {
                boolean found = false;
                for (int j = 0; j < 12; j++) {
                    if (sarra[i].equals(PermutationData.ROTOR_SPECS[j][0])) {
                        if (!rotorUnuse[j]) {
                            throw new EnigmaException("failed");
                        } else {
                            rotorTry[i - 1].setRotorName(sarra[i]);
                            rotorTry[i - 1].setRotorCode(j);
                            rotorUnuse[j] = false;
                            found = true;
                        }
                    }
                }
                if (!found) {
                    throw new EnigmaException("failed");
                }
            }
            if ((rotorTry[4].getRotorCode() > 7) || (rotorTry[3].getRotorCode() > 7)
                    || (rotorTry[2].getRotorCode() > 7) || (rotorTry[1].getRotorCode() < 8)
                    || (rotorTry[1].getRotorCode() > 9) || (rotorTry[0].getRotorCode() < 10)) {
                throw new EnigmaException("failed");
            }
            if (sarra[6].length() != 4) {
                throw new EnigmaException("failed");
            }
            for (int k = 0; k < 4; k++) {
                rotorTry[k + 1].set(Rotor.toIndex(sarra[6].charAt(k)));
            }
            N.buildRotors(rotorTry);
            return true;
        } else return false;
    }

    /**
     * Configure M according to the specification given on CONFIG,
     * which must have the format specified in the assignment.
     */
    private static void configure(Machine M, String config) {
        if (config == null) {
            throw new EnigmaException("failed");
        }
        String[] sarra = config.split(" ");
        for (int i = 1; i < sarra.length; i++) {
            for (int j = 0; j < sarra[i].length(); j++) {
                if (!((sarra[i].charAt(j) >= 'A') && (sarra[i].charAt(j) <= 'Z'))) {
                    throw new EnigmaException("failed");
                }
            }
        }
        M.setRotors(sarra);
    }


    /**
     * Return the result of converting LINE to all upper case,
     * removing all blanks and tabs.  It is an error if LINE contains
     * characters other than letters and blanks.
     */
    private static String standardize(String line) {
        if (line == null) {
            throw new EnigmaException("failed");
        } else {
            char[] chs = line.toCharArray();
            //boolean exception =false;
            String writeLine = "";
            for (int i = 0; i < line.length()/*&& !exception*/; i++) {
                if ((chs[i] >= 'a') && (chs[i] <= 'z')) {
                    chs[i] = Rotor.toLetter(chs[i] - 'a');
                    writeLine += String.valueOf(chs[i]);
                } else if (chs[i] == ' ') {
                    writeLine += "";
                } else if ((chs[i] >= 'A') && (chs[i] <= 'Z')) {
                    writeLine += String.valueOf(chs[i]);
                } else {
                    throw new EnigmaException("failed");
                }
            }
            return writeLine;
        }
    }

    /**
     * Write MSG in groups of five to out file (except that the last
     * group may have fewer letters).
     */
    private static void writeMessageLine(String msg, String filename) {
        try {
            FileWriter writer = new FileWriter(filename, true);
            String outputString = "";
            for (int i = 0; i < msg.length(); i += 5) {
                outputString += msg.substring(i, Math.min(i + 5, msg.length()));
                if (i + 5 < msg.length()) {
                    outputString += " ";
                }
            }

            writer.write(outputString + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException when writing: " + e);
        }
    }

    /** Create all the necessary rotors. */
   /*public static void buildRotors() {
    }*/

}
