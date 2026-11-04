public class Memoria{
    private MV[] ram; //ram[i] si es null, entonces está libre
    private int gib; //capacidad en capacidad
    private int tamanioPag;
    private int libres;
    public Memoria(int capacidad, int tamanio){

        this.gib=capacidad;
        this.tamanioPag=tamanio;
        int paginasTotales=aPaginas(capacidad);
        this.ram= new MV[paginasTotales];
        this.libres=paginasTotales;
    }
    private int aPaginas (int gib){
        long kib=(long) gib *1024*1024;
        return (int) Math.round((double) kib/tamanioPag);
    }
    public synchronized boolean reservar(MV mv, int gibSolicitados){
        int paginasNecesarias =aPaginas(gibSolicitados);
        if (libres<paginasNecesarias) return false;
        //si hay igual o más libres, entonces reservamos los primeros
        int guardados=0;
        for (int i=0; i<ram.length && guardados<paginasNecesarias; i++){
            if (ram[i]==null){
                ram[i]=mv;
                guardados+=1;
            }
            
        }
        libres -= paginasNecesarias;
        return true;
    }
    public synchronized void liberar(MV mv){
        int liberadas=0;
        for (int i=0; i<ram.length; i++){
            if (ram[i]==mv) {ram[i]=null;
            liberadas++;}
        }
        libres+=liberadas;
    }
   
    public synchronized int getLibres() { return libres; }
    public synchronized int getTotal()  { return ram.length; }

}