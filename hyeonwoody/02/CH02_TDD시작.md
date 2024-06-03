TDD는 먼저 테스트를 하고 그 다음에 구현한다.
- 기능이 올바르게 동작하는지 검증하는 테스트 코드를 먼저 작성한다.

# assertEquals() 메서드
기대한 값과 실제 값이 동일한지 비교한다.
- 비교한 결과 두 값이 동일하지 않으면 `AssertionFailedError`가 발생한다.

# 고려 사항
간단한 덧셈 기능을 하는 메서드를 구현하기 위해 고려해야할 사항.
## 메서드 이름
- plus가 좋을까 아니면 sum이 좋을까?
## 파라미터
- 파라미터 개수
- 파라미터 타입
- 반환 값
## 메서드 타입
- 정적 메서드로 구현할까 인스턴스 메서드로 구현할까?
	- 덧셈 기능을 구현하기 위해 새로운 객체를 만들 필요 없다. 지금 당장은 정적 메서드로도 충분하므로 정적 메서드로 구현.
## 클래스 이름
- 메서드를 제공할 클래스 이름은 뭐가 좋을까?

# 테스트에서 메인으로 코드 이동
덧셈 기능을 완벽히 수행하는 소스코드는 src/test/java 폴더에서 src/main/java로 이동 시켜서
배포 대상에 포함시킨다.

# 암호 검사기
## 검사할 규칙
- 길이가 8글자 이상
- 0부터 9 사이의 숫자를 포함
- 대문자 포함
세 규칙을 모두 충족하면 암호는 `강함`이다.
2개의 규칙을 충족하면 암호는 `보통`이다.
1개 이하의 규칙을 충족하면 암호는 `약함`이다.

## 첫번째 테스트 : 모든 규칙을 충족하는 경우
### 모든 규칙을 충족하지 않는 경우
각 규칙을 검사하는 코드를 모두 구현해야 함. 사실상 구현을 다 하고 테스트를 하는 방식.
### 모든 규칙을 충족하는 경우
`강함`에 해당하는 값을 리턴하면 테스트에 통과할 수 있음.
# @DisplayName
메서드 이름을 한글로 작성하지 않아도 테스트 메서드를 원하는 이름으로 쉽게 표시할 수 있다.
5장에서 자세히 설명한다.

## 두번째 테스트 : 길이만 8글자 미만이고 나머지 조건은 충족하는 경우
PasswordStrenth 열거 타입에 NORMAL을 추가한다.
길이가 8보다 작으면 NORMAL을 리턴한다.
```
if (s.length() < 8)
            return PasswordStrength.NORMAL;
```

## 세번째 테스트 : 숫자를 포함하지 않고 나머지 조건은 충족하는 경우
추가로 검사하는 코드를 메서드로 추출해서 가독성을 개선하고 메서드 길이를 줄여본다.
```
public PasswordStrength meter (String s){
        if (s.length() < 8)
            return PasswordStrength.NORMAL;
        boolean containNum = meetsContainingNumberCriteria(s);
        if (!containNum) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(String s){
        for (char ch : s.toCharArray()){
            if (ch >= '0' && ch <= '9')
                return true;
        }
        return false;
    }
```

## 코드 정리 : 테스트 코드 정리
테스트 코드도 코드이기 때문에 유지보수 대상이다. 
테스트 메서드에서 발생하는 중복을 알맞게 제거하거나 의미가 잘 드러나게 코드를 수정할 필요가 있다.
- 각 테스트 코드에서 생성하는 Meter 객체를 필드에서 생성
```
	public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();
```
- 암호 강도 측정 기능을 실행하고 확인하는 코드를 단축
```
PasswordStrength result = meter.meter("ab12!@A");
assertEquals(PasswordStrength.NORMAL, result);

-> 
private void assertStrength(String password, PasswordStrength expStr){
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
}
```

## 네번째 테스트 : 값이 없는 경우
meter() 메서드에 null을 전달하면 NPE(NullPointerException)이 발생한다.
null에 대해서도 알맞게 동작할 수 있도록 테스트해야 한다.
- `IllegalArgumentException` 발생
- 유효하지 않은 암호를 의미하는 PasswordStrength.INVALID를 리턴
```
if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
```

## 다섯번째 테스트 : 대문자를 포함하지 않고 나머지 조건을 충족하는 경우
```
private boolean meetsContainingUppercaseCriteria(String s){
        for (char ch : s.toCharArray()){
            if (Character.isUpperCase(ch))
                return true;
        }
        return false;
    }
```

## 여섯번째 테스트 : 길이가 8글자 이상인 조건만 충족하는 경우
남은 테스트는 한 가지 조건만 충족하거나 모든 조건을 충족하지 않는 경우다.
PasswordStrenth 열거 타입에 WEAK을 추가한다.
먼저 길이가 8이상인지 여부를 뒤에서 확인할 수 있어야 한다.
각 조건을 **확인하는 코드는 메서드 상단**에
강도를 **리턴하는 코드는 메서드 하단**에 위치하도록 한다.
```
public PasswordStrength meter (String s){
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        boolean lengthEnough = s.length() >= 8;
        boolean containNum = meetsContainingNumberCriteria(s);
        boolean containUppercase = meetsContainingUppercaseCriteria(s);



        if (!lengthEnough) return PasswordStrength.NORMAL;
        if (!containNum) return PasswordStrength.NORMAL;
        if (!containUppercase) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }
```

