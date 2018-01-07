package fr.esgi.devtvdb.fragments;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import fr.esgi.devtvdb.R;
import fr.esgi.devtvdb.entities.Series;
import fr.esgi.devtvdb.fragments.SeriesListFragment.OnListFragmentInteractionListener;
import io.realm.Realm;


public class SeriesHorizontalListAdapter extends RecyclerView.Adapter<SeriesHorizontalListAdapter.SingleItemRowHolder> {
    private final String BANNER_ENDPOINT = "https://www.thetvdb.com/banners/";
    private final String POSTER_ENDPOINT = "https://www.thetvdb.com/banners/posters/";
        private ArrayList<Series> itemsList;
        private Context mContext;

        public SeriesHorizontalListAdapter(Context context, ArrayList<Series> itemsList) {
            this.itemsList = itemsList;
            this.mContext = context;
        }

        @Override
        public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_series, null);
            SingleItemRowHolder mh = new SingleItemRowHolder(v);
            return mh;
        }

        @Override
        public void onBindViewHolder(SingleItemRowHolder holder, int position) {
            Series singleItem = itemsList.get(position);
//            Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    Series singleItem1 = itemsList.get(0);
//                    Log.d("LOOG", singleItem1.getSeriesName());
//                }
//            }, new Realm.Transaction.OnSuccess() {
//                @Override
//                public void onSuccess() {
//                    Log.d("SUCCESS","LOOOL");
//                }
//            }, new Realm.Transaction.OnError() {
//                @Override
//                public void onError(Throwable error) {
//                    Log.d("ERROR",error.getMessage());
//                }
//            });


            holder.tvTitle.setText(singleItem.getSeriesName());
            Glide.with(mContext)
                    .load(POSTER_ENDPOINT+singleItem.id+"-1.jpg")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_menu_gallery).into(holder.itemImage);

        }

        @Override
        public int getItemCount() {
            return (null != itemsList ? itemsList.size() : 0);
        }

        public class SingleItemRowHolder extends RecyclerView.ViewHolder {

            protected TextView tvTitle;

            protected ImageView itemImage;


            public SingleItemRowHolder(View view) {
                super(view);

                this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                this.itemImage = (ImageView) view.findViewById(R.id.itemImage);


                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();

                    }
                });


            }

        }

    }


