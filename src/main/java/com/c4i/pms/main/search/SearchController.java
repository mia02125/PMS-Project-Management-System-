package com.c4i.pms.main.search;


import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/main/search")
public class SearchController {
	
	@Resource(name = "SearchService")
	private SearchService searchService;

	@Value("${pems.solr.baseUrl}")
	private String baseUrl;				// Solr Base URL
	@Value("${pems.solr.documentId}")
	private String documentId;			// Solr Core Document Id
	@Value("${pems.solr.driver}")
	private String driver;				// Data Connection Driver
	@Value("${pems.solr.url}")
	private String jdbcURL;				// Data Connection URL
	@Value("${pems.solr.user}")
	private String username;			// DBMS ID
	@Value("${pems.solr.password}")
	private String password;			// DBMS PW

	@RequestMapping("")
	public ModelAndView autoComplete() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("f:search/autocomplete");
		return mav;
	}
	/**
	 * 검색 
	 * @param query
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/autocomplete")
	public ModelAndView search(@RequestParam(name="query", defaultValue="") String query, Model model) {
		// view객체정보를 셜정해주는 jsonView 이용 json형태로 클라이언트에 데이터 전달
		ModelAndView mav = new ModelAndView("jsonView");  
		// Solr 검색결과 가져오기(baseUrl : solrUrl / documentId : solr core명 / query : 검색데이터 )
		JSONArray solrResultArray = searchService.getSolrResult(baseUrl, documentId, query); 
		// 최종 결과값 data 로 전달
		mav.addObject("result", solrResultArray);
		return mav;
	}
	/**
	 * Solr 데이터 업데이트
	 */
	@RequestMapping(value = "/dataImport", method = RequestMethod.POST)
	public ModelAndView executeDataImport(Model model) {
		ModelAndView mav = new ModelAndView("jsonView");
		Boolean result = searchService.executeDataImport(baseUrl, documentId, driver, jdbcURL, username, password);
		mav.addObject("status", result);
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/mvWebPage")
	public ModelAndView mvWebPage(@RequestParam("code") String code) {
		ModelAndView mav = new ModelAndView("jsonView");
		if(code.contains("@")) {
			String[] splCode = code.split("@");
			System.out.println(splCode[0] + " : " + splCode[splCode.length-1]);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("userCode", splCode[0]);
			jsonObj.put("projCode", splCode[splCode.length-1]);
			JSONArray array = new JSONArray();
			array.add(jsonObj);
			mav.addObject("jsonObj", jsonObj);
		} else { 
			mav.addObject("code", code);
		}
		return mav;
	}
}
