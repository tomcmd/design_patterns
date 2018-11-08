package com.cmd.patterns.template_method;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: tomcmd
 * @create: 2018/11/8 10:52 AM
 */
public class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    private PreparedStatement createPreparedStatement(Connection conn, String sql) throws Exception {
        return conn.prepareStatement(sql);
    }

    private ResultSet executeQuery(PreparedStatement psmt, Object[] values) throws Exception {
        for (int i = 0; i < values.length; i++) {
            psmt.setObject(i, values[i]);
        }
        return psmt.executeQuery();
    }

    private void closeStatement(Statement stmt) throws Exception {
        stmt.close();
    }

    private void closeResultSet(ResultSet rs) throws Exception {
        rs.close();
    }

    private void closeConnection(Connection conn) throws Exception {
        //通常把它放到连接池回收
    }

    private List<?> parseResultSet(ResultSet rs, RowMapper rowMapper) throws Exception {
        List<Object> result = new ArrayList<>();
        int rowNm = 1;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNm++));
        }
        return result;
    }

    public List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values) {
        try {
            //1.获取连接
            Connection conn = this.getConnection();
            //2.创建语句集
            PreparedStatement psmt = this.createPreparedStatement(conn, sql);
            //3.执行语句集，并获得结果集
            ResultSet rs = this.executeQuery(psmt, values);
            //4.解析结果集
            List<?> result = this.parseResultSet(rs, rowMapper);
            //5.关闭结果集
            this.closeResultSet(rs);
            //6.关闭语句集
            this.closeStatement(psmt);
            //7.关闭连接
            this.closeConnection(conn);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //public abstract Object processResult(ResultSet rs, int rowNum) throws Exception;
}
