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
    
public boolean cobrarACliente(Vehiculo cliente,int tarifa) {
	boolean cobrado = false;

	if (cliente.getMatricula().compareTo(cliente.getInformacionPago().getMatricula()) != 0  || cliente.getMatricula() == null) {
		this.multa(cliente, 2000);
		//logger manda que no tiene matricula 
		// AUTO ROBADO
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