public class Main {
    static int ilosc_autobusow= 10;
    static int ilosc_peronow = 2;
    static przystanek przystanek;


    public static void main(String[] args) {
        przystanek = new przystanek(ilosc_peronow, ilosc_autobusow);
        for(int i = 0; i< ilosc_autobusow; i++) {
            new autobus(i, 2000, przystanek).start();
        }
    }

}