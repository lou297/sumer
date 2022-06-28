package com.example.sumer.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.util.Log
import com.example.sumer.MainActivity

class SpeechRecognitionListener(val context: Context) : RecognitionListener {
    private val mainActivityBinding by lazy {
        (context as MainActivity).binding
    }

    override fun onReadyForSpeech(p0: Bundle?) {
    }

    override fun onBeginningOfSpeech() {
    }

    override fun onRmsChanged(p0: Float) {
    }

    override fun onBufferReceived(p0: ByteArray?) {
    }

    override fun onEndOfSpeech() {
        mainActivityBinding.webview.loadUrl(Constants.END_VOICE)
    }

    override fun onError(p0: Int) {
    }

    override fun onResults(results: Bundle?) {
        val sttRecognizeResult =
            results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) as ArrayList<String>

        mainActivityBinding.webview.loadUrl(Constants.SET_VOICE(sttRecognizeResult[0]))
    }

    override fun onPartialResults(p0: Bundle?) {
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
    }
}