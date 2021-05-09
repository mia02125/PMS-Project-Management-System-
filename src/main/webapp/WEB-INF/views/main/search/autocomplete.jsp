<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="content-main-top">
	<nav class="bc-wrap" aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active" aria-current="page"><a href="#none">검색</a></li>
		</ol>
	</nav>
</div>
<div class="main-scroll">
	<div role="tabpanel">
		<div class="tab-content">
			<div class="tab-pane active" id="tab_biz02">
				<div class="search-drop search-table">
					<div class="input-box w80p mr10">
						<form id="form_main" class="form-group" style="display: flex; margin-left: 30%">
							<input type="text" autocomplete="off" onkeyup="enterKey();" style="height: 3rem; font-size: 16px !important;" 
							class="form-control search_txt"  id="search_txt" name="search_txt" value="" onfocus="this.value = this.value;" placeholder="검색어를 입력하세요. ex) PMS 프로젝트 인력관리 시스템, 홍길동, 진행완료">
							<button type="button" id="btn_find_search"  class="btn btn-type-03">검색</button>
							<button type="button" id="btn_update_search" class="btn btn-type-05">갱신</button>
							<input id="csrfVal" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
		            </div>
				</div>
			</div>
		</div>
	</div>
</div>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">	<!-- 자동검색 목록을 보여주기 위한 css구성 -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>						<!-- autocomplete-->

<style>
	/* 자동완성 ul*/
	.ui-menu.ui-widget.ui-widget-content.ui-autocomplete {
		z-index: 9999;
		font-size: 0.8rem;
		line-height: 30px;
	}
	/* 자동완성 목록 hover 스타일 */
	.ui-menu-item .ui-menu-item-wrapper.ui-state-active {
		background: #e2e1f5 !important;
		font-weight: bold !important;
	}
	.ui-menu-item div.ui-menu-item-wrapper{
		padding-left: 10px;
	}
</style>

