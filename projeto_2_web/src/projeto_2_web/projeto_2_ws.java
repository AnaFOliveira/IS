package projeto_2_web;

import java.math.BigDecimal;
import classes.Vinyl;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;


import project_2_ejb.webserviceremote;

@WebService
public class projeto_2_ws {

	@EJB
	private webserviceremote web_interface;

	@WebMethod
	public List<Vinyl> total_data()	{
		return web_interface.total_data();
	}

	@WebMethod
	public List<String> titles()
	{
		return web_interface.titles();
	}

	@WebMethod
	public List<Vinyl> album_getinfo(String title)
	{
		return web_interface.album_getinfo(title);
	}

	@WebMethod
	public List<String> artists()
	{
		return web_interface.artists();
	}

	@WebMethod
	public List<Vinyl> scoresabovex(BigDecimal x)
	{
		return web_interface.scoresabovex(x);
	}

	@WebMethod
	public List<Vinyl> albumBygenre(List<String> genre){
		return web_interface.albumBygenre(genre);
	}

}
