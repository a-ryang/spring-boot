package com.arynag.learnspringframework;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {
  public static void main(String[] args) {
    // 1. Spring 애플리케이션 or Spring Context를 실행
    var context =
      new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);

    // 2. Spring 프레임워크가 관리하도록 할 것을 설정
    //  - @Configuration 
    // 스프링에서 관리하는 클래스를 Bean이라 한다

    // 3. Spring이 관리하는 Bean 검색
    System.out.println(context.getBean("name")); // Rnaga
    System.out.println(context.getBean("age")); // 15
    System.out.println(context.getBean("person")); // Person[name=Ravi, age=20]
    System.out.println(context.getBean(Address.class)); // Person[name=Ravi, age=20]

    System.out.println(context.getBean("person2MethodCall")); // Person[name=Ranga, age=15]
    System.out.println(context.getBean("person3Parameters")); // Person[name=Ranga, age=15]

    Arrays.stream(context.getBeanDefinitionNames())
      .forEach(System.out::println);;
  }
}
