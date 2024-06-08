package edu.birzeit.proj.ui.SpecialOffer;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import edu.birzeit.proj.R;

public class SpecialOfferFragment extends Fragment {

    private SpecialOfferViewModel mViewModel;

    public static SpecialOfferFragment newInstance() {
        return new SpecialOfferFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special_offer, container, false);

        String[] options = {"Pepperoni", "Barbecue","Buffalo","Calzone","Hawaiian","Margherita","Mushroom","Neapolitan",
        "NewYork","Pesto","Seafood","Tandoori","Vegetarian"};
        final Spinner genderSpinner = view.findViewById(R.id.spinner2);
        ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, options);
        genderSpinner.setAdapter(objGenderArr);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SpecialOfferViewModel.class);
        // TODO: Use the ViewModel
    }
}
