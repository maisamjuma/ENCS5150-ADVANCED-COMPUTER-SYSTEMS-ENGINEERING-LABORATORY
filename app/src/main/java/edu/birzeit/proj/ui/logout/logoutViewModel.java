package edu.birzeit.proj.ui.logout;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class logoutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public logoutViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}