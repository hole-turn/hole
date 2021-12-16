package com.xlh.vo;

import com.xlh.annotation.MyValid;
import com.xlh.entity.ExtraNode;
import lombok.Data;

import java.util.List;

/**
 * @author: xielinhao
 * @title: ExtraInfoVO
 * @projectName: hole
 * @description:
 * @date: 16:03 2021/12/16
 */
@Data
public class ExtraInfoVO {

    private Integer id;

    @MyValid(max = 1,message = "超出一条！")
    private List<ExtraNode> extraList;

    @MyValid
    private ExtraNode extraNode;

}
