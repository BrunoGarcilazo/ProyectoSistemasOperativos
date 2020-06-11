package Peaje;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Logger extends Thread {

    //private ArrayList<ArrayList<String>> datosCabinas = new ArrayList<ArrayList<String>>();

    private LinkedHashMap< Integer,LinkedList<String> > datosCabinas = new LinkedHashMap<Integer,LinkedList<String>>();


    public Logger(){

        for (int i = 1; i <= 10; i++){
            Integer aux = i;
            this.datosCabinas.put(aux, new LinkedList<String>());            
        }
    }

    public void logCabina(Integer idCabina,String dato){
        this.datosCabinas.get(idCabina).addLast(dato);
    }

    @Override
    public void run()  {
	try  {
	Thread.sleep(20000);
	}  catch  (InterruptedException e)  {
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

    }

}