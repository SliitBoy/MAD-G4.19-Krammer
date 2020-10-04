package com.simpl.krammer.forum;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simpl.krammer.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CreateForumFragment extends Fragment {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    /** Create variables*/
    private FragmentForumCreateListener listener;
    private TextInputEditText Title_text;
    private TextInputEditText Description_text;
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
                CharSequence input = Title_text.getText();
                listener.onInputCreateSent(input);
                databaseReference = FirebaseDatabase.getInstance().getReference();
            }
        });
        return v;
    }
}