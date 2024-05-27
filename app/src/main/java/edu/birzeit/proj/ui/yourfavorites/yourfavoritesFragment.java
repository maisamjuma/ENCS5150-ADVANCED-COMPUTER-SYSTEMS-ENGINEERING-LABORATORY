package edu.birzeit.proj.ui.yourfavorites;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.birzeit.proj.databinding.FregmentYourfavoritesBinding;


public class yourfavoritesFragment extends Fragment {

    private FregmentYourfavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        yourfavoritesViewModel YourfavoritesViewModel =
                new ViewModelProvider(this).get(yourfavoritesViewModel.class);

        binding = FregmentYourfavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.favorites;
        YourfavoritesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
