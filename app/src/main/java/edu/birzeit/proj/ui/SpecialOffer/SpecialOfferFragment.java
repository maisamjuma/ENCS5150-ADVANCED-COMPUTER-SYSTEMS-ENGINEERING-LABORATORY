package edu.birzeit.proj.ui.SpecialOffer;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import edu.birzeit.proj.DataBaseHelper_offer;
import edu.birzeit.proj.Offer;
import edu.birzeit.proj.R;
import java.util.Calendar;

public class SpecialOfferFragment extends Fragment {

    private SpecialOfferViewModel mViewModel;
    private CheckBox smallCheckBox, mediumCheckBox, largeCheckBox;
    private EditText priceEditText, dayEditText, monthEditText, yearEditText;
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
        dayEditText = view.findViewById(R.id.editTextDay);
        monthEditText = view.findViewById(R.id.editTextMonth);
        yearEditText = view.findViewById(R.id.editTextYear);
        addButton = view.findViewById(R.id.button2);

        // All pizza types for the spinner
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
                String selectedPizza = pizzaTypeSpinner.getSelectedItem().toString();
                String price = priceEditText.getText().toString();
                String size = "";

                if (smallCheckBox.isChecked()) {
                    size = "S";
                } else if (mediumCheckBox.isChecked()) {
                    size = "M";
                } else if (largeCheckBox.isChecked()) {
                    size = "L";
                }

                if (!validateDate()) {
                    return;
                }

                String dayStr = dayEditText.getText().toString().trim();
                String monthStr = monthEditText.getText().toString().trim();
                String yearStr = yearEditText.getText().toString().trim();
                String dueDate = dayStr + "/" + monthStr + "/" + yearStr;

                Offer offer = new Offer();
                offer.setType(selectedPizza);
                offer.setSize(size);
                offer.setPrize(Float.parseFloat(price));
                offer.setDate(dueDate);

                DataBaseHelper_offer dataBaseHelper = new DataBaseHelper_offer(getActivity(), "Offer", null, 1);
                dataBaseHelper.insertOffer(offer);
                Toast.makeText(getActivity(), "Offer Added", Toast.LENGTH_SHORT).show();

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

    private boolean validateDate() {
        String dayStr = dayEditText.getText().toString().trim();
        String monthStr = monthEditText.getText().toString().trim();
        String yearStr = yearEditText.getText().toString().trim();

        if (dayStr.isEmpty() || monthStr.isEmpty() || yearStr.isEmpty()) {
            showToast("All date fields must be filled");
            return false;
        }

        int day, month, year;

        try {
            day = Integer.parseInt(dayStr);
            month = Integer.parseInt(monthStr);
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            showToast("Invalid date input");
            return false;
        }

        if (!isValidDate(day, month, year)) {
            showToast("Invalid date");
            return false;
        }

        Calendar current = Calendar.getInstance();
        Calendar inputDate = Calendar.getInstance();
        inputDate.set(year, month - 1, day);

        if (inputDate.before(current)) {
            showToast("Date must be today or later");
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }

        int[] daysInMonth = {31, (isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        return day >= 1 && day <= daysInMonth[month - 1];
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}