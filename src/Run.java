import java.io.IOException;
import java.util.Date;

public class Run {

    public static void main(String[] args) throws IOException {
        for(int i=0;i<Parameters.fileNames.length;i++)
        {
            System.out.println(Parameters.fileNames[i]+":");
            Long start,end;
            start = new Date().getTime();
            Data traindata = new Data(Parameters.userpath+Parameters.fileNames[i]+"/"+Parameters.fileNames[i]+"_TRAIN");
            ShapeletsLearner shapeletsLearner=new ShapeletsLearner((int)Parameters.para[i][0],(int)Parameters.para[i][1],Parameters.para[i][2],Parameters.para[i][3],Parameters.para[i][4]);
            shapeletsLearner.train(traindata);
            end = new Date().getTime();
            System.out.println("    Train time:"+(double)(end-start)/1000+"s");
            Data testdata = new Data(Parameters.userpath+Parameters.fileNames[i]+"/"+Parameters.fileNames[i]+"_TEST");
            System.out.println("    accuracy:" + 100 * shapeletsLearner.computeAccuracy(testdata) + "%");
        }
    }
}
