package com.example.mango.translator;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Words>>  {

    private List<Words> wordsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TranslatorAdapter mAdapter;
    private  EditText editText;
    ProgressDialog progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        editText = (EditText) findViewById(R.id.edit_txt);


        mAdapter = new TranslatorAdapter(wordsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        Bundle newBundle = new Bundle();
        newBundle.putString("word", editText.getText().toString());
        if(isNetworkAvailable())
            getLoaderManager().initLoader(1, newBundle, this);
        else
            Toast.makeText(this, "Please check internet connection and try again.", Toast.LENGTH_SHORT).show();
    }

    public void updateWords(View ccv)
    {
        if(isNetworkAvailable())
        {
            Bundle newBundle = new Bundle();
            newBundle.putString("word", editText.getText().toString());
            getLoaderManager().restartLoader(1, newBundle, this);
        }
        else
        {
            Toast.makeText(this, "Please check internet connection and try again.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public Loader<List<Words>> onCreateLoader(int i, Bundle bundle) {
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Looking For Translation....");
        progress.setCancelable(false);
        progress.show();
        return new TranslatorLoader(MainActivity.this, bundle.getString("word"));
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onLoadFinished(Loader<List<Words>> loader, List<Words> words) {
        recyclerView.setAdapter(new TranslatorAdapter(words));
        mAdapter.notifyDataSetChanged();
        progress.dismiss();
    }

    @Override
    public void onLoaderReset(Loader<List<Words>> loader) {

    }
}