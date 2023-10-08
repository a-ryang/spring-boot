package com.arynag.learnspringframework;

public class App01GamingBasicJava {
  public static void main(String[] args) {

    Warrior warrior = new Warrior(); // 1.객체를 만든다
    Archer archer = new Archer();
    Healer healer = new Healer();

    Player player = new Player(warrior); // 2. 객체를 만든다 + 종족성을 연결한다
                                         // Hero는 Player 클래스의 의존성이다

    player.play();
  }
}
