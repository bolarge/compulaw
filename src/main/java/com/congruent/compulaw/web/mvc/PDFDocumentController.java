/*package com.congruent.compulaw.web.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

//@Controller
//@RequestMapping("/public/act")
public class PDFDocumentController extends AbstractPdfView {
	
	
	final Logger logger = LoggerFactory.getLogger(PDFDocumentController.class);
			 
	    //@Override
	protected void buildPdfDocument(
	        Map model,
	        Document doc,
	        PdfWriter writer,
	        HttpServletRequest req,
	        HttpServletResponse resp)
	        throws Exception {

	        List words = (List) model.get("wordList");

	        for (int i=0; i<words.size(); i++)
	            doc.add( new Paragraph((String) words.get(i)));

	    }	   
	   
}


*/