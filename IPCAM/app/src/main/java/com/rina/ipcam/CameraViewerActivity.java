package com.rina.ipcam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jmedeisis.bugstick.Joystick;
import com.jmedeisis.bugstick.JoystickListener;

public class CameraViewerActivity extends AppCompatActivity {

    private Joystick joystick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        window.setBackgroundDrawableResource(android.R.color.black);
        setContentView(R.layout.activity_camera_viewer);

        joystick = (Joystick)findViewById(R.id.joystick);
        joystick.setJoystickListener(new JoystickListener() {
            @Override
            public void onDown() {
                Toast.makeText(getApplicationContext(),"Down",Toast.LENGTH_SHORT);
                Log.d("Down", "onDown: ");
            }

            @Override
            public void onDrag(float degrees, float offset) {
                Log.d("degree", ""+degrees);
            }

            @Override
            public void onUp() {
                Log.d("UP", "Up: ");
            }
        });
    }

}
