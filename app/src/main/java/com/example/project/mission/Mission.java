package com.example.project.mission;

import com.example.project.spaceshipObjects.CrewQuarters;
import com.example.project.spaceshipObjects.PassengerManifest;
import com.example.project.spaceshipObjects.SpaceShip;
import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.threatObjects.Threat;

import java.util.Random;

public class Mission {
    private Character crewMember1 = null;
    private Character crewMember2 = null;
    private Threat missionTarget;
    private boolean isPlayerTurn = true;
    private boolean crew1Moved = false;
    private boolean crew2Moved = false;

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public boolean isCrew1Moved() {
        return crew1Moved;
    }
    public boolean isCrew2Moved() {
        return crew2Moved;
    }

    public Mission(Threat missionTarget) {

        this.missionTarget = missionTarget;
    }
    public Character getCrewMember1(){
        return crewMember1;
    }
    public Character getCrewMember2(){
        return crewMember2;
    }
    public Threat getMissionTarget(){
        return missionTarget;
    }
    //bring the chosen crew members to the combat
    public void addCrewMembers (Character crewMember1, Character crewMember2) {
        this.crewMember1 = crewMember1;
        this.crewMember2 = crewMember2;
    }
    /*begins the combat and helps runs the combat, thread acts as pause before the enemy turn goes
     */
    public void executeMission() {
        new Thread(() -> {
            while (!isGameOver()){
                if (!isPlayerTurn) {
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // The CombatView will call enemyTurn() to sync animations, 
                    // so we just wait here if we're using CombatView.
                    // If running headless, we'd call it here.
                }
                try{ Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
            }
        }).start();
    }
    public void playerTurn(int crewIndex, int type) {
        if (!isPlayerTurn) return;

        // Prevents acting twice or acting if dead
        if (crewIndex == 1 && (crew1Moved || crewMember1 == null || crewMember1.getCurrentHealth() <= 0)) return;
        if (crewIndex == 2 && (crew2Moved || crewMember2 == null || crewMember2.getCurrentHealth() <= 0)) return;

        Character activeMember = (crewIndex == 1) ? crewMember1 : crewMember2;

        if (activeMember != null) {
            if (type == 0) {
                missionTarget.takeDamage(activeMember.attack());
            } else if (type == 1) {
                missionTarget.takeDamage(activeMember.special());
            }
        }

        if (crewIndex == 1) crew1Moved = true;
        else crew2Moved = true;

        // Checks if all available crew members have acted
        if (allCrewMoved()) {
            isPlayerTurn = false;
        }
    }
    //flags to see if characters have taken their turn
    public boolean allCrewMoved() {
        boolean c1Done = (crewMember1 == null || crewMember1.getCurrentHealth() <= 0 || crew1Moved);
        boolean c2Done = (crewMember2 == null || crewMember2.getCurrentHealth() <= 0 || crew2Moved);
        return c1Done && c2Done;
    }
    //enemy logic
    public int enemyTurn() {
        if (isPlayerTurn) return -1;

        // Determine valid targets (not dead)
        Character target1 = (crewMember1 != null && crewMember1.getCurrentHealth() > 0) ? crewMember1 : null;
        Character target2 = (crewMember2 != null && crewMember2.getCurrentHealth() > 0) ? crewMember2 : null;

        Random random = new Random();
        int action = random.nextInt(2);
        int damage = 0;
        // Pass only living targets to the threat logic
        if (action == 0) {
            missionTarget.attack(target1, target2);
        } else {
            missionTarget.special(target1, target2);
        }

        // Reset move flags for next round
        isPlayerTurn = true;
        crew1Moved = false;
        crew2Moved = false;

        return action;

    }
    /*once mission ends, checks the crew members, if dead, damage the ship, if not, put them back
    to crew quarters
     */
    public void endMission() {
        
        // increments the day counter at the end of a mission
        SpaceShip.getInstance().incrementDaysOnBoard();
        
        // If all crew members are dead it damages the ship
        boolean crewDead = (crewMember1 == null || crewMember1.getCurrentHealth() <= 0) &&
                          (crewMember2 == null || crewMember2.getCurrentHealth() <= 0);
        if (crewDead) {
            SpaceShip.getInstance().damageShip();
        }

        CrewQuarters crewQuarters = SpaceShip.getInstance().getCrewQuarters();
        PassengerManifest manifest = SpaceShip.getInstance().getManifest();

        /*checking for if member is dead, if they are not dead run endofcombatprep
        if they are dead, they wil then be sent to passenger mainifest as dead and removing
        from crew quarters
         */
        if (crewMember1 != null){
            if (crewMember1.getCurrentHealth() > 0){
                crewMember1.endOfCombatPrep(missionTarget);
                crewQuarters.addCrewMember(crewMember1);
            } else {
                manifest.recordDeath(crewMember1);
            }
        }
        if (crewMember2 != null){
            if (crewMember2.getCurrentHealth() > 0){
                crewMember2.endOfCombatPrep(missionTarget);
                crewQuarters.addCrewMember(crewMember2);
            } else {
                manifest.recordDeath(crewMember2);
            }
        }
    }
    //checks to see if the game is over if both crew members are dead.
    public boolean isGameOver() {
        boolean crewDead = (crewMember1 == null || crewMember1.getCurrentHealth() <= 0) &&
                (crewMember2 == null || crewMember2.getCurrentHealth() <= 0);
        boolean targetDead = (missionTarget == null || missionTarget.getCurrentHealth() <= 0);
        return crewDead || targetDead;
    }
}
