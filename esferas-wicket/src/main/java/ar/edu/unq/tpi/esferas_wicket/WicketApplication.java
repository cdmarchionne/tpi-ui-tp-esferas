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

	public WicketApplication() {
	}
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage(){
		return CrearMapaPage.class;
	}
	
	protected void init(){
		mount(new BufferedResponseMapper() {
			protected String getSessionId(){
				return Session.get().getId();
			}
		}); 
	}
	
}
