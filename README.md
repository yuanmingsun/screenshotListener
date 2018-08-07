#### 监听截屏事件

...
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.haixue.screenshottest">
    <!--读取外部存储卡-->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
...
...

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
...
