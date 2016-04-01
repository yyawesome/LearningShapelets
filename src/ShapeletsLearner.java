import de.bwaldvogel.liblinear.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by yyawesome on 15/11/20.
 */
public class ShapeletsLearner {
    private int maxLength;
    private int minLength;
    private double beta;
    private double alpha;
    private double C = 1;    // cost of constraints violation

    private Model model;
    private HashMap<Node,Integer> shapelets;
    public ShapeletsLearner(int maxLength, int minLength, double beta, double alpha,double C) {
        this.maxLength = maxLength;
        this.minLength = minLength;
        this.beta = beta;
        this.alpha = alpha;
        this.C= C;
    }

    public void train(Data data)
    {
        this.shapelets = new HashMap<>();
        ArrayList<Node> candidataShapelets=new ArrayList<>();
        for(int Len=maxLength;Len>=minLength;Len/=beta)
        {
            fastSOINN fs = new fastSOINN(100,100,0.3);
            for(Sample s:data.getData())
            {
                for(double[] d:s.generateSubsequence(Len))
                    fs.insertNewData(d);
            }
            for(Node node:fs.getNodes())
                    candidataShapelets.add(node);
        }
        Feature[][] features = new Feature[data.getData().size()][candidataShapelets.size()];
        double[] labels=new double[data.getData().size()];
        for(int i=0;i<data.getData().size();i++)
        {
            for(int j=0;j<candidataShapelets.size();j++)
                features[i][j]=new FeatureNode(j+1,candidataShapelets.get(j).distanceToSample(data.getData().get(i),alpha));
            labels[i] = (double)data.getData().get(i).getLabel();
        }

        Problem problem = new Problem();
        problem.l = data.getData().size(); // number of training examples
        problem.n = candidataShapelets.size(); // number of features
        problem.x = features; // feature nodes
        problem.y = labels; // target values

        SolverType solver = SolverType.getById(5); // -s 5

        double eps = 0.0001; // stopping criteria
        Parameter parameter = new Parameter(solver, C, eps, 10000);
        Linear.disableDebugOutput();
        Linear.resetRandom();
        System.out.println("    Start train L1-regularized L2-loss support vector classification!");
        this.model = Linear.train(problem, parameter);

        HashSet<Integer> zerofeatures =new HashSet<Integer>();
        for(int i=1;i<=candidataShapelets.size();i++)
        {
            boolean zero = true;
            for(int j=0;j<data.getLabels().size();j++)
            {
                if(Math.abs(model.getDecfunCoef(i,j))>0.0001)
                    zero = false;
            }
            if(zero)
                zerofeatures.add(i);
        }

        for(int i=1;i<=candidataShapelets.size();i++)
        {
            if(!zerofeatures.contains(i))
                shapelets.put(candidataShapelets.get(i-1),i);
        }
        System.out.println("    Useful Shapelet:"+shapelets.size());
    }

    public double computeAccuracy(Data data)
    {
        int correctSize= 0;
        for(Sample s:data.getData())
        {
            if(s.getLabel() == (int)predict(s))
                correctSize++;
        }
        System.out.println("    CorrectNum/TotalTestNum:"+correctSize + "/" + data.getData().size());
        return  (double)correctSize/data.getData().size();
    }

    public double predict(Sample s)
    {
        return Linear.predict(model,this.transformToFeature(s));
    }
    public Feature[] transformToFeature(Sample s)
    {
        Feature[] f=new Feature[shapelets.size()];
        int index=0;
        for(Node shapelet:shapelets.keySet())
        {
            f[index++] = new FeatureNode(shapelets.get(shapelet),shapelet.distanceToSample(s,alpha));
        }
        return f;
    }
}
