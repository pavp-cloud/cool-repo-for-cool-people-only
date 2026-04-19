👥 Group Information
**Group Name:** Project Remainder Zero 
**Course:** Object-Oriented Programming (Spring 2026)
**University:** LUT

| Name | Role | GitHub |
| Matthew Laughlin | Focus on Combat UI and Backend Combat Logic | fabled_03 |
| [Student Name 2] | [e.g. Backend / Logic] | [@username] |
| [Student Name 3] | [e.g. Assets / Testing] | [@username] |

Game Overview: Project Remainder Zero 
Genre: Space-Themed Turn-Based Strategy RPG Platform: Android
1. Core Concept
Players manage a spaceship and its crew as they navigate through deep space, encountering various extraterrestrial and supernatural threats. The game focuses on tactical turn-based combat, crew management, and character progression through an experience-based leveling system.
2. Gameplay Loop
•
Scanning: Using the Mission Room, players use radar to scan for nearby threats.
•
Mission Setup: Players select a mission and assign crew members (up to two) to tackle the identified threat.
•
Combat: A turn-based battle system where players choose between standard attacks and specialized abilities.
•
Resolution: Successful missions reward crew members with EXP, while failure can lead to crew injury or damage to the ship.
3. Crew Classes (Character Specializations)
Each crew member belongs to a specific class with unique scaling and abilities:
•
Soldier: High durability and damage. Uses a "Grenade" special for massive damage at the cost of self-inflicted recoil.
•
Medic: Focused on sustainability and healing (inferred from Medic.java).
•
Pilot/Engineer/Scientist: (Specializations found in the project, likely providing utility or ship-based bonuses).
4. Enemy Types (Threat Specializations)
Threats feature unique AI behaviors and "Special" moves:
•
Pirate: A cunning foe that steals EXP from your crew members to buff its own power.
•
Alien: Provides consistent pressurre by dealing damage to multiple crew members simultaneously.
•
Demon: Becomes more dangerous over time by stacking buffs that scale its damage.
•
Gundam: A heavy hitter that uses high-output special attacks but sustains recoil damage.
•
Parasite: Sustains itself by healing for a portion of the damage it deals to the crew.
5. Technical Architecture
•
Entity System: Utilizes a robust inheritance hierarchy (CombatActor interface → Threat/Character abstract classes) to ensure consistent combat logic across all entities.
•
Mission Logic: Managed via a Mission controller that handles turn-order, target validation (null-safety), and win/loss conditions.
•
UI Framework: Built with Android-native components, utilizing RecyclerView for crew management and Fragments for different ship rooms.
•
Generation System: Features a ThreatAnalysis engine for procedural enemy generation based on difficulty selections.
6. Key Mechanics
•
EXP Scaling: Unlike traditional RPGs where level is a static number, "EXP" is often used directly in damage formulas, making every point gained feel impactful in real-time.
•
Targeting Logic: The game uses a randomized targeting system for enemies, requiring players to keep both crew members healthy to avoid a single point of failure..
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
