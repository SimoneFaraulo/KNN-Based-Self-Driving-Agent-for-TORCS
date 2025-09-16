package scr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KNearestNeighbor {

    private List<Sample> trainingData;
    private KDTree kdtree;
    private String firstLineOfTheFile; // Assuming the CSV file format
    
    public KNearestNeighbor(String filename) {
        this.trainingData = new ArrayList<>();
        this.kdtree = null;
        this.firstLineOfTheFile = "ANGLE_SENS;GEAR_SENS;RPM_SENS;SPEEDX_SENS;SPEEDY_SENS;SPEEDZ_SENS;TRACK_SENS;TRACK_POS_SENS;ACCEL_ACT;BRAKE_ACT;CLUTCH_ACT;GEAR_ACT;STEERING_ACT"; // Assuming CSV format has this header
        this.readPointsFromCSV(filename);
    }

    private void readPointsFromCSV(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(firstLineOfTheFile)) {
                    continue; // Skip header
                }
                // Assuming Sample constructor from String is defined
                trainingData.add(new Sample(line));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.kdtree = new KDTree(trainingData); // Initialize KDTree using the read points
    }

    public List<Sample> findKNearestNeighbors(Sample testPoint, int k) {
        return kdtree.kNearestNeighbors(testPoint, k);
    }

    public Classe classify(Sample testPoint, int k) {
        //Trova i k-newarest neighbors del punto di test
        List<Sample> kNearestNeighbors = findKNearestNeighbors(testPoint, k);

        //Liste per memorizzare i valori degli attuatori dei k-nearest neighbors
        List<Double> acceleration = new ArrayList<>(k);
        List<Double> steering = new ArrayList<>(k);
        List<Double> brake = new ArrayList<>(k);
        List<Integer> gear = new ArrayList<>(k);

        //Itera sui k-nearest neighbors e aggiunge i valori degli attuatori alle
        //rispettive liste
        for (int i = 0; i < k; i++) {
            acceleration.add(kNearestNeighbors.get(i).cls.getAccelerate());
            steering.add(kNearestNeighbors.get(i).cls.getSteering());
            gear.add(kNearestNeighbors.get(i).cls.getGear());
            brake.add(kNearestNeighbors.get(i).cls.getBrake());
        }

        //Ordina i valori degli attuatori
        acceleration.sort(null);
        steering.sort(null);
        gear.sort(null);
        brake.sort(null);

        //Calcola l'indice della mediana        
        int medianIndex = (k + 1) / 2;

        //Stampa le liste ordinate dei valori degli attuatori
        System.out.println("Accelerazione: "+acceleration+" Steering: "+steering+ " Gear: "+gear+" Freno: "+brake);
        
        //Ritorna una nuova istanza della classe Classe usando i valori medi della
        //mediana
        return new Classe(
                acceleration.get(medianIndex),
                brake.get(medianIndex),
                gear.get(medianIndex),
                steering.get(medianIndex));
                
    }
    

    public List<Sample> getTrainingData() {
        return trainingData;
    }
}