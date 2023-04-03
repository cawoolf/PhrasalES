package com.example.phrasales.util

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import java.util.*


class PhrasalUtil(val context: Context?) {

    private lateinit var mTextToSpeech: TextToSpeech
    private lateinit var mENESTranslator: Translator
    private lateinit var mESENTranslator: Translator

    private fun buildTranslators() {
        // Create an English-French Translator
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()

        val englishSpanishTranslator = Translation.getClient(options)

        mENESTranslator = englishSpanishTranslator

        val options2 = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.SPANISH)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        val spanishEnglishTranslator = Translation.getClient(options2)
        mESENTranslator = spanishEnglishTranslator

    }


    // Sets up the TextToSpeech function for use in the Fragment
     fun setUpTTS() {
        mTextToSpeech = TextToSpeech(context, TextToSpeech.OnInitListener {
            if(it== TextToSpeech.SUCCESS){
                val locSpanish = Locale("spa", "MEX")
                mTextToSpeech.language = locSpanish
                mTextToSpeech.setSpeechRate(1.0f)
                Log.i("mTTS", "TTS Success")
            }
            if(it== TextToSpeech.ERROR) {
                Log.i("mTTS", "TTS Failed" + TextToSpeech.ERROR)
            }
        })

    }

    fun useTextToSpeech(frenchText: String) {
        if (!mTextToSpeech.isSpeaking) {

        mTextToSpeech.setSpeechRate(1.0F)
        mTextToSpeech.speak(frenchText,
            TextToSpeech.QUEUE_ADD,
            null)
        }
    }

    fun getENFRTranslator() : Translator {
        buildTranslators()
        return mENESTranslator
    }

    fun getFRENTranslator() : Translator {
        buildTranslators()
        return mESENTranslator
    }


}