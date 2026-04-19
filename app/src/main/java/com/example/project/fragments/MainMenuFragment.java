package com.example.project.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.project.R;
import com.example.project.spaceshipObjects.SpaceShip;

public class MainMenuFragment extends Fragment {
    private TextView daysAdriftNumber;
    private TextView currentCrewCountNumber;
    private TextView shipHealthNumber;

    public MainMenuFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflates the main menu layout for this fragment
        View view = inflater.inflate(R.layout.main_menu, container, false);

        // initializes the buttons and text views
        Button missionControlButton = view.findViewById(R.id.mission_control_button);
        Button onboardCrewButton = view.findViewById(R.id.onboard_crew_button);
        Button trainingRoomButton = view.findViewById(R.id.training_room_button);
        Button passengerManifestButton = view.findViewById(R.id.passenger_manifest_button);
        Button tutorialButton = view.findViewById(R.id.tutorial_button);

        daysAdriftNumber = view.findViewById(R.id.days_adrift_number);
        currentCrewCountNumber = view.findViewById(R.id.current_crew_count_number_text);
        shipHealthNumber = view.findViewById(R.id.ship_health_number_text);

        /*
        updates the stats on the main menu and
        checks if the game is over and needs to reset the game
        */
        updateStats();
        checkGameOver();


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

        tutorialButton.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new TutorialFragment())
                    .addToBackStack(null)
                    .commit();
        });


        return view;
    }
    //Displays game over if ship health is 0.
    private void checkGameOver() {
        if (SpaceShip.getInstance().getShipHealth() <= 0) {
            new AlertDialog.Builder(requireActivity(), R.style.CustomDialogTheme)
                    .setTitle("GAME OVER")
                    .setMessage("Your ship has been destroyed. And no one will now hear your screams.")
                    .setCancelable(false)
                    .setPositiveButton("Restart", (dialog, which) -> {
                        SpaceShip.getInstance().resetGame();
                        updateStats();
                    })
                    .setNegativeButton("Can you survive longer in the void of space?", (dialog, which) -> {
                        requireActivity().finish();
                    })
                    .show();
        }
    }

    private void showOnboardPopup() {
        // Creates a custom dialog layout for naming the onboarded crew member
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_onboard_crew, null);
        EditText nameInput = dialogView.findViewById(R.id.edit_text_crew_name);

        // opens the popup menu for naming the crew member
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

    /*
    updates the stats on the main menu
     */
    private void updateStats() {
        if (daysAdriftNumber != null && currentCrewCountNumber != null) {
            daysAdriftNumber.setText(String.valueOf(SpaceShip.getInstance().getDaysOnBoard()));
            currentCrewCountNumber.setText(String.valueOf(SpaceShip.getInstance().getCrewQuarters().getCrewMembers().size()));
            shipHealthNumber.setText(String.valueOf(SpaceShip.getInstance().getShipHealth()));
        }
    }
}
