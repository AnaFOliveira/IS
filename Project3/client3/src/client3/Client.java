package client3;

import java.io.Serializable;
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
import org.xnio.Option;

import jpa.Bank;
import jpa.Payment;
import jpa.Purchase;
import jpa.Vinyl;

public class Client {
	private ConnectionFactory connectionFactory;
	private Destination destination_store;
	private Destination destination_bank;
	private Destination destination_client;
	private Destination destination_topic;
	private int card_num;
	private String nome;

	public Client(int card_num, String nome) throws NamingException {
		this.card_num = card_num;
		this.nome = nome;
		this.connectionFactory = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.destination_store = InitialContext.doLookup("jms/queue/Store7");
		this.destination_bank = InitialContext.doLookup("jms/queue/Bank7");
		this.destination_client = InitialContext.doLookup("jms/queue/Client7");
		this.destination_topic=InitialContext.doLookup("jms/topic/Topic7");

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCard_num() {
		return card_num;
	}

	public void setCard_num(int card_num) {
		this.card_num = card_num;
	}

	private void send_checkBankuser(int card_number, String msg_property) {
		try (JMSContext context = connectionFactory.createContext("Project3", "project3")) {
			JMSProducer messageProducer = context.createProducer();
			TextMessage msg = context.createTextMessage();
			msg.setText("Check user");
			msg.setIntProperty("card_number", card_number);
			msg.setStringProperty("type", msg_property);
			messageProducer.send(destination_bank, msg);
		} catch (Exception re) {
			re.printStackTrace();
		}
	}

	private String receive_checkBankUser() {
		TextMessage msg = null;
		String status = null;
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSConsumer mc = context.createConsumer(destination_client);
			msg = (TextMessage) mc.receive();
			if (msg.getStringProperty("type").equals("bank_user_checked")) {
				int card_number = msg.getIntProperty("card_numb");
				status = (String) msg.getText();
			}

		} catch (JMSException re) {
			// TODO Auto-generated catch block
			re.printStackTrace();
		}

		return status;
	}

