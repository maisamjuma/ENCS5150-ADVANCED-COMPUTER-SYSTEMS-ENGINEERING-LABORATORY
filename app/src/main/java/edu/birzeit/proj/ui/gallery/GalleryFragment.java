package edu.birzeit.proj.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.birzeit.proj.BarbecueFragment;
import edu.birzeit.proj.BuffaloFragment;
import edu.birzeit.proj.CalzoneFragment;
import edu.birzeit.proj.HawaiianFragment;
import edu.birzeit.proj.MargheritaFragment;
import edu.birzeit.proj.MushroomFragment;
import edu.birzeit.proj.NeapolitanFragment;
import edu.birzeit.proj.NewYorkFragment;
import edu.birzeit.proj.PepperoniFragment;
import edu.birzeit.proj.PestoFragment;
import edu.birzeit.proj.R;
import edu.birzeit.proj.TandooriFragment;
import edu.birzeit.proj.VegetarianFragment;
import edu.birzeit.proj.databinding.FragmentGalleryBinding;
import edu.birzeit.proj.seafoodFragment;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private ImageButton pepperoniButton,pestoButton,seafoodButton,TandooriButton,barbecueButton,
    newyorkButton,margheritaButton,mushroomButoon,neapolitanButton,hawaiianButton,vegetarianButton,
    calzoneButton,buffaloButton;
    private TextView pepperoniText, pestoText, seafoodText, tandooriText, barbecueText,
            newyorkText, margheritaText, mushroomText, neapolitanText, hawaiianText,
            vegetarianText, calzoneText, buffaloText;
    private SearchView searchView;

    @Override
    public void onResume() {
        super.onResume();
        pepperoniText = getActivity().findViewById(R.id.textPepperoni);
        pestoText = getActivity().findViewById(R.id.textPestoChicken);
        seafoodText = getActivity().findViewById(R.id.textSeafood);
        tandooriText = getActivity().findViewById(R.id.textTandooriChicken);
        barbecueText = getActivity().findViewById(R.id.textBarbecue);
        buffaloText = getActivity().findViewById(R.id.textBuffaloChicken);
        calzoneText =getActivity().findViewById(R.id.textCalzone);
        hawaiianText = getActivity().findViewById(R.id.textHawaiian);
        margheritaText = getActivity().findViewById(R.id.textMargherita);
        mushroomText = getActivity().findViewById(R.id.textMushroomTruffle);
        neapolitanText = getActivity().findViewById(R.id.textNeapolitan);
        newyorkText = getActivity().findViewById(R.id.textNewYorkStyle);
        vegetarianText = getActivity().findViewById(R.id.textVegetarian);
        buffaloButton = getActivity().findViewById(R.id.imageButton2);
        buffaloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithbuffaloFragment();
            }
        });
        calzoneButton = getActivity().findViewById(R.id.imageButton3);
        calzoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithcalzoneFragment();
            }
        });
        vegetarianButton = getActivity().findViewById(R.id.imageButton14);
        vegetarianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithvegetarianFragment();
            }
        });
        neapolitanButton = getActivity().findViewById(R.id.imageButton7);
        neapolitanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithneapoltianFragment();
            }
        });
        hawaiianButton = getActivity().findViewById(R.id.imageButton4);
        hawaiianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithhawaiianFragment();
            }
        });

        newyorkButton = getActivity().findViewById(R.id.imageButton8);
        newyorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithnewyorkFragment();
            }
        });
        margheritaButton = getActivity().findViewById(R.id.imageButton5);
        margheritaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithmargheritaFragment();
            }
        });
        mushroomButoon = getActivity().findViewById(R.id.imageButton6);
        mushroomButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithmushroomFragment();
            }
        });
        barbecueButton = getActivity().findViewById(R.id.imageButton);
        barbecueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithBarbecueFragment();
            }
        });

        TandooriButton = getActivity().findViewById(R.id.imageButton12);
        TandooriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithTandooriFragment();
            }
        });
        seafoodButton = getActivity().findViewById(R.id.imageButton11);
        seafoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithseafoodFragment();
            }
        });
        pestoButton =getActivity().findViewById(R.id.imageButton10);
        pestoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithPestoFragment();
            }
        });

        pepperoniButton = getActivity().findViewById(R.id.imageButton9);
        pepperoniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWithPepperoniFragment();
            }
        });
        searchView = getActivity().findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                filterButtons(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterButtons(newText);
                return true;
            }
        });


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
private void deleteTextViews() {
    pepperoniText.setText("");
    pestoText.setText("");
    seafoodText.setText("");
    tandooriText.setText("");
    barbecueText.setText("");
    buffaloText.setText("");
    calzoneText.setText("");
    hawaiianText.setText("");
    margheritaText.setText("");
    mushroomText.setText("");
    neapolitanText.setText("");
    newyorkText.setText("");
    vegetarianText.setText("");
}
    private void resetTextViews() {
        pepperoniText.setText("Pepperoni");
        pestoText.setText("Pesto Chicken");
        seafoodText.setText("Seafood");
        tandooriText.setText("Tandoori Chicken");
        barbecueText.setText("Barbecue Chicken");
        buffaloText.setText("Buffalo Chicken");
        calzoneText.setText("Calzone");
        hawaiianText.setText("Hawaiian");
        margheritaText.setText("Margherita");
        mushroomText.setText("Mushroom Truffle");
        neapolitanText.setText("Neapolitan");
        newyorkText.setText("New York Style");
        vegetarianText.setText("Vegetarian");

    }

    private void filterButtons(String query) {
        query = query.toLowerCase().trim();

        if (query.isEmpty()) {
            resetTextViews();
            // Show all buttons if query is empty
            setButtonVisibility(View.VISIBLE);
            return;
        }
        deleteTextViews();
        // Hide all buttons initially
        setButtonVisibility(View.GONE);

        if (query.equals("chicken")) {
            pestoButton.setVisibility(View.VISIBLE);
            pestoText.setText("Pesto Chicken");
            buffaloButton.setVisibility(View.VISIBLE);
            buffaloText.setText("Buffalo Chicken");
            TandooriButton.setVisibility(View.VISIBLE);
            tandooriText.setText("Tandoori Chicken");
            barbecueButton.setVisibility(View.VISIBLE);
            barbecueText.setText("Barbecue Chicken");

        }
        if (query.equals("beef")) {
            pepperoniButton.setVisibility(View.VISIBLE);
            pepperoniText.setText("Pepperoni");
            calzoneButton.setVisibility(View.VISIBLE);
            calzoneText.setText("Calzone");

        }
        if (query.equals("veggies")) {
            mushroomButoon.setVisibility(View.VISIBLE);
            mushroomText.setText("Mushroom Truffle");
            vegetarianButton.setVisibility(View.VISIBLE);
            vegetarianText.setText("Vegetarian");
            margheritaButton.setVisibility(View.VISIBLE);
            margheritaText.setText("Margherita");
            neapolitanButton.setVisibility(View.VISIBLE);
            neapolitanText.setText("Neapolitan");

        }
        if (query.equals("l") || query.equals("large")) {
            pepperoniButton.setVisibility(View.VISIBLE);
            pepperoniText.setText("Pepperoni");
            pestoButton.setVisibility(View.VISIBLE);
            pestoText.setText("Pesto Chicken");
            TandooriButton.setVisibility(View.VISIBLE);
            tandooriText.setText("Tandoori Chicken");
            calzoneButton.setVisibility(View.VISIBLE);
            calzoneText.setText("Calzone");
            barbecueButton.setVisibility(View.VISIBLE);
            barbecueText.setText("Barbecue");
            newyorkButton.setVisibility(View.VISIBLE);
            newyorkText.setText("New York Style");
        }
        if (query.equals("s") || query.equals("small")){

            resetTextViews();
            // Show all buttons if query is empty
            setButtonVisibility(View.VISIBLE);
        }
        if (query.equals("m") || query.equals("medium")){

            vegetarianButton.setVisibility(View.VISIBLE);
            vegetarianText.setText("Vegetarian");
            newyorkButton.setVisibility(View.VISIBLE);
            newyorkText.setText("New York Style");
            neapolitanButton.setVisibility(View.VISIBLE);
            neapolitanText.setText("Neapolitan");
            mushroomButoon.setVisibility(View.VISIBLE);
            mushroomText.setText("Mushroom Truffle");
            pepperoniButton.setVisibility(View.VISIBLE);
            pepperoniText.setText("Pepperoni");
            seafoodButton.setVisibility(View.VISIBLE);
            seafoodText.setText("Seafood");
            pestoButton.setVisibility(View.VISIBLE);
            pestoText.setText("Pesto Chicken");
            TandooriButton.setVisibility(View.VISIBLE);
            tandooriText.setText("Tandoori Chicken");
            buffaloButton.setVisibility(View.VISIBLE);
            buffaloText.setText("Buffalo Chicken");
        }
        //all kinds that have any size that is $20 or less
        if (query.equals("20")){

            vegetarianButton.setVisibility(View.VISIBLE);
            vegetarianText.setText("Vegetarian");
            newyorkButton.setVisibility(View.VISIBLE);
            newyorkText.setText("New York Style");
            neapolitanButton.setVisibility(View.VISIBLE);
            neapolitanText.setText("Neapolitan");
            pestoButton.setVisibility(View.VISIBLE);
            pestoText.setText("Pesto Chicken");
            TandooriButton.setVisibility(View.VISIBLE);
            tandooriText.setText("Tandoori Chicken");

        }
        //all kinds that have any size that are $29
        if (query.equals("29")){
            mushroomButoon.setVisibility(View.VISIBLE);
            mushroomText.setText("Mushroom Truffle");
            seafoodButton.setVisibility(View.VISIBLE);
            seafoodText.setText("Seafood");
            vegetarianButton.setVisibility(View.VISIBLE);
            vegetarianText.setText("Vegetarian");

        }
//        //all kinds that have any size that are $12 ans small
//        if (query.contains("s12")||query.contains("small12") ){
//            vegetarianButton.setVisibility(View.VISIBLE);
//            vegetarianText.setText("Vegetarian");
//            pestoButton.setVisibility(View.VISIBLE);
//            pestoText.setText("Pesto Chicken");
//            newyorkButton.setVisibility(View.VISIBLE);
//            newyorkText.setText("New York Style");
//        }
        //all kinds that have any size that are $14 or less
        if (query.equals("14")){
            newyorkButton.setVisibility(View.VISIBLE);
            newyorkText.setText("New York Style");
            pestoButton.setVisibility(View.VISIBLE);
            pestoText.setText("Pesto Chicken");
            TandooriButton.setVisibility(View.VISIBLE);
            tandooriText.setText("Tandoori Chicken");
            vegetarianButton.setVisibility(View.VISIBLE);
            vegetarianText.setText("Vegetarian");

        }
        // Show buttons based on query
        if (query.equals("pepperoni")) {
            pepperoniButton.setVisibility(View.VISIBLE);
            pepperoniText.setText("Pepperoni");
        }
        if (query.equals("pesto")) {
            pestoButton.setVisibility(View.VISIBLE);
            pestoText.setText("Pesto Chicken");
        }
        if (query.equals("seafood")) {
            seafoodButton.setVisibility(View.VISIBLE);
            seafoodText.setText("Seafood");
        }

        if (query.equals("tandoori")) {
            TandooriButton.setVisibility(View.VISIBLE);
            tandooriText.setText("Tandoori Chicken");
        }
        if (query.equals("barbecue")) {
            barbecueButton.setVisibility(View.VISIBLE);
            barbecueText.setText("Barbecue");
        }
        if (query.equals("buffalo")) {
            buffaloButton.setVisibility(View.VISIBLE);
            buffaloText.setText("Buffalo Chicken");
        }
        if (query.equals("calzone")) {
            calzoneButton.setVisibility(View.VISIBLE);
            calzoneText.setText("Calzone");
        }
        if (query.equals("hawaiian")) {
            hawaiianButton.setVisibility(View.VISIBLE);
            hawaiianText.setText("Hawaiian");
        }
        if (query.equals("margherita")) {
            margheritaButton.setVisibility(View.VISIBLE);
            margheritaText.setText("Margherita");
        }
        if (query.equals("mushroom") || query.equals("mushroom truffle")) {
            mushroomButoon.setVisibility(View.VISIBLE);
            mushroomText.setText("Mushroom Truffle");
        }
        if (query.equals("neapolitan")) {
            neapolitanButton.setVisibility(View.VISIBLE);
            neapolitanText.setText("Neapolitan");
        }
        if (query.equals("new york") || query.equals("new york style")) {
            newyorkButton.setVisibility(View.VISIBLE);
            newyorkText.setText("New York Style");

        }
        if (query.equals("vegetarian")) {
            vegetarianButton.setVisibility(View.VISIBLE);
            vegetarianText.setText("Vegetarian");

        }
    }

    private void setButtonVisibility(int visibility) {
        pepperoniButton.setVisibility(visibility);
        pestoButton.setVisibility(visibility);
        seafoodButton.setVisibility(visibility);
        TandooriButton.setVisibility(visibility);
        barbecueButton.setVisibility(visibility);
        buffaloButton.setVisibility(visibility);
        calzoneButton.setVisibility(visibility);
        hawaiianButton.setVisibility(visibility);
        margheritaButton.setVisibility(visibility);
        mushroomButoon.setVisibility(visibility);
        neapolitanButton.setVisibility(visibility);
        newyorkButton.setVisibility(visibility);
        vegetarianButton.setVisibility(visibility);
    }
    private void replaceWithPepperoniFragment() {
        PepperoniFragment pepperoniFragment = new PepperoniFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, pepperoniFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithPestoFragment() {
        PestoFragment pestoFragment = new PestoFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, pestoFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithseafoodFragment() {
        seafoodFragment SeafoodFragment = new seafoodFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, SeafoodFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithTandooriFragment() {
        TandooriFragment tandooriFragment = new TandooriFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, tandooriFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithBarbecueFragment() {
        BarbecueFragment barbecueFragment = new BarbecueFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, barbecueFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithcalzoneFragment() {
        CalzoneFragment calzoneFragment = new CalzoneFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, calzoneFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithbuffaloFragment() {
        BuffaloFragment buffaloFragment = new BuffaloFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, buffaloFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithvegetarianFragment() {
        VegetarianFragment vegetarianFragment = new VegetarianFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, vegetarianFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithneapoltianFragment() {
        NeapolitanFragment neapolitanFragment = new NeapolitanFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, neapolitanFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithhawaiianFragment() {
        HawaiianFragment hawaiianFragment = new HawaiianFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, hawaiianFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithnewyorkFragment() {
        NewYorkFragment newYorkFragment = new NewYorkFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, newYorkFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithmargheritaFragment() {
        MargheritaFragment MargheritaFragment = new MargheritaFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, MargheritaFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void replaceWithmushroomFragment() {
        MushroomFragment MushroomFragment = new MushroomFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.root_layout, MushroomFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
