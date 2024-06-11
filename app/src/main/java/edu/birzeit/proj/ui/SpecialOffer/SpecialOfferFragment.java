package edu.birzeit.proj.ui.SpecialOffer;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;

import java.util.HashMap;
import java.util.Map;

import edu.birzeit.proj.R;

public class SpecialOfferFragment extends Fragment {

    private SpecialOfferViewModel mViewModel;

    private CheckBox smallCheckBox, mediumCheckBox, largeCheckBox;
    private EditText priceEditText, dueDateEditText;

    private Button addButton;

    public static SpecialOfferFragment newInstance() {
        return new SpecialOfferFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special_offer, container, false);

        smallCheckBox = view.findViewById(R.id.checkBox4);
        mediumCheckBox = view.findViewById(R.id.checkBox6);
        largeCheckBox = view.findViewById(R.id.checkBox5);
        priceEditText = view.findViewById(R.id.editTextText2);
        dueDateEditText = view.findViewById(R.id.editTextDate);
        addButton = view.findViewById(R.id.button2);

        //all pizza types for the spinner
        String[] pizzaTypes = {"Pepperoni", "Barbecue", "Buffalo", "Calzone", "Hawaiian", "Margherita", "Mushroom", "Neapolitan",
                "New York", "Pesto", "Seafood", "Tandoori", "Vegetarian"};

        final Spinner pizzaTypeSpinner = view.findViewById(R.id.spinner2);
        ArrayAdapter<String> pizzaTypeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, pizzaTypes);
        pizzaTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaTypeSpinner.setAdapter(pizzaTypeAdapter);

        // Map to hold pizza type and available sizes
        final Map<String, boolean[]> pizzaSizesMap = new HashMap<>();
        pizzaSizesMap.put("Pepperoni", new boolean[]{true, false, true}); // Small, large
        pizzaSizesMap.put("Barbecue", new boolean[]{true, false, true}); // Small, large
        pizzaSizesMap.put("Buffalo", new boolean[]{true, true, false}); // Small, medium
        pizzaSizesMap.put("Calzone", new boolean[]{true, false, true}); // Small, large
        pizzaSizesMap.put("Hawaiian", new boolean[]{true, false, false}); // Small
        pizzaSizesMap.put("Margherita", new boolean[]{true, false, false}); // Small
        pizzaSizesMap.put("Mushroom", new boolean[]{true, true, false}); // Small, medium
        pizzaSizesMap.put("Neapolitan", new boolean[]{true, true, false}); // Small, medium
        pizzaSizesMap.put("New York", new boolean[]{true, true, true}); // Small, medium, large
        pizzaSizesMap.put("Pesto", new boolean[]{true, true, true}); // Small, medium, large
        pizzaSizesMap.put("Seafood", new boolean[]{true, true, false}); // Small, medium
        pizzaSizesMap.put("Tandoori", new boolean[]{true, true, true}); // Small, medium, large
        pizzaSizesMap.put("Vegetarian", new boolean[]{true, true, false}); // Small, medium


        pizzaTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedPizza = pizzaTypeSpinner.getSelectedItem().toString();
                boolean[] availableSizes = pizzaSizesMap.get(selectedPizza);

                if (availableSizes != null) {
                    smallCheckBox.setVisibility(availableSizes[0] ? View.VISIBLE : View.GONE);
                    mediumCheckBox.setVisibility(availableSizes[1] ? View.VISIBLE : View.GONE);
                    largeCheckBox.setVisibility(availableSizes[2] ? View.VISIBLE : View.GONE);

                    // Reset checkboxes
                    smallCheckBox.setChecked(false);
                    mediumCheckBox.setChecked(false);
                    largeCheckBox.setChecked(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Set up listeners to ensure only one checkbox can be selected at a time
        smallCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mediumCheckBox.setChecked(false);
                    largeCheckBox.setChecked(false);
                }
            }
        });

        mediumCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    smallCheckBox.setChecked(false);
                    largeCheckBox.setChecked(false);
                }
            }
        });

        largeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    smallCheckBox.setChecked(false);
                    mediumCheckBox.setChecked(false);
                }
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collect input data
                String selectedPizza = pizzaTypeSpinner.getSelectedItem().toString();
                String price = priceEditText.getText().toString();
                String dueDate = dueDateEditText.getText().toString();
                String size = "";
                if (smallCheckBox.isChecked()) {
                    size = "Small";
                } else if (mediumCheckBox.isChecked()) {
                    size = "Medium";
                } else if (largeCheckBox.isChecked()) {
                    size = "Large";
                }

                // Create bundle to pass data
                Bundle bundle = new Bundle();
                bundle.putString("pizzaType", selectedPizza);
                bundle.putString("price", price);
                bundle.putString("dueDate", dueDate);
                bundle.putString("size", size);

                // Navigate to HotDealsFragment
               // Navigation.findNavController(view).navigate(R.id.action_specialOfferFragment_to_hotDealsFragment, bundle);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SpecialOfferViewModel.class);
        // TODO: Use the ViewModel
    }
}
