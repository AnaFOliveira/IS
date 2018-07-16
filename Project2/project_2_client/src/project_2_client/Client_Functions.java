package project_2_client;

import java.util.List;

import artifact.Music;
import artifact.Vinyl;

public class Client_Functions {
	public void Print_Info_Vinyl(List<Vinyl> list_vinyl) {
		System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n","ASIN","Género","Titulo",
				"Artista","Ano","Editora","Reviews","Score","Preço");
		for (int i =0 ; i < list_vinyl.size(); i++) {
			List<String> list_genero=list_vinyl.get(i).getGenre();
			for (int j = 0; j < list_genero.size(); j++) {
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n",list_vinyl.get(i).getId(),list_genero.get(j),
						list_vinyl.get(i).getTitle(),list_vinyl.get(i).getArtist().get(0),list_vinyl.get(i).getYear(),list_vinyl.get(i).getLabel(),
						list_vinyl.get(i).getNreview(),list_vinyl.get(i).getRating(),list_vinyl.get(i).getPrice());
			}
		}	
		System.out.println();
		System.out.println("Lista de Músicas");
		for (int i = 0; i < list_vinyl.size(); i++) {
			List<Music> list_music=list_vinyl.get(i).getMusic();
			System.out.println();
			System.out.println(list_vinyl.get(i).getTitle());
			System.out.println();
			System.out.printf("%-20s %-20s %-20s %-20s %-20s %n","Nome","Duração","Rating",
					"Review","Price");
			for (int j = 0; j <list_music.size() ; j++) {
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %n",list_music.get(j).getName(),list_music.get(j).getDuration(),
						list_music.get(j).getSrating(),list_music.get(j).getSnreview(),list_music.get(j).getSprice());
			}
		}
	}
	
	public void Print_Info_Vinyl2(List<artifactnew.Vinyl> list_vinyl) {
		System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n","ASIN","Género","Titulo",
				"Artista","Ano","Editora","Reviews","Score","Preço");
		for (int i =0 ; i < list_vinyl.size(); i++) {
			List<String> list_genero=list_vinyl.get(i).getGenre();
			for (int j = 0; j < list_genero.size(); j++) {
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n",list_vinyl.get(i).getId(),list_genero.get(j),
						list_vinyl.get(i).getTitle(),list_vinyl.get(i).getArtist().get(0),list_vinyl.get(i).getYear(),list_vinyl.get(i).getLabel(),
						list_vinyl.get(i).getNreview(),list_vinyl.get(i).getRating(),list_vinyl.get(i).getPrice());
			}
		}	
		System.out.println();
		System.out.println("Lista de Músicas");
		for (int i = 0; i < list_vinyl.size(); i++) {
			List<artifactnew.Music> list_music=list_vinyl.get(i).getMusic();
			System.out.println();
			System.out.println(list_vinyl.get(i).getTitle());
			System.out.println();
			System.out.printf("%-20s %-20s %-20s %-20s %-20s %n","Nome","Duração","Rating",
					"Review","Price");
			for (int j = 0; j <list_music.size() ; j++) {
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %n",list_music.get(j).getName(),list_music.get(j).getDuration(),
						list_music.get(j).getSrating(),list_music.get(j).getSnreview(),list_music.get(j).getSprice());
			}
		}
	}
	

}
