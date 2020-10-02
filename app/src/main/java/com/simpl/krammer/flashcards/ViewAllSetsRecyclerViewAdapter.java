package com.simpl.krammer.flashcards;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ViewAllSetsRecyclerViewAdapter extends RecyclerView.Adapter<ViewAllSetsRecyclerViewAdapter.ViewHolder> implements Filterable {

    private final List<FlashcardSet> mValues;
    //Duplicate List
    private final List<FlashcardSet> mAllValues;

    private OnItemCLickListener mListener;

    public interface OnItemCLickListener {
        void onItemClick(int position, FlashcardSet fcs);
    }

    public void setOnItemClickListener(OnItemCLickListener listener) {
        mListener = listener;
    }

    public ViewAllSetsRecyclerViewAdapter(List<FlashcardSet> items) {
        mValues = items;
        //Set Duplicate List
        mAllValues = new ArrayList<>(mValues);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_view_all_sets, parent, false);
        //pass mListener into constructor
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        FlashcardSet mSelectedSet= mValues.get(position);
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
        //public FlashcardSet mSelectedSet;

        public ViewHolder(View view, final OnItemCLickListener listener) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.cardSetTitle);
            mDescription = (TextView) view.findViewById(R.id.cardSetDescription);
            mNumberOfCards = (TextView) view.findViewById(R.id.cardSetTerms);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        FlashcardSet selectedFCS = mValues.get(position);
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position, selectedFCS);
                        }
                    }
                }
            });
        }

    }


    //Filter Logic
    private Filter FlashcardSetFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
           List<FlashcardSet> filteredList = new ArrayList<>();

           if (charSequence == null || charSequence.length() == 0) {
               filteredList.addAll(mAllValues);
           } else {
               //toLowerCase() to make search case insensitive
               String filterPattern = charSequence.toString().toLowerCase().trim();
                //iterate through FlashcardSets in mAllValues and add matching items into filteredList
               for (FlashcardSet fcs :mAllValues) {
                   if(fcs.getCardSetTitle().toLowerCase().startsWith(filterPattern)) {
                       filteredList.add(fcs);
                   }
               }
           }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return  filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            //Clear RecyclerView FlashcardSet list
            mValues.clear();
            //Add filtered items to FlashcardSet list
            mValues.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return FlashcardSetFilter;
    }
}