package project_2_webnew;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import artifact.Music;
import artifact.Projeto2Ws;
import artifact.Projeto2WsService;
import artifact.Vinyl;

@WebService
public class project_2_webnew {
	private Projeto2Ws Projeto2Ws;

	@WebMethod
	public List<Vinyl> total_vinyls() {

		this.Projeto2Ws = new Projeto2WsService().getProjeto2WsPort();
		List<Vinyl> total_vinyls = Projeto2Ws.totalData();
		return total_vinyls;
	}

	@WebMethod
	public List<Vinyl> albumsGenre(List<String> genre) {

		this.Projeto2Ws = new Projeto2WsService().getProjeto2WsPort();
		List<Vinyl> albumsGenre = Projeto2Ws.albumBygenre(genre);
		return albumsGenre;
	}
	
	@WebMethod
	public List<Vinyl> album_score(BigDecimal x){
		this.Projeto2Ws=new Projeto2WsService().getProjeto2WsPort();
		List<Vinyl> album_score=Projeto2Ws.scoresabovex(x);
		return album_score;
	}
		
	@WebMethod
	public List<String> album_title(String music_name) {
		List<Vinyl> total_vinyls = total_vinyls();
		List<String> titles = new ArrayList<>();
		for (int i = 0; i < total_vinyls.size(); i++) {
			for (int j = 0; j < total_vinyls.get(i).getMusic().size(); j++) {
				if (total_vinyls.get(i).getMusic().get(j).getName().toLowerCase().equals(music_name.toLowerCase()))
					titles.add(total_vinyls.get(i).getTitle());
			}

		}
		return titles;
	}



	@WebMethod
	public float average_score(String record_label) {
		List<Vinyl> total_vinyls = total_vinyls();
		List<String> recordlabel = new ArrayList<>();
		List<BigDecimal> scores = new ArrayList<>();
		float average_score = 0.0f;
		for (int i = 0; i < total_vinyls.size(); i++) {
			if (total_vinyls.get(i).getLabel().toLowerCase().equals(record_label.toLowerCase())) {
				scores.add(total_vinyls.get(i).getRating());
			}
		}

		for (int i = 0; i < scores.size(); i++) {
			average_score = average_score + scores.get(i).floatValue();
		}
		return average_score / scores.size();

	}


	@WebMethod
	public List<String> artists_names(String music_name){
		List<String> artists_names=new ArrayList<>();
		List<Vinyl> all_vinyls=total_vinyls();
		//all_vinyls.get(0).g
		for (int i = 0; i < all_vinyls.size(); i++) {
			List<Music> music_list=all_vinyls.get(i).getMusic();
			for (int j = 0; j < music_list.size(); j++) {
				if (music_list.get(j).getName().toLowerCase().equals(music_name.toLowerCase())) {
					artists_names.addAll(all_vinyls.get(i).getArtist());
				}
			}
		}
		
		for (int i = 0; i < artists_names.size(); i++) {
			if (i == artists_names.size() - 1) {
				break;
			}
			for (int j = i + 1; j < artists_names.size(); j++) {

				if (artists_names.get(i).equals(artists_names.get(j))) {
					artists_names.remove(j);
				}
			}
		}
		return artists_names;
		
	}
	
	@WebMethod
	public List<Vinyl> albums_genre_score(BigDecimal x, String genre){
		List<String> genre_list=new ArrayList<>();
		genre_list.add(genre);
		List<Vinyl> result_score=album_score(x);
		List<Vinyl> result_genre=albumsGenre(genre_list);
		List<Vinyl> result_vinyls=new ArrayList<>();
		for (int i = 0; i < result_score.size(); i++) {
			for (int j = 0; j < result_genre.size(); j++) {
				if (result_score.get(i).getId().equals(result_genre.get(j).getId())) {
					result_vinyls.add(result_score.get(i));
				}
			}
		}
		return result_vinyls;
		
		
	}
	
	@WebMethod
	public int alb_gen_lab(String record, List<String> genres) {
		List<Vinyl> vinyls = albumsGenre(genres);
		List<Vinyl> label_albuns = new ArrayList<>();
		for (int i = 0; i < vinyls.size(); i++) {
			if (vinyls.get(i).getLabel().toLowerCase().equals(record.toLowerCase())) {

				label_albuns.add(vinyls.get(i));
			}

		}
		return label_albuns.size();
	}
}
