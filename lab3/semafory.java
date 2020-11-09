import java.util.concurrent.Semaphore;

public class semafory implements Filozofowie {
    private int filozofowie;

    semafory(int ilosc) {
        
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

class semafora extends Thread {
    
    public static Semaphore[] widelce;
    
    private int mojNum;

    public semafora(int nr) {
        mojNum = nr;
    }

    public static void iloscwidelcow(int ilosc) {
        
        widelce = new Semaphore[ilosc];
        
        for (int i = 0; i < widelce.length; i++)
            
            widelce[i] = new Semaphore(1);
    }

    public void run() {
        
        while (true) {
            
            System.out.println("Mysli: " + mojNum);
            w();

            podnieswidelce();
            
            System.out.println("Zaczynaa jesc: " + mojNum);
            w();

            System.out.println("Konczy jesc: " + mojNum);
            odlozWidelce();
        }
    }

    
    private void odlozWidelce() {
        int lewywidelec = mojNum;
        int prawywidelec = (mojNum + 1) % widelce.length;
        widelce[lewywidelec].release();
        
        widelce[prawywidelec].release();
    }
    
    private void podnieswidelce() {
        
        int lewywidelec = mojNum;
        int prawywidelec = (mojNum + 1) % widelce.length;
        
        widelce[lewywidelec].acquireUninterruptibly();
        
        widelce[prawywidelec].acquireUninterruptibly();
    }


    private void w() {
        try {
            
            Thread.sleep((long) (5000 * Math.random()));
        } 
        catch (InterruptedException e) {
        }
    }
}