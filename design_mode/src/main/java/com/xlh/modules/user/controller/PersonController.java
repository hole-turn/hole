package com.xlh.modules.user.controller;

import com.xlh.modules.user.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: xielinhao
 * @title: PersonController
 * @projectName: hole
 * @description:
 * @date: 17:33 2021/8/3
 */
@RestController
@RequestMapping("person")
public class PersonController {
    @Resource
    private PersonService personService;
}
