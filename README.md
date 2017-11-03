# Android_Unit_Testing_example
부스트 캠프 유닛 테스트 파트

**JUnit4 Annotation**
* @BeforeClass 테스트 클래스 내에서 수행 전 한 번만 실행, static method 이여야함
* @AfterClass 테스트 클래스 내에서 수행 후 한 번만 실행, static method 여야 함
* @Before 테스트 케이스 수행 전 반복실행
* @After 테스트 케이스 수행 후 반복실행
* @Test 수행할 테스트 메소드 지정

Unit Test 작성하기:local unit test

1. 필수 라이브러리와 설정:build.gradle
 <pre><code>
 dependencies {
	androidTestCompile 'com.android.support:support-annotations:24.0.0'
	androidTestCompile 'com.android.support.test:runner:0.5'
	androidTestCompile 'com.android.support.test:rules:0.5'
	// Optional -- Hamcrest library
	androidTestCompile 'org.hamcrest:hamcrest-library:1.3'
	// Optional -- UI testing with Espresso
	androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
	exclude group: 'com.android.support', module: 'support-annotations'
	})
	// Optional -- UI testing with UI Automator
	androidTestCompile 'com.android.support.test.uiautomator:uiautomator-v18:2.1.2'
}
android {
	defaultConfig {
	testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
	}
}
</code></pre>

2. 테스트 코드 작성( ExampleUnitTest 파일에 작성)

  `public class ExampleUnitTest {`
  `@BeforeClass`
  `	public static void setUpBeforeClass() throws Exception{`
  `	System.out.println("@BeforeClass");`
  `}`
  `@AfterClass`
  `	public static void tearDownAfterClass() throws Exception{`
  `	System.out.println("@AfterClass");`
  `}`
  `@Before`
  `public void before() throws Exception {`
  `System.out.println("@Before");`
  `}`
  `@After`
  `public void after() throws Exception {`
  `System.out.println("@After");`
  `}`
  `@Test`
  `public void testCase1() throws Exception {`
  `System.out.println("testCase1");`
  `String str1 = "java";`
  `String str2 = new String("java");`
  `assertNotSame("str1, str2 not same", str1,`
  `str2);`
  `assertSame(str1, str2.intern());`
  `}`
  `@Test`
  `public void testCase2() throws Exception{`
  `System.out.println("testCase2");`
  `String text = "TEST";`
  `String subString = text.substring(0,2);`
  `assertTrue("TE".equals(subString));`
  `}`
  `@Test`
  `public void testCase3(){`
  `System.out.println("testCase3");`
  `assertNotEquals(1+5, 7);`
  `}`
  `}`


