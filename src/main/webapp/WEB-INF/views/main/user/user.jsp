
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<sec:authentication property="principal.auth" var="ss_auth" />
<sec:authentication property="principal.usId" var="ss_usId" />
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
				aria-controls="projList" aria-selected="true">
				<a href="/main/user">사용자 관리</a></li>
		</ol>
	</nav>
	<div class="main-scroll">
		<div role="tabpanel">
			<!-- 텝내용 구간 -->
			<div class="tab-content">
				<div class="tab-pane active" id="tab-01">
					<section class="search-wrap mt20">
						<div class="search-drop search-table">
							<div class="form-group search-table">
								<form id="form_main" action="/main/user" method="post" 
								class="form-group flex-center-wrap" autocomplete="off"
									  style="display: flex;">
									<!--
                                       input 사이즈
                                       w70, w80, w100, w150, w200, w300,
                                       w50p : 가로 50%
                                       w100p : 가로 100%
                                      -->
                                    <input type="hidden" id="csrfInfo" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<div class="input-box w150 mr10">
										<label for="select_user_name" class="small-text-01 mb10">성명</label>
										<input type="text" class="form-control select_user" id="select_user_name"  autocomplete="off"
											   onkeyup="enterKey()" name="userName" value="${searchUserData.userName}">
									</div>

									<div class="select-box-type2 w100 mr10">
										<input type="hidden"  id="ipt_user_post" value="${searchUserData.userPost}">
										<label for="select_user_post" class="small-text-01 mb10">직위</label>
										<select  class="form-control w100p select_user" id="select_user_post"
												 name="userPost">
											<option selected="selected" value="">선택</option>
											<option value="인턴">인턴</option>
											<option value="사원">사원</option>
											<option value="대리">대리</option>
											<option value="과장">과장</option>
											<option value="차장">차장</option>
											<option value="부장">부장</option>
											<option value="대표이사">대표이사</option>
										</select>
									</div>
									<div class="select-box-type2 w100 mr10">
										<input type="hidden"  id="ipt_user_level" value="${searchUserData.userLevel}">
										<label for="select_user_level" class="small-text-01 mb10">등급</label>
										<select  class="form-control select_user" id="select_user_level" name="userLevel">
											<option selected="selected" value="${searchUserData.userLevel}">선택</option>
											<option value="특급">특급</option>
											<option value="고급">고급</option>
											<option value="중급">중급</option>
											<option value="초급">초급</option>
										</select>
									</div>
									<div class="input-box" style="margin-top: 20px; margin-left: 20px">
										<button type="submit" id="btn_user_find" class="btn btn-type-03">검색</button>
										<button type="button" id="btn_user_clear" class="btn btn-type-04">초기화</button>
									</div>
								</form>
							</div>
						</div>
					</section>
					<!--  중간 통계 세션 -->
					<section class="list-table mt20">
						<article class="table-article">
							<div class="title-bar clearfix mb20">
								<button type="button" id="btn_user_del" class="btn btn-type-04"
										style="float: right; margin-right: 10px">삭제</button>
								<button type="button" id="btn_user_new" class="btn btn-type-03"
										style="float: right; margin-right: 10px">신규</button>
								<span class="table-title-01" data-title="Total">사용자 현황&nbsp;[ ${serverPage.count}명 ]</span>
								<div class="f-right">
								</div>
							</div>
							<div class="table-wrap">
								<div class="table-position row-line1" style="width : 100%; min-width: 60rem;">
									<div class="table-h-500">
										<table class="tbl04" id="table-total" >
											<caption>OO대테이블</caption>
											<colgroup>
												<!-- 체크박스 -->
												<col style="width: 4rem;" />
												<!-- 직위 -->
												<col style="width: 6rem;" />
												<!-- 성명 -->
												<col style="width: 6rem;" />
												<!-- 등급 -->
												<col style="width: 4rem;" />
												<!-- 부서-->
												<col style="width: 8rem;" />
												<!-- 연락처 -->
												<col style="width: 8rem;" />
												<!-- 이메일 -->
												<col style="width: 10rem;" />
												<!-- 최종수정일자 -->
												<col style="width: 8rem;" />
											</colgroup>
											<thead class="fixed-top" style="min-width: 60rem; width: 100%">
											<tr>
												<th scope="col" class="t-center" style="width: 4rem;" ><input type="checkbox" class="allCheckBox" style="margin-right: 27px"></th>
												<th style="width: 6rem;" class="t-left" scope="col">직위</th>
												<th style="width: 6rem;" class="t-left" scope="col">성명</th>
												<th style="width: 4rem;" class="t-left" scope="col">등급</th>
												<th style="width: 8rem;" class="t-left" scope="col">부서</th>
												<th style="width: 8rem;" class="t-left" scope="col">연락처</th>
												<th style="width: 10rem;"class="t-left" scope="col">이메일</th>
												<th style="width: 8rem;"class="t-left" scope="col">최종수정일자</th>
												
											</tr>
											</thead>
											<tbody>
											<c:choose>
												<c:when test="${empty serverPageSub}">
													<tr class="projList" id="${items.projCode}">
														<td colspan="7" id="proj_type" class="t-center">검색된 결과가 없습니다.</td>
													</tr>
												</c:when>
												<c:when test="${!empty serverPageSub}">
												<c:forEach items="${serverPageSub}" var="items">
													<tr>
														<th><input type="checkbox" scope="row" class="OneCheckBox" id="${items.userCode}" value="${items.userCode}"></th>
														<td id="user_post" class="t-left" onclick="mvPage('user', this)">${items.userPost}</td>
														<td id="user_name" style="white-space: pre;" class="t-left" onclick="mvPage('user', this)">${items.userName}</td>
														<td id="user_level" class="t-left" onclick="mvPage('user', this)">${items.userLevel}</td>
														<td id="user_buseo" style="white-space: pre;" class="t-left" onclick="mvPage('user', this)">${items.userBuseo}</td>
														<td id="user_tel" class="t-left" onclick="mvPage('user', this)">${items.userTel}</td>
														<td id="user_email" class="t-left" onclick="mvPage('user', this)">${items.userEmail}</td>
														<td id="user_date" class="t-left" onclick="mvPage('user', this)">${items.userModifyDate}</td>

													</tr>
											</c:forEach>
												</c:when>
											</c:choose>
											
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</article>
						<nav class="paging" aria-label="Page navigation">
							<ul class="pagination">
								<c:forEach items="${serverPage.pagination}" var="item"
										   varStatus="status">
									<c:if test="${item.label eq 'First'}">
										<li class="page-item">
										<a class="page-link" href="javascript:fnLoadUser('${item.queryString}')"
										 aria-label="First"> 
										 	<span class="pg-arrow-left pn2"></span>
										</a> 
										</li>
									</c:if>
									<c:if test="${item.label eq 'Previous'}">
										<li class="page-item">
										<a class="page-link" href="javascript:fnLoadUser('${item.queryString}')"
											aria-label="Previous"> 
											<span class="pg-arrow-left"></span>
										</a> 
										</li>
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
										</a>
										</li>
									</c:if>
									<c:if test="${item.label eq 'Last'}">
										<li class="page-item"><a class="page-link"
																 href="javascript:fnLoadUser('${item.queryString}')"
																 aria-label="Last"> <span class="pg-arrow-right pn2"></span>
										</a>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</nav>
						<div class="h30"></div>
					</section>
				</div>
			</div>
		</div>
	</div>


	<!-- 사용자 신규 팝업 -->
	<div class="modal fade" id="user_new" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-new modal-small">
			<div class="modal-content" style="left: 20rem;">
				<div class="modal-header dark">
					<h5 class="modal-title" id="exampleModalLabel">사용자 신규</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="detail_pop_form">
						<input type="hidden" id="_csrf" name="${_csrf.parameterName}" value="${_csrf.token}" />

						<section class="flex-center-top">
							<table class="tbl05">
								<caption>팝업테이블</caption>
								<colgroup>
									<col style="width: 30%;" />
									<col style="width: 70%;" />
								</colgroup>
								<tbody>
								<tr>
									<th>이름<label class="small-text-03 ml5">*</label></th>
									<td>
										<input type="text" class="form-control insert_user"  autocomplete="off" 
										id="input_user_name" name="userName" value=""/>
									</td>
								</tr>
								<tr style="height: 4rem;">
									<th>직위<label class="small-text-03 ml5">*</label></th>
									<td>
										<div class="select-box-type2 w100p">
											<select class="custom-select custom-select-sm form-control insert_user"
													id="input_user_post" name="userPost">
												<option value="" selected="selected">선택</option>
												<option value="인턴">인턴</option>
												<option value="사원">사원</option>
												<option value="대리">대리</option>
												<option value="과장">과장</option>
												<option value="차장">차장</option>
												<option value="부장">부장</option>
												<option value="대표이사">대표이사</option>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<th>등급<label class="small-text-03 ml5"></label></th>
									<td>
										<div class="select-box-type2 w100p">
											<select class="custom-select custom-select-sm form-control insert_user" 
													id="input_user_level" name="userLevel">
												<option value="" selected="selected">선택</option>
												<option value="특급">특급</option>
												<option value="고급">고급</option>
												<option value="중급">중급</option>
												<option value="초급">초급</option>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<th>부서<label class="small-text-03 ml5"></label></th>
									<td>
										<input type="text" class="form-control insert_user"  autocomplete="off" 
										id="input_user_buseo" name="userBuseo" value="" />
									</td>
								</tr>
								<tr>
									<th>연락처<label class="small-text-03 ml5"></label></th>
									<td>
										<div style="display: flex;">											
											<input type="text" class="form-control insert_user"  autocomplete="off" 
											id="input_user_tel" name="userTel" value="" onkeyup="inputPhoneNumber(this);"/>
										</div>
									</td>
								</tr>
								<tr>
									<th>이메일</th>
									<td>
										<input type="text" class="form-control insert_user"  autocomplete="off" 
										id="input_user_email" name="userEmail" value="" />
									</td>
								</tr>
								</tbody>
							</table>
						</section>
					</form>
				</div>
				<div class="modal-footer flex-center">
					<button type="button" id="btn_user_insert" class="btn btn-type-03"
							style="float: right; margin-right: 5px">등록</button>
					<button type="button" id="btn_user_cancel" class="btn btn-type-04" data-dismiss="modal"
							style="float: right; margin-right: 5px">취소</button>
				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript">
		//submit
		function fnLoadUser(param) {
			if (param) {
				comapp.util.setTagValue1('#form_main', param, [ 'page' ]);
			}
			comapp.submit({
				target : '#form_main',
				url : '/main/user',
				method : 'post'
			});
		}
	</script>