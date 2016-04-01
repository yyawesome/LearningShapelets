/**
 * Created by yyawesome on 15/11/20.
 */
public class Sample {

    //store the time series data
    private double[] d;

    //store the accumulated sum of time series
    private double[] sx;

    //store the accumulated sum of squared value
    private double[] sx2;

    //label
    private int label;

    public Sample(String s)
    {
        //create a time series sample from a String

        String[] str=s.split(",");
        int N= str.length;
        this.d=new double[N-1];
        this.sx = new double[N];
        this.sx2 = new double[N];

        //label
        this.label = new Integer(str[0]);

        sx[0]=0;
        sx2[0]=0;
        for(int i=1;i<N;i++) {
            d[i-1] = new Double(str[i]);
            sx[i]=sx[i-1]+d[i-1];
            sx2[i]=sx2[i-1]+d[i-1]*d[i-1];
        }
    }

    public double[][] generateSubsequence(int length)
    {
        //generate all sub-sequence of defined length

        double[][] rec= new double[d.length-length+1][length];
        for(int i=0;i<d.length-length+1;i++)
        {
            double mean = (sx[length+i]-sx[i])/length;
            double sdev = Math.sqrt((sx2[length+i]-sx2[i])/length-mean*mean);
            if(sdev>0.0001) {
                for (int j = 0; j < length; j++) {
                    rec[i][j]=(d[i+j]-mean)/sdev;
                }
            }
            else
            {
                for (int j = 0; j < length; j++) {
                    rec[i][j]=0;
                }
            }
        }
        return rec;
     }

    public double[] getD() {
        return d;
    }

    public void setD(double[] d) {
        this.d = d;
    }

    public double[] getSx() {
        return sx;
    }

    public void setSx(double[] sx) {
        this.sx = sx;
    }

    public double[] getSx2() {
        return sx2;
    }

    public void setSx2(double[] sx2) {
        this.sx2 = sx2;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
