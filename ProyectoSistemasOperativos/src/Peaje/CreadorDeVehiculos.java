package Peaje;

public class CreadorDeVehiculos extends Thread {
    
    Peaje pando;
    private Integer numero = 0;
    

    private String matriculaCamiones = "ATP";
    private Integer cantCamiones = 0;

    private String matriculaBus = "STU";
    private Integer cantBus = 0;

    private String matriculaFurgon = "STM";
    private Integer cantFurgon = 0;

    private String matriculaAuto = "SBN";
    private Integer cantAuto = 0;

    private String matriculaAmbulancia = "AAM";
    private Integer cantAmbulancia = 0;

    private String matriculaPolicia = "AMI";
    private Integer cantPolicia = 0;

    private String matriculaBomberos = "ACB"; // Bomberos y Policias coinciden en la terminacion de la matricula, por eso CB = "Cuerpo de Bomberos" para diferenciar.
	private Integer cantBomberos = 0;
	public CreadorDeVehiculos(Peaje peaje) {
        this.pando = peaje; 
	}

	// Caso 1:
	// - Todos los vehiculos menos prioritarios en gran cantidad en ambos sentidos

	public void caso1(){
        for(int i=1; i <= 10; i++){           
            Vehiculo automovil  = new Vehiculo(obtenerMatriculaAuto(), 2 ,"Ford Fiesta" , "Blanco" , true,pando,99000000+i,500,getCedulaUnica());
            Vehiculo furgon  = new Vehiculo(obtenerMatriculaFurgon(),3,"Hyundai H100" , "Rojo" , true,pando,94000000+i,600,getCedulaUnica());
           
            automovil.start();
            furgon.start();
            
        }
        for(int i=11; i <= 21; i++){          
            Vehiculo automovil  = new Vehiculo(obtenerMatriculaAuto(), 2 ,"Ford Fiesta" , "Blanco" , false,pando,99000000+i,500, getCedulaUnica());
            Vehiculo furgon  = new Vehiculo(obtenerMatriculaFurgon(), 3 ,"Hyundai H100" , "Rojo" , false,pando,94000000+i,600, getCedulaUnica());
            
            automovil.start();
            furgon.start();
            
        }
    }
    
	// Caso 2: Cerrar las cabinas.
	// - Todos los vehiculos menos prioritarios poca cantidad en ambos sentidos
    
    public void caso2(){
		for(int i=24; i <= 27; i++){           
            Vehiculo automovil  = new Vehiculo(obtenerMatriculaAuto(),2 ,"Ford Fiesta" , "Blanco" , true,pando,99000000+i,500,getCedulaUnica());
            Vehiculo furgon  = new Vehiculo(obtenerMatriculaFurgon(),3,"Hyundai H100" , "Rojo" , true,pando,94000000+i,600,getCedulaUnica());
            
            automovil.start();
            furgon.start();
            
        }
		for(int i=28; i <= 31; i++){           
            Vehiculo automovil  = new Vehiculo(obtenerMatriculaAuto(), 2 ,"Ford Fiesta" , "Blanco" , false,pando,99000000+i,500,getCedulaUnica());
            Vehiculo furgon  = new Vehiculo(obtenerMatriculaFurgon(), 3 ,"Hyundai H100" , "Rojo" , false,pando,94000000+i,600,getCedulaUnica());
            
            automovil.start();
            furgon.start();
            
        }
    }

    // Caso 3:
	// - Todos los tipos de vehiculos en gran cantidad, solo hacia el Este
	public void caso3(){
        for(int i=32; i <= 47; i++){           
            Vehiculo automovil  = new Vehiculo(obtenerMatriculaAuto(), 2 ,"Ford Fiesta" , "Blanco" , false,pando,99000000+i,500,getCedulaUnica());
            Vehiculo furgon  = new Vehiculo(obtenerMatriculaFurgon(), 3 ,"Hyundai H100" , "Rojo" , false,pando,94000000+i,600,getCedulaUnica());
            Vehiculo camion  = new Vehiculo(obtenerMatriculaCamion(), 5 ,"Scania R620" , "Azul" , false,pando,96000000+i,1000,getCedulaUnica());
			Vehiculo omnibus = new Vehiculo(obtenerMatriculaBus(), 4, "BYD Electrico", "Negro", false, pando,95000000 + i, 700, getCedulaUnica());
            
            automovil.start();
            furgon.start();
            camion.start();
            omnibus.start();
        }
		Vehiculo ambulancia = new Vehiculo(obtenerMatriculaAmbulancia(), 1, "Suat", "Blanco", false, pando, 949999999, 0, getCedulaUnica());
		Vehiculo bombero = new Vehiculo(obtenerMatriculaBombero(), 1, "Bombero", "Rojo", false, pando, 1099999, 0, getCedulaUnica());
        Vehiculo policia = new Vehiculo(obtenerMatriculaPolicia(), 1, "Policia", "Azul", false, pando,3099999, 0, getCedulaUnica());
        ambulancia.start();
        bombero.start();
        policia.start();
	}
   
