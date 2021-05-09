package com.c4i.pms.web.method.serverpage;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.c4i.pms.main.proj.vo.ProjVO;
import com.c4i.pms.main.user.vo.UserVO;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public class ServerPageCommand {
	private int count = -1;
	private int length;
	private String order;
	private int page;
	private int paginateButtonlength;
	private Map<String, String[]> parameterMap;
	private ProjVO proj;
	private UserVO user;
	
	
	
	public String getOrder() {
		return order;
	}

	public UserVO getUser() {
		return user;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public ProjVO getProj() {
		return proj;
	}

	public void setProj(ProjVO proj) {
		this.proj = proj;
	}

	public int getCount() {
		if (-1 == count) {
			throw new IllegalStateException("count not set");
		}

		return count;
	}

	public int getLength() {
		if (length <= 0) {
			return 10;
		}

		return length;
	}

	public String getOrderBy() {
		if (StringUtils.isBlank(order)) {
			return "1";
		}

		return order;
	}

	public int getPage() {
		if (page <= 0) {
			return 1;
		}

		return page;
	}

	public int getPaginateButtonlength() {
		if (paginateButtonlength <= 0) {
			return 10;
		}

		return paginateButtonlength;
	}

	public Map<String, Object> getParam() {
		Builder<String, Object> builder = ImmutableMap.<String, Object>builder();
		Map<String, String[]> parameterMap = getParameterMap();

		parameterMap.forEach((k, v) -> {
			builder.put(k, v[0]);
		});

		return builder.build();
	}

	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	public int getStart() {
		return (getPage() - 1) * getLength();
	}

	public int getTotalPage() {
		return (getCount() - 1) / getLength() + 1;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setOrderBy(String order) {
		this.order = order;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPaginateButtonlength(int paginateButtonlength) {
		this.paginateButtonlength = paginateButtonlength;
	}

	public void setParameterMap(Map<String, String[]> parameterMap) {

		this.parameterMap = parameterMap;
	}
}
