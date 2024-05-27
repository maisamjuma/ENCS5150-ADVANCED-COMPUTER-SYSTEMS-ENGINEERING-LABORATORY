package edu.birzeit.proj.ui.notification;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class notificationViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public notificationViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}