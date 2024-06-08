package edu.birzeit.proj;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.birzeit.proj.R;
import edu.birzeit.proj.ui.yourfavorites.yourfavoritesViewModel;

public class VegetarianFragment extends Fragment {

    private static final String ARG_PIZZA_IMAGE_RESOURCE = "pizzaImageResource";
    private static final String ARG_PIZZA_NAME = "pizzaName";

    private int pizzaImageResource;
    private String pizzaName;
    private yourfavoritesViewModel favoritesViewModel;


    public VegetarianFragment() {
        // Required empty public constructor
    }

    // Factory method to create a new instance of this fragment
    public static PepperoniFragment newInstance(int pizzaImageResource, String pizzaName) {
        PepperoniFragment fragment = new PepperoniFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PIZZA_IMAGE_RESOURCE, pizzaImageResource);
        args.putString(ARG_PIZZA_NAME, pizzaName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pizzaImageResource = getArguments().getInt(ARG_PIZZA_IMAGE_RESOURCE);
            pizzaName = getArguments().getString(ARG_PIZZA_NAME);
        }
        // Initialize the yourfavoritesViewModel instance
        favoritesViewModel = new ViewModelProvider(requireActivity()).get(yourfavoritesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_vegetarian_fragment, container, false);

        // Find the heartButton within the inflated view
        ImageButton heartButton = rootView.findViewById(R.id.heartButton);
        final TransitionDrawable transitionDrawable = (TransitionDrawable) heartButton.getDrawable();

        // Find the root layout
        RelativeLayout rootLayout = rootView.findViewById(R.id.vegeRootLayout);

        // Set an OnClickListener on the root layout to consume the click event
        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Consume the click event without performing any action
            }
        });

        // Restore favorite status when the fragment is created or resumed
        boolean isFavorite = restoreFavoriteStatusFromSharedPreferences();
        if (isFavorite) {
            transitionDrawable.startTransition(0); // Fill the heart icon
        }

        heartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toggle favorite status
                boolean isFavorite = toggleFavoriteStatus();
                // Update SharedPreferences with the new favorite status
                saveFavoriteStatusToSharedPreferences(isFavorite);
                // Start the transition animation
                if (isFavorite) {
                    transitionDrawable.startTransition(100);
                    addToFavorites("vegetarian");
                } else {
                    transitionDrawable.reverseTransition(100);
                    removeFromFavorites("vegetarian");
                }
            }
        });

        // Find the button by its ID
        Button custom = rootView.findViewById(R.id.custom);

        // Set a click listener for the button
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the dialog fragment
                dialogFragment dialogFragment = new dialogFragment();
                dialogFragment.show(getChildFragmentManager(), "MyDialogFragment");
            }
        });

        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // You can use the pizzaImageResource and pizzaName variables here to update UI elements
        // For example:
        // ImageView imageView = view.findViewById(R.id.imageView);
        // TextView textView = view.findViewById(R.id.textView);
        // imageView.setImageResource(pizzaImageResource);
        // textView.setText(pizzaName);
    }
    private boolean toggleFavoriteStatus() {
        // Retrieve the current favorite status
        boolean isCurrentlyFavorite = restoreFavoriteStatusFromSharedPreferences();

        // Toggle the favorite status
        boolean newFavoriteStatus = !isCurrentlyFavorite;

        // Save the updated favorite status to SharedPreferences
        saveFavoriteStatusToSharedPreferences(newFavoriteStatus);

        // Return the new favorite status
        return newFavoriteStatus;
    }

    private boolean restoreFavoriteStatusFromSharedPreferences() {
        // Retrieve favorite status from SharedPreferences
        // Use the context to access the SharedPreferences instance
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyFavorites", Context.MODE_PRIVATE);
        // Use the key to retrieve the favorite status, assuming the key is "favorite_pizza_status"
        // If the key doesn't exist, return false indicating that the pizza is not a favorite
        return sharedPreferences.getBoolean("favorite_pizza_status", true);
    }

    private void saveFavoriteStatusToSharedPreferences(boolean isFavorite) {
        // Save favorite status to SharedPreferences
        // Use the context to access the SharedPreferences instance
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyFavorites", Context.MODE_PRIVATE);
        // Use the editor to modify SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Save the favorite status with the key "favorite_pizza_status"
        editor.putBoolean("favorite_pizza_status", isFavorite);
        // Apply the changes
        editor.apply();
    }

    // Method to add a pizza to favorites
    private void addToFavorites(String pizzaName) {
        // Call the ViewModel method to add the pizza to favorites
        favoritesViewModel.addToFavorites(pizzaName);
    }
    // Method to remove a pizza from favorites
    private void removeFromFavorites(String pizzaName) {
        // Call the ViewModel method to remove the pizza from favorites
        favoritesViewModel.removeFromFavorites(pizzaName);
    }

}
