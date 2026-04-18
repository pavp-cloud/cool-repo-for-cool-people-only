package com.example.project.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.mission.Mission;
import com.example.project.R;
import com.example.project.spaceshipObjects.SpaceShip;
import com.example.project.adapters.CharacterAdapter;
import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.threatObjects.Threat;

import java.util.ArrayList;

public class MissionControlFragment extends Fragment {

    public MissionControlFragment() {

    }
    private ArrayList<Character> selectedMissionCrew = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mission_control, container, false);

        // initializes all the buttons and text views
        Button startMissionButton = view.findViewById(R.id.start_mission_button);
        Button scanForThreatsButton = view.findViewById(R.id.scan_for_threats_button);
        Button chooseCrewButton = view.findViewById(R.id.choose_crew_button);
        Button backButton = view.findViewById(R.id.back_button);

        TextView nameText = view.findViewById(R.id.threat_name);
        TextView hpText = view.findViewById(R.id.threat_hp);
        TextView xpText = view.findViewById(R.id.threat_xp);
        TextView selectedCrewLabel = view.findViewById(R.id.selected_crew_text);

        RecyclerView selectionRecycler = view.findViewById(R.id.recycler_view_crew_select);

        // begins crew member selection
        chooseCrewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Character c : selectedMissionCrew) {
                    SpaceShip.getInstance().getCrewQuarters().addCrewMember(c);
                }
                selectedMissionCrew.clear();
                updateMissionUI(selectedCrewLabel, startMissionButton);

                // opens selection overlay
                selectionRecycler.setVisibility(View.VISIBLE);
                selectionRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));

                // gets the list of available crew members
                ArrayList<Character> available = SpaceShip.getInstance().getCrewQuarters().getCrewMembers();

                // build adapter for containing the logic for selecting crew members
                CharacterAdapter adapter = new CharacterAdapter(available, new CharacterAdapter.OnCharacterClickListener() {
                    @Override
                    public void onCharacterClick(Character character) {
                        // adds the selected crew member to the mission crew
                        selectedMissionCrew.add(character);

                        // removes the selected crew member from the CrewQuarters for the duration fo the mission
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

        // starts the mission
        startMissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mission mission = SpaceShip.getInstance().getMissionRoom().getActiveMission();
                if (mission != null && selectedMissionCrew.size() == 2) {
                    // passes 2 characters into the Mission object
                    mission.addCrewMembers(selectedMissionCrew.get(0), selectedMissionCrew.get(1));

                    // clears the selection list (they are now active in the Mission class)
                    selectedMissionCrew.clear();

                    // sets the view to the combat view
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new CombatViewFragment())
                            .addToBackStack(null)
                            .commit();

                    // Starts the mission loop
                    SpaceShip.getInstance().getMissionRoom().runMission(mission);
                }
            }
        });

        // scans for threats and creates a mission
        scanForThreatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SpaceShip.getInstance().getMissionRoom().getActiveMission() == null) {
                    int randomSelection = (int) (Math.random() * 5) + 1;
                    Threat missionThreat = SpaceShip.getInstance().getMissionRoom().scanForThreats(randomSelection);
                    SpaceShip.getInstance().getMissionRoom().createMission(missionThreat);

                    if (missionThreat != null) {
                        nameText.setText(missionThreat.getName());
                        hpText.setText(String.valueOf(missionThreat.getCurrentHealth()));
                        xpText.setText(String.valueOf(missionThreat.getExp()));
                    }
                } else {
                    // tells the player that they are already on standby
                    String message = "Active mission on standby";
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });


        return view;
    }

    /*
    this function updates the UI for the mission control view
     */
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
            // only allows starting of the mission if exactly 2 people are ready
            startBtn.setEnabled(selectedMissionCrew.size() == 2);
        }
    }
}
