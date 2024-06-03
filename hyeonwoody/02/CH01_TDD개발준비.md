# Intelli J에서 JUnit 설정
1. `File` -> `Project Structure...` 메뉴를 실행해서 `Project Structure` 대화창을 연다.
2. 좌측에서 Module을 선택한 뒤 우측에서 Dependencies 탭을 선택한다.
3. `+` 버튼을 누른 뒤 `Library...` -> `From Maven...`메뉴를 실행한다.
4. 검색창에 "org.junit.jupiter:junit-jupiter:5.5.0"을 입력하고 `OK`버튼을 누른다.
5. `Configure Library` 대화창에서 `OK`버튼을 눌러 Junit 5 라이브러리를 프로젝트에 추가한다.
6. 마지막으로 Project Structure 대화창에서 `OK`버튼을 눌러 변경 내용 적용한다.

# 그레이들 프로젝트에서 JUnit 설정

```
plugins {
    id 'java'
}

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