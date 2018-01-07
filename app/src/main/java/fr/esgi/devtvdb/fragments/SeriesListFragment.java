package fr.esgi.devtvdb.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.esgi.devtvdb.R;
import fr.esgi.devtvdb.entities.Series;
import fr.esgi.devtvdb.entities.SeriesSection;
import fr.esgi.devtvdb.entities.SeriesUpdate;

import java.util.ArrayList;


public class SeriesListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_LIST_ITEMS = "list-items";
    private int mColumnCount = 1;
    private ArrayList<SeriesUpdate> seriesList;
    private ArrayList<SeriesSection> seriesSectionList;
    private OnListFragmentInteractionListener mListener;
    private boolean test = false;

    public SeriesListFragment() {
    }

    public SeriesListFragment(int columnCount, ArrayList<SeriesUpdate> seriesList){
        this.mColumnCount = columnCount;
        this.seriesList = seriesList;
        this.test = true;
    }

    public static SeriesListFragment newInstance(int columnCount, ArrayList<Series> seriesList) {
        SeriesListFragment fragment = new SeriesListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putParcelableArrayList(ARG_LIST_ITEMS, seriesList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            seriesList = getArguments().getParcelableArrayList(ARG_LIST_ITEMS);
            SeriesSection sec1 = new SeriesSection("Last updated", seriesList);
            SeriesSection sec2 = new SeriesSection("Favorites", seriesList);
            SeriesSection sec3 = new SeriesSection("TOP", seriesList);
            SeriesSection sec4 = new SeriesSection("Drama", seriesList);
            SeriesSection sec5 = new SeriesSection("Horror", seriesList);
            SeriesSection sec6 = new SeriesSection("Triller", seriesList);
            seriesSectionList = new ArrayList<>();
            seriesSectionList.add(sec1);
            seriesSectionList.add(sec2);
            seriesSectionList.add(sec3);
            seriesSectionList.add(sec4);
            seriesSectionList.add(sec5);
            seriesSectionList.add(sec6);
        }

        if (test){
            SeriesSection sec1 = new SeriesSection("Last updated", seriesList);
            SeriesSection sec2 = new SeriesSection("Favorites", seriesList);
            SeriesSection sec3 = new SeriesSection("TOP", seriesList);
            SeriesSection sec4 = new SeriesSection("Drama", seriesList);
            SeriesSection sec5 = new SeriesSection("Horror", seriesList);
            SeriesSection sec6 = new SeriesSection("Triller", seriesList);
            seriesSectionList = new ArrayList<>();
            seriesSectionList.add(sec1);
            seriesSectionList.add(sec2);
            seriesSectionList.add(sec3);
            seriesSectionList.add(sec4);
            seriesSectionList.add(sec5);
            seriesSectionList.add(sec6);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series_section_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if ( mColumnCount == 0) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            }else if (mColumnCount == 1){
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new SeriesVerticalListAdapter(context, seriesSectionList));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Series item);
    }
}
