package com.cmd.patterns.template_method;

import com.cmd.patterns.template_method.dao.MemberDao;

/**
 * @author: tomcmd
 * @create: 2018/11/8 2:50 PM
 */
public class MemberDaoTest {
    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao();
        memberDao.query();
    }
}
