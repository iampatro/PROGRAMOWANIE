import java.util.function.Function;

public class Main {
    public static void main(String[] args)
    {
        double p = 0;
        
        double k = 20*Math.PI;
        
        int n = 1000;
        
        Function<Double, Double> f = (Double x) -> {return Math.pow(x, 2) * Math.sin(-5*x) + 2;};

        double wynik = Prostokatow.oblicz(p, k, n, f);
        
        System.out.println("prostokątów: " + wynik);

        wynik = Trapezy.oblicz(p, k, n, f);
        
        System.out.println("trapezow: " + wynik);        

        wynik = Simpsona.oblicz(p, k, n, f);
        
        System.out.println("simpsona: " + wynik);  
    }
}