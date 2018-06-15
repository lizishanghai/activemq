package test.activemq.helloworld;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	public static void main(String[] args) throws JMSException{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				/*ActiveMQConnectionFactory.DEFAULT_USER, 
				ActiveMQConnectionFactory.DEFAULT_PASSWORD,*/
				"bhz","bhz",
				"tcp://localhost:61616");
		
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		
		Destination destDestination = session.createQueue("queue1");
		
		MessageConsumer messageConsumer = session.createConsumer(destDestination);
		
		while(true){
			TextMessage msg = (TextMessage) messageConsumer.receive();
			if(msg == null) break;
			System.out.println("�յ������ݣ�" + msg.getText());
		}
		
		if(connection != null){
			connection.close();
		}
		
	}
}
