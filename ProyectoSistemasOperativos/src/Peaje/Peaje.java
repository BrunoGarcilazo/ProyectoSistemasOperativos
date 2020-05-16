package Peaje;


import java.util.LinkedHashMap;

public class Peaje extends Thread {
    
	private LinkedHashMap<Integer, Cabina> cabinas = new LinkedHashMap<Integer, Cabina>(); // Pares, orden de insercion
	private LinkedHashMap<Integer, Carril> carriles = new LinkedHashMap<Integer, Carril>();

    private LinkedHashMap<Integer, Carril> getCarriles(){
        return this.carriles;
    }

    private LinkedHashMap<Integer, Cabina> getCabinas(){
        return this.cabinas;
    }
   







}