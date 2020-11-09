import java.util.function.Function;

class SIM extends Thread {
    private double w;
    private double p;
    
    private double k;
    private double lp;
    
    private int i;
    private r r;
    private Function<Double, Double> f;
    

    public enum r {
        
        Z, X, T
    }

    public SIM(double p, double k, double lp, int i, r r,
            
            Function<Double, Double> f) {
        
        this.p = p;
        
        this.k = k;
        this.lp = lp;
        
        this.i = i;
        this.r = r;
        this.f = f;
    }

    @Override
    public void run() {
        
        switch (r) {
            
            case Z:
                pz();
                break;
            case X:
                px();
                break;
            case T:
                pt();
                break;
        }
    }

    private void pz() {
        
        double dx = (k - p) / lp;
        
        if (i == 0)
            w = f.apply(p) * dx / 6;
        else if (i == 1)
            
            w = f.apply(k) * dx / 6;
    }

    private void px() {
        
        double dx = (k - p) / lp;
        double xi = p + i * dx;
        w = f.apply(xi) * dx / 6 * 2;
    }

    private void pt() {
        
        double dx = (k - p) / lp;
        double ti = (p + (i - 1) * dx + p + (i + 1) * dx) / 2;
        w = f.apply(ti) * dx / 6 * 4;
    }

    public double getw() {
        return w;
    }
}