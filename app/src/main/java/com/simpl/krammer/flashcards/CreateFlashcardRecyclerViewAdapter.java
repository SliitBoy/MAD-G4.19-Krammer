package com.simpl.krammer.flashcards;

import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CreateFlashcardRecyclerViewAdapter extends RecyclerView.Adapter<CreateFlashcardRecyclerViewAdapter.ViewHolder> {

    //TODO replace CardInput with FlashCard!!!!!!!!!!!
    private final List<Flashcard> mFlashCardSet;

    public CreateFlashcardRecyclerViewAdapter(List<Flashcard> items) {
        mFlashCardSet = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_create_flashcard_set, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mFlashCardSet.get(position);
        int index = position;
        holder.mItem.setIndex(++index);

        //Term TextWatcher
        holder.mTerm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Set FlashCard Term
                String term = holder.mTerm.getText().toString();
                if (!term.isEmpty()) {
                    holder.mItem.setTerm(term);
                   // holder.mId.setText("Card " + mFlashCardSet.get(position).getTerm() + mFlashCardSet.get(position).getIndex());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Definition TextWatcher
        holder.mDef.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Set FlashCard Definition
                String def = holder.mDef.getText().toString();
                if (!def.isEmpty()) {
                    holder.mItem.setDefinition(def);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Remove Card Button ClickListener
        holder.removeCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFlashCardSet.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(position, mFlashCardSet.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlashCardSet.size();
    }

    public List<Flashcard> getSavedList() {
         return mFlashCardSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //TODO: remove this
        //public final TextView mId;
        public TextInputEditText mTerm;
        public TextInputEditText mDef;
        public ImageButton removeCardButton;
        public Flashcard mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mId = (TextView) view.findViewById(R.id.newCardID);
            removeCardButton = (ImageButton) view.findViewById(R.id.ButtonRemove);
            mTerm = (TextInputEditText) view.findViewById(R.id.TextInputEditTextNewCardTerm);
            mDef = (TextInputEditText) view.findViewById(R.id.TextInputEditTextNewCardDef);
        }
    }
}