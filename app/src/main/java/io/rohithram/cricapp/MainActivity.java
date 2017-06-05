package io.rohithram.cricapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static io.rohithram.cricapp.R.id.tv_teams;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_list;
    CustomAdapter adapter;
    List<Matches> matches;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchactivity);

        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        rv_list.setItemAnimator(new DefaultItemAnimator());
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        Log.e("logcat1","Entered asyntask");
        matches= new ArrayList<>();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);


        GetmatchTask getmatch = new GetmatchTask();
        getmatch.execute("http://cricapi.com/api/matches","ij6Z9JWp8CcNCWJVGdvRTbWxS1C2");


    }


    class GetmatchTask extends AsyncTask<String,Void,List<Matches>> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected List<Matches> doInBackground(String... params) {
            List<Matches> matchesList = NetworkHelper.fetchmatchData(params[0], params[1]);
            Log.e("logcat1", "Downloading");
            return matchesList;
        }

        @Override
        protected void onPostExecute(List<Matches> matches) {
            Log.e("logcat1", "Onpost executed");
            progressBar.setVisibility(View.GONE);

            adapter = new CustomAdapter(MainActivity.this,matches);
            rv_list.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }


        }


    }



