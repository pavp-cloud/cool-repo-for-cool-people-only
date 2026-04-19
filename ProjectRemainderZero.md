👥 Group Information
**Group Name:** Project Remainder Zero 
**Course:** Object-Oriented Programming (Spring 2026)
**University:** LUT

| Name | Role | GitHub |
| Matthew Laughlin | Focus on Combat UI and Backend Combat Logic | fabled_03 |
| [Student Name 2] | [e.g. Backend / Logic] | [@username] |
| [Student Name 3] | [e.g. Assets / Testing] | [@username] |


---

# 🚀 Project Remainder Zero

**Project Remainder Zero** is a tactical, turn-based Android RPG where you command a spaceship and its crew through the dangers of deep space. Balance resource management, crew deployment, and strategic combat as you survive against an ever-growing list of extraterrestrial threats.

## 🎮 Gameplay Overview

### **The Core Loop**
1.  **Scan for Threats:** Utilize the **Mission Room** radar to identify nearby enemies. Encounter unique entities like *Anvaron the Exhalted*, *Vargmoth*, and *RxR 808*.
2.  **Crew Selection:** Assign up to two crew members from your **Passenger Manifest** to deploy.
3.  **Tactical Combat:** Engage in turn-based battles where choosing between basic attacks and class-specific "Special" moves determines survival.
4.  **Progression:** Earn EXP to scale your crew's power. Be warned—the difficulty scales based on your **Days On Board**.

---

## 👨‍🚀 Crew Specializations
Each crew member belongs to a class with distinct attributes and scaling:

*   **Soldier:** Heavy frontline combatant.
    *   *Special Ability:* **Frag Grenade** – Deals massive damage to the enemy at the cost of self-inflicted recoil.
*   **Medic:** Essential for long-term survival, focusing on sustainability.
*   *Special Ability:* **Heal**- Can heal self to stay in combat longer than the advisary. 
*   **Engineer & Scientist:** Utility specialists that provide technical support and unique combat modifiers.
*   *Special Abilities:* **Combat Armor** Take less damage so you can survive anything.
*   *Special Abilities:* **Exp Potion** Grew more powerful, quicker, with a secret formula.
*   **Pilot:** Expert navigators who influence the flow of combat.
*   *Special Abilities:* **Drone** Have a helping hand in dealing more damamge to your opponent. 

---

## 👾 Threat Analysis (Bestiary)
Threats feature unique AI behaviors and scaling logic. Enemies grow stronger as your voyage progresses.

| Threat | Combat Style | Signature Move |
| :--- | :--- | :--- |
| **Pirate** | Opportunistic | **EXP Siphon:** Steals experience points from your crew to buff its own stats. |
| **Alien** | Swarm | **Multi-Attack:** Deals damage to both deployed crew members in a single turn. |
| **Demon** | Escalating | **Soul Stack:** Gains permanent damage buffs every turn the battle continues. |
| **Parasite** | Vampiric | **Life Drain:** Heals itself for a portion of the damage dealt to your crew. |
| **Gundam** | Juggernaut | **Overload:** Massive output damage but sustains internal system damage (recoil). |

---

## 🛠 Implemented Features

### 1. Crew Management & Progression
- **Onboarding:** Recruit unique characters through a security background check system.
- **Experience (XP):** Characters gain XP from successful missions and training, increasing their max health and damage.
- **Class Specials:** Unique combat logic for each class (e.g., Soldier's recoil damage, Medic's sustain).

### 2. Strategic Turn-Based Combat
- **2v1 Battles:** Deploy two crew members against a single high-tier threat.
- **SurfaceView Rendering:** High-performance combat rendering at 60 FPS.
- **Null-Safe Targeting:** Advanced logic ensures enemies intelligently target living crew members, preventing application crashes.

### 3. Ship Systems
- **Persistent State:** Uses the Singleton pattern (`SpaceShip`) to maintain ship health, days on board, and manifest across fragments.
- **Training Room:** A limited-use facility to safely boost crew stats between missions.
- **Passenger Manifest:** A permanent record tracking every crew member's service and status (Alive/Dead) using a `RecyclerView`.

