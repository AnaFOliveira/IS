package project_2_client;
import artifact.Projeto2WsService;
import artifact.Projeto2Ws;
import artifact.Vinyl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import artifact.Music;
import artifactnew.Project2Webnew;
import artifactnew.Project2WebnewService;
public class Client {
	
	
	public static void main(String[] args) {
		Client_Functions functions=new Client_Functions();
		Projeto2WsService p2ws=new Projeto2WsService();
		Projeto2Ws p2w=p2ws.getProjeto2WsPort();
		Project2WebnewService p2wns=new Project2WebnewService();
		Project2Webnew p2wn=p2wns.getProject2WebnewPort();
		String menu_option;
		String menu;
		boolean keep_cycle=true;
		Scanner sc=new Scanner(System.in);
		do {
			System.out.println("1-Pesquisa");
			System.out.println("2-Visualizar todos os albuns");
			System.out.println("3-Visualizar todos os títulos");
			System.out.println("4-Visulizar todos os artístas");
			System.out.println("5-Sair");
			menu=sc.nextLine();
			
			if (menu.equals("2")) {
				List<Vinyl> list_vinyl=p2w.totalData();
				
				functions.Print_Info_Vinyl(list_vinyl);
			}
			
			if (menu.equals("3")) {
				List<String> list_titles=p2w.titles();
				System.out.println("Lista de Títulos");
				for (int i = 0; i < list_titles.size(); i++) {
					System.out.println(list_titles.get(i));
				}
				
			}
			
			if (menu.equals("4")) {
				List<String> list_artists=p2w.artists();
				System.out.println("Lista Artistas");
				for (int i = 0; i < list_artists.size(); i++) {
					System.out.println(list_artists.get(i));
					
				}
			}
			
			if (menu.equals("1")) {
				System.out.println("1-Pesquisar por título");
				System.out.println("2-Pesquisar por rating(>)");
				System.out.println("3-Pesquisar por género");
				System.out.println("4-Pesquisar por editora");
				System.out.println("5-Pesquisar por nome da musica, com visualização dos títulos");
				System.out.println("6-Pesquisar por género e rating(>)");
				System.out.println("7-Pesquisar por nome da musica, com visualização dos artistas");
				System.out.println("8-Pesquisar por editora e género, com visualização do número de albuns");
				System.out.println("9-Voltar para o Menu");
				menu_option=sc.nextLine();

				
				if (menu_option.equals("1")) {
					String titulo;
					System.out.println("Introduza o título do album");
					titulo=sc.nextLine();
					List<Vinyl> list_vinyl=p2w.albumGetinfo(titulo.toLowerCase());
					if (list_vinyl.size()==0) {
						System.out.println("Não obteve nenhum resultado");
						
					}
					else {
						functions.Print_Info_Vinyl(list_vinyl);
					}
					
					
					
				}
				
				
				if (menu_option.equals("2")) {
					double score;
					System.out.println("Introduza o rating:");
					while (!sc.hasNextDouble()) {
						System.out.println("Introduza o rating:");
						sc.next();
					}
					score=sc.nextDouble();
					sc.nextLine();
					List<Vinyl> list_vinyl=p2w.scoresabovex(BigDecimal.valueOf(score));
					if (list_vinyl.size()==0) {
						System.out.println("Não obteve nenhum resultado");
					}
					else {
						functions.Print_Info_Vinyl(list_vinyl);
					}
					
					
				}
				
				if (menu_option.equals("3")) {
					System.out.println("Introduza um ou mais géneros separados por ;");
					String[] generos=sc.nextLine().split(";");
					List<String> list_generos=new ArrayList<>();
					for (int i = 0; i < generos.length; i++) {
						list_generos.add(generos[i]);
						
					}
					List<Vinyl> list_vinyl=p2w.albumBygenre(list_generos);
					if (list_vinyl.size()==0) {
						System.out.println("Não obteve nenhum resultado");
					}
					else {
						functions.Print_Info_Vinyl(list_vinyl);
					}
				
				}
				
				if (menu_option.equals("4")) {
					String Editora;
					System.out.println("Introduza uma editora:");
					Editora=sc.nextLine();
					float average;
					average=p2wn.averageScore(Editora);
					if (Float.isNaN(average)) {
						System.out.println("Não obteve nenhum resultado");
					}
					else {
					System.out.println("Rating Médio dos Albuns");
					System.out.println(average);
					}
				}
				
				if(menu_option.equals("5")) {
					String music_name;
					System.out.println("Introduza o nome de uma música");
					music_name=sc.nextLine();
					List<String> album_title=p2wn.albumTitle(music_name);
					if (album_title.size()==0) {
						System.out.println("Não obteve nenhum resultado");
					}
					else {
					System.out.println("Titulos");
					for (int i = 0; i < album_title.size(); i++) {
						System.out.println(album_title.get(i));
						
					}
					}
				}
				
				if (menu_option.equals("6")) {
					String genero;
					double score;
					System.out.println("Introduza um ou mais géneros separados por ;");
					genero=sc.nextLine();
					System.out.println("Introduza o rating:");
					while (!sc.hasNextDouble()) {
						System.out.println("Introduza o rating:");
						sc.next();
					}
					score=sc.nextDouble();
					sc.nextLine();
					List<artifactnew.Vinyl> list_vinyl=p2wn.albumsGenreScore(BigDecimal.valueOf(score), genero);
					if (list_vinyl.size()==0) {
						System.out.println("Não obteve nenhum resultado");
					}
					else {
					functions.Print_Info_Vinyl2(list_vinyl);
					}
					
				}
				
				if (menu_option.equals(("7"))) {
					System.out.println("Introduza o nome de uma música");
					String name = sc.nextLine();
					List<String> list_artists = p2wn.artistsNames(name);
					if (list_artists.size()==0) {
						System.out.println("Não obteve nenhum resultado");
					}
					else {
					System.out.println("Artistas");
					for (int i = 0; i < list_artists.size(); i++) {
						System.out.println(list_artists.get(i));
					}
					}
				}
				
				if (menu_option.equals("8"))
				{
					System.out.println("Introduza a editora ");
					String editora = sc.nextLine();
					System.out.println("Introduza um ou mais géneros separados por ;");
					String[] generos = sc.nextLine().split(";");
					List<String> list_generos = new ArrayList();
					for (int i = 0; i < generos.length; i++) {
						list_generos.add(generos[i]);
					}
					int total = p2wn.albGenLab(editora, list_generos);
					System.out.println("Total de Albuns:");
					System.out.println(total);
				}
				if (menu_option.equals("9")) {
					
				}
				
			}
			if (menu.equals("5")) {
				System.out.println("Volte sempre :)");
				keep_cycle=false;
			}
			
			
		} while (keep_cycle);
		
		
		
	}
	
	
	

}
