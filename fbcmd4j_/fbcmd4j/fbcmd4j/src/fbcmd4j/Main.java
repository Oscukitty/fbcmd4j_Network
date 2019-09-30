package fbcmd4j;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import facebook4j.FacebookException;

public class Main {
	static final Logger log = LogManager.getLogger(Main.class);
	
	
	public static void main(String[] args) throws FacebookException, IOException, GenericError {
		FbAdapter fb = new FbAdapter();
		Scanner input = new Scanner(System.in);
		boolean next = true;

		while (next) {
			System.out.println("Cliente de FB v1.0");
			System.out.println("Menu:");
			System.out.println("\t1 - Configurar usuario");
			System.out.println("\t2 - Ver usuario");
			System.out.println("\t3 - Ver newsfeed");
			System.out.println("\t4 - Ver wall");
			System.out.println("\t5 - Publicar Estado");
			System.out.println("\t6 - Publicar link");
			System.out.println("\t7 - Salir");
			String op = input.nextLine();

			switch (op) {
			case "1":
				String newToken = SettingsManager.LoginProccess(input);
				fb = new FbAdapter(newToken);
				break;
			case "2":
				System.out.println("Hola " + fb.conn.getMe().getName());
				break;
			case "3":
				fb.verNewsFeed();
				break;
			case "4":
				fb.verWall();
				break;
			case "5":
				System.out.println("Nuevo estado:");
				String mensaje = input.nextLine();
				fb.publicar(mensaje);
				break;
			case "6":
				System.out.println("Nuevo estado:");
				String urlString = input.nextLine();
				try {
					URL url = new URL(urlString);
					fb.publicarLink(url);					
				} catch(MalformedURLException e) {
					log.error("La url introducida no es valida");
				} catch(Exception e) {
					log.error("Error de conectividad");
				}
				
				break;
			case "7":
				next = false;
				break;
			default:
				System.out.println("Opcion o valida");
				break;
			}
		}
	}
}