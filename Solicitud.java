public class Solicitud {
    private String id;
    private int numProcesadores;
    private int memoria; //en GiB
    private int espacio; //en GiB
    public Solicitud(String id, int numProcesadores, int memoria, int espacio) {
        this.id = id;
        this.numProcesadores = numProcesadores;
        this.memoria = memoria;
        this.espacio = espacio;
    }
    public String getId() {
        return id;
    }
    public int getNumProcesadores() {
        return numProcesadores;
    }
    public int getMemoria() {
        return memoria;
    }
    public int getEspacio() {
        return espacio;
    }  //getters y constructor autogenerados por VsCode (con sourceAction)
}
