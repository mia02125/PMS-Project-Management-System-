package com.c4i.pms.web.method.serverpage;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.c4i.pms.main.proj.vo.ProjVO;
import com.c4i.pms.main.user.vo.UserVO;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableMap;

public class ServerPage {
	private int count;
	private List<Map<String, Object>> list;
	private List<UserVO> userList;
	private List<ProjVO> projList;
	private int page;
	private String selStartPage;
	private String selEndPage;
	private int paginateButtonlength;
	private Map<String, String[]> parameterMap;
	private int start;
	private int totalPage;

	public ServerPage(ServerPageCommand command, List<Map<String, Object>> list, List<UserVO> userList, List<ProjVO> projList) {
		this.totalPage = command.getTotalPage();
		this.page = command.getPage();
		this.paginateButtonlength = command.getPaginateButtonlength();
		this.list = list;
		this.userList = userList;
		this.projList = projList;
		this.count = command.getCount();
		this.start = command.getStart();
		this.parameterMap = command.getParameterMap();
	}

	
	
	public List<ProjVO> getProjList() {
		return projList;
	}



	public void setProjList(List<ProjVO> projList) {
		this.projList = projList;
	}



	public int getCount() {
		return count;
	}

	public String getInformation() {
		int from = getStart() + 1;
		int to = from + getList().size() - 1;

//		return String.format("Showing %d to %d of %d entries", from, to, getCount());
		return String.format("%d (%d/%d)", getCount(), from, to);
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public List<UserVO> getUserList() {
		return userList;
	}

	public int getPage() {
		return page;
	}

	public String getSelStartPage() {
		return selStartPage;
	}

	public String getSelEndPage() {
		return selEndPage;
	}

	public int getPaginateButtonlength() {
		return paginateButtonlength;
	}

	public List<Map<String, String>> getPagination() {
		int page 		= getPage();
		int first 		= 1;
		int last 		= getTotalPage();
		int startPage 	= (page - 1) / paginateButtonlength * paginateButtonlength + 1;
		int endPage 	= startPage + (paginateButtonlength - 1) < last ? startPage + (paginateButtonlength - 1) : last;
		int previous 	= paginateButtonlength < endPage ? endPage - paginateButtonlength : 1;
		int next 		= startPage + paginateButtonlength < last ? startPage + paginateButtonlength : last;

		ImmutableList.Builder<Map<String, String>> listBuilder = ImmutableList.<Map<String, String>>builder();

		listBuilder.add(ImmutableMap.<String, String>builder().put("label", "First")
				.put("queryString", getQueryString(first)).build());
		listBuilder.add(ImmutableMap.<String, String>builder().put("label", "Previous")
				.put("queryString", getQueryString(previous)).build());

		for (int i = startPage; i <= endPage; i++) {
			ImmutableMap.Builder<String, String> builder = ImmutableMap.<String, String>builder();
			builder.put("label", Integer.toString(i)).put("queryString", getQueryString(i));

			if (page == i) {
				builder.put("clazz", "active");
			}

			listBuilder.add(builder.build());
		}

		listBuilder.add(ImmutableMap.<String, String>builder().put("label", "Next")
				.put("queryString", getQueryString(next)).build());
		listBuilder.add(ImmutableMap.<String, String>builder().put("label", "Last")
				.put("queryString", getQueryString(last)).build());

		this.selStartPage 	= Integer.toString(startPage);
		this.selEndPage 	= Integer.toString(endPage);
		
		return listBuilder.build();
	}

	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	public int getStart() {
		return start;
	}

	public int getTotalPage() {
		return totalPage;
	}

	private String getQueryString(int page) {
		if (page <= 0) {
			page = 1;
		}
		if (totalPage < page) {
			page = totalPage;
		}

		String queryString = queryString();

		if (StringUtils.isNotBlank(queryString)) {
			return "page=" + Integer.toString(page) + "&" + queryString;
		}

		return "page=" + Integer.toString(page);
	}

	private String queryString() {
		Builder<String> builder = ImmutableList.<String>builder();
		Map<String, String[]> param = getParameterMap();

		param.forEach((k, v) -> {
			if (StringUtils.equals("page", k)) {
				return;
			}

			for (String value : v) {
				builder.add(k + "=" + value);
			}
		});

		return StringUtils.join(builder.build(), "&");
	}
}
