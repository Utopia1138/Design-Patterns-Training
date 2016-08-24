package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;
import org.red.visitor.Context;
import org.red.visitor.Context.Value;

public class DeclarationStatement implements Statement {

	private Context.Type type;
	private final String ident;
	
	public DeclarationStatement( Context.Type type, String ident ) {
		this.type = type;
		this.ident = ident;
	}
	
	@Override
	public Value execute( Context context ) {
		context.push( ident, new Value( type ) );
		return Value.VOID; 
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visit( this );
	}

	public DeclarationStatement setType( Context.Type type ) {
		this.type = type;
		return this;
	}

	public Context.Type type() {
		return type;
	}

	public String ident() {
		return ident;
	}

}
