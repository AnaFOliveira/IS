package music;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Selector {
	protected static Catalog catalog = Unmarshall.unmarshall("Projeto1_xml");
	protected static List<Vinyl> list_vinyl = catalog.getVinyl();

	public static void main(String[] args) {
		Marshall marshall = new Marshall();
		SelectorFunctions sf = new SelectorFunctions();
		Scanner sc = new Scanner(System.in);
		boolean op = true;

		// Menu de entrada
		do {
			ArrayList<Vinyl> lista_vinyls = new ArrayList<Vinyl>();
			ArrayList<ArrayList<Vinyl>> lista_total = new ArrayList<ArrayList<Vinyl>>();
			System.out.println("Bem vindo!");
			System.out.println("1-Pesquisa");
			System.out.println("2-Catalogo");
			System.out.println("3-Sair");
			String option = sc.next();

			// Menu de pesquisa
			while (option.equals("1")) {
				System.out.println("Escolha o tipo de pesquisa: e ou ou");
				String pesquisa = sc.next();
				while (!pesquisa.equals("e") && !pesquisa.equals("ou")) { // Validação do tipo de pesquisa
					System.out.println("Escolha o tipo de pesquisa: e ou ou");
					pesquisa = sc.next();
				}
				String[] numeros_pesquisa;
				System.out.println("Menu de Pesquisa: escolha o seu numero de opções de pesquisa separados por ;");
				System.out.println("1-Título");
				System.out.println("2-Artista");
				System.out.println("3-Ano");
				System.out.println("4-Género");
				System.out.println("5-Rating");
				System.out.println("6-Número de Reviews");
				System.out.println("7-Música");
				System.out.println("8-Preço");
				System.out.println("9-Voltar ao Menu Inicial");
				String option2 = sc.next();
				sc.nextLine();
				numeros_pesquisa = option2.split(";");
				for (int i = 0; i < numeros_pesquisa.length; i++) {
					
					if (numeros_pesquisa[i].equals("1")) { //Procura por título
						String titulo;
						System.out.println("Escreva o título");
						titulo = sc.nextLine();
						lista_vinyls = sf.searchTitle(titulo);
						lista_total.add(lista_vinyls);
						
					} else if (numeros_pesquisa[i].equals("2")) {//Procura por artista
						String artista;
						System.out.println("Escreva o artista");
						artista = sc.nextLine();
						lista_vinyls = sf.searchArtist(artista);
						lista_total.add(lista_vinyls);
						
					} else if (numeros_pesquisa[i].equals("3")) {//Procura por ano 
						long year;
						System.out.println("Introduza o ano:");
						while (!sc.hasNextLong()) {
							System.out.println("Introduza o ano:");
							sc.next();
						}
						year = sc.nextLong();
						System.out.println("Escreva a opção: ");
						System.out.println("1 - =");
						System.out.println("2 - <");
						System.out.println("3 - >");
						System.out.println("4 - <=");
						System.out.println("5 - >=");
						String opcao = sc.next();
						while (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3") && !opcao.equals("4")
								&& !opcao.equals("5")) {
							System.out.println("Escreva a opção: ");
							System.out.println("1 - =");
							System.out.println("2 - <");
							System.out.println("3 - >");
							System.out.println("4 - <=");
							System.out.println("5 - >=");
							opcao = sc.next();
						}
						sc.nextLine();
						lista_vinyls = sf.searchYear(opcao, year);
						lista_total.add(lista_vinyls);
						
					} else if (numeros_pesquisa[i].equals("4")) {//Procura por género 
						String genre;
						System.out.println("Escreva o genero::");
						genre = sc.nextLine();
						lista_vinyls = sf.searchGenre(genre);
						lista_total.add(lista_vinyls);
						
					} else if (numeros_pesquisa[i].equals("5")) {//Procura por rating
						double rating;
						System.out.println("Escreva o valor do rating");
						while (!sc.hasNextDouble()) {
							System.out.println("Introduza o valor do rating:");
							sc.next();
						}
						rating = sc.nextDouble();
						System.out.println("Escreva a opção: ");
						System.out.println("1 - =");
						System.out.println("2 - <");
						System.out.println("3 - >");
						System.out.println("4 - <=");
						System.out.println("5 - >=");
						String opcao = sc.next();
						while (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3") && !opcao.equals("4")
								&& !opcao.equals("5")) {
							System.out.println("Escreva a opção: ");
							System.out.println("1 - =");
							System.out.println("2 - <");
							System.out.println("3 - >");
							System.out.println("4 - <=");
							System.out.println("5 - >=");
							opcao = sc.next();
						}
						lista_vinyls = sf.searchRating(opcao, rating);
						lista_total.add(lista_vinyls);
						
					} else if (numeros_pesquisa[i].equals("6")) {//Procura por número de reviews
						long nreviews;
						System.out.println("Escreva o número de reviews");
						while (!sc.hasNextLong()) {
							System.out.println("Introduza o número de reviews:");
							sc.next();
						}
						nreviews = sc.nextLong();
						System.out.println("Escreva a opção: ");
						System.out.println("1 - =");
						System.out.println("2 - <");
						System.out.println("3 - >");
						System.out.println("4 - <=");
						System.out.println("5 - >=");
						String opcao = sc.next();
						while (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3") && !opcao.equals("4")
								&& !opcao.equals("5")) {
							System.out.println("Escreva a opção: ");
							System.out.println("1 - =");
							System.out.println("2 - <");
							System.out.println("3 - >");
							System.out.println("4 - <=");
							System.out.println("5 - >=");
							opcao = sc.next();
						}
						lista_vinyls = sf.searchNreviews(nreviews, opcao);
						lista_total.add(lista_vinyls);
					}

					else if (numeros_pesquisa[i].equals("7")) {//Procura por música e respetivo menu
						String[] numeros_pesquisa_musicas;
						System.out.println(
								"Menu de Pesquisa de Musicas: escolha o seu numero de opções de pesquisa separados por ;");
						System.out.println("1-Nome");
						System.out.println("2-Rate");
						System.out.println("3-Numero de Reviews");
						System.out.println("4-Preço");
						System.out.println("5-Duração");
						String option3 = sc.next();
						sc.nextLine();
						numeros_pesquisa_musicas = option3.split(";");
						for (int j = 0; j < numeros_pesquisa_musicas.length; j++) {
							
							if (numeros_pesquisa_musicas[j].equals("1")) {//Procura por nome de música
								String nome_musica;
								System.out.println("Escreva o nome da musica");
								nome_musica = sc.nextLine();
								lista_vinyls = sf.nameMusic(nome_musica);
								lista_total.add(lista_vinyls);

							} else if (numeros_pesquisa_musicas[j].equals("2")) {//Procura por rating da música
								double rate_music;
								System.out.println("Escreva a rate da musica:");
								while (!sc.hasNextDouble()) {
									System.out.println("Escreva a rate da musica:");
									sc.next();
								}
								rate_music = sc.nextDouble();
								System.out.println("Escreva a opção: ");
								System.out.println("1 - =");
								System.out.println("2 - <");
								System.out.println("3 - >");
								System.out.println("4 - <=");
								System.out.println("5 - >=");
								String opcao = sc.next();
								while (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3")
										&& !opcao.equals("4") && !opcao.equals("5")) {
									System.out.println("Escreva a opção: ");
									System.out.println("1 - =");
									System.out.println("2 - <");
									System.out.println("3 - >");
									System.out.println("4 - <=");
									System.out.println("5 - >=");
									opcao = sc.next();
								}
								lista_vinyls = sf.rateMusic(rate_music, opcao);
								lista_total.add(lista_vinyls);

							} else if (numeros_pesquisa_musicas[j].equals("3")) {//Procura por numero de reviews da música
								long nreviews_music;
								System.out.println("Escreva o numero de reviews da musica:");
								while (!sc.hasNextLong()) {
									System.out.println("Escreva o numero de reviews da musica:");
									sc.next();
								}
								nreviews_music = sc.nextLong();
								System.out.println("Escreva a opção: ");
								System.out.println("1 - =");
								System.out.println("2 - <");
								System.out.println("3 - >");
								System.out.println("4 - <=");
								System.out.println("5 - >=");
								String opcao = sc.next();
								while (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3")
										&& !opcao.equals("4") && !opcao.equals("5")) {
									System.out.println("Escreva a opção: ");
									System.out.println("1 - =");
									System.out.println("2 - <");
									System.out.println("3 - >");
									System.out.println("4 - <=");
									System.out.println("5 - >=");
									opcao = sc.next();
								}
								lista_vinyls = sf.nreviewMusic(nreviews_music, opcao);
								lista_total.add(lista_vinyls);

							} else if (numeros_pesquisa_musicas[j].equals("4")) {//Procura por preço de música
								double price_music;
								System.out.println("Escreva o preço da musica:");
								while (!sc.hasNextDouble()) {
									System.out.println("Escreva o preço da musica:");
									sc.next();
								}
								price_music = sc.nextDouble();
								System.out.println("Escreva a opção: ");
								System.out.println("1 - =");
								System.out.println("2 - <");
								System.out.println("3 - >");
								System.out.println("4 - <=");
								System.out.println("5 - >=");
								String opcao = sc.next();
								while (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3")
										&& !opcao.equals("4") && !opcao.equals("5")) {
									System.out.println("Escreva a opção: ");
									System.out.println("1 - =");
									System.out.println("2 - <");
									System.out.println("3 - >");
									System.out.println("4 - <=");
									System.out.println("5 - >=");
									opcao = sc.next();
								}
								lista_vinyls = sf.sPrice(price_music, opcao);
								lista_total.add(lista_vinyls);
								
							} else if (numeros_pesquisa_musicas[j].equals("5")) {//Procura por duração da música
								double duration_music;
								System.out.println("Escreva a duração da musica:");
								while (!sc.hasNextDouble()) {
									System.out.println("Escreva a duração da musica:");
									sc.next();
								}
								duration_music = sc.nextDouble();
								System.out.println("Escreva a opção: ");
								System.out.println("1 - =");
								System.out.println("2 - <");
								System.out.println("3 - >");
								System.out.println("4 - <=");
								System.out.println("5 - >=");
								String opcao = sc.next();
								while (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3")
										&& !opcao.equals("4") && !opcao.equals("5")) {
									System.out.println("Escreva a opção: ");
									System.out.println("1 - =");
									System.out.println("2 - <");
									System.out.println("3 - >");
									System.out.println("4 - <=");
									System.out.println("5 - >=");
									opcao = sc.next();
								}
								lista_vinyls = sf.durationMusic(duration_music, opcao);
								lista_total.add(lista_vinyls);

							}
						}
					}

					else if (numeros_pesquisa[i].equals("8")) {//Procura por preço do album
						double preco;
						System.out.println("Escreva o Preco");
						while (!sc.hasNextDouble()) {
							System.out.println("Introduza o preço:");
							sc.next();
						}
						preco = sc.nextDouble();
						System.out.println("Escreva a opção: ");
						System.out.println("1 - =");
						System.out.println("2 - <");
						System.out.println("3 - >");
						System.out.println("4 - <=");
						System.out.println("5 - >=");
						String opcao = sc.next();
						while (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3") && !opcao.equals("4")
								&& !opcao.equals("5")) {
							System.out.println("Escreva a opção: ");
							System.out.println("1 - =");
							System.out.println("2 - <");
							System.out.println("3 - >");
							System.out.println("4 - <=");
							System.out.println("5 - >=");
							opcao = sc.next();
						}
						lista_vinyls = sf.searchPrice(preco, opcao);
						lista_total.add(lista_vinyls);
					}

				}

				if (option2.equals("9")) { //Voltar ao menu inicial
					break;
				}
				if (pesquisa.equals("e")) { 
					List<Vinyl> pre_result = new ArrayList<>();
					
					if (lista_total.size() == 1 && lista_total.get(0).size() != 0) { //Se for feita pesquisa apenas com um critério inserido pelo utilizador e esta obtiver resultados
						pre_result = lista_total.get(0);
						Catalog novo_cat = new Catalog();
						novo_cat.setVinyl(pre_result); //criação de um novo catálogo com os resultado
						marshall.marshall(novo_cat); //criação de um ficheiro XML com o resultado
						
						Catalog catalog_proc = Unmarshall.unmarshall("XML_2"); //unmarshall do resultado adquirido pelo Selector e criação de uma lista com os resultados
						List<Vinyl> list_proc = catalog_proc.getVinyl();
						ArrayList<Vinyl> list_vinyl_proc = new ArrayList<Vinyl>();
						for (int j = 0; j < list_proc.size(); j++) {
							list_vinyl_proc.add(list_proc.get(j));
						}
						
						Processor proc = new Processor();
						Map<ArrayList<ArrayList<Vinyl>>, ArrayList<String>> map_category = proc
								.search_category(list_vinyl_proc);
						Catalognew new_catalog = proc.create_category(map_category); //Criação de um novo catálogo organizado por género 
						ArrayList<Vinyl> list_vinyl_sorted = proc.artist_higher(list_vinyl_proc); //Lista dos artistas cujos albuns tinham melhor rating
						int number_artist;
						System.out.println(
								"Introduza o número de artistas a ser visualizados com highest review scores:");
						while (!sc.hasNextInt()) {//validação do numero de artistas visualizados
							System.out.println(
									"Introduza o número de artistas a ser visualizados com highest review scores:");
							sc.nextInt();
						}
						number_artist = sc.nextInt();
						proc.create_statistics(new_catalog, number_artist, list_vinyl_sorted); //criação das estatisticas 
						
						
						Marshall new_2 = new Marshall(); //Criação de um novo ficheiro XML com os resultados do Processor
						new_2.marshall_2(new_catalog);
						System.out.println("A pesquisa foi efetuada com sucesso!");
						System.out.println("Foi criado um ficheiro html!");
						System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
						String escolha = sc.next();
						while (!escolha.toLowerCase().equals("yes") && !escolha.toLowerCase().equals("no")) {
							System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
							escolha = sc.next();
						}
						if (escolha.toLowerCase().equals("yes")) {//Nova pesquisa
							break;
						} else {
							System.out.println("Obrigado! Volte sempre!");//Saída da pesquisa e do programa
							op = false;
							break;
						}

					} else if (lista_total.size() > 1) {//Se houver mais que um critério de pesquisa, garante que os resultados correspondem à intersecção de todos os critérios
						int contador = 0;
						List<Vinyl> comparison_list = lista_total.get(0);
						for (int k = 0; k < comparison_list.size(); k++) {
							Vinyl compared_one = comparison_list.get(k);
							for (int m = 1; m < lista_total.size(); m++) {
								List<Vinyl> comparative_list = lista_total.get(m);
								if (comparative_list.contains(compared_one)) {
									contador++;
								}
							}

							if (contador == lista_total.size() - 1) { //Se o contador corresponder ao número de critérios existente, adiciona o resultado à lista
								pre_result.add(compared_one);
							}
						}
						if (pre_result.size() == 0) { // Ausência de resultados
							System.out.println("A sua pesquisa não obteve nenhum resultado!");
							System.out.println("Não foi criado nenhum ficheiro html!");
							System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
							String escolha = sc.next();
							while (!escolha.toLowerCase().equals("yes") && !escolha.toLowerCase().equals("no")) {
								System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
								escolha = sc.next();
							}
							if (escolha.toLowerCase().equals("yes")) {//Nova pesquisa
								break;
							} else {
								System.out.println("Obrigado! Volte sempre!");//Saída da pesquisa e do programa
								op = false;
								break;
							}
						}

						else {
							Catalog novo_cat = new Catalog();
							novo_cat.setVinyl(pre_result);//criação de um novo catálogo com os resultado
							marshall.marshall(novo_cat);//criação de um ficheiro XML com o resultado
							
							Catalog catalog_proc = Unmarshall.unmarshall("XML_2");//unmarshall do resultado adquirido pelo Selector e criação de uma lista com os resultados
							List<Vinyl> list_proc = catalog_proc.getVinyl();
							ArrayList<Vinyl> list_vinyl_proc = new ArrayList<Vinyl>();
							for (int j = 0; j < list_proc.size(); j++) {
								list_vinyl_proc.add(list_proc.get(j));
							}
							
							
							Processor proc = new Processor();
							Map<ArrayList<ArrayList<Vinyl>>, ArrayList<String>> map_category = proc
									.search_category(list_vinyl_proc);
							Catalognew new_catalog = proc.create_category(map_category);//Criação de um novo catálogo organizado por género
							ArrayList<Vinyl> list_vinyl_sorted = proc.artist_higher(list_vinyl_proc);//Lista dos artistas cujos albuns tinham melhor rating
							int number_artist;
							System.out.println(
									"Introduza o número de artistas a ser visualizados com highest review scores:");
							while (!sc.hasNextInt()) {//validação do numero de artistas visualizados
								System.out.println(
										"Introduza o número de artistas a ser visualizados com highest review scores:");
								sc.nextInt();
							}
							number_artist = sc.nextInt();
							proc.create_statistics(new_catalog, number_artist, list_vinyl_sorted);//criação das estatisticas 
							
							Marshall new_2 = new Marshall();
							new_2.marshall_2(new_catalog);//Criação de um novo ficheiro XML com os resultados do Processor
							
							System.out.println("A pesquisa foi efetuada com sucesso!");
							System.out.println("Foi criado um ficheiro html!");
							System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
							String escolha = sc.next();
							while (!escolha.toLowerCase().equals("yes") && !escolha.toLowerCase().equals("no")) {
								System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
								escolha = sc.next();
							}
							if (escolha.toLowerCase().equals("yes")) {//Nova pesquisa
								break;
							} else {
								System.out.println("Obrigado! Volte sempre!");//Saída da pesquisa e do programa
								op = false;
								break;
							}
						}

					} else {//Ausencia de resultados
						System.out.println("A sua pesquisa não obteve nenhum resultado!");
						System.out.println("Não foi criado nenhum ficheiro html!");
						System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
						String escolha = sc.next();
						while (!escolha.toLowerCase().equals("yes") && !escolha.toLowerCase().equals("no")) {
							System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
							escolha = sc.next();
						}
						if (escolha.toLowerCase().equals("yes")) {//Nova pesquisa
							break;
						} else {
							System.out.println("Obrigado! Volte sempre!");//Saída da pesquisa e do programa
							op = false;
							break;
						}
					}
				} else if (pesquisa.equals("ou")) {
					List<Vinyl> pre_result = new ArrayList<>();
					
					if (lista_total.size() == 1 && lista_total.get(0).size() != 0) {//Se for feita pesquisa apenas com um critério inserido pelo utilizador e esta obtiver resultados
						pre_result = lista_total.get(0);
						Catalog novo_cat = new Catalog();
						novo_cat.setVinyl(pre_result);//criação de um novo catálogo com os resultado
						marshall.marshall(novo_cat);//criação de um ficheiro XML com o resultado
						
						Catalog catalog_proc = Unmarshall.unmarshall("XML_2");//unmarshall do resultado adquirido pelo Selector e criação de uma lista com os resultados
						
						List<Vinyl> list_proc = catalog_proc.getVinyl();
						ArrayList<Vinyl> list_vinyl_proc = new ArrayList<Vinyl>();
						for (int j = 0; j < list_proc.size(); j++) {
							list_vinyl_proc.add(list_proc.get(j));
						}
						
						
						Processor proc = new Processor();
						Map<ArrayList<ArrayList<Vinyl>>, ArrayList<String>> map_category = proc
								.search_category(list_vinyl_proc);
						Catalognew new_catalog = proc.create_category(map_category);//Criação de um novo catálogo organizado por género 
						ArrayList<Vinyl> list_vinyl_sorted = proc.artist_higher(list_vinyl_proc);//Lista dos artistas cujos albuns tinham melhor rating
						int number_artist;
						System.out.println(
								"Introduza o número de artistas a ser visualizados com highest review scores:");
						while (!sc.hasNextInt()) {
							System.out.println(
									"Introduza o número de artistas a ser visualizados com highest review scores:");
							sc.nextInt();
						}
						
						number_artist = sc.nextInt();
						proc.create_statistics(new_catalog, number_artist, list_vinyl_sorted);//criação das estatisticas 
						Marshall new_2 = new Marshall();//Criação de um novo ficheiro XML com os resultados do Processor
						new_2.marshall_2(new_catalog);
						
						System.out.println("A pesquisa foi efetuada com sucesso!");
						System.out.println("Foi criado um ficheiro html!");
						System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
						String escolha = sc.next();
						while (!escolha.toLowerCase().equals("yes") && !escolha.toLowerCase().equals("no")) {
							System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
							escolha = sc.next();
						}
						if (escolha.toLowerCase().equals("yes")) {//Nova pesquisa
							break;
						} else {
							System.out.println("Obrigado! Volte sempre!");//Saída da pesquisa e do programa
							op = false;
							break;
						}
					} else if (lista_total.size() > 1) {//Se houver mais que um critério de pesquisa, garante que não há resultados repetidos
						ArrayList<Vinyl> list_final = new ArrayList<Vinyl>();
						for (int i = 0; i < lista_total.size(); i++) {
							for (int j = 0; j < lista_total.get(i).size(); j++) {
								list_final.add(lista_total.get(i).get(j));
							}
						}
						for (int i = 0; i < list_final.size(); i++) {
							if (i == list_final.size() - 1) {
								break;
							}
							for (int j = i + 1; j < list_final.size(); j++) {

								if (list_final.get(i).getId().equals(list_final.get(j).getId())) {
									list_final.remove(j);
								}
							}
						}
						pre_result.addAll(list_final);

						if (pre_result.size() == 0) {//Ausência de resultados
							System.out.println("A sua pesquisa não obteve nenhum resultado!");
							System.out.println("Não foi criado nenhum ficheiro html!");
							System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
							String escolha = sc.next();
							while (!escolha.toLowerCase().equals("yes") && !escolha.toLowerCase().equals("no")) {
								System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
								escolha = sc.next();
							}
							if (escolha.toLowerCase().equals("yes")) {//Nova pesquisa
								break;
							} else {
								System.out.println("Obrigado! Volte sempre!");//Saída da pesquisa e do programa
								op = false;
								break;
							}
						}

						else {
							Catalog novo_cat = new Catalog();
							novo_cat.setVinyl(pre_result);//criação de um novo catálogo com os resultado
							marshall.marshall(novo_cat);//criação de um ficheiro XML com o resultado
							
							Catalog catalog_proc = Unmarshall.unmarshall("XML_2");//unmarshall do resultado adquirido pelo Selector e criação de uma lista com os resultados
							List<Vinyl> list_proc = catalog_proc.getVinyl();
							ArrayList<Vinyl> list_vinyl_proc = new ArrayList<Vinyl>();
							for (int j = 0; j < list_proc.size(); j++) {
								list_vinyl_proc.add(list_proc.get(j));
							}
							
							Processor proc = new Processor();
							Map<ArrayList<ArrayList<Vinyl>>, ArrayList<String>> map_category = proc
									.search_category(list_vinyl_proc);
							Catalognew new_catalog = proc.create_category(map_category);//Criação de um novo catálogo organizado por género
							ArrayList<Vinyl> list_vinyl_sorted = proc.artist_higher(list_vinyl_proc);//Lista dos artistas cujos albuns têm melhor rating
							
							int number_artist;
							System.out.println(
									"Introduza o número de artistas a ser visualizados com highest review scores:");
							while (!sc.hasNextInt()) {//validação do numero de artistas visualizados
								System.out.println(
										"Introduza o número de artistas a ser visualizados com highest review scores:");
								sc.nextInt();
							}
							number_artist = sc.nextInt();
							
							proc.create_statistics(new_catalog, number_artist, list_vinyl_sorted);//criação das estatisticas 
							
							Marshall new_2 = new Marshall();
							new_2.marshall_2(new_catalog);//Criação de um novo ficheiro XML com os resultados do Processor
							System.out.println("A pesquisa foi efetuada com sucesso!");
							System.out.println("Foi criado um ficheiro html!");
							System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
							String escolha = sc.next();
							while (!escolha.toLowerCase().equals("yes") && !escolha.toLowerCase().equals("no")) {
								System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
								escolha = sc.next();
							}
							if (escolha.toLowerCase().equals("yes")) {//Nova pesquisa
								break;
							} else {
								System.out.println("Obrigado! Volte sempre!");//Saída da pesquisa e do programa
								op = false;
								break;
							}
						}
						
					} else {//Ausencia de resultados
						System.out.println("A sua pesquisa não obteve nenhum resultado!");
						System.out.println("Não foi criado nenhum ficheiro html!");
						System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
						String escolha = sc.next();
						while (!escolha.toLowerCase().equals("yes") && !escolha.toLowerCase().equals("no")) {
							System.out.println("Deseja fazer uma nova pesquisa? Yes or No:");
							escolha = sc.next();
						}
						if (escolha.toLowerCase().equals("yes")) {//Nova pesquisa
							break;
						} else {
							System.out.println("Obrigado! Volte sempre!");//Saída da pesquisa e do programa
							op = false;
							break;
						}
					}

				}

			}

			if (option.equals("2")) {//Apresenção de todo o catálogo
				ArrayList<Vinyl> lista_teste = new ArrayList<Vinyl>();
				for (int i = 0; i < list_vinyl.size(); i++) {
					lista_teste.add(list_vinyl.get(i));
				}
				
				if (lista_teste.size() != 0) {//Se o catálogo tiver vinyls
					Processor proc = new Processor();
					
					Map<ArrayList<ArrayList<Vinyl>>, ArrayList<String>> map_category = proc
							.search_category(lista_teste);
					Catalognew new_catalog = proc.create_category(map_category);//Criação de um novo catálogo organizado por género
					ArrayList<Vinyl> list_vinyl_sorted = proc.artist_higher(lista_teste);//Lista dos artistas cujos albuns tinham melhor rating
					
					int number_artist;
					System.out.println("Introduza o número de artistas a ser visualizados com highest review scores:");
					while (!sc.hasNextInt()) {//validação do numero de artistas visualizados
						System.out.println(
								"Introduza o número de artistas a ser visualizados com highest review scores:");
						sc.nextInt();
					}
					number_artist = sc.nextInt();

					proc.create_statistics(new_catalog, number_artist, lista_teste);//criação das estatisticas 
					
					Marshall new_2 = new Marshall();
					new_2.marshall_2(new_catalog);//Criação de um novo ficheiro XML com os resultados do Processor
					
					System.out.println("A pesquisa foi efetuada com sucesso!");
					System.out.println("Foi criado um ficheiro html!");
					System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
					String escolha = sc.next();
					while (!escolha.toLowerCase().equals("yes") && !escolha.toLowerCase().equals("no")) {
						System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
						escolha = sc.next();
					}
					if (escolha.toLowerCase().equals("yes")) {//Nova pesquisa
						break;
					} else {
						System.out.println("Obrigado! Volte sempre!");//Saída da pesquisa e do programa
						op = false;
						break;
					}
					
				} else {//Se o catálogo não tiver vinyls
					System.out.println("O catálogo não contem vinyls!");
					System.out.println("Não foi criado nenhum ficheiro html!");
					System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
					String escolha = sc.next();
					while (!escolha.toLowerCase().equals("yes") && !escolha.toLowerCase().equals("no")) {
						System.out.println("Deseja fazer uma nova pesquisa? Se sim, os resultados anteriores não serão guardados. Yes or No:");
						escolha = sc.next();
					}
					if (escolha.toLowerCase().equals("yes")) {//Nova pesquisa
						break;
					} else {
						System.out.println("Obrigado! Volte sempre!");//Saída da pesquisa e do programa
						op = false;
						break;
					}
				}

			} else if (option.equals("3")) {//Sair do menu
				System.out.println("Obrigado! Volte sempre!");
				op = false;
			}

		} while (op == true);
		sc.close();
	}

}
