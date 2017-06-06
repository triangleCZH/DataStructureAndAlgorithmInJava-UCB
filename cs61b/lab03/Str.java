/**
 * Created by cs61bl-bk on 6/28/16.
 */
public class Str {
    public Str() {

    }

    public static void main(String[] args) {
        String line = "     ";
        String lines = line.trim();
        //for (int i = 0; i < lines.length; i++)
          //  System.out.println(lines[i]);
        if( lines.isEmpty())
        System.out.println("yeah");

    }

}


  /*if (str.charAt(1) == ' ') {
            boolean exception = false;
            int[] spacePos = new int[6];
            int spaceCount = 0;
            for (int i = 0; (i < str.length() - 2); i++) {//find just six spaces with more to be wrong
                if (str.charAt(i + 1) == ' ') {
                    spaceCount++;
                    if (spaceCount > 6)
                        exception = true;
                    else
                        spacePos[spaceCount - 1] = i + 1;
                    if (exception)
                    System.out.print("shit");
                    break;
                }
            }
            for (int i = 0; i < 5; i++) {
                /*if ((spacePos[i + 1] - spacePos[i]) <= 1)
                    System.out.print("shit");
                break;

                for(int j = 0;j<5;j++)

                System.out.println(str.substring(spacePos[j] + 1, spacePos[j + 1] - 1));}

            }*/
//boolean[] rotorUnuse = new boolean[12];//set unused rotors to true
            /*for (int p = 0; p < 12; p++)
                rotorUnuse[p] = true;
            if (spacePos[5] == config.length() - 1)//last space can't be the last char
                throw new EnigmaException("failed");
            for (int i = 0; i < 5; i++) {
                if ((spacePos[i + 1] - spacePos[i]) <= 1)//make sure that between spaces are not nothing
                    throw new EnigmaException("failed");// once is true, it should send out the message saying sth goes wrong
                boolean found = false;//find if the string matches rotors'name or not
                String temp = config.substring(spacePos[i] + 1, spacePos[i + 1] - 1);

            }*/