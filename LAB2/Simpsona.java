import java.util.ArrayList;

import java.util.function.Function;

class Simpsona{
    
    public static double oblicz(double p, double l, int lp, Function<Double, Double> f) {
        
        ArrayList<SIM> watki = new ArrayList<SIM>();
        

        for (int i = 0; i < 2; i++) {
            
            SIM watek = new SIM(p, l, lp, i, SIM.r.Z, f);
            watek.start();
            watki.add(watek);
        }

        for (int i = 1; i <= lp - 1; i++) {
           SIM watek = new SIM(p, l, lp, i, SIM.r.X, f);
            watek.start();
            watki.add(watek);
        }

        for (int i = 1; i <= lp; i++) {
            SIM watek = new SIM(p, l, lp, i, SIM.r.T, f);
            
            
            watek.start();
            
            watki.add(watek);
        }

        double wynik = 0;
        
        try {
            for (SIM watek : watki) {
                
                watek.join();
                
                wynik += watek.getw();
            }
        } catch (InterruptedException e) {
            
            e.printStackTrace();
            
        }

        return wynik;
    }
}