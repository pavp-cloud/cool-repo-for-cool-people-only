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
                setContentView(R.layout.main_menu);

                setupMainMenuButtons();
            }
        });
    }

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

    private void setupMissionControlButtons() {
        Button startMissionButton = findViewById(R.id.start_mission_button);
        Button scanForThreatsButton = findViewById(R.id.scan_for_threats_button);

        TextView nameText = findViewById(R.id.threat_name);
        TextView hpText = findViewById(R.id.threat_hp);
        TextView xpText = findViewById(R.id.threat_xp);

        startMissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SpaceShip.getInstance().getMissionRoom().getActiveMission() != null) {
                    // select crew members

                    // run the mission
                }
            }

        });

        scanForThreatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomSelection = (int) (Math.random() * 5) + 1;
                Threat missionThreat = SpaceShip.getInstance().getMissionRoom().scanForThreats(randomSelection);
                Mission mission = SpaceShip.getInstance().getMissionRoom().createMission(missionThreat);

                if (missionThreat != null) {
                    nameText.setText(missionThreat.getName());
                    hpText.setText(String.valueOf(missionThreat.getCurrentHealth()));
                    xpText.setText(String.valueOf(missionThreat.getExp()));
                }
            }
        });
    }
    private void showPassengerManifest() {
        setContentView(R.layout.passenger_manifest);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_manifest);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Get data from your existing SpaceShip singleton
            ArrayList<Character> crew = SpaceShip.getInstance().getManifest().getCrewManifest();

            CharacterAdapter adapter = new CharacterAdapter(crew);
            recyclerView.setAdapter(adapter);
        }

        // Return to main menu
        Button backButton = findViewById(R.id.back_to_menu_button);
        if (backButton != null) {
            backButton.setOnClickListener(v -> {
                setContentView(R.layout.main_menu);
                setupMainMenuButtons();
            });
        }
    }
}
