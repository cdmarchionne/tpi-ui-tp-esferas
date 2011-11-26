package domain.formulario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaPersonas {
	
		private static  ListaPersonas instance = null;
		private static List<Persona> lista = new ArrayList<Persona>(); 
		
		
	public ListaPersonas(){
		
	}		

	
	public static void createInstance(){
		if(instance==null){
			instance= new ListaPersonas();
		}
		
	}
	
	public static ListaPersonas getInstance(){
		if(instance == null) {
			createInstance();
			crearDatos();}
		return instance;
	}
	
	
	public static void crearDatos(){
		Persona p1=new Persona("Mariano", "123456", 25,
				1564974149, new Date(15/10/1986), "Yapeyu 4037",
				1884,"prietomarianomartin@gmail.com", "ZK LA ROMPE");
	
	
		Persona p2=new Persona("Diego", "332456", 13,
				1534567678, new Date(23/11/1998), "Africa 355",
				2234,"dieguito89@hotmail.com", "Sin opinion?");
		
		Persona p3=new Persona("Florencia", "14242350", 51,
				154456767, new Date(05/3/1960), "Pampa y la Via",
				1671,"florcita@hotmail.com", "No tengo IDEA DE NADA");
		
		Persona p4=new Persona("Marcos", "24356905", 33,
				1544567557, new Date(03/06/1973), "Matienzo 45",
				3244,"marquito.rockero@live.com.ar", "Puede ser que sirva");
		
	lista.add(p1);
	lista.add(p2);
	lista.add(p3);
	lista.add(p4);
	}
		
	public static List<Persona> getListaPersonas(){
		return lista;
		
	}	
	
	public void setListaPersonas(List<Persona> lista){
		this.lista= lista;
	}




	










}

