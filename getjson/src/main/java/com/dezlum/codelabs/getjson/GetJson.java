package com.dezlum.codelabs.getjson;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

/**
 * Created by saurabhkumar on 31/01/18.
 */

public class GetJson {
    private String URL;
    private String imageURL = "https://static.pexels.com/photos/34299/herbs-flavoring-seasoning-cooking.jpg";

    public static String FINDdata(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)url1.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            inputStream = connection.getInputStream();

            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public String AsString(String url) throws ExecutionException, InterruptedException {
        URL = url;
        return new GETasString().execute().get();
    }

    public JsonObject AsJSONObject(String url) throws ExecutionException, InterruptedException {
        URL = url;
        return new GETasJSON().execute().get();
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public boolean isTimeCorrect(int mode, String latitude, String longitude, String mUsername) throws ExecutionException, InterruptedException {
        String firstpart = "http://api.geonames.org/timezoneJSON?";
        int flag = 3;
        String secondpart = firstpart + "lat=" + latitude + "&lng=" + longitude;
        URL = secondpart + "&username=" + mUsername;
        try {
            JsonObject Timeobj = AsJSONObject(URL);
            String date = Timeobj.get("time").getAsString();
            if (mode == 0) {
                //check if Hours are unchanged
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
                String formattedDate = df.format(c.getTime());
                String[] datetimearr = date.split(":");
                if (datetimearr[0].equals(formattedDate)) {
                    flag = 1;
                } else {
                    flag = 0;
                }
            } else {
                //check if houurs and minutes are unchanged
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String formattedDate = df.format(c.getTime());
                if (date.equals(formattedDate)) {
                    flag = 1;
                } else {
                    flag = 0;
                }

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }

    public double getInternetSpeed() throws ExecutionException, InterruptedException {
        double speed = 0;
        long Starttime = System.currentTimeMillis() / 1000L;
        String formality = new GETasStringformality().execute().get();
        long EndTime = System.currentTimeMillis() / 1000L;
        long duration = EndTime - Starttime;
        double d = (double) duration;
        double filesize = 38.4;//megabits
        speed = filesize / d;
        return speed;
    }

    private class GETasString extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String id = URL;
            String jsonbuffer = FINDdata(id);
//            JsonObject reader = new JsonParser().parse(jsonbuffer).getAsJsonObject();
            return jsonbuffer;
            //return GET(id);
        }

    }

    private class GETasJSON extends AsyncTask<String, Void, JsonObject> {
        @Override
        protected JsonObject doInBackground(String... urls) {
            String id = URL;
            String jsonbuffer = FINDdata(id);
            JsonObject reader = new JsonParser().parse(jsonbuffer).getAsJsonObject();
            return reader;
            //return GET(id);
        }

    }

    private class GETasStringformality extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String flag = "";
            try {
                final InputStream inputStream = new URL(imageURL).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                flag = "Done";
            } catch (final MalformedURLException malformedUrlException) {
                // Handle error
            } catch (final IOException ioException) {
                // Handle error
            }
            return flag;
        }

    }



}
