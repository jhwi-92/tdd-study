JUnit은 크게 세 개의 요소로 구성되어 있다.
- JUnit 플랫폼 
	- 테스팅 프레임워크를 구동하기 위한 런처와 테스트 엔진을 위한 API 제공
- JUnit 주피터
	- JUnit 5를 위한 테스트 API와 실행 엔진 제공
- JUnit 빈티지
	- JUnit 3과 JUnit 4로 작성된 테스트를 JUnit 5 플랫폼에서 실행하기 위한 모듈

## Maven
```
<dependencies>
	<dependency>
		<groupId>org.junit.jupiter</groupId>
		<artifactId>junit-jupiter</artifactId>
		<version>5.5.0</version>
		<scope>test</scope>
	</dependency>
</dependencies>

<build>
	<plugins>
		<plugin>
			<artifactId>maven-surefire-plugin</artifactId>
			<version>2.22.1</version>
		</plugin>
		...생략
	<plugins>
</build>
```

## Gradle
```
plugins {
    id 'java'
}
sourceCompatibility = '1.8'
targetCompatibility = '1.8'
compileJava.options.encoding = 'UTF-8'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation ('org.junit.jupiter:junit-jupiter-api:5.5.0')
}

test {
    useJUnitPlatform()
    testLogging{
        event "passed", "skipped", "failed"
    }
}
```

## @Test 애노테이션과 테스트 메서드
테스트로 사용할 클래스를 만들고 @Test 애노테이션을 메서드에 붙인다.
메서드는 private이면 안 된다.

```java
public class Stringtest {
    @Test
    void subString() {
        String str = "abcde";
        assertEquals("cd", str.substring(2,4));
    }
}

```

### Assertions 클래스
값을 검증하기 위한 목적의 다양한 정적 메서드 제공.
#### assertEquals(expected, actual)
실제 값이 기대하는 값과 같은지 검사.
주요 타입별로 assertEquals() 메서드가 존재한다.
#### assertNotEquals(unexpected, actual)
실제 값이 특정 값과 같지 않은지 검사.
#### assertSame(Object expected, Object actual)
두 객체가 동일한 객체인지 검사.
#### assertNotSame(Object unexpected, Object actual)
두 객체가 동일하지 않은 객체인지 검사.
#### assertTrue(boolean condition)
값이 true인지 검사.
#### assertFalse(boolean condition)
값이 false인지 검사.
#### assertNull(Object actual)
값이 null인지 검사.
#### assertNotNull(Object actual)
값이 null이 아닌지 검사.
#### fail()
테스트를 실패 처리.
```
try{
	AuthService authService = new AuthService();
	authService.authenticate(null, null);
	fail(); //이 지점에 다다르면 테스트 실패 에러 발생.
}
```


#### assertThrows(Class expectedType, Executable executable)
executable을 실행한 결과로 지정한 타입의 익셉션이 발생하는지 검사.

#### assertDoesNotThrows(Class expectedType, Executable executable)
executable을 실행한 결과로 익셉션이 발생하지 않는지 검사.

#### assertAll()
모든 검증을 실행하고 그 중에 실패한 것이 있는지 확인.
```
asserAll(
	()->assertEquals(3, 5/2),
	()->assertEquals(4, 2*2),
	()->assertEquals(6, 11/2)
);
```

## 테스트 라이프사이클
### @BeforeEach 애노테이션과 @AfterEach 애노테이션
JUnit 코드 진행 순서.
1. 테스트 메서드를 포함한 객체 생성
2. (존재하면) @BeforeEach 애노테이션이 붙은 메서드 실행
3. @Test 애노테이션이 붙은 메서드 실행
4. (존재하면) @AfterEach 애노테이션이 붙은 메서드 실행
```
public class LifecycleTest {
    public LifecycleTest() {
        System.out.println("new LifecycleTest");
    }

    @BeforeEach
    void setUp(){
        System.out.println("setUp");
    }

    @Test
    void a(){
        System.out.println("A");
    }
    @Test
    void b(){
        System.out.println("B");
    }
    @AfterEach
    void tearDown(){
        System.out.println("tearDown");
    }
}

new LifecycleTest
setUp
A
tearDown

new LifecycleTest
setUp
B
tearDown

```
@Test 메서드를 실행할 때마다 객체를 새로 생성하고
테스트 메서드를 실행하기 전과 후에 @BeforeEach와 @AfterEach가 붙은 메서드가 실행된다.
### @BeforeAll 애노테이션과 @AfterEach 애노테이션
한 클래스의 모든 테스트 메서드가 실행되기 전에 실행하는 애노테이션.

- 각 테스트 메서드는 서로 독립적으로 동작해야 한다.
- 한 테스트 메서드의 결과에 따라 다른 테스트 메서드의 실행 결과가 달라지면 안된다.

### @DisplayName
테스트에 표시 이름을 붙일 수 있다.

### @Disabled
특정 테스트를 실행하지 않고 싶을 때 사용한다.
