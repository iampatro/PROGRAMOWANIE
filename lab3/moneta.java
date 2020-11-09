import java.util.Random;
import java.util.concurrent.Semaphore;

public class moneta implements Filozofowie {
    
    private final int filozofowie;

    moneta(int ilosc) {
        
        filozofowie = ilosc;
    }

    @Override
    public void odpal() {
        
        semafora.iloscwidelcow(filozofowie);

        for (int i = 0; i < filozofowie; i++) {
            
            new semafora(i).start();
        }
    }
}

class rzutmoneta extends Thread {
    
    public static Semaphore[] widelce;
    
    private final int mojNum;
    private final Random losuj = new Random();

    public rzutmoneta(int nr) {
        
        mojNum = nr;
    }

    public static void iloscwidelcow(int ilosc) {
        
        widelce = new Semaphore[ilosc];
        for (int i = 0; i < widelce.length; i++)
            widelce[i] = new Semaphore(1);
    }

    @Override
    
    public void run() {
        
        while (true) {
            
            System.out.println("Mysli: " + mojNum);
            w();

            podnieswidelce();
            System.out.println("Zaczyna jesc: " + mojNum);
            w();

            System.out.println("Konczy jesc: " + mojNum);
            odlozwidelce();
        }
    }

    private void podnieswidelce() {
        
        int lewywidelec = mojNum;
        int prawywidelec = (mojNum + 1) % widelce.length;

        boolean pierwszylewy = losuj.nextBoolean();
        
        boolean dwawidelcepodniesione = false;
        
        do {
            if (pierwszylewy)
                
                dwawidelcepodniesione = podnieswpierw(lewywidelec, prawywidelec);
            else
                dwawidelcepodniesione = podnieswpierw(prawywidelec, lewywidelec);
        } while (!dwawidelcepodniesione);
    }
    
    private void odlozwidelce() {
        
        int lewywidelec = mojNum;
        int prawywidelec = (mojNum + 1) % widelce.length;
        
        widelce[lewywidelec].release();
        widelce[prawywidelec].release();
    }

    private boolean podnieswpierw(int wpierw, int potem) {
        widelce[wpierw].acquireUninterruptibly();

        if (!widelce[potem].tryAcquire()) {
            widelce[wpierw].release();
        } else {
            return true;
        }

        return false;
    }


    private void w() {
        
        try {
            Thread.sleep((long) (5000 * Math.random()));
        } catch (InterruptedException e) {
        }
    }
}