public class Configuracion {
   private int nu;
   private int base;
   private int ng;
   private int tam1; //buzon
   private int tam2; //umbral
   private int procesadores;
   private int memoria;
   private int pagina; //en kiB
   private int almacenamiento;
   private int bloque; //bloque en kiB
   public Configuracion(int nu, int base, int ng, int tam1, int tam2, int procesadores, int memoria, int pagina,
        int almacenamiento, int bloque) {
    this.nu = nu;
    this.base = base;
    this.ng = ng;
    this.tam1 = tam1;
    this.tam2 = tam2;
    this.procesadores = procesadores;
    this.memoria = memoria;
    this.pagina = pagina;
    this.almacenamiento = almacenamiento;
    this.bloque = bloque;
   }
   public int getNu() {
    return nu;
   }
   public int getBase() {
    return base;
   }
   public int getNg() {
    return ng;
   }
   public int getTam1() {
    return tam1;
   }
   public int getTam2() {
    return tam2;
   }
   public int getProcesadores() {
    return procesadores;
   }
   public int getMemoria() {
    return memoria;
   }
   public int getPagina() {
    return pagina;
   }
   public int getAlmacenamiento() {
    return almacenamiento;
   }
   public int getBloque() {
    return bloque;
   }
}
