package edu.birzeit.proj.ui.yourfavorites;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class yourfavoritesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public yourfavoritesViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}