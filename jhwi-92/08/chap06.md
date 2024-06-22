# 테스트 가능한 설계

## 테스트가 어려운 코드
- 하드코딩된 값
```java
Path path = Paths.get("D:\\data\\pay\\test.txt");
--수정
private String filePath = "D:\\data\\pay\\test.txt";

public void setFilePath(String filePath) {
    this.filePath = filePath;
}
```
- 의존 대상을 직접 생성
```java
private Dao dao = new Dao();
--수정
생성자를 통해서 의존대상을 주입거나
public PaySync(Dao dao) {
    this.dao = dao;    
}
setter을 이용해 의존대상을 교체하기
이후
private MemoDao memoDao = new MemoDao();
paySync.setDao(memoDao);
```
- 정적메서드 생성(AuthUtil.authenicate)
```java
AuthUtils.authenicate();
--수정
public class AuthService {
    public authenicate() {
        return AuthUtils.authenicate();
    }
}

public class LoginServce {
    private AuthService authService = new AuthService();
    
}


```
- 실행 시점에 따라 달라지는 값
```java
LocalDate now = LocalDate.now();
--수정
public class Times {
    public LocalDate today() {
        return LocalDate.now();
    }
}
...

private Times times = new Times();
...
LocalDate date = times.today();
```



##이 외 테스트가 어려운 코드
- 메서드 중간에 소켓 통신 코드가 포함
- 콘솔에서 입력을 받거나 결과를 콘솔에 출력
- 메서드가 final
- 테스트 대상의 소스를 소유하고 있지 않아 수정이 어려울 경우

