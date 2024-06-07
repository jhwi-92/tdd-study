# TDD 시작
+ TDD는 Test-driven Development의 약자로 우리 말로는 `테스트 주도 개발`이라고 표현한다.  
+ JAVA에서는 주로 `JUnit`을 사용한다.<br>
  + _이 책에서는 `JUnit5`버전 사용_

## TDD 이전의 개발
1. 만들 기능에 대해 설계를 고민한다. 
   + 어떤 클래스와 인터페이스를 돌출할지, 각 타입에 어떤 메서드를 넣을지 고민
2. 1을 수행하면서 구현에 대해서도 고민한 후 코드를 작성한다. 
3. 기능에 대한 구현을 완료한 것 같으면 기능 테스트를 한다.
   + 원하는 대로 동작하지 않거나 문제가 발생하면 작성한 코드를 디버깅하며 원인을 찾는다. <br>

## TDD란?
+ TDD는 테스트부터 시작한다.
  + 기능이 올바르게 동작하는지 검증하는 테스트 코드를 작성한다는 것을 의미
+ 기능을 검증하는 테스트 코드를 먼저 작성하고 `테스트를 통과시키기 위해 개발을 진행`한다. 

### 예제) 간단한 덧셈 기능 TDD로 구현해보기
#### [STEP.1] 테스트 코드 작성하기
````java
package chap02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    void plus() {
        int result = Calculator.plus(1, 2);
        assertEquals(3, result);
    }
}
````
* JUnit은 `@Test` 애노테이션을 붙인 plus() 메서드를 테스트 메서드로 인식한다. 
* `assertEquals()`메서드는 인자로 받은 두 값이 동일한지 비교한다. 첫 번째 인자는 기대한 값, 두 번째 인자는 실제 값이다. 
  * 두 결과가 동일하지 않다면 `AssertionFailedError`가 발생한다. 

+ 테스트 코드를 작성하며 고민한 내용
  + 덧셈에 관한 메서드 이름은 plus가 좋을까? sum이 좋을까?
  + 덧셈 기능을 제공하는 메서드는 파라미터가 몇 개여야 할까? 팜라미터 타입은? 반환할 값은?
  + 메서드를 정적 메서드로 구현할까 인스턴스 메서드로 구현할까?
  + 메서드를 제공할 클래스 이름은 뭐가 좋을까?

#### [STEP.2] 테스트 통과 시키기
````java
package chap02;

public class Calculator {
    public static int plus(int a1, int a2){
        return 3;
    }
}
````
1+2 = 3이기 때문에 테스트는 통과한다. 그렇다면 값을 하나 더 추가해보자.

#### [STEP.3] 경우의 수 추가하기
````java
package chap02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    void plus() {
        int result = Calculator.plus(1, 2);
        assertEquals(3, result);
        assertEquals(5, Calculator.plus(4, 1));
    }
}
````
새로 추가한 코드 때문에 테스트를 통과하지 못한다. <br>
오류가 나지 않도록 다시 고쳐준다. 

#### [STEP.4] 테스트 통과시키기
````java
package chap02;

public class Calculator {
    public static int plus(int a1, int a2){
        return a1+a2;
    }
}
````
구현 로직이 단순하지 않을 때는 위 예제와 같이 `점진적으로 구현`을 완성해 나가야 한다. 
---
# 정리
## TDD 흐름
TDD는 `테스트`를 먼저 작성하고 `테스트를 통과`시킬 만큼 코드를 작성하고 개선할 코드가 있으면 `리팩토링`으로 마무리 하는 과정을 반복한다. 

## 테스트가 개발을 주도
테스트 코드를 먼저 작성하면 테스트가 개발을 주도하게 된다. <br>
첫 번째 테스트를 선택할 때는 `가장 쉽거나 가장 예외적인 상황`을 선택해야 한다. 예제에서는
* 모든 규칙을 충족하는 경우
* 모든 조건을 충족하지 않는 경우<br>

의 경우를 잡은 후 `테스트를 쉽게 통과시킬 수 있는 경우`를 먼저 작성했다. 
## 지속적인 코드 정리
* 구현 완료 후 `리팩토링` 진행
* 당장 리팩토링 대상이나 방법이 생각나지 않는다면 우선 다음 테스트 진행
* 개발 과정에서 지속적으로 코드 정리를 하므로 코드 품질이 급격히 나빠지지 않게 막아준다.<br>
* 
## 빠른 피드백
* 코드 수정에 대한 피드백이 빠르다.
  * 잘못된 코드 배포 방지