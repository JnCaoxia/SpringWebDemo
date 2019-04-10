package com.caox.ReqDTO;

import com.caox.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.util.List;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/25 10:01
 */
@Setter
@Getter
@ToString
public class TestReqDTO {
//    @NotNull(message = "请求ID不能为空")
//    @NotBlank(message = "请求ID不能为空或空格符")
    private String reqId;

    @NotNull(message = "列表参数不能为空")
    @NotEmpty(message = "列表参数集合内容不能为空")
    @NotBlank(message = "列表参数不能为空")
    private List<User> userList;
}
