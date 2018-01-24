package com.congruent.compulaw.web.mvc;

import com.congruent.compulaw.domain.Caselaw;
import com.congruent.compulaw.domain.CaselawSearchCriteria;
import com.congruent.compulaw.domain.Document;
import com.congruent.compulaw.service.CaselawService;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/public/sb_keyw")
@Controller
public class KeywordController{
	
  final Logger logger = LoggerFactory.getLogger(KeywordController.class);
  
  private final static boolean accountStatus = true;

  @Autowired
  private CaselawService caselawService;
  
  //Document Renderer
  @RequestMapping(value="/document/{id}", method=RequestMethod.GET, produces="application/pdf")
  @ResponseBody
  public String showDocument(@PathVariable("id") Long id, Model uiModel, HttpServletRequest request, HttpServletResponse response) throws IOException { Caselaw caselaw = this.caselawService.findById(id);
    Document caseDocument = caselaw.getCaseLawDocument();
    if ((caseDocument == null) || (caseDocument.getFileData().length == 0)) {
      response.getWriter().write("Error retrieving document. (ERR001)");
      return null;
    }
    response.setContentType("application/pdf");
    response.setContentLength(caseDocument.getFileData().length);
    response.getOutputStream().write(caseDocument.getFileData());
    uiModel.addAttribute("accountStatus", accountStatus);
    return null;
  }

  //Basic Search Implementation
  @RequestMapping(value="/listgrid", method=RequestMethod.GET)
  public String list2Grid(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="rows", 
  required=false) Integer rows, @RequestParam(value="sidx", required=false) String sortBy, @RequestParam(value="sord", 
  required=false) String order, @RequestParam(value="keyword", 
  required=false) String keyword, Model uiModel)
  {
    this.logger.info("Listing caselaws for grid with page: {}, rows: {}", page, rows);
    this.logger.info("Listing caselaws for grid with sort: {}, order: {}", sortBy, order);
    this.logger.info("Search field keyword: {}", new Object[] {keyword});
    
    //
    if (keyword == null)
      keyword = "%";
    else {
      keyword = "%" + keyword + "%";
    }
    
    CaselawSearchCriteria searchCriteria = new CaselawSearchCriteria();
    searchCriteria.setCaseTitle(keyword);
    searchCriteria.setKeyword(keyword);
    
    /*CaselawSearchCriteria searchCriteria = new CaselawSearchCriteria();
    //String link = null;
    //
    if (keyword == null){
    	keyword = "on grints";
        searchCriteria.setCaseTitle(keyword);
        searchCriteria.setKeyword(keyword);
    }else {
      keyword = "%" + keyword + "%";
      searchCriteria.setCaseTitle(keyword);
      searchCriteria.setKeyword(keyword);
    }*/
       
    //
    Sort sort = null;
    String orderBy = sortBy;
    if ((orderBy != null) && (orderBy.equals("keyword"))) orderBy = "keyword";
    if ((orderBy != null) && (order != null)) {
      if (order.equals("desc"))
        sort = new Sort(Sort.Direction.DESC, new String[] { orderBy });
      else {
        sort = new Sort(Sort.Direction.ASC, new String[] { orderBy });
      }
    }
    PageRequest pageRequest = null;
    if (sort != null)
      pageRequest = new PageRequest(page.intValue() - 1, rows.intValue(), sort);
    else {
      pageRequest = new PageRequest(page.intValue() - 1, rows.intValue());
    }
   
      Page<Caselaw> caselawPage = this.caselawService.findCaselawByKeyword(searchCriteria, pageRequest);
      //Page<Caselaw> caselawPage = this.caselawService.findCaselawByKeyword(searchCriteria, pageRequest);
  
    	this.logger.info("Inside method.................");
    	int current = caselawPage.getNumber() + 1;
    	int begin = Math.max(1, current - 5);
    	int end = Math.min(begin + 10, caselawPage.getTotalPages());
    	List<Caselaw> caselaws = caselawPage.getContent();
	    //
	    uiModel.addAttribute("caselaws", caselaws);
	    uiModel.addAttribute("caselawPage", caselawPage);
    	uiModel.addAttribute("beginIndex", begin);
    	uiModel.addAttribute("endIndex", end);
    	uiModel.addAttribute("currentIndex", current);   
    	uiModel.addAttribute("accountStatus", accountStatus);
    	//ink = "public/sb_keyw/search";
    
    //
    return "public/sb_keyw/search";
  }

  //Default Search Form
  @RequestMapping(value="/search", method=RequestMethod.GET)
  public String search(Model uiModel) {
	  uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_keyw/search";
  }
   
}