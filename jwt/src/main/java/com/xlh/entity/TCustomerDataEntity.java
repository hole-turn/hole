package com.xlh.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 客户表
 * 
 * @date 2021-11-14 19:51:11
 */
@Data
@TableName("t_customer_data")
public class TCustomerDataEntity extends Model<TCustomerDataEntity>  {

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 编号
	 */
	private String patientNo;
	/**
	 * 头图url
	 */
	private String fileUrl;
	/**
	 * 客户姓名
	 */
	private String custName;
	/**
	 * 客户手机
	 */
	private String phone;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * openId
	 */
	private String openId;
	/**
	 * 会员积分
	 */
	private Integer membershipPoint;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 邀请码
	 */
	private String invitationCode;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 性别: 0女 1男
	 */
	private Integer gender;
	/**
	 * 0启用 1禁用
	 */
	private Integer status;
	/**
	 * 来源 0九医云 1互联网医院 2九药云
	 */
	private Integer origin;
	/**
	 * 0未删除 1已删除
	 */
	private Integer isDel;
	/**
	 * 邀请这个患者的推广者表中的邀请码
	 */
	private String inviteCode;

}
