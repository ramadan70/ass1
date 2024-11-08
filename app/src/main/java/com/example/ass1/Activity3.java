package com.example.ass1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Activity3 extends AppCompatActivity {

    private TextView totalWorkoutsTxt, totalTimeTxt, selectDayTxt;
    private ListView ListView;
    private Button clearBtn, backBtn;
    private List<Workout> workoutHst;
    private int totalWorkouts = 0;
    private int totalTime = 0;
    private String selectDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        totalWorkoutsTxt = findViewById(R.id.totalWorkoutsTxt);
        totalTimeTxt = findViewById(R.id.totalTimeTxt);
        selectDayTxt = findViewById(R.id.selectDayTxt);
        ListView = findViewById(R.id.ListView);
        clearBtn = findViewById(R.id.clearBtn);
        backBtn = findViewById(R.id.backBtn);

        Intent intent = getIntent();
        selectDay = intent.getStringExtra("selectedDay");

        selectDayTxt.setText("Selected Day: " + selectDay);

        workoutHst = WorkoutData.getWorkoutsForDay(selectDay);

        List<String> workoutDescriptions = new ArrayList<>();
        for (Workout workout : workoutHst) {
            workoutDescriptions.add(workout.getDescription());
            totalTime += workout.getDuration();
        }
        totalWorkouts = workoutHst.size();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workoutDescriptions);
        ListView.setAdapter(adapter);

        updateProgress();

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutHst.clear();
                totalWorkouts = 0;
                totalTime = 0;
                updateProgress();
                adapter.notifyDataSetChanged();
                Toast.makeText(Activity3.this, "Progress cleared!", Toast.LENGTH_SHORT).show();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, Activity2.class);
                startActivity(intent);
            }
        });
    }


    private void updateProgress() {
        totalWorkoutsTxt.setText("Total Workouts Completed: " + totalWorkouts);
        totalTimeTxt.setText("Total Workout Time: " + totalTime + " mins");
    }
}