	private void send_newbank(List<String> info, String msg_property) {
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSProducer messageProducer = context.createProducer();
			ObjectMessage msg = context.createObjectMessage();
			msg.setObject((Serializable) info);
			msg.setStringProperty("type", msg_property);
			messageProducer.send(destination_bank, msg);
		} catch (Exception re) {
			re.printStackTrace();
		}
	}

	private void send_request_vinyls(String text, String msg_property, int msg_property2) {

		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSProducer messageProducer = context.createProducer();
			TextMessage msg = context.createTextMessage();
			msg.setText(text);
			msg.setStringProperty("type", msg_property);
			msg.setIntProperty("card_numb", msg_property2);

			messageProducer.send(destination_store, msg);
		} catch (Exception re) {
			re.printStackTrace();
		}
	}

	private ArrayList<Vinyl> receive_vinyls(int msg_property_2) {
		ObjectMessage msg = null;
		ArrayList<Vinyl> list_vinyl = null;
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSConsumer mc = context.createConsumer(destination_client);

			msg = (ObjectMessage) mc.receive();
			System.out.println((ArrayList<Vinyl>) msg.getObject());
			if (msg.getStringProperty("type").equals("all_vinyls")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				list_vinyl = (ArrayList<Vinyl>) msg.getObject();
			}

			if (msg.getStringProperty("type").equals("title_vinyls")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				list_vinyl = (ArrayList<Vinyl>) msg.getObject();
			}

			if (msg.getStringProperty("type").equals("genre_vinyls")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				list_vinyl = (ArrayList<Vinyl>) msg.getObject();
			}
			if (msg.getStringProperty("type").equals("artist_vinyls")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				list_vinyl = (ArrayList<Vinyl>) msg.getObject();
			}

		} catch (JMSException re) {
			re.printStackTrace();
		}
		return list_vinyl;
	}

	private void send_request_purchases(String text, String msg_property, int msg_property2) {
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSProducer messageProducer = context.createProducer();
			TextMessage msg = context.createTextMessage();
			msg.setText(text);
			msg.setStringProperty("type", msg_property);
			msg.setIntProperty("card_numb", msg_property2);

			messageProducer.send(destination_store, msg);
		} catch (Exception re) {
			re.printStackTrace();
		}

	}

	private ArrayList<Purchase> receive_purchases(int msg_property_2) {
		ObjectMessage msg = null;
		ArrayList<Purchase> list_purchases = null;
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSConsumer mc = context.createConsumer(destination_client);

			msg = (ObjectMessage) mc.receive();
			System.out.println((ArrayList<Purchase>) msg.getObject());
			if (msg.getStringProperty("type").equals("completed_purchases")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				list_purchases = (ArrayList<Purchase>) msg.getObject();
			}

			if (msg.getStringProperty("type").equals("pending_purchases")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				list_purchases = (ArrayList<Purchase>) msg.getObject();
			}

		} catch (JMSException re) {
			re.printStackTrace();
		}
		return list_purchases;
	}

	private void send_request_credit(String text, String msg_property, int msg_property2) {
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSProducer messageProducer = context.createProducer();
			TextMessage msg = context.createTextMessage();
			msg.setText(text);
			msg.setStringProperty("type", msg_property);
			msg.setIntProperty("card_numb", msg_property2);

			messageProducer.send(destination_bank, msg);
		} catch (Exception re) {
			re.printStackTrace();
		}

	}

	private double receive_credit(int msg_property_2) {
		ObjectMessage msg = null;
		Double credit = null;
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSConsumer mc = context.createConsumer(destination_client);
			msg = (ObjectMessage) mc.receive();
			if (msg.getStringProperty("type").equals("bank_credit")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				credit = (Double) msg.getObject();

			}
		} catch (JMSException re) {
			re.printStackTrace();
		}
		return credit;
	}

	private void send_request_deposit(double amount, String msg_property, int msg_property2) {
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSProducer messageProducer = context.createProducer();
			ObjectMessage msg = context.createObjectMessage();
			msg.setObject(amount);
			msg.setStringProperty("type", msg_property);
			msg.setIntProperty("card_numb", msg_property2);

			messageProducer.send(destination_bank, msg);
		} catch (Exception re) {
			re.printStackTrace();
		}

	}

	private String receive_deposit_update(int msg_property_2) {
		TextMessage msg = null;
		String info = null;
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSConsumer mc = context.createConsumer(destination_client);
			msg = (TextMessage) mc.receive();
			if (msg.getStringProperty("type").equals("bank_deposit")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				info = (String) msg.getText();

			}
		} catch (JMSException re) {
			re.printStackTrace();
		}
		return info;
	}

	private void send_request_new_purchase(String list_order, String msg_property, int msg_property2) {

		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSProducer messageProducer = context.createProducer();
			TextMessage msg = context.createTextMessage();
			msg.setText(list_order);
			msg.setStringProperty("type", msg_property);
			msg.setIntProperty("card_numb", msg_property2);

			messageProducer.send(destination_store, msg);
		} catch (Exception re) {
			re.printStackTrace();
		}
	}
	
	
	private String receive_new_purchase(int msg_property_2) {
		TextMessage msg = null;
		String status = null;
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSConsumer mc = context.createConsumer(destination_client);
			msg = (TextMessage) mc.receive();
			if (msg.getStringProperty("type").equals("purchase_success")&& msg.getIntProperty("card_numb") == msg_property_2) {
				status = (String) msg.getText();
			}
			
			if (msg.getStringProperty("type").equals("purchase_failure")&& msg.getIntProperty("card_numb") == msg_property_2) {
				status = (String) msg.getText();
			}

		} catch (JMSException re) {
			// TODO Auto-generated catch block
			re.printStackTrace();
		}

		return status;
	}
	
	private String receive_payment_info(int msg_property_2) {
		TextMessage msg = null;
		String status = null;
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSConsumer mc = context.createConsumer(destination_client);
			msg = (TextMessage) mc.receive();
			if (msg.getStringProperty("type").equals("payment_success")&& msg.getIntProperty("card_numb") == msg_property_2) {
				status = (String) msg.getText();
			}
			
			if (msg.getStringProperty("type").equals("payment_pending")&& msg.getIntProperty("card_numb") == msg_property_2) {
				status = (String) msg.getText();
			}

		} catch (JMSException re) {
			// TODO Auto-generated catch block
			re.printStackTrace();
		}

		return status;
	}
	
	
	

	public static void main(String[] args) throws NamingException {
		boolean run = true;
		boolean login = true;
		functions functions = new functions();
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Bem vindo! De modo a utilizar todas as funcionalidades, é necessário fazer o login ou efetuar um novo registo!");

		System.out.println("Introduza o seu nome: ");
		String name = sc.nextLine();
		System.out.println("Introduza o seu número do cartão de crédito:");
		int card_number = sc.nextInt();
		Client client = new Client(card_number, name);
		client.send_newbank(functions.info_newbank(client.getNome(), client.getCard_num()), "new_bank");
		sc.nextLine();
		do {
			System.out.println("Menu:");
			System.out.println("1-Consultar todos os vinyls na store");
			System.out.println("2-Pesquisar vinyls");
			System.out.println("3-Realizar uma compra");
			System.out.println("4-Consultar compras completas");
			System.out.println("5-Consultar compras pendentes");
			System.out.println("6-Consultar saldo do cartão");
			System.out.println("7-Realizar um depósito");
			String option = sc.nextLine();
			if (option.equals("1")) {
				client.send_request_vinyls("Todos os Vinyls", "all_vinyls", client.getCard_num());
				functions.Print_Info_Vinyl(client.receive_vinyls(client.getCard_num()));
			}
			if (option.equals("2")) {
				System.out.println("1-Pesquisa por título");
				System.out.println("2-Pesquisa por artista");
				System.out.println("3-Pesquisa por genero");
				String option_search = sc.nextLine();
				if (option_search.equals("1")) {
					String titulo;
					System.out.println("Introduza o título do album");
					titulo = sc.nextLine();
					client.send_request_vinyls(titulo.toLowerCase(), "title_vinyls", client.getCard_num());
					functions.Print_Info_Vinyl(client.receive_vinyls(client.getCard_num()));

				}

				else if (option_search.equals("2")) {
					String artist;
					System.out.println("Introduza os artistas separados por ; ");
					artist = sc.nextLine();
					client.send_request_vinyls(artist.toLowerCase(), "artist_vinyls", client.getCard_num());
					functions.Print_Info_Vinyl(client.receive_vinyls(client.getCard_num()));

				} else if (option_search.equals("3")) {
					String genre;
					System.out.println("Introduza os genres seprados por ;");
					genre = sc.nextLine();
					client.send_request_vinyls(genre.toLowerCase(), "genre_vinyls", client.getCard_num());
					functions.Print_Info_Vinyl(client.receive_vinyls(client.getCard_num()));

				}
			} else if (option.equals("3")) {
				String list_order;
				System.out.println("Introduza os títulos dos vinyls que prentende comprar separados por ;:");
				list_order=sc.nextLine();
				client.send_request_new_purchase(list_order,"new_purchase",client.getCard_num());
				String result=client.receive_new_purchase(client.getCard_num());
				if (result.equals("Pedido registado com sucesso")==false) {
					System.out.println(result);
				}
				else {
					System.out.println(result);
					System.out.println(client.receive_payment_info(client.getCard_num()));

				}
				
			}

			else if (option.equals("4")) {
				client.send_request_purchases("Visualizar Compras Concluídas", "completed_purchases",
						client.getCard_num());
				functions.Print_Info_Purchase(client.receive_purchases(client.getCard_num()));
			}

			else if (option.equals("5")) {
				client.send_request_purchases("Visualizar Compras Pendentes", "pending_purchases",
						client.getCard_num());
				functions.Print_Info_Purchase(client.receive_purchases(client.getCard_num()));
			}

			else if (option.equals("6")) {
				client.send_request_credit("Visualizar saldo", "bank_credit", client.getCard_num());
				System.out.println("Saldo" + " " + client.receive_credit(client.getCard_num()));

			} else if (option.equals("7")) {
				double amount;
				System.out.println("Introduza o valor do deposito a realizar:");
				amount = sc.nextDouble();
				client.send_request_deposit(amount, "bank_deposit", client.getCard_num());
				System.out.println(client.receive_deposit_update(client.getCard_num()));

			}
			

		} while (run);

	}

}
