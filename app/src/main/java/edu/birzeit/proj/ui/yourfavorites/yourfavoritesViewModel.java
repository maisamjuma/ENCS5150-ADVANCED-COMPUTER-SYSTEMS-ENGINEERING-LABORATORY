package edu.birzeit.proj.ui.yourfavorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class yourfavoritesViewModel extends ViewModel {

    // Map to hold favorite pizzas for each user
    private Map<String, MutableLiveData<List<String>>> userFavoritePizzasMap;

    public yourfavoritesViewModel() {
        userFavoritePizzasMap = new HashMap<>();
    }

    public LiveData<List<String>> getFavoritePizzas(String userEmail) {
        if (!userFavoritePizzasMap.containsKey(userEmail)) {
            MutableLiveData<List<String>> favoritesLiveData = new MutableLiveData<>();
            favoritesLiveData.setValue(new ArrayList<>());
            userFavoritePizzasMap.put(userEmail, favoritesLiveData);
        }
        return userFavoritePizzasMap.get(userEmail);
    }


    public void addToFavorites(String userEmail, String pizzaName) {
        MutableLiveData<List<String>> favoritesLiveData = userFavoritePizzasMap.get(userEmail);
        if (favoritesLiveData != null) {
            List<String> pizzas = favoritesLiveData.getValue();
            if (pizzas == null) {
                pizzas = new ArrayList<>();
            }
            pizzas.add(pizzaName);
            favoritesLiveData.setValue(pizzas);
        }
    }

    public void removeFromFavorites(String userEmail, String pizzaName) {
        MutableLiveData<List<String>> favoritesLiveData = userFavoritePizzasMap.get(userEmail);
        if (favoritesLiveData != null) {
            List<String> pizzas = favoritesLiveData.getValue();
            if (pizzas != null) {
                pizzas.remove(pizzaName);
                favoritesLiveData.setValue(pizzas);
            }
        }
    }
}