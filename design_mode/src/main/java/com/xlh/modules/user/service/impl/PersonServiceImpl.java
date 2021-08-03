package com.xlh.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlh.modules.user.bean.Person;
import com.xlh.modules.user.mapper.PersonMapper;
import com.xlh.modules.user.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * @author: xielinhao
 * @title: PersonServiceImpl
 * @projectName: hole
 * @description:
 * @date: 17:31 2021/8/3
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper,Person> implements PersonService {
}
