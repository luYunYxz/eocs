package codes;

import hack.aseembler.codes.CCode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * CCode Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>12月 9, 2022</pre>
 */
public class CCodeTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: translate()
     */
    @Test
    public void testTranslate() throws Exception {
//TODO: Test goes here...
        CCode code = new CCode("D;JMP");
        String translate = code.translate();
        //todo 后面添加一些测试用例
        Assert.assertEquals(translate, "1110001100000111");
    }

    @Test
    public void testTranslate2() throws Exception {
//TODO: Test goes here...
        CCode code = new CCode("D;JLE");
        String translate = code.translate();
        //todo 后面添加一些测试用例
        Assert.assertEquals(translate, "1110001100000110");
    }

} 
