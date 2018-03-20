package com.example.mango.translator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mango on 20-03-2018.
 */

public class TranslatorAdapter extends RecyclerView.Adapter<TranslatorAdapter.WordViewHolder> {

    private List<Words> wordList;

    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_list, parent, false);

        return new WordViewHolder(itemView);
    }

    public  TranslatorAdapter(List<Words> _wordlist)
    {
        this.wordList = _wordlist;
    }

    public void onBindViewHolder(WordViewHolder holder, int position) {
        Words movie = wordList.get(position);
        holder.language.setText(movie.getLanguage());
        holder.translateWord.setText(movie.getTranslatedWord());
    }


    public int getItemCount() {
        return wordList.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        public TextView translateWord, language;

        public WordViewHolder(View view) {
            super(view);
            translateWord = (TextView) view.findViewById(R.id.word);
            language = (TextView) view.findViewById(R.id.lang);
        }
    }
}
