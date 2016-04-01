import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by yyawesome on 15/11/20.
 */
public class Data {

    //time series data
    private ArrayList<Sample> data;

    //labels
    private HashSet<Integer> labels;

    public Data(String filepath) throws IOException
    {
        //read data from file
        this.data=new ArrayList<>();
        this.labels=new HashSet<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8"));
        String s = null;
        while ((s = br.readLine()) != null) {
            Sample sample=new Sample(s);
            labels.add(sample.getLabel());
            data.add(sample);
        }
    }

    public ArrayList<Sample> getData()
    {
        return this.data;
    }
    public HashSet<Integer> getLabels()
    {
        return this.labels;
    }

}
