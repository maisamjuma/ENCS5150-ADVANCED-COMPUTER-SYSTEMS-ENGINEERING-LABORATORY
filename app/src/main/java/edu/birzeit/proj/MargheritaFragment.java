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

public class MargheritaFragment extends Fragment {

    private static final String ARG_PIZZA_IMAGE_RESOURCE = "pizzaImageResource";
    private static final String ARG_PIZZA_NAME = "pizzaName";

    private int pizzaImageResource;
    private String pizzaName;
    private yourfavoritesViewModel favoritesViewModel;

    public MargheritaFragment() {
        // Required empty public constructor
    }

    // Factory method to create a new instance of this fragment
    public static MargheritaFragment newInstance(int pizzaImageResource, String pizzaName) {
        MargheritaFragment fragment = new MargheritaFragment();
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
        View rootView = inflater.inflate(R.layout.activity_margherita_fragment, container, false);

        // Find the heartButton within the inflated view
        ImageButton heartButton = rootView.findViewById(R.id.heartButton);
        final TransitionDrawable transitionDrawable = (TransitionDrawable) heartButton.getDrawable();

        // Find the root layout
        RelativeLayout rootLayout = rootView.findViewById(R.id.margheritaRootLayout);

        // Set an OnClickListener on the root layout to consume the click event
        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Consume the click event without performing any action
            }
        });

        // Restore favorite status when the fragment is created or resumed
        boolean isFavorite = restoreFavoriteStatusFromSharedPreferences("margherita");
        if (isFavorite) {
            transitionDrawable.startTransition(0); // Fill the heart icon
        }

        heartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toggle favorite status
                boolean isFavorite = toggleFavoriteStatus("margherita");
                // Update SharedPreferences with the new favorite status
                saveFavoriteStatusToSharedPreferences("margherita", isFavorite); // Pass pizza type
                // Start the transition animation based on the new favorite status
                if (isFavorite) {
                    transitionDrawable.startTransition(100); // Start from the empty to full state
                    addToFavorites("margherita");
                } else {
                    transitionDrawable.reverseTransition(100); // Start from the full to empty state
                    removeFromFavorites("margherita");
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
    private boolean toggleFavoriteStatus(String pizzaType) {
        boolean isCurrentlyFavorite = restoreFavoriteStatusFromSharedPreferences(pizzaType);
        boolean newFavoriteStatus = !isCurrentlyFavorite;
        saveFavoriteStatusToSharedPreferences(pizzaType, newFavoriteStatus);
        return newFavoriteStatus;
    }


    private boolean restoreFavoriteStatusFromSharedPreferences(String pizzaType) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyFavorites", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("favorite_" + pizzaType + "_status", false);
    }

    private void saveFavoriteStatusToSharedPreferences(String pizzaType, boolean isFavorite) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyFavorites", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("favorite_" + pizzaType + "_status", isFavorite);
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