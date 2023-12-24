package com.example.buaadb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.entity.Login;
import com.example.buaadb.entity.Sel;
import com.example.buaadb.mapper.LoginMapper;
import com.example.buaadb.mapper.SelMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginService extends ServiceImpl<LoginMapper, Login> {

}
