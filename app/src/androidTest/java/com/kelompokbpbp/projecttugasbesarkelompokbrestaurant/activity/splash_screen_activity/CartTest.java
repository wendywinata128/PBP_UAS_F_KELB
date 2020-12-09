package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.splash_screen_activity;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CartTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.CAMERA");

    public static ViewAction waitFor(long delay) {
        return new ViewAction() {
            @Override public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override public String getDescription() {
                return "wait for " + delay + "milliseconds";
            }

            @Override public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        };
    }

    @Test
    public void cartTest() {

        onView(isRoot()).perform(waitFor(5000));

        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.cbCamera), withText("Yes , you can access my camera"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                6)));
        materialCheckBox.perform(scrollTo(), click());

        ViewInteraction materialCheckBox2 = onView(
                allOf(withId(R.id.cbLocation), withText("Yes , you can access my location"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                4)));
        materialCheckBox2.perform(scrollTo(), click());

        ViewInteraction materialCheckBox3 = onView(
                allOf(withId(R.id.cbNotification), withText("Yes , please send me a notification"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                2)));
        materialCheckBox3.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnContinue), withText("Continue"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout2),
                                        7),
                                0)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btnMoveLogin), withText("I already have account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                5)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction textInputEditText = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.textInputUsername),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.textInputUsername),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("wennywen"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.textInputPassword),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("cobacoba"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnLogin), withText("LOGIN"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout4),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                4)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_cart), withContentDescription("Cart"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_food), withContentDescription("Menu"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigation),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.orderbtn), withText("Order"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.gridFoodAdapter),
                                        0),
                                3),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.navigation_cart), withContentDescription("Cart"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btn_checkOut), withText("CheckOut"),
                        childAtPosition(
                                allOf(withId(R.id.layout_price),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.menuAdd),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btn_checkOut), withText("CheckOut"),
                        childAtPosition(
                                allOf(withId(R.id.layout_price),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.menuMinus),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.btn_checkOut), withText("CheckOut"),
                        childAtPosition(
                                allOf(withId(R.id.layout_price),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction bottomNavigationItemView4 = onView(
                allOf(withId(R.id.navigation_food), withContentDescription("Menu"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigation),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView4.perform(click());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.orderbtn), withText("Order"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.gridFoodAdapter),
                                        0),
                                3),
                        isDisplayed()));
        materialButton10.perform(click());

        ViewInteraction bottomNavigationItemView5 = onView(
                allOf(withId(R.id.navigation_cart), withContentDescription("Cart"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView5.perform(click());

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.btn_checkOut), withText("CheckOut"),
                        childAtPosition(
                                allOf(withId(R.id.layout_price),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton11.perform(click());

        ViewInteraction bottomNavigationItemView6 = onView(
                allOf(withId(R.id.navigation_cart), withContentDescription("Cart"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView6.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.deleteProductButton),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction materialButton12 = onView(
                allOf(withId(R.id.btn_checkOut), withText("CheckOut"),
                        childAtPosition(
                                allOf(withId(R.id.layout_price),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton12.perform(click());

        ViewInteraction materialButton13 = onView(
                allOf(withId(R.id.btnBayar), withText("Bayar"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                0)));
        materialButton13.perform(scrollTo(), click());
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
