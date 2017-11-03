# Android_Unit_Testing_example
부스트 캠프 유닛 테스트 파트

## local unit test
로컬 JVM(Java Virtual Machine)에서 실행되는 테스트
**안드로이드에 대한 의존성이 없거나 단순한 테스트에 이용(Mockito사용가능)**

### JUnit4 Annotation
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

2. <a href=https://github.com/Leejigun/Android_Unit_Testing_example/blob/master/Android_unit_test_example/app/src/test/java/com/openit/jglee/android_unit_test_example/ExampleUnitTest.java>테스트 코드 작성( ExampleUnitTest 파일에 작성)</a>
  <pre><code>
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
      assertNotSame("str1, str2 not same", str1,str2);
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
  </code></pre>
3. ExamplaUnitTest class file compile

## instrumented test
**안드로이드 디바이스나 에뮬레이터에서 실행되는 테스트**
테스트에서는 Instrumentation API에 액세스가능 테스트 앱의 Context 접근 가능
테스트에 Android 종속성이 있는 경우 사용(Activity, Service등)
APK(앱 APK와는 별개)로 빌드되므로 자체 AndroidManifest.xml 파일이 필요하나
Gradle이 빌드 과정에서 이 파일을 자동으로 생성(직접 추가도 가능)

### Espresso 사용
1. 버튼을 누르면 1씩 더해서 표시하는 간단한 동작 구현 (MainActivity.class)
  <pre><code>
  public class MainActivity extends AppCompatActivity {
  TextView txtView;
  Button btnClick;
  int count = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    txtView = (TextView) findViewById(R.id.hellowTtext);
    btnClick = (Button) findViewById(R.id.btnClick);

    /* RxJava로 구성된 부분
    RxView.clicks(btnClick)
    .map(event -> count++)
    .subscribe(value -> {
    txtView.setText("count:"+value.toString());
    }, throwable -> {
    Log.e(TAG, "Error: " + throwable.getMessage());
    throwable.printStackTrace();
    });
    */

    // 일반 코드로 풀어 쓴 내용
    btnClick.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    count++;
    txtView.setText("count:"+Integer.toString(count));
    }
    });
  }
  }
  </pre></code>
2. <a href=https://github.com/Leejigun/Android_Unit_Testing_example/blob/master/Android_unit_test_example/app/src/androidTest/java/com/openit/jglee/android_unit_test_example/ExampleInstrumentedTest.java>테스트를 진행 할 코드 작성 (ExampleInstrumentedTest.class)</a>
  <pre><code>
  @RunWith(AndroidJUnit4.class)
  public class ExampleInstrumentedTest {
  @Rule
  public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void useAppContext() throws Exception {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();
    assertEquals("com.openit.jglee.android_unit_test_example", appContext.getPackageName());
  }

  @Test
  public void test1() throws Exception {
    Espresso.onView(withId(R.id.btnClick)).perform(click());
    Espresso.onView(withId(R.id.hellowTtext)).check(matches(withText("count:1")));
    Espresso.onView(withId(R.id.btnClick)).perform(click());
    Espresso.onView(withId(R.id.hellowTtext)).check(matches(withText("count:2")));
  }
  }
  </pre></code>
####Espresso:함수 설명
* onView : View 처리(TestView, EditText, Button 등)
* withText : 해당 텍스트 속성으로 가지는 View를 찾음
* withId : R.id.view에 해당하는 View를 찾음
* perform : ViewAction을 실행
* click : 아이템을 클릭하는 이벤트 실행
* check(matches()) : ViewAssertion 유효성을 체크하고, 맞는지 확인
* isDisplayed : 화면에 보이는 상태를 확인
- - -
즉, 위 코드 내용은 버튼을 한번 클릭하고 text 값이 count: 1인 view가 있는지 찾는다.
후에 한번 더 버튼을 클릭하고 text값이 count:2인 view를 자동으로 찾는 테스트로 응용을 통해서 UI의 변화를 자동으로 테스팅 할 수 있다.