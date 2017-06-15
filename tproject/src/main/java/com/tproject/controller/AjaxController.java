package com.tproject.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tproject.util.FileDownloadView;
import com.tproject.util.SVNUtil;

@Controller
public class AjaxController {
	@Autowired
	SVNUtil svnUtil;
	
	@RequestMapping(value = "/makerepoajax", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> makerepo(@RequestBody Map<String, Object> info) {	
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		String repourl = svnUtil.doMakeRepo(info.get("url").toString());
		
		retVal.put("result", "success");
		retVal.put("repourl", repourl);
		
		return retVal;
	}
	
	@RequestMapping(value = "/repoinfoajax", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> getrepoinfo(@RequestBody Map<String, Object> info) {	
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		retVal.put("result", "success");
		retVal.put("repoinfo", svnUtil.doPrintRepo(info.get("url").toString()));
		
		return retVal;
	}
	
	@RequestMapping(value = "/repohistoryajax", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> getrepohistory(@RequestBody Map<String, Object> info) {	
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		retVal.put("result", "success");
		retVal.put("loginfolist", svnUtil.doPrintRepoLog(info.get("url").toString()));
		
		return retVal;
	}
	
	@RequestMapping(value = "/repotreeajax", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> treerepo(@RequestBody Map<String, Object> info) {	
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		retVal.put("result", "success");
		retVal.put("repotreelist", svnUtil.doPrintRepotree(info.get("url").toString()));
		
		return retVal;
	}
	
	@RequestMapping(value = "/filecontentajax", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> filecontent(@RequestBody Map<String, Object> info) {	
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		retVal.put("result", "success");
		retVal.put("filecontentinfo", svnUtil.doPrintFilecontent(
				info.get("url").toString(), 
				info.get("filename").toString(), 
				info.get("filepath").toString()));
		
		return retVal;
	}
	
	@RequestMapping(value = "/commitajax", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> commit(@RequestBody Map<String, Object> info) {	
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
	
		retVal.put("result", "success");
		retVal.put("commitinfo", svnUtil.docommit(
				info.get("repourl").toString(), 
				info.get("commitpath").toString(), 
				info.get("commitlog").toString(), 
				info.get("commitfilename").toString(), 
				info.get("commitfilecontent").toString()));
		
		return retVal;
	}
	
	@RequestMapping(value = "/commitmodifyajax", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> commitmodify(@RequestBody Map<String, Object> info) {	
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		retVal.put("result", "success");
		retVal.put("commitinfo", svnUtil.docommitmodify(
				info.get("repourl").toString(), 
				info.get("commitpath").toString(), 
				info.get("commitlog").toString(), 
				info.get("commitfilename").toString(), 
				info.get("originalcontent").toString(),
				info.get("updatecontent").toString()));
		
		return retVal;
	}
	
	@RequestMapping(value = "/commitdirajax", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> commitdir(@RequestBody Map<String, Object> info) {	
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		retVal.put("result", "success");
		retVal.put("commitinfo", svnUtil.docommitdir(
				info.get("repourl").toString(), 
				info.get("commitpath").toString(), 
				info.get("commitlog").toString(), 
				info.get("commitfilename").toString(), 
				info.get("commitfilecontent").toString(),
				info.get("commitdirname").toString()));
		
		return retVal;
	}
	
	@RequestMapping(value = "/commitdeleteajax", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> commitdelete(@RequestBody Map<String, Object> info) {	
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		retVal.put("result", "success");
		retVal.put("commitinfo", svnUtil.docommitdelete(
				info.get("url").toString(), 
				info.get("deletepath").toString(),
				info.get("commitlog").toString()));
		
		return retVal;
	}
	
	@RequestMapping(value = "/downloadfile" ,method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody ModelAndView downloadfile(@RequestBody Map<String, Object> info) throws Exception {	
		File downloadFile = null;
		
		try{
			downloadFile = new File(info.get("downloadfilepath").toString());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		//파일을 downloadFile 이라는 이름으로 삽입시켜 servlet의 download에 매핑된 view Class로 간다.
		return new ModelAndView("fileDownloadView", "downloadFile", downloadFile);
	}
}
