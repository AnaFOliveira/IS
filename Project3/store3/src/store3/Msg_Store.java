package store3;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jgroups.demos.TotalOrder;

import jpa.Music;
import jpa.Purchase;
import jpa.Vinyl;


public class Msg_Store {
	private ConnectionFactory connectionFactory;
	private Destination destination_store;
	private Destination destination_bank;
	private Destination destination_client;
	private Destination destination_topic;
	
	public Msg_Store() throws NamingException {
		this.connectionFactory = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.destination_store = InitialContext.doLookup("jms/queue/Store7");
		this.destination_bank=InitialContext.doLookup("jms/queue/Bank7");
		this.destination_client=InitialContext.doLookup("jms/queue/Client7");
		this.destination_topic=InitialContext.doLookup("jms/topic/Topic7");

		
	}
	
	private void send_requestPendingOrders(String text, String msg_property, int msg_property_2) {

		try (JMSContext context = connectionFactory.createContext("Project3", "project3")) {
			JMSProducer messageProducer = context.createProducer();
			TextMessage msg = context.createTextMessage();
			msg.setText(text);
			msg.setStringProperty("type", msg_property);// "See_pending");
			msg.setIntProperty("card_numb", msg_property_2);
			messageProducer.send(destination_store, msg);
		} catch (Exception re) {
			re.printStackTrace();
		}
	}

	private void send_requestNewVinyl(Vinyl v) {

		try (JMSContext context = connectionFactory.createContext("Project3", "project3")) {
			JMSProducer messageProducer = context.createProducer();
			ObjectMessage msg = context.createObjectMessage();
			msg.setObject(v);
			msg.setStringProperty("type", "Add_vinyl");// "See_pending");
			messageProducer.send(destination_store, msg);
		} catch (Exception re) {
			re.printStackTrace();
		}
	}

	private List<Purchase> viewPending(int card_number) {
		ObjectMessage msg = null;
		List<Purchase> list_purchase_pending =null;
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSConsumer mc = context.createConsumer(destination_store);
			msg = (ObjectMessage) mc.receive();
			if (msg.getStringProperty("type").equals("pending_orders")
					&& msg.getIntProperty("card_numb") == card_number) {
				list_purchase_pending = (ArrayList<Purchase>) msg.getObject();
			}
		} catch (JMSException re) {
			re.printStackTrace();
		}
		return list_purchase_pending;
	}

	private String confirmation_vinyl() {
		String confirmation = null;
		TextMessage msg = null;
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSConsumer mc = context.createConsumer(destination_store);
			msg = (TextMessage) mc.receive();
			if (msg.getStringProperty("type").equals("vinyl_success")) {
				confirmation = (String) msg.getText();
			}

		} catch (JMSException re) {
			// TODO Auto-generated catch block
			re.printStackTrace();
		}
		return confirmation;

	}

	public static void main(String[] args) throws NamingException {
		boolean run = true;
		Msg_Store msg_store=new Msg_Store();
		functions functions=new functions();
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("Bem vindo! O que pretende fazer?");
			System.out.println("Menu:");
			System.out.println("1-Ver compras pendentes");
			System.out.println("2-Introduzir novo vinyl");
			System.out.println("3- Sair");
			String firstmenu = sc.nextLine();
			if (firstmenu.equals("1")) {
				System.out.println("Introduza o número do cartão que pretende analisar: ");
				int card_number = sc.nextInt();
				msg_store.send_requestPendingOrders("", "pending_orders", card_number);
				functions.Print_Info_Purchase(msg_store.viewPending(card_number));
				;
			}
			else if (firstmenu.equals("2")) {
				System.out.println("Introduza o Título: ");
				String title = sc.nextLine();
				System.out.println("Introduza os Artistas, separados por ';': ");
				String art = sc.nextLine();
				System.out.println("Introduza o Ano: ");
				BigInteger year = sc.nextBigInteger();
				sc.nextLine();
				System.out.println("Introduza o Gênero, separados por ';': ");
				String gen = sc.nextLine();
				System.out.println("Introduza o Rating: ");
				BigDecimal rating = sc.nextBigDecimal();
				sc.nextLine();
				System.out.println("Introduza a Review: ");
				BigInteger review = sc.nextBigInteger();
				sc.nextLine();
				System.out.println("Introduza as Músicas, separadas por ';' : ");
				String musica = sc.nextLine();
				System.out.println("Introduza o Preco: ");
				BigDecimal price = sc.nextBigDecimal();
				sc.nextLine();
				System.out.println("Introduza a Editora: ");
				String label = sc.nextLine();
				System.out.println("Introduza o ASIN: ");
				String id = sc.nextLine();

				String[] artista = art.split(";");
				List<String> artist = new ArrayList<>();
				for (int i = 0; i < artista.length; i++) {
					artist.add(artista[i]);
				}
				String[] genero = gen.split(";");
				List<String> genre = new ArrayList<>();
				for (int i = 0; i < genero.length; i++) {
					genre.add(genero[i]);
				}
				String[] musicas = musica.split(";");
				List<Music> music = new ArrayList<>();
				for (int i = 0; i < musicas.length; i++) {
					Music m = new Music();
					m.setName(musicas[i]);
					music.add(m);
				}
				Vinyl v = new Vinyl();
				v.setArtist(artist);
				v.setGenre(genre);
				v.setTitle(title);
				v.setId(id);
				v.setMusic(music);
				v.setPrice(price);
				msg_store.send_requestNewVinyl(v);
				//System.out.println(msg_store.confirmation_vinyl());
			} else if (firstmenu.equals("3")) {
				run = false;
			}
		} while (run = true);
	}
}
