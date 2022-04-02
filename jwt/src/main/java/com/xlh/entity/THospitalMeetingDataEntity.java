package com.xlh.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 会议医院表
 * 
 * @date 2021-11-14 15:28:35
 */
@Data
@TableName("t_hospital_meeting_data")
public class THospitalMeetingDataEntity extends Model<THospitalMeetingDataEntity> {
	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 医院办事处
	 */
	private String hospitalProvince;
	/**
	 * 医院地/市
	 */
	private String hospitalCity;
	/**
	 * 医院级别
	 */
	private String hospitalLevel;
	/**
	 * 医院名称
	 */
	private String hospitalName;

}
