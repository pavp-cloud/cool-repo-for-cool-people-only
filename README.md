# ⚔️ Remanider Zero 

> "In Space Nothing is Seen, In Space Nothing Survives, In Space... Nothing Remains" 

An Android-based turn-based RPG and management game developed for Object-Oriented Programming (OOP) principles. Command a crew, manage ship resources, and survive missions against evolving space threats.

<img width="171" height="377" alt="image" src="https://github.com/user-attachments/assets/54d14c51-6191-4729-a105-443e992e05ec" />



## 🚀 Game Overview

In Remainder Zero, you take command of a spaceship drifting through the void. Your goal is to survive as many days as possible by managing your crew, training them to improve their skills, and successfully completing dangerous missions agianst several different enemy types.

<img width="173" height="376" alt="image" src="https://github.com/user-attachments/assets/ec1c79fc-a6c5-4d80-bf03-01a1b9dabe79" />


### Key Features

- **Crew Management**: Onboard specialized crew members including Medics, Soldiers, Scientists, Pilots, and Engineers.
- **Turn-Based Combat**: Engage in tactical 2v1 battles against various threats (Aliens, Pirates, Gundams, Demons, Parasites).
- **Dynamic Progression**: Characters gain experience and level up their health and attack stats through missions and training.
- **Ship Survival**: Manage the ship's health. If the crew fails a mission, the ship takes damage proportional to the days adrift.
- **Persistent History**: Track the service record and status (Alive/Dead) of every crew member who has boarded your ship via the Passenger Manifest.

<img width="168" height="377" alt="image" src="https://github.com/user-attachments/assets/f00bcebb-8035-492a-aacb-7649de5e52c2" />

## 🛠 Architecture & OOP Principles

This project serves as a practical implementation of core Object-Oriented Programming concepts:

- **Inheritance & Abstraction**: 
    - Base `Character` class for all crew members with specialized subclasses (e.g., `Engineer`, `Medic`).
    - Base `Threat` class for all enemies with unique AI behaviors (e.g., `Alien` laser AOE, `Parasite` lifesteal).
- **Encapsulation**: Strict access control over ship resources and character stats.
- **Polymorphism**: Unified combat logic that handles diverse character actions and enemy patterns through method overriding.
- **Singleton Pattern**: The `SpaceShip` class uses a singleton instance to provide global access to ship systems (Mission Room, Crew Quarters, etc.).
- **Interface Design**: Implementation of listeners (e.g., `OnCombatEndedListener`) to decouple game logic from UI transitions.

## 🎮 How to Play

1.  **Onboard Crew**: Start by recruiting specialized members to your ship.
<img width="169" height="377" alt="image" src="https://github.com/user-attachments/assets/f712bb07-fa95-46c0-b4eb-d715e907c80f" />

2.  **Training**: Use the Training Room to increase your crew's XP before sending them into danger.
<img width="171" height="375" alt="image" src="https://github.com/user-attachments/assets/3badb60c-262d-48c9-8989-331e09348df5" />

3.  **Scan for Threats**: Use Mission Control to identify nearby threats in the sector.
<img width="168" height="377" alt="image" src="https://github.com/user-attachments/assets/f00bcebb-8035-492a-aacb-7649de5e52c2" />

4.  **Execute Missions**: Select two crew members and enter the Combat View.
    - Each character gets one turn to either perform a basic **Attack** or a class-specific **Special** move.
    - After the crew moves, the **Threat** retaliates.
  
<img width="173" height="376" alt="image" src="https://github.com/user-attachments/assets/55e5e91e-caf6-44ae-a6ad-bbd63c481ecc" />

5.  **Survive**: Complete the mission to return your survivors to the ship. If the ship health drops to 0, it's Game Over!

## 📦 Technical Details

- **Language**: Java
- **Platform**: Android
- **UI Components**: SurfaceView for high-performance combat rendering, Fragments for menu navigation, and RecyclerView for lists.
- **Threading**: Multi-threaded game loop to separate combat calculations from UI rendering, ensuring a smooth 60 FPS experience.

---

*Developed as part of the Spring 2026 Semester - Object Oriented Programming Exercises.*
