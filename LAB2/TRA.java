import java.util.function.Function;

class TRA extends Thread {
    private double w;
    private double p;
    
    private double k;
    
    private double lp;
    private int i;
    
    private Function<Double, Double> f;

    public TRA(double p, double k, double lp, int i, Function<Double, Double> f) {
        
        this.p = p;
        this.k = k;
        
        this.lp = lp;
        this.i = i;
        this.f = f;
    }

    @Override
    public void run() {
        
        double dx = (k - p) / lp;
        double X = p + i * dx;
        
        double T = f.apply(X);
        if (i == 0 || i == lp)
            
            w = T / 2 * dx;
        else
            
            w = T * dx;
        
    }

    public double getw() {
        
        return w;
    }
}