package com.caox.model.mq;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/7 16:57
 */
@Setter
@Getter
@ToString
public class ParamsDTO implements Serializable {
    /** 消息 */
    private String message;
}
