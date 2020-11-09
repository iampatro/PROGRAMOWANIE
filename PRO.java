import java.util.function.Function;


class PRO extends Thread {
    
    private double w;
    private double p;
    private double k;
    private double lp;
    
    private int i;
    private Function<Double, Double> f;

    public PRO(double p, double k, double lp, int i, Function<Double, Double> f) {
        
        this.p = p;
        this.k = k;
        this.lp = lp;
        
        this.i = i;
        this.f = f;
    }

    @Override
    public void run() {
        double dx = (k - p) / lp;
        
        double X = dx * (i + 1);
        double T = f.apply(X);
        
        w = T * dx;
    }

    public double getw() {
        
        return w;
    }
}