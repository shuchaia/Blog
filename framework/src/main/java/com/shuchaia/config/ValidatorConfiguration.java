package com.shuchaia.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @ClassName ValidatorConfiguration
 * @Description 配置hibernate Validator为快速失败返回模式
 * @Author shuchaia
 * @Date 2023/7/10 14:16
 * @Version 1.0
 */
@Configuration
 public class ValidatorConfiguration {
     @Bean
     public Validator validator(){
         ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                 .configure()
                 .addProperty( "hibernate.validator.fail_fast", "true" )
                 .buildValidatorFactory();
         Validator validator = validatorFactory.getValidator();

         return validator;
     }
 }