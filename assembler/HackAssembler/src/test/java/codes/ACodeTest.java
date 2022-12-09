package codes;

import hack.aseembler.codes.ACode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* ACode Tester. 
* 
* @author <Authors name> 
* @since <pre>12月 9, 2022</pre> 
* @version 1.0 
*/ 
public class ACodeTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: translate() 
* 
*/ 
@Test
public void testTranslate() throws Exception { 
//TODO: Test goes here...
    String code="@100";
    String binCode="0000000001100100";
    ACode  aCode = new ACode(code);
    Assert.assertEquals(aCode.translate(),binCode);
} 


} 
