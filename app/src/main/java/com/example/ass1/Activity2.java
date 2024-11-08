package com.example.ass1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {

    private Spinner workSpinner;
    private Spinner daySpinner;
    private EditText durationTxt;
    private RadioGroup difficulty;
    private CheckBox cboxSaveWork, cboxCalorie;
    private Button startBtn;
    private TextView caloriesTxt;
    private String selectDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        workSpinner = findViewById(R.id.workSpinner);
        daySpinner = findViewById(R.id.daySpinner);
        durationTxt = findViewById(R.id.durationTxt);
        difficulty = findViewById(R.id.difficulty);
        cboxSaveWork = findViewById(R.id.cboxSaveWork);
        cboxCalorie = findViewById(R.id.cboxCalorie);
        startBtn = findViewById(R.id.startBtn);
        caloriesTxt = findViewById(R.id.caloriesTxt);


        List<String> days = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        setWorkoutSpinner("Monday");
        daySpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectDay = parentView.getItemAtPosition(position).toString();
                setWorkoutSpinner(selectDay);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parentView) {
            }
        });

        cboxSaveWork.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                savePreferences();
                Toast.makeText(Activity2.this, "Preferences saved!", Toast.LENGTH_SHORT).show();
            }
        });

        startBtn.setOnClickListener(view -> {

            String workoutType = workSpinner.getSelectedItem().toString();
            selectDay = daySpinner.getSelectedItem().toString();
            String duration = durationTxt.getText().toString();
            int selectedDifficultyId = difficulty.getCheckedRadioButtonId();
            RadioButton selectedDifficulty = findViewById(selectedDifficultyId);

            if (selectDay == null || duration.isEmpty() || selectedDifficulty == null) {
                Toast.makeText(Activity2.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Activity2.this, Activity3.class);
            intent.putExtra("selectedDay", selectDay);
            intent.putExtra("workoutType", workoutType);
            intent.putExtra("duration", duration);
            intent.putExtra("difficulty", selectedDifficulty.getText().toString());
            startActivity(intent);
        });


        cboxCalorie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                calculateCaloriesBurned();
            } else {
                caloriesTxt.setText("Calories Burned: 0");
            }
        });
    }

    private void setWorkoutSpinner(String day) {
        List<String> workoutTypes = new ArrayList<>();
        List<Workout> workoutsForDay = WorkoutData.getWorkoutsForDay(day);

        for (Workout workout : workoutsForDay) {
            workoutTypes.add(workout.getType());
        }
        ArrayAdapter<String> workoutTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, workoutTypes);
        workoutTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workSpinner.setAdapter(workoutTypeAdapter);
    }

    private void savePreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("workoutPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedDay", selectDay);
        editor.putString("workoutType", workSpinner.getSelectedItem().toString());
        editor.putString("duration", durationTxt.getText().toString());
        editor.putString("difficulty", ((RadioButton) findViewById(difficulty.getCheckedRadioButtonId())).getText().toString());
        editor.apply();
    }

    private void calculateCaloriesBurned() {
        String durationText = durationTxt.getText().toString();
        if (durationText.isEmpty()) {
            caloriesTxt.setText("Calories Burned: 0");
            return;
        }

        int duration = Integer.parseInt(durationText);
        int selectedDifficultyId = difficulty.getCheckedRadioButtonId();
        RadioButton selectedDifficulty = findViewById(selectedDifficultyId);

        double caloriesBurned = 0 ;
        if (selectedDifficulty != null) {
            if(selectedDifficulty.getText().toString().equals("Easy")){
                caloriesBurned = duration * 5;
            } else if (selectedDifficulty.getText().toString().equals("Medium")) {
                caloriesBurned = duration * 8;
            } else if (selectedDifficulty.getText().toString().equals("Hard")) {
                caloriesBurned = duration * 12;
            }
        } else {
            caloriesBurned = 0;
        }
        caloriesTxt.setText("Calories Burned: " + caloriesBurned);
    }
}
