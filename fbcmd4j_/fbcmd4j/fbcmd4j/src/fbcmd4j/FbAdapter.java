package fbcmd4j;


import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

public class FbAdapter {
	String appId;
	String appSecret;
	String userToken;
	Facebook conn;
	static final Logger log = LogManager.getLogger(FbAdapter.class);
	
	public FbAdapter() throws GenericError {
		super();
		this.appId = SettingsManager.getAppId();
		this.appSecret = SettingsManager.getAppSecret();
		this.userToken = fbcmd4j.SettingsManager.getAccessToken();
		
		try {
			conn = new FacebookFactory().getInstance();
			conn.setOAuthAppId(appId, appSecret);
			conn.setOAuthPermissions(fbcmd4j.SettingsManager.getPermissions());
			conn.setOAuthAccessToken(new AccessToken(userToken));
		} catch(Exception e) {
			log.error("Error de conectividad con facebook");
			throw new fbcmd4j.GenericError("Error de conectividad con facebook");
		}
	}
	
	public FbAdapter(String userToken) throws GenericError {
		super();
		this.appId = SettingsManager.getAppId();
		this.appSecret = SettingsManager.getAppSecret();
		this.userToken = userToken;
		
		try {
			conn = new FacebookFactory().getInstance();
			conn.setOAuthAppId(appId, appSecret);
			conn.setOAuthPermissions(SettingsManager.getPermissions());
			conn.setOAuthAccessToken(new AccessToken(userToken));
		} catch(Exception e) {
			log.error("Error de conectividad con facebook");
			throw new GenericError("Error de conectividad con facebook");
		}
	}
	
	public void changeUser(String newToken) {
		conn.setOAuthAccessToken(new AccessToken(userToken));
	}
	
	private void imprimirPost(Post p) {
		String name = p.getName();
		String story = p.getStory();
		String msg = p.getMessage();
		if(name == null) {
			name = "";
		}
		if(story == null) {
			story = "";
		}
		if(msg == null) {
			msg = "";
		}
		System.out.println("================================");
		System.out.println("Nombre: " + name);
		System.out.println("Historia: " + story);
		System.out.println("Mensaje: " + msg);
		System.out.println("================================");
	}
	
	public void verNewsFeed() {
		
		try {
			System.out.println("Mostrando newsfeed de " + conn.getMe().getName());
			ResponseList<Post> p = conn.getFeed();
			for(Post currentP : p) {
				imprimirPost(currentP);
			}
		} catch (FacebookException e) {
			log.error(e);
			System.err.println("Error de conectividad");
		}
	}
	
	public void verWall() {
		try {
			System.out.println("Mostrando wall de " + conn.getMe().getName());
			ResponseList<Post> p = conn.getPosts();
			for(Post currentP : p) {
				imprimirPost(currentP);
			}
		} catch (FacebookException e) {
			log.error(e);
			System.err.println("Error de conectividad");
		}
		
	}
	
	public void publicar(String mensaje) {
		try {
			conn.postStatusMessage(mensaje);
			System.out.println("Se publico correctamente");
		} catch (FacebookException e) {
			log.error(e);
			System.err.println("Error de conectividad");
		}
	}
	
	public void publicarLink(URL url) {
		try {
			conn.postLink(url);
			System.out.println("Se publico correctamente");
		} catch (FacebookException e) {
			log.error(e);
			System.err.println("Error de conectividad");
		}
	}
}