	// Caso 4:
	// - Pocos autos, ambos sentidos, alguno sin saldo.
	public void caso4() {      
       Vehiculo automovil  = new Vehiculo(obtenerMatriculaAuto(), 2 ,"Corsa" , "Violeta" , false,pando,9010,100,getCedulaUnica());
       Vehiculo automovil1  = new Vehiculo(obtenerMatriculaAuto(), 2 ,"Audi" , "Gris" , true,pando,9019,0,getCedulaUnica());
       Vehiculo automovil2  = new Vehiculo(obtenerMatriculaAuto(), 2 ,"BMW" , "Negro" , false,pando,9020,0,getCedulaUnica());
            
       automovil.start();
	   automovil1.start();
       automovil2.start();
            
        }
	

    // Caso 5:
	// - Vehiculos no prioritarios, alguno con información irregular.
    public void caso5() {
       Vehiculo automovil  = new Vehiculo("", 2 ,"Peugeot" , "Violeta" , false,pando,9010,2000,getCedulaUnica());
       Vehiculo automovil1  = new Vehiculo("", 2 ,"Citroen" , "Naranja" , true,pando,9019,9000,getCedulaUnica());
	   Vehiculo camion1  = new Vehiculo("", 5 ,"Renault" , "Negro" , true,pando,9090,2000,8);
            
       automovil.start();
	   automovil1.start();
       camion1.start();
            
        }
	
    // Caso 6 :
	// - Envio vehiculos prioritarios luego de un tiempo.
	public void caso6() {
		Vehiculo ambulancia = new Vehiculo(obtenerMatriculaAmbulancia(), 1, "Semm", "Blanco", false, pando, 1049999999, 0, getCedulaUnica());
		Vehiculo bombero = new Vehiculo(obtenerMatriculaBombero(), 1, "Bombero", "Rojo", true, pando, 10799999, 0,getCedulaUnica());
		Vehiculo bombero1 = new Vehiculo(obtenerMatriculaBombero(),1,"Bombero", "Rojo", false, pando, 107999980, 0,getCedulaUnica());
		Vehiculo policia = new Vehiculo(obtenerMatriculaPolicia(), 1, "Policia", "Azul", true, pando, 10399999, 0, getCedulaUnica());
		ambulancia.start();
		bombero.start();
		bombero1.start();
		policia.start();
	}
	
	//Caso 7:
    // - Envio camiones y omnibus
    public void caso7() {
        
        for(int i=50; i <= 52; i++){        
            Vehiculo camion  = new Vehiculo(obtenerMatriculaCamion(), 5 ,"Scania R620" , "Azul" , false,pando,96000000+i,1000,getCedulaUnica());
			Vehiculo omnibus = new Vehiculo(obtenerMatriculaBus(), 4, "BYD Electrico", "Negro", false, pando,95000000 + i, 700, getCedulaUnica());
            camion.start();
		    omnibus.start();

            }

        for(int i=53; i <= 55; i++){
            Vehiculo camion  = new Vehiculo(obtenerMatriculaCamion(), 5 ,"Scania R620" , "Azul" , true,pando,96000000+i,1000,getCedulaUnica());
			Vehiculo omnibus = new Vehiculo(obtenerMatriculaBus(), 4, "BYD Electrico", "Negro", true, pando,95000000 + i, 700, getCedulaUnica());
            camion.start();
		    omnibus.start();

           }

		
    }
    
    //Caso8:
    // - Un Vehiculo solo. Testeo de funciones basicas (logs por ej)
    public void caso8(){
        Vehiculo automovil  = new Vehiculo(obtenerMatriculaAuto(),2,"Tata" , "Indigo" , false,pando,99096096,2000,49042045);
        automovil.start();
    }

    public String obtenerMatriculaAuto(){
        this.cantAuto = this.cantAuto + 1;
        Integer id = this.cantAuto;
        String matricula = this.matriculaAuto + id.toString();
        return matricula;
    }

    public String obtenerMatriculaCamion(){
        this.cantCamiones = this.cantCamiones + 1;
        Integer id = this.cantCamiones;
        String matricula = this.matriculaCamiones + id.toString();
        return matricula;
    }

    public String obtenerMatriculaBus(){
        this.cantBus = this.cantBus + 1;
        Integer id = this.cantBus;
        String matricula = this.matriculaBus + id.toString();
        return matricula;
    }

    public String obtenerMatriculaFurgon(){
        this.cantFurgon = this.cantFurgon + 1;
        Integer id = this.cantFurgon;
        String matricula = this.matriculaFurgon+ id.toString();
        return matricula;
    }

    public String obtenerMatriculaAmbulancia(){
        this.cantAmbulancia = this.cantAmbulancia + 1;
        Integer id = this.cantAmbulancia;
        String matricula = this.matriculaAmbulancia + id.toString();
        return matricula;
    }
    public String obtenerMatriculaBombero(){
        this.cantBomberos = cantBomberos + 1;
        Integer id = this.cantBomberos;
        String matricula = this.matriculaBomberos + id.toString();
        return matricula;
    }

    public String obtenerMatriculaPolicia(){
        this.cantPolicia = this.cantPolicia + 1;
        Integer id = this.cantPolicia;
        String matricula = this.matriculaPolicia + id.toString();
        return matricula;
    }

    public Integer getCedulaUnica(){
        this.numero = this.numero + 1;
        return numero;
    }
    @Override
	public void run() {
    // inicia el peaje uniformemente.
        this.pando.start();
        caso3();
     
			try{
			   Thread.sleep(10000);
			} catch (InterruptedException e) {
			e.printStackTrace();
            }

        
        
    }
}      
