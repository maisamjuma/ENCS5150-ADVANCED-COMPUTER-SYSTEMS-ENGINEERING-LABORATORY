package edu.birzeit.proj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.birzeit.proj.R;
import edu.birzeit.proj.ui.slideshow.SlideshowViewModel;
import edu.birzeit.proj.ui.yourfavorites.yourfavoritesViewModel;

public class PepperoniFragment extends Fragment {

    private static final String ARG_PIZZA_IMAGE_RESOURCE = "pizzaImageResource";
    private static final String ARG_PIZZA_NAME = "pizzaName";
    SharedPrefManager sharedPrefManager3;
    SharedPrefManager sharedPrefManager_for_extra;
    String s;
    private SlideshowViewModel ViewOrder;
    private int pizzaImageResource;
    private String pizzaName;
    private yourfavoritesViewModel favoritesViewModel;


    public PepperoniFragment() {
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
//        sharedPrefManager_for_extra=SharedPrefManager.getInstance(getActivity());
        sharedPrefManager3=SharedPrefManager.getInstance(getActivity());
        sharedPrefManager_for_extra=SharedPrefManager.getInstance(getActivity());


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
        View rootView = inflater.inflate(R.layout.activity_pepperoni_fragment, container, false);

        // Find the heartButton within the inflated view
        ImageButton heartButton = rootView.findViewById(R.id.heartButton);
        final TransitionDrawable transitionDrawable = (TransitionDrawable) heartButton.getDrawable();

        Button submit = rootView.findViewById(R.id.submit);
        RadioGroup choiceRadioGroup = rootView.findViewById(R.id.choiceRadioGroup);
        RadioButton Rsmall = rootView.findViewById(R.id.radioSmall);
        RadioButton Rlarge = rootView.findViewById(R.id.radioLarge);


        // Find the root layout
        RelativeLayout rootLayout = rootView.findViewById(R.id.pepperoniRootLayout);

        // Set an OnClickListener on the root layout to consume the click event
        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        choiceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button is selected
                if (checkedId == R.id.radioSmall) {
                    Rsmall.setText(" S    $22.0");
                    s=Rsmall.getText().toString();
                } else if (checkedId == R.id.radioLarge) {
                    Rlarge.setText(" L    $34.0");
                    s=Rlarge.getText().toString();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choiceRadioGroup.getCheckedRadioButtonId() == -1) {
                    // No radio button is checked, display a message
                    Toast.makeText(getActivity(), "Please select a Size of Pizza", Toast.LENGTH_SHORT).show();
                } else {
                    Order_pizza newone = new Order_pizza();
                    String emailUSER = sharedPrefManager3.readString("Email_user", "");
                    String extra = sharedPrefManager_for_extra.readString("extra", "");
                    SharedPreferences preferences = getActivity().getSharedPreferences("sharedPrefManager_for_extra", 0);
                    preferences.edit().clear().commit();
                    // Clear the default value when the fragment is created
                    sharedPrefManager_for_extra.writeString("extra", "");
                    DataBaseHelper_Order dataBaseHelperOrder = new
                            DataBaseHelper_Order(getActivity(), "order_pizza", null, 1);
                    newone.setEmail(emailUSER);//                newone.setExtra("Extra_order");
                    newone.setOrderr(" ");
                    dataBaseHelperOrder.insertOrder(newone);
                    Cursor allOrderCursor = dataBaseHelperOrder.SearchforOrder(emailUSER);
                    if (allOrderCursor != null && allOrderCursor.moveToFirst()) {
                        Cursor cursor = dataBaseHelperOrder.SearchforOrder(emailUSER);
                        if (cursor != null) {
                            try {
                                // Get the index of the "Ord" column
                                int ordColumnIndex = cursor.getColumnIndex("Ord");

                                if (ordColumnIndex != -1) {
                                    while (cursor.moveToNext()) {
                                        String order = cursor.getString(ordColumnIndex);
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                        String currentDate = dateFormat.format(new Date());

                                        dataBaseHelperOrder.settingOrder(emailUSER, order);
                                        if (extra == null || extra.isEmpty()) {
                                            order += "\nPepperoni" + s + " no Extra\n"+ "ordered in:"+ currentDate +"\n\n";
                                        }
                                        else {
                                            order += "\nPepperoni" + s +"  "+ extra +"\n" +"ordered in:"+ currentDate+"\n\n";
                                        }
                                        dataBaseHelperOrder.settingOrder(emailUSER, order);
                                    }

                                }

                            } finally {
                                cursor.close();  // Always close the cursor to avoid memory leaks
                            }
                        }

                    }

                    Toast.makeText(getActivity(), "Added To Your Orders", Toast.LENGTH_LONG).show();

                }
            }
        });

        // Restore favorite status when the fragment is created or resumed
        boolean isFavorite = restoreFavoriteStatusFromSharedPreferences("pepperoni");
        if (isFavorite) {
            transitionDrawable.startTransition(0); // Fill the heart icon
        }

        heartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toggle favorite status
                boolean isFavorite = toggleFavoriteStatus("pepperoni");
                // Update SharedPreferences with the new favorite status
                saveFavoriteStatusToSharedPreferences("pepperoni",isFavorite);

                // Start the transition animation
                if (isFavorite) {
                    transitionDrawable.startTransition(100);
                    addToFavorites("pepperoni");
                } else {
                    transitionDrawable.reverseTransition(100);
                    removeFromFavorites("pepperoni");
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

    // Method to remove a pizza from favorites
    private void removeFromFavorites(String pizzaName) {
        // Call the ViewModel method to remove the pizza from favorites
        String userEmail = getCurrentUserEmail();
        favoritesViewModel.removeFromFavorites(userEmail, pizzaName);

    }

    private String getCurrentUserEmail() {
        return sharedPrefManager3.readString("Email_user", "");
    }

    private void addToFavorites(String pizzaName) {
        String userEmail = getCurrentUserEmail();
        favoritesViewModel.addToFavorites(userEmail, pizzaName);
    }

}