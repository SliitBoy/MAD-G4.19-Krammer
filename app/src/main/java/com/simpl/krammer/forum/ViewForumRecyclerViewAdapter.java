package com.simpl.krammer.forum;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simpl.krammer.R;

import java.util.List;

public class ViewForumRecyclerViewAdapter extends RecyclerView.Adapter<ViewForumRecyclerViewAdapter.ViewHolder> {

    private final List<Forum> mValues;

    public ViewForumRecyclerViewAdapter(List<Forum> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forum_view_all_copy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getForum_title());
        holder.mContentView.setText(mValues.get(position).getForum_desc());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Forum mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.forum_title_copy);
            mContentView = (TextView) view.findViewById(R.id.forum_descp_copy);
        }

    }
}