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
import com.google.android.material.textfield.TextInputLayout;
import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * Created by IT19008042, N.H. Thiranjaya
 * {@link RecyclerView.Adapter} that can display a {@link Flashcard}.
 *
 */
public class CreateFlashcardRecyclerViewAdapter extends RecyclerView.Adapter<CreateFlashcardRecyclerViewAdapter.ViewHolder> {

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
        holder.mTerm.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Set FlashCard Term
                String term = holder.mTerm.getEditText().getText().toString();
                if (term.isEmpty()) {
                    //if input field is empty
                    holder.mTerm.setError("Term field cannot be empty");

                } else if (term.length() > 20) {
                    holder.mTerm.setError("Term cannot exceed 20 characters");
                } else {
                    //set values
                    holder.mItem.setTerm(term);
                    //hide error field
                    holder.mTerm.setError(null);
                    holder.mTerm.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Definition TextWatcher
        holder.mDef.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Set FlashCard Definition
                String def = holder.mDef.getEditText().getText().toString();
                if (def.isEmpty()) {
                    //if input field is empty
                    holder.mDef.setError("Definition field cannot be empty");

                } else if (def.length() > 100) {
                    holder.mDef.setError("Definition cannot exceed 100 characters");
                } else {
                    //set values
                    holder.mItem.setDefinition(def);
                    //hide error field
                    holder.mDef.setError(null);
                    holder.mDef.setErrorEnabled(false);
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
    } public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //TODO: remove this
        //public final TextView mId;
        public TextInputLayout mTerm;
        public TextInputLayout mDef;
        public ImageButton removeCardButton;
        public Flashcard mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mId = (TextView) view.findViewById(R.id.newCardID);
            removeCardButton = (ImageButton) view.findViewById(R.id.ButtonRemove);
            mTerm = (TextInputLayout) view.findViewById(R.id.InputLayoutNewCardTerm);
            mDef = (TextInputLayout) view.findViewById(R.id.InputLayoutNewCardDef);
        }
    }

    public List<Flashcard> getSavedList() {
         return mFlashCardSet;
    }



}