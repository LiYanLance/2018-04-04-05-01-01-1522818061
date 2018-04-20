package tw.core;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.generator.RandomIntGenerator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    private RandomIntGenerator randomIntGenerator;

    @Before
    public void setUp() throws Exception {
        randomIntGenerator = new RandomIntGenerator();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGenerateNumsWhen3and4ThrowException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Can't ask for more numbers than are available");
        randomIntGenerator.generateNums(3, 4);
    }

    @Test
    public void testGenerateNumsWhen10and4Return4LengthString() {
        String result = randomIntGenerator.generateNums(9, 4);
        assertEquals(4, result.replaceAll("\\s", "").length());
    }



}