/*******************************************************************************
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

//package mqttClientJava;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * The Class Listener.
 * 
 * @author Yasith Lokuge
 */
public class Listener implements MqttCallback {

	private static final String topic = "temperature";
	private String clientId; 
	MemoryPersistence persistence;
	MqttClient sampleClient;
	MqttConnectOptions connOpts;
	MqttConnectionConfiguration myConfiguration;
	

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static Listener launch(String[] args) {
		Listener myListener = new Listener();
		
		myListener.subscribe(args == null ? topic : args[0]);
		
		return myListener;
	}

	/**
	 * Subscribe.
	 *
	 * @param topic
	 *            the topic
	 */
	public void subscribe(String topic) {
		persistence = new MemoryPersistence();
		myConfiguration = new MqttConnectionConfiguration();
		topic = myConfiguration.getRootTopic() + topic;
		
		try {
			clientId = MqttClient.generateClientId();
			sampleClient = new MqttClient(myConfiguration.getBrokerURL(), clientId, persistence);
			connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setUserName(myConfiguration.getUsername());
			connOpts.setPassword(myConfiguration.getPassword());
			
			System.out.println("checking");

			sampleClient.connect(connOpts);
			System.out.println("Mqtt Connected");

			sampleClient.setCallback(this);
			sampleClient.subscribe(topic);

			System.out.println("Subscribed");
			System.out.println("Listening");
		} catch (MqttException me) {
			System.out.println("Mqtt reason " + me.getReasonCode());
			System.out.println("Mqtt msg " + me.getMessage());
			System.out.println("Mqtt loc " + me.getLocalizedMessage());
			System.out.println("Mqtt cause " + me.getCause());
			System.out.println("Mqtt excep " + me);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.paho.client.mqttv3.MqttCallback#connectionLost(java.lang.
	 * Throwable)
	 */
	public void connectionLost(Throwable arg0) {
		System.out.println("connectionLost : " + topic);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.paho.client.mqttv3.MqttCallback#deliveryComplete(org.eclipse.
	 * paho.client.mqttv3.IMqttDeliveryToken)
	 */
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		System.out.println("deliveryComplete : " + topic);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.paho.client.mqttv3.MqttCallback#messageArrived(java.lang.
	 * String, org.eclipse.paho.client.mqttv3.MqttMessage)
	 */
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Mqtt topic : " + topic);
		System.out.println("messageArrived : " + message.toString());
	}

}