package tw.core.generator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    private AnswerGenerator answerGenerator;
    private RandomIntGenerator randomIntGenerator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        randomIntGenerator = mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }

    @Test
    public void testGenerateWhen1232ThrowsException() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3 2");
        thrown.expectMessage("Answer format is incorrect");
        answerGenerator.generate();
    }

    @Test
    public void testGenerateWhen1234Return1234() throws OutOfRangeAnswerException{
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3 4");
        String result = answerGenerator.generate().toString();
        assertEquals("1 2 3 4", result);
    }

}

