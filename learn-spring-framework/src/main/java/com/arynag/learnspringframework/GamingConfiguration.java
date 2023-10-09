package com.arynag.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GamingConfiguration {
  
  @Bean
  public Hero hero() {
    Hero hero = new Warrior();
    return hero;
  }

  @Bean
  public Player player(Hero hero) {
    Player player = new Player(hero);
    return player;
  }
}
