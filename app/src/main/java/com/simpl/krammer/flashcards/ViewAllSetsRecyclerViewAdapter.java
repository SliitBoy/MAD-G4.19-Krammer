package com.simpl.krammer.flashcards;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ViewAllSetsRecyclerViewAdapter extends RecyclerView.Adapter<ViewAllSetsRecyclerViewAdapter.ViewHolder> {

    private final List<FlashcardSet> mValues;

    public ViewAllSetsRecyclerViewAdapter(List<FlashcardSet> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_view_all_sets, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitle.setText(mValues.get(position).getCardSetTitle());
        holder.mDescription.setText(mValues.get(position).getCardSetDescription());
        holder.mNumberOfCards.setText(mValues.get(position).getSetSize() + " Cards");
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mDescription;
        public final TextView mNumberOfCards;
        public FlashcardSet mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.cardSetTitle);
            mDescription = (TextView) view.findViewById(R.id.cardSetDescription);
            mNumberOfCards = (TextView) view.findViewById(R.id.cardSetTerms);
        }

    }
}