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

## Usage 🖥️

1. Open the terminal and the **Torcs** application.
2. Move into the `src` folder where the client source code is located and compile it using:

   ```bash
   javac -d ../classes src/*.java
   ```

3. After compilation, start a new race from the Torcs application, ensuring that the race player is configured as `scr_server 1`.
4. Once the race has started (Torcs will wait for a client connection), move into the `classes` folder from the terminal and run the client with:

   ```bash
   java scr.Client scr.SimpleDriver host:localhost port:3001 verbose on
   ```

5. From the terminal, choose the execution mode:

   - **Manual Driving**  
     Enter the character `m` and press Enter.  
     Use `w` to accelerate, `a` to steer left, `d` to steer right, and `s` to brake or reverse.  
     To start recording race data into `dataset.csv`, press `q` in the JFrame that appears on screen.  
     To stop recording, press `e`.

   - **Autonomous Driving**  
     Enter the character `a` and press Enter.  
     The car will drive autonomously using the data in `dataset.csv`.

---

## Dataset 📊

If you want to replace the dataset with an existing one for testing:

1. Go to the `classes` folder.
2. Delete the existing `dataset.csv`.
3. Place your new CSV dataset in the same folder and rename it to `dataset.csv`.

The dataset must follow this format:

```csv
"ANGLE_SENS(Double);GEAR_SENS(Int);RPM_SENS(Double);SPEEDX_SENS(Double);SPEEDY_SENS(Double);SPEEDZ_SENS(Double);TRACK_SENS(Double[]);TRACK_POS_SENS(Double);ACCEL_ACT(Double);BRAKE_ACT(Double);CLUTCH_ACT(Double);GEAR_ACT(Int);STEERING_ACT(Double)"
```

---

## Recommendations for Testing 🧪

- Use a plugged-in computer to ensure maximum performance and avoid slowdowns.
- Close unnecessary background applications (e.g., browsers), as they may reduce the performance of the KNN algorithm and affect lap completion times.

---

## Deliverables 📦

According to the project requirements, the submission includes:
- Commented source code (`code/`)
- Demonstration videos (`demo_videos/`)
- Written report (`report.pdf`, in Italian) detailing:
  - Classifier design choices
  - Experiments conducted
  - Final evaluation of the KNN model
