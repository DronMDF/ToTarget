package com.mdf.totarget;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class MainActivity extends Activity {

    Integer[] task_values = {0, 1, 2, 3, 5, 8, 13, 20, 40, 100};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout tasks = (LinearLayout) findViewById(R.id.tasks);
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < 3; i++) {
            View item = inflater.inflate(R.layout.task_view, tasks, false);
            final EditText task_text = (EditText) item.findViewById(R.id.name);
            final Spinner task_spinner = (Spinner) item.findViewById(R.id.spinner);
            final ImageButton task_button = (ImageButton) item.findViewById(R.id.done);

            task_text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    if (task_spinner.getSelectedItemId() != 0) {
                        task_button.setEnabled(true);
                    }
                }
            });

            ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                    android.R.layout.simple_spinner_dropdown_item, task_values);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            task_spinner.setAdapter(adapter);
            task_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!TextUtils.isEmpty(task_text.getText().toString())) {
                        task_button.setEnabled(true);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                   task_button.setEnabled(false);
                }
            });
            task_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Засчитать баллы
                    task_text.clearFocus();
                    task_spinner.setSelection(0);
                    task_button.setEnabled(false);
                }
            });

            tasks.addView(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
