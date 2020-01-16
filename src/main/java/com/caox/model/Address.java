package com.caox.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/5/28 13:48
 */
@Setter
@Getter
@ToString
public class Address {

    /** 主键ID */
    private String id;

    /** 地址 */
    private String address;

    /** 备注 */
    private String remark;

    private Boolean flag;
}
