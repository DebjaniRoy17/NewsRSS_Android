package com.example.newsrssfeed;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsrssfeed.Adapter.FeedAdapter;
import com.example.newsrssfeed.Common.HTTPDataHandler;
import com.example.newsrssfeed.Model.Rss;
import com.example.newsrssfeed.Model.RssObject;
import com.example.newsrssfeed.Model.SampleXMLModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;

import org.json.JSONException;

import org.w3c.dom.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    Rss rss;

    private static final String TAG = "MyActivity";
    private final String RSS_link="https://www.hindustantimes.com/rss/topnews/rssfeed.xml";//change the link of the RSS feed you want to chow
    //private final String RSS_to_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Top News");
        //setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            loadRSS();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_refresh) {
            try {
                loadRSS();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    private void loadRSS() throws IOException, JSONException {
        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(MainActivity.this);

            @Override
            protected String doInBackground(String... strings) {

                try {
                    String json = null;
                    HTTPDataHandler handler = new HTTPDataHandler();
                    String xmlString= handler.GetHTTPData(RSS_link);
                    Log.i(TAG,xmlString);
                    XmlMapper xmlMapper = new XmlMapper();

                    Rss rss = xmlMapper.readValue(xmlString, Rss.class);
                    Log.d(TAG,rss.toString());
                   /* xmlString = "<kuchbhi><title>Hindustan Times - topnews</title> <link>https://www.hindustantimes.com/</link> <description> <![CDATA[ News Headlines: Hindustan Times provides exclusive top stories of the day, today headlines from politics, business, technology, photos, videos, latest English news and much more ]]> </description> <language>en-US</language></kuchbhi>";
                    SampleXMLModel sampleXMLModel = xmlMapper.readValue(xmlString, SampleXMLModel.class);
                    Log.d(TAG,sampleXMLModel.toString());*/
                    // Log.i(TAG,rssObject.getItems ().get(5).getDescription());
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.enable(SerializationFeature.INDENT_OUTPUT);
                    json = mapper.writeValueAsString(rss);
                    Log.i(TAG,json);
                    return json;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
                /*JSONObject jsonObject = null;
                String output = null;
                try {
                    jsonObject = XML.toJSONObject(xmlString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                Object json = null;
                try {
                    json = objectMapper.readValue(jsonObject.toString(), Object.class);
                    output = objectMapper.writeValueAsString(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return output;*/

            }

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Please wait...");
                mDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                mDialog.dismiss();
                rss = new Gson().fromJson(s,Rss.class);
                FeedAdapter feedAdapter = new FeedAdapter(rss,getBaseContext());
                recyclerView.setAdapter(feedAdapter);
                feedAdapter.notifyDataSetChanged();
            }



        };


        loadRSSAsync.execute();




    }


}
