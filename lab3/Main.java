import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        int r;
        
        int ilosc;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                clear();
                System.out.println("Wybierz:");
                System.out.println("1 Semafory");
                System.out.println("2 Niesymetryczne");
                System.out.println("3 Moneta");
                
                r = scanner.nextInt();
                
            }while(r < 1 || r > 3);
            
            do{
                clear();
                System.out.println("Ile filozofow:");
                ilosc = scanner.nextInt();
            }
            while(ilosc <2 || ilosc > 100);
        }

        Filozofowie f;
        switch (r) {
            
            case 1 -> f = new semafory(ilosc);
            case 2 -> f = new niesymetryczne(ilosc);
            case 3 -> f = new moneta(ilosc);
            
            default -> {
                
                f = new semafory(2);
                
                System.exit(1);
            }
        }

        f.odpal();
    }

    private static void clear()
    {
        try {
            
            Runtime.getRuntime().exec("clear");
            
        } catch (IOException e) {
        }
    }
}