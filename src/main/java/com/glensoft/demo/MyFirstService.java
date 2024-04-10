package com.glensoft.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySources({
        @PropertySource("classpath:custom.properties"),
        @PropertySource("classpath:custom.b.properties")
})
public class MyFirstService {
    private final MyFirstClass myFirstClass;
    private Environment environment;
    @Value("${my.prop}")
    private String myCustomPropertyFromAnotherFile;
    @Value("${my.prop.2}")
    private String myCustomPropertyFromASecondFile;
    public MyFirstService(@Qualifier("bean2") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }
    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String tellAStory() {
        return "The dependancy is saying : " + myFirstClass.sayHello();
    }

    public String getJavaVersion() {
        return "Java Version : " + environment.getProperty("java.version");
    }

    public String getOS() {
        return "Operating System : " + environment.getProperty("os.name");
    }
    public String readProp() {
        return "'my.custom.property' from application.properties : " + environment.getProperty("my.custom.property");
    }

    public String getMyCustomPropertyFromAnotherFile() {
        return myCustomPropertyFromAnotherFile;
    }

    public String getMyCustomPropertyFromASecondFile() {
        return myCustomPropertyFromASecondFile;
    }
}
