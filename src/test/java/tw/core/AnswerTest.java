package tw.core;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    private Answer answer;

    @Before
    public void setup() {
        answer = new Answer();
    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void testCreateAnswerWhen1234Return1234(){
        Answer result = Answer.createAnswer("1 2 3 4");
        assertEquals("1 2 3 4", result.toString());
    }


    @Test
    public void testValidateWhen123and10ThrowException() throws OutOfRangeAnswerException {
        answer = Answer.createAnswer("1 2 3 10");
        thrown.expect(OutOfRangeAnswerException.class);
        thrown.expectMessage("Answer format is incorrect");
        answer.validate();
    }

    @Test
    public void testCheckWhenAnswer1234andInputAnswer1234Return4A0B(){
        Record record = new Record();
        for (int i = 0; i < 4; i++) {
            record.increaseCurrentNum();
        }
        answer = Answer.createAnswer("1 2 3 4");
        Answer inputAnswer = Answer.createAnswer("1 2 3 4");
        Record result = answer.check(inputAnswer);

        assertThat(result.getValue(), is(record.getValue()));
    }

    @Test
    public void testCheckWhenAnswer1234andInputAnswer1235Return3A0B(){
        Record record = new Record();
        for (int i = 0; i < 3; i++) {
            record.increaseCurrentNum();
        }
        answer = Answer.createAnswer("1 2 3 4");
        Answer inputAnswer = Answer.createAnswer("1 2 3 5");
        Record result = answer.check(inputAnswer);

        assertThat(result.getValue(), is(record.getValue()));
    }

    @Test
    public void testCheckWhenAnswer1234andInputAnswer4321Return0A4B(){
        Record record = new Record();
        for (int i = 0; i < 4; i++) {
            record.increaseIncludeOnlyNum();
        }
        answer = Answer.createAnswer("1 2 3 4");
        Answer inputAnswer = Answer.createAnswer("4 3 2 1");
        Record result = answer.check(inputAnswer);

        assertThat(result.getValue(), is(record.getValue()));
    }




}