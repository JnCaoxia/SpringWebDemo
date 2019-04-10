package com.caox.ThreadPool.FactoryBean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/5 10:48
 */
@Setter
@Getter
public class CarFactoryBean implements FactoryBean<Car> {
    private String carInfo;

    @Override
    public Car getObject() throws Exception {
        Car car =  new  Car () ;
        String []  infos =  carInfo .split ( "," ) ;
        car.setBrand ( infos [ 0 ]) ;
        car.setMaxSpeed ( Integer. valueOf ( infos [ 1 ])) ;
        car.setPrice ( Double. valueOf ( infos [ 2 ])) ;
        return  car;
    }

    @Override
    public Class<Car> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
