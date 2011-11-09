package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.BufferedResponseMapper;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see ar.edu.unq.tpi.esferas_wicket.Start#main(String[])
 */
public class WicketApplication extends WebApplication{    	

	
	public WicketApplication() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<CrearMapaPage> getHomePage(){
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
