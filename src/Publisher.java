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

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.*;

/**
 * The Class Publisher.
 * @author Yasith Lokuge
 */
public class Publisher {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void launch(String[] args) {
		String topic = "temperature";
		String content = (args == null) ? "32" : args[0];
		int qos = 0;//2;
		String clientId;
		MemoryPersistence persistence = new MemoryPersistence();
		MqttConnectionConfiguration myConfiguration = new MqttConnectionConfiguration();
		topic = myConfiguration.getRootTopic() + topic;

		try {
			clientId = MqttClient.generateClientId();
			MqttClient sampleClient = new MqttClient(myConfiguration.getBrokerURL(), clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setUserName(myConfiguration.getUsername());
			connOpts.setPassword(myConfiguration.getPassword());	
			sampleClient.connect(connOpts);
			if(sampleClient.isConnected())
			{
				sampleClient.publish(topic, content.getBytes(), qos, true);
				sampleClient.disconnect();
			}
			else
			{
				System.out.println("Client is not connect");
			}
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}
}