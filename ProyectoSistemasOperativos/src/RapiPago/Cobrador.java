package RapiPago;
import Peaje.*;

/**
 * RapiPago es la "Aplicacion" que utiliza el Peaje
 * para cobrarle al cliente que este transitando.
 * Cobrador es el encargado de realizar la transaccion y
 * notificar al Peaje si la transaccion resulto exitosa o no.
 * Para que luego, el Peaje haga las acciones correspondientes,
 * (dejar transitar o sacar foto y notificar a las autoridades, por ejemplo).
 */

public class Cobrador {
	
	Logger logger = new Logger(false,true,true);
/**
 * Se le cobrara al Cliente una tarifa segun su tipo de Vehiculo.
 * Adicionalmente, verifica que los datos sean "legitimos" y avisa a las autoridades sobre irregularidades
 * @return si el pago fue exitoso
 */
public boolean cobrarACliente(Vehiculo cliente,int tarifa) {
	boolean cobrado = false;

	if (cliente.getMatricula().compareTo(cliente.getInformacionPago().getMatricula()) != 0  || cliente.getMatricula() == null) {
		this.multa(cliente, 2000);
		logger.logCliente(cliente.getInformacionPago().getCI(),"Tu vehiculo paso por el Peaje con informacion irregular");
		if(cliente.getSentido()){ // Se dirige hacia Montevideo. Corresponde Caminera Oeste.
			if(cliente.getMatricula() == null){ // Si no tiene matricula, informo solo el Modelo y Color
				logger.logCaminera(true,"ALERTA: "+cliente.getModelo()+" / Color: "+cliente.getColor()+ " irregular se dirige hacia su direccion");
			}else{ // Si tiene matricula, informo tambien la matricula.
				logger.logCaminera(true,"ALERTA: "+cliente.getModelo()+" / Color: "+cliente.getColor()+" Matricula:"+cliente.getMatricula()+
					" irregular se dirige hacia su direccion");
			}						
		}else{ // Se dirige hasta el Este
			if(cliente.getMatricula() == null){ // Si no tiene matricula, solo informo el Modelo y Color
				logger.logCaminera(false,"ALERTA: "+cliente.getModelo()+" / Color: "+cliente.getColor()+ " irregular se dirige hacia su direccion");
			}else{ // Si tiene matircula, informo tambien la matricula.
				logger.logCaminera(false,"ALERTA: "+cliente.getModelo()+" / Color: "+cliente.getColor()+" Matricula:"+cliente.getMatricula()+
					" irregular se dirige hacia su direccion");
			}
		}			
	}
    else{
		if (cliente.getInformacionPago().getSaldo() >= tarifa) {
			cliente.getInformacionPago().decrementarSaldo(tarifa);// CASO NORMAL
			cobrado = true;
			// logger de vehiculo : "Peaje Pando: Tarifa X , Vehiculo Matricula X"
		}
        else{
			this.multa(cliente, tarifa + 250);
			// logger me multaron: X plata.
		}
    }
     
      return cobrado;
	}

	public void multa(Vehiculo infractor, int multa){
        infractor.getInformacionPago().setMulta(multa); // Se le aplica multa de 2000 pesos.
    }
			

   
	
}