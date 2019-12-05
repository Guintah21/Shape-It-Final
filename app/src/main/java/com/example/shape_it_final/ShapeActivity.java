package com.example.shape_it_final;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ShapeActivity extends AppCompatActivity {

    //for logging and debugging
    String TAG = "SHAPEIT ShapeActivity";

    //Global declarations to manipulate textView and imageButton
    TextView shapeName;
    ImageButton shapeButton;

    //Global declarations to manipulate gameItem and shapeFactory
    GameItem gameItem;
    ShapeFactory shapeFactory;


    //This begins our gameActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //just logging for debugging
        Log.i(TAG, "Started ShapeActivity");

        //getting started
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);

        //commented out because we are using random shapes and don't need to save state of shape
        //saves the state of our game and shape by recording the ID of the current shape
        /*SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        currentShapeID = pref.getInt("currentShape", 0);*/

        //sets up the variables of the Button and the Name text field
        shapeButton = findViewById(R.id.imageButton);
        shapeName = findViewById(R.id.textView);

        //logging for debugging
        Log.i(TAG, "Instantiate a shapeFactory");

        //initializing our shapeFactory
        shapeFactory = new ShapeFactory();

        //Create our game item using our factory random generator method
        gameItem = shapeFactory.getShape(shapeButton, shapeName);

        //logging for debugging
        Log.i(TAG, "Got a game item " + gameItem);

        //calling the draw function to start our first shape
        gameItem.draw();

        //This listens for a click on our shape
        shapeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //shows the name above the shape image and starts the sound file
                //1
                gameItem.showsName();
                //2
                gameItem.saysName(getApplicationContext());

                //begins a pause to allow for sound to play before updating shape
                Runnable r = new Runnable() {
                    @Override
                    public void run(){

                        //After 3 seconds, our shape will update and we will move to the next
                        //gameItem
                        //4
                        gameItem = shapeFactory.getShape(shapeButton, shapeName);
                        if (gameItem != null) {
                            gameItem.clearName();
                            gameItem.draw();
                        }
                    }
                };

                //3 helps handled the delay
                Handler h = new Handler();
                h.postDelayed(r, 3000); // <-- the "3000" is the delay time in milliseconds.
            }
        });
    }

    //commented out because we are using random shapes
    //if our game is paused, this will save our ShapeID so we know our current state
    /*@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void saveCurrentShape() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("currentShape", currentShapeID);
        editor.commit();
    }*/


}
