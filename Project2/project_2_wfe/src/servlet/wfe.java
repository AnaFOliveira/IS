package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Vinyl;
import project_2_ejb.webserviceremote;


@WebServlet("/wfe")
public class wfe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public wfe() {
        
    }
    @EJB
	private webserviceremote web_interface;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> map=new HashMap<>();
		map.put("0", "Select an option");
		map.put("1", "Visualizar todos os vinyls");
		map.put("2", "Visualizar todos os títulos");
		map.put("3", "Visualizar todos os artistas");
		List<String> albumtitles=web_interface.titles();
		List<String> artistsnames=web_interface.artists();
		List<Vinyl> list_vinyl=web_interface.total_data();

		request.setAttribute("options", map);
		if (request.getParameter("option")!=null && request.getParameter("option").equals("1")) {
			request.setAttribute("vinyls", list_vinyl);
			request.getRequestDispatcher("/Display2.jsp").forward(request,response);
			return;
		}
		else if (request.getParameter("option")!=null && request.getParameter("option").equals("2")) {
			request.setAttribute("titulos", albumtitles);
			request.getRequestDispatcher("/Display.jsp").forward(request,response);
			
		}
		else if(request.getParameter("option")!=null && request.getParameter("option").equals("3")) {
			request.setAttribute("artistas", artistsnames);
			request.getRequestDispatcher("/Display.jsp").forward(request,response);
			
		}
		else {
			request.setAttribute("options", map);
			request.getRequestDispatcher("/Display.jsp").forward(request,response);
			
			
		}
		
		
		///request.getRequestDispatcher("/Display.jsp").forward(request, response);
		
		

	
	
	

		

			}


}
