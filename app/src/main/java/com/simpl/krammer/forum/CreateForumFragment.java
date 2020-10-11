package com.simpl.krammer.forum;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.ViewFlashcardSetFragment;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CreateForumFragment extends Fragment {

    /** Create variables*/
    private FragmentForumCreateListener listener;
    private TextInputLayout Title_text;
    private TextInputLayout Description_text;
    private MaterialButton Forum_Cancel;
    private MaterialButton Forum_Submit;

    private DatabaseReference databaseReference;

    public interface FragmentForumCreateListener{
        void onInputCreateSent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_forum, container, false);

        Title_text = v.findViewById(R.id.textField_title);
        Description_text = v.findViewById(R.id.textFieldDescription);
        Forum_Cancel = v.findViewById(R.id.cancel_forum);
        Forum_Submit = v.findViewById(R.id.submit_forum);

        //Save data in Firebase on button click
        Forum_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = Title_text.getEditText().getText().toString();
                String description = Description_text.getEditText().getText().toString();
                databaseReference = FirebaseDatabase.getInstance().getReference("Forum");
                Forum forum = new Forum(title, description);
                databaseReference.child(title).setValue(forum).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        ForumFragment forumFragment = new ForumFragment();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.fragment_container, forumFragment);
                        transaction.commit();
                    }
                });

            }
        });

        Forum_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForumFragment forumFragment = new ForumFragment();

                FragmentManager fm = getFragmentManager();

                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, forumFragment);

                transaction.commit();
            }
        });
        return v;
    }
}