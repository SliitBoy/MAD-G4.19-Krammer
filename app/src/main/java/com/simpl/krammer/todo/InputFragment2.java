package com.simpl.krammer.todo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simpl.krammer.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InputFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputFragment2 extends Fragment {

    private TextInputLayout edit_task,edit_priority;

    private Button btn_add;

    private DatabaseReference databaseReference;

    private ToDo toDo;


    public InputFragment2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InputFragment2 newInstance(String param1, String param2) {
        InputFragment2 fragment = new InputFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input2, container, false);

        edit_task = view.findViewById(R.id.task_input_layout);
        edit_priority = view.findViewById(R.id.task_number_input_layout);


        btn_add = view.findViewById(R.id.add_todo_button);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = edit_task.getEditText().getText().toString();
                String priority = edit_priority.getEditText().getText().toString();

                toDo = new ToDo(task, priority);

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Todo");

                databaseReference.push().setValue(toDo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(getActivity(), "Task is add", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



        return view;
    }
}