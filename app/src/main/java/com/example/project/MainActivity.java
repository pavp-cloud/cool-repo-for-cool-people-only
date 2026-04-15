package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Temporarily holds the selected crew for the mission before it starts
    private ArrayList<Character> selectedMissionCrew = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SpaceShip.getInstance();

        Button newGameButton = findViewById(R.id.new_game_button);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Hide the start menu UI (buttons and text)
                findViewById(R.id.new_game_button).setVisibility(View.GONE);
                findViewById(R.id.load_save_button).setVisibility(View.GONE);
                findViewById(R.id.game_name_text).setVisibility(View.GONE);

                // 2. Make the fragment container visible
                View container = findViewById(R.id.fragment_container);
                if (container != null) {
                    container.setVisibility(View.VISIBLE);

                    // 3. Load the MainMenuFragment into the container
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new MainMenuFragment())
                            .commit();
                }
            }
        });
    }
/* temp disable while testing fragments
    private void setupMainMenuButtons() {
        Button missionControlButton = findViewById(R.id.mission_control_button);
        Button onboardCrewButton = findViewById(R.id.onboard_crew_button);
        Button trainingRoomButton = findViewById(R.id.training_room_button);
        Button passengerManifestButton = findViewById(R.id.passenger_manifest_button);

        missionControlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.mission_control);
                setupMissionControlButtons();
            }
        });

        onboardCrewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_onboard_crew, null);
                EditText nameInput = dialogView.findViewById(R.id.edit_text_crew_name);

                new AlertDialog.Builder(MainActivity.this, R.style.CustomDialogTheme)
                        .setTitle("Onboard New Crew")
                        .setView(dialogView)
                        .setPositiveButton("Onboard", (dialog, which) -> {
                            String name = nameInput.getText().toString();
                            if (name.isEmpty()) name = "Imposter";

                            int randomSelection = (int) (Math.random() * 5) + 1;
                            SpaceShip.getInstance().onboardCrewMember(randomSelection, name);
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });

        trainingRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.training_room);
            }
        });

        passengerManifestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPassengerManifest();
            }
        });

    }
*/

    private void setupMissionControlButtons() {
        Button startMissionButton = findViewById(R.id.start_mission_button);
        Button scanForThreatsButton = findViewById(R.id.scan_for_threats_button);
        Button chooseCrewButton = findViewById(R.id.choose_crew_button);

        TextView nameText = findViewById(R.id.threat_name);
        TextView hpText = findViewById(R.id.threat_hp);
        TextView xpText = findViewById(R.id.threat_xp);
        TextView selectedCrewLabel = findViewById(R.id.selected_crew_text);

        RecyclerView selectionRecycler = findViewById(R.id.recycler_view_crew_select);

        // Update the label and button state immediately
        updateMissionUI(selectedCrewLabel, startMissionButton);

        chooseCrewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Rule: Choosing again returns currently "pulled" characters back to CrewQuarters 
                   so the pool remains accurate. */
                for (Character c : selectedMissionCrew) {
                    SpaceShip.getInstance().getCrewQuarters().addCrewMember(c);
                }
                selectedMissionCrew.clear();
                updateMissionUI(selectedCrewLabel, startMissionButton);

                // Open selection overlay
                selectionRecycler.setVisibility(View.VISIBLE);
                selectionRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                // Get people sitting in CrewQuarters
                ArrayList<Character> available = SpaceShip.getInstance().getCrewQuarters().getCrewMembers();

                // Build adapter with custom listener for the selection logic
                CharacterAdapter adapter = new CharacterAdapter(available, new CharacterAdapter.OnCharacterClickListener() {
                    @Override
                    public void onCharacterClick(Character character) {
                        // 1. Add to the pending mission list
                        selectedMissionCrew.add(character);
                        
                        // 2. Remove from the global ship pool so they can't be picked again
                        SpaceShip.getInstance().getCrewQuarters().removeCrewMember(character);

                        // 3. Logic: If we need more characters, refresh; otherwise, close list
                        if (selectedMissionCrew.size() < 2 && SpaceShip.getInstance().getCrewQuarters().getCrewMembers().size() > 0) {
                            ((CharacterAdapter)selectionRecycler.getAdapter()).refreshData(
                                    SpaceShip.getInstance().getCrewQuarters().getCrewMembers());
                        } else {
                            selectionRecycler.setVisibility(View.GONE);
                            updateMissionUI(selectedCrewLabel, startMissionButton);
                        }
                    }
                });
                selectionRecycler.setAdapter(adapter);
            }
        });

        startMissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mission mission = SpaceShip.getInstance().getMissionRoom().getActiveMission();
                if (mission != null && selectedMissionCrew.size() == 2) {
                    // LINK: Pass our 2 characters into the Mission object
                    mission.addCrewMembers(selectedMissionCrew.get(0), selectedMissionCrew.get(1));
                    
                    // Clear the selection list (they are now active in the Mission class)
                    selectedMissionCrew.clear();

                    // EXECUTE: Switch to the CombatView (SurfaceView)
                    CombatView combatView = new CombatView(MainActivity.this);
                    combatView.setupCombat(mission);
                    setContentView(combatView);
                    
                    // Start mission logic
                    SpaceShip.getInstance().getMissionRoom().runMission(mission);
                }
            }
        });

        scanForThreatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomSelection = (int) (Math.random() * 5) + 1;
                Threat missionThreat = SpaceShip.getInstance().getMissionRoom().scanForThreats(randomSelection);
                SpaceShip.getInstance().getMissionRoom().createMission(missionThreat);

                if (missionThreat != null) {
                    nameText.setText(missionThreat.getName());
                    hpText.setText(String.valueOf(missionThreat.getCurrentHealth()));
                    xpText.setText(String.valueOf(missionThreat.getExp()));
                }
            }
        });
    }

    // Helper to keep the "Selected Crew" label and "Start" button in sync
    private void updateMissionUI(TextView label, Button startBtn) {
        if (selectedMissionCrew.isEmpty()) {
            label.setText("Selected Crew: None");
            startBtn.setEnabled(false);
        } else {
            StringBuilder names = new StringBuilder("Selected Crew: ");
            for (int i = 0; i < selectedMissionCrew.size(); i++) {
                names.append(selectedMissionCrew.get(i).getName());
                if (i < selectedMissionCrew.size() - 1) names.append(", ");
            }
            label.setText(names.toString());
            // Only allow starting if exactly 2 people are ready
            startBtn.setEnabled(selectedMissionCrew.size() == 2);
        }
    }

    private void showPassengerManifest() {
        setContentView(R.layout.passenger_manifest);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_manifest);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Get data from your existing SpaceShip singleton
            ArrayList<Character> crew = SpaceShip.getInstance().getManifest().getCrewManifest();

            CharacterAdapter adapter = new CharacterAdapter(crew, null);
            recyclerView.setAdapter(adapter);
        }

        // Return to main menu
        Button backButton = findViewById(R.id.back_to_menu_button);
        if (backButton != null) {
            backButton.setOnClickListener(v -> {
                setContentView(R.layout.main_menu);
                // setupMainMenuButtons();
            });
        }
    }
}
