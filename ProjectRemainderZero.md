👥 Group Information
**Group Name:** Project Remainder Zero 
**Course:** Object-Oriented Programming (Spring 2026)
**University:** LUT

| Name | Role | GitHub |
| Matthew Laughlin | Focus on Combat UI and Backend Combat Logic | fabled_03  |
| Pavel Pavlov     | OOP Design, entity logic/structure, UI      | pavp-cloud |
| Heikki Kornmann | overall game design, UI, fragments, menu systems and logic | heikkikornmann-bit |


---

# 🚀 Project Remainder Zero

**Project Remainder Zero** is a tactical, turn-based Android RPG where you command a spaceship and its crew through the dangers of deep space. Balance resource management, crew deployment, and strategic combat as you survive against an ever-growing list of extraterrestrial threats.

**Project Remainder Zero Lore** in the year 2133 an event known as the Collision seperated humanity from itself. In years before 2133, humanity had grown complacent in its place in the Universe. No longer is there fear of what space has in store for humanity, and space travel had become a rutine event. Then, as though lightning striking in a storm, a worm hole opened up right next to Earth, and behind that worm hole, a black hole that engulfed the entire Sol system in matter of seconds. Once being an advance race, now the pieces of humanity that survived in space ships scattered through the rest of the galaxy begin a fight for survival, trying to find the truth behind the Collision, or dying out till none remain. Humanity thought it was alone in the Universe, till it become evident that space isn't just a void, but an ecosystem ready to slaughter the weak like cattle. Can you survive long enough to ensure that humanity survives?

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
    *    *Special Ability:* **Heal**- Can heal self to stay in combat longer than the advisary. 
*   **Engineer & Scientist:** Utility specialists that provide technical support and unique combat modifiers.
    *   *Special Abilities:* **Combat Armor** Take less damage so you can survive anything.
    *   *Special Abilities:* **Exp Potion** Grow more powerful, quicker, with a secret formula.
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
- **Object Oriented Code:** - this has been implemented, see code and class diagram.
- **Code in English:** - see code
- **Basic Functionality:** - refer to video, code, above, or play test.
- **Documentation:** - refer to this md and readme. 

### 6. Bonus Features Implemented - Locations: 
- **Recyler Viewer** - TrainingRoomFragment, PassengerManifestFragement, etc. 
- **Crew Images as Sprites** -CombatView 
- **Mission Visualization** - CombatView 
- **Tatical Combat** - Mission 
- **Statistics** - PassengerManifest 
- **Randomness in Mission** - Mission, MissionControl, Threats, CrewQuarters
-  **Specialization Bonuses** - Threat and Characters
-  **Fragments** - CombatView Fragment, MissionControl Fragment, Tutorial Fragment, etc.
-  **Statics Visulaiztation** - PassengerManifest
-  **Own Creation** - Ship will take damage when failing a mission, and a lot more features. 

---

## 📐 Architecture overview
Below is the architectural overview of the project, highlighting the inheritance hierarchies for Crew Members and Threats.

<img width="1182" height="372" alt="image" src="https://github.com/user-attachments/assets/2486e3d2-6743-4915-ab6b-407efb2c201d" />


<img width="932" height="595" alt="image" src="https://github.com/user-attachments/assets/df7f9792-2c4f-43e7-a152-76ae5dc08fee" />


<img width="1045" height="415" alt="image" src="https://github.com/user-attachments/assets/e71ff2bb-8ace-4f36-a1f0-1b15e5c7f26e" />


<img width="1001" height="515" alt="image" src="https://github.com/user-attachments/assets/e8d48d52-507b-43dc-ab92-ec50d53272ad" />




https://miro.com/welcomeonboard/Z3JvdmErRnczSk1wUExWYnBRbWNNUEZJZFhIN2o5Yk1qV1BlS3JSYUNBL3FsaFFqQi9DVXZwTWI4M0EyWWRMT2Fzcy9kTFRrd3FiWHZKcTZ0bjZxQmd0YjM5WndPaGppZDhoMCtvc0txcmp2aGlkcmUvdUgxVHdWcWYvc2hkclVzVXVvMm53MW9OWFg5bkJoVXZxdFhRPT0hdjE=?share_link_id=104402814781





---

## 🤝 Division of Work
- Designing, Scaling, and Logic was done as a group effort, with all members meeting accordingly

### Matthew Laughlin 
- Designed and created the entire CombatView. This includes adding sprites, toasts, animations, and buttons. This was done in order to add a degree of customization and to practice with working with coding UI inside Java.
- Worked on the Mission class, working on playerTurn, executeMission, isGameOver, endMission, enemyTurn, and allowedCrewMembers. My work included playerTurn logic and enemyTurn logic with focus on setting up combat to execute properly and triggering the correct events when conditions were met. I also ensured that crew members were returned to their proper places after combat had occured. Then worked to connect all of these to the UI elements of the CombatView. 
- I also worked on minor details inside of SpaceShip, Threat, and Mission Room. This included helping with Threat logic on the targeting of the two crew members with basic and special attacks, resetGame, and creating the array list for the Threat names.

### Pavel Pavlov
- Designed and worked on the entity classes, including the abstract classes, interfaces, individual specialization classes and their methods.
- Managed RecyclerView implementation as well as certain other UI functionality.
- Managed the project organization, set up the package structure and helped ensure the project follows proper Object Oriented principles (encapsulation, inheritance, polymorphism, abstraction...) where necessary.
- Worked on implementation details inside classes to help eliminate basic coding issues (such as but not limited to avoidable code reuse and magic numbers)

### [Student Name 3]
- Designed and integrated **Sprite Assets** for all entities and backgrounds.
- Implemented the **`PassengerManifest`** and `RecyclerView` adapters for crew tracking.
- Created the **Training Room** mechanics and daily usage limitations.

## AI usage declaration
- No AI was used in designing the architecture of our game nor in creating the logic or design of how the gameplay would work
- Our xml files were made by us as well.
- We did however use gemini to learn and understand how to connect our UI elements together
- Gemini was also used in the creation of the combatview to learn and understand how it might be done. It was still created by us only with some help.
- Overall we feel our use of Gemini in the project was well within the guidelines. We used for learning and understanding and did not let it write our project for us.

## Sources used
- 
