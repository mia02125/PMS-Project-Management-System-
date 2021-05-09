package com.c4i.pms.main.search.vo;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *  Solr Query Value Object
 */
public class SearchQueryVo {
	// solr 쿼리 중 컬럼이 아니라 field로 정의한 name을 명시해야한다.  			
	private final String[] solrFiledList = {"id","c4i_NAME","c4i_CHAR1","c4i_DIVIS","c4i_CHAR2","c4i_CHAR3"};  // solr에서 가져올 결과값(넣은 순서대로 결과값 반환)
    private final String[][] sortFiledList = {{"c4i_NAME", "asc"},{"c4i_DIVIS", "asc"}};   // solr 결과 정렬 방법
    private final int resultCount = 10; // select로 가져올 데이터 갯수
    private final String dataImportQuery =  
    		// dataimport 쿼리
    		"SELECT"
    		+ 	" CONCAT(COALESCE(ppu.USER_CODE,'00'),'@',pp.PROJ_CODE) AS id,"
    		+ 	" CONCAT(REPLACE(pp.PROJ_NAME,' ','ㄸㄲ'),'') AS c4i_NAME,"
    		+ 	" COALESCE(pu.USER_NAME,'미지정') AS c4i_CHAR1,"
    		+   " '프로젝트' AS c4i_DIVIS,"
    		+ 	" COALESCE(ppu.USER_DIVISION,'미지정') AS c4i_CHAR2,"
    		+ 	" COALESCE(pp.PROJ_STATUS,'미지정') AS c4i_CHAR3"
    		+ " FROM pems_cjm.pms_proj pp"
    		+ " LEFT OUTER JOIN pems_cjm.pms_proj_user ppu"
    		+ " ON pp.PROJ_CODE = ppu.PROJ_CODE"
    		+ " LEFT OUTER JOIN pems_cjm.pms_user pu"
    		+ " ON pu.USER_CODE = ppu.USER_CODE"
    		+ " UNION ALL"
    		+ " SELECT"
    		+ 	" USER_CODE AS id,"
    		+ 	" COALESCE(USER_NAME,'') AS c4i_NAME,"
    		+ 	" COALESCE(USER_LEVEL,'') AS c4i_CHAR2,"
    		+   " '사용자' AS c4i_DIVIS,"
    		+ 	" COALESCE(USER_POST,'') AS c4i_CHAR1,"
    		+ 	" COALESCE(USER_EMAIL,'') AS c4i_CHAR3"
    		+ " FROM pems_cjm.pms_user pu"
    		+ " WHERE pu.USER_DELETE = 'N'";
    // [ 고정 ]
    private String solrRestBaseURL = "";                                            	// solr api url
    private String solrDocumentId = "";                                             	// solr document id
    private String originTxtQuery = "";                                             	// 요청 쿼리
    private String solrTxtQuery = "";                                               	// solr 쿼리 text용
    private final SolrQuery solrObjQuery = new SolrQuery();                        		// solr 쿼리 object용
    private final ModifiableSolrParams solrParams = new ModifiableSolrParams();     	// solr dataimport query
    private final Map<String, String> dataSourceParam = new HashMap<String, String>();  // solr dataimport용 임시 param
    

    // [#1] 생성자 : dataimport 수행 (순서 1) 
    public SearchQueryVo(String baseUrl, String documentId, String driver, String jdbcUrl, String username, String password){
        this.solrRestBaseURL = baseUrl;
        this.solrDocumentId = documentId;
        // jdbc연결에 필요한 데이터 
        this.dataSourceParam.put("driver", driver);
        this.dataSourceParam.put("jdbcUrl", jdbcUrl);
        this.dataSourceParam.put("username", username);
        this.dataSourceParam.put("password", password);
        // dataImport 옵션 
        this.solrParams.set("qt", "/dataimport");
        this.solrParams.set("clean", "true"); // 기존 데이터 clean
        this.solrParams.set("command", "full-import"); // (full-import : 기존 문서 삭제 후 외부 데이터로 index 구성 / Delta-import : 마지막 import 시점 부터 index추가 및 변경  
        this.solrParams.set("dataConfig", getDataImportQuery());
        
    }
    // [#1] dataimport 쿼리 (순서 2) 
    public ModifiableSolrParams getSolrParams() {
        return this.solrParams; 
    }
    // [#1] 데이터 진행확인 쿼리_20201105
    public ModifiableSolrParams getSolrStatusParams(){
        ModifiableSolrParams solrParams = new ModifiableSolrParams();
        this.solrParams.set("qt", "/dataimport"); //  Request-Handler 
        this.solrParams.set("command", "status");  
        this.solrParams.set("indent", "on"); // off : 결과물이 일렬로 나열
        this.solrParams.set("wt", "json"); // writer type : 결과 형태(json)
        return solrParams;
    }    
    // [#2] 생성자 : select 쿼리
    public SearchQueryVo(String baseUrl, String documentId, String query){
        this.solrRestBaseURL = baseUrl;
        this.solrDocumentId = documentId;

        String convertQuery = query.replaceAll("[*]", "");								// * 제외
        convertQuery = convertQuery.replaceAll("\\s+$",""); 							// RTRIM
        convertQuery = convertQuery.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s,]", "");	// 특수문자는 like검색으로 대체
        convertQuery = (convertQuery.length()==1) ? convertQuery.replaceAll("[-]", "") : convertQuery;

