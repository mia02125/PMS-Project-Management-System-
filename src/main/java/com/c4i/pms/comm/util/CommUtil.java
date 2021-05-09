package com.c4i.pms.comm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
/**
 * 2020-12-23 리팩토링 : CommUtil 생성 
 * @author com4in
 *
 */
public class CommUtil {
	// 예산 : 천단위 콤마 생성
	public static String setCommaBudget(String target) {
			String budget = target;
			if(budget != null) {
				long long_budget = Long.parseLong(budget); // int로 파싱할 경우 2,147,483,647이 최대값이기 때문에 그 이상의 값을 파싱이 불가능 
				String fm_budget = String.format("%,d",long_budget);
				return fm_budget;
			}
			return budget;
	}
	// 예산 : 콤마 제거 	
	public static String deleteCommaBudget(String target) {
		String input_budget = target;
		if(target != null) {
			String format_budget = input_budget.replace(",","");
			return format_budget;
		}
		return input_budget;
	}
	// 기간 : 프로젝트  기간 계산
	public static long CalcDiffDay(String tg_inDate, String tg_outDate) {
		String strInDate = tg_inDate;
		String strOutDate = tg_outDate;
		if(strInDate != null && strOutDate != null) {
			String reInDate = strInDate.replace(".", "-");
			String reOutDate = strOutDate.replace(".", "-");
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date inDate = sdf.parse(reInDate);
				Date outDate = sdf.parse(reOutDate);
				long diffDay = ((outDate.getTime() - inDate.getTime()) / (24*60*60*1000)); // 전체 프로젝트 기간 
				return diffDay;
			} catch (ParseException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return 0;
	}
	// 기간 : 남은 프로젝트  기간 계산
	public static long CalcLeftDay(String tg_outDate) {
		String strCurrentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
		String strOutDate = tg_outDate;
		if(strOutDate != null) {
			String reOutDate = strOutDate.replace(".", "-");
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date currentDate = sdf.parse(strCurrentDate);
				Date outDate = sdf.parse(reOutDate);
				long leftDay = ((outDate.getTime() - currentDate.getTime()) / (24*60*60*1000)); // 남은 프로젝트 기간 
				return leftDay;
			} catch (ParseException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return 0;
	}
}

