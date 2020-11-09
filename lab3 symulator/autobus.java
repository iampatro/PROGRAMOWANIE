import java.util.Random;

public class autobus extends Thread{
    static int przystanek = 1;
    static int start = 2;
    static int droga = 3;
    static int koniec_drogi = 4;
    static int kolizja = 5;
    static int zaladuj = 1000;
    static int rezerwa = 500;
    int numer;
    int czas;
    int stan;
    przystanek l;
    Random rand;
    public autobus(int numer, int czas, przystanek l){
        this.numer = numer;
        this.czas = czas;
        this.stan = droga;
        this.l=l;
        rand = new Random();
    }
    public void run(){
        while(true){
            if(stan== przystanek){
                if(rand.nextInt(2)==1){
                    stan = start;
                    czas = zaladuj;
                    System.out.println("Na peronie, niedługo ruszy " + numer);
                            stan=l.start(numer);
                }
                else{
                    System.out.println("Czekam na komplet pasazerow");
                }
            }
            else if(stan==start){
                System.out.println("Odjezdzamy "+numer);
                stan= droga;
            }
            else if(stan== droga){
                czas -= rand.nextInt(1000);
                System.out.println("Autobus PKS " + numer + " w drodze");
                if(czas <=rezerwa){
                    stan = koniec_drogi;
                }
                else try{
                    Thread.sleep(rand.nextInt(500));
                }
                catch (Exception e){}
            }
            else if(stan==koniec_drogi){
                System.out.println("autobus "+numer+" prosi o wjazd na peron, czas - "+ czas);
                stan=l.wsiadamy();
                if(stan==koniec_drogi){
                    czas -= rand.nextInt(1000);
                    System.out.println("REZERWA"+ czas);
                    if(czas <=0) stan=kolizja;
                }
            }
            else if(stan==kolizja){
                System.out.println("PKS przeprasza za spoznienie   "+numer);
            }
        }}
}