        this.originTxtQuery = query;
        this.solrTxtQuery = convertQuery;
        this.setSolrQuery(this.solrTxtQuery);
    }
    // [#2] Select 특수문자 처리용 replaceall
    private String replaceCharacters(String targetString){
        String resultString = "";
        resultString = targetString.replaceAll("( )+", "ㄸㄲ"); 	// 띄어쓰기 검색을 위해 변경
        resultString = resultString.replaceAll("[#]+", " "); 	// 특수문자 # -> 띄어쓰기로 변경
        resultString = resultString.replaceAll("\\s+$","");     // 우측 띄어쓰기는 항상 제외
        resultString = resultString.replaceAll("[*]", "");		// 전체검색 방지
        return resultString;
    }
    private void addFilterQuery(String query){
        query = query.replaceAll("^\\s+", ""); 		// LTRIM
        query = replaceCharacters(query);
        solrObjQuery.addFilterQuery("content_txt_ko:*" + query + "*");
    };
    // [#2] solr 쿼리용으로 설정
    private void setSolrQuery(String inputText) {
        String[] inputTextList = inputText.split(",");
        // 단일쿼리
        if(inputTextList.length == 1 && inputText.contains(" ") == false){
        	String query = replaceCharacters(inputTextList[0]);
            solrObjQuery.setQuery("content_txt_ko:*" + query + "*");
        }
        // 다중쿼리
        else if(inputTextList.length > 1 || inputText.contains(" ") == true){
            for(int idx=0; idx<inputTextList.length; idx++){
                String query = inputTextList[idx];
                if(idx == 0){
                    query = replaceCharacters(query); // 유효성 검사 
                    if(query.contains(" ")) {
                        query = query.split(" ")[0];
                    }
                    solrObjQuery.setQuery("content_txt_ko:*" + query + "*");
                }else{
                    addFilterQuery(query);
                }
                // 띄어쓰기 일 경우에는 filter query로 추가함
                String filterQuery = inputTextList[idx].replaceAll("^\\s+", ""); 		// LTRIM
                if(inputTextList[idx].contains(" ")){
                	String[] queryArray = replaceCharacters(filterQuery).split(" ");
                    int startIdx = ((idx == 0) ? 1 : 0);
                    for(int innerIdx=startIdx; innerIdx<queryArray.length; innerIdx++){
                        addFilterQuery(queryArray[innerIdx]);
                    }
                }
            }
        }
        // 가져올 정보 필드 목록
        String txtFields = "";
        for(int idx=0; idx<solrFiledList.length; idx++){
        	// 갯수만큼 fileName을 txtFields에 담는다 
            txtFields += ((idx==0) ? "" : ",") + solrFiledList[idx];
        }
        solrObjQuery.setFields(txtFields);
        // 정렬
        List<SolrQuery.SortClause> sortClauseList = new ArrayList<SolrQuery.SortClause>();
        for(String[] sortFieldInfo : sortFiledList){
            String sortField = sortFieldInfo[0];
            SolrQuery.ORDER sortOrder = (sortFieldInfo[1].equals("asc") ? SolrQuery.ORDER.asc : SolrQuery.ORDER.desc);
            sortClauseList.add(new SolrQuery.SortClause(sortField, sortOrder));
        }
        solrObjQuery.setSorts(sortClauseList);
        // 가져올 데이터 갯수
        solrObjQuery.setRows(resultCount);
    }
    // [#2] Solr Query 적용가능 여부 확인
    public boolean statusSolrQuery(){
        return (!this.solrTxtQuery.equals("") && !originTxtQuery.trim().equals(""));
    }
    // [#2] Solr REST API URL
    public String getSolrBuildURL(){
        return this.solrRestBaseURL + this.solrDocumentId;
    }
    // [#2] Solr REST API Query
    public SolrQuery getSolrQuery(){
        return this.solrObjQuery;
    }
    // [#1] data-config XML파일을 String 형태로 변환
    public String getDataImportQuery()  {
    	// 새로운 인스턴스를 얻는다.(문서를 읽기위한 공장이라 생각하자) 
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        // 빌더 생성 
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = docBuilder.newDocument(); 
        // 생성된 빌더를 통해서 dataConfig를 doc객체로 파싱(182~196)
        // dataConfig  
        Element rootElement = doc.createElement("dataConfig");
        doc.appendChild(rootElement);

        // - dataSource
        Element dataSource = doc.createElement("dataSource");
        rootElement.appendChild(dataSource);
        dataSource.setAttribute("type", "JdbcDataSource");
        dataSource.setAttribute("driver", this.dataSourceParam.get("driver"));
        dataSource.setAttribute("url", this.dataSourceParam.get("jdbcUrl"));
        dataSource.setAttribute("user", this.dataSourceParam.get("username"));
        dataSource.setAttribute("password", this.dataSourceParam.get("password"));

        // - document
        Element document = doc.createElement("document");
        rootElement.appendChild(document);

        // - document - entity
        Element entity = doc.createElement("entity");
        entity.setAttribute("name", "pms");
        entity.setAttribute("query", this.dataImportQuery);

        // - document - entity - field
        for(String field : this.solrFiledList){
            Element tmpField = doc.createElement("field");
            tmpField.setAttribute("column", field);
            tmpField.setAttribute("name", field);
            entity.appendChild(tmpField);

            // ID값은 검색에 제외
            if(!field.equals("id")) {
            	Element tmpSearchField = doc.createElement("field");
            	tmpSearchField.setAttribute("column", field);
            	tmpSearchField.setAttribute("name", "content_txt_ko");
            	entity.appendChild(tmpSearchField);
            }
        }
        document.appendChild(entity);

        //XML 문자열로 변환
        String resultStringXML = "";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(out);
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            resultStringXML = "";
        }

        // 출력 시 문자코드: UTF-8
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        // 들여 쓰기 있음
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            resultStringXML = "";
        }
        resultStringXML = new String(out.toByteArray(), StandardCharsets.UTF_8);
        return resultStringXML;
    }
}
