package client3;

import java.util.ArrayList;
import java.util.List;

import jpa.Music;
import jpa.Purchase;
import jpa.Vinyl;

public class functions {

	public List<String> info_newbank(String nome, int number) {
		List<String> list_info = new ArrayList<>();
		list_info.add(nome);
		list_info.add(Integer.toString(number));
		return list_info;
	}

	public void Print_Info_Vinyl(List<Vinyl> list_vinyl) {
		if (list_vinyl.size() == 0) {
			System.out.println("Não houve resultados da pequisa");
		} else {
			System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", "ASIN", "Género", "Titulo",
					"Artista", "Ano", "Editora", "Reviews", "Score", "Preço");
			for (int i = 0; i < list_vinyl.size(); i++) {
				List<String> list_genero = list_vinyl.get(i).getGenre();
				for (int j = 0; j < list_genero.size(); j++) {
					System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", list_vinyl.get(i).getId(),
							list_genero.get(j), list_vinyl.get(i).getTitle(), list_vinyl.get(i).getArtist().get(0),
							list_vinyl.get(i).getYear(), list_vinyl.get(i).getLabel(), list_vinyl.get(i).getNreview(),
							list_vinyl.get(i).getRating(), list_vinyl.get(i).getPrice());
				}
			}
			System.out.println();
			System.out.println("Lista de Músicas");
			for (int i = 0; i < list_vinyl.size(); i++) {
				List<Music> list_music = list_vinyl.get(i).getMusic();
				System.out.println();
				System.out.println(list_vinyl.get(i).getTitle());
				System.out.println();
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %n", "Nome", "Duração", "Rating", "Review", "Price");
				for (int j = 0; j < list_music.size(); j++) {
					System.out.printf("%-20s %-20s %-20s %-20s %-20s %n", list_music.get(j).getName(),
							list_music.get(j).getDuration(), list_music.get(j).getSrating(),
							list_music.get(j).getSnreview(), list_music.get(j).getSprice());
				}
			}
		}
	}

	public void Print_Info_Purchase(List<Purchase> list_purchases) {
		if (list_purchases.size() == 0) {
			System.out.println("Não existe nenhuma compra nesse estado.");
		} else {
			System.out.println(list_purchases.get(0).getVinyl_title());
			System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", "Id", "Data-Tempo", "Vinyls", "Estado", "Preço",
					"Cartão");
			for (int i = 0; i < list_purchases.size(); i++) {
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", list_purchases.get(i).getId(),
						list_purchases.get(i).getDate_time(), list_purchases.get(i).getVinyl_title().get(0),
						list_purchases.get(i).getStatus(), list_purchases.get(i).getAmount(),
						list_purchases.get(i).getCard_number());
				
			}
		}
	}
}
