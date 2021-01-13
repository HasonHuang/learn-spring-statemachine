package com.ihason.learn.springstatemachine.framework;

import com.ihason.learn.springstatemachine.States;
import org.springframework.statemachine.annotation.OnStateChanged;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnStateChanged
public @interface OnEnumStateChanged {

    States[] source() default {};

    States[] target() default {};
}
