<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<sec:authentication property="principal.auth" var="ss_auth" />
<sec:authentication property="principal.usId" var="ss_usId" />
<style>
/* 팝업창 가운데 정렬 */
.modal-content {
	width: 800px;
	height: 700px;
}

</style>
<div class="content-main-top">
	<nav class="bc-wrap" aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li class="breadcrumb-item active" id="projDetail_tab"
				data-rwtoggle="tab" style="cursor: pointer;"
				onclick="location.href='/main/user'" role="tab"
				aria-controls="projDetail" aria-selected="false">사용자 관리</li>
			<li class="breadcrumb-item active" id="userDetail_tab"
				data-toggle="tab" role="tab" aria-controls="userDetail"
				aria-selected="false">
				<a href="/main/user/detail?userCode=${detailUsInfo.userCode}">사용자 상세보기</a></li>
		</ol>
	</nav>
	<div class="main-scroll">
		<section class="search-wrap mt20">
			<div class="table-article">
				<div class="form-group">
					<div style="display: flex;">
						<!-- 프로젝트 투입 목록 -->
						<div class="table-wrap table-h-300"
							style="overflow-y: hidden !important; overflow-x: auto !important; height: 16rem;">
							<div class="table-position"
								style="min-width: 55rem; max-width: 65rem; width: 100%; padding-top: 1.5rem !important;">
								<div class="fixedUserData table-article col-sm-3">
									<div style="margin: 0.1rem">
										<input type="hidden" id="select_imgPath"
											value="${pageContext.request.contextPath}${imgData.filePath}">
										<img class="select_imgPath" style="width: 7rem;" src="">
									</div>
									<div class="col-sm-7">
										<form action="/main/user/updateUser" method="post"
											enctype="multipart/form-data">
											<input id="csrfValue" type="hidden"
												name="${_csrf.parameterName}" value="${_csrf.token}" />
											<div class="container" style="display: flex;">
												<input type="hidden" id="update_user_code" name="userCode"
													value="${detailUsInfo.userCode}">
												<article class="n-box-01"
													style="max-width: 255px; min-width: 255px;">
													<span class="n-title-box text-01">직위</span> <span
														class="n-info-box number-text-01"> <input
														type="hidden" id="ipt_update_user_post"
														value="${detailUsInfo.userPost}"> <select
														class="update_user form-control number-text-01"
														id="update_user_post"
														style="padding: 1px !important; height: 35px;"
														name="userPost">
															<option value="인턴">인턴</option>
															<option value="사원">사원</option>
															<option value="대리">대리</option>
															<option value="과장">과장</option>
															<option value="차장">차장</option>
															<option value="부장">부장</option>
															<option value="대표이사">대표이사</option>
													</select>
													</span>
												</article>
												<article class="n-box-01" style="min-width: 252px">
													<span class="n-title-box text-01">이름</span> <span
														class="n-info-box number-text-01"> <input
														type="text"
														style="padding: 1px !important; height: 35px; width: 80px;"
														class="form-control update_user number-text-01" disabled
														id="update_user_name" name="userName"
														value="${detailUsInfo.userName}">
													</span>
												</article>
												<article class="n-box-01">
													<span class="n-title-box text-01">등급</span> <span
														class="n-info-box number-text-01"> <input
														type="hidden" id="ipt_update_user_level"
														value="${detailUsInfo.userLevel}"> <select
														class="number-text-01 form-control update_user"
														style="padding: 1px !important; height: 35px;"
														id="update_user_level" name="userLevel"
														disabled="disabled">
															<option value="특급">특급</option>
															<option value="고급">고급</option>
															<option value="중급">중급</option>
															<option value="초급">초급</option>
													</select>
													</span>
												</article>
											</div>
											<div class="container" style="display: flex;">
												<article class="n-box-01">
													<span class="n-title-box text-01">부서</span> <span
														class="n-info-box number-text-01 form-group"
														style="width: 150px"> <input type="text"
														class="form-control form-control-sm update_user number-text-01"
														style="padding: 1px !important; height: 35px;" disabled
														id="update_user_buseo" name="userBuseo"
														value="${detailUsInfo.userBuseo}">
													</span>
												</article>
												<article class="n-box-01">
													<span class="n-title-box text-01">연락처</span> <span
														class="n-info-box number-text-01 form-group"
														style="width: 150px;"> <input type="text"
														class="form-control form-control-sm update_user number-text-01"
														style="padding: 1px !important; height: 35px;"
														onkeyup="inputPhoneNumber(this);" disabled
														id="update_user_tel" name="userTel"
														value="${detailUsInfo.userTel}">
													</span>
												</article>
												<article class="n-box-01">
													<span class="n-title-box text-01">이메일</span> <span
														id="detail_proj_status" class="n-info-box number-text-01">
														<input type="text"
														class="form-control form-control-sm update_user number-text-01"
														style="padding: 1px !important; height: 35px;" disabled
														id="update_user_email" name="userEmail"
														value="${detailUsInfo.userEmail}">
													</span>
												</article>
											</div>
											<div id="fileUpdateForm">
												<div class="container" style="display: flex; width: 500px;">
													<article class="n-box-01" style="margin-top: 3px">
														<span class="n-title-box text-01">이미지</span> <span
															id="detail_proj_status" class="n-info-box number-text-01">
															<button type="button"
																class="btn btn-type-02 mr20 btnImgFileCustom update_user number-text-01"
																style="margin-left: 5px;" value="uploadImg">파일선택</button>
															<input type="file" id="uploadImg" name="uploadImg"
															onchange="changeImgValue(this)" accept=".png, .jpg">
															<input type="hidden" id="imgNMText"
															value="${imgData.fileName}"> <span
															class="small-text-02 file-custom-label" id="imgNameText"
															style="text-overflow: ellipsis; width: 110px"></span>
														</span>
													</article>
													<article class="n-box-01"
														style="width: 110px; vertical-align: middle !important;">
														<button hidden type="button" style="float: right;" 
															id="btn_user_res" class="btn btn-type-03 btn_user_res">사용자 수정</button>
														<button hidden type="submit" style="float: right;"
															id="btn_user_update"
															class="btn btn-type-03 btn_user_update">사용자 등록</button>

													</article>
													<article class="n-box-01" style="width: 100px">
														<c:if test="${checkCareer == false}">
															<button type="button" style="float: right;"
																id="btn_career_insert_popUp"
																class="btn btn-type-03 career_insert_popUp">이력서
																추가</button>
														</c:if>
														<c:if test="${checkCareer == true}">
															<button type="button" style="float: right;"
																id="btn_career_update_popUp"
																class="btn btn-type-03 career_update_popUp">이력서
																수정</button>
														</c:if>
													</article>
												</div>
										</form>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="table-wrap table-h-300"
						style="width: 32%; height: 17rem; overflow: hidden !important;">
						<div class="table-position"
							style="max-width: 25rem; width: 100%; padding-top: 0rem !important;">
							<article class="n-box" style="margin: 1rem;">
								<div class="n-bar t-right bg">
									<span class="text-01 color04">미진행</span>
								</div>
								<div class="n-body t-right">
									<span class="number-text-01">${projStatusData.noStatus}</span>
								</div>
							</article>

							<article class="n-box" style="margin: 1rem">
								<div class="n-bar t-right bg">
									<span class="text-01 color04">진행중</span>
								</div>
								<div class="n-body t-right">
									<span class="number-text-01">${projStatusData.stStatus}</span>
								</div>
							</article>

							<article class="n-box" style="margin: 1rem">
								<div class="n-bar t-right bg">
									<span class="text-01 color04">진행완료</span>
								</div>
								<div class="n-body t-right">
									<span class="number-text-01">${projStatusData.fiStatus}</span>
								</div>
							</article>
						</div>
					</div>

				</div>
			</div>
	</div>
	</section>
	<section class="search-wrap mt20">
		<div class="table-article">
			<div class="form-group">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<!--
                                    input 사이즈
                                    w70, w80, w100, w150, w200, w300,
                                    w50p : 가로 50%
                                    w100p : 가로 100%
                                  -->
				<div class="title-bar clearfix mb20" style="display: flex;">
					<span class="table-title-01" data-title="Total">프로젝트 투입 목록</span>
				</div>
				<!-- 프로젝트 투입 목록 -->
				<div class="table-wrap table-h-500" style="overflow: auto;">
					<div class="table-position" style="min-width: 75rem; width: 100%;">
						<table class="tbl02" id="table-total">
							<caption>OO대테이블</caption>
							<colgroup>
								<!-- 프로젝트명 -->
								<col style="width: 11%;" />
								<!-- 유형 -->
								<col style="width: 8%;" />
								<!-- 등급 -->
								<col style="width: 8%;" />
								<!-- 담당자 -->
								<col style="width: 6%;" />
								<!-- 예산 -->
								<col style="width: 8%;" />
								<!-- 진행상태 -->
								<col style="width: 8%;" />
								<!-- 시작일자 -->
								<col style="width: 8%;" />
								<!-- 완료일자 -->
								<col style="width: 8%;" />
								<!-- 프로젝트기간 -->
								<col style="width: 8%;" />
								<!-- 남은기간 -->
								<col style="width: 6%;" />
								<!-- 진행기간 -->
								<col style="width: 6%;" />
								<!-- 투입인원 -->
								<col style="width: 6%;" />
							</colgroup>
							<thead class="fixed-top" style="min-width: 75rem; width: 100%">
								<tr>
									<th style="width: 11.3rem;" class="t-left" scope="col">프로젝트명</th>
									<th style="width: 8rem;" class="t-left" scope="col">유형</th>
									<th style="width: 8rem;" class="t-left" scope="col">등급</th>
									<th style="width: 6rem;" class="t-left" scope="col">담당자</th>
									<th style="width: 8rem;" class="t-right" scope="col">예산</th>
									<th style="width: 8rem;" class="t-left" scope="col">진행상태</th>
									<th style="width: 8rem;" class="t-left" scope="col">시작일자</th>
									<th style="width: 8rem;" class="t-left" scope="col">완료일자</th>
									<th style="width: 8rem;" class="t-left" scope="col">수행기간</th>
									<th style="width: 6rem;" class="t-left" scope="col">남은기간</th>
									<th style="width: 6rem;" class="t-left" scope="col">진행기간</th>
									<th style="width: 6rem;" class="t-left" scope="col">투입인원</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty detailUserProjList}">
										<tr class="projList" id="${items.projCode}">
											<td colspan="12" id="proj_type" class="t-center">투입된
												프로젝트가 없습니다.</td>
										</tr>
									</c:when>
									<c:when test="${!empty detailUserProjList}">
										<c:forEach var="items" items="${detailUserProjList}">
											<tr class="projList" id="${items.projCode}">
												<td id="inputed_proj_name"
													onclick="mvPage('inputedProj', this)" class="t-left">${items.projName}</td>
												<td id="inputed_proj_type" class="t-left">${items.projType}</td>
												<td id="inputed_proj_level" class="t-left">${items.projLevel}</td>
												<td id="inputed_proj_manager" class="t-left">${items.projManagerComp}<br>${items.projManagerPost}&nbsp;${items.projManagerName}</td>
												<td id="inputed_proj_budget" class="t-right">${items.projBudget}</td>
												<c:choose>
													<c:when test="${items.projStatus eq '미진행'}">
														<td id="inputed_proj_status" style="color: red"
															class="t-left">${items.projStatus}</td>
													</c:when>
													<c:when test="${items.projStatus eq '진행중'}">
														<td id="inputed_proj_status" style="color: green"
															class="t-left">${items.projStatus}</td>
													</c:when>
													<c:when test="${items.projStatus eq '진행완료'}">
														<td id="inputed_proj_status" style="color: green"
															class="t-left">${items.projStatus}</td>
													</c:when>
												</c:choose>
												<td id="inputed_proj_inDate" class="t-left">${items.projInDate}</td>
												<td id="inputed_proj_outDate" class="t-left">${items.projOutDate}</td>
												<td id="inputed_proj_DATE" class="t-left">${items.projDate}일</td>
												<c:choose>
													<c:when test="${items.projLeftDate le 0}">
														<td id="inputed_proj_leftDATE" style="color: red"
															class="t-left">${items.projLeftDate-items.projLeftDate*2}&nbsp;일[초과]</td>
													</c:when>
													<c:when test="${items.projLeftDate ge 0}">
														<td id="inputed_proj_leftDATE" class="t-left">${items.projLeftDate}일</td>
													</c:when>
												</c:choose>
												<td id="inputed_proj_statusPer" class="t-left">0일<br>
												<td id="inputed_proj_inputCount" class="t-left">${items.projInputCount}명</td>
											</tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
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
											<!-- 신상정보 -->
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" /> <input type="hidden"
												id="ipt_update_career_code" name="resCode" value="${userInfo.career.resCode}"> 
												<input type="hidden" id="ipt_update_career_post" autocomplete="off" value="${userInfo.userPost}"> 
												<input type="hidden" name="userCode" value="${detailUsInfo.userCode}">
											<div class=" col-lg-12 content-panel">
												<span class="number-text-01" style="font-size: 20px !important; margin: 5px;">신상정보</span>
												<font style="color: red; font-size: 10px">*이미 저장된 사용자 상세정보의 데이터는 수정이 불가능합니다.</font>
												<table class="table table-bordered table-condensed number-text-01"
													style="display: inline-table;">
													<tr>
														<th rowspan="5" style="text-align: center;"><img
															class="select_imgPath" style="width: 7rem;" src="">
														</th>
													</tr>
													<tr>
														<th class="career_category">성명</th>
														<td><input id="insert_career_name" maxlength="20"
															readonly="readonly" autocomplete="off" type="text"
															class="form-control number-text-01"
															value="${userInfo.userName}" /></td>
													</tr>
													<tr>
														<th class="career_category" style="min-width: 110px;">주민등록번호<label
															class="small-text-03 ml5">*</label></th>
														<td style="display: flex; width: 100%"><input
															type="text" maxlength="6" autocomplete="off"
															style="width: 40%; min-width: 69px"
															class="form-control number-text-01"
															onkeyup="num_check(this);" id="insert_career_SSN1"
															name="resSsn1" oninput="SSNFocus();"
															value="${userInfo.career.resSsn1}" /> &nbsp;&nbsp;_&nbsp;&nbsp; <input
															type="text" maxlength="1"
															class="form-control t-center number-text-01"
															id="insert_career_SSN2" onkeyup="num_check2(this);"
															style="min-width: 10%; width: 10%" name="resSsn2"
															value="${userInfo.career.resSsn2}" autocomplete="off" /> <span>&nbsp;&nbsp;＊&nbsp;&nbsp;＊&nbsp;&nbsp;＊&nbsp;&nbsp;＊&nbsp;&nbsp;＊&nbsp;&nbsp;＊</span></td>
													</tr>
													<tr>
														<th class="career_category">직위</th>
														<td><input class="form-control number-text-01"
															readonly="readonly" autocomplete="off"
															id="insert_career_post" value="${userInfo.userPost}"></td>
													</tr>
													<tr>
														<th class="career_category">등급</th>
														<td><input id="insert_career_comp" type="text"
															readonly="readonly" autocomplete="off" maxlength="20"
															class="form-control number-text-01"
															value="${userInfo.userLevel}" /></td>
													</tr>
												</table>
												<!-- 부가정보 -->
												<table
													class="table table-bordered table-condensed number-text-01"
													style="display: inline-table; margin-bottom: 3rem">
													<tr>
														<th class="career_category">부서</th>
														<td><input id="insert_career_buseo" type="text"
															readonly="readonly" autocomplete="off" maxlength="20"
															class="form-control number-text-01"
															value="${userInfo.userBuseo}" /></td>
														<th class="career_category">경력기간<label
															class="small-text-03 ml5">*</label></th>
														<td><input id="insert_career_careerDate" type="text"
															placeholder="ex) yy년 mm개월" maxlength="10"
															name="resCareerDays" autocomplete="off"
															class="form-control number-text-01"
															value="${userInfo.career.resCareerDays}" /></td>
													</tr>
													<tr>
														<th class="career_category">연락처</th>
														<td><input id="insert_career_tel" type="text"
															readonly="readonly" autocomplete="off" maxlength="13"
															class="form-control number-text-01"
															value="${userInfo.userTel}"
															onkeyup="inputPhoneNumber(this);" /></td>
														<th class="career_category">E-MAIL</th>
														<td><input id="insert_career_email" type="text"
															readonly="readonly" autocomplete="off" maxlength="30"
															value="${userInfo.userEmail}"
															class="form-control number-text-01" /></td>
													</tr>
													<tr>
														<th class="career_category">주소<label
															class="small-text-03 ml5">*</label></th>
														<td colspan="5"><input id="insert_career_addr"
															name="resAddr" autocomplete="off"
															onkeyup="convLtrim(this);" type="text" maxlength="50"
															class="form-control number-text-01"
															value="${userInfo.career.resAddr}" placeholder="50자 이내로 입력하세요." /></td>
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
															<th class="text-center career_category">학교명<label
																class="small-text-03 ml5">*</label></th>
															<th class="text-center career_category">입학연도<label
																class="small-text-03 ml5">*</label></th>
															<th class="text-center career_category">졸업연도<label
																class="small-text-03 ml5">*</label></th>
															<th class="text-center career_category">전공 및 학위<label
																class="small-text-03 ml5">*</label></th>

														</tr>
													</thead>
													<tbody>
														<tr>
															<td><input type="text"
																class="form-control number-text-01" id="insert_edu_name"
																autocomplete="off" onkeyup="convLtrim(this);"
																name="resEduName" maxlength="20"
																value="${userInfo.career.resEduName}" /></td>
															<td><input type="text"
																class="form-control datepicker  number-text-01"
																id="insert_edu_admission"
																onkeyup="inputDateNumber(this);" autocomplete="off"
																oninput="num_check(this);" onblur="cert_Check(this);"
																name="resEduStDate" maxlength="10"
																value="${userInfo.career.resEduStDate}" /></td>
															<td><input type="text"
																class="form-control datepicker  number-text-01"
																id="insert_edu_graduation"
																onkeyup="inputDateNumber(this);" autocomplete="off"
																oninput="num_check(this);" onblur="cert_Check(this);"
																name="resEduFiDate" maxlength="10"
																value="${userInfo.career.resEduFiDate}" /></td>
															<td><input type="text"
																class="form-control number-text-01"
																id="insert_edu_major" autocomplete="off"
																name="resEduMajor" maxlength="20"
																value="${userInfo.career.resEduMajor}" /></td>
														</tr>
													</tbody>
												</table>
											</div>
											<!-- 자격증 -->
											<div class="col-lg-12 content-panel mt">
												<span class="number-text-01"
													style="font-size: 20px !important; margin: 5px;">자격증</span>
												<font style="color: red; font-size: 10px">*최대 10개까지
													등록이 가능합니다.</font>
												<table
													class="table table-bordered table-condensed number-text-01"
													style="display: inline-table;">
													<colgroup>
														<!-- 자격증명 -->
														<col width="40%" />
														<!-- 취득일자 -->
														<col width="20%" />
														<!-- 발급처 -->
														<col width="30%" />
														<!-- 삭제 -->
														<col width="10%" />
													</colgroup>
													<thead>
														<tr>
															<th rowspan="2" class="text-center career_category">자격증명<label
																class="small-text-03 ml5">*</label></th>
															<th rowspan="2" class="text-center career_category">취득일자</th>
															<th rowspan="2" class="text-center career_category">발급처</th>
															<th rowspan="2" class="text-center career_category">삭제</th>
														</tr>
													</thead>
													<tbody class="ipt_certificate" id="tbodyLicense">
														<c:forEach var="items" items="${certVOs}">
															<tr class="career_cert">
																<td><input type="hidden" class="insert_cert_code"
																	name="resCertCode" value="${items.certVOs.certCode}"> <input
																	type="text"
																	class="form-control insert_cert_name number-text-01"
																	name="resCertName" autocomplete="off" maxlength="20"
																	value="${items.certVOs.certName}" /></td>
																<td><input type="text" autocomplete="off"
																	class="datepicker form-control number-text-01 insert_cert_date"
																	onkeyup="inputDateNumber(this);" name="resCertDate"
																	oninput="num_check(this);" onblur="cert_Check(this);"
																	maxlength="10" value="${items.certVOs.certDate}" /></td>
																<td><input type="text"
																	class="form-control number-text-01 insert_cert_place"
																	name="resCertAgency" autocomplete="off" maxlength="20"
																	value="${items.certVOs.certAgency}" /></td>
																<td><button type="button"
																		onclick="deleteRow(this);"
																		class="btn btn-type-04 delete_certOne">삭제</button></td>
															</tr>
														</c:forEach>
														<tr class="career_cert">
															<td><input type="text"
																class="form-control insert_cert_name number-text-01"
																name="resCertName" autocomplete="off"
																id="insert_cert_name" maxlength="20" value="" /></td>
															<td><input type="text" autocomplete="off"
																class="datepicker form-control insert_cert_date number-text-01"
																onkeyup="inputDateNumber(this);" name="resCertDate"
																oninput="num_check(this);" onblur="cert_Check(this);"
																id="insert_cert_date" maxlength="10" value="" /></td>
															<td><input type="text"
																class="form-control insert_cert_place number-text-01"
																name="resCertAgency" autocomplete="off" maxlength="20"
																value="" /></td>
															<td><button type="button" onclick="deleteRow(this);"
																	class="btn btn-type-04">삭제</button></td>
														</tr>
													</tbody>
												</table>
												<button type="button" id="btn_certificate_add"
													class="btn btn-type-03 btn_certificate_add"
													style="float: left;">자격증 추가</button>
												<br> <br> <br>
											</div>
											<!-- 근무경력 -->
											<div class="col-lg-12 content-panel mt">
												<span class="number-text-01"
													style="font-size: 20px !important; margin: 5px;">근무경력</span>
												<font style="color: red; font-size: 10px">*최대 10개까지
													등록이 가능합니다.</font>
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
														<!-- 삭제 -->
														<col width="5%" />
													</colgroup>
													<thead>
														<tr>
															<th rowspan="2" class="text-center career_category">회사명<label
																class="small-text-03 ml5">*</label></th>
															<th rowspan="2" class="text-center career_category">근무기간</th>
															<th rowspan="2" class="text-center career_category">직위</th>
															<th rowspan="2" class="text-center career_category">담당업무</th>
															<th rowspan="2" class="text-center career_category">삭제</th>
														</tr>
													</thead>
													<tbody class="ipt_comp">
														<c:forEach var="items" items="${compVOs}">
															<tr class="career_comp">
																<td><input type="hidden" class="insert_career_code"
																	name="resCareerCode" value="${items.compVOs.careerCode}">
																	<input type="text"
																	class="form-control insert_comp_name number-text-01"
																	name="resCareerComp" autocomplete="off" maxlength="20"
																	value="${items.compVOs.careerComp}" /></td>
																<td style="display: flex;"><input type="text"
																	onkeyup="inputDateNumber(this);" name="resCareerStDate"
																	oninput="num_check(this);" style="width: 43%"
																	class="form-control datepicker  number-text-01 insert_comp_stDate"
																	autocomplete="off" onblur="cert_Check(this);"
																	maxlength="10" value="${items.compVOs.careerStDate}" />
																	&nbsp;&nbsp;~&nbsp;&nbsp; <input type="text"
																	onkeyup="inputDateNumber(this);" name="resCareerFiDate"
																	oninput="num_check(this);" style="width: 43%"
																	class="form-control datepicker number-text-01 insert_comp_fiDate"
																	autocomplete="off" onblur="cert_Check(this);"
																	maxlength="10" value="${items.compVOs.careerFiDate}" /></td>
																<td><input type="text"
																	class="form-control number-text-01 insert_comp_post"
																	name="resCareerPost" autocomplete="off" maxlength="10"
																	value="${items.compVOs.careerPost}" /></td>
																<td><input type="text"
																	class="form-control number-text-01 insert_comp_content"
																	name="resCareerContent" autocomplete="off"
																	maxlength="20" value="${items.compVOs.careerContent}" /></td>
																<td><button type="button"
																		onclick="deleteRow(this);"
																		class="btn btn-type-04 delete_compOne">삭제</button></td>
															</tr>
														</c:forEach>
														<tr class="career_comp">
															<td><input type="text"
																class="form-control insert_comp_name number-text-01"
																name="resCareerComp" autocomplete="off" maxlength="20"
																value="" /></td>
															<td style="display: flex;"><input type="text"
																onkeyup="inputDateNumber(this);" name="resCareerStDate"
																autocomplete="off" oninput="num_check(this);"
																onblur="cert_Check(this);" style="width: 43%"
																class="form-control datepicker insert_comp_stDate number-text-01"
																onblur="cert_Check(this);" maxlength="10" value="" />
																&nbsp;&nbsp;~&nbsp;&nbsp; <input type="text"
																onkeyup="inputDateNumber(this);" name="resCareerFiDate"
																autocomplete="off" oninput="num_check(this);"
																onblur="cert_Check(this);" style="width: 43%"
																class="form-control datepicker insert_comp_fiDate number-text-01"
																onblur="cert_Check(this);" maxlength="10" value="" /></td>
															<td><input type="text"
																class="form-control insert_comp_post number-text-01"
																name="resCareerPost" autocomplete="off" maxlength="10"
																value="" /></td>
															<td><input type="text"
																class="form-control insert_comp_content number-text-01"
																name="resCareerContent" autocomplete="off"
																maxlength="20" value="" /></td>
															<td><button type="button" onclick="deleteRow(this);"
																	class="btn btn-type-04 delete_compOne">삭제</button></td>
														</tr>
													</tbody>
												</table>
												<button type="button" id="btn_career_add"
													class="btn btn-type-03" style="float: left;">경력 추가</button>
												<br> <br> <br>
											</div>
										</div>
										<!-- SKILL INVENTORY -->
										<div class="col-lg-12 content-panel mt">
											<span class="number-text-01"
												style="font-size: 20px !important; margin: 5px;">프로젝트
												경력</span>
											<!-- 투입된 프로젝트 현황 -->
											<div class="ipt_proj">
												<c:forEach var="items" items="${projVOs}">
													<table
														class="table table-bordered table-condensed number-text-01"
														style="display: inline-table;">
														<colgroup>
															<!-- 프로젝트 명 -->
															<col width="30%" />
															<!-- 참여 기간 -->
															<col width="25%" />
															<!-- 고객사 -->
															<col width="20%" />
															<!-- 수행업무 -->
															<col width="20%" />
														</colgroup>
														<thead>
															<tr>
																<th rowspan="2" class="text-center career_category">프로젝트명</th>
																<th rowspan="2" class="text-center career_category">참여
																	기간</th>
																<th rowspan="2" class="text-center career_category">회사명</th>
																<th rowspan="2" class="text-center career_category">수행
																	업무</th>
															</tr>
														</thead>
														<tbody class="ipt_proj">
															<tr>
																<td><input type="hidden" name="projCode"
																	value="${items.projVOs.projCodes}"> <input
																	type="hidden" name="detProjCodes"
																	value="${items.projVOs.detProjCode}"> <input
																	type="text"
																	class="form-control insert_career_proj_name"
																	readonly="readonly" autocomplete="off" maxlength="50"
																	value="${items.projVOs.projName}" /></td>
																<td style="display: flex;"><input type="text"
																	readonly="readonly" autocomplete="off"
																	style="width: 50%" onkeyup="inputDateNumber(this);"
																	oninput="num_check(this);"
																	class="form-control stDate insert_career_proj_stDate"
																	onblur="cert_Check(this);" maxlength="10"
																	value="${items.projVOs.userInDate}" />
																	&nbsp;&nbsp;~&nbsp;&nbsp; <input type="text"
																	readonly="readonly" autocomplete="off"
																	style="width: 50%" onkeyup="inputDateNumber(this);"
																	oninput="num_check(this);"
																	class="form-control insert_career_proj_fiDate"
																	onblur="cert_Check(this);" maxlength="10"
																	value="${items.projVOs.userOutDate}" /></td>
																<td><input type="text"
																	class="form-control insert_career_proj_comp"
																	readonly="readonly" autocomplete="off" maxlength="20"
																	value="${items.projVOs.userComp}" /></td>
																<td><input type="text"
																	class="form-control insert_career_proj_job"
																	readonly="readonly" autocomplete="off" maxlength="20"
																	value="${items.projVOs.userJob}" /></td>
															</tr>
														</tbody>
													</table>
													<table
														class="table table-bordered table-condensed number-text-01"
														style="display: inline-table;">
														<colgroup>
															<!-- O.S -->
															<col width="8%" />
															<!-- 언어 -->
															<col width="8%" />
															<!-- DBMS -->
															<col width="8%" />
															<!-- TOOL -->
															<col width="8%" />
															<!-- 통신 -->
															<col width="8%" />
														</colgroup>
														<thead>
															<tr>
																<th colspan="6" class="text-center career_category">개발
																	환경</th>
															</tr>
															<tr>
																<th class="text-center career_category">OS</th>
																<th class="text-center career_category">언어</th>
																<th class="text-center career_category">DBMS</th>
																<th class="text-center career_category">TOOL</th>
																<th class="text-center career_category">통신</th>
															</tr>
														</thead>
														<tbody class="ipt_developer" id="tbodySkill">
															<tr>
																<td><input type="text"
																	class="form-control insert_developer_os" name="projOs"
																	autocomplete="off" onkeyup="convLtrim(this);"
																	maxlength="15" value="${items.projVOs.detProjOs}" /></td>
																<td><input type="text"
																	class="form-control insert_developer_lang"
																	name="projLang" autocomplete="off"
																	onkeyup="convLtrim(this);" maxlength="15"
																	value="${items.projVOs.detProjLang}" /></td>
																<td><input type="text"
																	class="form-control insert_developer_dbms"
																	name="projDb" autocomplete="off"
																	onkeyup="convLtrim(this);" maxlength="15"
																	value="${items.projVOs.detProjDb}" /></td>
																<td><input type="text"
																	class="form-control insert_developer_tool"
																	name="projTool" autocomplete="off"
																	onkeyup="convLtrim(this);" maxlength="15"
																	value="${items.projVOs.detProjTool}" /></td>
																<td><input type="text"
																	class="form-control insert_developer_commu"
																	name="projNet" autocomplete="off"
																	onkeyup="convLtrim(this);" maxlength="15"
																	value="${items.projVOs.detProjNet}" /></td>
															</tr>
															<tr>
																<td colspan="1" class="text-center career_category">기타</td>
																<td colspan="7"><input type="text" name="projOther"
																	onkeyup="convLtrim(this);"
																	class="form-control insert_developer_other"
																	maxlength="80" value="${items.projVOs.detProjOther}"
																	placeholder="80자 내외로 입력하세요." /></td>
															</tr>
														</tbody>
													</table>
													<br>
													<br>
													<br>
												</c:forEach>
											</div>
										</div>
									</div>

								</section>
							</section>
						</div>
					</section>
				</div>
				<div class="modal-footer flex-right">
					<button type="submit" id="btn_career_insert"
						class="btn btn-type-03" style="float: right; margin-right: 5px">등록</button>
					<button type="submit" id="btn_career_update"
						class="btn btn-type-03" style="float: right; margin-right: 5px">수정</button>
					<button type="button" id="btn_career_delete"
						class="btn btn-type-03"
						style="float: right; margin-right: 5px; background-color: red">삭제</button>
					<button type="button" id="btn_career_cancel"
						class="btn btn-type-04" data-dismiss="modal"
						style="float: right; margin-right: 10px">취소</button>
				</div>
			</form>
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
			url : '/main/proj',
			method : 'post'
		});
	}

</script>