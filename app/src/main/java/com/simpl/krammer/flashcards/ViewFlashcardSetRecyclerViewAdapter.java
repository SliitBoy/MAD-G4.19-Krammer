package com.simpl.krammer.flashcards;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Flashcard}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ViewFlashcardSetRecyclerViewAdapter extends RecyclerView.Adapter<ViewFlashcardSetRecyclerViewAdapter.ViewHolder> {

    private final List<Flashcard> mValues;

    public ViewFlashcardSetRecyclerViewAdapter(FlashcardSet items) {
        mValues = items.getCardSet();
        Log.e("getTest " ,""+mValues.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_view_flashcard_set, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mTerm.setText( holder.mItem.getTerm());
        holder.mDefinition.setText( holder.mItem.getDefinition());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTerm;
        public final TextView mDefinition;
        public Flashcard mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTerm = (TextView) view.findViewById(R.id.cardTerm);
            mDefinition = (TextView) view.findViewById(R.id.cardDefinition);
        }

    }

}