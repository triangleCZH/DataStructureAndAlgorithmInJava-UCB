/**
 * Created by cs61bl-bk on 8/9/16.
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class trie
{
    public static void main(String[] args) {
        String line = "q34tgaerygh6478w67u[1, 2, 3]artgaser5tqat4q (1, 2)aretgrztgas634";
        Pattern catch3d = Pattern.compile("\\[[1-9][0-9]*, [1-9][0-9]*, [1-9][0-9]*\\]");
        Pattern catchxy = Pattern.compile("\\([1-9][0-9]*, [1-9][0-9]*\\)");
            // Code for processing each line
            Matcher matchxy = catchxy.matcher(line);
            Matcher match3d = catch3d.matcher(line);
            String line1 = "", line2 = "";
            //if (match3d != null && matchxy != null) // FIXME: 8/9/16  how to make condition
            while (match3d.find())
                line1 = match3d.group(0);
            while (matchxy.find())
                line2 = matchxy.group(0);
            String[] arr3d = line1.substring(1, line1.length() - 1).split(", ");
            String[] arrxy = line2.substring(1, line2.length() - 1).split(", ");
            /*int[] int3d = new int[3];
            int[] intxy = new int[2];
            for (int i = 0; i < 2; i++) {
                int3d[i] = Integer.parseInt(arr3d[i]);
                intxy[i] = Integer.parseInt(arrxy[i]);
            }
            int3d[2] = Integer.parseInt(arr3d[2]);*/
        System.out.println(arr3d[2]);
        System.out.println(arrxy[1]);
    }
}