### if 절의 위치 이동한 이유
다음 두 로직을 구분해서 모으기 위함.
- 개별 규칙을 검사하는 로직
- 규칙을 검사한 결과에 따라 암호 강도를 계산하는 로직

### 최종 소스코드
```
public PasswordStrength meter (String s){
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        boolean lengthEnough = s.length() >= 8;
        boolean containNum = meetsContainingNumberCriteria(s);
        boolean containUppercase = meetsContainingUppercaseCriteria(s);

        **if (lengthEnough && !containNum && !containUppercase)
            return PasswordStrength.WEAK;**

        if (!lengthEnough) return PasswordStrength.NORMAL;
        if (!containNum) return PasswordStrength.NORMAL;
        if (!containUppercase) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }
```

## 일곱번째 테스트 : 숫자 포함 조건만 충족하는 경우
이전에 추가한 소스코드를 복붙하고 느낌표 위치만 바꿔준다.
```
if (lengthEnough && !containNum && !containUppercase)
            return PasswordStrength.WEAK;
->
if (!lengthEnough && containNum && !containUppercase)
            return PasswordStrength.WEAK;

```

## 여덞번째 테스트 : 대문자 포함 조건만 충족하는 경우
이전에 추가한 소스코드를 복붙하고 느낌표 위치만 바꿔준다.
```
if (!lengthEnough && containNum && !containUppercase)
            return PasswordStrength.WEAK;
->
if (!lengthEnough && !containNum && containUppercase)
            return PasswordStrength.WEAK;
```

## 코드 정리 meter() 메서드 리펙토링
충족하는 조건 개수를 카운트해서 세개의 변수를 이용하는 if 절을 지울 수 있다.
```
public PasswordStrength meter (String s){
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        int metCnt = 0;
        if (s.length() >= 8) metCnt++;
        if (meetsContainingNumberCriteria(s)) metCnt++;
        if (meetsContainingUppercaseCriteria(s)) metCnt++;


        if (metCnt == 1) return PasswordStrength.WEAK;
        if (metCnt == 2) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }
```

## 아홉번째 테스트 : 아무 조건도 충족하지 않은 경우
테스트를 통과시키려면 다음 중 한 가지 방법을 사용한다.
- 충족 개수가 1개 이하인 경우 WEAK을 리턴
- 충족 개수가 0인 경우 WEAK을 리턴
- 충족 개수가 3인 경우 STRONG을 리턴하고 마지막에 WEAK을 리턴
마지막 방법을 코드로 구현해보자면 다음과 같다.
```
if (metCnt == 1) return PasswordStrength.WEAK;
        if (metCnt == 2) return PasswordStrength.NORMAL;
        if (metCnt == 3) return PasswordStrength.STRONG;
        return PasswordStrength.WEAK;
```

## 코드 정리 : 코드 가독성 개선
metCnt 계산 부분을 메서드로 빼면 가독성을 높일 수 있다.
```
public PasswordStrength meter (String s){
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int metCnt = getMetCriteriaCounts(s);

        if (metCnt == 1) return PasswordStrength.WEAK;
        if (metCnt == 2) return PasswordStrength.NORMAL;
        if (metCnt == 3) return PasswordStrength.STRONG;

        return PasswordStrength.WEAK;
    }

    private int getMetCriteriaCounts(String s){
        int metCnt = 0;
        if (s.length() >= 8) metCnt++;
        if (meetsContainingNumberCriteria(s)) metCnt++;
        if (meetsContainingUppercaseCriteria(s)) metCnt++;
        return metCnt;
    }
```

## TDD 흐름
**테스트 -> 코딩 -> 리팩토링**
- 기능을 검증하는 테스트 먼저 작성
- 테스트 통과한 뒤에는 개선할 코드를 리팩토링
- 리팩토링 후 기능이 망가지지 않았는지 확인
- 반복
- 레드-그린-리팩터
	- 레드 : 실패
	- 그린 : 성공
	- 리팩터 : 리팩토링 과정

## 지속적인 코드 정리
당장 리팩토링 하지 않더라도 테스트 코드가 있으면 리팩토링을 보다 과감하게 진행할 수 있다.
- 온전한 검증 테스트가 있다면 수정에 대한 심리적 불안감을 줄여준다.
- 코드 품질이 나빠지지 않게 막아주어 유지보수 비용을 낮춘다.

## TDD 이점
- 빠른 피드백
	- 기존 코드 수정 시 코드가 올바른지 바로 확인 가능
	- 잘못된 코드가 배포되는 것을 방지