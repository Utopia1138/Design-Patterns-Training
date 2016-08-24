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
