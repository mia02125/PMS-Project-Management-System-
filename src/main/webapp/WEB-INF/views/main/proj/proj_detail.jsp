<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<sec:authentication property="principal.auth" var="ss_auth" />
<sec:authentication property="principal.usId" var="ss_usId" />
<style>
	.modal-content {
		height: 900px;
		width: 100%;
	}
	.n-title-box {
		display: inline;
	}
	.n-info-box {
		height: 2rem;
	}
</style>
<div class="content-main-top">
	<nav class="bc-wrap" aria-label="breadcrumb">
	
		<ol class="breadcrumb">
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li class="breadcrumb-item active" id="projList_tab"
				aria-controls="projList" aria-selected="true">
				<a href="/main/proj" style="color: gray;">프로젝트 관리</a></li>
			<li class="breadcrumb-item active" id="projDetail_tab"
				data-toggle="tab" aria-selected="false"
				role="tab" aria-controls="projDetail" >
				<a href="/main/proj/detail?projCode=${projInfo.projCode}">프로젝트 상세정보</a></li>
		</ol>
	</nav>
	<div class="main-scroll">
		<div role="tabpanel">
			<!-- 텝내용 구간 -->
			<div class="tab-content">
				<div class="tab-pane active" id="tab-01">
				<section class="section-top n-box-wrap flex-center-col mt20">
					<div class="w100p">
						<div class="w100p flex-center-start">
							<input type="hidden" id="projCodeInfo" value="${projInfo.projCode}">
							<article class="n-box-01">
								<span class="n-title-box text-01">프로그램명</span>
								<span  class="n-info-box number-text-01">
									<input class="fixed_user number-text-01 update_proj" id="update_proj_name" autocomplete="off" maxlength="50" 
									       style="width: 300px; font-size: 13px !important;" disabled="disabled" value="${projInfo.projName}">
								</span>
							</article>
							<article class="n-box-01">
								<span class="n-title-box text-01">유형</span> 
								<span class="n-info-box number-text-01">
									<input class="fixed_user number-text-01 update_proj" id="update_proj_type"  autocomplete="off" maxlength="10"
									style="width: 120px; font-size: 13px !important;" disabled="disabled" value="${projInfo.projType}">
								</span>
							</article>
							<article class="n-box-01">
								<span class="n-title-box text-01">등급</span> 
								<span class=" n-info-box number-text-01">
								<input type="hidden" id="ipt_proj_level" value="${projInfo.projLevel}">
								<select id="update_proj_level"  class="w100p fixed_user number-text-01 form-control update_proj" 
										name="PROJLEVEL" disabled="disabled" style="min-width: 50px !important; font-size: 14px !important; color: #6c6b8b !important;">
									<option value="S">S</option>
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
								</select>
								</span>
							</article>
							<article class="n-box-01">
								<span class="n-title-box text-01">담당자</span> 								
								<span id="update_proj_manager" class="n-info-box number-text-01" 
								style="font-size: 13px !important;">
								<input class="fixed_user number-text-01" disabled="disabled" 
								style="width: 140px"  value="${projInfo.projManagerComp}&nbsp;${projInfo.projManagerPost}&nbsp;${projInfo.projManagerName}">
								</span>
							</article>
							<article class="n-box-01">
								<span class="n-title-box text-01">예산</span> 
								<span class="n-info-box number-text-01">
								<input id="update_proj_budget" name="projBudget" class="fixed_user number-text-01 update_proj t-right"  autocomplete="off" maxlength="14"
										style="width: 100px; font-size: 13px !important;" 
									 disabled="disabled" value="${projInfo.projBudget}">
								</span>
							</article>
							<article class="n-box-01">
								<span class="n-title-box text-01">진행상태</span> 
								<span class="n-info-box number-text-01">
									<input type="hidden" id="ipt_proj_status" value="${projInfo.projStatus}">
									<select class="form-control update_proj fixed_user number-text-01" disabled="disabled" 
									style="width: 80px;font-size: 13px !important; color: #6c6b8b !important;"
									id="update_proj_status" name="projStatus">
										<option value="미진행">미진행</option>
										<option value="진행중">진행중</option>
										<option value="진행완료">진행완료</option>
									</select>
								</span>
							</article>
							<article class="n-box-01" style="z-index: 2">
								<span class="n-title-box text-01">시작일자</span> 
								<span class="n-info-box number-text-01 ">
									<input type="text" id="update_proj_inDate" name="projInDate"  autocomplete="off" onchange="DetaildateChange()"
									style="width: 85px; font-size: 13px !important;"  onkeyup="inputDateNumber(this)"
									disabled="disabled"
									class="datepicker number-text-01 update_proj fixed_user" value="${projInfo.projInDate}" />
								</span>
							</article>
							<article class="n-box-01" style="z-index: 2">
								<span class="n-title-box text-01">완료일자</span>
								<span class="n-info-box number-text-01">
									<input type="text" id="update_proj_outDate" name="projOutDate" autocomplete="off" onchange="DetaildateChange()"
									style="width: 85px; font-size: 13px !important;"   onkeyup="inputDateNumber(this)"
										disabled="disabled"
									class="datepicker number-text-01 update_proj"  value="${projInfo.projOutDate}" />
								</span>
							</article>
							<article class="n-box-01">
								<span class="n-title-box text-01">수행 기간</span> 
								<span class="n-info-box number-text-01">
								<input class="fixed_user number-text-01" id="update_proj_projDate" style="width: 120px; font-size: 13px !important;" disabled="disabled" value="">
								</span>
							</article>
							<article class="n-box-01">
								<span class="n-title-box text-01">남은 기간</span>
								<input type="hidden" id="ipt_projLeftDate" value="${projInfo.projLeftDate}">
								<span  style="font-size: 13px !important;" class="n-info-box number-text-01">
									<c:choose>
										<c:when test="${projInfo.projLeftDate le 0}">
											<input class="fixed_user number-text-01" id="detail_proj_leftDate" style="width: 120px; color: #ff0000; font-size: 13px !important;" disabled="disabled" value="">
										</c:when>
										<c:when test="${projInfo.projLeftDate ge 0}">
											<input class="fixed_user number-text-01" id="detail_proj_leftDate" style="width: 120px; font-size: 13px !important;" disabled="disabled" value="">
										</c:when>
										<c:otherwise>
											<input class="fixed_user number-text-01" id="detail_proj_leftDate" style="width: 120px; font-size: 13px !important;" disabled="disabled" value="">
										</c:otherwise>
									</c:choose>
								</span>
							</article>
						</div>
					</div>
					<!-- 추가된 내용 끝 -->
					<div class="w100p">
						<button hidden type="button" style="float: right;"  
							class="btn btn-type-04 btn_proj_res">수정</button>
						<button hidden type="button" style="float: right;" id="btn_proj_update"
							class="btn btn-type-04 btn_proj_update">등록</button>
					</div>
				</section>
					<!--  중간 통계 세션 -->
					<section class="list-table mt20">
						<article class="table-article">
							<div class="title-bar clearfix mb20">
								<button type="button" id="btn_proj_user_del" class="btn btn-type-04"
										style="float: right; margin-right: 10px">방출</button>
								<button type="button" id="btn_proj_user_new" class="btn btn-type-03"
										style="float: right; margin-right: 10px">추가</button>
								<span class="table-title-01" data-title="Total">투입인원 현황</span>
								<div class="f-right"></div>
							</div>
							<div class="table-wrap">
								<div class="table-position row-line1" >
									<div class="table-wrap table-h-300">
										<table class="tbl04" id="table-total" >
											<caption>OO대테이블</caption>
											<colgroup>
												<!-- 체크박스 -->
												<col style="width: 3rem;" />
												<!-- 소속 -->
												<col style="width: 4.3rem;" />
												<!-- 직위 -->
												<col style="width: 3rem;" />
												<!-- 성명 -->
												<col style="width: 3rem;" />
												<!-- 직책 -->
												<col style="width: 3rem;" />	
												<!-- 담당자 / 구성원 -->
												<col style="width: 4rem;" />
												<!-- 시작일자 -->
												<col style="width: 3rem;" />
												<!-- 완료일자 -->
												<col style="width: 3rem;" />
												<!-- 수정 -->
												<col style="width: 2rem;" />
												<!-- 이력서 보기 -->
												<col style="width: 2rem;" />
											</colgroup>
											<thead>
											<tr class="fixed-top" style="width: 100%; z-index: 1;">
												<th style="width: 3rem" scope="col"><input type="checkbox" class="allCheckBox" style="margin-right: 25px"></th>
												<th style="width: 4rem" class="t-left" scope="col">소속</th>
												<th style="width: 3rem" class="t-left" scope="col">직위</th>
												<th style="width: 3rem" class="t-left" scope="col">성명</th>
												<th style="width: 3rem" class="t-left" scope="col">직책</th>
												<th style="width: 4rem" class="t-left" scope="col">담당자/구성원</th>
												<th style="width: 3rem" class="t-left" scope="col">시작일자</th>
												<th style="width: 3rem" class="t-left" scope="col">완료일자</th>
												<th style="width: 2rem" class="t-left" scope="col"></th>
												<th style="width: 2rem" class="t-left" scope="col"></th>
											</tr>
											</thead>
											<tbody style="min-width: 75rem; width : 100%;" >
											<c:choose>
												<c:when test="${empty serverPageSub}">
													<tr class="projList" id="${items.projCode}">
														<td colspan="12" id="proj_type" class="t-center">투입된 인원이 없습니다.</td>
													</tr>
												</c:when>
												<c:when test="${!empty serverPageSub}">
													<c:forEach var="items" items="${serverPageSub}">
														<tr class="projList" id="${items.userCode}">
															<th><input type="checkbox" scope="row" class="OneCheckBox" id="${items.userCode}" value="${items.userCode}"></th>
															<td id="inputed_user_comp" style="white-space: pre;" class="t-left">${items.userComp}</td>
															<td id="inputed_user_post" onclick="mvPage('user', this)"
																	 class="t-left">${items.userPost}</td>
															<td id="inputed_user_name" style="white-space: pre;" onclick="mvPage('user', this)" 
															         class="t-left">${items.userName}</td>
															<td id="inputed_user_job" style="white-space: pre;" class="t-left">${items.userJob}</td>
															<td id="inputed_user_userDivision" class="t-left">${items.userDivision}</td>
															<td id="inputed_user_inDate" class="t-left">${items.userInDate}</td>
															<td id="inputed_user_outDate" class="t-left">${items.userOutDate}</td>
															<td class="t-left btn_inputed_user_career" style="text-overflow: clip; !important" id="${items.userCode}"><a><i class="far fa-file-alt ml10 f24 color04 btn_inputData_pop" id="${items.userCode}"></i></a></td>
															<td class="t-left inputed_user_btn_res" style="text-overflow: clip; !important" id="${items.userCode}"><a><i class="fas fa-pen-square ml10 f24 color04 btn_inputData_pop" id="${items.userCode}"></i></a></td>
															
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
						<div class="h30"></div>
					</section>
				</div>
			</div>
		</div>
	</div>
	<!-- 투입인원 추가 -->
	<div class="modal fade" id="proj_user_new" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="z-index: 9999">
		<div id="detail_pop_type" class="modal-dialog modal-new">
			<div class="modal-content">
				<div class="modal-header dark">
					<h5 class="modal-title" id=""></h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<section class="flex-center-top">
						<div class="col-sm-7" id="dp_left_div">
							<form id="detail_pop_form1" >
								<input type="hidden" id="csrfValue" name="${_csrf.parameterName}" value="${_csrf.token}" />

								<section class="pop-search mt20">
									<div class="form-group">
										<div class="input-box w200 mr30">
											<label class="small-text-01 mb10" for="select_inputUser_name" >성명</label>
											<input type="text" class="form-control select_inputUser" id="select_inputUser_name" name="userName">
										</div>
										<div class="select-box-type2 w100 mr30">
											<label class="small-text-01 mb10" for="select_inputUser_level">직위</label>
											<select  class="form-control w100p select_inputUser" id="select_inputUser_post" name="userPost">
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
										<div class="select-box-type2 w100 mr30">
											<label class="small-text-01 mb10" for="select_inputUser_level">등급</label>
											<select id="select_inputUser_level" class="form-control select_inputUser" name="userPost">
												<option value="" selected>선택</option>
												<option value="특급">특급</option>
												<option value="고급">고급</option>
												<option value="중급">중급</option>
												<option value="초급">초급</option>
											</select>
										</div>
										<div class="input-box no-wrap" style="margin-left: 1%">
											<button id="btn_find_user" type="button" class="btn btn-type-03">Find</button>
											<button id="btn_clear_user" type="button" class="btn btn-type-04">Clear</button>
										</div>
									</div>
								</section>
							</form>
							<section class="pop-list-table mt20">
								<article class="table-article">
									<div class="table-wrap">
										<div class="table-position" style="min-width: 50rem; width: 100%">
											<div class="table-h-500" style="height: 22rem !important;">
												<table class="tbl02">
													<colgroup>
														<!-- 직위 -->
														<col style="width: 3rem;" />
														<!-- 성명 -->
														<col style="width: 3rem;" />
														<!-- 부서-->
														<col style="width: 6rem;" />
														<!-- 연락처 -->
														<col style="width: 6rem;" />
														<!-- 이메일 -->
														<col style="width: 6rem;" />
													</colgroup>
													<thead class="fixed-top" style="min-width: 50rem; width: 100%">
													<tr>
														<th style="width: 4rem;" class="t-left" scope="col">직위</th>
														<th style="width: 4rem;" class="t-left" scope="col">성명</th>
														<th style="width: 6rem;" class="t-left" scope="col">부서</th>
														<th style="width: 6rem;" class="t-left" scope="col">연락처</th>
														<th style="width: 6rem;" class="t-left" scope="col">이메일</th>
													</tr>
													</thead>
													<tbody id="pop_tbody2">
													<!-- 비동기식으로 데이터 뿌리기 -->
													<c:forEach var="items" items="${userList}">
														<tr class="userList"  id="${items.userCode}">
															<td id="selected_user_post" class="t-left">${items.userPost}</td>
															<td id="selected_user_name" class="t-left">${items.userName}</td>
															<td id="selected_user_buseo" class="t-left">${items.userBuseo}</td>
															<td id="selected_user_tel" class="t-left">${items.userTel}</td>
															<td id="selected_user_email" class="t-left">${items.userEmail}</td>
														</tr>
													</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</article>
							</section>
						</div>

						<div id="dp_right_div" class="col-sm-5">
							<form id="detail_pop_form2">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<input id="dp_p_seq" type="hidden" name="P_SEQ">

								<table class="tbl05" style="height: 33rem;">
									<caption>팝업테이블</caption>
									<colgroup>
										<col style="width: 30%;" />
										<col style="width: 70%;" />
									</colgroup>
									<tbody>
									<tr>
										<th>이름</th>
										<td>
											<input type="hidden" id="fixed_user_code" name="USERCODE" value="">
											<div class="select-box-type2 w100p">
												<input type="text" class="form-control insert_inputUser" autocomplete="off" 
												id="fixed_user_name" name="userName" disabled="disabled" value="${bringUserData.userName}">
											</div>
										</td>
									</tr>
									<tr>
										<th>직위</th>
										<td>
											<input type="hidden" id="bringUserPost" value="${bringUserData.userPost}">
											<select class="form-control insert_inputUser"  id="fixed_user_post" name="userPost" disabled>
												<option selected value="">선택</option>
												<option value="인턴">인턴</option>
												<option value="사원">사원</option>
												<option value="대리">대리</option>
												<option value="과장">과장</option>
												<option value="차장">차장</option>
												<option value="부장">부장</option>
												<option value="대표이사">대표이사</option>
											</select>
										</td>
									</tr>
									<tr>
										<th>등급</th>
										<td>
											<input type="hidden" id="bringUserLevel" value="${bringUserData.userLevel}">
											<select class="form-control insert_inputUser" id="fixed_user_level"  name="userLevel" disabled>
												<option selected value="">선택</option>
												<option value="특급">특급</option>
												<option value="고급">고급</option>
												<option value="중급">중급</option>
												<option value="초급">초급</option>
											</select>
										</td>
									</tr>
									<tr>
										<th>부서</th>
										<td>
											<input type="text" class="form-control insert_inputUser" autocomplete="off" 
											 id="fixed_user_buseo" name="userBuseo" disabled="disabled" value="${bringUserData.userBuseo}">
										</td>
									</tr>
									<tr>
										<th>연락처</th>
										<td>
											<div style="display: flex">
												<input type="text" class="form-control form-control-sm  insert_inputUser"
														onkeyup="inputPhoneNumber(this);" autocomplete="off"
													   disabled id="fixed_user_tel" name="userTel" value="${bringUserData.userTel}">
											</div>
										</td>
									</tr>
									<tr>
										<th>이메일</th>
										<td>
											<div style="display: flex">
												<input type="text" class="form-control form-control-sm  insert_inputUser" autocomplete="off"
													   disabled id="fixed_user_email" name="userEmail" value="${bringUserData.userEmail}">
											</div>
										</td>
									</tr>
									<tr>
										<th>구분<label class="small-text-03 ml5">*</label></th>
										<td>
											<select class="form-control insert_inputUser" id="input_inputUser_division" name="userDivis">
												<option selected value="">선택</option>
												<option value="담당자">담당자</option>
												<option value="구성원">구성원</option>
											</select>
										</td>
									</tr>
									<tr>
										<th>소속<label class="small-text-03 ml5">*</label></th>
											<td>
												<input type="text" class="form-control insert_inputUser"  autocomplete="off" maxlength="10" 
												id="input_inputUser_comp" name="userComp" value=""/>											
											</td>
									</tr>
									<tr>
										<th>직책<label class="small-text-03 ml5">*</label></th>
										<td>
											<div class="select-box-type2 w100p">
												<input type="text" class="form-control insert_inputUser" autocomplete="off" maxlength="10"
												id="input_inputUser_job" name="userJob">
											</div>
										</td>
									</tr>
									<tr>
										<th>시작일자<label class="small-text-03 ml5 datepicker">*</label></th>
										<td class="datepicker">
											<input type="text" class="form-control datepicker insert_inputUser" autocomplete="off" 
											id="input_inputUser_inDate" name="projInDate" onkeyup="inputDateNumber(this)"
												   style="width: 100%; height: 60%"
												   value="">
										</td>
									</tr>
									<tr>
										<th>완료일자<label class="small-text-03 ml5">*</label></th>
										<td class="datepicker">
											<input type="text" class="form-control datepicker insert_inputUser" autocomplete="off" 
											id="input_inputUser_outDate" name="projOutDate" onkeyup="inputDateNumber(this)"
												   style="width: 100%; height: 60%"
												   value="">
										</td>
									</tr>
									</tbody>
								</table>
							</form>
						</div>
					</section>
				</div>
				<div class="modal-footer flex-center">
					<button type="button" id="btn_inputUser_insert"
							class="btn btn-type-03 w100" style="float: right; margin-right: 5px">등록</button>
					<button type="button" id="btn_inputUser_cancel" data-dismiss="modal"
							class="btn btn-type-05 w100" style="float: right; margin-right: 5px">취소</button>
				</div>
			</div>
		</div>
	</div>


