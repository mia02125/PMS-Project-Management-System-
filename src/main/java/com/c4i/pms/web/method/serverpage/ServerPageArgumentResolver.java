package com.c4i.pms.web.method.serverpage;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

public class ServerPageArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return ServerPageCommand.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		Map<String, String[]> parameterMap = webRequest.getParameterMap();
		parameterMap.forEach((k, v) -> {
			propertyValues.addPropertyValue(k, v);
		});

		ServerPageCommand table = new ServerPageCommand(); 

		DataBinder binder = new DataBinder(table);
		binder.bind(propertyValues); // 파라미터값을 테이블에 바인딩(할당)을 해주지 않는다면  ~

		table.setParameterMap(parameterMap);
		
		return table; // ~ null값이 반환되면 excution.do 메서드가 nullPointer 오류 
	}
}
