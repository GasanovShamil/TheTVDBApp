package fr.esgi.devtvdb.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.esgi.devtvdb.Helpers.DateHelper;
import fr.esgi.devtvdb.entities.Series;
import fr.esgi.devtvdb.entities.SeriesUpdate;
import fr.esgi.devtvdb.interfaces.ISeriesService;

/**
 * Created by shgas on 14/12/2017.
 */

public class SeriesService {
    private static ISeriesService _seriesService;

    public static ArrayList<SeriesUpdate> getLastUpdatedSeries(){
        _seriesService = TVDBServices.getTvdbSeriesInstance();
        ArrayList<SeriesUpdate> updateList = new ArrayList<>();
        try {
            updateList = _seriesService.getUpdatedSeries(DateHelper.getEpochTime(-1),"Bearer " + TVDBServices.getToken()).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updateList;
    };

    public static Series getSeriesById(Long id, String language){
        Series series = new Series();
        try {
            series =  _seriesService.getSeriesById(id, "Bearer " + TVDBServices.getToken(),language).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return series;
    }
}
