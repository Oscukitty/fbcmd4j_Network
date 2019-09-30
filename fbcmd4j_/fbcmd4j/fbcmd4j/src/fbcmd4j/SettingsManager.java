package fbcmd4j;


import java.util.Scanner;

public class SettingsManager {

	
	public static String getAppSecret() {
		return "0b7ca36593f57cdc82392d370e86fbea";
	}
	
	public static String getAccessToken() {
		return "EAAB4hGJUj5kBAEFRwbVbsPdZBAqzrcLV1gYhlTcakXzVT4deAxTPbvvH3wJ1ZBFaCnXqz8qzj2x8WCmung2yIjvQHPd0qw972oE6wzrbV4vtZB3GvqILxeH56syoj4IW993lOP2fw4RwQB9vHD8F5V2tr1bKmYs0RZBCaxH9JgZDZD";
	}
	
	public static String getPermissions() {
		return "public_profile,user_actions.news,user_posts,publish_actions";
	}
	
	public static String getAppId() {
		return "132509980725145";
	}
	
	public static String getUrlForToken() {
		//https://www.facebook.com/v2.10/dialog/oauth?client_id=132509980725145&redirect_uri=https://www.facebook.com/connect/login_success.html&scope=public_profile,user_actions.news,user_posts,publish_actions
		return "https://www.facebook.com/v2.10/dialog/oauth?client_id="+getAppId()+"&redirect_uri=https://www.facebook.com/connect/login_success.html&scope=" + getPermissions();
	}
	
	public static String LoginProccess(Scanner scan) {
		System.out.println("Hola!, para acceder debes entrar a la siguiente liga y a continuacion copiar el parametro code aqui (" + getUrlForToken() + "):");
		String code = scan.nextLine();
		String urlToken = "https://graph.facebook.com/oauth/access_token?client_id=" + getAppId() + "&redirect_uri=https://www.facebook.com/connect/login_success.html" + "&client_secret=" + getAppSecret() + "&code=" + code;
		System.out.println("Ahora accede a la url y del JSON pega el dato de acces_token (" + urlToken + "):" );
		String newToken = scan.nextLine();
		setAppSecret(newToken);
		return newToken;
		
	}
	
	public static void setAppSecret(String newValue) {
		//
	}
	
	public static void setAccessToken(String newValue) {
		//
	}
	
	public static void setPermissions(String newValue) {
		// 
	}
	
	public static void setAppId(String newValue) {
		//
	}
}