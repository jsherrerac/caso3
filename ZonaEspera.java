import java.util.ArrayList;

public class ZonaEspera {
    private int umbral;
    private ArrayList<MV> cola;

    public ZonaEspera(int umbral){
        this.umbral=umbral;
        this.cola= new ArrayList<MV>();
    }
    public synchronized void entrarYEsperar(MV mv){
        cola.add(mv);
        notifyAll();
        try {
        wait();   
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cola.remove(mv);
        notifyAll(); //para avisar que el size bajo, el anterior es para avisar que subió


    }
    public synchronized void despertar(){
        notifyAll();
    }
    public void esperarDebajoUmbral(){
        while(true){ 
            synchronized (this){
                if(cola.size() <umbral) return;
            }
        Thread.yield();
        }
    }

}
