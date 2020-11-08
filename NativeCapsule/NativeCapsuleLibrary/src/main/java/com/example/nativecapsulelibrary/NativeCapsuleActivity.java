package com.example.nativecapsulelibrary;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class NativeCapsuleActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.native_capsule_layout);
    }
}
