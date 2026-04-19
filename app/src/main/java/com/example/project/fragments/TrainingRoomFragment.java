package com.example.project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.spaceshipObjects.SpaceShip;
import com.example.project.adapters.CharacterAdapter;
import com.example.project.adapters.StringAdapter;
import com.example.project.entities.characterObjects.Character;

import java.util.ArrayList;
import java.util.Arrays;

/*Fragment that manages Training Room UI and handles the logic
  The idea is that you get a few attempts to increase EXP of selected crew members
  before combat
 */
public class TrainingRoomFragment extends Fragment {
    private TextView dailyUsagesNumber;
    private TextView remainingUsagesText;

    private int selectedTrainingDifficulty = -1;

    public TrainingRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.training_room, container, false);

        // Initialize UI components like buttons and such
        Button selectTraineeBtn = view.findViewById(R.id.select_trainee_button);
        Button selectDiffBtn = view.findViewById(R.id.select_difficulty_button);
        Button beginBtn = view.findViewById(R.id.begin_training_button);
        TextView traineeNameText = view.findViewById(R.id.trainee_name_text);
        TextView diffLabelText = view.findViewById(R.id.difficulty_label_text);
        RecyclerView selectionRecycler = view.findViewById(R.id.recycler_view_training_select);
        Button backBtn = view.findViewById(R.id.back_to_menu_button);

        dailyUsagesNumber = view.findViewById(R.id.remaining_usages_number);
        remainingUsagesText = view.findViewById(R.id.daily_usages_text);

        // Initial UI state setup
        updateUses();
        updateTrainingUI(traineeNameText, diffLabelText, beginBtn);

        // Sets up click listeners
        selectTraineeBtn.setOnClickListener(v -> onSelectTraineeClicked(traineeNameText, diffLabelText, beginBtn, selectionRecycler));
        selectDiffBtn.setOnClickListener(v -> onSelectDifficultyClicked(traineeNameText, diffLabelText, beginBtn, selectionRecycler));
        beginBtn.setOnClickListener(v -> onBeginTrainingClicked(traineeNameText, diffLabelText, beginBtn));

        // Set up navigation listener
        if (backBtn != null) {
            backBtn.setOnClickListener(v -> {
                /*
                checks if there is a trainee selected waiting for training and returns
                them to the crew quarters if they are. in playtesting we frequently forgot
                trainees inside of the training room and would find them not available for
                combat and have to backtrack.
                */
                Character trainee = SpaceShip.getInstance().getTrainingRoom().getTrainees();
                if (trainee != null) {
                    SpaceShip.getInstance().getCrewQuarters().addCrewMember(SpaceShip.getInstance().getTrainingRoom().removeTrainee());
                }
                getParentFragmentManager().popBackStack();
            });
        }
        return view;
    }

    // Opens an overlay to select a crew member from the available pool in CrewQuarters.
    private void onSelectTraineeClicked(TextView nameText, TextView diffText, Button beginBtn, RecyclerView recycler) {
        // If someone is already in the slot, put them back in the pool before picking a new one
        Character current = SpaceShip.getInstance().getTrainingRoom().getTrainees();
        if (current != null) {
            SpaceShip.getInstance().getCrewQuarters().addCrewMember(SpaceShip.getInstance().getTrainingRoom().removeTrainee());
        }
        updateTrainingUI(nameText, diffText, beginBtn);

        recycler.setVisibility(View.VISIBLE);
        dailyUsagesNumber.setVisibility(View.GONE);
        remainingUsagesText.setVisibility(View.GONE);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Character> available = SpaceShip.getInstance().getCrewQuarters().getCrewMembers();

        // Bind adapter with selection logic
        recycler.setAdapter(new CharacterAdapter(available, character -> {
            // Move character from quarters to training
            SpaceShip.getInstance().getTrainingRoom().addTrainee(character);
            SpaceShip.getInstance().getCrewQuarters().removeCrewMember(character);

            recycler.setVisibility(View.GONE);
            dailyUsagesNumber.setVisibility(View.VISIBLE);
            remainingUsagesText.setVisibility(View.VISIBLE);

            updateTrainingUI(nameText, diffText, beginBtn);
        }));
    }

    // Opens the overlay for picking difficulty
    private void onSelectDifficultyClicked(TextView nameText, TextView diffText, Button beginBtn, RecyclerView recycler) {
        recycler.setVisibility(View.VISIBLE);
        dailyUsagesNumber.setVisibility(View.GONE);
        remainingUsagesText.setVisibility(View.GONE);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        // Different types of training have different success rates
        ArrayList<String> difficulties = new ArrayList<>(Arrays.asList(
                "Basic (100% Success)", "Intermediate (70% Success)", "Advanced (40% Success)"));

        recycler.setAdapter(new StringAdapter(difficulties, (item, position) -> {
            // Position 0=Basic, 1=Intermediate, 2=Advanced. Map to 1, 2, 3.
            selectedTrainingDifficulty = position + 1;

            recycler.setVisibility(View.GONE);
            dailyUsagesNumber.setVisibility(View.VISIBLE);
            remainingUsagesText.setVisibility(View.VISIBLE);

            updateTrainingUI(nameText, diffText, beginBtn);
        }));
    }

    // Executes the training logic
    private void onBeginTrainingClicked(TextView nameText, TextView diffText, Button beginBtn) {
        // Trigger the training logic
        int result = SpaceShip.getInstance().getTrainingRoom().trainCrewMember(selectedTrainingDifficulty);
        
        // Update use count
        updateUses();
        
        // Displays the result
        String message = (result == 0) ? "Training Successful!" : "Training Failed.";
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        
        // Return the trainee that just finished training back to the pool
        Character finishedTrainee = SpaceShip.getInstance().getTrainingRoom().removeTrainee();
        if (finishedTrainee != null) {
            SpaceShip.getInstance().getCrewQuarters().addCrewMember(finishedTrainee);
        }
        
        updateTrainingUI(nameText, diffText, beginBtn);
    }

    // Syncs UI labels with the data
    private void updateTrainingUI(TextView traineeText, TextView diffText, Button beginBtn) {
        Character trainee = SpaceShip.getInstance().getTrainingRoom().getTrainees();
        traineeText.setText(trainee != null ? "Trainee: " + trainee.getName() : "Trainee: None");

        String[] diffNames = {"Basic", "Intermediate", "Advanced"};
        if (selectedTrainingDifficulty >= 1 && selectedTrainingDifficulty <= 3) {
            diffText.setText("Difficulty: " + diffNames[selectedTrainingDifficulty - 1]);
        } else {
            diffText.setText("Difficulty: Not Selected");
        }

        // Only allow clicking begin if we have both a person and a difficulty selected
        beginBtn.setEnabled(trainee != null && selectedTrainingDifficulty != -1);
    }


    // Updates the text showing how many times the room can still be used today.
    private void updateUses() {
        if (dailyUsagesNumber != null) {
            dailyUsagesNumber.setText(String.valueOf(SpaceShip.getInstance().getTrainingRoom().getDailyUsages()));
        }
    }
}
