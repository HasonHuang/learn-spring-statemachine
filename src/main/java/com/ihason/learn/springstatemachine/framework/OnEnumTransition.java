package com.ihason.learn.springstatemachine.framework;

import com.ihason.learn.springstatemachine.States;
import org.springframework.statemachine.annotation.OnStateChanged;
import org.springframework.statemachine.annotation.OnTransition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义枚举类型的 {@link OnTransition}
 *
 * @author Hason
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnTransition
public @interface OnEnumTransition {

    States[] source() default {};

    States[] target() default {};
}
