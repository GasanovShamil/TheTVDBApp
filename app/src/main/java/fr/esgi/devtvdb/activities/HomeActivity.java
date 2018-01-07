package fr.esgi.devtvdb.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import fr.esgi.devtvdb.Helpers.SystemHelper;
import fr.esgi.devtvdb.R;
import fr.esgi.devtvdb.Services.SeriesService;
import fr.esgi.devtvdb.entities.Series;
import fr.esgi.devtvdb.entities.SeriesUpdate;
import fr.esgi.devtvdb.entities.User;
import fr.esgi.devtvdb.fragments.SeriesListFragment;
import fr.esgi.devtvdb.Helpers.SharedPreferencesHelper;
import io.realm.Realm;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SeriesListFragment.OnListFragmentInteractionListener {
    static final private int USER_LOGIN = 0;

    private User _user=null;
    private UpdatedSeriesTask _updateSeriesTask;
    private Boolean userLogged = false;
    private ArrayList<Series> lastUpdatedSeries = new ArrayList<>();
    private ArrayList<SeriesUpdate> seriesUpdate  = new ArrayList<>();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        _user = SharedPreferencesHelper.getUser(this);
        _updateSeriesTask = new UpdatedSeriesTask(this);

        if(savedInstanceState == null ) {
            if(SystemHelper.isOnline(this)) {
                startLogin();
            }else{
                _updateSeriesTask.execute((Void) null);
            }
        }

    }





    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("user", _user);
        outState.putParcelableArrayList("lastUpdatedSeries",lastUpdatedSeries);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        _user = savedInstanceState.getParcelable("user");
        lastUpdatedSeries = savedInstanceState.getParcelableArrayList("lastUpdatedSeries");
        showSeriesListFragment(seriesUpdate);
    }

    private void startLogin() {
        Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivityForResult(loginIntent, USER_LOGIN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case USER_LOGIN:
                    _user = data.getParcelableExtra("user");
                    userLogged = true;
                    _updateSeriesTask.execute((Void) null);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Series item) {

    }

    private void showSeriesListFragment(ArrayList<SeriesUpdate> s){
        int columnNumber = 1; //SystemHelper.getScreenOrientation(this);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        Fragment seriesListFragment = SeriesListFragment.newInstance(columnNumber, s);
        Fragment seriesListFragment = new SeriesListFragment(columnNumber, s);
        fragmentTransaction.replace(R.id.content_home,seriesListFragment).commitAllowingStateLoss();
    };

    public class UpdatedSeriesTask extends AsyncTask<Void, Void, Boolean> {

        private Context context;

        UpdatedSeriesTask(Context aContext) {
            context = aContext;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

//            if (!SystemHelper.isOnline(context)){
                seriesUpdate = SeriesService.getLastUpdatedSeries();
                int size = seriesUpdate.size();
                seriesUpdate = (ArrayList<SeriesUpdate>) seriesUpdate.subList(0,(size<20)?size:20);
//                for (int i=20; i>=2;i--){
//                    SeriesUpdate s = (seriesUpdate.get(size - i));
//                    lastUpdatedSeries.add(SeriesService.getSeriesById(s.id, "en"));
//                }
//                final Series favoriteSeries = SeriesService.getSeriesById((long)281662, "en");
//                Realm realm = Realm.getDefaultInstance();
//                realm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        // This will create a new object in Realm or throw an exception if the
//                        // object already exists (same primary key)
//                        // realm.copyToRealm(obj);
//
//                        // This will update an existing object with the same primary key
//                        // or create a new object if an object with no primary key = 42
//                        realm.copyToRealmOrUpdate(favoriteSeries);
//                    }
//                });
                return (seriesUpdate != null && !seriesUpdate.isEmpty())?true:false;
//            }else{
//                Realm realm = Realm.getDefaultInstance();
//                Series favoriteSeries = realm.where(Series.class).equalTo("id", 281662).findFirst();
//                Log.d("LOG", favoriteSeries.getSeriesName());
//                lastUpdatedSeries.add(realm.copyFromRealm(favoriteSeries));
//                return true;
//
//            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                showSeriesListFragment(seriesUpdate);
            } else {
                Log.e("LOOOOSER","LOOOOOSER");
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
