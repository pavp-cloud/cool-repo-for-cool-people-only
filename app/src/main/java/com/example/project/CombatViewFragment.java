package com.example.project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CombatViewFragment extends Fragment {

    public CombatViewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // acquires what the currently active mission is
        Mission mission = SpaceShip.getInstance().getMissionRoom().getActiveMission();
        
        // starts the combat view for the mission
        CombatView combatView = new CombatView(requireContext());
        combatView.setupCombat(mission);

        return combatView;
    }
}
