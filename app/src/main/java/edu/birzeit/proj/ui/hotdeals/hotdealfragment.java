package edu.birzeit.proj.ui.hotdeals;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.birzeit.proj.DataBaseHelper;
import edu.birzeit.proj.DataBaseHelper_Order;
import edu.birzeit.proj.DataBaseHelper_offer;
import edu.birzeit.proj.Order_pizza;
import edu.birzeit.proj.SharedPrefManager;
import edu.birzeit.proj.databinding.FregmentHotdealsBinding;

public class hotdealfragment extends Fragment {
    SharedPrefManager sharedPrefManager3;

    private FregmentHotdealsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sharedPrefManager3= SharedPrefManager.getInstance(getActivity());

        binding = FregmentHotdealsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final LinearLayout rootLinearLayout = binding.rootLinearLayout;

        // Create a ScrollView to contain the list of offers
        ScrollView scrollView = new ScrollView(getActivity());
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // Add scrollView to the root view
        rootLinearLayout.addView(scrollView);

        // Create a LinearLayout inside the ScrollView to hold all offers
        LinearLayout scrollContent = new LinearLayout(getActivity());
        scrollContent.setOrientation(LinearLayout.VERTICAL);
        scrollContent.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // Add scrollContent to the scrollView
        scrollView.addView(scrollContent);

        DataBaseHelper_offer dataBaseHelper = new DataBaseHelper_offer(getActivity(), "Offer", null, 1);
        Cursor allOfferCursor = dataBaseHelper.getAllOffer();

        while (allOfferCursor.moveToNext()) {
            String pizzaType = allOfferCursor.getString(0);
            String size = allOfferCursor.getString(1);
            String price = allOfferCursor.getString(2);
            String dueDate = allOfferCursor.getString(3);

            // Create a LinearLayout to hold each offer and its button
            LinearLayout offerLayout = new LinearLayout(getActivity());
            offerLayout.setOrientation(LinearLayout.VERTICAL);
            offerLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            // Create TextView for offer information
            TextView offerTextView = new TextView(getActivity());
            offerTextView.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium);
            offerTextView.setText(
                    "Pizza Type= " + pizzaType +
                            "\nSize= " + size +
                            "\nPrice= " + price +
                            "\nDue Date= " + dueDate +
                            "\n-------------------------------------------------------------"
            );
            offerLayout.addView(offerTextView);

            // Create Button for add to orders (or any other action)
            Button actionButton = new Button(getActivity());
            actionButton.setText("Add to Orders");

            // OnClickListener for the button
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    String currentDate = dateFormat.format(new Date());
                    Order_pizza newone = new Order_pizza();
                    String emailUSER=sharedPrefManager3.readString("Email_user","");
                    DataBaseHelper_Order dataBaseHelper_odred = new
                            DataBaseHelper_Order(getActivity(), "order_pizza", null, 1);
                    Cursor allOrderCursor = dataBaseHelper_odred.SearchforOrder(emailUSER);
                    newone.setEmail(emailUSER);
                    newone.setOrderr(" ");
                    dataBaseHelper_odred.insertOrder(newone);
                    if (allOrderCursor != null && allOrderCursor.moveToFirst()) {
                        Cursor cursor = dataBaseHelper_odred.SearchforOrder(emailUSER);
                        if (cursor != null) {
                            try {
                                // Get the index of the "Ord" column
                                int ordColumnIndex = cursor.getColumnIndex("Ord");

                                if (ordColumnIndex != -1) {
                                    while (cursor.moveToNext()) {
                                        String order = cursor.getString(ordColumnIndex);
//                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//                                        String currentDate = dateFormat.format(new Date());

                                        dataBaseHelper_odred.settingOrder(emailUSER, order);
                                        order += "\n"+ pizzaType + " " + size +  "  " +price + " no extra\n"+ "ordered in:"+ currentDate +"\n\n";
                                        dataBaseHelper_odred.settingOrder(emailUSER, order);
                                    }

                                }

                            } finally {
                                cursor.close();  // Always close the cursor to avoid memory leaks
                            }
                        }


//                    if (allOrderCursor != null && allOrderCursor.moveToFirst()) {
//                        String order = allOrderCursor.getString(1); // Replace "OrderColumn" with the actual column name
//
//                        newone.setOrderr(pizzaType + size + price + "No extra\n"+ "ordered in:"+ currentDate +"\n\n");
//                    }
//                    dataBaseHelper.settingOrder(emailUSER, order);
                    }
                }
            });

            // Add actionButton to offerLayout
            offerLayout.addView(actionButton);

            // Add offerLayout to scrollContent
            scrollContent.addView(offerLayout);

        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}