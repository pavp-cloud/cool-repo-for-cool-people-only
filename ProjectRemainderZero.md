👥 Group Information
**Group Name:** Project Remainder Zero 
**Course:** Object-Oriented Programming (Spring 2026)
**University:** LUT

| Name | Role | GitHub |
| Matthew Laughlin | Focus on Combat UI and Backend Combat Logic | fabled_03 |
| [Student Name 2] | [e.g. Backend / Logic] | [@username] |
| [Student Name 3] | [e.g. Assets / Testing] | [@username] |

Space RPG: Project Remainder Zero 
Deep Space Command is a tactical, turn-based Android RPG where you manage a spaceship crew navigating a hostile galaxy. Balance risk and reward as you scan for threats, manage crew health, and engage in strategic combat against evolving extraterrestrial enemies.
Gameplay Overview
The Core Loop
1.
Scan: Use the Mission Room radar to identify nearby threats.
2.
Deploy: Assign up to two crew members to a mission based on the threat profile.
3.
Engage: Fight through turn-based combat where positioning and ability timing are key.
4.
Advance: Earn EXP to scale your crew's power. Be careful—enemies scale in difficulty the longer you survive (Days On Board).
5. 
Survival: How long can you survive it the deep troves of space? (See how many days you can survive.)


Crew Specializations
Every crew member has unique scaling and distinct special abilities:
•
Soldier: The frontline powerhouse.
◦
Special: Frag Grenade – Deals massive area damage but causes self-recoil.
•
Medic: Essential for sustainability. Keeps the crew alive during long deployments.
•
Engineer & Scientist: Specialists providing technical utility and unique combat modifiers.
•
Pilot: Expert maneuvers to navigate and influence combat flow.

Threat Analysis (Bestiary)

Enemies in Deep Space Command aren't just stat-blocks; they feature unique AI behaviors:
Threat
Behavior
Special Ability
Pirate
Opportunistic
EXP Siphon: Steals experience points from your crew to buff itself.
Alien
Swarm Tactics
Multi-Strike: Attacks both deployed crew members simultaneously.
Demon
Escalating
Soul Stack: Gains permanent damage buffs every turn.
Parasite
Vampiric
Life Drain: Heals itself for a portion of damage dealt to your crew.
Gundam
Juggernaut
Overload: Massive damage output at the cost of its own internal systems.
---

## 🛠 Implemented Features

### 1. Crew Management & Progression
- **Onboarding:** Recruit unique characters through a security background check system.
- **Experience (XP):** Characters gain XP from successful missions and training, increasing their max health and damage.
- **Class Specials:** Unique combat logic for each class (e.g., Engineer's Combat Armor, Medic's Healing).

### 2. Strategic Turn-Based Combat
- **2v1 Battles:** Deploy two crew members against a single high-tier threat.
- **SurfaceView Rendering:** High-performance combat rendering at 60 FPS.
- **Smart AI:** Threats intelligently target living crew members and utilize special moves based on logic states.

### 3. Ship Systems
- **Persistent State:** Uses the Singleton pattern to maintain ship health, days on board, and manifest across fragments.
- **Training Room:** A limited-use facility to safely boost crew stats between missions.
- **Passenger Manifest:** A permanent record tracking every crew member's service and status (Alive/Dead).

### 4. Game Mechanics
- **Dynamic Difficulty:** Threats scale in difficulty based on the number of days the ship has been adrift.
- **Survival Penalties:** Ship health is damaged if a mission ends in total crew defeat.
- **Soft Reset:** Full game reset functionality upon ship destruction.

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

## Class Diagram 
Below is the architectural overview of the project, highlighting the inheritance hierarchies for Crew Members and Threats, and the Singleton pattern used for ship management.

classDiagram
    class SpaceShip {
        -static SpaceShip instance
        -MissionRoom missionRoom
        -CrewQuarters crewQuarters
        -PassengerManifest manifest
        -TrainingRoom trainingRoom
        -BackgroundCheck securityCheck
        -int daysOnBoard
        -int shipHealth
        +static getInstance() SpaceShip
        +resetGame()
        +onboardCrewMember(int, String)
        +damageShip()
    }

    class Character {
        <<abstract>>
        #int maxHealth
        #int currentHealth
        #String name
        #int exp
        #boolean isDead
        +attack()* int
        +special()* int
        +takeDamage(int)*
        +endOfCombatPrep(Threat)*
    }

    class Threat {
        <<abstract>>
        #int maxHealth
        #int currentHealth
        #String name
        #int exp
        +attack(Character, Character)* int
        +special(Character, Character)* int
        +takeDamage(int)*
    }

    class Mission {
        -Character crewMember1
        -Character crewMember2
        -Threat missionTarget
        -boolean isPlayerTurn
        +executeMission()
        +playerTurn(int, int)
        +enemyTurn() int
        +endMission()
        +isGameOver() boolean
    }

    class CombatView {
        -Mission activeMission
        -OnCombatEndedListener listener
        +setupCombat(Mission)
        +draw()
        +onTouchEvent(MotionEvent)
    }

    class MissionRoom {
        -Mission activeMission
        +scanForThreats(int) Threat
        +createMission(Threat) Mission
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

    %% Composition and Associations
    SpaceShip *-- MissionRoom
    SpaceShip *-- CrewQuarters
    SpaceShip *-- PassengerManifest
    SpaceShip *-- TrainingRoom
    
    MissionRoom o-- Mission
    Mission "1" -- "2" Character : involves
    Mission "1" -- "1" Threat : targets
    
    CombatViewFragment ..> CombatView : creates
    CombatViewFragment ..> Mission : uses
    CombatView --|> SurfaceView

## 🤝 Division of Work
- Designing, Scaling, and Logic was done as a group effort, with all members meeting accordingly

### Matthew Laughlin 
- Designed and created the entire CombatView. This includes adding sprites, toasts, animations, and buttons. This was done in order to add a degree of customization and to practice with working with coding UI inside Java.
- Worked on the Mission class, working on playerTurn, executeMission, isGameOver, endMission, enemyTurn, and allowedCrewMembers. My work included playerTurn logic and enemyTurn logic with focus on setting up combat to execute properly and triggering the correct events when conditions were met. I also ensured that crew members were returned to their proper places after combat had occured. Then worked to connect all of these to the UI elements of the CombatView. 
- I also worked on minor details inside of SpaceShip, Threat, and Mission Room. This included helping with Threat logic on the targeting of the two crew members with basic and special attacks, resetGame, and creating the array list for the Threat names.

### [Student Name 2]
- Engineered the **`Mission` logic** and turn-sequencing (Player 1 -> Player 2 -> Enemy).
- Created the **`SpaceShip` Singleton** and the global state management.
- Developed the **Threat AI logic** and damage scaling systems.

### [Student Name 3]
- Designed and integrated **Sprite Assets** for all entities and backgrounds.
- Implemented the **`PassengerManifest`** and `RecyclerView` adapters for crew tracking.
- Created the **Training Room** mechanics and daily usage limitations.
