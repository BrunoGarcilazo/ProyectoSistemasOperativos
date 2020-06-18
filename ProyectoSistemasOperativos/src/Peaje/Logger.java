package Peaje;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

public class Logger extends Thread {

    //private ArrayList<ArrayList<String>> datosCabinas = new ArrayList<ArrayList<String>>();

    private LinkedHashMap< Integer,LinkedList<String> > datosCabinas = new LinkedHashMap<Integer,LinkedList<String>>(); //<idCabina,datos>
    private LinkedHashMap<Integer,LinkedList<String>> datosCaminera = new LinkedHashMap<Integer,LinkedList<String>>();  //<ubicacion,datos>
    private LinkedHashMap<Integer,LinkedList<String>> datosClientes = new LinkedHashMap<Integer,LinkedList<String>>();   //<cedula,datos>
    private boolean cabinaLogger;   // Indica si este Logger es encargado de escribir datos sobre Cabinas
    private boolean camineraLogger; // IDEM con Caminera
	private boolean clientesLogger; // IDEM con Clientes 
    
    public Logger(boolean cabinaLogger,boolean camineraLogger,boolean clientesLogger){
        this.cabinaLogger = cabinaLogger;
        this.camineraLogger = camineraLogger;
		this.clientesLogger = clientesLogger;
        
        //Inicializa datosCabinas
        for (int i = 1; i <= 10; i++){
            Integer aux = i;
            this.datosCabinas.put(aux, new LinkedList<String>());            
        }
        //Inicializa datosCaminera
        for(int i = 0 ; i<2 ; i++){
            Integer aux = i;
            this.datosCaminera.put(aux,new LinkedList<String>());
        }
    }
    /**
     * Metodo que guardara la informacion con respecto a cada Cabina en un lugar especifico del Array,
     * con el proposito de crear los archivos de forma ordenada.
     */
    public void logCabina(Integer idCabina,String dato){
        this.datosCabinas.get(idCabina).addLast(dato);
    }

    /**
     * Metodo que guarda la informacion sobre infractores que la Caminera debe de saber.
     * sentido = true significa que el infractor paso el Peaje hacia Montevideo (lugar 1 del Array)
     * sentido = false signiica que el infractor paso el Peaje hacia el Este (lugar 0 del Array)
     */
    public void logCaminera(boolean sentido,String mensaje){
        if(sentido){
            this.datosCaminera.get(1).addLast(mensaje);
        }else{
            this.datosCaminera.get(0).addLast(mensaje);
        }
    }
    /**
     * Funcion que guardara informacion sobre un Vehiculo/Cliente que ha transitado por el Peaje.
     */
    public void logCliente(Integer ci,String datos){
        if(!this.datosClientes.containsKey(ci)){ // Si ese Cliente es nuevo en el Peaje
            this.datosClientes.put(ci, new LinkedList<String>());
        }else{  // Si ese Cliente ya ha pasado anteriormente por el Peaje
            this.datosClientes.get(ci).addLast(datos);
        }
    }

    
    @Override
    public void run()  {
	    try  {
	    Thread.sleep(30000); // Exporta los datos cada 20s
	    }  catch  (InterruptedException e){
	        e.printStackTrace();
        }


        System.out.println("Logger exporta los datos");

        // Envia los datos de cada Cabina en archivos por separado.
        if(cabinaLogger){
	        for (int i = 1; i <= 10; i++) {
                Integer key = i;
                String[] datosEscribir = new String[this.datosCabinas.get(key).size()];
                for (int j = 0; j < datosEscribir.length; j++) {
                    datosEscribir[j] = this.datosCabinas.get(key).get(j);
                }
		    ManejadorArchivosGenerico.escribirArchivo("src\\Peaje\\Cabina"+key.toString(), datosEscribir);
            }
        }
  
        //Escribe los avisos para la Caminera en archivos por separado para cada Caminera (Este y Oeste)
        if(camineraLogger){
            for(int i=1 ; i < 2 ; i++){
                Integer key = i;
                String[] datosEscribir = new String[this.datosCabinas.get(key).size()];
                for(int j = 0 ; j < datosEscribir.length ; j++){
                    datosEscribir[j] = this.datosCaminera.get(key).get(j);
                }
                if(key == 0){
                    ManejadorArchivosGenerico.escribirArchivo("src\\Peaje\\Caminera_Oeste", datosEscribir);
                }else{
                    ManejadorArchivosGenerico.escribirArchivo("src\\Peaje\\Caminera_Este" , datosEscribir);
                }        
            }
        }

        // Escribe los datos de cada Cliente en su propio archivo
        if(clientesLogger){
            Set<Integer> clavesClientes = this.datosClientes.keySet(); // Set con las Cedulas de los Clientes.
            for(Integer i : clavesClientes){
                LinkedList<String> datos = this.datosClientes.get(i);
                String[] datosEscribir = new String[datos.size()];
                for(int j=0;j<datos.size();j++){
                    datosEscribir[j] = datos.get(j);
                }
                ManejadorArchivosGenerico.escribirArchivo("src\\Peaje\\logsClientes\\cliente"+i.toString(),datosEscribir);
            }
        }
    }
}