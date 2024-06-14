# JUnit5 모듈 구성
> JUnit 5는 크게 세 개의 요소로 구성되어 있다. 
- JUnit 플랫폼<br>
테스팅 프레임워크를 구동하기 위한 런처와 테스트 엔진을 위한 API 제공<br>

- JUnit 주피터(Jupiter)<br>
JUnit 5를 위한 테스트 API와 실행 엔진 제공<br>

- JUnit 빈티지(Vintage)<br>
JUnit 3과 4로 작성된 테스트를 JUnit 5 플랫폼에서 실행하기 위한 모듈 제공<br>

JUnit 5는 테스트를 위한 API로 주피터 API 제공
junit-jupiter 모듈은 `junit-jupiter-api 모듈`, `junit-jupiter-params 모듈`, `junit-jupiter-engine 모듈`을 포함한다.

# @Test 애노테이션과 테스트 메서드
```java
...
public class SumTest{

    @Test
    void sum(){
        int result = 2 + 3;
        assertEquals(5, result);
    }
}
```

- 테스트 클래스는 다른 클래스와 구분을 쉽게 하기 위해 `Test`를 접미사로 붙인다. 
- 테스트를 실행할 메서드에는 `@Test` 애노테이션을 붙인다.
- @Test 애노테이션을 붙인 메서드는 `private`면 안된다. 

# 주요 단언 메서드
|메서드|설명|
|---|:---|
assertEquals(expected, actual)|실제 값(actual)이 기대하는 값(expected)과 같은지 검사한다.
assertNotEquals(unexpected, actual)|실제 값(actual)이 특정 값(unexpected)과 같지 않은지 검사한다.
assertSame(Object expected, Object actual)|두 객체가 동일한 객체인지 검사한다.
assertNotSame(Object unexpected, Object actual)|두 객체가 동일하지 않은 객체인지 검사한다.
assertTrue(boolean condition)|값이 true인지 검사한다.
assertFalse(boolean condition)|값이 false인지 검사한다.
assertNull(Object actual)|값이 null인지 검사한다.
assertNotNull(Object actual)|값이 null이 아닌지 검사한다.
fail()|테스트를 실패 처리한다.

## Assertions가 제공하는 익셉션 발생 유무 검사 메서드
|메서드|설명|
|---|:---|
assertThrows(Class<T> expectedType, Executable executable)|executable을 실행한 결과로 지정한 타입의 익셉션이 발생하는지 검사한다.
assertDoesNotThrow(Executable executable)|executable을 실행한 결과로 익셉션이 발생하지 않는지 검사한다. 

# 테스트 라이프사이클
## @BeforeEach 애노테이션과 @AfterEach 애노테이션
JUnit은 각 테스트 메서드마다 다음 순서대로 코드를 실행한다.
1. 테스트 메서드를 포함한 객체 생성
2. (존재하면) @BeforeEach 애노테이션이 붙은 메서드 실행
3. @Test 애노테이션이 붙은 메서드 실행
4. (존재하면) @AfterEach 애노테이션이 붙은 메서드 실행
<br>

`@BeforeEach`
- 테스트를 실행하는데 필요한 준비 작업을 할 때 사용
- 임시 파일을 생성한다거나 테스트 메서드에서 사용할 객체 생성
<br>

`@AfterEach`
- 테스트를 실행한 후 정리할 것이 있을 때 사용
- 테스트에서 사용한 임시파일 삭제
<br>

## @BeforeAll 애노테이션과 @AfterAll 애노테이션
`@BeforeAll` 
- 한 클래스의 모든 테스트 메서드가 실행되기 전에 특정 작업을 수행해야 할 때 사용
- 정적 메서드에 적용
- 클래스의 모든 테스트 메서드를 실행하기 전에 한 번 실행됨.
<br>

`@AfterAll`
- 클래스의 모든 테스트 메서드를 실행한 뒤에 실행
- 정적 메서드에 적용

## 테스트 메서드 간 실행 순서 의존과 필드 공유하지 않기
- 각 메서드는 서로 독립적으로 동작해야 한다.
- 한 테스트 메서드의 결과에 따라 다른 테스트 메서드의 실행 결과가 달라지면 안 된다.
- 테스트 메서드가 서로 필드를 공유하거나 실행 순서를 가정하고 테스트를 작성하지 말아야 한다. 

## @DisplayName, @Disabled
`@DisplayName`
- 테스트에 이름 붙일 때 사용

`@Disabled`
- 특정 테스트를 실행하지 않고 싶을 때 사용
  - 아직 테스트가 완성되지 않았거나 잠시 테스트를 실행하지 않을 때 사용
