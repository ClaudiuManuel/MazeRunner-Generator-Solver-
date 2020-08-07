package maze;

public class Spot {
    private int i;
    private int j;
    private boolean isPass;
    private int distance;
    Spot parent;

    public Spot(int i,int j,boolean isPass,int distance){
        this.i=i;
        this.j=j;
        this.isPass=isPass;
        this.distance=distance;
    }

    public int getI(){
        return i;
    }

    public int getJ(){
        return j;
    }

    public boolean findIfPass(){
        return isPass;
    }

    public void setDistance(int distance){
        this.distance=distance;
    }

    public int getDistance(){
        return distance;
    }

    public void setParent(Spot parent){
        this.parent=parent;
    }

    public Spot getParent(){
        return parent;
    }

}
