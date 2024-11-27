package com.bezkoder.spring.datajpa.mapper;

import org.modelmapper.ModelMapper; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
@Configuration
public class BeanMapper {
@Bean
public ModelMapper modelMapper() {
    return new ModelMapper();
}

}
