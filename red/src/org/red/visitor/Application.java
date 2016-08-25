package org.red.visitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.red.visitor.ast.Sequence;
import org.red.visitor.compiler.Lexer;
import org.red.visitor.compiler.Parser;
import org.red.visitor.compiler.TypeInferenceVisitor;
import org.red.visitor.interpreter.SimpleASTInterpreter;

/**
 * Compiler for a simple strong statically typed programming language
 * to show uses of the interpreter and specifically the visitor pattern.
 * 
 * There are plenty of edge cases that aren't well handled yet, though
 * this should show the principles well enough.
 * 
 * hello.lang on the classpath is read through a lexer, the output is fed
 * into a syntax parser, which generates an AST. The visitor pattern is used
 * to add additional type-hierarchy wide behaviours to the AST dynamically.
 * 
 * The type inferring visitor attempts to re-read the AST to fill in types
 * that have not been declared by examining the assignment points.
 * 
 * The AST interpreter visits the AST, executing the nodes with equivalent
 * java code.
 * 
 * The AST printing visitor walks through the AST displaying it in a
 * convenient way.
 * 
 * The pretty printer takes the parsed AST and attempts to transform it
 * back into language syntax in a uniform way.
 */
public class Application {
	public static void main( String[] args ) throws IOException {
	
		BufferedReader reader = new BufferedReader( new InputStreamReader(
				Application.class.getClassLoader().getResourceAsStream( "hello.lang" ),
				StandardCharsets.UTF_8 ));
		
		Lexer lexer = new Lexer();
		Parser parser = new Parser();
		Sequence ast = parser.parse( lexer.tokenize( reader ) );
		
		Application sample = new Application();
		sample.compile( ast );
		sample.runProgram( ast );
	}

	public void compile( Sequence sequence ) {
		// First pass, infer types when not explicitly declared
		while(true) {
			TypeInferenceVisitor typeInferrer = new TypeInferenceVisitor();
			sequence.accept( new ASTTraversingVisitor(typeInferrer) );
			typeInferrer.apply();
			
			if ( !typeInferrer.inferrencesLeft() ) {
				break;
			}
			
			if ( !typeInferrer.changesMade() ) {
				throw new RuntimeException( "Failed to infer types for: " + typeInferrer.left()
					.stream()
					.map(decl -> decl.ident())
					.collect(Collectors.joining(", ")) );
			}
		}
	}

	public void runProgram( Sequence sequence ) {
		System.out.println( "=== STDOUT: ===" );
		
		SimpleASTInterpreter interp = new SimpleASTInterpreter();
		sequence.accept(interp);
		
		System.out.println( "\n=== Context after run: ===" );
		System.out.println( interp.context() );
		
		System.out.println( "\n=== AST: ===" );
		sequence.accept( new ASTPrintingVisitor() );
		
		System.out.println( "\n\n=== Pretty printed: ===" );
		sequence.accept( new PrettyPrinter() );
	}
}
