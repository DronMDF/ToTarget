package com.mdf.totarget;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import static android.test.TouchUtils.*;
import static android.test.ViewAsserts.*;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity activity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
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
    public void testTaskOnScreen() {
        final View view = activity.getWindow().getDecorView();
        for (int i = 0; i < 3; i++) {
            LinearLayout tasks = (LinearLayout) activity.findViewById(R.id.tasks);
            View task = tasks.getChildAt(i);
            assertOnScreen(view, task.findViewById(R.id.name));
            assertOnScreen(view, task.findViewById(R.id.spinner));
            assertOnScreen(view, task.findViewById(R.id.done));
        }
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
        final Spinner task_spinner = (Spinner) task.findViewById(R.id.spinner);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                task_spinner.setSelection(5);
            }
        });
        getInstrumentation().waitForIdleSync();

        View task_button = tasks.findViewById(R.id.done);
        // When
        clickView(this, task_button);
        getInstrumentation().waitForIdleSync();
        // Then
        assertFalse(task_button.isEnabled());
        assertTrue(TextUtils.isEmpty(task_text.getText().toString()));
    }

}