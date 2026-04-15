package com.example.project;

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

import java.util.ArrayList;
import java.util.Arrays;

public class TrainingRoomFragment extends Fragment {

    private int selectedTrainingDifficulty = -1;

    public TrainingRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the training_room layout
        View view = inflater.inflate(R.layout.training_room, container, false);

        Button selectTraineeBtn = view.findViewById(R.id.select_trainee_button);
        Button selectDiffBtn = view.findViewById(R.id.select_difficulty_button);
        Button beginBtn = view.findViewById(R.id.begin_training_button);
        TextView traineeNameText = view.findViewById(R.id.trainee_name_text);
        TextView diffLabelText = view.findViewById(R.id.difficulty_label_text);
        RecyclerView selectionRecycler = view.findViewById(R.id.recycler_view_training_select);
        Button backBtn = view.findViewById(R.id.back_to_menu_button);

        updateTrainingUI(traineeNameText, diffLabelText, beginBtn);

        selectTraineeBtn.setOnClickListener(v -> onSelectTraineeClicked(traineeNameText, diffLabelText, beginBtn, selectionRecycler));
        selectDiffBtn.setOnClickListener(v -> onSelectDifficultyClicked(traineeNameText, diffLabelText, beginBtn, selectionRecycler));
        beginBtn.setOnClickListener(v -> onBeginTrainingClicked(traineeNameText, diffLabelText, beginBtn));

        if (backBtn != null) {
            backBtn.setOnClickListener(v -> {
                getParentFragmentManager().popBackStack();
            });
        }

        return view;
    }

    private void onSelectTraineeClicked(TextView nameText, TextView diffText, Button beginBtn, RecyclerView recycler) {
        // Return current trainee to pool if switching
        Character current = SpaceShip.getInstance().getTrainingRoom().getTrainees();
        if (current != null) {
            SpaceShip.getInstance().getCrewQuarters().addCrewMember(SpaceShip.getInstance().getTrainingRoom().removeTrainee());
        }
        updateTrainingUI(nameText, diffText, beginBtn);

        recycler.setVisibility(View.VISIBLE);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Character> available = SpaceShip.getInstance().getCrewQuarters().getCrewMembers();

        recycler.setAdapter(new CharacterAdapter(available, character -> {
            SpaceShip.getInstance().getTrainingRoom().addTrainee(character);
            SpaceShip.getInstance().getCrewQuarters().removeCrewMember(character);
            recycler.setVisibility(View.GONE);
            updateTrainingUI(nameText, diffText, beginBtn);
        }));
    }

    private void onSelectDifficultyClicked(TextView nameText, TextView diffText, Button beginBtn, RecyclerView recycler) {
        recycler.setVisibility(View.VISIBLE);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<String> difficulties = new ArrayList<>(Arrays.asList(
                "Basic (100% Success)", "Intermediate (60% Success)", "Advanced (30% Success)"));

        recycler.setAdapter(new StringAdapter(difficulties, (item, position) -> {
            selectedTrainingDifficulty = position + 1;
            recycler.setVisibility(View.GONE);
            updateTrainingUI(nameText, diffText, beginBtn);
        }));
    }

    private void onBeginTrainingClicked(TextView nameText, TextView diffText, Button beginBtn) {
        int result = SpaceShip.getInstance().getTrainingRoom().trainCrewMember(selectedTrainingDifficulty);
        String message = (result == 0) ? "Training Successful!" : "Training Failed.";
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Character finishedTrainee = SpaceShip.getInstance().getTrainingRoom().removeTrainee();
        SpaceShip.getInstance().getCrewQuarters().addCrewMember(finishedTrainee);
        updateTrainingUI(nameText, diffText, beginBtn);
    }

    private void updateTrainingUI(TextView traineeText, TextView diffText, Button beginBtn) {
        Character trainee = SpaceShip.getInstance().getTrainingRoom().getTrainees();
        traineeText.setText(trainee != null ? "Trainee: " + trainee.getName() : "Trainee: None");

        String[] diffNames = {"Basic", "Intermediate", "Advanced"};
        if (selectedTrainingDifficulty >= 1 && selectedTrainingDifficulty <= 3) {
            diffText.setText("Difficulty: " + diffNames[selectedTrainingDifficulty - 1]);
        } else {
            diffText.setText("Difficulty: Not Selected");
        }

        beginBtn.setEnabled(trainee != null && selectedTrainingDifficulty != -1);
    }
}
