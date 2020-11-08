package com.prayag.nativecapsule.ui.gallery;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.nativecapsulelibrary.NativeCapsuleActivity;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory;
import com.prayag.nativecapsule.R;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private SplitInstallManager manager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        Button btn = root.findViewById(R.id.directionButton);
        Button nativeBtn = root.findViewById(R.id.nativecapsuleButton);
        manager = SplitInstallManagerFactory.create(getActivity());

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (manager.getInstalledModules().contains("dynamicfeature")) {
                        Intent i = new Intent("com.example.dynamicfeature.DirectionActivity");
                        startActivity(i);
                    } else {
                        System.out.println("no feature");
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        });

        nativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(getActivity(), "com.example.nativecapsulelibrary.NativeCapsuleActivity");
                startActivity(intent);

            }
        });
        return root;
    }
}