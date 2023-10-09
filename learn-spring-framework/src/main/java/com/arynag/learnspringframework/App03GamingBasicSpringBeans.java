package com.arynag.learnspringframework;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App03GamingBasicSpringBeans {
  public static void main(String[] args) {

    try (var context = new AnnotationConfigApplicationContext(GamingConfiguration.class)) {

      context.getBean(Hero.class).attack();
      context.getBean(Player.class).play();
      
    } catch (BeansException e) {
      e.printStackTrace();
    }
  }
}
