
import java.io.File;

public class AUSheet {
    private double[][] data;
    private int a1Included, a2Included, a3Included, h1Included, h2Included, h3Included, s1Included, s2Included, s3Included;

    public AUSheet(){
        this.data = new double[9][17];
        a1Included = 0;
        a2Included = 0;
        a3Included = 0;
        h1Included = 0;
        h2Included = 0;
        h3Included = 0;
        s1Included = 0;
        s2Included = 0;
        s3Included = 0;
    }

    public void include(double[] row, File file){
        String path = file.getPath();
        if(path.contains("angry" + File.separator + "level_1")){
            includeAngry1(row);
        } else if(path.contains("angry" + File.separator + "level_2")){
            includeAngry2(row);
        } else if(path.contains("angry" + File.separator + "level_3")){
            includeAngry3(row);
        } else if(path.contains("happy" + File.separator + "level_1")){
            includeHappy1(row);
        } else if(path.contains("happy" + File.separator + "level_2")){
            includeHappy2(row);
        } else if(path.contains("happy" + File.separator + "level_3")){
            includeHappy3(row);
        } else if(path.contains("sad" + File.separator + "level_1")){
            includeSad1(row);
        } else if(path.contains("sad" + File.separator + "level_2")){
            includeSad2(row);
        } else if(path.contains("sad" + File.separator + "level_3")){
            includeSad3(row);
        } else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString(){
        double[][] meanData = sumToMean();

        StringBuilder sb = new StringBuilder();
        sb.append("expression,AU01_r,AU02_r,AU04_r,AU05_r,AU06_r,AU07_r,AU09_r,AU10_r,AU12_r,AU14_r,AU15_r,AU17_r,AU20_r,AU23_r,AU25_r,AU26_r,AU45_r,");
        sb.append("\n");
        sb.append("angry_1,");
        for(int i=0; i<17; i++){
            sb.append(meanData[0][i]);
            sb.append(",");
        }
        sb.append("\n");
        sb.append("angry_2,");
        for(int i=0; i<17; i++){
            sb.append(meanData[1][i]);
            sb.append(",");
        }
        sb.append("\n");
        sb.append("angry_3,");
        for(int i=0; i<17; i++){
            sb.append(meanData[2][i]);
            sb.append(",");
        }
        sb.append("\n");
        sb.append("happy_1,");
        for(int i=0; i<17; i++){
            sb.append(meanData[3][i]);
            sb.append(",");
        }
        sb.append("\n");
        sb.append("happy_2,");
        for(int i=0; i<17; i++){
            sb.append(meanData[4][i]);
            sb.append(",");
        }
        sb.append("\n");
        sb.append("happy_3,");
        for(int i=0; i<17; i++){
            sb.append(meanData[5][i]);
            sb.append(",");
        }
        sb.append("\n");
        sb.append("sad_1,");
        for(int i=0; i<17; i++){
            sb.append(meanData[6][i]);
            sb.append(",");
        }
        sb.append("\n");
        sb.append("sad_2,");
        for(int i=0; i<17; i++){
            sb.append(meanData[7][i]);
            sb.append(",");
        }
        sb.append("\n");
        sb.append("sad_3,");
        for(int i=0; i<17; i++){
            sb.append(meanData[8][i]);
            sb.append(",");
        }

        return sb.toString();
    }

    private void includeAngry1(double[] angry1){
        for(int i=0; i<angry1.length; i++){
            this.data[0][i] += angry1[i];
        }
        a1Included++;
    }
    private void includeAngry2(double[] angry2){
        for(int i=0; i<angry2.length; i++){
            this.data[1][i] += angry2[i];
        }
        a2Included++;
    }
    private void includeAngry3(double[] angry3){
        for(int i=0; i<angry3.length; i++){
            this.data[2][i] += angry3[i];   
        }
        a3Included++;
    }
    private void includeHappy1(double[] happy1){
        for(int i=0; i<happy1.length; i++){
            this.data[3][i] += happy1[i];
        }
        h1Included++;
    }
    private void includeHappy2(double[] happy2){
        for(int i=0; i<happy2.length; i++){
            this.data[4][i] += happy2[i];
        }
        h2Included++;
    }
    private void includeHappy3(double[] happy3){
        for(int i=0; i<happy3.length; i++){
            this.data[5][i] += happy3[i];
        }
        h3Included++;
    }
    private void includeSad1(double[] sad1){
        for(int i=0; i<sad1.length; i++){
            this.data[6][i] += sad1[i];
        }
        s1Included++;
    }
    private void includeSad2(double[] sad2){
        for(int i=0; i<sad2.length; i++){
            this.data[7][i] += sad2[i];
        }
        s2Included++;
    }
    private void includeSad3(double[] sad3){
        for(int i=0; i<sad3.length; i++){
            this.data[8][i] += sad3[i];
        }
        s3Included++;
    }

    private double[][] sumToMean(){
        double[][] meanData = new double[9][17];

        for(int i=0; i<9; i++){
            for(int j=0; j<17; j++){
                switch(i){
                    case 0:
                        meanData[i][j] = data[i][j] / a1Included;
                        break;
                    case 1:
                        meanData[i][j] = data[i][j] / a2Included;
                        break;
                    case 2:
                        meanData[i][j] = data[i][j] / a3Included;
                        break;
                    case 3:
                        meanData[i][j] = data[i][j] / h1Included;
                        break;
                    case 4:
                        meanData[i][j] = data[i][j] / h2Included;
                        break;
                    case 5:
                        meanData[i][j] = data[i][j] / h3Included;
                        break;
                    case 6:
                        meanData[i][j] = data[i][j] / s1Included;
                        break;
                    case 7:
                        meanData[i][j] = data[i][j] / s2Included;
                        break;
                    case 8:
                        meanData[i][j] = data[i][j] / s3Included;
                        break;
                }
            }
        }

        return meanData;
    }
    
}
