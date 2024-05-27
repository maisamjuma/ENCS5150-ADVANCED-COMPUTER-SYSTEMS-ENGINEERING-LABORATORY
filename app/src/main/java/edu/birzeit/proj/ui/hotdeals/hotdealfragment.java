package edu.birzeit.proj.ui.hotdeals;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.birzeit.proj.databinding.FregmentHotdealsBinding;


public class hotdealfragment extends Fragment {

    private FregmentHotdealsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hotdealViewModel HotdealViewModel =
                new ViewModelProvider(this).get(hotdealViewModel.class);

        binding = FregmentHotdealsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}