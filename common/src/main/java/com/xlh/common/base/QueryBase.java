package com.xlh.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryBase {

    @ApiModelProperty("当前页")
    private Integer current = 1;
    @ApiModelProperty("每页的数量")
    private Integer size = 20;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current < 1 ? 1 : current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size =  size < 10 ? 10 : size;
    }
}
