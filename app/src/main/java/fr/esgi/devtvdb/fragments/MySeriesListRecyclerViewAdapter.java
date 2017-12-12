package fr.esgi.devtvdb.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.esgi.devtvdb.R;
import fr.esgi.devtvdb.entities.Series;
import fr.esgi.devtvdb.fragments.SeriesListFragment.OnListFragmentInteractionListener;
import fr.esgi.devtvdb.fragments.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Series} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySeriesListRecyclerViewAdapter extends RecyclerView.Adapter<MySeriesListRecyclerViewAdapter.ViewHolder> {
    private final String IMAGE_ENDPOINT = "https://www.thetvdb.com/banners/";
    private final List<Series> mValues;
    private final OnListFragmentInteractionListener mListener;
    private ViewGroup parent;
    public MySeriesListRecyclerViewAdapter(List<Series> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_serieslist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mIdView.setCardBackgroundColor(ColorStateList.valueOf(Color.BLUE));
        Glide.with(parent.getContext()).load(IMAGE_ENDPOINT+mValues.get(position).banner).into(holder.mImageView);
        holder.mContentView.setText(mValues.get(position).seriesName);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CardView mIdView;
        public final ImageView mImageView;
        public final TextView mContentView;
        public Series mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (CardView) view.findViewById(R.id.card_view);
            mImageView = (ImageView) view.findViewById(R.id.cardImageView);
            mContentView = (TextView) view.findViewById(R.id.info_text);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
