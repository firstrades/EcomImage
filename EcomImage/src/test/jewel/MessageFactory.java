package test.jewel;

public class MessageFactory {
	
	private static SOAPMessage soapMessage = new SOAPMessageImpl();
	
	private MessageFactory() {}

	public static MessageFactory newInstance() {		
		return new MessageFactory();
	}
	
	public SOAPMessage createMessage() {		
		return soapMessage;
	}

}
