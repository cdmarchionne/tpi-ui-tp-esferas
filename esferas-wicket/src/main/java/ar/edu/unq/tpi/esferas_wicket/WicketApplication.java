package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.BufferedResponseMapper;

import dominio.Mapa;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see ar.edu.unq.tpi.esferas_wicket.Start#main(String[])
 */
public class WicketApplication extends WebApplication{    	

	private Mapa mapa = null;
	
	public WicketApplication() {
	}
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage(){
		if(existeMapa()){
			return MapaPage.class;
		}
		else{
			return CrearMapaPage.class;
		}
	}
	
	/**
	 * Devuelvo un booleano que representa si ya existe el mapa
	 * @return
	 */
	private boolean existeMapa() {
		return (this.getMapa()!=null);
	}
	
	protected void init(){
		mount(new BufferedResponseMapper() {
			protected String getSessionId(){
				return Session.get().getId();
			}
		}); 
	}
	
	public Mapa getMapa() {
		return mapa;
	}
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

}
