package org.red.visitor.compiler;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
	
	public enum TokenType {
		OPERATOR_MULT( "*" ),
		OPERATOR_DIV( "/" ),
		OPERATOR_ADD( "+" ),
		OPERATOR_SUB( "-" ),
		OPERATOR_GT( ">" ),
		OPERATOR_LT( "<" ),
		DECL( ":" ),
		ASSIGN( "=" ),
		BRACE_OPEN( "{" ),
		BRACE_CLOSE( "}" ),
		STMT_END( "\n" ),
		IF( "if" ),
		ELSE( "else" ),
		WHILE( "while" ),

		STRING_LITERAL( Pattern.compile( "^\"((?:\\\\\"|[^\\\"])*)\"$" ) ),
		INT_LITERAL( Pattern.compile( "^(\\d+)$" ) ),
		FLOAT_LITERAL( Pattern.compile( "^(\\d*\\.\\d+)$" ) ),
		BOOL_LITERAL( Pattern.compile( "^(true|false)$" ) ),
		IDENT( Pattern.compile( "^([\\p{L}_]+)$" ) ),
		EOF((String)null);
		
		private final Pattern pattern;
		private final String literal;

		private TokenType( String literal ) {
			this.pattern = null;
			this.literal = literal;
		}

		private TokenType( Pattern pattern ) {
			this.pattern = pattern;
			this.literal = null;
		}

		public String matchGroup( String tok ) {
			Matcher matcher = pattern.matcher( tok );

			if ( !matcher.matches() )
				return null;

			return matcher.group(1);
		}

		public Pattern pattern() {
			return pattern;
		}

		public String literal() {
			return literal;
		}
	}
	
	private static final Map<String, TokenType> BASIC_TOKENS = new HashMap<>();
	private static final List<TokenType> REGEX_TOKENS = new ArrayList<>();
	
	static {
		Arrays.stream( TokenType.values() )
			.filter( t -> t.literal() != null )
			.forEach( t -> BASIC_TOKENS.put( t.literal(), t ) );
		
		Arrays.stream( TokenType.values() )
			.filter( t -> t.pattern() != null )
			.forEach( REGEX_TOKENS::add );
	}
	
	public static class Token {
		private final TokenType type;
		private final String token;
		
		public Token( TokenType type, String token ) {
			this.type = type;
			this.token = token;
		}

		public TokenType type() {
			return type;
		}

		public String token() {
			return token;
		}
		
		@Override
		public String toString() {
			String tok = token != null ? token.replaceAll( "\n", "\\\\n" ) : null;
			return type + ": " + tok;
		}
	}

	public List<Token> tokenize( Reader in ) throws IOException {
		List<Token> tokens = new ArrayList<>();
		
		// Bad inefficient tokenizing, but this would normally be handled by a
		// separate lexer/grammar parser, and efficiently tokenizing this
		// would take more time than wanted for such a simple example
		
		boolean inToken = false;
		boolean inString = false;
		StringBuilder token = new StringBuilder();
		
		int read;
		while( (read = in.read()) > -1 || inToken ) {
			char ch = read > -1 ? (char) read : ' ';
			String part = "" + ch;
			
			if ( inString ) {
				token.append( ch );
				String contents;
				if ( (contents = TokenType.STRING_LITERAL.matchGroup( token.toString() )) != null ) {
					tokens.add( new Token( TokenType.STRING_LITERAL, contents ) );
					inToken = inString = false;
					token.setLength( 0 );
				}
			}
			else if ( inToken ) {
				boolean addsBasic = false;
				if ( Character.isWhitespace( ch ) ||
						( addsBasic = BASIC_TOKENS.containsKey( part ) ) ) {
					inToken = false;
					
					if ( addsBasic ) {
						tokens.add( new Token( BASIC_TOKENS.get( part ), part ) );
					}

					String tok = token.toString();

					for ( TokenType type : REGEX_TOKENS ) {
						String group;
						if ( (group = type.matchGroup( tok )) != null ) {
							tokens.add( new Token( type, group ) );
							break;
						}
					}

					token.setLength( 0 );
				}
				else {
					token.append( ch );

					if ( BASIC_TOKENS.containsKey( token.toString() ) ) {
						tokens.add( new Token( BASIC_TOKENS.get( token.toString() ), token.toString() ) );
						inToken = false;
						token.setLength( 0 );
					}
				}
			}
			else {
				if ( BASIC_TOKENS.containsKey( part ) ) {
					tokens.add( new Token( BASIC_TOKENS.get( part ), part ) );
					continue;
				}
				
				if ( Character.isWhitespace( ch ) )
					continue;
			
				token.append( ch );
				inToken = true;
				inString = ch == '"';
			}
		}

		return tokens;
	}

}
