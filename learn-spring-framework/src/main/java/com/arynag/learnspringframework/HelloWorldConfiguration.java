package com.arynag.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// record - jdk16에서 추가된 기능
// 세터 게터 생성자를 만들 필요가 없음
record Person (String name, int age, Address address) { };
record Address (String name, String city) { };

@Configuration
public class HelloWorldConfiguration {
  
  @Bean
  public String name() {
    return "Ranga";
  }

  @Bean
  public int age() {
    return 15;
  }

  @Bean
  public Person person() {
    return new Person("Ravi", 20, new Address("hi", "seoul"));
  }

  @Bean
  public Person person2MethodCall() {
    return new Person(name(), age(), address());
  }

    @Bean
  public Person person3Parameters(String name, int age, Address address) {
    return new Person(name, age, address);
  }

  @Bean(name = "address2")
  public Address address() {
    return new Address("Baker Street", "London");
  }

}
