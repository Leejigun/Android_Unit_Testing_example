package com.openit.jglee.android_unit_test_example;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @BeforeClass 테스트 클래스 내에서 수행 전 한 번만 실행, static method 이여야함
 * @AfterClass 테스트 클래스 내에서 수행 후 한 번만 실행, static method 여야 함
 * @Before 테스트 케이스 수행 전 반복실행
 * @After 테스트 케이스 수행 후 반복실행
 * @Test 수행할 테스트 메소드 지정
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        System.out.println("@BeforeClass");
    }
    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        System.out.println("@AfterClass");
    }
    @Before
    public void before() throws Exception {
        System.out.println("@Before");
    }
    @After
    public void after() throws Exception {
        System.out.println("@After");
    }

    @Test
    public void testCase1() throws Exception {
        System.out.println("testCase1");
        String str1 = "java";
        String str2 = new String("java");
        assertNotSame("str1, str2 not same", str1,
                str2);
        assertSame(str1, str2.intern());
    }
    @Test
    public void testCase2() throws Exception{
        System.out.println("testCase2");
        String text = "TEST";
        String subString = text.substring(0,2);
        assertTrue("TE".equals(subString));
    }
    @Test
    public void testCase3(){
        System.out.println("testCase3");
        assertNotEquals(1+5, 7);
    }
}