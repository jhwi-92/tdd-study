# Junit5의 주요 메서드

### assertEquals(expected, actual)
- 실제 값(actual)이 기대하는 값(expected)과 같은지 검사


### assertNotEquals(unexpected, actual)
- 실제 값(actual)이 특정 값(unexpected)과 같지 않은지 검사

### assertSame(Object expected, Object actual)
- 두 객체가 동일한 객체인지 검사한다.

### assertNotSame(Object unexpected, Object actual)
- 두 객체가 동일하지 않은 객체인지 검사한다.

### assertTrue(boolean condition)
- 값이 true인지 검사한다.

### assertFalse(boolean condition)
- 값이 false인지 검사한다.

### assertNull(Object actual)
- 값이 null인지 검사한다.

### assertNotNull(Object actual)
- 값이 null이 아닌지 검사한다.

### assertThrows(Class<T> expectedType, Executable executable)
- executable을 실행한 결과로 지정한 타입의 익셉션이 발생하는지 검사한다.

### assertDoesNotThrow(Executable executable)
- executable을 실행한 결과로 익셉션이 발생하지 않는지 검사한다.

### fail()
- 테스트를 실패 처리한다.


## 주의!!
- 첫번째 인자가 기대값이고 두번째 인자가 검사하려는 값이다.