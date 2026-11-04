import java.util.ArrayList;

public class ZonaEspera {
    private int umbral;
    private ArrayList<MV> cola;
    private long version =0;

    public ZonaEspera(int umbral){
        this.umbral=umbral;
        this.cola= new ArrayList<MV>();
    }
    public synchronized long getVersion(){
        return this.version;
    }
    public synchronized void entrarYEsperar(MV mv, long versionVista){
        if (!cola.contains(mv)) cola.add(mv);
        while (version==versionVista){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
    public synchronized void salir(MV mv){
        cola.remove(mv);
    }
    public synchronized void despertar(){
        version++;
        notifyAll();
    }
    public void esperarDebajoUmbral(){
        synchronized (this){
            if (cola.size()<umbral) return;
        }
        while (true){
            synchronized (this){
                if (cola.isEmpty()) return;
            }
            Thread.yield();
        }

    }
    public synchronized boolean estaVacia(){
        return cola.isEmpty();
    }
}
