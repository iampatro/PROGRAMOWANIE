import java.util.function.Function;

class Prostokatow {
    
    public static double oblicz(double p, double k, int lp, Function<Double, Double> f) {
        
        PRO[] watki = new PRO[lp];
        
        for (int i = 0; i < lp; i++) {
            
            watki[i] = new PRO(p, k, lp, i, f);
            
            watki[i].start();
            
        }

        double wynik = 0;
        
        for (int i = 0; i < watki.length; i++) {
            try {
                
                watki[i].join();
                wynik += watki[i].getw();
                
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
        }
        return wynik;
    }
}