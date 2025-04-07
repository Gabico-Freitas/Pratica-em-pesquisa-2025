
import java.io.File;

public class Frame implements Comparable<Frame> {
    private double[] AUValues;
    private File sourceFile;

    public Frame(double[] AUValues, File sourceFile){
        this.AUValues = AUValues;
        this.sourceFile = sourceFile;
    }

    @Override
    public int compareTo(Frame f) {
        String path = this.sourceFile.getPath();
        double myIntensity, yourIntensity, result;

        if(path.contains("angry")){
            //2, 3, 5 e 13
            myIntensity = this.AUValues[2] + this.AUValues[3] + this.AUValues[5] + this.AUValues[13];
            yourIntensity = f.AUValues[2] + f.AUValues[3] + f.AUValues[5] + f.AUValues[13];
        } else if(path.contains("happy")){
            //4 e 8
            myIntensity = this.AUValues[4] + this.AUValues[8];
            yourIntensity = f.AUValues[4] + f.AUValues[8];
        } else if(path.contains("sad")){
           //0, 2 e 10 
           myIntensity = this.AUValues[0] + this.AUValues[2] + this.AUValues[10];
           yourIntensity = f.AUValues[0] + f.AUValues[2] + f.AUValues[10];
        } else{
            throw new IllegalStateException();
        }

        result = myIntensity - yourIntensity;
        if(result<0){
            return -1;
        } else if(result==0){
            return 0;
        } else{
            return 1;
        }
    }

    public double[] getAUValues() {
        return AUValues;
    }

    public File getSourceFile() {
        return sourceFile;
    }

}
