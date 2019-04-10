package com.caox.ThreadPool.FactoryBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/5 11:14
 */
@Setter
@Getter
@ToString
public class FactoryBeanPojo implements FactoryBean {
    private String type;

    @Override
    public Object getObject() throws Exception {
        if("student".equals(type)){
            return new Student();
        }else{
            return new School();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return School.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
