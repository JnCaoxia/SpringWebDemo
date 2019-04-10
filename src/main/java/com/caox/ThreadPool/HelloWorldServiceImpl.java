package com.caox.ThreadPool;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/4 17:49
 */
@Setter
@Getter
@ToString
public class HelloWorldServiceImpl implements HelloWorldService {

    private String value1;

    private String value2;
    @Override
    public String sayHello() {
        return "hello：" + value1;
    }

    @Override
    public String getBeanName() {
        return "bean Name：" + value2;
    }
}
