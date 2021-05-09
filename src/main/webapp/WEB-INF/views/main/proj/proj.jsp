<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<sec:authentication property="principal.auth" var="ss_auth" />
<sec:authentication property="principal.usId" var="ss_usId" />
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<style>
.modal-content {
	width: 450px;
}
</style>
<div class="content-main-top">
	<nav class="bc-wrap" aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li class="breadcrumb-item active" id="projList_tab"
				aria-controls="projList" aria-selected="true" style="color: blue;">
				<a href="/main/proj">프로젝트 관리</a>
			</li>
		</ol>
	</nav>
</div>
<div class="main-scroll">
	<div role="tabpanel">
		<!-- 텝내용 구간 -->
		<div class="tab-content">
			<div class="tab-pane active" id="tab-01">
				<section class="search-wrap mt20">
					<!-- <span class="search-view"></span> -->
					<!-- 서치박스 펴기/접기 버튼 추가 -->
					<div class="search-drop search-table">
						<div class="form-group">
							<form id="form_main" action="/main/proj"
								method="post" class="form-group" autocomplete="off"
								style="display: flex;">
								<!--
                                   input 사이즈
                                   w70, w80, w100, w150, w200, w300,
                                   w50p : 가로 50%
                                   w100p : 가로 100%
                                   form-control : input select 등 입력값 UI 변경
                                -->
								<input type="hidden" id="csrfInfo" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								
								<div class="form-row">
									<!-- from-row 어떻게 활용될까?? -->
									<div class="input-box w200 mr10">
										<label for="select_proj_name" class="small-text-01 mb10">프로젝트명</label>
										<input type="text" class="form-control select_proj" id="select_proj_name"
											onkeyup="enterKey()" autocomplete="off" name="projName"
											value="${searchProjData.projName}">
									</div>
									<div class="select-box-type2 w150 mr10">
										<label for="select_proj_type" class="small-text-01 mb10">유형</label>
										<input type="text" class="form-control select_proj" id="select_proj_type"
											onkeyup="enterKey()" autocomplete="off" name="projType"
											value="${searchProjData.projType}">
									</div>
									<Input type="hidden" id="ipt_proj_level" value="${searchProjData.projLevel}">
									<div class="select-box-type2 w80 mr10">
										<label for="select_proj_level" class="small-text-01 mb10">등급</label>
										<select id="select_proj_level" name="projLevel"
											class="w100p form-control select_proj">
											<option selected="selected" value="">선택</option>
											<option value="S">S</option>
											<option value="A">A</option>
											<option value="B">B</option>
											<option value="C">C</option>
											<option value="D">D</option>
										</select>
									</div>
								</div>
								<div class="input-box"
									style="margin-top: 25px; margin-left: 20px;">
									<button type="submit" id="btn_find" class="btn btn-type-03 btn_find">검색</button>
									<button type="button" id="btn_proj_clear" class="btn btn-type-04">초기화</button>
								</div>
							</form>
						</div>
					</div>
				</section>
				<!-- 상단 섹션 -->
				<section class="section-top mt20">
					<article class="n-box">
						<div class="n-bar t-right bg">
							<span class="text-01 color04">미진행</span>
						</div>
						<div class="n-body t-right">
							<span class="number-text-01">${detailProjCount.noneCount}</span>
						</div>
					</article>
					<article class="n-box">
						<div class="n-bar t-right bg">
							<span class="text-01 color04">진행중</span>
						</div>
						<div class="n-body t-right">
							<span class="number-text-01">${detailProjCount.startCount}</span>
						</div>
					</article>
					<article class="n-box">
						<div class="n-bar t-right bg">
							<span class="text-01 color04">진행완료</span>
						</div>
						<div class="n-body t-right">
							<span class="number-text-01">${detailProjCount.finishCount}</span>
						</div>
					</article>
					<article class="n-box">
						<div class="n-bar t-right bg">
							<span class="text-01 color04">총 예산</span>
						</div>
						<div class="n-body t-right">
							<span class="number-text-01">${totalBudget}&nbsp;원</span>
						</div>
					</article>
				</section>
				<!--  중간 통계 세션 -->
				<section class="list-table mt20">
					<article class="table-article">
						<div class="title-bar clearfix mb20">
							<button type="button" id="btn_proj_del" class="btn btn-type-04"
								style="float: right; margin-right: 10px">삭제</button>
							<button type="button" id="btn_proj_new" class="btn btn-type-03"
								style="float: right; margin-right: 10px">신규</button>
							<span class="table-title-01" data-title="Total">프로젝트 현황&nbsp;[ ${serverPage.count}건 ]</span>
							<div class="f-right"></div>
						</div>
						<div class="table-wrap">
							<div class="table-position row-line1"
								style="min-width: 100rem; width: 100%;">
								<div class="table-wrap table-h-400">
								<form action="post" action="/main/proj">
									<table class="tbl02" id="table-total">
										<caption>OO대테이블</caption>
										<colgroup>
											<!-- 체크박스 -->
											<col style="width: 4rem;" />
											<!-- 프로젝트명 -->
											<col style="width: 15rem;" />
											<!-- 유형 -->
											<col style="width: 5rem;" />
											<!-- 등급 -->
											<col style="width: 5rem;" />
											<!-- 담당자 -->
											<col style="width: 6rem;" />
											<!-- 예산 -->
											<col style="width: 7rem;" />
											<!-- 진행상태 -->
											<col style="width: 8rem;" />
											<!-- 시작일자 -->
											<col style="width: 8rem;" />
											<!-- 완료일자 -->
											<col style="width: 8rem;" />
											<!-- 수행일자 -->
											<col style="width: 8rem;" />
											<!-- 최종수정일  -->
											<col style="width: 6rem;" />
											<!-- 남은기간 -->
											<col style="width: 6rem;" />
											<!-- 투입인원 -->
											<col style="width: 5rem;" />
										</colgroup>
										<thead class="fixed-top" style="width: 100%; min-width: 100rem;">
											<tr>
												<th class="t-center" style="width: 4rem;"><input type="checkbox" 
													style="margin-left: 4px"
													class="allCheckBox"></th>
												<th style="width: 15rem;" class="t-left" scope="col">프로젝트명</th>
												<th style="width: 5rem;" class="t-left" scope="col">유형</th>
												<th style="width: 5rem;" class="t-left" scope="col">등급</th>
												<th style="width: 6rem;" class="t-left" scope="col">소속/담당자</th>
												<th style="width: 7rem;" class="t-right" scope="col">예산</th>
												<th style="width: 8rem;" class="t-left" scope="col">진행상태</th>
												<th style="width: 8rem;" class="t-left" scope="col">시작일자</th>
												<th style="width: 8rem;" class="t-left" scope="col">완료일자</th>
												<th style="width: 8rem;" class="t-left" scope="col">최종수정일자</th>
												<th style="width: 6rem;" class="t-left" scope="col">수행기간</th>
												<th style="width: 6rem;" class="t-left" scope="col">남은기간</th>
												<th style="width: 5rem;" class="t-left" scope="col">투입인원</th>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${empty serverPageSub}">
													<tr class="projList"  id="${items.projCode}">
														<td colspan="14" id="proj_type" class="t-center">검색된
															결과가 없습니다.</td>
													</tr>
												</c:when>
												<c:when test="${!empty serverPageSub}">
													<c:forEach var="items" items="${serverPageSub}">
														<tr class="projList" id="${items.projCode}">
															<td><input type="checkbox" class="OneCheckBox"
																id="${items.projCode}" value="${items.projCode}"></td>
															<td id="proj_name" class="t-left" 
																style="text-overflow: ellipsis; white-space: pre" 
																onclick="mvPage('proj', this)">${items.projName}</td>
															<td id="proj_type" class="t-left" style="white-space: pre"
																onclick="mvPage('proj', this)">${items.projType}</td>
															<td id="proj_level" class="t-left"
																onclick="mvPage('proj', this)">${items.projLevel}</td>
															<td id="proj_manager" class="t-left"
																onclick="mvPage('proj', this)">${items.projManagerComp}<br>${items.projManagerPost}&nbsp;${items.projManagerName}</td>
															<td id="proj_budget" class="t-right"
																onclick="mvPage('proj', this)">${items.projBudget}&nbsp;원</td>
															<c:choose>
																<c:when test="${items.projStatus eq '미진행'}">
																	<td id="proj_status" style="color: red" class="t-left"
																		onclick="mvPage('proj', this)">${items.projStatus}</td>
																</c:when>
																<c:when test="${items.projStatus eq '진행중'}">
																	<td id="proj_status" style="color: green;"
																		class="t-left"
																		onclick="mvPage('proj', this)">${items.projStatus}</td>
																</c:when>
																<c:when test="${items.projStatus eq '진행완료'}">
																	<td id="proj_status" class="t-left"
																		onclick="mvPage('proj', this)">${items.projStatus}</td>
																</c:when>
																<c:otherwise>
																	<td id="proj_status" class="t-left"
																		onclick="mvPage('proj', this)">${items.projStatus}</td>
																</c:otherwise>
															</c:choose>
															<td id="proj_inDate" class="t-left proj_inDate"
																onclick="mvPage('proj', this)">${items.projInDate}</td>
															<td id="proj_outDate" class="t-left proj_outDate"
																onclick="mvPage('proj', this)">${items.projOutDate}</td>
															<td id="proj_modifyDate" class="t-left "
																onclick="mvPage('proj', this)">${items.projModifyDate}</td>
															<td id="proj_Date" class="t-left"
																onclick="mvPage('proj', this)">${items.projDate}&nbsp;일</td>
															<c:choose>
																<c:when test="${items.projLeftDate le 0}">
																	<td id="proj_leftDate" style="color: red"
																		class="t-left proj_leftDate"
																		onclick="mvPage('proj', this)">${items.projLeftDate-items.projLeftDate*2}&nbsp;일[초과]</td>
																</c:when>
																<c:when test="${items.projLeftDate ge 0}">
																	<td id="proj_leftDate" class="t-left"
																		onclick="mvPage('proj', this)">${items.projLeftDate}&nbsp;일</td>
																</c:when>
																<c:otherwise>
																	<td id="proj_leftDate" class="t-left"
																		onclick="mvPage('proj', this)">${items.projLeftDate}&nbsp;일</td>
																</c:otherwise>
															</c:choose>
															<td id="proj_inputCount" class="t-left"
																onclick="mvPage('proj', this)">${items.projInputCount}&nbsp;명</td>
														</tr>
													</c:forEach>
												</c:when>
											</c:choose>
										</tbody>
									</table>
									</form>
								</div>
							</div>
						</div>
						<nav class="paging" aria-label="Page navigation">
							<ul class="pagination">
								<c:forEach items="${serverPage.pagination}" var="item"
									varStatus="status">
									<c:if test="${item.label eq 'First'}">
										<li class="page-item"><a class="page-link"
											href="javascript:fnLoadUser('${item.queryString}')"
											aria-label="First"> <span class="pg-arrow-left pn2"></span>
										</a></li>
									</c:if>
									<c:if test="${item.label eq 'Previous'}">
										<li class="page-item"><a class="page-link"
											href="javascript:fnLoadUser('${item.queryString}')"
											aria-label="Previous"> <span class="pg-arrow-left"></span>
										</a></li>
									</c:if>

									<c:if
										test="${item.label ne 'First' and item.label ne 'Previous' and item.label ne 'Next' and item.label ne 'Last'}">
										<li class="page-item wauto"><a
											class="page-link <c:if test="${item.clazz eq 'active'}">active</c:if>"
											href="javascript:fnLoadUser('${item.queryString}')">${item.label}</a>
										</li>
									</c:if>

									<c:if test="${item.label eq 'Next'}">
										<li class="page-item"><a class="page-link"
											href="javascript:fnLoadUser('${item.queryString}')"
											aria-label="Next"> <span class="pg-arrow-right"></span>
										</a></li>
									</c:if>
									<c:if test="${item.label eq 'Last'}">
										<li class="page-item"><a class="page-link"
											href="javascript:fnLoadUser('${item.queryString}')"
											aria-label="Last"> <span class="pg-arrow-right pn2"></span>
										</a></li>
									</c:if>
								</c:forEach>
							</ul>
						</nav>
					</article>
					<div class="h30"></div>
				</section>
			</div>
		</div>
	</div>
