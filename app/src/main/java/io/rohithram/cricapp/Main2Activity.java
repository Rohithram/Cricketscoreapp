package io.rohithram.cricapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView tv_status;
    List<Matchscores> matchscores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoresactivity);

        Intent score = getIntent();
        String h1 = score.getStringExtra("send");
        String id = score.getStringExtra("send1");
        matchscores = new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tv_status = (TextView)findViewById(R.id.tv_status);



        GetscoreTask getscore= new GetscoreTask();
        getscore.execute("http://cricapi.com/api/cricketScore","ij6Z9JWp8CcNCWJVGdvRTbWxS1C2",id);

        TextView tv_matchname = (TextView) findViewById(R.id.tv_matchname);


        tv_matchname.setText(h1);
    }
    class GetscoreTask extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            tv_status.setText("");
        }
        @Override
        protected String doInBackground(String... params) {
            String scoresList = NetworkHelper2.fetchscoreData(params[0], params[1],params[2]);
            Log.e("logcat1", "Downloading scores");
            return scoresList;
        }

        @Override
        protected void onPostExecute(String statuses) {
            Log.e("logcat1", "Onpost executed in scores");
            progressBar.setVisibility(View.GONE);
            tv_status.setText(statuses);

        }


    }

}