<!-- 투입정보 수정 팝업  -->
<!-- left : modal 좌우위치수정 -->
<div class="modal fade" id="inputData_update" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"  style="z-index: 9999">
	<div class="modal-dialog modal-new modal-small">
		<div class="modal-content" style="width:60%; height:50%;left: 265px; min-width: 600px;">
			<div class="modal-header dark">
				<h5 class="modal-title" id="exampleModalLabel">사용자 신규</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form id="detail_pop_form">
					<input type="hidden" id="csrfInfo" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<section class="flex-center-top">
						<table class="tbl05">
							<caption>팝업테이블</caption>
							<colgroup>
								<col style="width: 30%;" />
								<col style="width: 70%;" />
							</colgroup>
							<tbody>
							<tr>
								<th>구분<label class="small-text-03 ml5"></label></th>
								<td>
									<div class="select-box-type2 w100p">
									<input type="hidden" id="inputUserCode" value="">
										<select class="custom-select custom-select-sm form-control"
												id="update_inputUser_division" name="userDivision">
											<option value="구성원">구성원</option>
											<option value="담당자">담당자</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<th>소속<label class="small-text-03 ml5"></label></th>
									<td>
										<input type="text" class="form-control"  autocomplete="off" 
										id="update_inputUser_comp" name="userComp" value=""/>
									</td>
							</tr>
							<tr>
								<th>직책<label class="small-text-03 ml5"></label></th>
								<td>
									<input type="text" class="form-control" autocomplete="off" 
									id="update_inputUser_job" name="userJob" value=""/>
								</td>
							</tr>
							<tr>
								<th>시작일자<label class="small-text-03 ml5"></label></th>
								<td>
									<input type="text" class="datepicker form-control" autocomplete="off" 
									id="update_inputUser_inDate" name="userInDate" value="" />
								</td>
							</tr>
							<tr>
								<th>완료일자<label class="small-text-03 ml5"></label></th>
								<td>
									<input type="text" class="datepicker form-control" autocomplete="off" 
									id="update_inputUser_outDate" name="userOutDate" value="" />
								</td>
							</tr>
							</tbody>
						</table>
					</section>
				</form>
			</div>
			<div class="modal-footer flex-center">
				<button type="button" id="btn_inputData_update" class="btn btn-type-03"
						style="float: right; margin-right: 5px">수정</button>
				<button type="button" id="btn_proj_cancel" class="btn btn-type-04" data-dismiss="modal"
						style="float: right; margin-right: 5px">취소</button>
			</div>
		</div>
	</div>
