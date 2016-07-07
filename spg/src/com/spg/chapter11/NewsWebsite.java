
package com.spg.chapter11;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.spg.chapter11.handler.ArticleEditorHandler;
import com.spg.chapter11.handler.ArticleReaderHandler;
import com.spg.chapter11.handler.CommentModeratorHandler;
import com.spg.chapter11.handler.CommentReaderHandler;
import com.spg.chapter11.users.ModeratorAccount;
import com.spg.chapter11.users.ReaderAccount;
import com.spg.chapter11.users.UserAccount;
import com.spg.chapter11.users.WriterAccount;

public class NewsWebsite {

	public static void main( String[] args ) {

		try {
			niceArticle();
			nastyArticle();
		}
		catch ( Exception e ) {
			System.out.println( "Unexpected error" );
			e.printStackTrace();
		}
	}

	public static Article getArticleProxy( Article article, InvocationHandler handler ) {
		return (Article) Proxy.newProxyInstance( article.getClass().getClassLoader(), article.getClass().getInterfaces(), handler );
	}

	public static Comment getCommentProxy( Comment comment, InvocationHandler handler ) {
		return (Comment) Proxy.newProxyInstance( comment.getClass().getClassLoader(), comment.getClass().getInterfaces(), handler );
	}

	/**
	 * A nice, non-controversial article
	 */
	public static void niceArticle() {
		// Set up some accounts
		UserAccount writer = new WriterAccount( "R. Over" );
		UserAccount reader1 = new ReaderAccount( "Fido" );
		UserAccount reader2 = new ReaderAccount( "Herb Oy" );

		Article article = new OpinionArticle();

		System.out.println( "\nArticle writer:" );

		// Editor writes the article
		Article editorAccess = getArticleProxy( article, writer.getArticleHandler( article ) );
		editorAccess.setTitle( "Man's best friend" );
		editorAccess.setArticleText( "I like puppies." );

		System.out.println( "\nArticle reader:" );

		// Reader reads the article
		Article readerAccess = getArticleProxy( article, reader1.getArticleHandler( article ) );
		System.out.println( "Reader reads article text: " + readerAccess.getArticleText() );
		readerAccess.addComment( reader1.getName(), "I really like this article" );

		System.out.println( "\nArticle reader 2:" );

		// People like this comment
		Article readerAccess2 = getArticleProxy( article, reader2.getArticleHandler( article ) );
		Comment readerAccess2Comment0 = getCommentProxy( readerAccess2.getComment( 0 ), reader2.getCommentHandler( readerAccess2.getComment( 0 ) ) );
		System.out.println( "Reader 2 reads comment 0: " + readerAccess2Comment0.getCommentText() );
		readerAccess2Comment0.changeScore( 5 );
		System.out.println( "Reader 2 changes comment score to: " + readerAccess2Comment0.getScore() );
	}

	/**
	 * A controversial article that has tempers flare
	 */
	public static void nastyArticle() {
		// Create some users		
		UserAccount writer = new WriterAccount( "S. Whiplash" );
		UserAccount reader1 = new ReaderAccount( "Dennis" );
		UserAccount reader2 = new ReaderAccount( "Righteous dog-lover" );
		UserAccount moderator = new ModeratorAccount( "TinMother" );

		Article article = new OpinionArticle();

		System.out.println( "\nArticle writer:" );

		// Editor writes the article
		Article editorAccess = getArticleProxy( article, writer.getArticleHandler( article ) );
		editorAccess.setTitle( "Man's worst friend" );
		editorAccess.setArticleText( "I hate puppies. They're only good for kicking." );

		System.out.println( "\nArticle reader:" );

		// Reader reads the article
		Article readerAccess = getArticleProxy( article, reader1.getArticleHandler( article ) );
		System.out.println( "Reader reads article text: " + readerAccess.getArticleText() );
		readerAccess.addComment( "Dennis", "Totally agree. My dog ate my homework and now my teacher is mad" );

		System.out.println( "\nArticle reader 2:" );

		// Another reader hates the article and tries to change it out of anger
		Article readerAccess2 = getArticleProxy( article, reader2.getArticleHandler( article ) );
		System.out.println( "Reader 2 reads article text: " + readerAccess2.getArticleText() );
		try {
			readerAccess2.setArticleText( "Puppies are nothing but sunshine and happiness and I was a monster to consider otherwise" );
		}
		catch ( Exception e ) {
			System.out.println( "Reader 2 gets error: " + e.getCause().getMessage() );
		}

		// Failing that, they try to change the comment
		Comment readerAccess2Comment0 = getCommentProxy( readerAccess2.getComment( 0 ), reader2.getCommentHandler( readerAccess2.getComment( 0 ) ) );
		try {
			readerAccess2Comment0.setCommentText( "I got a detention and blamed it on my pure and innocent canine qq" );
			readerAccess2Comment0.setCommentAuthor( "An idiot" );
			;
		}
		catch ( Exception e ) {
			System.out.println( "Reader 2 gets error: " + e.getCause().getMessage() );
		}

		// Finally they leave an angry comment
		readerAccess2.addComment( "Righteous dog-lover", "You're both monsters. I hope you get eaten by starving dogs" );

		System.out.println( "\nArticle reader:" );

		// Reader one does not like this so they downvote it and flag it for moderation
		Comment readerAccessComment1 = getCommentProxy( readerAccess.getComment( 1 ), reader1.getCommentHandler( readerAccess.getComment( 1 ) ) );
		System.out.println( "Reader reads comment: " + readerAccessComment1.getCommentText() );
		readerAccessComment1.changeScore( -5 );
		readerAccessComment1.setFlagged( true );
		System.out.println( "Reader changes comment score to: " + readerAccessComment1.getScore() );

		System.out.println( "\nArticle moderator:" );

		// A moderator comes in to examine the thread
		Article modAccess = getArticleProxy( article, moderator.getArticleHandler( article ) );
		Comment modAccessComment;

		for ( Comment moderatedComment : modAccess.getComments() ) {
			modAccessComment = getCommentProxy( moderatedComment, moderator.getCommentHandler( moderatedComment ) );

			if ( modAccessComment.isFlagged() ) {
				modAccessComment.setCommentText( "MOD NOTICE: This comment has been removed for breaking the rules" );
			}
		}

		// Also leaves a comment and attempts to downvote the first comment, forgetting that they can't
		modAccess.addComment( "Modman", "That said, you really are both monsters" );
		Comment modAccessComment0 = getCommentProxy( modAccess.getComment( 0 ), moderator.getCommentHandler( modAccess.getComment( 0 ) ) );
		try {
			modAccessComment0.changeScore( -100 );
		}
		catch ( Exception e ) {
			System.out.println( "Moderator gets error: " + e.getCause().getMessage() );
		}
	}

}
