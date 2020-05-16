package RapiPago;

/**
 * RapiPago es la "Aplicacion" que utiliza el Peaje
 * para cobrarle al cliente que este transitando.
 * Cliente es quien debera de pagarle a Cobrador
 * durante su paso por el Peaje.
 */

public class Cliente {

    private int numeroTelefono; /** Numero de contacto del Cliente */
    private int saldo; /** Saldo restante del Cliente */
    private String matricula;  /** Matricula del vehiculo. Sera util para verificar que el sticker en el vehiculo se correspondan. */            
    private int ci;   /** C.I del Cliente */
    private int multas; //Monto de las multas

    public Cliente(int telefono,int saldo,String matricula,int ci){
        this.numeroTelefono = telefono;
        this.saldo = saldo;
        this.matricula = matricula;
        this.ci = ci;
        this.multas = 0;
    }   

    public int getTelefono(){
        return this.numeroTelefono;
    }
    public int getSaldo(){
        return this.saldo;
    }
    public String getMatricula(){
        return this.matricula;
    }
    public int getCI(){
        return this.ci;
    }

	public void decrementarSaldo(int tarifa) {
		this.saldo = this.saldo - tarifa;
    }
    
    public void meMultan(){
        this.multas += 2000;
    }
}