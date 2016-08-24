package org.red.visitor.compiler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.red.visitor.ast.AssignmentStatement;
import org.red.visitor.ast.Condition;
import org.red.visitor.ast.ConditionalLoop;
import org.red.visitor.ast.Constant;
import org.red.visitor.ast.DeclarationStatement;
import org.red.visitor.ast.Expression;
import org.red.visitor.ast.LookupExpression;
import org.red.visitor.ast.Sequence;
import org.red.visitor.ast.Statement;
import org.red.visitor.ast.Terminal;
import org.red.visitor.ast.UnaryOperator;
import org.red.visitor.ast.binop.AdditionOperator;
import org.red.visitor.ast.binop.DivisionOperator;
import org.red.visitor.ast.binop.GreaterThanOperator;
import org.red.visitor.ast.binop.LessThanOperator;
import org.red.visitor.ast.binop.MultiplicationOperator;
import org.red.visitor.ast.binop.SubtractionOperator;
import org.red.visitor.compiler.Lexer.Token;
import org.red.visitor.compiler.Lexer.TokenType;
import org.red.visitor.interpreter.Context.Type;
import org.red.visitor.interpreter.Context.Value;

public class Parser {
	
	private Map<String, Type> identifiers = new HashMap<>();
	private Map<String, Function<Value, Value>> builtinConsumer = new HashMap<>();

	private List<Token> tokens;
	private int index = -1;
	
	public Parser() {
		// built-in functions
		identifiers.put( "print", Type.UNIT );
		
		builtinConsumer.put( "print", value -> {
			System.out.println( value.get() );
			return Value.VOID;
		});
	}
	
	public Token next() {
		if ( ++index >= tokens.size() ) {
			index--;
			return new Token(TokenType.EOF, null);
		}
		
		return tokens.get( index );
	}
	
	public Token peek() {
		int idx = index + 1;
		return idx >= tokens.size() ?
			new Token(TokenType.EOF, null) :
			tokens.get( idx );
	}

	public Token previous() {
		if ( --index < 0 ) {
			index = 0;
		}
		
		return tokens.get( index );
	}

	public Token nextAfterEos() {
		while( peek().type() == TokenType.STMT_END ) {
			next();
		}
		
		return next();
	}
	
	public Token peekSkipEos() {
		while( peek().type() == TokenType.STMT_END ) {
			next();
		}
		
		return peek();
	}
	
	public Sequence parse( List<Token> toks ) {
		this.tokens = toks;
		
		return parseBlock( false );
	}
	
	public Sequence parseBlock( boolean braced ) {
		Sequence seq = new Sequence();

		PARSE_LOOP: while( true ) {
			Token cur = next();
			
			switch( cur.type() ) {
			case IDENT:
				seq.append( new Terminal( parseIdentifier( cur ) ) );
				break;
			case IF:
				seq.append( parseIf() );
				break;
			case WHILE:
				seq.append( parseWhile() );
			case STMT_END:
				break;
			case BRACE_CLOSE:
				if ( braced ) {
					break PARSE_LOOP;
				}
				throw new RuntimeException( "Unmatched closing brace" );
			case EOF:
				break PARSE_LOOP;
			default:
				throw new RuntimeException( "Invalid token: " + cur.type() );
			}
		}
		
		return seq;
	}

	private Statement parseIf() {
		Expression cond = parseExpression();
		
		Token next = nextAfterEos();
		
		if ( next.type() != TokenType.BRACE_OPEN ) {
			throw new RuntimeException( "Condition bodies must be within a block" );
		}
		
		Sequence ifTrue = parseBlock( true );
		
		Sequence ifFalse = null;
		if ( peekSkipEos().type() == TokenType.ELSE ) {
			next();
			
			next = nextAfterEos();

			if ( next.type() != TokenType.BRACE_OPEN ) {
				throw new RuntimeException( "Condition bodies must be within a block" );
			}
			
			ifFalse = parseBlock( true );
		}
		
		return new Condition(cond, ifTrue, ifFalse);
	}
	
	private Statement parseWhile() {
		Expression cond = parseExpression();
		
		Token next = nextAfterEos();
		
		if ( next.type() != TokenType.BRACE_OPEN ) {
			throw new RuntimeException( "Condition bodies must be within a block" );
		}
		
		Sequence body = parseBlock( true );
		
		return new ConditionalLoop( cond, body );
	}
	
