
package com.c4i.pms.main.proj;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.c4i.pms.comm.util.CommUtil;
import com.c4i.pms.main.proj.vo.CountVO;
import com.c4i.pms.main.proj.vo.ProjUserVO;
import com.c4i.pms.main.proj.vo.ProjVO;
import com.c4i.pms.main.user.UserService;
import com.c4i.pms.main.user.vo.UserVO;
import com.c4i.pms.mapper.main.ProjMapper;
import com.c4i.pms.mapper.main.ProjUserMapper;
import com.c4i.pms.web.method.serverpage.ServerPage;
import com.c4i.pms.web.method.serverpage.ServerPageCommand;

	
@Service("ProjService")
@Transactional
public class ProjService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjService.class);

	private ProjMapper projMapper;

	private ProjUserMapper projUserMapper;
	
	@Resource(name = "UserService")
	private UserService userService;
	/**
	 * 프로젝트 현황 페이지
	 * 2020-12-23 리팩토링 : CommUtil 활용 
	 * @param command
	 * @return
	 * @throws java.text.ParseException 
	 */
	public ServerPage projList(ServerPageCommand command) {
		// 프로젝트 갯수  파악 
		int count = projMapper.ProjCount(command);
		// 프로젝트 현황 데이터 
		List<ProjVO> projList = projMapper.projList(command);
		command.setCount(count);
		// 예산 & 전체 프로젝트 기간  & 남은 프로젝트 기간 계산
		for(ProjVO proj : projList) {
			// Util : 전체 프로젝트 기간 & 남은 기간 계산 
			String budget = CommUtil.setCommaBudget(proj.getProjBudget());
			long diffDay = CommUtil.CalcDiffDay(proj.getProjInDate(), proj.getProjOutDate());
			long leftDay = CommUtil.CalcLeftDay(proj.getProjOutDate());
			proj.setProjBudget(budget);
			proj.setProjDate(diffDay);
			proj.setProjLeftDate(leftDay);
		}
		return new ServerPage(command, null, null, projList);
	}
	/**
	 * 진행상태별 프로젝트 건수현황 
	 * 2020-12-23 리팩토링 : 하나의 쿼리로 정리 
	 * @param command
	 * @return
	 */
	public CountVO detailProjCount(ServerPageCommand command) {
		return projMapper.detailProjCount(command);
	}
	/**
	 * 특정 프로젝트 세부정보
	 * 2020-12-23 리팩토링 : CommUtil 활용 
	 * @param proj
	 * @return
	 */
	public ProjVO oneProj(ProjVO proj) {
		ProjVO projOne = projMapper.oneProj(proj);
		// 해당 프로젝트 존재 여부 
		if(projOne != null) {
			String budget = CommUtil.setCommaBudget(projOne.getProjBudget());
			long diffDay = CommUtil.CalcDiffDay(projOne.getProjInDate(), projOne.getProjOutDate());
			long leftDay = CommUtil.CalcLeftDay(projOne.getProjOutDate());
			projOne.setProjBudget(budget);
			projOne.setProjDate(diffDay);
			projOne.setProjLeftDate(leftDay);
			return projOne;
		}
		return projOne;
	}
	/**
	 * 총 예산
	 * 2020-12-31 리팩토링 : CommUtil 활용 
	 * @param command
	 * @return
	 */
	public ProjVO totalBudget(ServerPageCommand command) {
		ProjVO projData = projMapper.totalBudget(command);
		String budget = CommUtil.setCommaBudget(projData.getProjBudget());
		projData.setProjBudget(budget);
		return projData;
	}
	/**
	 * 해당 담당자 현황(user)
	 * @param proj
	 * @return
	 */
	public UserVO ManagerInfo(ProjVO proj) {
		return projUserMapper.oneManager(proj);
	}
	/**
	 * 담당자 존재 여부 유효성 검사 
	 * @param projUser
	 * @return
	 */
	public int ManagerCount(ProjUserVO projUser) {
		return projUserMapper.oneManagerCount(projUser);
	}
	/**
	 * 투입인원 수 
	 * @param command
	 * @return
	 */
	public int inputUserCount(ServerPageCommand command) {
		return projUserMapper.inputUserCount(command);
	}
	/**
	 * 클릭시 투입입원(사용자) 데이터 가져오기
	 * @param user
	 * @return
	 */
	public UserVO bringData(UserVO user) {
		return projUserMapper.clickInputUser(user);
	}
	/**
	 *  프로젝트 등록
	 *  2020-01-04 리팩토링 : 프로젝트 입력 및 수정 유효성 검사 추가 
	 * @param proj
	 * @return
	 */
	public String insertAndUpdate(ProjVO proj)  {
		// 해당 프로젝트 존재 여부 확인 
		if(proj != null) {
			ProjVO projVo = this.oneProj(proj);
			String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			String reCurrentDate = currentDate.replace("-","."); 
			String format_budget = CommUtil.deleteCommaBudget(proj.getProjBudget());
			proj.setProjBudget(format_budget);
			proj.setProjModifyDate(reCurrentDate);
			if (projVo != null) {
				projMapper.updateProj(proj);
				return "update";
			} else {
				projMapper.insertProj(proj);
				return "insert";
			}
		} else {
			return "서버 내 프로젝트 등록 이벤트 오류가 발생하였습니다.\n담당자에게 문의 바랍니다.";
		}
	}
	/**
	 * 프로젝트 삭제 
	 * 2020-12-23 리팩토링 : 프로젝트 삭제 유효성 검사 추가 
	 * @param proj
	 */
	public String delete(ProjVO proj) {
		if(proj != null) { 
			for(int projcode : proj.getProjCodes()) {
				proj.setProjCode(projcode);
				projMapper.deleteProj(proj);
			} 
			return "체크된 프로젝트를 삭제되었습니다.";
		} else {
			return "서버 내 삭제 이벤트 오류가 발생하였습니다.\n담당자에게 문의바랍니다.";
		}
	}
	/**
	 * 해당 투입인원(사용자) 데이터 가져오기 
	 * @param projUser
	 * @return
	 */
	public UserVO InputUserOne(ProjUserVO projUser) {
		return projUserMapper.InputUserOne(projUser);
	}
	/**
	 * 해당 투입인원 투입 여부 확인
	 * @param projUser
	 * @return
	 */
	public ProjUserVO checkProjUserOne(ProjUserVO projUser) {
		return projUserMapper.InputProjUserOne(projUser);
	}
	/**
	 * 해당 투입인원(사용자) 존재 여부 
	 * @param projUser
	 * @return
	 */
	public int InputUserCheck(ProjUserVO projUser) {
		return projUserMapper.InputUserCheck(projUser);
	}
	/**
	 * 투입인원 등록
	 * @param projUser
	 * @return
	 */
	public ProjUserVO insertInputUser(ProjUserVO projUser) {
		int count = this.InputUserCheck(projUser);		
		int managerCount = this.ManagerCount(projUser);
		ProjUserVO userInfo = this.checkProjUserOne(projUser);
		if(count <= 0) { // 해당 투입인원 존재 여부  
			if(managerCount == 1) { // 담당자 존재 여부  
				if(projUser.getUserDivision().indexOf("담당자") > -1) { // 요청받은 데이터가 담당자인지 확인 
					return projUser; // 요청받은 데이터가 담당자일 때
				} else {
					projUserMapper.insertInputUser(projUser);
				}
			} else {
				projUserMapper.insertInputUser(projUser);
			}
		} else {
			return userInfo; // 해당 프로젝트에 이미 존재하는 사용자가 있음. 
		}
		return null;
	}
	/**
	 * 투입인원 정보 수정 
	 * 2020-12-23 리팩토링 : controller 내 로직을 service 영역에 재구현 
	 * @param projUser
	 * @param proj
	 * @return
	 */
	public int updateInputUser(ProjUserVO projUser, ProjVO proj) {
		int managerCount = this.ManagerCount(projUser);
		proj.setProjCode(projUser.getProjCode());
		UserVO projUserInfo = this.ManagerInfo(proj);
		if(managerCount == 1) { // 담당자 존재 여부 
			if(projUser.getUserDivision().indexOf("담당자") > -1) { // 담당자 존재한다면 
				if(projUserInfo.getUserCode() == projUser.getUserCode()) {
					projUserMapper.updateInputUser(projUser); // 없다면 수정
					return 1; // 수정 완료 알림 출력 
				} else {
					return 0; // 담당자 이미 존재 알림 출력
				}
			} else {
				projUserMapper.updateInputUser(projUser); // 없다면 수정 
		    	return 1;
			}
		} else {
			projUserMapper.updateInputUser(projUser); // 담당자가 0명이면 수정 
	    	return 1;
		}
	}
	/**
	 * 투입인원 삭제 
	 * @param projUser
	 */
	public String deleteInputUser(ProjUserVO projUser) {
		if(projUser != null) {
			for(int i = 0; i < projUser.getUserCodes().size(); i++) {
				projUser.setProjCode(projUser.getProjCode());
				projUser.setUserCode(projUser.getUserCodes().get(i).intValue());
				projUserMapper.deleteInputUser(projUser);
			}
			return "투입인원이 방출되었습니다.";
		} else {
			return "투입인원 방출 이벤트 오류가 발생하였습니다.\n담당자에게 문의바랍니다.";
		}
	}	
	/**
	 * 투입인원 현황 페이지
	 * @param command
	 * @param proj
	 * @return
	 */
	public ServerPage selectListExecutionDetailPage(ServerPageCommand command,ProjVO proj) {
		command.setProj(proj);
		int count = inputUserCount(command);
		command.setCount(count);
		List<UserVO> inputedUserList = projUserMapper.inputUserList(command);
		return new ServerPage(command, null, inputedUserList, null);
	}
	//setter로 의존성 주입을 해야 빈 객체정보를 가진다.
	@Autowired
	public void setProjMapper(ProjMapper prMapper) {
		this.projMapper = prMapper;
	}
	@Autowired
	public void setProjUserMapper(ProjUserMapper puMapper) {
		this.projUserMapper = puMapper;
	}

}