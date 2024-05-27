package edu.birzeit.proj;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.birzeit.proj.R;

public class BuffaloFragment extends Fragment {

    private static final String ARG_PIZZA_IMAGE_RESOURCE = "pizzaImageResource";
    private static final String ARG_PIZZA_NAME = "pizzaName";

    private int pizzaImageResource;
    private String pizzaName;

    public BuffaloFragment() {
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_buffalo_fragment, container, false);

        // Find the heartButton within the inflated view
        ImageButton heartButton = rootView.findViewById(R.id.heartButton);
        final TransitionDrawable transitionDrawable = (TransitionDrawable) heartButton.getDrawable();

        heartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the transition animation
                transitionDrawable.reverseTransition(100);

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
}
