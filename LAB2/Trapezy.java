import java.util.function.Function;

class Trapezy{
    
   
    public static double oblicz(double P, double K, int lp, Function<Double, Double> f) {
        
        TRA[] watki = new TRA[lp + 1];
        
        for (int i = 0; i < watki.length; i++) {
            
            watki[i] = new TRA(P, K, lp, i, f);
            
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