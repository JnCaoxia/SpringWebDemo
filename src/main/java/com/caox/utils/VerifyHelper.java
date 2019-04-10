package com.caox.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/25 9:57
 */
@Slf4j
public class VerifyHelper {
    private static Validator validator = new Validator();

    /**
     * 对象属性校验
     *
     * @param object 请求校验对象
     */
    public static void validatorObject(Object object) {
        List<ConstraintViolation> list = validator.validate(object);
        if (null != list && !list.isEmpty()) {
            log.info("call validatorObject :{}", list.get(0).getMessage());

        }
    }

    /**
     * 请求参数Object非空校验
     *
     * @param value 请求参数model
     */
    public static void validatorNotNull(Object value) {
        if (null == value) {
            log.info("call validatorNotNull {}", "MAQ_INVALID_PARAMETER");
        }
        if(value instanceof String && StringUtils.isEmpty(value.toString())){
            log.info("call validatorNotNull {}", "MAQ_INVALID_PARAMETER");
        }
    }
}
