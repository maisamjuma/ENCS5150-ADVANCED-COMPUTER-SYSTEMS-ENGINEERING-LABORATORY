package edu.birzeit.proj.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.birzeit.proj.databinding.FregmentLogoutBinding;
import edu.birzeit.proj.login_Activity;

public class logoutfragment extends Fragment {

    private FregmentLogoutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FregmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.logout;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void navigateToLogin() {
        // Start the LoginActivity
        Intent intent = new Intent(getActivity(), login_Activity.class);
        startActivity(intent);

        // Finish the current activity
        getActivity().finish();
    }
}
