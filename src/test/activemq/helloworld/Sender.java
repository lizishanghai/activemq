package test.activemq.helloworld;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	
	public static void main(String[] args) throws JMSException{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
			/*	ActiveMQConnectionFactory.DEFAULT_USER, 
				ActiveMQConnectionFactory.DEFAULT_PASSWORD,*/
				"bhz","bhz",
				"tcp://localhost:61616");
		
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		
		Destination destDestination = session.createQueue("queue1");
		
		MessageProducer messageProducer = session.createProducer(destDestination);
		
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		for(int i=0;i<5;i++){
			TextMessage textMessage = session.createTextMessage("我是消息内容, id为: " + i);
			messageProducer.send(textMessage);
			System.out.println("生产者：" + textMessage.getText());
		}
		
		if(connection != null){
			connection.close();
		}
		
	}

}
 