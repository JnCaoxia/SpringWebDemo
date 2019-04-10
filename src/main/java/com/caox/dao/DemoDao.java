package com.caox.dao;
import com.caox.TimeOutAop.InterfaceProperty;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
/**
 * @author : nazi
 * @version : 1.0
 * @date : 2018/12/28 17:50
 */
public class DemoDao {
    private JdbcTemplate jdbcTemplate;

    // 测试获取address信息
    public List<Map<String,Object>> getAddressAll(){
        String sql = "select * from address";
        try{
            return jdbcTemplate.queryForList(sql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Map<String, Object>> getAddress(String address){
        String sql = "select * from address where address= " + "'" + address + "'";
        try{
            return jdbcTemplate.queryForList(sql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