	private Expression parseIdentifier( Token cur ) {
		if ( identifiers.containsKey( cur.token() ) ) {
			if ( builtinConsumer.containsKey( cur.token() ) ) {
				return voidCall( cur );
			}
			
			if ( peek().type() == TokenType.ASSIGN ) {
				next();
				return new AssignmentStatement( cur.token(), parseExpression() );
			}
			
			return new LookupExpression( cur.token(), identifiers.get( cur.token() ) );
		}
		
		Token next = next();
		
		if ( next.type() != TokenType.DECL ) {
			throw new RuntimeException( "Undeclared indentifier: " + cur.token() );
		}
		
		next = next();
		Type type = Type.INFERRED;
		if ( next.type() == TokenType.IDENT ) {
			Optional<Type> opt = Type.fromToken( next.token() );

			if( !opt.isPresent() ) {
				throw new RuntimeException( "No type " + next.token() + " in declaration of " + cur.token() );
			}
			type = opt.get();
			next = next();
		}
		
		identifiers.put( cur.token(), type );
		
		if ( next.type() == TokenType.STMT_END || next.type() == TokenType.EOF ) {
			return new DeclarationStatement( type, cur.token() );
		}
		
		if ( next.type() != TokenType.ASSIGN ) {
			throw new RuntimeException( "Invalid token: " + next.type() );
		}

		Sequence combined = new Sequence();
		combined.append( new DeclarationStatement( type, cur.token() ) );
		combined.append( new AssignmentStatement( cur.token(), parseExpression() ));
		
		return combined;
	}

	private Expression voidCall( Token fun ) {
		return new UnaryOperator(
			fun.token(),
			builtinConsumer.get( fun.token() ),
			parseExpression(),
			Type.UNIT );
	}
	
	private Expression parseExpression() {
		return parseExpression( parseValue( next() ), 0 );
	}
	
	private Expression parseExpression( Expression lhs, int prec ) {
		Token lah = peek();
		
		while( isBinaryOp( lah ) && precedence( lah ) >= prec ) {
			Token op = next();
			Expression rhs = parseValue( next() );
			lah = peek();
			
			while( isBinaryOp(lah) && precedence( lah ) > precedence( op ) ) {
				rhs = parseExpression( rhs, precedence(lah) );
				lah = peek();
			}
			
			lhs = binaryOperatorFor( op, lhs, rhs );
		}
		
		return lhs;
	}
	
	private Expression binaryOperatorFor(Token op, Expression lhs, Expression rhs) {
		switch( op.type() ) {
		case OPERATOR_ADD: return new AdditionOperator(lhs, rhs);
		case OPERATOR_DIV: return new DivisionOperator(lhs, rhs);
		case OPERATOR_GT: return new GreaterThanOperator(lhs, rhs);
		case OPERATOR_LT: return new LessThanOperator(lhs, rhs);
		case OPERATOR_MULT: return new MultiplicationOperator(lhs, rhs);
		case OPERATOR_SUB: return new SubtractionOperator(lhs, rhs);
		}
		
		throw new RuntimeException( "Token not an operator" );
	}

	public boolean isBinaryOp( Token op ) {
		switch( op.type() ) {
		case OPERATOR_ADD:
		case OPERATOR_DIV:
		case OPERATOR_GT:
		case OPERATOR_LT:
		case OPERATOR_MULT:
		case OPERATOR_SUB:
			return true;
		default:
			return false;
		}
	}

	public int precedence( Token op ) {
		switch( op.type() ) {
		case OPERATOR_GT:
		case OPERATOR_LT:
			return 1;
		case OPERATOR_ADD:
		case OPERATOR_SUB:
			return 2;
		case OPERATOR_DIV:
		case OPERATOR_MULT:
			return 3;
		default:
			break;
		}
		
		throw new RuntimeException( "Token not an operator" );
	}
	
	public Expression parseValue( Token cur ) {
		switch( cur.type() ) {
		case FLOAT_LITERAL:
			return new Constant( new Value( Type.FLOAT ).set( cur.token() ) );
		case IDENT:
			return parseIdentifier( cur );
		case INT_LITERAL:
			return new Constant( new Value( Type.INTEGER ).set( cur.token() ) );
		case BOOL_LITERAL:
			return new Constant( new Value( Type.BOOLEAN ).set( cur.token() ) );
		case STMT_END:
			break;
		case BRACE_OPEN:
			break;
		case STRING_LITERAL:
			return new Constant( new Value( Type.STRING ).set( cur.token() ) );
		default:
			throw new RuntimeException( "Invalid token: " + cur.type() );
		}
		
		throw new RuntimeException( "Early EOF" );
	}

}
