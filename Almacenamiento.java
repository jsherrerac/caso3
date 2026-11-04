public class Almacenamiento{
    private MV[] disco;
    private int libres;
    private int gib;
    private int tamanioBloque;
    public Almacenamiento(int capacidad,int tamanioBloque){
        this.tamanioBloque=tamanioBloque;
        int bloquesTotales= aBloques(capacidad);
        this.disco= new MV[bloquesTotales];
        this.libres= bloquesTotales;
    }   
    public synchronized boolean reservar(MV mv, int gibSolicitados){
        int bNecesarios= aBloques(gibSolicitados);
        if (libres< bNecesarios) return false;
        int guardados=0;
        for (int i=0; i<disco.length && guardados<bNecesarios; i++){
            if (disco[i]==null){
                disco[i]=mv;
                guardados+=1;
            }
            
        }
        libres -= bNecesarios;
        return true;
    }
    public synchronized void liberar(MV mv){
        int liberadas=0;
        for (int i=0; i<disco.length; i++){
            if (disco[i]==mv) {disco[i]=null;
            liberadas++;}
        }
        libres+=liberadas;
    }
    private int aBloques(int gib){
        long kib=(long) gib*1024*1024;
        return (int) Math.round((double) kib/tamanioBloque);
    }
    public synchronized int getLibres(){return libres;}
    public synchronized int getTotal(){return disco.length;}

}