# 테스트 코드의 구성 요소

## 상황, 실행, 결과 확인(given, when, then)

### sample code
```java

@Test
void exactMatch()
//given
BaseballGame game = new BaseballGame("456");

//when
Score score = game.guess("456");

//then
assertEquals(3, score.strikes());
```

- 또는 @BeforeEach를 적용한 메서드에서 given을 설정한다.
```java
private BaseballGame game;

@BeforeEach
void givenGame() {
    game = new BaeballGame("456");
}

@Test
void exactMatch()
//when
Score score = game.guess("456");

//then
assertEquals(3, score.strikes());
```
