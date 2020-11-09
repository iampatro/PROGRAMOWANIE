public class przystanek {
    static int przystanek = 1;
    static int start = 2;
    static int koniecdrogi = 4;
    int ilosc_pasow;
    int ilosc_zajetych;
    int ilosc_autobosow;
    przystanek(int ilosc_przystankow, int ilosc_autobusow){
        this.ilosc_pasow = ilosc_przystankow;
        this.ilosc_autobosow = ilosc_autobusow;
        this.ilosc_zajetych = 0;
    }
    int start(int numer){
        ilosc_zajetych--;
        System.out.println("Odjazd autobusu "+numer);
        return start;
    }
    void odjazd_pociagu(){
        ilosc_autobosow--;
        System.out.println("przypal");
        if(ilosc_autobosow ==ilosc_pasow) System.out.println("Ilosc autobusow taka sama jak pasow");
    }
    int wsiadamy(){
        try{
            Thread.currentThread().sleep(100);
        }
        catch(Exception ie){
        }
        if(ilosc_zajetych <ilosc_pasow){
            ilosc_zajetych++;
            System.out.println("Wjazd na przystanek ");
            return przystanek;
        }
        else
        {return koniecdrogi;}
    }

}