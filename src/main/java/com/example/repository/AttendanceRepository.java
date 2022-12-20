package com.example.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Attendance;


@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	//勤怠取得
	@Query
	(value = "SELECT * FROM attendance WHERE  user_id = :userId   ORDER BY border_date ASC ", nativeQuery = true)
	List<Attendance> findByIdList(@Param("userId") int userId);

	//編集する勤怠取得
	@Query
	(value = "SELECT * FROM attendance WHERE  id = :id   ORDER BY id DESC ", nativeQuery = true)
	Attendance findByAttendanceId(@Param("id") int id);


	//出勤登録確認
	@Query
	(value = "SELECT * FROM attendance WHERE ( user_id = :userId )  AND ( start_date  BETWEEN :start AND :startt )  ORDER BY border_date DESC LIMIT 1", nativeQuery = true)
	Attendance todayStartSearch(@Param("userId") int userId,@Param("start") Timestamp start,@Param("startt") Timestamp startt);

	//出勤登録確認
	@Query
	(value = "SELECT * FROM attendance WHERE ( user_id = :userId )  AND ( border_date  BETWEEN :start AND :startt )  ORDER BY border_date DESC LIMIT 1", nativeQuery = true)
	Attendance todayBorderSearch(@Param("userId") int userId,@Param("start") Timestamp start,@Param("startt") Timestamp startt);


	//休憩開始登録確認
	@Query
	(value = "SELECT * FROM attendance WHERE ( user_id = :userId )  AND ( rest_start_date  BETWEEN :start AND :startt )  ORDER BY id DESC LIMIT 1", nativeQuery = true)
	Attendance todayRestStartSearch(@Param("userId") int userId,@Param("start") Timestamp start,@Param("startt") Timestamp startt);


	//出勤登録（Top画面）
	@Modifying
	@Transactional
	@Query
	(value = " INSERT INTO attendance ( user_id , category_id , content, start_date , end_date , rest_start_date , rest_end_date ) Values ( :userId , :categoryId , '' , :todayTime , :defaultDate , :defaultDate , :defaultDate )", nativeQuery = true)
	public void newTodayStart(@Param("userId") int userId, @Param("categoryId") int categoryId,
	@Param("todayTime") Timestamp todayTime, @Param("defaultDate") Timestamp defaultDate);

	//出勤登録（Top画面）2回目以降
	@Transactional
	@Modifying
	@Query(value ="UPDATE attendance SET start_date = :todayTime , border_date = :border"
		+ " WHERE id = :id     ", nativeQuery = true)
	public void reNewTodayStart(@Param("id") int id,@Param("border") Timestamp border,
			@Param("todayTime") Timestamp todayTime);


	//退勤登録（Top画面）
	@Transactional
	@Modifying
	@Query(value ="UPDATE attendance SET end_date = :todayTime "
		+ " WHERE ( user_id = :userId )  AND ( start_date  BETWEEN :start AND :startt )  ", nativeQuery = true)
	public void newTodayEnd(@Param("userId") int userId,@Param("start") Timestamp start,@Param("startt") Timestamp startt,
			@Param("todayTime") Timestamp todayTime);

	//休憩開始登録（Top画面）
	@Transactional
	@Modifying
	@Query(value ="UPDATE attendance SET rest_start_date = :todayTime "
		+ " WHERE ( user_id = :userId )  AND ( start_date  BETWEEN :start AND :startt )  ", nativeQuery = true)
	public void newTodayRestStart(@Param("userId") int userId,@Param("start") Timestamp start,@Param("startt") Timestamp startt,
			@Param("todayTime") Timestamp todayTime);

	//休憩終了登録（Top画面）
	@Transactional
	@Modifying
	@Query(value ="UPDATE attendance SET rest_end_date = :todayTime "
		+ " WHERE ( user_id = :userId )  AND ( start_date  BETWEEN :start AND :startt )  ", nativeQuery = true)
	public void newTodayRestEnd(@Param("userId") int userId,@Param("start") Timestamp start,@Param("startt") Timestamp startt,
			@Param("todayTime") Timestamp todayTime);


	//自動登録確認
	@Query
	(value = "SELECT * FROM attendance WHERE ( user_id = :userId )  AND ( border_date  BETWEEN :start AND :startt )  ORDER BY border_date ASC ", nativeQuery = true)
	List<Attendance> findByStartDate(@Param("userId") int userId,@Param("start") Timestamp start,@Param("startt") Timestamp startt);

	//自動登録（Top画面）
	@Modifying
	@Transactional
	@Query
	(value = " INSERT INTO attendance ( user_id , category_id , content, start_date , end_date , rest_start_date , rest_end_date ,border_date ) "
			+ " Values ( :userId , :categoryId , '' , :date , :date , :date , :date , :dateS )", nativeQuery = true)
	public void saveStart(@Param("userId") int userId, @Param("categoryId") int categoryId,
	@Param("date") Timestamp date, @Param("dateS") Timestamp dateS);

	//勤怠削除処理＜編集処理＞（Top画面）
	@Transactional
	@Modifying
	@Query(value ="UPDATE attendance SET start_date = :date ,end_date = :date ,rest_start_date = :date ,rest_end_date = :date , category_id = '11', content = ''  "
		+ " WHERE id = :id ", nativeQuery = true)
	public void deleteEdit(@Param("id") int id, @Param("date") Timestamp date);

	@Modifying
	@Transactional
	@Query(value ="DELETE FROM attendance WHERE user_id = :id ", nativeQuery = true)
	public void deleteByUserIdAll(@Param("id") int id);
}
