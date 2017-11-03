package com.openit.jglee.android_unit_test_example;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * onView : View 처리(TestView, EditText, Button 등)
 * withText : 해당 텍스트 속성으로 가지는 View를 찾음
 * withId : R.id.view에 해당하는 View를 찾음
 * perform : ViewAction을 실행
 * click : 아이템을 클릭하는 이벤트 실행
 * check(matches()) : ViewAssertion 유효성을 체크하고, 맞는지 확인
 * isDisplayed : 화면에 보이는 상태를 확인
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
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
