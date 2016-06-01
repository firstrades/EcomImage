package test.jewel;

public class Implement {

	public static void main(String[] args) {
		
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		
		@SuppressWarnings("unused")
		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
	}

}
