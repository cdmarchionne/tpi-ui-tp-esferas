package domain.formulario;

import java.util.Date;

public class Persona {

	private String nombre;
	private String contrasenia;
	private Integer edad;
	private Integer telefono;
	private Date cumpleanios;
	private String direccion;
	private Integer codigoPostal;
	private String email;
	private String opinion;
	
	
	
	public Persona(String nombre, String contrasenia, Integer edad,
			Integer telefono, Date cumpleanios, String direccion,
			Integer codigoPostal, String email, String opinion) {
		
		super();
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.edad = edad;
		this.telefono = telefono;
		this.cumpleanios = cumpleanios;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.email = email;
		this.opinion = opinion;
		
		this.ImprimirDatos();	
	}

	
	
	public void ImprimirDatos(){
		System.out.println(nombre + " " + contrasenia + " "+ edad + " " + telefono.toString() + " " + cumpleanios.toString() + " " +  direccion+ " " + codigoPostal.toString() + " "+ email + " "+ opinion );
	}
	


	public String getNombre() {
		return nombre;
	}



	public String getContrasenia() {
		return contrasenia;
	}



	public Integer getEdad() {
		return edad;
	}



	public Integer getTelefono() {
		return telefono;
	}



	public Date getCumpleanios() {
		return cumpleanios;
	}



	public String getDireccion() {
		return direccion;
	}



	public Integer getCodigoPostal() {
		return codigoPostal;
	}



	public String getEmail() {
		return email;
	}



	public String getOpinion() {
		return opinion;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}





	public void setEdad(Integer edad) {
		this.edad = edad;
	}





	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}





	public void setCumpleanios(Date cumpleanios) {
		this.cumpleanios = cumpleanios;
	}





	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}





	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}


	
	

}
