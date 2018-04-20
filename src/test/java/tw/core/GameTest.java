package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    private Game game;
    private AnswerGenerator answerGenerator;

    @Before
    public void setup() throws OutOfRangeAnswerException {
        answerGenerator = mock(AnswerGenerator.class);
        Answer answer = Answer.createAnswer("1 2 3 4");
        when(answerGenerator.generate()).thenReturn(answer);

        game = new Game(answerGenerator);
    }

    @Test
    public void testGuessWhenAnswer1234andInput1234Return4A0B() throws OutOfRangeAnswerException {
        game = new Game(answerGenerator);

        Answer inputAnswer = Answer.createAnswer("1 2 3 4");
        assertEquals("4A0B", game.guess(inputAnswer).getResult());
    }

    @Test
    public void testGuessHistoryWhen1234And5678Return11234and5678() {

        Answer inputAnswer1 = Answer.createAnswer("5 6 7 8");
        Answer inputAnswer2 = Answer.createAnswer("1 2 3 4");
        game.guess(inputAnswer1);
        game.guess(inputAnswer2);

        List<GuessResult> guessResults = new ArrayList<>();
        guessResults.add(new GuessResult("0A0B", inputAnswer1));
        guessResults.add(new GuessResult("4A0B", inputAnswer2));

        for (int i = 0; i < 2; i++) {
            assertEquals(guessResults.get(i).getResult(), game.guessHistory().get(i).getResult());
        }
    }

    @Test
    public void testCheckStatusWhenGuessCorrectReturnSUCCESS() {
        game.guess(Answer.createAnswer("1 2 3 4"));
        assertEquals(game.checkStatus(), GameStatus.SUCCESS);
    }

    @Test
    public void testCheckStatusWhenGuessWrongLessThan6ReturnCONTINUE() {
        for (int i = 0; i < 5; i++) {
            game.guess(Answer.createAnswer("1 2 3 5"));
            assertEquals(game.checkStatus(), GameStatus.CONTINUE);
        }
    }

    @Test
    public void testCheckStatusWhenGuessWrong6TimesReturnFAIL() {
        for (int i = 0; i < 6; i++) {
            game.guess(Answer.createAnswer("1 2 3 5"));
        }
        assertEquals(game.checkStatus(), GameStatus.FAIL);
    }
}
