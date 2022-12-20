package com.example.model;
import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * ユーザー情報一覧画面用 データクラス
 */
@Data
public class AttendanceListParam implements Serializable {
  /**
   * ユーザー情報リスト
   */
  @Valid
  @Getter
  @Setter
  private List<AttendanceData> attendanceDataList;
}
