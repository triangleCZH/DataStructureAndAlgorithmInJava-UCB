/**
 * Created by cs61bl-bk on 6/29/16.
 */
public class a {
    String convert(String msg) {


           String writer = "";
                char chs[]= msg.toCharArray();
                for(int i=0;i<msg.length();i++){
                    if(rotors[4].atNotch()){
                        if(rotors[3].atNotch()){
                            if(rotors[2].atNotch()){
                                rotors[3].advance();
                            }
                            rotors[2].advance();
                        }
                        rotors[3].advance();
                    }
                    rotors[4].advance();

                    int inverse = 0;
                    char writerChar = chs[i];
                    if((writerChar>='a')||(writerChar<='z'))
                        writerChar = Rotor.toLetter(writerChar-'a') ;
                    else if(!((writerChar>='A')||(writerChar<='Z')))
                        throw new EnigmaException("failed");
                    for(int k=4;k>=0;k--){
                        writerChar = oneConvert(writerChar,k,inverse);
                    }
                    inverse =1;
                    for(int k=1;k<5;k++){
                        writerChar = oneConvert(writerChar,k,inverse);
                    }
                    writer+=String.valueOf(writerChar);


                }
                return writer;
            }
        }

    /*rotors[4].advance();
                if(rotors[4].atNotch()){// reset the _setting to make sure they have rotated
                    rotors[3].advance();
                    if(rotors[3].atNotch()){
                        rotors[2].advance();
                        if(rotors[2].atNotch())
                           s
                    }
                }*/

    char oneConvert(char a,int i,int inverse){
        if(inverse == 0)
        {if(i>0)
        {int remainder1 = (Rotor.toIndex(a)+rotors[i].getSetting())%26;
            int remainder2 = (rotors[i].convertForward(remainder1)- rotors[i].getSetting())%26;
            return Rotor.toLetter(remainder2);}
        else if(i==0){
            int remainder1 = rotors[i].convertForward(Rotor.toIndex(a));
            return Rotor.toLetter(remainder1);
        }
        else throw new EnigmaException("failed");}
        else {
            if (i > 0) {
                int remainder1 = (Rotor.toIndex(a) + rotors[i].getSetting()) % 26;
                int remainder2 = (rotors[i].convertBackward(remainder1) - rotors[i].getSetting()) % 26;
                return Rotor.toLetter(remainder2);
            } else throw new EnigmaException("failed");
        }

    }
}
