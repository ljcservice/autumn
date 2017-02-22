package com.hitzd.Annotations;

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MHPerformProp
{
    /* ���������� */
    @SuppressWarnings ("rawtypes")
    public Class[] MethodParam() default {} ; 
    /* ���������� */
    @SuppressWarnings ("rawtypes")
    public Class ReturnType() default Object.class; 
}