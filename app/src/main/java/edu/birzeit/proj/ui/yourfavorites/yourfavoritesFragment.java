package edu.birzeit.proj.ui.yourfavorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import edu.birzeit.proj.R;
import edu.birzeit.proj.databinding.FregmentYourfavoritesBinding;

public class yourfavoritesFragment extends Fragment {

    private yourfavoritesViewModel favoritesViewModel;
    private FregmentYourfavoritesBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoritesViewModel = new ViewModelProvider(requireActivity()).get(yourfavoritesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FregmentYourfavoritesBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        final TextView favoritesTextView = binding.favoritesTextView;

        favoritesViewModel.getFavoritePizzas().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> favoritePizzas) {
                // Update the UI with the list of favorite pizzas
                StringBuilder stringBuilder = new StringBuilder();
                for (String pizza : favoritePizzas) {
                    stringBuilder.append(pizza).append("\n");
                }
                favoritesTextView.setText(stringBuilder.toString());
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
