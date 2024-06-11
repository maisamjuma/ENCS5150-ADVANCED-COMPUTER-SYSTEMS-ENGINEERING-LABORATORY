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
// pizzaTypeTextView = view.findViewById(R.id.pizzaTypeTextView);
//        priceTextView = view.findViewById(R.id.priceTextView);
//        dueDateTextView = view.findViewById(R.id.dueDateTextView);
//        sizeTextView = view.findViewById(R.id.sizeTextView);
//
//        if (getArguments() != null) {
//            String pizzaType = getArguments().getString("pizzaType");
//            String price = getArguments().getString("price");
//            String dueDate = getArguments().getString("dueDate");
//            String size = getArguments().getString("size");
//
//            pizzaTypeTextView.setText(pizzaType);
//            priceTextView.setText(price);
//            dueDateTextView.setText(dueDate);
//            sizeTextView.setText(size);
//        }