</div>

<!-- 이력서 등록 팝업 -->
<div class="modal fade" id="career_update_popUp" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-new modal-large">
		<div class="modal-content"
			style="left: 22%; width: 65%; min-width: 600px; height: 41rem;">
			
			<div class="modal-header dark">
				<h5 class="modal-title" id="exampleModalLabel"></h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form action="/main/user/career/insertCareer" method="post">
			<div class="modal-body" style="width: 100%">
				<section id="container">
					<div class="table-wrap table-h-600" style="overflow: auto;">
						<section id="main-content">
							<section class="wrapper">
								<div class="row mt">
								<div class="col-lg-12">
									<!-- 신상 정보 -->
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<input  type="hidden"  name="userCode" id="checkUserCode" value="">
									<div class=" col-lg-12 content-panel">
										<span class="number-text-01" style="font-size: 20px !important; margin: 5px;">신상정보</span>
										<font style="color: red; font-size: 10px">* 이미 저장된 사용자 상세정보의 데이터는 수정이 불가능합니다.</font>
										<table class="table table-bordered table-condensed number-text-01"
											style="display: inline-table;">
											<tr>
												<th rowspan="5" style="text-align: center;">
													<img class="select_imgPath" style="width: 7rem;"
													src="">
												</th>
											</tr>
											<tr>
												<th class="career_category" >성명</th>
												<td><input id="insert_career_name" maxlength="20"  readonly="readonly"
													type="text" class="form-control number-text-01" value="${userInfo.userName}"/></td>
											</tr>
											<tr>
												<th class="career_category" style="width: 100px;">주민등록번호</th>
												<td style="display: flex; width: 100%">
													<input
													type="text" maxlength="6" style="width: 40%; min-width: 69px" class="form-control number-text-01"
													id="insert_career_SSN1" name="resSsn1" onkeyup="SSNFocus()" value="${userInfo.career.resSsn1}" />
													&nbsp;&nbsp;_&nbsp;&nbsp; 
													<input type="text" maxlength="1" class="form-control t-center number-text-01" id="insert_career_SSN2" 
													style="min-width: 10%; width: 10%" name="resSsn2" value="${userInfo.career.resSsn2}" /> 
													<span>＊&nbsp;&nbsp;＊&nbsp;&nbsp;＊&nbsp;&nbsp;＊&nbsp;&nbsp;＊&nbsp;&nbsp;＊</span></td>
											</tr>
											<tr>
												<th class="career_category">직위</th>
												<td><input class="form-control number-text-01" readonly="readonly"
													id="insert_career_post"  value="${userInfo.userPost}"></td>
											</tr>
											<tr>
												<th class="career_category">등급</th>
												<td><input id="insert_career_comp" type="text" readonly="readonly" 
													maxlength="20" class="form-control number-text-01" value="${userInfo.userLevel}"/></td>
											</tr>
											</table>
											<!-- 부가정보 -->
											<table class="table table-bordered table-condensed number-text-01"
											style="display: inline-table; margin-bottom: 3rem">
											<tr>
												<th class="career_category">부서</th>
												<td><input id="insert_career_buseo" type="text" readonly="readonly" 
													maxlength="20" class="form-control number-text-01" value="${userInfo.userBuseo}" /></td>
												<th class="career_category">경력기간</th>
												<td><input id="insert_career_careerDate" type="text"
													placeholder="" maxlength="10" name="resCareerDays"
													class="form-control number-text-01" value="${userInfo.career.resCareerDays}" /></td>
											</tr>
											<tr>
												<th class="career_category">연락처</th>
												<td><input id="insert_career_tel" type="text"  readonly="readonly"
													maxlength="13" class="form-control number-text-01" value="${userInfo.userTel}"
													onkeyup="inputPhoneNumber(this);"/></td>
												<th class="career_category">E-MAIL</th>
												<td><input id="insert_career_email" type="text"  readonly="readonly" 
													onblur="CheckCareerEmail(this);" maxlength="30" value="${userInfo.userEmail}"
													class="form-control number-text-01" /></td>
											</tr>
											<tr>
												<th class="career_category">주소</th>
												<td colspan="5"><input id="insert_career_addr" name="resAddr"
													type="text" maxlength="50" class="form-control number-text-01" value="${userInfo.career.resAddr}" /></td>
											</tr>
										</table>
									</div>
									<!-- 학력 -->
									<div id="divAcademic" class="col-lg-12 content-panel mt">
										<span class="number-text-01"
											style="font-size: 20px !important; margin: 5px;">최종학력</span>
										<table
											class="table table-bordered table-condensed number-text-01"
											style="display: inline-table;">
											<colgroup>
												<!-- 학교명 -->
												<col width="30%" />
												<!-- 입학연도 -->
												<col width="20%" />
												<!-- 졸업연도 -->
												<col width="20%" />
												<!-- 전공 및 학위 -->
												<col width="30%" />
											</colgroup>
											<thead>
												<tr>
													<th class="text-center career_category">학교명</th>
													<th class="text-center career_category">입학년도</th>
													<th class="text-center career_category">졸업연도</th>
													<th class="text-center career_category">전공 및 학위</th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td><input type="text" class="form-control number-text-01" id="insert_edu_name"  
															name="resEduName" maxlength="20" value="${userInfo.resEduName}" /></td>
													<td><input type="text" class="form-control  number-text-01" id="insert_edu_admission" onkeyup="inputDateNumber(this);"
															name="resEduStDate" maxlength="10" value="${userInfo.resEduStDate}" /></td>
													<td><input type="text" class="form-control number-text-01" id="insert_edu_graduation" onkeyup="inputDateNumber(this);" 
														    name="resEduFiDate" maxlength="10" value="${userInfo.resEduFiDate}" /></td>
													<td><input type="text" class="form-control number-text-01" id="insert_edu_major"  
															name="resEduMajor" maxlength="20" value="${userInfo.resEduMajor}" /></td>
												</tr>
											</tbody>
										</table>
									</div>
									<!-- 자격증 -->
									<div class="col-lg-12 content-panel mt">
										<span class="number-text-01"
											style="font-size: 20px !important; margin: 5px;">자격증</span>
										<table
											class="table table-bordered table-condensed number-text-01"
											style="display: inline-table;">
											<colgroup>
												<!-- 자격증명 -->
												<col width="40%" />
												<!-- 취득일 -->
												<col width="20%" />
												<!-- 발급처 -->
												<col width="30%" />
											</colgroup>
											<thead>
												<tr>
													<th rowspan="2" class="text-center career_category">자격증명</th>
													<th rowspan="2" class="text-center career_category">취득일</th>
													<th rowspan="2" class="text-center career_category">발급처</th>
												</tr>
											</thead>
											<tbody class="ipt_certificate">
											<!-- 비동기 방식으로 자격증 데이터 가져오기  -->
											</tbody>
										</table>
										<br> <br> <br>
									</div>
									<!-- 근무경력 -->
									<div class="col-lg-12 content-panel mt">
										<span class="number-text-01"
											style="font-size: 20px !important; margin: 5px;">근무경력</span>
										<table
											class="table table-bordered table-condensed number-text-01"
											style="display: inline-table;">
											<colgroup>
												<!-- 회사명 -->
												<col width="30%" />
												<!-- 근무 기간  -->
												<col width="30%" />
												<!-- 직위 -->
												<col width="15%" />
												<!-- 담당 업무 -->
												<col width="20%" />
											</colgroup>
											<thead>
												<tr>
													<th rowspan="2" class="text-center career_category">회사명</th>
													<th rowspan="2" class="text-center career_category">근무 기간</th>
													<th rowspan="2" class="text-center career_category">직위</th>
													<th rowspan="2" class="text-center career_category">담당 업무</th>
												</tr>
											</thead>
											<tbody class="ipt_comp">
											<!-- 비동기 방식으로 근무 경력 데이터 가져오기  -->											
											</tbody>
										</table>
										<br> <br> <br>
									</div>
										</div>
										<!-- SKILL INVENTORY -->
										<div class="col-lg-12 content-panel mt">
											<span class="number-text-01"
												style="font-size: 20px !important; margin: 5px;">프로젝트 경력</span>
											<!-- 투입된 프로젝트 현황 -->
											<div class="ipt_career_proj">
											<!-- 비동기 방식으로 프로젝트 경력 데이터 가져오기  -->
											</div>
										</div>
									</div>
							</section>
						</section>
					</div>
				</section>
			</div>
			<div class="modal-footer flex-right">
				<button type="button" id="btn_career_cancel" class="btn btn-type-04" onclick="cancelModal();" 
						style="float: right; margin-right: 1rem">창닫기</button>
			</div>
			</form>
		</div>
	</div>
