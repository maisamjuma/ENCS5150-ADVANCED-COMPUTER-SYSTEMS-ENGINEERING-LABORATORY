package edu.birzeit.proj.ui.SpecialOffer;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.birzeit.proj.R;

public class SpecialOfferFragment extends Fragment {

    private SpecialOfferViewModel mViewModel;

    public static SpecialOfferFragment newInstance() {
        return new SpecialOfferFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_special_offer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SpecialOfferViewModel.class);
        // TODO: Use the ViewModel
    }

}