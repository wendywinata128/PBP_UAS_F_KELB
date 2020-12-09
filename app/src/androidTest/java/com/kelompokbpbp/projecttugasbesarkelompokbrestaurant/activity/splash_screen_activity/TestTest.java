package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.splash_screen_activity;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestTest {

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
    public void testTest() {

        //Menunggu selama 5 detik untuk aplikasi melakukan splash screen
        onView(isRoot()).perform(waitFor(5000));


        //Swipe Left pada menu first activity , lalu centang izin camera , location lalu klik continue
        onView(isRoot()).perform(swipeLeft());
        onView(withId(R.id.cbCamera)).perform(click());
        onView(withId(R.id.cbLocation)).perform(click());
        onView(withId(R.id.btnContinue)).perform(click());
        onView(withId(R.id.btnMoveLogin)).perform(click());

        //Isi Username dan password dengan benar , lalu klik login , dan tunggu 3 detik untuk aplikasi melakukan process login (asinkron)
        onView(withId(R.id.ti_username)).perform(replaceText("wen12"),closeSoftKeyboard());
        onView(withId(R.id.ti_password)).perform(replaceText("kukububu"),closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(isRoot()).perform(waitFor(5000));
        onView(isRoot()).perform(closeSoftKeyboard());

        //Tambahkan 3 menu ke dalam keranjang dan tunggu 3 detik untuk aplikasi melakukan processnya
        onView(withId(R.id.recycler_view_menu)).perform(RecyclerViewActions.actionOnItemAtPosition(0,clickChildViewWithId(R.id.orderbtn)));
        onView(withId(R.id.recycler_view_menu)).perform(RecyclerViewActions.actionOnItemAtPosition(2,clickChildViewWithId(R.id.orderbtn)));
        onView(withId(R.id.recycler_view_menu)).perform(RecyclerViewActions.actionOnItemAtPosition(3,clickChildViewWithId(R.id.orderbtn)));
        onView(isRoot()).perform(waitFor(3000));

        //Pilih Bottom Navigation Cart dan tunggu 3 detik , untuk aplikasi melakukan process loading cart
        onView(withId(R.id.navigation_cart)).perform(click());
        onView(isRoot()).perform(waitFor(3000));

        //Di dalam cart hapus menu ketiga yang telah ditambahkan tadi dan tunggu process delete menu di cart selesai
        onView(withId(R.id.recycler_cart)).perform(RecyclerViewActions.actionOnItemAtPosition(2,clickChildViewWithId(R.id.deleteProductButton)));
        onView(isRoot()).perform(waitFor(3000));

        //Pilih Checkout , dan tunggu 3 detik , untuk aplikasi melakukan process loading transaksi
        onView(withId(R.id.btn_checkOut)).perform(click());
        onView(isRoot()).perform(waitFor(3000));

        //Check apakah total yang keluar sesuai dengan yang di inginkan , jika sesuai klik bayar dan tunggu processnya selesai
        onView(withId(R.id.tvTotal)).check(matches(withText("Total : Rp. 32.000,00")));
        onView(withId(R.id.btnBayar)).perform(click());
        onView(isRoot()).perform(waitFor(2000));

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

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}
