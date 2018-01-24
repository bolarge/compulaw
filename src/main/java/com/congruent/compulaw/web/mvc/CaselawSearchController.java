package com.congruent.compulaw.web.mvc;

import com.congruent.compulaw.domain.Act;
import com.congruent.compulaw.domain.Caselaw;
import com.congruent.compulaw.domain.CaselawSearchCriteria;
import com.congruent.compulaw.domain.Document;
import com.congruent.compulaw.domain.LawCategory;
import com.congruent.compulaw.domain.LawSubCategory;
import com.congruent.compulaw.service.ActService;
import com.congruent.compulaw.service.CaselawService;
import com.congruent.compulaw.service.LawCategoryService;
import com.congruent.compulaw.service.LawSubCategoryService;
import com.congruent.compulaw.web.form.DynaNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

@RequestMapping("/public/sb_srch")
@Controller
public class CaselawSearchController{
	
  final Logger logger = LoggerFactory.getLogger(CaselawSearchController.class);
  
  @Autowired
  private CaselawService caselawService;
  
  @Autowired
  private LawCategoryService lawCategoryService;

  @Autowired
  private LawSubCategoryService lawSubCategoryService;
  
  @Autowired
  private ActService actService;
  
  private final static boolean accountStatus = true;

  //Retrieves Selected Document for display
  @RequestMapping(value="/document/{id}", method=RequestMethod.GET, produces="application/pdf")
  @ResponseBody
  public String showDocument(@PathVariable("id") Long id, Model uiModel, HttpServletRequest request, HttpServletResponse response) throws IOException {
	  Caselaw caselaw = this.caselawService.findById(id);
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

  //Logic Implementation for Advance Search and Filter
  @RequestMapping(value="/listgrid", method=RequestMethod.GET)
  public String list2Grid(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="rows", required=false) Integer rows, @RequestParam(value="sidx", required=false) String sortBy, @RequestParam(value="sord", required=false) String order, @RequestParam(value="caseTitle", required=false) String caseTitle, @RequestParam(value="judge", required=false) String judge, @RequestParam(value="counsels", required=false) String counsels, @RequestParam(value="keyword", required=false) String keyword, @RequestParam(value="citations1", required=false) String citations1, @RequestParam(value="citations2", required=false) String citations2, @RequestParam(value="citations3", required=false) String citations3, Model uiModel){
	if(counsels == null)
    this.logger.info("Listing caselaws for grid with page: {}, rows: {}", page, rows);
    this.logger.info("Listing caselaws for grid with sort: {}, order: {}", sortBy, order);
    //this.logger.info("Is search: {}", Boolean.valueOf(isSearch));
    this.logger.info("Search field caseTitle: {}, judge: {}, counsels: {}", new Object[] { caseTitle, judge, counsels });
    this.logger.info("Search field keyword: {}, citations2: {}, citations3: {}", new Object[] { keyword, citations2, citations3 });

    if (caseTitle == null)
      caseTitle = "%";
    else {
      caseTitle = "%" + caseTitle + "%";
    }

    if (judge == null)
      judge = "%";
    else {
      judge = "%" + judge + "%";
    }

    if (counsels == null)
      counsels = "%";
    else {
      counsels = "%" + counsels + "%";
    }

    if (keyword == null)
      keyword = "%";
    else {
      keyword = "%" + keyword + "%";
    }

    if (citations1 == null)
      citations1 = "%";
    else {
      citations1 = "%" + citations1 + "%";
    }

    if (citations2 == null)
      citations2 = "%";
    else {
      citations2 = "%" + citations2 + "%";
    }

    if (citations3 == null)
      citations3 = "%";
    else {
      citations3 = "%" + citations3 + "%";
    }
    CaselawSearchCriteria searchCriteria = new CaselawSearchCriteria();
    searchCriteria.setCaseTitle(caseTitle);
    searchCriteria.setJudge(judge);
    searchCriteria.setCounsels(counsels);
    searchCriteria.setKeyword(keyword);
    searchCriteria.setCitations1(citations1);
    searchCriteria.setCitations2(citations2);
    searchCriteria.setCitations3(citations3);
    //
    Sort sort = null;
    String orderBy = sortBy;
    if ((orderBy != null) && (orderBy.equals("caseTitle"))) orderBy = "caseTitle";

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
    Page<Caselaw> caselawPage = this.caselawService.findCaselawByCriteria(searchCriteria, pageRequest);
    //
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
    //
    return "public/sb_srch/search";
  }

  //Fetches and displays all Case law records
  @RequestMapping(method=RequestMethod.GET)
  public String list(Model uiModel){
    List<Caselaw> caselaws = this.caselawService.findAll();
    uiModel.addAttribute("caselaws", caselaws);
    //this.logger.info("No. of caselaws: " + caselaws.size());
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_srch/search";
  }

  //Form for Advance Search & Filter
  @RequestMapping(value="/search", method=RequestMethod.GET)
  public String search(Model uiModel) {
	  uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_srch/search";
  }
  
