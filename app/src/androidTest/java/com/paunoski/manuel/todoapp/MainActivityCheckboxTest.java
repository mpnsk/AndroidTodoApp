package com.paunoski.manuel.todoapp;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.paunoski.manuel.todoapp.db.AppDatabase;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityCheckboxTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    private AppDatabase mDb;
    private Context context;

    @Before
    public void createDb() {
        context = InstrumentationRegistry.getTargetContext();
        mDb = AppDatabase.switchToInMemory(context);
        mActivityTestRule.launchActivity(null);
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void add_item_via_submit() {
        String itemName = "new  todo";
        onView(withId(R.id.editTextInput)).perform(typeText(itemName), closeSoftKeyboard());
        onView(withId(R.id.buttonSubmit)).perform(click());
        onView(withId(R.id.checkBoxDone)).check(matches(isNotChecked()));

    }

    @Test
    public void add_item_via_submit_check_its_box() {
        String itemName = "new item";
        onView(withId(R.id.editTextInput)).perform(typeText(itemName), closeSoftKeyboard());
        onView(withId(R.id.buttonSubmit)).perform(click());
        String textFalse = "Todo(id=1, text=" + itemName + ", done=false)";

        ViewInteraction onRecyclerView = onView(withId(R.id.recylerViewTodoList));
//        onRecyclerView.perform(RecyclerViewActions.actionOnItem(
//                allOf(
//                        withId(R.id.checkBoxDone),
//                        hasSibling(withText(textFalse))
//                ), click()
//                ));
        onView(allOf(withId(R.id.checkBoxDone), hasSibling(withText(textFalse)))).check(matches(isNotChecked()));
        onView(allOf(withId(R.id.checkBoxDone), hasSibling(withText(textFalse)))).perform(click());
        onView(allOf(withId(R.id.checkBoxDone), hasSibling(withText(textFalse)))).check(matches(isChecked()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
