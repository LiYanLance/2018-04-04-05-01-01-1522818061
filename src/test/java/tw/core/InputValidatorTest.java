package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {

    private InputValidator inputValidator;

    @Before
    public void setup() {
        inputValidator = new InputValidator();
    }

    @Test
    public void testWhen1234ReturnTrue(){
        boolean res = inputValidator.validate("1 2 3 4");
        assertTrue(res);
    }

    @Test
    public void testValidateWhen1224ReturnFalse(){
        boolean res = inputValidator.validate("1 2 2 4");
        assertFalse(res);
    }

    @Test
    public void testValidateWhen123ReturnFalse(){
        boolean res = inputValidator.validate("1 2 3");
        assertFalse(res);
    }

    @Test
    public void testValidateWhen12310ReturnFalse(){
        boolean res = inputValidator.validate("1 2 3 10");
        assertFalse(res);
    }

    @Test
    public void testValidateWhen12345ThenFalse(){
        boolean res = inputValidator.validate("1 2 3 4 5");
        assertFalse(res);
    }

}
