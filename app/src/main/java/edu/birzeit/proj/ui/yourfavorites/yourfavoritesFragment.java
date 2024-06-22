package edu.birzeit.proj.ui.yourfavorites;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.birzeit.proj.BarbecueFragment;
import edu.birzeit.proj.BuffaloFragment;
import edu.birzeit.proj.CalzoneFragment;
import edu.birzeit.proj.DataBaseHelper;
import edu.birzeit.proj.HawaiianFragment;
import edu.birzeit.proj.MargheritaFragment;
import edu.birzeit.proj.MushroomFragment;
import edu.birzeit.proj.NeapolitanFragment;
import edu.birzeit.proj.NewYorkFragment;
import edu.birzeit.proj.PepperoniFragment;
import edu.birzeit.proj.PestoFragment;
import edu.birzeit.proj.R;
import edu.birzeit.proj.SharedPrefManager;
import edu.birzeit.proj.TandooriFragment;
import edu.birzeit.proj.VegetarianFragment;
import edu.birzeit.proj.databinding.FregmentYourfavoritesBinding;
import edu.birzeit.proj.neww;
import edu.birzeit.proj.seafoodFragment;

public class yourfavoritesFragment extends Fragment {

    private yourfavoritesViewModel favoritesViewModel;
    private FregmentYourfavoritesBinding binding;
    private LinearLayout favoritesContainer;

    SharedPrefManager sharedPrefManager3;
    private DataBaseHelper dbHelper;
    private String currentUserEmail;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //favoritesViewModel = new ViewModelProvider(requireActivity()).get(yourfavoritesViewModel.class);
        sharedPrefManager3=SharedPrefManager.getInstance(getActivity());
        currentUserEmail=sharedPrefManager3.readString("Email_user", "");
        favoritesViewModel = new ViewModelProvider(requireActivity()).get(yourfavoritesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FregmentYourfavoritesBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        favoritesContainer = binding.favoritesContainer;


        favoritesViewModel.getFavoritePizzas(currentUserEmail).observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> favoritePizzas) {
                loadFavoritePizzasFromPrefs();
                displayFavoritePizzas(favoritePizzas);
            }
        });
        return rootView;
    }

    private void displayFavoritePizzas(List<String> favoritePizzas) {
        favoritesContainer.removeAllViews();

        for (String pizza : favoritePizzas) {
            // Create a LinearLayout to wrap the TextView and add a bottom margin
            LinearLayout pizzaItemLayout = new LinearLayout(getContext());
            pizzaItemLayout.setOrientation(LinearLayout.VERTICAL);

            // Create a TextView for each favorite pizza item
            TextView pizzaTextView = new TextView(getContext());
            pizzaTextView.setText(pizza);
            pizzaTextView.setTextSize(18);
            pizzaTextView.setPadding(16, 16, 16, 16);
            pizzaTextView.setTypeface(null, Typeface.BOLD); // Set text to bold
            pizzaTextView.setTextColor(getResources().getColor(R.color.black));
            pizzaTextView.setGravity(View.TEXT_ALIGNMENT_CENTER); // Center the text
            pizzaTextView.setBackgroundResource(R.drawable.border); // Set border

            pizzaTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    togglePizzaFragment(pizza);
                }
            });

            // Add the TextView to the LinearLayout
            pizzaItemLayout.addView(pizzaTextView);

            // Create a separator line
            View separator = new View(getContext());
            LinearLayout.LayoutParams separatorParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1
            );
            separatorParams.setMargins(0, 16, 0, 16);
            separator.setLayoutParams(separatorParams);
            separator.setBackgroundColor(getResources().getColor(R.color.black)); // Set separator color

            // Add the separator to the LinearLayout
            pizzaItemLayout.addView(separator);

            // Add the LinearLayout to the container
            favoritesContainer.addView(pizzaItemLayout);
        }
    }

    private void togglePizzaFragment(String pizzaType) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(pizzaType);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (fragment != null && fragment.isVisible()) {
            // Fragment is already open, so remove it
            transaction.remove(fragment);
            fragmentManager.popBackStack(pizzaType, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            // Fragment is not open, so open it
            fragment = getPizzaFragmentByType(pizzaType);
            if (fragment != null) {
                transaction.add(R.id.fragmentContainer, fragment, pizzaType);
                transaction.addToBackStack(pizzaType);
            }
        }

        transaction.commit();
    }

    private Fragment getPizzaFragmentByType(String pizzaType) {
        switch (pizzaType) {
            case "margherita":
                return new MargheritaFragment();
            case "pepperoni":
                return new PepperoniFragment();
            case "hawaiian":
                return new HawaiianFragment();
            case "barbecue":
                return new BarbecueFragment();
            case "Buffalo":
                return new BuffaloFragment();
            case "calzone":
                return new CalzoneFragment();
            case "mushroom":
                return new MushroomFragment();
            case "neapolitan":
                return new NeapolitanFragment();
            case "new york":
                return new NewYorkFragment();
            case "pesto":
                return new PestoFragment();
            case "seafood":
                return new seafoodFragment();
            case "tandoori":
                return new TandooriFragment();
            case "vegetarian":
                return new VegetarianFragment();
            default:
                Log.e("FragmentTransaction", "Unknown pizza type: " + pizzaType);
                return null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        saveFavoritePizzasToPrefs();
    }

    private void saveFavoritePizzasToPrefs() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyFavorites", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Retrieve the current list of favorite pizzas from LiveData
        List<String> favoritePizzasList = favoritesViewModel.getFavoritePizzas(currentUserEmail).getValue();

        // Ensure list is not null before saving
        if (favoritePizzasList != null) {
            Set<String> favoritePizzasSet = new HashSet<>(favoritePizzasList);
            editor.putStringSet(currentUserEmail, favoritePizzasSet);
            editor.apply();
        }
    }



    @Override
    public void onResume() {
        super.onResume();

      // loadFavoritePizzasFromPrefs();

    }

    private void loadFavoritePizzasFromPrefs() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyFavorites", Context.MODE_PRIVATE);
        Set<String> favoritePizzasSet = sharedPreferences.getStringSet(currentUserEmail, null);

        List<String> favoritePizzasList;
        if (favoritePizzasSet != null) {
            favoritePizzasList = new ArrayList<>(favoritePizzasSet);
        } else {
            favoritePizzasList = new ArrayList<>(); // Initialize an empty list if no favorites found
        }

        displayFavoritePizzas(favoritePizzasList);
    }

}


