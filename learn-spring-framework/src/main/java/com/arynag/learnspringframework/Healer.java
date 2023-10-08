package com.arynag.learnspringframework;

public class Healer implements Hero {
  public void attack() {
    System.out.println("힐러가 봉으로 공격합니다!");
  }

  public void move() {
    System.out.println("힐러가 침착하게 움직입니다");
  }

}
