package edu.birzeit.proj.ui.yourfavorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class yourfavoritesViewModel extends ViewModel {

    private MutableLiveData<List<String>> favoritePizzas;

    public yourfavoritesViewModel() {
        favoritePizzas = new MutableLiveData<>();
        favoritePizzas.setValue(new ArrayList<>());
    }

    public LiveData<List<String>> getFavoritePizzas() {
        return favoritePizzas;
    }

    public void addToFavorites(String pizzaName) {
        List<String> pizzas = favoritePizzas.getValue();
        if (pizzas == null) {
            pizzas = new ArrayList<>();
        }
        pizzas.add(pizzaName);
        favoritePizzas.setValue(pizzas);
    }

    public void removeFromFavorites(String pizzaName) {
        List<String> pizzas = favoritePizzas.getValue();
        if (pizzas != null) {
            pizzas.remove(pizzaName);
            favoritePizzas.setValue(pizzas);
        }
    }
}