</div>


<!-- 프로젝트 등록 팝업  -->
<div class="modal fade" id="proj_new" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-new modal-small">
		<div class="modal-content" style="left: 20rem;">
			<div class="modal-header dark">
				<h5 class="modal-title" id="exampleModalLabel"></h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form id="detail_pop_form">
					<input id="csrfValue" type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<section class="flex-center-top">
						<table class="tbl05">
							<caption>팝업테이블</caption>
							<colgroup>
								<col style="width: 30%;" />
								<col style="width: 70%;" />
							</colgroup>
							<tbody>
								<tr>
									<th>프로젝트명<label class="small-text-03 ml5">*</label></th>
									<td><input type="text" class="form-control insert_proj"  maxlength="50"
										id="input_proj_name" autocomplete="off" name="projName"
										value="" /></td>
								</tr>
								<tr>
									<th>예산(원)<label class="small-text-03 ml5">*</label></th>
									<td><input type="text" class="form-control insert_proj" maxlength="14"
										id="input_proj_budget" autocomplete="off" name="projBudget"
										value="" /></td>
								</tr>
								<tr>
									<th>시작일자<label class="small-text-03 ml5">*</label></th>
									<td><input type="text"
										class="datepicker form-control insert_proj" maxlength="10"
										id="input_proj_inDate" onkeyup="inputDateNumber(this)"
										autocomplete="off" name="projInDate" value="" /></td>
								</tr>
								<tr>
									<th>완료일자<label class="small-text-03 ml5">*</label></th>
									<td><input type="text"
										class="datepicker form-control insert_proj" maxlength="10"
										id="input_proj_outDate" onkeyup="inputDateNumber(this)"
										autocomplete="off" name="projOutDate" value="" /></td>
								</tr>
								<tr style="height: 4rem;">
									<th>유형<label class="small-text-03 ml5"></label></th>
									<td><input type="text" class="form-control insert_proj" maxlength="10"
										id="input_proj_type" autocomplete="off" name="projType"
										value="" /></td>
								</tr>
								<tr>
									<th>등급<label class="small-text-03 ml5"></label></th>
									<td>
										<div class="select-box-type2 w100p">
											<select
												class="custom-select custom-select-sm form-control insert_proj"
												id="input_proj_level" name="projLevel">
												<option value="" selected="selected">선택</option>
												<option value="S">S</option>
												<option value="A">A</option>
												<option value="B">B</option>
												<option value="C">C</option>
												<option value="D">D</option>
											</select>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</section>
				</form>
			</div>
			<div class="modal-footer flex-center">
				<button type="button" id="btn_proj_insert" class="btn btn-type-03"
					style="float: right; margin-right: 5px">등록</button>
				<button type="button" id="btn_proj_cancel" class="btn btn-type-04"
					data-dismiss="modal" style="float: right; margin-right: 5px">취소</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	//submit
	function fnLoadUser(param){
		if(param){
			comapp.util.setTagValue1('#form_main', param, ['page']);
		}
		comapp.submit({
			target      : '#form_main',
			url         : '/main/proj',
			method      : 'post'
		});
	}
	

</script>