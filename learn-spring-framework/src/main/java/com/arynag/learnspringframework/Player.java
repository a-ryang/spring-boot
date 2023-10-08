package com.arynag.learnspringframework;

public class Player {
  private Hero hero;

  public Player(Hero hero) {
    this.hero = hero;
  }

  public void play() {
    hero.move();
    hero.move();
    hero.attack();
  }
}
