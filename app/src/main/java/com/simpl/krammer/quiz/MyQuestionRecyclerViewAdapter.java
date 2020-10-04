package com.simpl.krammer.quiz;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simpl.krammer.R;


import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Question}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyQuestionRecyclerViewAdapter extends RecyclerView.Adapter<MyQuestionRecyclerViewAdapter.ViewHolder> {

    private final List<Question> mValues;

    public MyQuestionRecyclerViewAdapter(List<Question> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_view_questions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.Question.setText(mValues.get(position).getQuestion());
        holder.Answer1.setText(mValues.get(position).getAnswer(0).toString());
        holder.Answer2.setText(mValues.get(position).getAnswer(1).toString());
        holder.Answer3.setText(mValues.get(position).getAnswer(2).toString());
        holder.Answer4.setText(mValues.get(position).getAnswer(3).toString());
        Log.d("MyApp",mValues.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView Answer1;
        public final TextView Answer2;
        public final TextView Answer3;
        public final TextView Answer4;
        public final TextView Question;
        public Question mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            Question = (TextView) view.findViewById(R.id.Question);
            Answer1 = (TextView) view.findViewById(R.id.answer1);
            Answer2 = (TextView) view.findViewById(R.id.answer2);
            Answer3 = (TextView) view.findViewById(R.id.answer3);
            Answer4 = (TextView) view.findViewById(R.id.answer4);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + Question.getText() + "'";
        }
    }
}