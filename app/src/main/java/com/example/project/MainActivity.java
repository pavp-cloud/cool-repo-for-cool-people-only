package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
            }
        });

        onboardCrewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_onboard_crew, null);
                EditText nameInput = dialogView.findViewById(R.id.edit_text_crew_name);

                new AlertDialog.Builder(MainActivity.this)
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

        /*passengerManifestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.passenger_manifest);
            }
        }); */
    }
}
