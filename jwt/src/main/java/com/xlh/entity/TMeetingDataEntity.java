package com.xlh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 会议内容数据表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-14 12:28:40
 */
@Data
@Accessors(chain = true)
@TableName("t_meeting_data")
public class TMeetingDataEntity extends Model<TMeetingDataEntity> {
	/**
	 * 
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	private String uuid;
	/**
	 * 会议日期
	 */
	private Date meetingDate;
	/**
	 * 会议开始时间
	 */
	private Date meetingDateStart;
	/**
	 * 会议结束时间
	 */
	private Date meetingDateEnd;
	/**
	 * 目标医院
	 */
	private String hospitalTarget;
	/**
	 * 医院等级
	 */
	private String hospitalLevel;
	/**
	 * 目标科室
	 */
	private String officeTarget;
	/**
	 * 会议人数
	 */
	private Integer meetingNum;
	/**
	 * 会议人员  逗号分割
	 */
	private String meetingMembers;
	/**
	 * 积分
	 */
	private Integer integral;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 会议ID
	 */
	private String meetId;
	/**
	 * 费用结算
	 */
	private BigDecimal costSettle;
	/**
	 * 会议时长
	 */
	private Time meetTime;

	private String serviceObj;

	private String meetingMemberIds;

}
