package edu.birzeit.proj.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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

    @Override
    public void onResume() {
        super.onResume();
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


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery,container,false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
