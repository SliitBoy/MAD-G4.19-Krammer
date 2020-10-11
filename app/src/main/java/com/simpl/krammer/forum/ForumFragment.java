package com.simpl.krammer.forum;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simpl.krammer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForumFragment extends Fragment {

    private FloatingActionButton ForumCreate;
    private DatabaseReference databaseReference;

    // RecyclerView Objects
    private View view;
    private RecyclerView recyclerView;
    private ViewForumRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Forum> forumList;

    public ForumFragment() {
        // Required empty public constructor
    }


    public static ForumFragment newInstance(String param1, String param2) {
        ForumFragment fragment = new ForumFragment();
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
        view = inflater.inflate(R.layout.fragment_forum, container, false);

        ForumCreate = view.findViewById(R.id.CreateForumButton);
        ForumCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateForumFragment createForumFragment = new CreateForumFragment();
                FragmentManager fragmentManager = getFragmentManager();
                assert fragmentManager != null;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, createForumFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        forumList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Forum");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Forum tempForum =  dataSnapshot.getValue(Forum.class);
                    forumList.add(tempForum);
                }
                //create recyclerview
                Context context = view.getContext();
                recyclerView = view.findViewById(R.id.fragment_forum_recycler);
                layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);
                mAdapter = new ViewForumRecyclerViewAdapter(forumList);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }
}

