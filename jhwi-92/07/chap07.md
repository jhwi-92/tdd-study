# 대역

## 대역의 필요성
- 테스트 대상에서 파일 시스템을 사용
- 테스트 대상에서 DB로부터 데이터를 조회하거나 데이터를 추가
- 테스트 대상에서 외부의 HTTP 서버와 통신

## 대역을 사용해 외부 상황 흉내와 결과를 검증한다.

## 대역의 종류
### Stub
- 구현을 단순한 것으로 대체
### Fake
- 실제 동작하는 구현을 제공
- ex) DB대신 메모리를 이용한 대역
### Spy
- 호출된 내역을 기록한다 - valify?
- stub이기도 하다
### Mock
- 기대한 대로 상호작용 하는지 검증.
- 모의 객체는 stub이자 spy

### Stub의 경우 인터페이스를 만들어서 이 인터페이스를 상속하여 스텁 추가.

## 대역의 필요성
### 대역을 이용하지 않고 실제 구현을 사용한다면?
- 실제의 값을 받기 위해 테스트를 기다려야한다.
- ex) 정보 제공 API를 사용할 경우 API가 테스트에 알맞는 데이터를 줄 때까지 기다려야함.