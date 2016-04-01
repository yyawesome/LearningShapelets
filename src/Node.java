import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yyawesome on 15/11/12.
 */
public class Node{
    //data
    private double[] d;

    //accumulated number
    private int M;

    //threshold
    private double threshold;

    //connections
    private HashMap<Node,Integer> edge;

    //maxage for connections
    private int maxage;

    public Node(double[] d, int maxage) {
        this.d = new double[d.length];
        for (int i = 0; i < d.length; i++)
            this.d[i] = d[i];
        this.M=1;
        this.threshold = Double.POSITIVE_INFINITY;
        this.edge=new HashMap<>();
        this.maxage = maxage;
    }

    public double distanceToSample(Sample s,double alpha)
    {
        double[] d=s.getD();
        double[] sx=s.getSx();
        double[] sx2 = s.getSx2();
        double minDs = Double.POSITIVE_INFINITY;
        for(int i=0;i<d.length-this.d.length+1;i++)
        {
            double sum=0;
            double mean = (sx[i+this.d.length]-sx[i])/this.d.length;
            double sdev = Math.sqrt((sx2[i+this.d.length]-sx2[i])/this.d.length-mean*mean);
            if(sdev>0.0001) {
                for (int j = 0; j < this.d.length; j++) {
                    double dsj = this.d[j] - (d[i + j] - mean) / sdev;
                    sum += dsj * dsj;
                    if (sum > minDs)
                        break;
                }
                if (sum < minDs)
                    minDs = sum;
            }
            else
            {
                for (int j = 0; j < this.d.length; j++) {
                    double dsj = this.d[j] - 0.0;
                    sum += dsj*dsj;
                    if (sum > minDs)
                        break;
                }
                if (sum < minDs)
                    minDs = sum;
            }
        }
        return Math.exp(-minDs/this.threshold/alpha);
    }

    public void refreshEdge(Node d)
    {
        this.edge.put(d,1);
    }

    public void update(double[] d)
    {
        //update connection's between neighborhood
        for(Node n:new ArrayList<Node>(this.edge.keySet()))
        {
           this.addEdge(n);
            n.addEdge(this);
        }
        //update M
        this.M = this.M+1;

        //update d
        for(int i=0;i<d.length;i++)
        {
            this.d[i] = this.d[i]+(d[i]-this.d[i])/this.M;
        }

    }

    public void addEdge(Node d)
    {
        assert(this.edge.containsKey(d));
        int age = this.edge.get(d)+1;
        if(age>this.maxage)
            this.edge.remove(d);
        else
            this.edge.put(d, age);
    }

    public void removeEdge(Node d)
    {
        if(this.edge.containsKey(d))
            this.edge.remove(d);
    }

    public void updateThreshold()
    {
        double max = Double.NEGATIVE_INFINITY;
        for(Node d:this.edge.keySet())
        {
            double ds = this.distanceTo(d.getData());
            if(ds>max)
                max = ds;
        }
        this.threshold = max;
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(Double.toString(d[0]));
        for(int i=1;i<d.length;i++)
            s.append(","). append(Double.toString(d[i]));
        return s.toString();
    }

    public double distanceTo(double[] d)
    {
        double sum=0.0;
        for(int i=0;i<d.length;i++)
        {
            double ds=this.d[i]-d[i];
            sum+=ds*ds;
        }
        return sum;
    }

    public double[] getData() {
        return d;
    }

    public int getM()
    {
        return this.M;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public HashMap<Node, Integer> getEdge() {
        return edge;
    }
}
