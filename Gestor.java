import java.util.ArrayList;
public class Gestor extends Thread {
    private ZonaEspera zona;
    private int Id;
    private BuzonEntrada buzon;
    private Procesador cProc;
    private Almacenamiento cAlm;
    private Memoria cMem;
    private ArrayList<MV> creadas;
    public Gestor(ZonaEspera zonaEspera, int id, BuzonEntrada buzon, Procesador cProc, Almacenamiento cAlm,
            Memoria cMem) {
        this.zona = zonaEspera;
        Id = id;
        this.buzon = buzon;
        this.cProc = cProc;
        this.cAlm = cAlm;
        this.cMem = cMem;
        this.creadas= new ArrayList<MV>();
    }

    public void run(){
        while (true){
            zona.entrarSiCongestionada();
            Solicitud s = buzon.extraer();
            if (s==null) return; //no hay solicitud
            MV mv= new MV(s, cProc, cMem, cAlm, zona);
            creadas.add(mv);
            mv.start();
        }
    }
    public ArrayList<MV> getCreadas(){
        return creadas;
    }
        
}  