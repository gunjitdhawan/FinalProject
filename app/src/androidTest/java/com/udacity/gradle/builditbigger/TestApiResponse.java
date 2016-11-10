package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by gunjit on 08/11/16.
 */

@RunWith(AndroidJUnit4.class)
public class TestApiResponse {

    private static final String TAG = TestApiResponse.class.getSimpleName();

    @Rule
    public ActivityTestRule<MainTestActivity> mActivityRule = new ActivityTestRule<>(MainTestActivity.class);

    @Test
    public void testLoginViaService() throws Exception {

        final MainTestActivity loginActivity = mActivityRule.getActivity();
        loginActivity.setResponseCallback(new MainTestActivity.MainTestCallback() {
            @Override
            public void onReceivedResponse(String response) {

                Assert.assertNotEquals("", response);
                Assert.assertNotNull(response);

            }
        });

        onView(withId(R.id.btn)).perform(click());
    }
}