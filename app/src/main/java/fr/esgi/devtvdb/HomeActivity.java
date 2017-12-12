package fr.esgi.devtvdb;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.esgi.devtvdb.entities.Series;
import fr.esgi.devtvdb.entities.SeriesUpdate;
import fr.esgi.devtvdb.entities.User;
import fr.esgi.devtvdb.entities.Wrapper;
import fr.esgi.devtvdb.fragments.SeriesListFragment;
import fr.esgi.devtvdb.tools.ApiServices;
import fr.esgi.devtvdb.tools.DateHelper;
import fr.esgi.devtvdb.tools.GodMod;
import fr.esgi.devtvdb.tools.ITheTVDB;
import fr.esgi.devtvdb.tools.SharedPreferencesHelper;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SeriesListFragment.OnListFragmentInteractionListener {
    static final private int USER_LOGIN = 0;
    private ITheTVDB _tvdb;
    private User _user=null;
    private UpdatedSeriesTask _updateSeriesTask;
    private Boolean userLogged = false;
    private ArrayList<Series> lastUpdatedSeries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        _tvdb = ApiServices.getTvdbInstance();
        _user = SharedPreferencesHelper.getUser(this);
        _updateSeriesTask = new UpdatedSeriesTask(this);

        if(savedInstanceState == null) {
            startLogin();
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
        showLastUpdatedSeries(lastUpdatedSeries);
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

    private void showLastUpdatedSeries(ArrayList<Series> s){
        int columnNumber = GodMod.getScreenOrientation(this);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment seriesListFragment = SeriesListFragment.newInstance(columnNumber, s);
        fragmentTransaction.replace(R.id.content_home,seriesListFragment).commitAllowingStateLoss();
    };

    public class UpdatedSeriesTask extends AsyncTask<Void, Void, Boolean> {
        List<SeriesUpdate> seriesUpdate;

        private Context context;

        UpdatedSeriesTask(Context aContext) {
            context = aContext;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            seriesUpdate = getLastUpdatedSeries();
            int size = seriesUpdate.size();
            Log.e("SIZE", ""+size);

            for (int i=20; i>=2;i--){
                SeriesUpdate s = (seriesUpdate.get(size - i));
                lastUpdatedSeries.add(getSeriesById(s.id));
            }

            Log.e("SIZE2", ""+lastUpdatedSeries.size());
            return (lastUpdatedSeries != null && !lastUpdatedSeries.isEmpty())?true:false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                showLastUpdatedSeries(lastUpdatedSeries);

            } else {
                Log.e("LOOOOSER","LOOOOOSER");
            }
        }

        @Override
        protected void onCancelled() {

        }

        private List<SeriesUpdate> getLastUpdatedSeries(){
            Wrapper<List<SeriesUpdate>> updateList = new Wrapper<>();
            try {
                updateList = _tvdb.getUpdatedSeries(DateHelper.getEpochTime(-1),"Bearer " + _user.getToken(),"en").execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return updateList.data;
        };

        private Series getSeriesById(Long id){
            Wrapper<Series> series = new Wrapper<>();
            _tvdb.getSeriesById(id, "Bearer " + _user.getToken(),"en");
            try {
               series =  _tvdb.getSeriesById(id, "Bearer " + _user.getToken(),"en").execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

           return series.data;
        }



    }
}
