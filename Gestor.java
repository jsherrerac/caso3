public class Gestor extends Thread {
    private int zonaEspera;
    private int Id;
    private BuzonEntrada buzon;
    private int umbral; 
    private Procesador cProc;
    private Almacenamiento cAlm;
    private Memoria cMem;
    private Configuracion config;
    public Gestor(int zonaEspera, int id, BuzonEntrada buzon, int umbral, Procesador cProc, Almacenamiento cAlm,
            Memoria cMem, Configuracion config) {
        this.zonaEspera = zonaEspera;
        Id = id;
        this.buzon = buzon;
        this.umbral = umbral;
        this.cProc = cProc;
        this.cAlm = cAlm;
        this.cMem = cMem;
        this.config = config;
    }
        
}  