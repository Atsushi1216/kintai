package com.example.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Attendance;
import com.example.entity.User;
import com.example.model.AttendanceData;
import com.example.model.AttendanceListParam;
import com.example.model.TotalAttendance;
import com.example.repository.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	AttendanceRepository AttendanceRepository;
	@Autowired
	HttpSession session;

	//出勤日データチェック(本日)
	public boolean todayStartSearch(Timestamp start1,Timestamp start2) {
		User user = new User();
		 user = (User) session.getAttribute("loginUser");
		 int userId = user.getId();

		Attendance todayAttendance = new Attendance();
		todayAttendance = AttendanceRepository.todayStartSearch(userId,start1,start2);

		boolean check = false;
		if(todayAttendance == null) {
			check = true;
		}
		return check;
	}

	//休憩開始データチェック(本日)
	public boolean todayRestStartSearch(Timestamp start1,Timestamp start2) {
		User user = new User();
		 user = (User) session.getAttribute("loginUser");
		 int userId = user.getId();

		Attendance todayAttendance = new Attendance();
		todayAttendance = AttendanceRepository.todayRestStartSearch(userId,start1,start2);

		/*String mm = String.valueOf(todayAttendance.getRestStartDate());
		String oo ="2000-01-01 00:00:00";*/


		boolean check = false;
		if(todayAttendance ==null) {
			check = true;
		}
		return check;
	}

	//出勤編集処理(Top画面)
	public void reNewTodayStart(Timestamp start1,Timestamp start2) {
		User user = new User();
		 user = (User) session.getAttribute("loginUser");
		 int userId = user.getId();
		Timestamp todayTime = new Timestamp(System.currentTimeMillis());

		Attendance mm = new Attendance();
		mm = AttendanceRepository.todayBorderSearch(userId,start1,start2);
		int oo = mm.getId();
		Timestamp yy = mm.getBorderDate();

		AttendanceRepository.reNewTodayStart(oo,yy,todayTime);
	}

	//退勤登録(編集SQL)処理
	public void newTodayEnd(Timestamp start1,Timestamp start2) {
		Timestamp todayTime = new Timestamp(System.currentTimeMillis());
		User user = new User();
		 user = (User) session.getAttribute("loginUser");
		 int userId = user.getId();
		AttendanceRepository.newTodayEnd(userId,start1,start2,todayTime);
	}

	//休憩開始登録(編集SQL)処理
	public void newTodayRestStart(Timestamp start1,Timestamp start2) {
		Timestamp todayTime = new Timestamp(System.currentTimeMillis());
		User user = new User();
		 user = (User) session.getAttribute("loginUser");
		 int userId = user.getId();
		AttendanceRepository.newTodayRestStart(userId,start1,start2,todayTime);
	}

	//休憩終了登録(編集SQL)処理
	public void newTodayRestEnd(Timestamp start1,Timestamp start2) {
		Timestamp todayTime = new Timestamp(System.currentTimeMillis());
		User user = new User();
		 user = (User) session.getAttribute("loginUser");
		 int userId = user.getId();
		AttendanceRepository.newTodayRestEnd(userId,start1,start2,todayTime);
	}

	public List<Attendance> findById() {
		User user = new User();
		 user = (User) session.getAttribute("loginUser");
		 int userId = user.getId();
		 List<Attendance> allAttendance = AttendanceRepository.findByIdList(userId);
		return allAttendance;
	}

	public boolean startDateCheck(String dby, String dbm ,int days) {
		String day = String.valueOf(days);
		User user = new User();
		user = (User) session.getAttribute("loginUser");
		int userId = user.getId();
		String searchDateStart = dby+"-"+dbm+"-01 00:00:00";
		String searchDateEnd = dby+"-"+dbm+"-"+day+" 23:59:59";
		Timestamp sds = Timestamp.valueOf(searchDateStart);
		Timestamp sde = Timestamp.valueOf(searchDateEnd);

		 List<Attendance> searchAttendance = AttendanceRepository.findByStartDate(userId,sds,sde);
		boolean check = false;
		if(searchAttendance.size() == 0) {
			check = true;
		}
		return check;
	}

	public void saveStart(Timestamp dateS) {
		String date = "2000-01-01 00:00:00";
		Timestamp date2 = Timestamp.valueOf(date);
		User user = new User();
		 user = (User) session.getAttribute("loginUser");
		 int userId = user.getId();

		 int categoryId =11;
		AttendanceRepository.saveStart(userId,categoryId,date2,dateS);
	}

	public void deleteEdit(int id) {
		 String date = "2000-01-01 00:00:00";
		 Timestamp date2 = Timestamp.valueOf(date);
		AttendanceRepository.deleteEdit(id,date2);
	}

	//レコード1件取得
	public Attendance selectAttendance(int id ){
		return AttendanceRepository.findByAttendanceId(id);
	}

	//勤怠情報更新
	public void saveAttendance(Attendance editAttendance) {
		AttendanceRepository.save(editAttendance);
	}

	//月別リストメソッド
	  public List<Attendance>  generateList(String year,String month) {
		  User user = new User();
			user = (User) session.getAttribute("loginUser");

			Integer borderYear = Integer.parseInt(year);
			Integer borderMonth = Integer.parseInt(month);
			if(borderMonth >12) {
				month = "01";
				borderYear += 1;
				borderMonth = 1;
			}
			if(borderMonth < 1) {
				month = "12";
				borderYear -= 1;
				borderMonth =12;
			}

			Calendar c = new GregorianCalendar(borderYear,(borderMonth-1),1);
			int days=c.getActualMaximum(Calendar.DAY_OF_MONTH);
			String day = String.valueOf(days);
			String borderYearString = String.valueOf(borderYear);

			int userId = user.getId();
			String searchDateStart = borderYearString+"-"+month+"-01 00:00:00";
			String searchDateEnd = borderYearString+"-"+month+"-"+day+" 23:59:59";
			Timestamp sds = Timestamp.valueOf(searchDateStart);
			Timestamp sde = Timestamp.valueOf(searchDateEnd);

			 List<Attendance> AttendanceList = AttendanceRepository.findByStartDate(userId,sds,sde);

			 return AttendanceList;
	  }

	  public AttendanceListParam searchAll(Integer id) {
		    // 勤怠情報の取得
		  Date DateTime = new Date();
			SimpleDateFormat by = new SimpleDateFormat("yyyy");
			String dby = by.format(DateTime);
		    List<Attendance> attendanceList = generateList(dby,String.valueOf(id));
		    SimpleDateFormat simple = new SimpleDateFormat("HH:mm");

		    AttendanceListParam attendanceListParam = new AttendanceListParam();
		    List<AttendanceData> list = new ArrayList<AttendanceData>();
		    // エンティティを画面データに詰め替える
		    for(Attendance attendance : attendanceList) {
		      AttendanceData data = new AttendanceData();
		      data.setId(attendance.getId());
		      data.setUserId(attendance.getUserId());
		      data.setCategoryId(attendance.getCategoryId());

		      if(!attendance.getStartTime().equals("2000-01-01 00:00:00")) {
		    	  String str = simple.format(attendance.getStartTime());
		    	  data.setStartDate(str);
		      }else {data.setStartDate(String.valueOf(attendance.getStartTime()));}

		      if(!attendance.getEndTime().equals("2000-01-01 00:00:00")) {
		    	  String str = simple.format(attendance.getEndTime());
		    	  data.setEndDate(str);
		      }else {data.setEndDate(String.valueOf(attendance.getEndTime()));}

		      if(!attendance.getRestStartTime().equals("2000-01-01 00:00:00")) {
		    	  String str = simple.format(attendance.getRestStartTime());
		    	  data.setRestStartDate(str);
		      }else {data.setRestStartDate(String.valueOf(attendance.getRestStartTime()));}

		      if(!attendance.getRestEndTime().equals("2000-01-01 00:00:00")) {
		    	  String str = simple.format(attendance.getRestEndTime());
		    	  data.setRestEndDate(str);
		      }else {data.setRestEndDate(String.valueOf(attendance.getRestEndTime()));}



		      data.setBorderDate(attendance.getBorderDate());
		      data.setCreatedDate(attendance.getCreatedTime());
		      data.setUpdatedDate(attendance.getUpdatedTime());
		      list.add(data);
		    }
		    attendanceListParam.setAttendanceDataList(list);
		    return attendanceListParam;
		  }

	public void updateAll(AttendanceListParam param) {

		    List<Attendance> attendanceList = new ArrayList<Attendance>();

		    // 画面パラメータをエンティティに詰め替える
		    for (AttendanceData data : param.getAttendanceDataList()) {
		    String borderDateS = String.valueOf(data.getBorderDate()) ;
		    String date = borderDateS.substring(0, 10);
		    Attendance attendance = AttendanceRepository.findById(data.getId()).get();
		    attendance.setId(data.getId());
		    attendance.setUserId(data.getUserId());
		    attendance.setCategoryId(data.getCategoryId());


		    if(!StringUtils.isBlank(data.getStartDate()) && !data.getStartDate().equals("00:00")) {
		    	attendance.setStartTime(Timestamp.valueOf(date+" "+data.getStartDate()+":00"));
		    }else {
				attendance.setStartTime(Timestamp.valueOf("2000-01-01 00:00:00"));
		    }

		    if(!StringUtils.isBlank(data.getRestStartDate()) && !data.getRestStartDate().equals("00:00")) {
		    	attendance.setRestStartTime(Timestamp.valueOf(date+" "+data.getRestStartDate()+":00"));
		    }else {
				attendance.setRestStartTime(Timestamp.valueOf("2000-01-01 00:00:00"));

		    }

		    if(!StringUtils.isBlank(data.getRestEndDate()) && !data.getRestEndDate().equals("00:00")) {
		    	attendance.setRestEndTime(Timestamp.valueOf(date+" "+data.getRestEndDate()+":00"));
		    }else {
				attendance.setRestEndTime(Timestamp.valueOf("2000-01-01 00:00:00"));

		    }

		    if(!StringUtils.isBlank(data.getEndDate()) && !data.getEndDate().equals("00:00")) {
		    	attendance.setEndTime(Timestamp.valueOf(date+" "+data.getEndDate()+":00"));
		    }else {
				attendance.setEndTime(Timestamp.valueOf("2000-01-01 00:00:00"));

		    }

		    attendance.setBorderDate(data.getBorderDate());
		    attendance.setCreatedTime(data.getCreatedDate());
		    attendance.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
		    attendanceList.add(attendance);
		    }
		    AttendanceRepository.saveAll(attendanceList);
		  }

	public TotalAttendance total(Integer id) {
	    // 勤怠情報の取得
		Date DateTime = new Date();
		SimpleDateFormat by = new SimpleDateFormat("yyyy");
		String dby = by.format(DateTime);
	    List<Attendance> attendanceList = generateList(dby,String.valueOf(id));

	    int sumStartHour = 0;
	    int sumStartMinit = 0;

	    int sumRestStartHour = 0;
	    int sumRestStartMinit = 0;

	    int sumRestEndHour = 0;
	    int sumRestEndMinit = 0;

	    int sumEndHour = 0;
	    int sumEndMinit = 0;


	    for(Attendance i : attendanceList) {
	    	if((!String.valueOf(i.getStartTime()).equals("2000-01-01 00:00:00.0")) && (!String.valueOf(i.getEndTime()).equals("2000-01-01 00:00:00.0")) ) {

	    		String startDateS = String.valueOf(i.getStartTime()) ;
	    		String startDateHour = startDateS.substring(11, 13);
	    		String startDateMinit = startDateS.substring(14, 16);
	    		Integer startDateHourInt = Integer.parseInt(startDateHour);
	    		Integer startDateMinitInt = Integer.parseInt(startDateMinit);

	    		sumStartHour += startDateHourInt;
	    		sumStartMinit += startDateMinitInt;

	    		String endDateS = String.valueOf(i.getEndTime()) ;
	    		String endDateHour = endDateS.substring(11, 13);
	    		String endDateMinit = endDateS.substring(14, 16);
	    		Integer endDateHourInt = Integer.parseInt(endDateHour);
	    		Integer endDateMinitInt = Integer.parseInt(endDateMinit);

	    		sumEndHour += endDateHourInt;
	    		sumEndMinit += endDateMinitInt;

	    		if((!String.valueOf(i.getRestStartTime()).equals("2000-01-01 00:00:00.0") ) && (!String.valueOf(i.getRestEndTime()).equals("2000-01-01 00:00:00.0")) ) {

		    		String restStartDateS = String.valueOf(i.getRestStartTime()) ;
		    		String restStartDateHour = restStartDateS.substring(11, 13);
		    		String restStartDateMinit = restStartDateS.substring(14, 16);
		    		Integer restStartDateHourInt = Integer.parseInt(restStartDateHour);
		    		Integer restStartDateMinitInt = Integer.parseInt(restStartDateMinit);

		    		sumRestStartHour += restStartDateHourInt;
		    		sumRestStartMinit += restStartDateMinitInt;

		    		String restEndDateS = String.valueOf(i.getRestEndTime()) ;
		    		String restEndDateHour = restEndDateS.substring(11, 13);
		    		String restEndDateMinit = restEndDateS.substring(14, 16);
		    		Integer restEndDateHourInt = Integer.parseInt(restEndDateHour);
		    		Integer restEndDateMinitInt = Integer.parseInt(restEndDateMinit);

		    		sumRestEndHour += restEndDateHourInt;
		    		sumRestEndMinit += restEndDateMinitInt;


	    		}
	    	}
	    }

	    int Si =0;
	    while(sumStartMinit >= 60 ) {
	    	sumStartMinit -= 60;
	    	Si++;
	    	}
	    sumStartHour += Si;

	    int Ei =0;
	    while(sumEndMinit >= 60 ) {
	    	sumEndMinit -= 60;
	    	Ei++;
	    	}
	    sumEndHour += Ei;

	    int RSi =0;
	    while(sumRestStartMinit >= 60 ) {
	    	sumRestStartMinit -= 60;
	    	RSi++;
	    	}
	    sumRestStartHour += RSi;

	    int REi =0;
	    while(sumRestEndMinit >= 60 ) {
	    	sumRestEndMinit -= 60;
	    	REi++;
	    	}
	    sumRestEndHour += REi;


	    TotalAttendance totalAttendance = new TotalAttendance();
	    totalAttendance.setStartDate(sumStartHour);
	    totalAttendance.setRestStartDate(sumRestStartHour);
	    totalAttendance.setRestEndDate(sumRestEndHour);
	    totalAttendance.setEndDate(sumEndHour);


	return totalAttendance;
	}

	public void saveData(Attendance i) {
		AttendanceRepository.save(i);
	}

	public void delete(int id) {
		AttendanceRepository.deleteByUserIdAll(id);
	}


}
