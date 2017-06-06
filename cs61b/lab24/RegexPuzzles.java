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

public class RegexPuzzles {
    public static List<String> urlRegex(String[] urls) {
        /* Your code here */
        List<String> temp = new ArrayList<>();
        //Pattern pattern;
        for (String a: urls) {
            if (a.matches("\\(.*?https?://(\\w+\\.)+[\\w]{2,3}(/[a-zA-Z]+)+\\.html.*?\\)")) {
                temp.add(a);
            }
        }
        return temp;
    }

    public static List<String> findStartupName(String[] names) {
        /* Your code here */
        List<String> temp = new ArrayList<>();
        //Pattern pattern;
        for (String a: names) {
            //pattern = Pattern.compile(a);
            //Matcher matcher = pattern.matcher("(Data|App|my|on|un)[^i]+(ly|sy|ify|\\.io|\\.fm|\\.tv)");
            if (a.matches("^(Data|App|my|on|un)[a-hj-zA-HJ-Z0-9]+(ly|sy|ify|\\.io|\\.fm|\\.tv)$")) {
                temp.add(a);
            }
        }
        return temp;
    }

    public static BufferedImage imageRegex(String filename, int width, int height) {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file found: " + filename);
        }

        // Possible initialization code
        try {
            int[][][] arr = new int[width][height][3];
            String line;
            Pattern catch3d = Pattern.compile("\\[[0-9]*, [0-9]*, [0-9]*\\]");
            Pattern catchxy = Pattern.compile("\\([0-9]*, [0-9]*\\)");
            while ((line = br.readLine()) != null) {
                // Code for processing each line
                Matcher matchxy = catchxy.matcher(line);
                Matcher match3d = catch3d.matcher(line);
                String line1 = "", line2 = "";
                //if (match3d != null && matchxy != null) // FIXME: 8/9/16  how to make condition
                while (match3d.find())
                line1 = match3d.group(0);
                while (matchxy.find())
                line2 = matchxy.group(0);
                System.out.println(line2);
                String[] arr3d = line1.substring(1, line1.length() - 1).split(", ");
                String[] arrxy = line2.substring(1, line2.length() - 1).split(", ");
                int[] int3d = new int[3];
                int[] intxy = new int[2];
                for (int i = 0; i < 2; i++) {
                    int3d[i] = Integer.parseInt(arr3d[i]);
                    intxy[i] = Integer.parseInt(arrxy[i]);
                }
                int3d[2] = Integer.parseInt(arr3d[2]);
                if (intxy[0] <= width && intxy[1] <= height) {
                    for (int i = 0; i < 3; i++) {
                        arr[intxy[0]][intxy[1]][i] = int3d[i];
                    }
                }
            }
            return arrayToBufferedImage(arr);
        } catch (IOException e) {
            System.err.printf("Input error: %s%n", e.getMessage());
            System.exit(1);
        }

        return null;
    }

    public static BufferedImage arrayToBufferedImage(int[][][] arr) {
        BufferedImage img = new BufferedImage(arr.length, arr[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int pixel = 0;
                for (int k = 0; k < 3; k++) {
                    pixel += arr[i][j][k] << (16 - 8*k);
                }
                img.setRGB(i, j, pixel);
            }
        }

        return img;
    }

    public static void main(String[] args) {
        /* For testing image regex */
        BufferedImage img = imageRegex("m-ystery.txt", 400, 400);

        File outputfile = new File("output_img.jpg");
        try {
            ImageIO.write(img, "jpg", outputfile);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
