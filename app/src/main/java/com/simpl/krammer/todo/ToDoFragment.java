package com.simpl.krammer.todo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.simpl.krammer.MainActivity;
import com.simpl.krammer.R;

import java.util.ArrayList;
import java.util.List;

public class ToDoFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ViewToDoRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FloatingActionButton CreateTodo;
    private DatabaseReference databaseReference;

    private List<ToDo> toDoList;


    public ToDoFragment() {

    }

    public static ToDoFragment newInstance(String param1, String param2) {
        ToDoFragment fragment = new ToDoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_to_do, container, false);

        CreateTodo = view.findViewById(R.id.create_todo);
        CreateTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputFragment2 inputFragment = new InputFragment2();
                FragmentManager fragmentManager = getFragmentManager();
                assert fragmentManager != null;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, inputFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        });


        toDoList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Todo");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ToDo tempToDo = dataSnapshot.getValue(ToDo.class);
                    toDoList.add(tempToDo);
                }
                Log.e("getTest", toDoList.get(0).getTask());
                //create recyclerview
                Context context = view.getContext();
                recyclerView = view.findViewById(R.id.fragment_todo_recycler);
                layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);
                mAdapter = new ViewToDoRecyclerViewAdapter(toDoList);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}




