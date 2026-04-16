package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class MainMenuFragment extends Fragment {
    private TextView daysAdriftNumber;
    private TextView currentCrewCountNumber;
    private TextView shipHealthNumber;

    public MainMenuFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 1. Inflate the EXISTING main_menu.xml layout
        View view = inflater.inflate(R.layout.main_menu, container, false);

        // 2. Find the buttons within this specific Fragment's view
        Button missionControlButton = view.findViewById(R.id.mission_control_button);
        Button onboardCrewButton = view.findViewById(R.id.onboard_crew_button);
        Button trainingRoomButton = view.findViewById(R.id.training_room_button);
        Button passengerManifestButton = view.findViewById(R.id.passenger_manifest_button);

        daysAdriftNumber = view.findViewById(R.id.days_adrift_number);
        currentCrewCountNumber = view.findViewById(R.id.current_crew_count_number_text);
        shipHealthNumber = view.findViewById(R.id.ship_health_number_text);

        updateStats();

        // sets up listeners for each button on the main menu

        missionControlButton.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MissionControlFragment())
                    .addToBackStack(null)
                    .commit();
        });

        onboardCrewButton.setOnClickListener(v -> {
            showOnboardPopup();
        });

        trainingRoomButton.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new TrainingRoomFragment())
                    .addToBackStack(null)
                    .commit();
        });

        passengerManifestButton.setOnClickListener(v -> {

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PassengerManifestFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    private void showOnboardPopup() {
        // We use 'requireActivity()' because the Dialog needs a Context (the MainActivity)
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_onboard_crew, null);
        EditText nameInput = dialogView.findViewById(R.id.edit_text_crew_name);

        new AlertDialog.Builder(requireActivity(), R.style.CustomDialogTheme)
                .setTitle("Onboard New Crew")
                .setView(dialogView)
                .setPositiveButton("Onboard", (dialog, which) -> {
                    String name = nameInput.getText().toString();
                    if (name.isEmpty()) name = "Imposter";

                    int randomSelection = (int) (Math.random() * 5) + 1;
                    SpaceShip.getInstance().onboardCrewMember(randomSelection, name);

                    updateStats();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void updateStats() {
        if (daysAdriftNumber != null && currentCrewCountNumber != null) {
            daysAdriftNumber.setText(String.valueOf(SpaceShip.getInstance().getDaysOnBoard()));
            currentCrewCountNumber.setText(String.valueOf(SpaceShip.getInstance().getCrewQuarters().getCrewMembers().size()));
            shipHealthNumber.setText(String.valueOf(SpaceShip.getInstance().getShipHealth()));
        }
    }
}