package com.example.project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CombatViewFragment extends Fragment {

    public CombatViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Get the active mission from the MissionRoom
        Mission mission = SpaceShip.getInstance().getMissionRoom().getActiveMission();
        
        // Create the custom SurfaceView
        CombatView combatView = new CombatView(requireContext());
        combatView.setupCombat(mission);
        
        // Return the SurfaceView as the Fragment's view
        return combatView;
    }
}
