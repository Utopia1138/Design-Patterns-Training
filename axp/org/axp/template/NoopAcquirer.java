package org.axp.template;

import org.axp.template.xml.XMLBuilder;
import org.w3c.dom.Document;

public class NoopAcquirer extends AbstractAcquirer<String> {
	private int currAuthcode = 10000;

	protected Document acceptEverything() {
		synchronized ( this ) {
			return new XMLBuilder().
				start( "Response" ).
					addChild( "reference", "87654321" ).
					addChild( "authcode", Integer.toString( this.currAuthcode++ ) ).
					addChild( "code", "1" ).
					addChild( "reason", "ACCEPTED" ).
				finish().getDocument();
		}
	}
	
	@Override
	protected String createSaleMessage(Document request) {
		return null;
	}

	@Override
	protected String receiveSaleReply(String saleRequest) {
		return null;
	}

	@Override
	protected String createReversalMessage(Document request) {
		return null;
	}

	@Override
	protected String receiveReversalReply(String reversalRequest) {
		return null;
	}

	@Override
	protected Document generateSaleResponse( String saleMsg, String saleReply, String reversalMsg, String reversalReply ) {
		return acceptEverything();
	}

	@Override
	protected String createRefundMessage(Document request) {
		return null;
	}

	@Override
	protected String receiveRefundReply(String saleRequest) {
		return null;
	}

	@Override
	protected Document generateRefundResponse(String refundMsg, String refundReply) {
		return acceptEverything();
	}

	@Override
	protected Document generateCancelResponse(String reversalMsg, String reversalReply) {
		return acceptEverything();
	}

	@Override
	protected Document generateFulfillResponse(Document request) {
		return acceptEverything();
	}

	@Override
	protected void verifyPreOrAuth(Document request) throws TransactionRejectException {
		System.out.println( "Yeah, that looks like a valid pre or auth request" );
	}

	@Override
	protected void verifyRefund(Document request) throws TransactionRejectException {
		System.out.println( "Yeah, that looks like a valid refund request" );
	}

	@Override
	protected void verifyCancel(Document request) throws TransactionRejectException {
		System.out.println( "Yeah, that looks like a valid cancel request" );
	}

	@Override
	protected void verifyFulfill(Document request) throws TransactionRejectException {
		System.out.println( "Yeah, that looks like a valid fulfill request" );
	}
}
