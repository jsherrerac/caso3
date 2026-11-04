import java.util.concurrent.ThreadLocalRandom;

public class MV extends Thread{
    private Solicitud solicitud;
    private Procesador procesador;
    private Memoria memoria;
    private Almacenamiento almacenamiento;
    private ZonaEspera zona;
    public MV(Solicitud solicitud, Procesador procesador, Memoria memoria, Almacenamiento almacenamiento,
            ZonaEspera zona) {
        this.solicitud = solicitud;
        this.procesador = procesador;
        this.memoria = memoria;
        this.almacenamiento = almacenamiento;
        this.zona = zona;
    }

    public void run(){
        while(true){
            long versionVista = zona.getVersion();
            if (reservarTodo()) break;
            zona.entrarYEsperar(this, versionVista);
        }
        zona.salir(this);

        try{
            Thread.sleep(ThreadLocalRandom.current().nextInt(4000, 40001));
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        procesador.liberar(this);
        memoria.liberar(this);
        almacenamiento.liberar(this);
        zona.despertar();

    }
    private boolean reservarTodo(){
        if (!procesador.reservar(this, solicitud.getNumProcesadores())){
            return false;
        }
        if (!memoria.reservar(this, solicitud.getMemoria())){
            procesador.liberar(this);
            return false;
        }
        if (!almacenamiento.reservar(this, solicitud.getEspacio())){
            procesador.liberar(this);
            memoria.liberar(this);
            return false;
        }
        //en este punto, obviamente puede reservar todo
        return true;
    }
}