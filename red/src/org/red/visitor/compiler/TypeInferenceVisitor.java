package org.red.visitor.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.red.visitor.DefaultASTVisitor;
import org.red.visitor.ast.AssignmentStatement;
import org.red.visitor.ast.DeclarationStatement;
import org.red.visitor.ast.LookupExpression;
import org.red.visitor.interpreter.Context.Type;

public class TypeInferenceVisitor implements DefaultASTVisitor {
	private final Map<String, DeclarationStatement> declarations = new HashMap<>();
	private final Map<String, List<LookupExpression>> lookups = new HashMap<>();
	private final Map<String, Type> assignments = new HashMap<>();
	private boolean changed = false;
	private boolean inferrencesLeft = false;

	@Override
	public void visit( DeclarationStatement decl ) {
		if ( decl.type() == Type.INFERRED ) {
			declarations.put( decl.ident(), decl );
		}
	}

	@Override
	public void visit( AssignmentStatement assign ) {
		// Assignment to this value already made and types differ!
		// In future this could do coercion checks to see if types are implicitly translatable
		if ( assignments.containsKey( assign.ident() ) &&
			 assignments.get( assign.ident() ) != assign.assignmentExpr().typeOf() ) {
			
			throw new RuntimeException( String.format( "Invalid type [%s] in assignment of [%s] of type %s", 
				assign.assignmentExpr().typeOf(), assign.ident(), assignments.get( assign.ident() ) ));
		}
		
		assignments.put( assign.ident(), assign.assignmentExpr().typeOf() );
	}

	@Override
	public void visit( LookupExpression lu ) {
		if ( lu.typeOf() == Type.INFERRED ) {
			if ( !lookups.containsKey(lu.ident()) ) {
				lookups.put(lu.ident(), new ArrayList<>());
			}

			lookups.get( lu.ident() ).add( lu );
		}
	}

	public void apply() {
		declarations.forEach( ( ident, decl ) -> {
			if ( !assignments.containsKey( ident ) ) {
				throw new RuntimeException(
					String.format( "Type could not be inferred for [%s]", ident ) );
			}

			decl.setType( assignments.get( ident ) );

			if ( lookups.containsKey(ident) ) {
				lookups.get(ident).forEach(lu -> lu.typeIs( decl.type() ));
			}

			if ( assignments.get( ident ) != Type.INFERRED ) {
				changed = true;
			}
		});
		
		inferrencesLeft = declarations.values().stream()
			.anyMatch(d -> d.type() == Type.INFERRED);
	}
	
	public List<DeclarationStatement> left() {
		return declarations.values().stream()
			.filter(d -> d.type() == Type.INFERRED)
			.collect(Collectors.toList());
	}

	public boolean inferrencesLeft() {
		return inferrencesLeft;
	}

	public boolean changesMade() {
		return changed;
	}
}
