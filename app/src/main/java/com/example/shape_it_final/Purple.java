package com.example.shape_it_final;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Purple Class
 *
 * A Purple is a type of GameItem that will appear in ColorActivity to
 * be touched where it will then show its name and say its name
 *
 * Implements the GameItem Interface
 *
 *Methods:
 *  draw() - Draw will draw the GameItem on the screen
 *  showsName() - will make the name of the GameItem appear
 *  saysName() - will say the name of the GameItem
 *  clearName() - will clear the name from the screen
 */
public class Purple implements GameItem {

    private String TAG = "SHAPEIT Purple";
    private ImageButton purpleButton;
    private TextView purpleName;

    /**
     * Purple Constructor
     *
     * Assigns the parameters to the method variables.
     * @param colorButton - reference to the button on ColorActivity
     * @param colorName - reference to the TextView on ColorActivity
     */
    Purple(ImageButton colorButton, TextView colorName) {
        Log.i(TAG, "Started Purple class");
        purpleButton = colorButton;
        purpleName = colorName;
    }

    @Override
    public void draw() {
        purpleButton.setImageResource(R.drawable.purple);
        Log.i(TAG, "Drew a purple color");
    }

    @Override
    public void showsName() {
        purpleName.setText("Purple");
        Log.i(TAG, "Shows the Purple Name");
    }

    @Override
    public void saysName(Context context) {
        //this needs changed to Purple audio
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.oval);
        mediaPlayer.start();

        Runnable r = new Runnable() {
            @Override
            public void run(){
                mediaPlayer.release();
            }
        };

        //3 helps handled the delay
        Handler h = new Handler();
        h.postDelayed(r, 3000); // <-- the "3000" is the delay time in milliseconds.

        Log.i(TAG, "Played the sound of the name of the Purple");
    }

    @Override
    public void clearName() {
        purpleName.setText("");
        Log.i(TAG, "Cleared the Purple Name");
    }
}