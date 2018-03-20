package com.example.mango.translator;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mango on 20-03-2018.
 */

public class TranslatorLoader extends AsyncTaskLoader<List<Words>> {

    private String word_to = "";
    public TranslatorLoader(Context context, String _word) {
        super(context);
        word_to = _word;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Words> loadInBackground() {
        FetchData obj = new FetchData();
        String json_Data = obj.getData("http://translator-133.herokuapp.com/JSON/" + word_to);
        List<Words> finalList = new ArrayList<>();
        try {

            JSONObject json = new JSONObject(json_Data);
            Iterator<String> temp = json.keys();
            while(temp.hasNext())
            {
                String key = temp.next();
                finalList.add(new Words(json.getString(key), key));
            }
        } catch (JSONException e) {
            Log.w("Error", "Error While Loadin to Translations");
            e.printStackTrace();
        }
        return finalList;
    }
}
