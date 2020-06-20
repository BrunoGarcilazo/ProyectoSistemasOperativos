package Peaje;


public class Proyecto {

    public static void main(String[] args) throws Exception {
	
        Peaje pando = new Peaje("Ruta Interbalnearia KM.33");
        CreadorDeVehiculos creador = new CreadorDeVehiculos(pando);
        creador.start();
}
}
