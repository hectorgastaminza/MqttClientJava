import java.util.Scanner;

/**
 * 
 * @author hecto
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);
		final String topic = "temperature";
		MqttConnectionConfiguration myConfiguration = new MqttConnectionConfiguration();
		MqttListener myListener = null;
		MqttPublisher myPublisher = null;

		int option = 99;
		int value = 30;
		
		while (option != 0)
		{
			System.out.println("Enter option: ");
			System.out.println("0 - Exit");
			System.out.println("1 - Publish new data");
			if(myListener == null)
			{
				System.out.println("2 - Launch listener");
			}
			
			option = myScanner.nextInt();
			
			switch (option) {
			// Publish
			case 1:
				if(myPublisher == null)
				{
					myPublisher = new MqttPublisher(myConfiguration);
				}
				myPublisher.publish(topic, String.valueOf(value++));
				break;
			// Listener
			case 2:
				if(myListener == null)
				{
					myListener = new MqttListener(topic, myConfiguration, new MqttMessageReceiver());
				}
				break;
			}
		}		
		System.out.println("Application end.");
	}

}

