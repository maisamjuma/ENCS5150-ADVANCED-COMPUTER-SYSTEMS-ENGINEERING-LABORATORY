package edu.birzeit.proj.ui.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.birzeit.proj.NewdialogFragment;
import edu.birzeit.proj.R;
import edu.birzeit.proj.databinding.FregmentProfileBinding;
import edu.birzeit.proj.dialogFragment;


public class profileFragment extends Fragment {

    Button EditProfile;

    private FregmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel ProfileViewModel =
                new ViewModelProvider(this).get(profileViewModel.class);

        binding = FregmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button EditProfile = root.findViewById(R.id.button);

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
}
