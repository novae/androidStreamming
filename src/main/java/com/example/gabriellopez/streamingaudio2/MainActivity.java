package com.example.gabriellopez.streamingaudio2;

import android.content.DialogInterface;
import android.media.MediaExtractor;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    private Button streamButton;

    private ImageButton playButton;

    private TextView textStreamed;

    private boolean isPlaying;

    private StreamingMediaPlayer audioStreamer;

    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);

        setContentView(R.layout.activity_main);
        initControls();
    }

    private void initControls() {
        textStreamed = (TextView) findViewById(R.id.text_kb_streamed);
        streamButton = (Button) findViewById(R.id.button_stream);
        streamButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startStreamingAudio();
            }});

        playButton = (ImageButton) findViewById(R.id.button_play);
        playButton.setEnabled(false);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (audioStreamer.getMediaPlayer().isPlaying()) {
                    audioStreamer.getMediaPlayer().pause();
                    playButton.setImageResource(R.drawable.button_play);
                } else {
                    audioStreamer.getMediaPlayer().start();
                    audioStreamer.startPlayProgressUpdater();
                    playButton.setImageResource(R.drawable.button_pause);
                }
                isPlaying = !isPlaying;
            }});
    }

    private void startStreamingAudio() {
        try {
            final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            if ( audioStreamer != null) {
                audioStreamer.interrupt();
            }
            //http://issultra.endavomedia.com:8000/ultra90s.mp3
            //http://2483.live.streamtheworld.com:80/KFTZFMAACCMP3


            audioStreamer = new StreamingMediaPlayer(this,textStreamed, playButton, streamButton,progressBar);
            audioStreamer.startStreaming("http://2473.live.streamtheworld.com:443/XERLAMAACAAC_SC",1677, 214);
            //streamButton.setEnabled(false);
        } catch (IOException e) {
            Log.e(getClass().getName(), "Error starting to stream audio.", e);
        }

    }
}