 //Tree Implementations for Caselaws and Acts  
//Initialize Case-law as a root and setup law Categories
  @RequestMapping(value={"/listlawjson"}, method={RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public DynaNode getLawCategoryNode(){
	//  
    List<LawCategory> caselawCategory = this.lawCategoryService.findAll();
    Iterator<LawCategory> da = caselawCategory.iterator();
    ArrayList<DynaNode> n5 = new ArrayList<DynaNode>(caselawCategory.size());
    DynaNode cn = new DynaNode();//Parent Node
    //
     while(da.hasNext()){
    	LawCategory lc =  da.next(); //Instantiate new Law category object
    	DynaNode n4 = new DynaNode(); //Children Node    	
    	n4.setTitle(lc.getName()); n4.setFolder(true); n4.setLazy(true);n4.setKey(lc.getId().toString());
    	n5.add(n4);
    	cn.setTitle("Caselaws"); cn.setFolder(true); cn.setLazy(false); cn.setKey("0"); cn.setChildren(n5);
    }
    return cn;
  }
  
  
  //Lazy Loads Law Sub Categories
  //@OrderBy("name DESC")
  @RequestMapping(value="/listsubjson", method=RequestMethod.GET, produces="application/json")
  @ResponseBody
  public List<DynaNode> getLawSubCategory(@RequestParam(value="key", required=true) Long key){
	//  
    LawCategory thisCategory = this.lawCategoryService.findById(key);
    Set<LawSubCategory> lawSubCategory = thisCategory.getTheSubCategories();
    //
    ArrayList<LawSubCategory> listSubCat = new ArrayList<LawSubCategory>(); 
    listSubCat.addAll(lawSubCategory);
    Collections.sort(listSubCat);
    logger.info("These are the sub categories " + lawSubCategory.toString());
    //
    Iterator<LawSubCategory> theSubs = listSubCat.iterator();
    ArrayList<DynaNode> n6 = new ArrayList<DynaNode>(lawSubCategory.size());
     while(theSubs.hasNext()){
    	LawSubCategory lsc =  theSubs.next(); 
    	DynaNode n7 = new DynaNode();
    	n7.setTitle(lsc.getName()); n7.setFolder(true); n7.setLazy(true); n7.setKey(lsc.getId().toString());
    	//Collections.sort(n6);
    	n6.add(n7);
    }
    return n6;
  }
  
//Lazy Loads Case-laws 
  @RequestMapping(value="/listcasjson", method=RequestMethod.GET, produces="application/json")
  @ResponseBody //List<DynaNode>
  public List<DynaNode> getLawSubCategoryCaselaws(@RequestParam(value="key", required=true) Long subCategoryId){
	 
	logger.info("The sub category ID passed in is: " + subCategoryId.toString());  
    LawSubCategory theLawSubCategory = this.lawSubCategoryService.findById(subCategoryId);
    Set<Caselaw> caselawsInLawSubCategory = theLawSubCategory.getCaselaws();
    //
    ArrayList<Caselaw> caselawList = new ArrayList<Caselaw>();
    caselawList.addAll(caselawsInLawSubCategory);
    Collections.sort(caselawList);
    //
    logger.info("The are the caselaws inside this CaselawSubCat is: " + caselawsInLawSubCategory.toString());
    Iterator<Caselaw> theCases = caselawList.iterator();
    ArrayList<DynaNode> n8 = new ArrayList<DynaNode>();
    //DynaNode n12 = new DynaNode();  
    //
     while(theCases.hasNext()){
    	Caselaw cl =  theCases.next(); 
    	logger.info("Caselaw is: " + cl.toString());
    	DynaNode n9 = new DynaNode();
    	n9.setTitle(cl.getCaseTitle()); n9.setFolder(false); n9.setLazy(false); n9.setUrl("http://www.compulawonline.com/public/sb_srch/document/" + cl.getId().toString()); n9.setKey(cl.getId().toString());
    	 n8.add(n9); 
      	//n12.setTitle(theLawSubCategory.getName()); n12.setFolder(false); n12.setLazy(true); n12.setChildren(n8);     
    }
     //
    return n8;
    //return n12;
  }
  
  //Lazy Loads Acts
  @RequestMapping(value="/listactsjson", method=RequestMethod.GET, produces="application/json")
  @ResponseBody
  //public List<DynaNode> getLawActCategory(@RequestParam(value="key", required=true) Long key)
  public DynaNode getLawActCategory() { 
    List<Act> actList = this.actService.findAll();
    Iterator<Act> theActs = actList.iterator();
    List<DynaNode> childrenActNodes = new ArrayList<DynaNode>(actList.size()); 
    DynaNode n12 = new DynaNode();    
    //
     while(theActs.hasNext()){
     	Act nodeAct =  theActs.next(); 
     	DynaNode acts = new DynaNode();
     	acts.setTitle(nodeAct.getTitle());acts.setFolder(false); acts.setLazy(true); acts.setUrl("http://www.compulawonline.com/public/sb_acts/document/" + nodeAct.getId().toString()); //key.toString();    	
     	childrenActNodes.add(acts);    
     	n12.setTitle("Acts"); n12.setFolder(true); n12.setLazy(true); n12.setChildren(childrenActNodes);
     }   
     //
    //return n14;
     return n12;
  }
}