package com.simpl.krammer.flashcards;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent.DummyItem;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.List;

/**
 * Created by IT19008042, N.H. Thiranjaya
 * {@link RecyclerView.Adapter} that can display a {@link Flashcard}.
 * TODO: Replace the implementation with code for your data type.
 */
public class StudyFlashcardSetRecyclerViewAdapter extends RecyclerView.Adapter<StudyFlashcardSetRecyclerViewAdapter.ViewHolder> {

    private final List<Flashcard> mValues;
    private OnItemCLickListener mListener;

    public interface OnItemCLickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemCLickListener listener) {
        mListener = listener;
    }

    public StudyFlashcardSetRecyclerViewAdapter(List<Flashcard> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_study_flashcard_set, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTerm.setText(mValues.get(position).getTerm());
        holder.mDefinition.setText(mValues.get(position).getDefinition());
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
        public EasyFlipView mEasyFlipView;

        public ViewHolder(View view, final OnItemCLickListener listener) {
            super(view);
            mView = view;
            //set to front TextView
            mTerm = (TextView) view.findViewById(R.id.flashcardFrontTextView);
            //set to back TextView
            mDefinition = (TextView) view.findViewById(R.id.flashcardBackTextView);

            mEasyFlipView = itemView.findViewById(R.id.studyEasyFlip);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEasyFlipView.flipTheView();
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}