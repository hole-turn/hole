package com.xlh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;

import java.util.List;


/**
 * @author: xielinhao
 * @title: a
 * @projectName: hole
 * @description:
 * @date: 17:00 2021/9/13
 */
@Data
@TableName(autoResultMap = true,value = "extra_info")
public class ExtraInfo extends Model<ExtraInfo> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private ExtraNode extraObject;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<ExtraNode> extraList;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private ExtraNode[] extraArray;

}
