package com.mdf.totarget;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {
    private MainActivity activity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        startActivity(intent, null, null);
        activity = (MainActivity) getActivity();
    }

    @MediumTest
    public void testUserShouldWriteTaskToEnableIt() {
        // Given
        LinearLayout tasks = (LinearLayout) activity.findViewById(R.id.tasks);
        View task = tasks.getChildAt(0);
        // When
        final TextView text = (TextView) task.findViewById(R.id.name);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText("Test");
            }
        });
        // Then
        View done = task.findViewById(R.id.done);
        assertTrue(done.isEnabled());
    }

    @MediumTest
    public void testUserCanClickDoneToDisableIt() {
        // Given
        LinearLayout tasks = (LinearLayout) activity.findViewById(R.id.tasks);
        View task = tasks.getChildAt(1);
        final TextView text = (TextView) task.findViewById(R.id.name);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText("Test");
            }
        });
        View done = tasks.findViewById(R.id.done);
        // When
        done.performClick();
        // Then
        assertFalse(done.isEnabled());
    }

}