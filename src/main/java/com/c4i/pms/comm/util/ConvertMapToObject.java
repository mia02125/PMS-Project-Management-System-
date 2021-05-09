package com.c4i.pms.comm.util;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
/*
 * Map to VO
 * 요청받은 Map형식 데이터를 해당 객체클래스와 비교해서 Key값이 속성값(컴럼)과 일치한다면 
 * 해당 컬럼의 데이터값을 setter함  
 *  
 */
public class ConvertMapToObject {	
	public static Object convertMapToObject(Map<String,Object> map,Object obj) {
	    String keyAttribute = null;
	    String setMethodString = "set";
	    String methodString = null;
	    Iterator itr = map.keySet().iterator();

	    while(itr.hasNext()){
	        keyAttribute = (String) itr.next();
	        methodString = setMethodString+keyAttribute.substring(0,1).toUpperCase()+keyAttribute.substring(1);
	        Method[] methods = obj.getClass().getDeclaredMethods();
	        for(int i=0;i<methods.length;i++){
	            if(methodString.equals(methods[i].getName())){
	                try{
	                    methods[i].invoke(obj, map.get(keyAttribute));
	                }catch(Exception e){
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	    return obj;
	}
}