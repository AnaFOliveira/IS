package mdb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import jpa.Payment;
import jpa.Purchase;
import jpa.Vinyl;
import session.SessionStoreRemote;
import session.ejbremote;

/**
 * Message-Driven Bean implementation class for: mdb_store
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "Store7"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "Store7")
public class mdb_store implements MessageListener {
	@EJB (beanName="Session_store")
	private SessionStoreRemote store_remote;
	
	@EJB(beanName = "ejb")
	private ejbremote ejb;

	/**
	 * Default constructor.
	 */
	public mdb_store() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			if (message.getStringProperty("type").equals("all_vinyls")) {
				System.out.println("Chegou pedido para ver todos os vinyls");
				int card_number=message.getIntProperty("card_numb");
				String title=null;
				List<String> genre=null;
				List<String> artist=null;
				store_remote.sendToClientVinyls("all", card_number, title, genre, artist);
			}
			if (message.getStringProperty("type").equals("title_vinyls")) {
				System.out.println("Chegou o pedido para ver os vinyls por titulo");
				String title=message.getBody(String.class);
				int card_number=message.getIntProperty("card_numb");
				List<String> genre=null;
				List<String> artist=null;
				store_remote.sendToClientVinyls("title", card_number, title, genre, artist);
				
			}
			if (message.getStringProperty("type").equals("artist_vinyls")) {
				System.out.println("Chegou o pedido para ver os vinyls por artista");
				String artist=message.getBody(String.class);
				String[] artists=artist.split(";");
				List<String> list_artists=new ArrayList<>();
				for (int i = 0; i < artists.length; i++) {
					list_artists.add(artists[i]);
					
				}
				int card_number=message.getIntProperty("card_numb");
				List<String> genre=null;
				String title=null;
				store_remote.sendToClientVinyls("artist", card_number, title, genre, list_artists);
				
			}
			if (message.getStringProperty("type").equals("genre_vinyls")) {
				System.out.println("Chegou o pedido para ver os vinyls por genre");
				String genre=message.getBody(String.class);
				String[] genres=genre.split(";");
				List<String> list_genres=new ArrayList<>();
				for (int i = 0; i < genres.length; i++) {
					list_genres.add(genres[i]);
					
				}
				int card_number=message.getIntProperty("card_numb");
				List<String> artist=null;
				String title=null;
				store_remote.sendToClientVinyls("genre", card_number, title, list_genres, artist);
				
			}
			if(message.getStringProperty("type").equals("completed_purchases")) {
				System.out.println("Chegou o pedido para ver as compras completas");
				int card_number=message.getIntProperty("card_numb");
				store_remote.sendToClientPurchase("completed",card_number);
				
			}
			
			if(message.getStringProperty("type").equals("pending_purchases")) {
				System.out.println("Chegou o pedido para ver as compras pendentes");
				int card_number=message.getIntProperty("card_numb");
				store_remote.sendToClientPurchase("pending",card_number);
				
			}
			
			if(message.getStringProperty("type").equals("pending_orders")) {
				System.out.println("Chegou o pedido para ver as orders pendentes");
				int card_number=message.getIntProperty("card_numb");
				store_remote.sendToStore_pendingOrders(card_number);
				
			}
			
			if(message.getStringProperty("type").equals("Add_vinyl")) {
				System.out.println("Chegou o pedido para adicionar novo vinyl");
				Vinyl vinyl=message.getBody(Vinyl.class);
				ejb.new_vinyl(vinyl);
				store_remote.send_news_topic("Novo Vinyl Disponivel");
				store_remote.sendToStore_Vinyl("Vinyl Adicionado com Sucesso");
				
			}
			
			if(message.getStringProperty("type").equals("new_purchase")) {
				System.out.println("Chegou o pedido de nova compra");
				int card_number=message.getIntProperty("card_numb");
				String[] new_purchase=message.getBody(String.class).split(";");
				List<Vinyl> list_vinyls=new ArrayList<>();
				for (int i = 0; i < new_purchase.length; i++) {
					list_vinyls.addAll(ejb.albumByTitle(new_purchase[i].toLowerCase()));
				}
				if (list_vinyls.size()<new_purchase.length) {
					store_remote.sendToClientInfo("purchase_failure",card_number);
				}
				else {
					store_remote.sendToClientInfo("purchase_success",card_number);
					List<String> title=new ArrayList<>();
					String date_time=new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
					double amount=0;
					for (int i = 0; i < list_vinyls.size(); i++) {
						title.add(list_vinyls.get(i).getTitle());
						amount=amount+list_vinyls.get(i).getPrice().doubleValue();
					}
					Purchase purchase=new Purchase();
					Payment payment=new Payment();
					purchase.setAmount(amount);
					payment.setAmount(amount);
					purchase.setCard_number(card_number);
					payment.setStatus("pending");
					purchase.setDate_time(date_time);
					payment.setDate_time(date_time);
					purchase.setStatus("pending");
					purchase.setVinyl_title(title);
					payment.setInfo("");
					ejb.store_purchase_order(purchase);
					store_remote.sendToBankPayment(card_number, payment);
				}
			}
			
			if (message.getStringProperty("type").equals("purchase_update")) {
				System.out.println("Chegou o resultado do pagamento");
				int card_number=message.getIntProperty("card_numb");
				List<String> ids=message.getBody(ArrayList.class);
				if (ids.size()==0) {
					store_remote.sendToClientInfo("payment_pending", card_number);
				}
				
				else {
					long id=Long.valueOf(ids.get(0));
					ejb.store_update_purchase_order("completed", id);
					store_remote.sendToClientInfo("payment_success", card_number);
				}
			}
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//@EJB
	//private SessionBankRemote bank_sender;
	
//	@EJB (beanName="SessionStoreRemote")
//    private SessionStoreRemote sender_store;
//	
//
//	@Resource(mappedName = "java:jboss/exported/jms/queue/Client")
//	private Destination destination;


}