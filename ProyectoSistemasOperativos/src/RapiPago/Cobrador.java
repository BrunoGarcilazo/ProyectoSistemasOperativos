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

public class Cobrador extends Thread {
    
    private Cabina cabina;

    public Cobrador(Cabina cabina){
        this.cabina = cabina;
    }

	public boolean cobrarACliente(Vehiculo cliente,int tarifa) {

		if (cliente.getInformacionPago().getSaldo() >= tarifa) {
			cliente.getInformacionPago().decrementarSaldo(tarifa);
			return true;
		} else {
			return false;
		}
	}

	public void multa(Vehiculo infractor){
        infractor.getInformacionPago().meMultan(); // Se le aplica multa de 2000 pesos.
    }
    
	@Override
    public void run(){
        
	}
}