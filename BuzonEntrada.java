import java.util.ArrayList;

public class BuzonEntrada {
    private int max;
    private ArrayList<Solicitud> agregados;
    private int uActivos;
    public BuzonEntrada(int max, int uActivos){
        this.max=max;
        this.agregados= new ArrayList<Solicitud>();
        this.uActivos=uActivos;
    }

    public boolean estaLleno(){
        if (agregados.size()==max) return true;
        return false;
    }
    public synchronized void agregar(Solicitud solicitud){
        while(this.estaLleno()) {
            try {wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
        }
        
            agregados.add(solicitud);
            notifyAll();
    }
    public Solicitud extraer(){ //tiene que ser semi activo
        while(true){
            synchronized(this){
                if (!agregados.isEmpty()){
                    Solicitud s= agregados.remove(0);
                    notifyAll();
                    return s;
                }
                if (uActivos==0) return null;
            }
        Thread.yield();
        }
    }
    public synchronized boolean hayUsuariosActivos(){
        return uActivos>0;
    }
    public synchronized void reportarTerminacion(){
        uActivos--;
        notifyAll();
    }
    public synchronized boolean estaVacio(){
        return agregados.isEmpty();
    }
}
