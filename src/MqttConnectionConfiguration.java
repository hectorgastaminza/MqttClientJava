
public class MqttConnectionConfiguration {
	private String brokerHost = "mqtt.dioty.co";
	private int brokerPort = 1883;
	/* Optional */
	private int brokerWebSocketsPort = 8080;
	/* Optional */
	private String userId = "hectorgastaminza@gmail.com";
	/* Optional */
	private String password = "67b9ee69";
	/* Optional */
	private String rootTopic = "/hectorgastaminza@gmail.com/";

	public String getUsername() {
		return userId;
	}

	public char[] getPassword() {
		return password.toCharArray();
	}

	public String getBrokerURL() {
		return ("tcp://"+brokerHost+":"+String.valueOf(brokerPort));		
	}
	
	public String getRootTopic() {
		return rootTopic;
	}
}
