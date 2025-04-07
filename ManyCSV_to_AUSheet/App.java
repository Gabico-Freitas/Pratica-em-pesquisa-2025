import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class App{
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

    private static void buildAUSheet(String frameType){
        String[] videos = {"video1", "video2", "video3", "video4", "video5", "video6", "video7", "video8", "video9", "video10"};
        String[] emotions = {"angry", "happy", "sad"};
        String[] levels = {"level_1", "level_2", "level_3"};
        String csv;
        String separator = File.separator;
        File file;
        AUSheet sheet = new AUSheet();

        for(String video : videos){
            for(String emotion : emotions){
                for(String level : levels){
                    for(int i=1;; i++){ 
                        csv = getCSVName(i);
                        file = new File ("videos" + separator + video + separator + emotion + separator + level + separator + csv + ".csv");
                        if(file.exists()){
                            addCSVToSheet(file, frameType, sheet);
                        } else{
                            break;
                        }
                    }
                }
            }
        }

        try{
            FileWriter w = new FileWriter("output.csv");
            w.append(sheet.toString());
            w.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    private static String getCSVName(int i){
        if(i>=1 && i<=9){
            return "00" + Integer.toString(i);
        } else if(i>=10 && i<=99){
            return "0" + Integer.toString(i);
        } else if(i>=100 && i<=999){
            return Integer.toString(i);
        } else{
            return null;
        }
    }

    private static void addCSVToSheet(File file, String frameType, AUSheet sheet){
        switch(frameType){
            case "top_frame":
                addCSVTopFrame(file, sheet);
                break;
            case "10_frames":
                addCSV10Frames(file, sheet);
                break;
            case "all_frames":
                addCSVAllFrames(file, sheet);
                break;
        }
    }
    private static void addCSVTopFrame(File file, AUSheet sheet){
        String line;
        String[] tokens;
        double[] values;
        PriorityQueue<Frame> minPQ = new PriorityQueue<>();
        Frame f;
        try{
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while(sc.hasNext()){
                line = sc.nextLine();
                tokens = line.split(", ");
                values = Arrays.stream(tokens, 679, 696).mapToDouble(s -> Double.valueOf(s)).toArray();
                f = new Frame(values, file);
                minPQ.add(f);
                if(minPQ.size()>1){
                    minPQ.poll();
                }
            }
            sc.close();
        } catch(Exception e){
            System.out.println(e);
        }

        for(Frame frame : minPQ){
            sheet.include(frame.getAUValues(), file);
        }
    }
    private static void addCSV10Frames(File file, AUSheet sheet){
        String line;
        String[] tokens;
        double[] values;
        PriorityQueue<Frame> minPQ = new PriorityQueue<>();
        Frame f;
        try{
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while(sc.hasNext()){
                line = sc.nextLine();
                tokens = line.split(", ");
                values = Arrays.stream(tokens, 679, 696).mapToDouble(s -> Double.valueOf(s)).toArray();
                f = new Frame(values, file);
                minPQ.add(f);
                if(minPQ.size()>10){
                    minPQ.poll();
                }
            }
            sc.close();
        } catch(Exception e){
            System.out.println(e);
        }

        for(Frame frame : minPQ){
            sheet.include(frame.getAUValues(), file);
        }
    }
    private static void addCSVAllFrames(File file, AUSheet sheet){
        String line;
        String[] tokens;
        double[] values;
        try{
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while(sc.hasNext()){
                line = sc.nextLine();
                tokens = line.split(", ");
                values = Arrays.stream(tokens, 679, 696).mapToDouble(s -> Double.valueOf(s)).toArray();
                sheet.include(values, file);
            }
            sc.close();
        } catch(Exception e){
            System.out.println(e);
        }

    }
}