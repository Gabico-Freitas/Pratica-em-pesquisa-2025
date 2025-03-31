import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String[] frameTypes = {"top_frame", "10_frames", "all_frames"};
        Scanner input = new Scanner(System.in);
        int option;
        
        System.out.println("Select frame type:");
        System.out.println("[0] Top frame");
        System.out.println("[1] 10 frames");
        System.out.println("[2] All frames");
        option = input.nextInt();
        input.close();

        buildAUSheet(frameTypes[option]);
    }

    public static void buildAUSheet(String frameType){
        String[] emotions = {"angry", "happy", "sad"};
        String[] emotionLevels = {"1", "2", "3"};
        String[] techniques = {"+delta", "delta", "nodelta"};
        String title;
        String fileName;
        StringBuilder text = new StringBuilder();

        includeHeader(text);

        for(String emotion : emotions){
            for(String emotionLevel : emotionLevels){
                for(String technique : techniques){
                    title = emotion + "_" + emotionLevel + "_" + technique;
                    fileName = "input\\" + emotion + "_" + emotionLevel + "_" + frameType + "_" + technique + ".csv";    
                    includeRow(text, fileName, title);
                }
            }
        }

        try {
            FileWriter writer = new FileWriter("output.csv");
            writer.append(text);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void includeHeader(StringBuilder sb){
        sb.append("expression, AU01_r, AU02_r, AU04_r, AU05_r, AU06_r, AU07_r, AU09_r, AU10_r, AU12_r, AU14_r, AU15_r, AU17_r, AU20_r, AU23_r, AU25_r, AU26_r, AU45_r, AU01_c, AU02_c, AU04_c, AU05_c, AU06_c, AU07_c, AU09_c, AU10_c, AU12_c, AU14_c, AU15_c, AU17_c, AU20_c, AU23_c, AU25_c, AU26_c, AU28_c, AU45_c\n");
    }

    public static void includeRow(StringBuilder sb, String fileName, String title){
        String[] values;

        sb.append(title);
        sb.append(", ");

        try {
            File f = new File(fileName);
            Scanner reader = new Scanner(f);
            reader.nextLine();
            values = reader.nextLine().split(", ");

            for(int i=676; i<values.length-1; i++){
                sb.append(values[i]);
                sb.append(", ");
            }
            sb.append(values[values.length-1]);
            sb.append("\n");

            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
