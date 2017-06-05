package io.rohithram.cricapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by rohithram on 5/6/17.
 */

public class NetworkHelper2 {


        public static String fetchscoreData(String requestUrl, String apikey, String unique_id){
            URL url = null;
            try {
                url = new URL(requestUrl+"?unique_id="+unique_id+"&apikey="+apikey);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String response = null;

            try {
                response = makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return extractscoreData(response);
        }




        private static String makeHttpRequest(URL url) throws IOException{
            String response = "";

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;






            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(15000);
                urlConnection.connect();

                if (urlConnection.getResponseCode() == 200){
                    inputStream = urlConnection.getInputStream();
                    response = getStringResponse(inputStream);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
                if(inputStream != null)
                    inputStream.close();
            }

            return response;
        }

        private static String getStringResponse(InputStream inputStream) throws IOException{

            StringBuilder builder = new StringBuilder();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();

            while (line != null){
                builder.append(line);
                line = bufferedReader.readLine();
            }

            return builder.toString();
        }

        private static String extractscoreData(String response) {

            String status,score,result;
            result=null;
            try {
                JSONObject data = new JSONObject(response);

                try {
                    Log.e("logcat1",data.getString("score"));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                            status= data.getString("innings-requirement");
                            if(data.getBoolean("matchStarted")){
                                score = data.getString("score");
                                result = status.concat("\n\n"+"Scores:\n"+score+"\n");
                            }
                            else
                                result = status;


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return result;
        }
    }

