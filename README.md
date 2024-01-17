# spring-boot

## CommandLineRunner / ApplicationRunner

> https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.spring-application.command-line-runner

- 애플리케이션이 시작된 후 특정 코드를 실행하는 데 사용되는 인터페이스
- 커맨드 라인 인자(`args`)에 접근할 수 있게 해준다.
- `CommandLineRunner`, `ApplicationRunner` 두 인터페이스는 인자를 처리하는 방식에서 차이가 있다.
  - CommandLineRunner의 인자 String[] args
  - CommandLineRunner의 인자 ApplicationArguments args

### 예시코드
```java
// 어플리케이션 시작후 동작 확인
@SpringBootApplication
public class MyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}

	@Bean
	CommandLineRunner init(TestService testService) {
		return (args) -> {
			testService.say();
		};
	}

}

```
### 실행 결과
```bash
2024-01-17T21:31:02.575+09:00  INFO 51223 --- [  restartedMain] c.n.boot.MyApplication     : Started MyApplication in 0.976 seconds (process running for 1.465)
2024-01-17T21:31:02.575+09:00  INFO 51223 --- [  restartedMain] c.n.boot.service.TestService         : Say Hi
```
### 커맨드 라인 인자 사용
두 인터페이스로 `--key=value`, `--key2=value2`에 접근할 수 있다.
```bash
java -jar MyApplication.jar --key=value --key2=value2
```
