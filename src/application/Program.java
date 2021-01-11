package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			System.out.println("\nEntre com os dados do cliente.");

			System.out.print("Nome: ");
			String name = input.nextLine();
			System.out.print("Email: ");
			String email = input.next();
			System.out.print("Data de nascimento (DD/MM/YYYY): ");
			Date birthDate = sdf.parse(input.next());

			Client client = new Client(name, email, birthDate);

			System.out.println("\nEntre com os dados do pedido.");
			System.out.print("Status: ");
			OrderStatus status = OrderStatus.valueOf(input.next());

			Order order = new Order(new Date(), status, client);

			System.out.print("\nQuantos itens terão o pedido? ");
			int qtdOrder = input.nextInt();

			for (int i = 1; i <= qtdOrder; i++) {
				System.out.println("\nEntre com os dados do " + i + "º item.");

				System.out.print("Nome do produto: ");
				input.nextLine();
				String productName = input.nextLine();
				System.out.print("Preço do produto: R$ ");
				double productPrice = input.nextDouble();
				System.out.print("Quantidade: ");
				int itemsQtd = input.nextInt();

				OrderItem item = new OrderItem(itemsQtd, productPrice, new Product(productName, productPrice));

				order.addItems(item);
			}
			System.out.println("\n\nSUMÁRIO DO PEDIDO.");
			System.out.println(order);
			
		} catch (RuntimeException e) {
			System.out.println("Você inseriu um tipo de dado inapropriado para a operação!");
			System.out.println("Reinicie o programa para tentar novamente.");
		} catch (ParseException e) {
			System.out.println("Não foi possível converter a data de nascimento do cliente.");
			System.out.println("Certifique-se de seguir o parametro (DD/MM/YYYY)");
			System.out.println("Reinicie o programa para tentar novamente.");
		} finally {
			input.close();
		}
	}
}
