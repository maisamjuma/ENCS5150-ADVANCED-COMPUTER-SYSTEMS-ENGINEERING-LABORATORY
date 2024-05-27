package edu.birzeit.proj.ui.hotdeals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class hotdealViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public hotdealViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}