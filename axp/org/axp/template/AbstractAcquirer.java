package org.axp.template;

import org.axp.template.xml.XMLBuilder;
import org.axp.template.xml.XMLQuery;
import org.w3c.dom.Document;

public abstract class AbstractAcquirer<E> {
	public Document processTransaction( Document request ) {
		String method = XMLQuery.get( "/Request/Transaction/method", request );
		
		if ( method == null ) {
			method = "null";
		}
		
		E saleMsg = null, saleReply = null, reversalMsg = null, reversalReply = null;
		
		
		try {
			switch (method) {
			case "auth":
			case "pre":
				verifyPreOrAuth( request );
				
				saleMsg = createSaleMessage( request );
				saleReply = receiveSaleReply( saleMsg );
				
				if ( inlineReversalRequired( saleReply ) ) {
					reversalMsg = createReversalMessage( request );
					reversalReply = receiveReversalReply( reversalMsg );
				}
				
				return generateSaleResponse( saleMsg, saleReply, reversalMsg, reversalReply );
			case "refund":
				verifyRefund( request );
				E refundMsg = createRefundMessage( request );
				E refundReply = receiveRefundReply( refundMsg );
				
				return generateRefundResponse( refundMsg, refundReply );
			case "cancel":
				verifyCancel( request );
				reversalMsg = createReversalMessage( request );
				reversalReply = receiveReversalReply( reversalMsg );
				
				return generateCancelResponse( reversalMsg, reversalReply );
			case "fulfill":
				verifyFulfill( request );
				return generateFulfillResponse( request );
				
			/*
			 * TODO: Plenty more methods here...
			 */
			
			default:
				throw new TransactionRejectException( 63, "No such method '" + method + "'" );
			}
		
		}
		catch ( TransactionRejectException e ) {
			return generateErrorResponse( request, e.getCode(), e.getMessage() );
		}
	}
	

	protected Document generateErrorResponse( Document request, int errorCode, String errorMessage ) {
		return new XMLBuilder().
			start( "Response" ).
				addChild( "status", Integer.toString( errorCode ) ).
				addChild( "reason", errorMessage ).
			finish().getDocument();
	}

	protected boolean inlineReversalRequired( E saleResponse ) {
		return false;
	}

	// A bunch of verify methods
	protected abstract void verifyPreOrAuth(Document request) throws TransactionRejectException;
	protected abstract void verifyRefund(Document request) throws TransactionRejectException;
	protected abstract void verifyCancel(Document request) throws TransactionRejectException;
	protected abstract void verifyFulfill(Document request) throws TransactionRejectException;
	
	// A bunch of message-building methods
	protected abstract E createSaleMessage( Document request );
	protected abstract E createReversalMessage( Document request );
	protected abstract E createRefundMessage( Document request );
	
	// A bunch of reply-receiving methods
	protected abstract E receiveSaleReply( E saleRequest ) throws TransactionRejectException;
	protected abstract E receiveReversalReply( E reversalRequest ) throws TransactionRejectException;
	protected abstract E receiveRefundReply( E saleRequest ) throws TransactionRejectException;
	
	// A bunch of response-generating methods
	protected abstract Document generateSaleResponse( E saleMsg, E saleReply, E reversalMsg, E reversalReply );
	protected abstract Document generateRefundResponse( E refundMsg, E refundReply );
	protected abstract Document generateCancelResponse( E refundMsg, E refundReply );
	protected abstract Document generateFulfillResponse( Document request );
}
