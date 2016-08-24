package org.red.visitor;

import java.util.HashMap;
import java.util.Map;

import org.red.visitor.Context.Type;
import org.red.visitor.ast.AssignmentStatement;
import org.red.visitor.ast.DeclarationStatement;

public class TypeInferenceVisitor implements ASTVisitor {
	private final Map<String, DeclarationStatement> inferred = new HashMap<>();
	private final Map<String, Type> assignments = new HashMap<>();

	@Override
	public void visit( DeclarationStatement decl ) {
		if ( decl.type() == Type.INFERRED ) {
			inferred.put( decl.ident(), decl );
		}
	}

	@Override
	public void visitPost( AssignmentStatement assign ) {
		// Assignment to this value already made and types differ!
		// In future this could do coercion checks to see if types are implicitly translatable
		if ( assignments.containsKey( assign.ident() ) &&
			 assignments.get( assign.ident() ) != assign.assignmentExpr().typeOf() ) {
			
			throw new RuntimeException( String.format( "Invalid type [%s] in assignment of [%s] of type %s", 
				assign.assignmentExpr().typeOf(), assign.ident(), assignments.get( assign.ident() ) ));
		}
		
		assignments.put( assign.ident(), assign.assignmentExpr().typeOf() );
	}

	public void apply() {
		inferred.forEach( ( ident, decl ) -> {
			if ( !assignments.containsKey( ident ) ) {
				throw new RuntimeException(
					String.format( "Type could not be inferred for [%s]", ident ) );
			}

			decl.setType( assignments.get( ident ) );
		});
	}
}
