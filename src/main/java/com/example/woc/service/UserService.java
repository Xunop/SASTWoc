package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import com.example.woc.utils.SecurityUtils;
import com.sun.xml.internal.ws.api.model.ExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //示例

    //注册

    public int uploadUsername(Account account) {
        String s = userMapper.queryUserByUname(account.getUsername());
        Integer id = userMapper.findId(account.getId());
        if (s == null && id == null){
            String Password = SecurityUtils.encodePassword(account.getPassword());//此处加密
            userMapper.addUser(account.getId(), account.getUsername(), Password, account.getEmail());
            System.out.println("注册成功！");
            return 1;
        }else {
            System.out.println("注册失败！");
            return 0;
        }

    }

    //登录  0为假1为真

    public int login(String username, String password){
            String s = userMapper.queryUserByUname(username);
                if (s == null) {
                    return 0;
            }else {
                    String pword = userMapper.pword(username);
                    if (SecurityUtils.matchesPassword(password, pword)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
    }
}
