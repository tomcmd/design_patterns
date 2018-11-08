package com.cmd.patterns.template_method.dao;

import com.cmd.patterns.template_method.JdbcTemplate;
import com.cmd.patterns.template_method.RowMapper;
import com.cmd.patterns.template_method.entity.Member;

import java.sql.ResultSet;
import java.util.List;

/**
 * 解耦
 * @author: tomcmd
 * @create: 2018/11/8 2:44 PM
 */
public class MemberDao {

    //为什么不继承，主要是为了解耦
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(null);

    public List<?> query() {
        String sql = "select * from t_member";

        return jdbcTemplate.executeQuery(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws Exception {
                Member member = new Member();
                member.setUsername(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                return member;
            }
        }, null);
    }
}
