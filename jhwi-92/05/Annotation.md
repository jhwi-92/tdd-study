# Annotation

### @Test
- 테스트 메서드
- private이면 안된다.


### @BeforeAll
- 정적메서드에 사용
- 클래스의 모든 테스트 메서드를 실행하기 전에 한 번 실행한다.

### AfterAll
- 정적메서드에 사용
- 클래스의 모든 테스트 메서드를 실행 후 한 번 실행

### DisplayName
- 메서드의 이름 작성

### Disabled
- 특정 메서드를 실행 대상에서 제외한다.


# 테스트 메서드 간 실행 순서 의존과 필드 공유XXX
- 테스트 메서드의 실행순서를 지정할 수는 있지만 각 테스트 메서드는 독립적으로 동작해야 한다.
- 테스트 메서드 간에 의존이 생기면 유지보수가 어렵다.