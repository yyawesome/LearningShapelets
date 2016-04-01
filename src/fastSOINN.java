import java.util.ArrayList;

/**
 * Created by yyawesome on 15/11/12.
 */
public class fastSOINN {

    private ArrayList<Node> nodes;
    private double lambda;
    private int maxage;
    private double c;
    private int size;

    public fastSOINN(int maxage, double lambda, double c)
    {
        this.maxage=maxage;
        this.lambda=lambda;
        this.c=c;
        this.nodes = new ArrayList<Node>();
        this.size=0;
    }

    public  void insertNewData(double[] d)
    {
        // if nodes.size == 0 or nodes.size==1,initialize the network;
        if(this.nodes.size()==0)
        {
            this.nodes.add(new Node(d,maxage));
            this.size++;
            return;
        }
        else if(this.nodes.size()==1)
        {
            this.nodes.add(new Node(d,maxage));
            double ds = this.nodes.get(0).distanceTo(d)*1.001;
            this.nodes.get(0).setThreshold(ds);
            this.nodes.get(1).setThreshold(ds);
            this.size++;
            return;
        }
        //find winner node and ruuner-up node
        Node winner = null;
        double bestdist = Double.POSITIVE_INFINITY;
        Node secondwinner = null;
        double seconddist = Double.POSITIVE_INFINITY;
        for(int i=0;i<this.nodes.size();i++)
        {
            double ds = this.nodes.get(i).distanceTo(d);
            if(ds<bestdist)
            {
                seconddist = bestdist;
                secondwinner = winner;
                bestdist=ds;
                winner = this.nodes.get(i);
            }
            else if(ds<seconddist)
            {
                seconddist=ds;
                secondwinner = this.nodes.get(i);
            }
        }
        if(bestdist>winner.getThreshold() || seconddist> secondwinner.getThreshold())
        {
            //insert new node if bestdist > winner.threshold or seconddist > secondwinner.threshold
            this.nodes.add(new Node(d, maxage));
        }
        else
        {
            //update winner's data
            winner.update(d);

            //update connection between winner and secondwinner
            winner.refreshEdge(secondwinner);
            secondwinner.refreshEdge(winner);

        }
        //Threshold update for winner and secondwinner
        double ds = winner.distanceTo(secondwinner.getData());
        if(winner.getEdge().size()==0)
            winner.setThreshold(ds);
        else
            winner.updateThreshold();
        if(secondwinner.getEdge().size()==0)
            secondwinner.setThreshold(ds);
        else
            secondwinner.updateThreshold();
        //denoise
        this.size++;
        if(this.size%lambda==0)
            this.denosing();
    }

    public void denosing()
    {
        int sumM = 0;
        for(Node node:this.nodes)
            sumM += node.getM();
        double meanM = (double)sumM /this.nodes.size();
        for(Node node:new ArrayList<>(this.nodes))
        {

            //remove node which has a small M and only one connections
            if(node.getM()<c*meanM && node.getEdge().size()==1) {
                for(Node n:this.nodes)
                    n.removeEdge(node);
                this.nodes.remove(node);
            }

            //remove isolated node which has a M less than average M
            if(node.getM()<meanM && node.getEdge().size()==0){
                for(Node n:this.nodes)
                    n.removeEdge(node);
                this.nodes.remove(node);
            }
        }
    }

    public ArrayList<Node> getNodes()
    {
        return nodes;
    }

}
