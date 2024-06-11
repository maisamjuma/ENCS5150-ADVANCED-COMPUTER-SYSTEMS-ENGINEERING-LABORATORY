package edu.birzeit.proj.ui.slideshow;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import edu.birzeit.proj.DataBaseHelper_Order;
import edu.birzeit.proj.SharedPrefManager;
import edu.birzeit.proj.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private SlideshowViewModel favoritesViewModel;
    SharedPrefManager sharedPrefManager3;
    SharedPrefManager sharedPrefManager_for_extra;



    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoritesViewModel = new ViewModelProvider(requireActivity()).get(SlideshowViewModel.class);
//        final TextView OrderTextView = binding.textSlideshow;
//        DataBaseHelperPizza dataBaseHelperpizza = new
//                DataBaseHelperPizza(getActivity(), "Pizza", null, 1);
//        Cursor allpizzasCursor = dataBaseHelperpizza.getAllPizzas();

//        if (allpizzasCursor.moveToNext()) {
//            OrderTextView.setText(
//                    "Name= " + allpizzasCursor.getString(0)
//                            + "\n\n"
//            );
//        }

    }
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        sharedPrefManager3= SharedPrefManager.getInstance(getActivity());
        sharedPrefManager_for_extra= SharedPrefManager.getInstance(getActivity());

        final TextView OrderTextView = binding.textSlideshow;
        favoritesViewModel.getOrder().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> favoritePizzas) {
                // Update the UI with the list of favorite pizzas
                StringBuilder stringBuilder = new StringBuilder();
                String emailUSER=sharedPrefManager3.readString("Email_user","");

                DataBaseHelper_Order dataBaseHelperOrder = new
                        DataBaseHelper_Order(getActivity(), "order_pizza", null, 1);
                Cursor allUserCursor = dataBaseHelperOrder.SearchforOrder(emailUSER);

                if (allUserCursor != null && allUserCursor.moveToFirst()) {
                    stringBuilder.append(allUserCursor.getString(1));
//                   stringBuilder.append(allUserCursor.getString(2));
//                    OrderTextView.setText(stringBuilder.toString());
                }
                else{
                    stringBuilder.append("Nothing in OrderList");
//                    OrderTextView.setText(stringBuilder.toString());

                }
//                DataBaseHelperPizza dataBaseHelperpizza = new
//                        DataBaseHelperPizza(getActivity(), "Pizza", null, 1);
//                Cursor allpizzasCursor = dataBaseHelperpizza.getAllPizzas();
//
//                while (allpizzasCursor.moveToNext()) {
//                    stringBuilder.append(allpizzasCursor.getString(0));
//                }
                OrderTextView.setText(stringBuilder.toString());
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}