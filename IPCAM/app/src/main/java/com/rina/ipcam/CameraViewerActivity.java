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
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.setBackgroundDrawableResource(android.R.color.black);

        setContentView(R.layout.activity_camera_viewer);

        joystick = (Joystick)findViewById(R.id.joystick);
        joystick.setJoystickListener(new JoystickListener() {
            @Override
            public void onDown() {}
            @Override
            public void onDrag(float degrees, float offset) {
                if(degrees >= 45 && degrees <= 135 ){
                    Log.d("UP", "Up");
                }
                else if (degrees >= -224 && degrees <= -46 ) {
                    Log.d("DOWN", "DOWN");
                }
                else if (degrees <=44 && degrees >= -44 ) {
                    Log.d("RIGHT", "RIGHT");
                }
                else if (degrees >= 136 || degrees <= -225 ) {
                    Log.d("Left", "Left"+degrees);
                }
            }
            @Override
            public void onUp() {}
        });
    }
}
