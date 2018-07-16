package music;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Processor {
	
	public Map<ArrayList<ArrayList<Vinyl>>, ArrayList<String>> search_category(ArrayList<Vinyl> final_result) {//Divide os resultados por categoria
		
		Map<ArrayList<ArrayList<Vinyl>>, ArrayList<String>> map_category = new HashMap<>();
		ArrayList<ArrayList<Vinyl>> list_category = new ArrayList<ArrayList<Vinyl>>();
		ArrayList<String> list_genre = new ArrayList<String>();
		for (int i = 0; i < final_result.size(); i++) {
			if (i == 0) {
				Vinyl vinyl = final_result.get(i);
				for (int j = 0; j < vinyl.getGenre().size(); j++) {
					ArrayList<Vinyl> vinyl_category = new ArrayList<Vinyl>();
					vinyl_category.add(vinyl);
					list_genre.add(vinyl.getGenre().get(j));
					list_category.add(vinyl_category);

				}
			} else if (i > 0) {
				Vinyl vinyl = final_result.get(i);
				for (int j = 0; j < vinyl.getGenre().size(); j++) {
					int count = 0;
					for (int j2 = 0; j2 < list_genre.size(); j2++) {
						if (vinyl.getGenre().get(j).toLowerCase().equals(list_genre.get(j2).toLowerCase())) {
							list_category.get(j2).add(vinyl);
							count++;
						}
					}
					if (count == 0) {
						ArrayList<Vinyl> vinyl_category = new ArrayList<Vinyl>();
						vinyl_category.add(vinyl);
						list_genre.add(vinyl.getGenre().get(j));
						list_category.add(vinyl_category);
					}
				}
			}

		}
		map_category.put(list_category, list_genre);
		return map_category;
	}

	public Catalognew create_category(Map<ArrayList<ArrayList<Vinyl>>, ArrayList<String>> map_category) { //Cria um catálogo novo, cujos resultados estão ordenados por categoria (tendo em conta o nome dos artistas)
		Catalognew catalog_new = new Catalognew();
		ArrayList<String> list_genre = new ArrayList<String>();
		ArrayList<ArrayList<Vinyl>> list_vinyl = new ArrayList<ArrayList<Vinyl>>();
		for (Map.Entry<ArrayList<ArrayList<Vinyl>>, ArrayList<String>> entry : map_category.entrySet()) {
			list_vinyl = entry.getKey();
			list_genre = entry.getValue();
		}
		for (int i = 0; i < list_vinyl.size(); i++) {
			ArrayList<String> list_artist = new ArrayList<String>();
			ArrayList<Vinyl> list_vinyl_sorted = new ArrayList<Vinyl>();
			for (int j = 0; j < list_vinyl.get(i).size(); j++) {
				String artist_name = list_vinyl.get(i).get(j).getArtist().get(0);
				list_artist.add(artist_name.toLowerCase());
			}
			Collections.sort(list_artist);
			for (int j = 0; j < list_artist.size(); j++) {
				for (int j2 = 0; j2 < list_vinyl.get(i).size(); j2++) {
					if (list_artist.get(j).equals(list_vinyl.get(i).get(j2).getArtist().get(0).toLowerCase())) {
						list_vinyl_sorted.add(list_vinyl.get(i).get(j2));
					}
				}
			}

			list_vinyl.get(i).clear();
			list_vinyl.get(i).addAll(list_vinyl_sorted);
		}

		List<Category> list_category = new ArrayList<>();
		for (int i = 0; i < list_genre.size(); i++) {
			Category category = new Category();
			ArrayList<Vinylnew> list_newvinyl = new ArrayList<Vinylnew>();
			category.setId(list_genre.get(i));
			for (int j = 0; j < list_vinyl.get(i).size(); j++) {
				Vinylnew new_vinyl = new Vinylnew();
				new_vinyl.setAsin(list_vinyl.get(i).get(j).getId());
				new_vinyl.setNreview(list_vinyl.get(i).get(j).getNreview());
				new_vinyl.setPrice(list_vinyl.get(i).get(j).getPrice());
				new_vinyl.setRating(list_vinyl.get(i).get(j).getRating());
				new_vinyl.setTitle(list_vinyl.get(i).get(j).getTitle());
				new_vinyl.setYear(list_vinyl.get(i).get(j).getYear());
				new_vinyl.setArtist(list_vinyl.get(i).get(j).getArtist());
				List<Music> album = list_vinyl.get(i).get(j).getMusic();
				List<Musicnew> new_album = new ArrayList<>();

				for (int k = 0; k < album.size(); k++) {
					Musicnew music = new Musicnew();
					music.setName(album.get(k).getName());
					music.setDuration(album.get(k).getDuration());
					music.setSnreview(album.get(k).getSnreview());
					music.setSprice(album.get(k).getSprice());
					music.setSrating(album.get(k).getSrating());
					new_album.add(music);
				}
				new_vinyl.setMusicnew(new_album);
				list_newvinyl.add(new_vinyl);
			}
			category.setVinylnew(list_newvinyl);
			list_category.add(category);

		}
		catalog_new.setCategory(list_category);
		return catalog_new;
	}

	public ArrayList<Vinyl> artist_higher(ArrayList<Vinyl> final_result) {//Ordena os artistas cujos albuns tem melhores reviews
		ArrayList<BigDecimal> list_scores = new ArrayList<BigDecimal>();
		ArrayList<Vinyl> list_vinyl_sorted = new ArrayList<Vinyl>();
		for (int i = 0; i < final_result.size(); i++) {
			BigDecimal scores = final_result.get(i).getRating();
			list_scores.add(scores);
		}
		Collections.sort(list_scores);
		for (int j = 0; j < list_scores.size(); j++) {
			for (int j2 = 0; j2 < final_result.size(); j2++) {
				if (list_scores.get(j) == final_result.get(j2).getRating()) {
					list_vinyl_sorted.add(final_result.get(j2));
				}
			}
		}
		return list_vinyl_sorted;
	}

	public void create_statistics(Catalognew catalog_new, int N, ArrayList<Vinyl> list_viny_inicial) { //Cria estatisticas
		ArrayList<String> total_artists = new ArrayList<String>();
		ArrayList<String> total_artists_final = new ArrayList<String>();

		for (int i = 0; i < list_viny_inicial.size(); i++) {
			for (int j = 0; j < list_viny_inicial.get(i).getArtist().size(); j++) {
				total_artists.add(list_viny_inicial.get(i).getArtist().get(j));
			}
		}

		Set<String> set = new HashSet<String>(total_artists);
		total_artists_final.addAll(set);
		long total = total_artists_final.size();
		Statistics statistics = new Statistics();
		statistics.setTotalartists(BigInteger.valueOf(total)); //Número de artistas processados na pesquisa

		List<Rateartists> Nartists = new ArrayList<>();//Lista com N artistas cujos albuns tem as melhores reviews
		for (int i = 0; i < N; i++) {
			if (i >= list_viny_inicial.size()) {
				break;
			}
			Rateartists adding = new Rateartists();
			adding.setArtistalbum(list_viny_inicial.get(i).getTitle());
			adding.setArtistname(list_viny_inicial.get(i).getArtist().get(0));
			adding.setNumberscore(list_viny_inicial.get(i).getRating());
			Nartists.add(adding);

		}
		statistics.setRateartists(Nartists);

		catalog_new.setStatistics(statistics);
	}
}
