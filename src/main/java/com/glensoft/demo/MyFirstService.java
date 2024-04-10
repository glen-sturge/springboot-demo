package com.glensoft.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {
    private final MyFirstClass myFirstClass;
    @Value("${my.custom.property}")
    private String customProperty;
    @Value("${my.custom.int.property}")
    private Integer customPropertyInt;
    public MyFirstService(@Qualifier("bean1") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }


    public String tellAStory() {
        return "The dependancy is saying : " + myFirstClass.sayHello();
    }

    public String getCustomProperty() {
        return customProperty;
    }

    public Integer getCustomPropertyInt() {
        return customPropertyInt;
    }
}
