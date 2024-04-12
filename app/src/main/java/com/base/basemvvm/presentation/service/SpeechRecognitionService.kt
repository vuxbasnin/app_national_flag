package com.base.basemvvm.presentation.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.speech.RecognitionListener
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SpeechRecognitionService : Service(), RecognitionListener {
    private var service: ExecutorService? = null
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        startForeground(1, createNotification())
        service = Executors.newFixedThreadPool(3)
        init()
        return START_NOT_STICKY
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

    }

    override fun onError(p0: Int) {

    }

    override fun onResults(p0: Bundle?) {

    }

    override fun onPartialResults(p0: Bundle?) {

    }

    override fun onEvent(p0: Int, p1: Bundle?) {

    }

    //function

    private fun init() {
        service?.submit {
            try {

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

//    private fun createNotification(): Notification? {
//
//    }
}