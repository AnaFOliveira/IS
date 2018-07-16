package project_2_ejb;

import java.math.BigDecimal;
import classes.Vinyl;
import java.util.List;

public interface webserviceremote {

	public List<Vinyl> total_data();
	
	public List<String> titles();
	
	public List<Vinyl> album_getinfo(String title);
	
	public List<String> artists();
	
	public List<Vinyl> scoresabovex(BigDecimal x);
	
	public List<Vinyl> albumBygenre(List<String> genre);
	

}
// necessário fazer um deployment - export em ear 