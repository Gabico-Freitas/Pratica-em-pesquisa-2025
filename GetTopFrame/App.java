import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        for(int i=1; i<=10; i++){
            System.out.printf("Top frame from video%d\\happy\\level_3\\002.csv = %d\n", i, topFrame("videos\\video"+i+"\\happy\\level_3\\002.csv"));
        }
    }
    
    public static int topFrame(String filename){
        int topFrame = -1;
        File f = new File(filename);

        try{
            Scanner sc = new Scanner(f);
            sc.nextLine();
            double topSum = -1;
            while(sc.hasNext()){
                String line = sc.nextLine();
                String[] tokens = line.split(", ");
                double sumAUs = Double.valueOf(tokens[683]) + Double.valueOf(tokens[687]);
                if(sumAUs > topSum){
                    topSum = sumAUs;
                    topFrame = Integer.parseInt(tokens[0]);
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }

        return topFrame;
    }
}