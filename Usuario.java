import java.util.concurrent.ThreadLocalRandom;
public class Usuario extends Thread{
    private int solicitudes;
    private int Id;
    private BuzonEntrada buzon;
    private Configuracion config;
    public Usuario(int solicitudes, int Id, BuzonEntrada buzon, Configuracion config){
        this.solicitudes=solicitudes;
        this.buzon=buzon;
        this.Id=Id;
        this.config=config;
    }
    public void run(){
        
        for (int i=1; i<=solicitudes;i++){
            
            String idSolicitud= this.Id+ "-"+i;
            Solicitud solicitud = new Solicitud(idSolicitud,
                generarProcesadores(),
                generarMemoria(), 
                generarEspacio());
                buzon.agregar(solicitud);

        } 
        buzon.reportarTerminacion();

    }
    public int generarProcesadores(){
        int topeProc = (int) Math.round(0.75 * config.getProcesadores());
        int numProcesadores= ThreadLocalRandom.current().nextInt(1, topeProc+1);
        return numProcesadores;
        }
    public int generarMemoria(){
        int topeMemoria= (int) Math.round(0.75*config.getMemoria());
        int memoria= ThreadLocalRandom.current().nextInt(1, topeMemoria+1);
        if (memoria==1) return 1;
        int exponente =(int) Math.floor(Math.log(memoria)/Math.log(2) + 1e-9); //1e-9 es para que nunca de 2.9999 sino 3, por ejemplo
        return 1<<exponente; //esto al parecer corre bits (multiplica por 2), si exponente es 3, se mueve 3 a la izquierda que es 2^3=8
    }
    public int generarEspacio(){
        int topeAlm= (int) Math.round(0.75* config.getAlmacenamiento());
        int Alm= ThreadLocalRandom.current().nextInt(1, topeAlm +1);
        return 2*((Alm-1)/2);
    }


}