package webGui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import core.*;
import ocsf.client.AbstractClient;
import webGui.util.ServerMessageHandler;

public class MockWebClientConnectionManager extends AbstractClient {
	private static MockWebClientConnectionManager instance;
	final private static int DEFAULT_PORT = 5555;
	final private static String DEFAULT_HOST = "localhost";
	final private Gson gson = new Gson();
	private List<ServerMessageHandler> listeners = new ArrayList<ServerMessageHandler>();
	
	private MockWebClientConnectionManager() throws IOException {
		super(DEFAULT_HOST, DEFAULT_PORT);
		openConnection();
	}
	
	public static MockWebClientConnectionManager getInstance(){
		if(instance == null){
			try {
				return new MockWebClientConnectionManager();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}
	
//	Observer pattern
	public void addServerMessageListener(ServerMessageHandler listner){
		listeners.add(listner);
	}

	@Override
	protected void handleMessageFromServer(Object arg0) {
		notifyListeners((String)arg0);
	}

	public void sendMessageToServer(WebCustomerRequest order) {
		try {
			sendToServer(gson.toJson(order));
		} catch (IOException e) {
			notifyListeners("Could not send message to server.  Terminating client.");
			quit();
		}
	}
	
	public WebCustomerRequest CreateOrder(){
//		Add parameters as needed or change implementation.
		return new WebCustomerRequest();
	}

	private void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
			
		}
		
		System.exit(0);
	}
	
	private void notifyListeners(String message){
//		Observer pattern. Send message from the model to the controller (We don't want the model to reference the WebClientMockController
//		because the controller references the model and that could end up in circular reference).
		for (ServerMessageHandler listener : listeners){
			listener.handleServerMessage(message);
		}
	}
}