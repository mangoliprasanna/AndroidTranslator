package com.example.mango.translator;

/**
 * Created by mango on 20-03-2018.
 */

public class Words {
    private String translatedWord = "";
    private String language = "";

    public Words(String _translatedWord, String _language)
    {
        translatedWord = _translatedWord;
        language = _language;
    }

    public  String getTranslatedWord(){ return  translatedWord; }
    public  String getLanguage() { return  language; }
}
