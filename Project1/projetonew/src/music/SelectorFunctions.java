package music;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SelectorFunctions {

	public ArrayList<Vinyl> searchTitle(String title) {// Procura por título
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>();

		for (int i = 0; i < Selector.list_vinyl.size(); i++) {
			Vinyl vinyl = Selector.list_vinyl.get(i);
			if (vinyl.getTitle().toLowerCase().contains(title.toLowerCase())) {
				result_vinyl.add(vinyl);
			}
		}
		return result_vinyl;
	}

	public ArrayList<Vinyl> searchArtist(String artist) {// Procura por artista
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>();

		for (int i = 0; i < Selector.list_vinyl.size(); i++) {
			Vinyl vinyl = Selector.list_vinyl.get(i);
			for (int j = 0; j < Selector.list_vinyl.get(i).getArtist().size(); j++) {
				if (Selector.list_vinyl.get(i).getArtist().get(j).toLowerCase().contains(artist.toLowerCase())) {
					result_vinyl.add(vinyl);
				}
			}
		}
		return result_vinyl;
	}

	public ArrayList<Vinyl> searchYear(String opcao, long year) { // Procura por ano 
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>(); 
		for (int i = 0; i < Selector.list_vinyl.size(); i++) {
			Vinyl vinyl = Selector.list_vinyl.get(i);
			if (opcao.equals("1")) {

				if (BigInteger.valueOf(year).compareTo(vinyl.getYear()) == 0) {
					result_vinyl.add(vinyl);
				}
			}
			if (opcao.equals("2")) {

				if (BigInteger.valueOf(year).compareTo(vinyl.getYear()) == 1) {
					result_vinyl.add(vinyl);
				}
			} else if (opcao.equals("3")) // if value>rating
			{
				if (BigInteger.valueOf(year).compareTo(vinyl.getYear()) == -1) {
					result_vinyl.add(vinyl);
				}
			}

			else if (opcao.equals("4")) // if value<rating
			{
				if (BigInteger.valueOf(year).compareTo(vinyl.getYear()) == 1
						|| BigInteger.valueOf(year).compareTo(vinyl.getYear()) == 0) {
					result_vinyl.add(vinyl);
				}
			} else if (opcao.equals("5")) // if value>rating
			{
				if (BigInteger.valueOf(year).compareTo(vinyl.getYear()) == -1
						|| BigInteger.valueOf(year).compareTo(vinyl.getYear()) == 0) {
					result_vinyl.add(vinyl);
				}
			}

		}
		return result_vinyl;
	}

	public ArrayList<Vinyl> searchRating(String opcao, double value) { // Procura por rating
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>(); 
		for (int i = 0; i < Selector.list_vinyl.size(); i++) {
			Vinyl vinyl = Selector.list_vinyl.get(i);
			if (vinyl.getRating() != null) {

				if (opcao.equals("1")) {
					if (BigDecimal.valueOf(value).compareTo(vinyl.getRating()) == 0) {

						result_vinyl.add(vinyl);
					}
				} else if (opcao.equals("2")) // if value<rating
				{
					if (BigDecimal.valueOf(value).compareTo(vinyl.getRating()) == 1) {
						result_vinyl.add(vinyl);
					}
				} else if (opcao.equals("3")) // if value>rating
				{
					if (BigDecimal.valueOf(value).compareTo(vinyl.getRating()) == -1) {
						result_vinyl.add(vinyl);
					}
				}

				else if (opcao.equals("4")) // if value<rating
				{
					if (BigDecimal.valueOf(value).compareTo(vinyl.getRating()) == 1
							|| BigDecimal.valueOf(value).compareTo(vinyl.getRating()) == 0) {
						result_vinyl.add(vinyl);
					}
				} else if (opcao.equals("5")) // if value>rating
				{
					if ((BigDecimal.valueOf(value).compareTo(vinyl.getRating()) == -1)
							|| (BigDecimal.valueOf(value).compareTo(vinyl.getRating()) == 0)) {
						result_vinyl.add(vinyl);
					}
				}
			}
		}
		return result_vinyl;
	}

	public ArrayList<Vinyl> searchGenre(String genre) { // Procura por género
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>();

		for (int j = 0; j < Selector.list_vinyl.size(); j++) {
			Vinyl vinyl = Selector.list_vinyl.get(j);
			List<String> vinyl_genre = vinyl.getGenre();
			for (int k = 0; k < vinyl_genre.size(); k++) {
				if (vinyl_genre.get(k).toLowerCase().contains(genre.toLowerCase())) {
					result_vinyl.add(vinyl);
				}
			}
		}
		return result_vinyl;

	}

	public ArrayList<Vinyl> searchNreviews(long nreviews, String opcao) { //Procura por Número de Reviews
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>();
		for (int i = 0; i < Selector.list_vinyl.size(); i++) {
			Vinyl vinyl = Selector.list_vinyl.get(i);
			if (vinyl.getNreview() != null) {

				if (opcao.equals("1")) {
					if (BigInteger.valueOf(nreviews).compareTo(vinyl.getNreview()) == 0) {
						result_vinyl.add(vinyl);
					}

				} else if (opcao.equals("2")) {
					if (BigInteger.valueOf(nreviews).compareTo(vinyl.getNreview()) == 1) {
						result_vinyl.add(vinyl);
					}

				} else if (opcao.equals("3")) {
					if (BigInteger.valueOf(nreviews).compareTo(vinyl.getNreview()) == -1) {
						result_vinyl.add(vinyl);
					}

				} else if (opcao.equals("4")) {
					if (BigInteger.valueOf(nreviews).compareTo(vinyl.getNreview()) == 0
							|| BigInteger.valueOf(nreviews).compareTo(vinyl.getNreview()) == 1) {
						result_vinyl.add(vinyl);
					}

				} else if (opcao.equals("5")) {
					if (BigInteger.valueOf(nreviews).compareTo(vinyl.getNreview()) == 0
							|| BigInteger.valueOf(nreviews).compareTo(vinyl.getNreview()) == -1) {
						result_vinyl.add(vinyl);
					}
				}
			}
		}

		return result_vinyl;
	}

	public ArrayList<Vinyl> searchPrice(double price, String opcao) { //Procura por preço
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>();

		for (int i = 0; i < Selector.list_vinyl.size(); i++) {
			Vinyl vinyl = Selector.list_vinyl.get(i);
			if (opcao.equals("1")) {
				if (BigDecimal.valueOf(price).compareTo(vinyl.getPrice()) == 0) {
					result_vinyl.add(vinyl);
				}

			} else if (opcao.equals("2")) {
				if (BigDecimal.valueOf(price).compareTo(vinyl.getPrice()) == 1) {
					result_vinyl.add(vinyl);
				}

			} else if (opcao.equals("3")) {
				if (BigDecimal.valueOf(price).compareTo(vinyl.getPrice()) == -1) {
					result_vinyl.add(vinyl);
				}

			} else if (opcao.equals("4")) {
				if (BigDecimal.valueOf(price).compareTo(vinyl.getPrice()) == 0
						|| BigDecimal.valueOf(price).compareTo(vinyl.getPrice()) == 1) {
					result_vinyl.add(vinyl);
				}

			} else if (opcao.equals("5")) {
				if (BigDecimal.valueOf(price).compareTo(vinyl.getPrice()) == 0
						|| BigDecimal.valueOf(price).compareTo(vinyl.getPrice()) == -1) {
					result_vinyl.add(vinyl);
				}
			}
		}
		return result_vinyl;

	}

	public ArrayList<Vinyl> nameMusic(String name) { //Procura por nome de música 
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>();

		for (int i = 0; i < Selector.list_vinyl.size(); i++) {
			Vinyl vinyl = Selector.list_vinyl.get(i);
			List<Music> list_music = vinyl.getMusic();
			for (int j = 0; j < list_music.size(); j++) {
				if (list_music.get(j).getName().toLowerCase().contains(name.toLowerCase())) {
					result_vinyl.add(vinyl);
				}
			}
		}
		return result_vinyl;
	}

	public ArrayList<Vinyl> durationMusic(double duration, String opcao) { //Procura por duração de música
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>();

		for (int i = 0; i < Selector.list_vinyl.size(); i++) {
			Vinyl vinyl = Selector.list_vinyl.get(i);
			List<Music> list_music = vinyl.getMusic();
			for (int j = 0; j < list_music.size(); j++) {
				if (list_music.get(j).getDuration() != null) {

					if (opcao.equals("1")) {
						if (BigDecimal.valueOf(duration).compareTo(list_music.get(j).getDuration()) == 0) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("2")) {
						if (BigDecimal.valueOf(duration).compareTo(list_music.get(j).getDuration()) == 1) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("3")) {
						if (BigDecimal.valueOf(duration).compareTo(list_music.get(j).getDuration()) == -1) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("4")) {
						if (BigDecimal.valueOf(duration).compareTo(list_music.get(j).getDuration()) == 0
								|| BigDecimal.valueOf(duration).compareTo(list_music.get(j).getDuration()) == 1) {
							result_vinyl.add(vinyl);
						}
					} else if (opcao.equals("5")) {
						if (BigDecimal.valueOf(duration).compareTo(list_music.get(j).getDuration()) == 0
								|| BigDecimal.valueOf(duration).compareTo(list_music.get(j).getDuration()) == -1) {
							result_vinyl.add(vinyl);
						}
					}
				}
			}
		}
		for (int i = 0; i < result_vinyl.size(); i++) {
			if (i == result_vinyl.size() - 1) {
				break;
			}
			for (int j = i + 1; j < result_vinyl.size(); j++) {

				if (result_vinyl.get(i).getId().equals(result_vinyl.get(j).getId())) {
					result_vinyl.remove(j);
				}
			}
		}
		return result_vinyl;

	}

	public ArrayList<Vinyl> rateMusic(double rate, String opcao) { //Procura por rating da música
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>();

		for (int i = 0; i < Selector.list_vinyl.size(); i++) {
			Vinyl vinyl = Selector.list_vinyl.get(i);
			List<Music> list_music = vinyl.getMusic();
			for (int j = 0; j < list_music.size(); j++) {
				if (list_music.get(j).getSrating() != null) {

					if (opcao.equals("1")) {
						if (BigDecimal.valueOf(rate).compareTo(list_music.get(j).getSrating()) == 0) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("2")) {
						if (BigDecimal.valueOf(rate).compareTo(list_music.get(j).getSrating()) == 1) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("3")) {
						if (BigDecimal.valueOf(rate).compareTo(list_music.get(j).getSrating()) == -1) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("4")) {
						if (BigDecimal.valueOf(rate).compareTo(list_music.get(j).getSrating()) == 0
								|| BigDecimal.valueOf(rate).compareTo(list_music.get(j).getSrating()) == 1) {
							result_vinyl.add(vinyl);
						}
					} else if (opcao.equals("5")) {
						if (BigDecimal.valueOf(rate).compareTo(list_music.get(j).getSrating()) == 0
								|| BigDecimal.valueOf(rate).compareTo(list_music.get(j).getSrating()) == -1) {
							result_vinyl.add(vinyl);
						}
					}
				}
			}
		}
		for (int i = 0; i < result_vinyl.size(); i++) {
			if (i == result_vinyl.size() - 1) {
				break;
			}
			for (int j = i + 1; j < result_vinyl.size(); j++) {

				if (result_vinyl.get(i).getId().equals(result_vinyl.get(j).getId())) {
					result_vinyl.remove(j);
				}
			}
		}
		return result_vinyl;
	}

	public ArrayList<Vinyl> nreviewMusic(long snreviews, String opcao) { //Procura de Número de Reviews da música
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>();
		for (int i = 0; i < Selector.list_vinyl.size(); i++) {
			Vinyl vinyl = Selector.list_vinyl.get(i);
			List<Music> list_music = vinyl.getMusic();
			for (int j = 0; j < list_music.size(); j++) {
				if (list_music.get(j).getSnreview() != null) {

					if (opcao.equals("1")) {
						if (BigInteger.valueOf(snreviews).compareTo(list_music.get(j).getSnreview()) == 0) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("2")) {
						if (BigInteger.valueOf(snreviews).compareTo(list_music.get(j).getSnreview()) == 1) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("3")) {
						if (BigInteger.valueOf(snreviews).compareTo(list_music.get(j).getSnreview()) == -1) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("4")) {
						if (BigInteger.valueOf(snreviews).compareTo(list_music.get(j).getSnreview()) == 0
								|| BigInteger.valueOf(snreviews).compareTo(list_music.get(j).getSnreview()) == 1) {
							result_vinyl.add(vinyl);
						}
					} else if (opcao.equals("5")) {
						if (BigInteger.valueOf(snreviews).compareTo(list_music.get(j).getSnreview()) == 0
								|| BigInteger.valueOf(snreviews).compareTo(list_music.get(j).getSnreview()) == -1) {
							result_vinyl.add(vinyl);
						}
					}
				}
			}
		}
		for (int i = 0; i < result_vinyl.size(); i++) {
			if (i == result_vinyl.size() - 1) {
				break;
			}
			for (int j = i + 1; j < result_vinyl.size(); j++) {

				if (result_vinyl.get(i).getId().equals(result_vinyl.get(j).getId())) {
					result_vinyl.remove(j);
				}
			}
		}
		return result_vinyl;
	}

	public ArrayList<Vinyl> sPrice(double sprice, String opcao) {//Procura por preço da música
		ArrayList<Vinyl> result_vinyl = new ArrayList<Vinyl>();

		for (int i = 0; i < Selector.list_vinyl.size(); i++) {
			Vinyl vinyl = Selector.list_vinyl.get(i);
			List<Music> list_music = vinyl.getMusic();
			for (int j = 0; j < list_music.size(); j++) {
				if (list_music.get(j).getSprice() != null) {

					if (opcao.equals("1")) {
						if (BigDecimal.valueOf(sprice).compareTo(list_music.get(j).getSprice()) == 0) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("2")) {
						if (BigDecimal.valueOf(sprice).compareTo(list_music.get(j).getSprice()) == 1) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("3")) {
						if (BigDecimal.valueOf(sprice).compareTo(list_music.get(j).getSprice()) == -1) {
							result_vinyl.add(vinyl);
						}

					} else if (opcao.equals("4")) {
						if (BigDecimal.valueOf(sprice).compareTo(list_music.get(j).getSprice()) == 0
								|| BigDecimal.valueOf(sprice).compareTo(list_music.get(j).getSprice()) == 1) {
							result_vinyl.add(vinyl);
						}
					} else if (opcao.equals("5")) {
						if (BigDecimal.valueOf(sprice).compareTo(list_music.get(j).getSprice()) == 0
								|| BigDecimal.valueOf(sprice).compareTo(list_music.get(j).getSprice()) == -1) {
							result_vinyl.add(vinyl);
						}
					}
				}
			}
		}
		for (int i = 0; i < result_vinyl.size(); i++) {
			if (i == result_vinyl.size() - 1) {
				break;
			}
			for (int j = i + 1; j < result_vinyl.size(); j++) {

				if (result_vinyl.get(i).getId().equals(result_vinyl.get(j).getId())) {
					result_vinyl.remove(j);
				}
			}
		}
		return result_vinyl;
	}
}