package com.simpl.krammer.todo;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simpl.krammer.R;

import java.util.List;

public class ViewToDoRecyclerViewAdapter extends RecyclerView.Adapter<ViewToDoRecyclerViewAdapter.ViewHolder> {

    private final List<ToDo> mValues;

    public ViewToDoRecyclerViewAdapter(List<ToDo> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getTask());
        holder.mContentView.setText(mValues.get(position).getPriority());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public ToDo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.todo_title_task);
            mContentView = (TextView) view.findViewById(R.id.todo_num_task);
        }

    }
}
