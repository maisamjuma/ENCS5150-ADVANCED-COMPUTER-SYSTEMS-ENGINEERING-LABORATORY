package edu.birzeit.proj.ui.slideshow;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SlideshowViewModel extends ViewModel {

    private static MutableLiveData<List<String>> mText;
    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(new ArrayList<>());
    }

    public LiveData<List<String>> getOrder() {
        return mText;
    }    // TODO: Implement the ViewModel
}