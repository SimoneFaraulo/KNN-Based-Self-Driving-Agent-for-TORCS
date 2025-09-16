# Autonomous Driving Agent with KNN Classifier 🏎️

## Overview 📖

This project was developed as part of the **Artificial Intelligence: Methods and Applications (2023–2024)** course and presented at the **Granpremio MIVIA 2024 Final Contest**.

The goal of the contest was to design and implement an autonomous driving agent capable of completing a lap in the shortest possible time using machine learning techniques.  
Our solution adopts a **K-Nearest Neighbors (KNN)** classifier integrated into the **TORCS** racing simulator through a custom **Java client**.

The repository includes:
- `code/` – full Java source code of the TORCS client implementing the KNN algorithm
- `demo_videos/` – demonstration videos showcasing the autonomous driving performance
- `report.pdf` – a detailed report (in Italian) describing design choices, dataset preparation, experiments, and results

---

## System Description ⚙️

The Java client communicates with the TORCS server via **UDP protocol** and manages driving based on configurable parameters.  
The approach follows **behavioral cloning**, where the agent learns from human driving demonstrations using sensor–action pairs collected in a dataset.

---

## Modes of Operation 🎮

**Manual Driving**  
Control the car manually via keyboard. During this mode, you can record race data into `dataset.csv` for training.

**Autonomous Driving**  
The KNN-based agent drives autonomously using the pre-collected dataset.

---

## Dataset Format 📊

The dataset must be named `dataset.csv` and structured as follows:

```csv
speed,angle,distance_from_center,steering,acceleration,brake
123.4,0.02,0.1,-0.05,1.0,0.0
125.1,0.01,0.0,0.00,0.9,0.0
119.8,-0.03,-0.2,0.07,0.8,0.1
```

You can replace the dataset with a new one, provided it matches this format.

---

## Installation & Usage 🖥️

1. Launch TORCS and configure the race with `scr_server 1` as the race player.
2. Compile the client source code:

```bash
cd code
javac -d bin src/*.java
```

3. Start a race in TORCS, then run the client:

```bash
java -cp bin MainClient
```

4. Select the execution mode:
   - `m` → Manual driving
   - `a` → Autonomous driving (KNN classifier)

**During manual mode logging:**
- Press `q` in the JFrame to start recording
- Press `e` to stop recording

---

## Recommendations for Testing 🧪

- Use a plugged-in computer to ensure stable performance.
- Close unnecessary background applications (e.g., browsers) to prevent slowdowns affecting KNN execution.

---

## Deliverables 📦

According to the project requirements, the submission includes:
- Commented source code (`code/`)
- Demonstration videos (`demo_videos/`)
- Written report (`report.pdf`, in Italian) detailing:
  - Classifier design choices
  - Experiments conducted
  - Final evaluation of the KNN model
