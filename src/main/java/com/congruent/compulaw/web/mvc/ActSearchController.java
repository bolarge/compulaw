package com.congruent.compulaw.web.mvc;

import com.congruent.compulaw.domain.Act;
import com.congruent.compulaw.domain.ActSearchCriteria;
import com.congruent.compulaw.domain.Document;
import com.congruent.compulaw.service.ActService;
import com.congruent.compulaw.web.form.ActGrid;
import com.congruent.compulaw.web.form.ActNode;
import com.congruent.compulaw.web.form.DynaNode;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

@RequestMapping("/public/sb_acts")
@Controller
public class ActSearchController{
	
  final Logger logger = LoggerFactory.getLogger(ActSearchController.class);
  
  private final static boolean accountStatus = true;

  @Autowired
  private ActService actService;

  @RequestMapping(value="/document/{id}", method=RequestMethod.GET, produces="application/pdf")
  @ResponseBody
  public String showDocument(@PathVariable("id") Long id, Model uiModel, HttpServletRequest request, HttpServletResponse response) throws IOException { Act act = this.actService.findById(id);
    Document actDocument = act.getDocument();
    if ((actDocument == null) || (actDocument.getFileData().length == 0)) {
      response.getWriter().write("Error retrieving document. (ERR001)");
      
      return null;
    }

    response.setContentType("application/pdf");
    response.setContentLength(actDocument.getFileData().length);
    response.getOutputStream().write(actDocument.getFileData());
    uiModel.addAttribute("accountStatus", accountStatus);
    return null;
  }

  @RequestMapping(value="/listgrid", method=RequestMethod.GET, produces="application/json")
  @ResponseBody
  public ActGrid list2Grid(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="rows", required=false) Integer rows, @RequestParam(value="sidx", required=false) String sortBy, @RequestParam(value="sord", required=false) String order, @RequestParam(value="_search", required=false) boolean isSearch, @RequestParam(value="title", required=false) String title)
  {
    this.logger.info("Listing act entries for grid with page: {}, rows: {}", page, rows);
    this.logger.info("Listing blog entries for grid with sort: {}, order: {}", sortBy, order);
    //this.logger.info("Is search: {}", Boolean.valueOf(isSearch));
    this.logger.info("Search field subject: {}, categoryId: {}", title);

    if (title == null)
      title = "%";
    else {
      title = "%" + title + "%";
    }

    ActSearchCriteria searchCriteria = new ActSearchCriteria();
    searchCriteria.setTitle(title);

    Sort sort = null;
    String orderBy = sortBy;
    if ((orderBy != null) && (orderBy.equals("title"))) orderBy = "title";

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
    Page<Act> actPage = this.actService.findActByCriteria(searchCriteria, pageRequest);

    ActGrid actGrid = new ActGrid();

    actGrid.setCurrentPage(actPage.getNumber() + 1);
    actGrid.setTotalPages(actPage.getTotalPages());
    actGrid.setTotalRecords(actPage.getTotalElements());
    actGrid.setActData(Lists.newArrayList(actPage.iterator()));
    
    return actGrid;
  }

  @RequestMapping(method=RequestMethod.GET)
  public String list(Model uiModel) {
    List<Act> acts = this.actService.findAll();
    uiModel.addAttribute("acts", acts);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_acts/search";
  }

  @RequestMapping(value="/public/sb_acts/search", method=RequestMethod.GET)
  public String search(Model uiModel) {
	  uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_acts/search";
  }
  
  //Parent Act List
  @RequestMapping(value="/listparentactjson", method=RequestMethod.GET, produces={"application/json"})
  @ResponseBody
  public DynaNode getActNode(){  
	  /*DynaNode n4 = new DynaNode();
	  n4.setTitle("Acts"); n4.setFolder(true); n4.setLazy(true);n4.setKey("0"); 	
	  return n4;*/
	  List<Act> actList = this.actService.findAll();
	    Iterator<Act> theActs = actList.iterator();
	    List<DynaNode> childrenActNodes = new ArrayList<DynaNode>(actList.size()); 
	    DynaNode n12 = new DynaNode();    
	    //
	     while(theActs.hasNext()){
	     	Act nodeAct =  theActs.next(); 
	     	DynaNode acts = new DynaNode();
	     	acts.setTitle(nodeAct.getTitle());acts.setFolder(false); acts.setLazy(false); acts.setUrl("http://www.compulawonline.com/public/sb_acts/document/" + nodeAct.getId().toString()); //key.toString();    	
	     	childrenActNodes.add(acts);    
	     	}   
	     n12.setTitle("Acts"); n12.setFolder(true); n12.setLazy(false); n12.setChildren(childrenActNodes);	     
	    //return n14;
	     return n12;
  }
  
  //Sub Act List
  @RequestMapping(value={"/listactsubjson"}, method={RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public List<ActNode> getActCategoryNode()
  {
    List<Act> actList = this.actService.findAll();
    Iterator<Act> da = actList.iterator();
    ArrayList<ActNode> n5 = new ArrayList<ActNode>(actList.size());
  
     while(da.hasNext()){
    	Act nodeAct =  da.next(); 
    	ActNode n4 = new ActNode();
    	n4.setTitle(nodeAct.getTitle()); n4.setFolder(false); n4.setLazy(true);n4.setKey(nodeAct.getId().toString());
    	n5.add(n4);
    }
    return n5;
  }
}