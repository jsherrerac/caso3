public class Procesador  {
    private MV[] cpus; //cpu[i] si es null, entonces está libre
    private int libres;
    public Procesador(int capacidad){
        this.cpus= new MV[capacidad];
        this.libres=capacidad;
    }
    public synchronized boolean reservar(MV mv, int n){

        if (libres<n) return false;
        //si hay igual o más libres, entonces reservamos los primeros
        int guardados=0;
        for (int i=0; i<cpus.length && guardados<n; i++){
            if (cpus[i]==null){
                cpus[i]=mv;
                guardados+=1;
            }
            
        }
        libres-=n;
        return true;
    }
    public synchronized void liberar(MV mv){
        int liberados=0;
        for (int i=0; i<cpus.length; i++){
            if (cpus[i]==mv) {
            cpus[i]=null;
            liberados++;
        }
        }
        libres+=liberados;
    }
    public synchronized int getLibres() {return libres;}
    public synchronized int getTotal() {return cpus.length;}
}
