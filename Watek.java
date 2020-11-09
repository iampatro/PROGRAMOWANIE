import java.util.function.Function;


class Watek extends Thread
{
    private double w;
    private double x;
    
    private Function<Double, Double> f;
    

    public Watek(double x, Function<Double, Double> f)
            
    {
        this.x = x;
        
        this.f = f;
    } 
    public void run()
    {
        w = f.apply(x);
        
    }
    public double getw()
            
    {
        return w;
    }
}