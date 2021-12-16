package com.xlh.annotation;

import java.lang.annotation.*;


@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE_PARAMETER,ElementType.TYPE_USE})
public @interface MyExample {


}


