package com.example.ass1;

import java.util.ArrayList;
import java.util.List;

public class WorkoutData {

    public static List<Workout> getWorkoutsForDay(String day) {
        List<Workout> workoutsForDay = new ArrayList<>();

        switch (day) {
            case "Monday":
                workoutsForDay.add(new Workout("Push-ups", 15));
                workoutsForDay.add(new Workout("Running", 30));
                workoutsForDay.add(new Workout("Yoga", 45));
                workoutsForDay.add(new Workout("Hiking", 60));

                break;
            case "Tuesday":
                workoutsForDay.add(new Workout("Swimming", 30));
                workoutsForDay.add(new Workout("Cycling", 45));
                workoutsForDay.add(new Workout("Weightlifting", 60));
                workoutsForDay.add(new Workout("Push-ups", 15));
                workoutsForDay.add(new Workout("Boxing", 30));


                break;
            case "Wednesday":
                workoutsForDay.add(new Workout("Pilates", 30));
                workoutsForDay.add(new Workout("Jumping Jacks", 20));
                workoutsForDay.add(new Workout("Squats", 25));


                break;
            case "Thursday":
                workoutsForDay.add(new Workout("Hiking", 60));
                workoutsForDay.add(new Workout("Walking", 30));
                workoutsForDay.add(new Workout("Bodyweight Training", 40));
                workoutsForDay.add(new Workout("Stretching", 20));

                break;
            case "Friday":
                workoutsForDay.add(new Workout("CrossFit", 45));
                workoutsForDay.add(new Workout("Running", 40));
                workoutsForDay.add(new Workout("Stretching", 20));
                workoutsForDay.add(new Workout("Hiking", 60));
                workoutsForDay.add(new Workout("Jump Rope", 20));


                break;
            case "Saturday":
                workoutsForDay.add(new Workout("Boxing", 30));
                workoutsForDay.add(new Workout("Zumba", 45));
                workoutsForDay.add(new Workout("Jump Rope", 20));
                workoutsForDay.add(new Workout("Yoga", 40));

                break;
            case "Sunday":
                workoutsForDay.add(new Workout("Boxing", 30));
                workoutsForDay.add(new Workout("Yoga", 40));
                workoutsForDay.add(new Workout("Stretching", 30));
                workoutsForDay.add(new Workout("Pilates", 35));
                break;
            default:
                break;
        }

        return workoutsForDay;
    }
}
