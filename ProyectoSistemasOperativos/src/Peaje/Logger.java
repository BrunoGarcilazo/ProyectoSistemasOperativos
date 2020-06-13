package Peaje;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Logger extends Thread {

    //private ArrayList<ArrayList<String>> datosCabinas = new ArrayList<ArrayList<String>>();

    private LinkedHashMap< Integer,LinkedList<String> > datosCabinas = new LinkedHashMap<Integer,LinkedList<String>>();
    private LinkedHashMap<Integer,LinkedList<String>> datosCaminera = new LinkedHashMap<Integer,LinkedList<String>>();

    public Logger(){
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

    
    @Override
    public void run()  {
	    try  {
	    Thread.sleep(20000); // Exporta los datos cada 20s
	    }  catch  (InterruptedException e){
	        e.printStackTrace();
        }

        System.out.println("Logger exporta los datos");
	    for (int i = 1; i <= 10; i++) {
            Integer key = i;
            String[] datosEscribir = new String[this.datosCabinas.get(key).size()];
            for (int j = 0; j < datosEscribir.length; j++) {
                datosEscribir[j] = this.datosCabinas.get(key).get(j);
            }
		ManejadorArchivosGenerico.escribirArchivo("src\\Peaje\\Cabina"+key.toString(), datosEscribir);
        }
  
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
}