</div>




	<script type="text/javascript">
		//submit
		function fnLoadUser(param) {
			var projCode = $('#projCodeInfo').val();
			if (param) {
				comapp.util.setTagValue1('#form_main', param, [ 'page' ]);
			}
			comapp.submit({
				target : '#form_main',
				url : '/main/proj/detail?projCode=' + projCode,
				method : 'post'
			});
		}
		// 날짜 유효성 검사 
		var start_String = $('#update_proj_inDate').val();
		var end_String = $('#update_proj_outDate').val();
		var start_Array = start_String.split('.');
		var end_Array = end_String.split('.');
		var date1 = new Date(start_Array[0], start_Array[1]-1,start_Array[2]); 
		var date2 = new Date(end_Array[0], end_Array[1]-1,end_Array[2]);
		var now = new Date(); 
		var interval = date2 - date1;
		var nowInterval = date2-now; 
		var day = 1000*60*60*24; 
		var month = day*30; 
		var year = month*12; 
		$('#update_proj_projDate').val(interval/day + "일[" + Math.floor(interval/month) + "개월 " + (Math.ceil(interval/day) - Math.floor(interval/month)*30) + "일]");
		if(end_Array < start_Array) {
			$('#update_proj_projDate').val('일자를 확인하세요.');
			$('#detail_proj_leftDate').val('일자를 확인하세요.');
		} else {
			if(Math.ceil(nowInterval/day) < 0) {
				$('#detail_proj_leftDate').val((Math.floor(nowInterval/day)-Math.floor(nowInterval/day)*2) + "일[초과]");	
			} else {
				$('#detail_proj_leftDate').val(Math.ceil(nowInterval/day) + "일");	
			}
		}
		console.log();
		console.log("기간 개월수: 약 " + Math.floor(interval/month) + "개월" + (Math.ceil(interval/day) - Math.floor(interval/month)*30) + "일"); 
	</script>