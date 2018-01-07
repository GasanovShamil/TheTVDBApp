package fr.esgi.devtvdb.entities;

import java.util.ArrayList;

/**
 * Created by shgas on 14/12/2017.
 */

public class SeriesSection {

        private String headerTitle;
        private ArrayList<SeriesUpdate> allSeriesInSection;


        public SeriesSection() {

        }
        public SeriesSection(String headerTitle, ArrayList<SeriesUpdate> allSeriesInSection) {
            this.headerTitle = headerTitle;
            this.allSeriesInSection = allSeriesInSection;
        }



        public String getHeaderTitle() {
            return headerTitle;
        }

        public void setHeaderTitle(String headerTitle) {
            this.headerTitle = headerTitle;
        }

        public ArrayList<SeriesUpdate> getAllSeriesInSection() {
            return allSeriesInSection;
        }

        public void setAllSeriesInSection(ArrayList<SeriesUpdate> allSeriesInSection) {
            this.allSeriesInSection = allSeriesInSection;
        }



}
