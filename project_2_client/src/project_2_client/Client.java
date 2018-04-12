package project_2_client;
import artifact.Projeto2WsService;
import artifact.Projeto2Ws;
import artifact.Vinyl;

import java.util.List;
import java.util.Scanner;

import artifact.Music;
import artifactnew.Project2Webnew;
import artifactnew.Project2WebnewService;
public class Client {
	public static void main(String[] args) {
		Projeto2WsService p2ws=new Projeto2WsService();
		Projeto2Ws p2w=p2ws.getProjeto2WsPort();
		Project2WebnewService p2wns=new Project2WebnewService();
		Project2Webnew p2wn=p2wns.getProject2WebnewPort();
		String menu_option;
		boolean keep_cycle=true;
		Scanner sc=new Scanner(System.in);
		do {
			System.out.println("Menu:");
			System.out.println("1-Visualizar toda a informação");
			System.out.println("2-Visualizar todos os títulos dos albuns");
			System.out.println("3-Pesquisar por título do album");
			System.out.println("4-Visulizar todos os artístas ");
			System.out.println("5-Pesquisar albuns por score");
			System.out.println("6-Pesquisar por genero");
			System.out.println("7-Pesquisar por record label");
			System.out.println("8-Pesquisar por nome da musica->titulos");
			System.out.println("9-Pesquisar por genero e score");
			System.out.println("10-Pesquisar por nome da musica->artistas");
			System.out.println("11-Pesquisar por record label e genero");
			menu_option=sc.next();
			if (menu_option.equals("1")) {
				List<Vinyl> list_vinyl=p2w.totalData();
				System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n","Género","ASIN","Titulo","Artista","Ano","Editora","Reviews","Score","Preço");
				System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n",list_vinyl.get(0).getGenre().get(0),list_vinyl.get(0).getId(),
						list_vinyl.get(0).getTitle(),list_vinyl.get(0).getArtist().get(0),list_vinyl.get(0).getYear(),list_vinyl.get(0).getLabel(),
						list_vinyl.get(0).getNreview(),list_vinyl.get(0).getRating(),list_vinyl.get(0).getPrice());
				
			}
			
		} while (keep_cycle);
		
		
		
	}
	
	
	

}
