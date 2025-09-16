package scr;


import java.util.Arrays;

/**
 * Questa classe va cambiata sulla base del vostro vettore di feature.
 * Per ora, considero: 
 * un vettore di feature di 3 double (x,y,z) e una classe che è un intero.
 */

public class Sample {
    double angle;
    double speedX;
    double speedY;
    double track[];
    double track_pos;
    Classe cls;

    /*
    Chiamo questo costruttore quando ho la classe di appartenenza e sto costruendo il dataset. 
    In alternativa, quando voglio classificare un nuovo campione, uso l'altro costruttore.
    */
    public Sample(double angle, double speedX,double speedY, double[] track,double track_pos, Classe cls) {
        this.angle = angle;
        this.speedX = speedX;
        this.speedY = speedY;
        this.track = track;
        this.track_pos = track_pos;
        this.cls = cls;
    }

    public Sample(double angle, double speedX,double speedY, double[] track, double track_pos) {
        this.angle = angle;
        this.speedX = speedX;
        this.speedY = speedY;
        this.track = track;
        this.track_pos = track_pos;
        this.cls = null;
    }

    /*
    Questo costruttore prende la stringa dal file csv e costruisce il Sample
    */
    public Sample (String line){
        String[] parts = line.split(";");
        this.angle = Double.parseDouble(parts[0].trim());
        this.speedX = Double.parseDouble(parts[3].trim());
        this.speedY = Double.parseDouble(parts[4].trim());
        String track = parts[6].trim();
        this.track_pos = Double.parseDouble(parts[7].trim());

        track = track.replaceAll("[\\[\\],]", "");

        // Dividere la stringa in un array di sottostringhe
        String[] numeriStringa = track.split("\\s+");

        // Creare un array di double
        double[] numeriDouble = new double[numeriStringa.length];

        // Convertire ciascuna sottostringa in double
        for (int i = 0; i < numeriStringa.length; i++) {
            numeriDouble[i] = Double.parseDouble(numeriStringa[i]);
        }

        this.track = numeriDouble;

        this.cls = new Classe();

        this.cls.setAccelerate(Double.parseDouble(parts[8].trim()));
        this.cls.setBrake(Double.parseDouble(parts[9].trim()));
        this.cls.setGear(Integer.parseInt(parts[11].trim()));
        this.cls.setSteering(Double.parseDouble(parts[12].trim()));

    }
 
    
    public double distance(Sample other) {
        //Inizializza la somma delle differenze quadrate del sensore track a 0.0    
        double sumOfSquareDifferences = 0.0;

        //Controlla se gli array track di this e other hanno la stessa lunghezza
        if (this.track.length == other.track.length) {
            //Calcola la somma delle differenze quadrate per ciascun elemento dell'array
            //track
            for (int i = 0; i < this.track.length; i++) {
                sumOfSquareDifferences += Math.pow(this.track[i] - other.track[i], 2);
            }
        } else {
            //Lancia un'eccezione se gli array track non hanno la stessa lunghezza
            throw new IllegalArgumentException("Gli array track non hanno la stessa lunghezza.");
        }

        //Calcola e ritorna la distanza euclidea combinando tutte le differenze
        return Math.sqrt(Math.pow(this.angle - other.angle, 2) + Math.pow(this.speedX - other.speedX, 2) +Math.pow(this.speedY -other.speedY,2)
        + sumOfSquareDifferences+ Math.pow(this.track_pos - other.track_pos,2));
    }
    
    
}