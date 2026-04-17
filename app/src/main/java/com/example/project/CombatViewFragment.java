package com.example.project;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CombatViewFragment extends Fragment {

    public CombatViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Get the active mission from the MissionRoom
        Mission mission = SpaceShip.getInstance().getMissionRoom().getActiveMission();
        
        // Creates our custom SurfaceView
        CombatView combatView = new CombatView(requireContext());
        combatView.setupCombat(mission);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // this is used to disable the back swiping feature of the fragment to
                // not exit combat early
            }
        };
        
        // Return the SurfaceView as the Fragment's view
        // Set the listener for when the Continue button is clicked
        combatView.setOnCombatEndedListener(new CombatView.OnCombatEndedListener() {
            @Override
            public void onCombatEnded() {
                // Return to main thread to perform UI changes
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        // 1. Run the mission cleanup logic (survivors return, XP gain, etc.)
                        mission.endMission();

                        //Reset the mission room for new threat
                        SpaceShip.getInstance().getMissionRoom().updateMissionStatus();

                        //Reset the training room back to 3
                        SpaceShip.getInstance().getTrainingRoom().resetDailyUsages();


                        //Return to the Main Menu
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new MainMenuFragment())
                                .commit();
                    });
                }
            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        // Return the SurfaceView as the Fragment's view
        return combatView;
    }
}
