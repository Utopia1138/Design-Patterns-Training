package org.txr.designpatterns.chapter1.politics;

public class MPLister {
	
	MPRetriever retriever;
	OutputFormatter formatter;
	public MPLister( MPRetriever retriever, OutputFormatter formatter ) {
		this.retriever = retriever;
		this.formatter = formatter;
	}
	
	public void  listMPS() throws Exception {
		String content = formatter.format(retriever.retrieveMPS());
		System.out.println(content);
	}
	
	
}
