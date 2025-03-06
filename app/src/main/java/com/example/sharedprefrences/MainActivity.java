package com.example.sharedprefrences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * @author Daniel Azar
 * @version 1.0
 * @since 2025-03-04
 */
public class MainActivity extends AppCompatActivity {

    //global variables
    Button btnExit, btnReset, btnCount;
    TextView tvCounter;
    EditText tvName;
    int count = 0;
    String name;
    SharedPreferences scoreData;
    SharedPreferences.Editor editor;

    /**
     * @param savedInstanceState The saved instance state
     * Function creates the activity
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking the views
        btnExit = findViewById(R.id.exitBtn);
        btnReset = findViewById(R.id.resetBtn);
        btnCount = findViewById(R.id.countBtn);
        tvCounter = findViewById(R.id.counterTV);
        tvName = findViewById(R.id.nameTV);

        // Create shared preferences file
        scoreData = getSharedPreferences("SCORE_DATA", MODE_PRIVATE);

        // Create editor
        editor = scoreData.edit();

        // Load data if it exists
        count = scoreData.getInt("count", 0);
        tvCounter.setText(String.valueOf(count));
        name = scoreData.getString("name", "");
        tvName.setText(name);
    }

    /**
     * @param view The button view
     * Fucntion saves the data and exits the activity
     * @return void
     */
    public void exit(View view) {
        // Save data
        editor.putString("name", tvName.getText().toString());
        editor.putInt("count", count);

        // Commit data
        editor.commit();

        // Exit the activity
        finish();
    }

    /**
     * @param view The button view
     * Function resets the counter
     * @return void
     */
    public void reset(View view)
    {
        // Reset the counter
        count = 0;
        tvCounter.setText(String.valueOf(count));
    }

    /**
     * @param view The button view
     * Function increments the counter
     * @return void
     */
    public void inc(View view)
    {
        // Increment the counter
        count++;
        tvCounter.setText(String.valueOf(count));
    }

    /**
     * @param menu The menu which is created
     * @return True if the menu is created, false otherwise
     * Function creates the menu
     */
    public boolean  onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * @param item The item which is selected
     * @return True if the item is selected, false otherwise
     * Function launches the credits activity
     */
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent myIntent = new Intent(this, Credits.class);
        startActivity(myIntent);
        return true;
    }
}