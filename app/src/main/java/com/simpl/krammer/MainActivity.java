package com.simpl.krammer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.simpl.krammer.flashcards.FlashcardsHomeFragment;
import com.simpl.krammer.forum.ForumFragment;
import com.simpl.krammer.quiz.QuizFragment;
import com.simpl.krammer.quiz.QuizSearchFragment;
import com.simpl.krammer.quiz.StartQuizFragment;
import com.simpl.krammer.todo.ToDoFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottom_nav);
        //Set Bottom Navigation Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FlashcardsHomeFragment()).commit();
    }

    //Bottom Navigation Item Selected Listener
    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment =  null;

                    //check witch bottomNav item was clicked
                    switch (item.getItemId()) {
                        case R.id.bottom_nav_flashcards:
                            selectedFragment = new FlashcardsHomeFragment();
                            break;
                        case R.id.bottom_nav_Forum:
                            selectedFragment = new ForumFragment();
                            break;
                        case R.id.bottom_nav_quiz:
                            //selectedFragment = new QuizFragment();
                            selectedFragment = new QuizFragment();
                            break;
//                        case R.id.bottom_nav_quiz:
//                            //selectedFragment = new QuizFragment();
//                            selectedFragment = new StartQuizFragment();
//                            break;
                        case R.id.bottom_nav_toDo_List:
                            selectedFragment = new ToDoFragment();
                            break;
                    }
                    //Replace Fragment
                    assert selectedFragment != null;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}