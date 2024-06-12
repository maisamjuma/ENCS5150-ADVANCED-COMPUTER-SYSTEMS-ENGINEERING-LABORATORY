package edu.birzeit.proj.ui.ViewAllOrders;

import androidx.lifecycle.ViewModelProvider;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.birzeit.proj.DataBaseHelper;
import edu.birzeit.proj.DataBaseHelper_Order;
import edu.birzeit.proj.R;
import edu.birzeit.proj.SharedPrefManager;
import edu.birzeit.proj.databinding.FragmentSlideshowBinding;
import edu.birzeit.proj.databinding.FragmentViewAllOrdersBinding;
import edu.birzeit.proj.neww;
import edu.birzeit.proj.ui.slideshow.SlideshowViewModel;

public class ViewAllOrdersFragment extends Fragment {
    private FragmentViewAllOrdersBinding binding;
    SharedPrefManager sharedPrefManager3;
    SharedPrefManager sharedPrefManager_for_extra;


    private ViewAllOrdersViewModel mViewModel;

    public static ViewAllOrdersFragment newInstance() {
        return new ViewAllOrdersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentViewAllOrdersBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        final TextView AllOrderTextView = binding.textAllOrder;
        sharedPrefManager3= SharedPrefManager.getInstance(getActivity());
        StringBuilder allOrderText = new StringBuilder();

        DataBaseHelper_Order dataBaseHelper = new
                DataBaseHelper_Order(getActivity(), "order_pizza", null, 1);
        Cursor allOrderCursor = dataBaseHelper.getAllOrders();

        while (allOrderCursor.moveToNext()) {
//            TextView AllOrderTextView1 = new TextView(getActivity());
            allOrderText.append(
                    "Email= " + allOrderCursor.getString(0)
                            + "\nOrder= {" + allOrderCursor.getString(1)+"}"
                            + "-------------------------------------------------------------\n\n"
            );

        }
        AllOrderTextView.setText(allOrderText.toString());


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewAllOrdersViewModel.class);
        // TODO: Use the ViewModel
    }

}