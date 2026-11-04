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
    public synchronized Solicitud extraer(){
        while(agregados.size()==0 && uActivos>0){
            try{wait();

            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        if (agregados.isEmpty()) return null;   
        Solicitud s=agregados.remove(0); 
        notifyAll();
        return s;
        

    }
    public synchronized boolean hayUsuariosActivos(){
        return uActivos>0;
    }
    public synchronized void reportarTerminacion(){
        uActivos--;
        notifyAll();
    }
}
