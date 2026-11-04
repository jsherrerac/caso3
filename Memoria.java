public class Memoria{
    private MV[] ram; //ram[i] si es null, entonces está libre
    private int gib; //capacidad en capacidad
    private int tamanioPag;
    private int libres;
    public Memoria(int capacidad, int tamanio){

        this.gib=capacidad;
        this.tamanioPag=tamanio;
        int paginasTotales=(int) Math.round(1024*1024*this.gib/this.tamanioPag);
        this.ram= new MV[paginasTotales];
        this.libres=paginasTotales;
    }

    public synchronized boolean reservar(MV mv, int n){
        if (libres<n) return false;
        //si hay igual o más libres, entonces reservamos los primeros
        int guardados=0;
        for (int i=0; i<ram.length && guardados<n; i++){
            if (ram[i]==null){
                ram[i]=mv;
                guardados+=1;
            }
            
        }
        libres -= n;
        return true;
    }
    public synchronized void liberar(MV mv){
        int liberadas=0;
        for (int i=0; i<ram.length; i++){
            if (ram[i]==mv) ram[i]=null;
        }
        libres+=liberadas;
    }
    public synchronized int getLibres() { return libres; }
    public synchronized int getTotal()  { return ram.length; }

}