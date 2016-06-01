package test.jewel;

public class SOAPMessageImpl extends SOAPMessage {
	
	private static SOAPPart soapPart = new SOAPPart();

	public SOAPPart getSOAPPart() {		
		return soapPart;
	}
}
