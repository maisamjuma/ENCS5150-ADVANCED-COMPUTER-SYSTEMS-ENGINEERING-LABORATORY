package edu.birzeit.proj.ui.notification;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.birzeit.proj.R;
import edu.birzeit.proj.databinding.FregmentNotificationsBinding;

public class notificationFragment extends Fragment {

    private FregmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FregmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button1 = root.findViewById(R.id.call);
        Button button2 = root.findViewById(R.id.googlemaps);
        Button button3 = root.findViewById(R.id.email);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for button1
                Intent dialIntent =new Intent();
                dialIntent.setAction(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:0599000000"));
                startActivity(dialIntent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for button2
                Intent mapsIntent = new Intent(Intent.ACTION_VIEW);
                mapsIntent.setData(Uri.parse("geo:31.961013,35.190483")); // Provide valid coordinates here
                startActivity(mapsIntent);
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for button3
                Intent gmailIntent =new Intent();
                gmailIntent.setAction(Intent.ACTION_SENDTO);
                gmailIntent.setType("message/rfc822");
                gmailIntent.setData(Uri.parse("mailto:"));
                gmailIntent.putExtra(Intent.EXTRA_EMAIL, new String ("AdvancePizza@Pizza.com"));
                gmailIntent.putExtra(Intent.EXTRA_SUBJECT,"My Subject");
                gmailIntent.putExtra(Intent.EXTRA_TEXT,"Content of the message");
                startActivity(gmailIntent);

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
