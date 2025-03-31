import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class App{
    public static void main(String[] args) {
        String[] frameTypes = {"topframe", "10frames", "allframes"};
        String[] emotions = {"angry", "happy", "sad"};
        String[] emotionLevels = {"1", "2", "3"};
        String[] techniques = {"+delta", "delta", "nodelta"};
        String frameType, emotion, emotionLevel, technique;
        List<ActionUnit> specifiedAUs = new LinkedList<>();
        boolean fractional;
        Scanner input = new Scanner(System.in);
        int option;

        System.out.println("Define the subset to be analyzed:");

        System.out.println("\n1) Which frame types should be included in the subset?");
        System.out.println("[0] Top frame");
        System.out.println("[1] 10 frames");
        System.out.println("[2] All frames");
        System.out.println("[3] All frame types");
        option = input.nextInt();
        switch(option){
            case 0:
                frameType = frameTypes[0];
                break;
            case 1:
                frameType = frameTypes[1];
                break;
            case 2:
                frameType = frameTypes[2];
                break;
            default:
                frameType = ".+";
        }

        System.out.println("\n2) Which emotions should be included in the subset?");
        System.out.println("[0] Angry");
        System.out.println("[1] Happy");
        System.out.println("[2] Sad");
        System.out.println("[3] All emotions");
        option = input.nextInt();
        switch(option){
            case 0:
                emotion = emotions[0];
                break;
            case 1:
                emotion = emotions[1];
                break;
            case 2:
                emotion = emotions[2];
                break;
            default:
                emotion = ".+";
        }

        System.out.println("\n3) Which emotion levels should be included in the subset?");
        System.out.println("[0] Intesity 1");
        System.out.println("[1] Intensity 2");
        System.out.println("[2] Intensity 3");
        System.out.println("[3] All emotion levels");
        option = input.nextInt();
        switch(option){
            case 0:
                emotionLevel = emotionLevels[0];
                break;
            case 1:
                emotionLevel = emotionLevels[1];
                break;
            case 2:
                emotionLevel = emotionLevels[2];
                break;
            default:
                emotionLevel = ".+";
        }

        System.out.println("\n4) Which techniques should be included in the subset?");
        System.out.println("[0] +Delta");
        System.out.println("[1] Delta");
        System.out.println("[2] No delta");
        System.out.println("[3] All techniques");
        option = input.nextInt();
        switch(option){
            case 0:
                technique = "\\" + techniques[0];
                break;
            case 1:
                technique = techniques[1];
                break;
            case 2:
                technique = techniques[2];
                break;
            default:
                technique = ".+";
        }

        System.out.println("\n5) Should the subset include all action units?");
        System.out.println("[0] All action units");
        System.out.println("[1] Only some action units");
        option = input.nextInt();
        if(option == 1){
            System.out.println("Type the number of the desired AU. Type 0 when all are included.");
            ActionUnit au;
            do { 
                option = input.nextInt();
                if(option != 0){
                    au = ActionUnit.getAU(option);
                    if(au != null){
                        specifiedAUs.add(au);
                    } else{
                        System.out.println("Value does not correspond to AU in the result table.");
                    }
                }
                
            } while(option!=0);
        }

        System.out.println("\n6) The subset should be comprised of what type of data?");
        System.out.println("[0] Boolean AU values");
        System.out.println("[1] Fractional AU values");
        option = input.nextInt();
        if(option == 0){
            fractional = false;
        } else{
            fractional = true;
        }

        input.close();
        List<Double> values = listValuesOfSubset(frameType, emotion, emotionLevel, technique, specifiedAUs ,fractional);
        generateStatistics(values);
    }

    //Generates list of values in the constructed subset, using regular expression.
    //If aus is empty, it is assumed all action units should be treated.
    private static List<Double> listValuesOfSubset(String frameType, String emotion, String emotionLevel, String technique, List<ActionUnit> aus, boolean fractional){
        Pattern regEx = Pattern.compile("^" + frameType + "_" + emotion + "_" + emotionLevel + "_" + technique);
        String line;
        String[] splitLine;
        List<Double> values = new LinkedList<>();
        int beginningIndex, endingIndex;
        Set<Integer> allowedIndexes = new HashSet<>();

        if(fractional){
            beginningIndex = 1;
            endingIndex = 17;
        } else{
            beginningIndex = 18;
            endingIndex = 35;
        }

        for(ActionUnit au : aus){
            if(fractional){
                allowedIndexes.add(au.fractionalIndex);
            } else{
                allowedIndexes.add(au.booleanIndex);
            }
        }

        try {
            File f = new File("results.csv");
            Scanner reader = new Scanner(f);
            reader.nextLine();

            while(reader.hasNext()){
                line = reader.nextLine();
                if(regEx.matcher(line).find()){
                    splitLine = line.split(", ");
                    for(int i=beginningIndex; i<=endingIndex; i++){
                        if(aus.isEmpty()){ //If NO action units were specifed, treat all
                            values.add(Double.valueOf(splitLine[i]));
                        } else if(allowedIndexes.contains(i)){ //Else, only treat values related to specified action units
                            values.add(Double.valueOf(splitLine[i]));
                        }
                    }
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return values;
    }

    //Calculates: Simple arithmetic  mean, median, amplitude, population variance, standart deviation and coefficient of varation.
    private static void generateStatistics(List<Double> values){
        //Mean
        double sum = 0;
        for(Double value : values){
            sum += value;
        }
        double mean = sum / values.size();

        //Median
        double median;
        Collections.sort(values);
        if(values.size() % 2 != 0){
            median = values.get(((values.size()+1) / 2) - 1);
        } else{
            median = (values.get(((values.size()+1) / 2) - 1) + values.get((values.size()+1) / 2)) / 2;
        }

        //Amplitude
        double amplitude = values.get(values.size()-1) - values.get(0);

        //Population variance
        double varianceAux = 0;
        for(Double value : values){
            varianceAux += Math.pow(value, 2);
        }
        double variance = (varianceAux / values.size()) - Math.pow(mean, 2);

        //Standart deviation
        double deviation = Math.sqrt(variance);

        //Coefficient of variation
        double varCoef = deviation / mean;

        System.out.println("\nMean: " + mean);
        System.out.println("Median: " + median);
        System.out.println("Amplitude: " + amplitude);
        System.out.println("Population variance: " + variance);
        System.out.println("Standart deviation: " + deviation);
        System.out.println("Coefficient of variation: " + varCoef*100 + "%");

    }
}