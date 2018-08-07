package com.haixue.screenshottest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {

    private ImageView ivScreenShot;

    private ScreenShotListenManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivScreenShot=findViewById(R.id.iv_screenshot);
        initListener();
    }

    private void initListener(){
        manager = ScreenShotListenManager.newInstance(this);
        manager.startListen();
        manager.setListener(
                new ScreenShotListenManager.OnScreenShotListener() {
                    @Override
                    public void onShot(String imagePath) {
                        // do something
                        ivScreenShot.setImageURI(Uri.fromFile(new File(imagePath)));
                    }
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.stopListen();
    }
}
