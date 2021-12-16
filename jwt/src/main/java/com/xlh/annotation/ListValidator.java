//package com.xlh.annotation;
//
//import org.springframework.stereotype.Service;
//
//import javax.validation.*;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author: xielinhao
// * @title: ListValidator
// * @projectName: hole
// * @description:
// * @date: 15:41 2021/12/16
// */
//public class ListValidator implements ConstraintValidator<MyValid, List> {
//
//    private int min;
//    private int max;
//
//    @Override
//    public void initialize(MyValid constraintAnnotation) {
//        this.max = constraintAnnotation.max();
//        this.min = constraintAnnotation.min();
//    }
//
//    @Override
//    public boolean isValid(List list, ConstraintValidatorContext context) {
//        if (list != null) {
//            if (list.size() < min || list.size() > max) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//}