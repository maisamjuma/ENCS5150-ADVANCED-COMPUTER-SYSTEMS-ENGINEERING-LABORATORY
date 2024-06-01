package edu.birzeit.proj.ui.profile;


import static android.app.Activity.RESULT_OK;

import android.content.ContentProvider;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.birzeit.proj.NewdialogFragment;
import edu.birzeit.proj.R;
import edu.birzeit.proj.databinding.FregmentProfileBinding;
import edu.birzeit.proj.dialogFragment;


public class profileFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_PERMISSION = 2;
    private static int GALLERY_REQ_CODE = 1000;

    ImageView imageView;
    Button EditProfile;
    private FregmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel ProfileViewModel =
                new ViewModelProvider(this).get(profileViewModel.class);

        binding = FregmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        imageView = root.findViewById(R.id.imageView6);

        Button EditProfile = root.findViewById(R.id.button);
        Button choose_photo = root.findViewById(R.id.change_photo);

        choose_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Intent.ACTION_PICK);
                intents.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intents, GALLERY_REQ_CODE);
            }
        });
        // Set a click listener for the button
        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the dialog fragment
                NewdialogFragment dialogFragment = new NewdialogFragment();
                dialogFragment.show(getChildFragmentManager(), "MyDialogFragment");

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE ) {
                imageView.setImageURI(data.getData());
            }
        }
    }
}