package org.txr.designpatterns.chapter1.politics;


import org.junit.Test;
import org.txr.designpatterns.chapter1.politics.formatter.DefaultMPFormatter;
import org.txr.designpatterns.chapter1.politics.retriever.PublicWipMPRetriever;
import org.txr.designpatterns.chapter1.politics.retriever.WorkForYouMPRetriever;
import org.txr.designpatterns.chapter1.politics.retriever.mapper.PublicWipMPMapper;
import org.txr.designpatterns.chapter1.politics.retriever.mapper.WorkForYouMPMapper;
public class MPListerTest {
	@Test
	public void testPublicWip() throws Exception {
		DefaultMPFormatter formatter = new DefaultMPFormatter();
		PublicWipMPRetriever retriever = new PublicWipMPRetriever();
		retriever.setMpMapper(new PublicWipMPMapper());
		MPLister lister = new MPLister(retriever, formatter);
		lister.listMPS();
	}
	
	@Test
	public void testWorkForYou() throws Exception {
		DefaultMPFormatter formatter = new DefaultMPFormatter();
		WorkForYouMPRetriever retriever = new WorkForYouMPRetriever();
		retriever.setMapper(new WorkForYouMPMapper());
		MPLister lister = new MPLister(retriever, formatter);
		lister.listMPS();
	}
}
