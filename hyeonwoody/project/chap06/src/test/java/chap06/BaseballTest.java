package test.java.chap06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BaseballTest {

    @BeforeEach
    void givenGame(){
        game = new BaseballGame()"456";
    }

    @Test
    void exactMatch(){
        //실행
        Score score = game.guess("456");

        //결과 확인
        assertEquals(3, score.strikes());
        assertEquals(0, score.balls());
    }

    @Test
    void noDataFile_Then_Exception(){
        givenNoFile("badpath.txt");
        File dataFile = new File("badpath.txt");
        assertThrows(IllegalArgumentException.class, ()->MathUtils.sum(dataFile));
    }

    private void givenNoFile(String path){
        File file = new File(path);
        if (file.exists()){
            boolean deleted = file.delete();
            if (!deleted)
                throw new RuntimeException("fail givenNoFile" + path);
        }
    }

}
