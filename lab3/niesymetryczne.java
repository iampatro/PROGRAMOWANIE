import java.util.concurrent.Semaphore;

public class niesymetryczne implements Filozofowie {
    private final int filozofowie;

    niesymetryczne(int ilosc) {
        filozofowie = ilosc;
    }

    @Override
    public void odpal() {
        
        niesymetryczny.iloscwidelcow(filozofowie);

        for (int i = 0; i < filozofowie; i++) {
            new niesymetryczny(i).start();
        }
    }
}

class niesymetryczny extends Thread {
    
    public static Semaphore[] widelce;
    private final int mojNum;

    public niesymetryczny(int nr) {
        
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
            System.out.println("Zaczyna jesc: " + mojNum);
            w();

            System.out.println("Konczy jesc: " + mojNum);
            odlozwidelce();
        }
    }

    
    private void odlozwidelce() {
        int lewywidelec = mojNum;
        int prawywidelec = (mojNum + 1) % widelce.length;
        widelce[lewywidelec].release();
        widelce[prawywidelec].release();
    }
    private void podnieswidelce() {
        
        int lewywidelec = mojNum;
        
        int prawywidelec = (mojNum + 1) % widelce.length;
        
        if (mojNum == 0) {
            
            widelce[prawywidelec].acquireUninterruptibly();
            
            widelce[lewywidelec].acquireUninterruptibly();
            
        } else {
            
            widelce[lewywidelec].acquireUninterruptibly();
            widelce[prawywidelec].acquireUninterruptibly();
        }
    }


    private void w() {
        
        try {
            Thread.sleep((long) (5000 * Math.random()));
        } catch (InterruptedException e) {
        }
    }
}