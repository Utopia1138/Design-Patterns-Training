package org.red.visitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.red.visitor.ast.Sequence;

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
		TypeInferenceVisitor typeInferrer = new TypeInferenceVisitor();
		sequence.accept( typeInferrer );
		typeInferrer.apply();
	}

	public void runProgram( Sequence sequence ) {
		Context context = new Context();
		
		System.out.println( "=== STDOUT: ===" );
		
		sequence.execute( context );
		
		System.out.println( "\n=== Context after run: ===" );
		System.out.println( context );
		
		System.out.println( "\n=== AST: ===" );
		sequence.accept( new ASTPrintingVisitor() );
	}
}
