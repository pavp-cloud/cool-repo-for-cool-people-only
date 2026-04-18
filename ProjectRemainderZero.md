👥 Group Information
**Group Name:** Project Remainder Zero 
**Course:** Object-Oriented Programming (Spring 2026)
**University:** LUT

| Name | Role | GitHub |
| :--- | :--- | :--- |
| [Student Name 1] | [e.g. Lead Developer / UI] | [@username] |
| [Student Name 2] | [e.g. Backend / Logic] | [@username] |
| [Student Name 3] | [e.g. Assets / Testing] | [@username] |

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

### [Student Name 1]
- Implemented the **SurfaceView Combat Engine** and multithreaded game loop.
- Developed the **Inheritance Hierarchy** for the `Character` base class and its specializations.
- Integrated **Fragment Navigation** and the main menu UI.

### [Student Name 2]
- Engineered the **`Mission` logic** and turn-sequencing (Player 1 -> Player 2 -> Enemy).
- Created the **`SpaceShip` Singleton** and the global state management.
- Developed the **Threat AI logic** and damage scaling systems.

### [Student Name 3]
- Designed and integrated **Sprite Assets** for all entities and backgrounds.
- Implemented the **`PassengerManifest`** and `RecyclerView` adapters for crew tracking.
- Created the **Training Room** mechanics and daily usage limitations.
