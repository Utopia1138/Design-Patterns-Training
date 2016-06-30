
package com.spg.chapter11;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.spg.chapter11.handler.ArticleEditorHandler;
import com.spg.chapter11.handler.ArticleReaderHandler;
import com.spg.chapter11.handler.CommentModeratorHandler;
import com.spg.chapter11.handler.CommentReaderHandler;

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
		Article article = new OpinionArticle();

		// Editor writes the article
		Article editorAccess = getArticleProxy( article, new ArticleEditorHandler( article ) );
		editorAccess.setTitle( "Man's best friend" );
		editorAccess.setArticleText( "I like puppies." );
		System.out.println( "Wrote article: " + editorAccess.getArticleText() );

		// Reader reads the article
		Article readerAccess = getArticleProxy( article, new ArticleReaderHandler( article ) );
		System.out.println( "Reader reads article text: " + readerAccess.getArticleText() );
		readerAccess.addComment( "Fido", "I really like this article" );

		// People like this comment
		Article readerAccess2 = getArticleProxy( article, new ArticleReaderHandler( article ) );
		Comment readerAccess2Comment0 = getCommentProxy( readerAccess2.getComment( 0 ), new CommentReaderHandler( readerAccess2.getComment( 0 ) ) );
		System.out.println( "Reader 2 reads comment 0: " + readerAccess2Comment0.getCommentText() );
		readerAccess2Comment0.changeScore( 5 );
		System.out.println( "Reader 2 changes comment score to: " + readerAccess2Comment0.getScore() );
	}

	/**
	 * A controversial article that has tempers flare
	 */
	public static void nastyArticle() {
		Article article = new OpinionArticle();

		// Editor writes the article
		Article editorAccess = getArticleProxy( article, new ArticleEditorHandler( article ) );
		editorAccess.setTitle( "Man's worst friend" );
		editorAccess.setArticleText( "I hate puppies. They're only good for kicking." );

		// Reader reads the article
		Article readerAccess = getArticleProxy( article, new ArticleReaderHandler( article ) );
		System.out.println( "Reader reads article text: " + readerAccess.getArticleText() );
		readerAccess.addComment( "Dennis", "Totally agree. My dog ate my homework and now my teacher is mad" );

		// Another reader hates the article and tries to change it out of anger
		Article readerAccess2 = getArticleProxy( article, new ArticleReaderHandler( article ) );
		System.out.println( "Reader 2 reads article text: " + readerAccess2.getArticleText() );
		try {
			readerAccess2.setArticleText( "Puppies are nothing but sunshine and happiness and I was a monster to consider otherwise" );
		}
		catch ( Exception e ) {
			System.out.println( "Reader 2 gets error: " + e.getMessage() );
		}

		// Failing that, they try to change the comment
		Comment readerAccess2Comment0 = getCommentProxy( readerAccess2.getComment( 0 ), new CommentReaderHandler( readerAccess2.getComment( 0 ) ) );
		try {
			readerAccess2Comment0.setCommentText( "I got a detention and blamed it on my pure and innocent canine qq" );
			readerAccess2Comment0.setCommentAuthor( "An idiot" );
			;
		}
		catch ( Exception e ) {
			System.out.println( "Reader 2 gets error: " + e.getMessage() );
		}

		// Finally they leave an angry comment
		readerAccess2.addComment( "Righteous dog-lover", "You're both monsters. I hope you get eaten by starving dogs" );

		// Reader one does not like this so they downvote it and flag it for moderation
		Comment readerAccessComment1 = getCommentProxy( readerAccess.getComment( 1 ), new CommentReaderHandler( readerAccess.getComment( 1 ) ) );
		System.out.println( "Reader reads comment: " + readerAccessComment1.getCommentText() );
		readerAccessComment1.changeScore( -5 );
		readerAccessComment1.setFlagged( true );
		System.out.println( "Reader changes comment score to: " + readerAccessComment1.getScore() );

		// A moderator comes in to examine the thread
		Article modAccess = getArticleProxy( article, new ArticleReaderHandler( article ) );
		Comment modAccessComment;

		for ( Comment moderatedComment : modAccess.getComments() ) {
			modAccessComment = getCommentProxy( moderatedComment, new CommentModeratorHandler( moderatedComment ) );

			if ( modAccessComment.isFlagged() ) {
				modAccessComment.setCommentText( "MOD NOTICE: This comment has been removed for breaking the rules" );
			}
		}

		// Also leaves a comment and attempts to downvote the first comment, forgetting that they can't
		modAccess.addComment( "Modman", "That said, you really are both monsters" );
		Comment modAccessComment0 = getCommentProxy( modAccess.getComment( 0 ), new CommentReaderHandler( modAccess.getComment( 0 ) ) );
		try {
			modAccessComment0.changeScore( -100 );
		}
		catch ( Exception e ) {
			System.out.println( "Moderator gets error: " + e.getMessage() );
		}
	}

}