### 4. Game Mechanics
- **Dynamic Difficulty:** Threats scale (Health/EXP) based on the number of days the ship has been adrift (`SpaceShip.getDaysOnBoard()`).
- **Survival Penalties:** Ship health is damaged if a mission ends in total crew defeat.
- **Reset Logic:** Full game state reset functionality integrated upon ship destruction.

### 5. Mandatory Requirements Implemented - Descriptions: 
- ** Object Oriented Code** - this has been implemented, see code and class diagram.
- ** Code in English** - see code
- ** Basic Functionality** - refer to video, above, or play test.
- ** Documentation** - refer to this md and readme. 

### 6. Bonus Features Implemented - Locations: 
- ** Recyler Viewer - TrainingRoomFragment, PassengerManifestFragement, etc. 
- ** Crew Images as Sprites -CombatView 
- ** Mission Visualization - CombatView 
- ** Tatical Combat - Mission 
- ** Statistics - PassengerManifest 
- ** Randomness in Mission - Mission, MissionControl, Threats, CrewQuarters
-  ** Specialization Bonuses - Threat and Characters
-  ** Fragments - CombatView Fragment, MissionControl Fragment, Tutorial Fragment, etc.
-  ** Statics Visulaiztation - PassengerManifest
-  ** Own Creation - Ship will take damage when failing a mission, and a lot more features. 

---

## 📐 Architecture overview
Below is the architectural overview of the project, highlighting the inheritance hierarchies for Crew Members and Threats.

classDiagram
    class SpaceShip {
        -static SpaceShip instance
        -int daysOnBoard
        -int shipHealth
        +static getInstance() SpaceShip
        +resetGame()
        +onboardCrewMember(int, String)
    }

    class Character {
        <<abstract>>
        #int maxHealth
        #int currentHealth
        #String name
        #int exp
        +attack()* int
        +special()* int
    }

    class Threat {
        <<abstract>>
        #int maxHealth
        #int currentHealth
        #String name
        #int exp
        +attack(Character, Character)* int
        +special(Character, Character)* int
    }

    class Mission {
        -Character crewMember1
        -Character crewMember2
        -Threat missionTarget
        +executeMission()
        +enemyTurn() int
    }

    %% Inheritance Hierarchies
    Character <|-- Medic
    Character <|-- Soldier
    Character <|-- Engineer
    Character <|-- Pilot
    Character <|-- Scientist

    Threat <|-- Alien
    Threat <|-- Demon
    Threat <|-- Gundam
    Threat <|-- Pirate
    Threat <|-- Parasite

---

## 🤝 Division of Work
- Designing, Scaling, and Logic was done as a group effort, with all members meeting accordingly

### Matthew Laughlin 
- Designed and created the entire CombatView. This includes adding sprites, toasts, animations, and buttons. This was done in order to add a degree of customization and to practice with working with coding UI inside Java.
- Worked on the Mission class, working on playerTurn, executeMission, isGameOver, endMission, enemyTurn, and allowedCrewMembers. My work included playerTurn logic and enemyTurn logic with focus on setting up combat to execute properly and triggering the correct events when conditions were met. I also ensured that crew members were returned to their proper places after combat had occured. Then worked to connect all of these to the UI elements of the CombatView. 
- I also worked on minor details inside of SpaceShip, Threat, and Mission Room. This included helping with Threat logic on the targeting of the two crew members with basic and special attacks, resetGame, and creating the array list for the Threat names.

### [Student Name 2]
- Engineered the **`Mission` logic** and turn-sequencing (Player -> Enemy).
- Created the **`SpaceShip` Singleton** and the global state management.
- Developed the **Threat AI logic**, null-safe targeting, and damage scaling systems.

### [Student Name 3]
- Designed and integrated **Sprite Assets** for all entities and backgrounds.
- Implemented the **`PassengerManifest`** and `RecyclerView` adapters for crew tracking.
- Created the **Training Room** mechanics and daily usage limitations.
