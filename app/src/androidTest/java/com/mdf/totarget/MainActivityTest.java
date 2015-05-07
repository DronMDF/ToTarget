package com.mdf.totarget;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.text.TextUtils;
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

    private void setTaskText(final TextView task_text, String text) {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                task_text.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync(text);
        getInstrumentation().waitForIdleSync();
    }

    @MediumTest
    public void testUserShouldWriteTaskToEnableIt() {
        // Given
        LinearLayout tasks = (LinearLayout) activity.findViewById(R.id.tasks);
        View task = tasks.getChildAt(0);
        // When
        final TextView text = (TextView) task.findViewById(R.id.name);
        setTaskText(text, "Test");
        // Then
        View done = task.findViewById(R.id.done);
        assertTrue(done.isEnabled());
    }

    @MediumTest
    public void testUserCanClickDoneToDisableIt() {
        // Given
        LinearLayout tasks = (LinearLayout) activity.findViewById(R.id.tasks);
        View task = tasks.getChildAt(1);

        final TextView task_text = (TextView) task.findViewById(R.id.name);
        setTaskText(task_text, "Test");

        View task_button = tasks.findViewById(R.id.done);
        // When
        task_button.performClick();
        getInstrumentation().waitForIdleSync();
        // Then
        assertFalse(task_button.isEnabled());
        assertTrue(TextUtils.isEmpty(task_text.getText().toString()));
    }

}