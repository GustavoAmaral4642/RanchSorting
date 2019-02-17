package com.ranchsorting.util.jpa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

@InterceptorBinding //dizendo que é uma anotação de interceptação
@Retention(RetentionPolicy.RUNTIME) // explicado anterioremente. 
@Target({ ElementType.TYPE, ElementType.METHOD }) // explicado anterioremente.
public @interface Transactional {

}
