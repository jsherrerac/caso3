public class Almacenamiento{
    private MV[] disco;
    private int libres;
    private int gib;
    private int tamanioBloque;
    public Almacenamiento(int tamanioBloque, int capacidad){
        this.tamanioBloque=tamanioBloque;
        int bloquesTotales= aBloques(capacidad);
        this.disco= new MV[bloquesTotales];
        this.libres= bloquesTotales;
    }   
    private int aBloques(int gib){
        long kib=(long) gib*1024*1024;
        return (int) Math.round((double) kib/tamanioBloque);
    }
    public synchronized int getLibres(){return libres;}
    public synchronized int getTotal(){return disco.length;}

}