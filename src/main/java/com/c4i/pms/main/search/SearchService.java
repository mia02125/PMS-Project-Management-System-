package com.c4i.pms.main.search;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.c4i.pms.main.search.vo.SearchQueryVo;




@Service("SearchService")
@Transactional
public class SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    /**
     *  Solr Core 데이터 생성
     */
    public boolean executeDataImport(String baseUrl, String documentId, String driver, String jdbcURL, String username, String password){
        boolean status = true;
        SearchQueryVo searchQueryVo = new SearchQueryVo(baseUrl, documentId, driver, jdbcURL, username, password);
        // HTTP를 통해 Solr서버와 직접 통신하는 SolrCilent 빌드(구현)
        HttpSolrClient solrClient = new HttpSolrClient.Builder(searchQueryVo.getSolrBuildURL()).build();
        solrClient.setParser(new XMLResponseParser());
        // dataImport 쿼리(옵션) 
        ModifiableSolrParams solrParams = searchQueryVo.getSolrParams();
        try {
        	// enable.dih.dataConfigParam
            solrClient.query(solrParams); //dataImport 쿼리(옵션)를 solr서버에 perform 
        } catch (SolrServerException e) {
            LOGGER.error("Solr 서버와의 통신 중 오류가 발생하였습니다.", e);
            status = false;
        } catch (IOException e) {
            LOGGER.error("Solr 서버와의 통신 중 IO오류가 발생하였습니다.", e);
            status = false;
        }
        return status;
    }

    /**
     * 검색어 Solr 검색결과 가져오기
     *
     * @date 2020-10-14
     * @param query : input 창에 입력된 text
     * @return
     */
    @SuppressWarnings("unchecked")
	public JSONArray getSolrResult(String baseUrl, String documentId, String query) {
    	JSONArray solrResultArray =new JSONArray(); 
        SearchQueryVo searchQueryVo = new SearchQueryVo(baseUrl, documentId, query);

        if(searchQueryVo.statusSolrQuery()) {
        	// HTTP를 통해 Solr서버와 직접 통신하는 SolrCilent 빌드(구현)
        	HttpSolrClient solrClient = new HttpSolrClient.Builder(searchQueryVo.getSolrBuildURL()).build();
            solrClient.setParser(new XMLResponseParser());
            
            SolrQuery solrQuery = searchQueryVo.getSolrQuery(); // 검색 쿼리
            QueryResponse solrResponse = null;
            String pattern = "ㄸㄲ"; // 20201105_검색 오류 해결
            
            try {
            	// response 시 responseHeader에 결과값을 가져온다 
                solrResponse = solrClient.query(solrQuery); // solr서버에 연결하여 검색 쿼리를 response한다. 
            } catch (SolrServerException e) {
                LOGGER.error("Solr 서버와의 통신 중 오류가 발생하였습니다.", e);
            } catch (IOException e) {
                LOGGER.error("Solr 서버와의 통신 중 IO오류가 발생하였습니다.", e);
            }

            if(solrResponse != null) {
                for(SolrDocument solrDocument: solrResponse.getResults()) {
                    JSONObject userJSON =new JSONObject();
                    // 필드값들을 하나씩 JSONObject에 담는다. 
                    for(String fieldName : solrDocument.getFieldNames()) {
                        String resultValue = (String) solrDocument.getFieldValue(fieldName); 
                        resultValue = resultValue.replaceAll(pattern, " ");
                        userJSON.put(fieldName, resultValue);
                    }
                    solrResultArray.add(userJSON);
                }
            }
        }
        // 최종 결과값  전달
        return solrResultArray;
    }
}