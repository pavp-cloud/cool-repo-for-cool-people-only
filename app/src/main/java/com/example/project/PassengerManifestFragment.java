package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class PassengerManifestFragment extends Fragment {

    public PassengerManifestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.passenger_manifest, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_manifest);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

            // Get data from your existing SpaceShip singleton
            ArrayList<Character> crew = SpaceShip.getInstance().getManifest().getCrewManifest();

            CharacterAdapter adapter = new CharacterAdapter(crew, null);
            recyclerView.setAdapter(adapter);
        }

        // Return to main menu
        Button backButton = view.findViewById(R.id.back_to_menu_button);
        if (backButton != null) {
            backButton.setOnClickListener(v -> {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new MainMenuFragment())
                        .addToBackStack(null)
                        .commit();
            });
        }
        return view;
    }
}