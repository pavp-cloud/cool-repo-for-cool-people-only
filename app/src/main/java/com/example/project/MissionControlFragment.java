package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MissionControlFragment extends Fragment {

    public MissionControlFragment() {
        // Required empty public constructor
    }
    private ArrayList<Character> selectedMissionCrew = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mission_control, container, false);

        Button startMissionButton = view.findViewById(R.id.start_mission_button);
        Button scanForThreatsButton = view.findViewById(R.id.scan_for_threats_button);
        Button chooseCrewButton = view.findViewById(R.id.choose_crew_button);

        TextView nameText = view.findViewById(R.id.threat_name);
        TextView hpText = view.findViewById(R.id.threat_hp);
        TextView xpText = view.findViewById(R.id.threat_xp);
        TextView selectedCrewLabel = view.findViewById(R.id.selected_crew_text);

        RecyclerView selectionRecycler = view.findViewById(R.id.recycler_view_crew_select);

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
                selectionRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));

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
                    CombatView combatView = new CombatView(requireActivity());
                    combatView.setupCombat(mission);
                    requireActivity().setContentView(combatView);

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

        return view;
    }
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
}
