package store3;

import java.util.List;

import jpa.Payment;
import jpa.Purchase;

public class functions {
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
