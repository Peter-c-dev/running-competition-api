package org.example.runcomp;

public class RunningCompetition {
    static {
        System.loadLibrary("RunningCompNative");
    }

    public native double calculatePace(double distance, double time);

    public static void main(String[] args) {
        RunningCompetition rc = new RunningCompetition();
        double distance = 10.0;
        double time = 50.0;
        double pace = rc.calculatePace(distance, time);
        System.out.println("Your pace is: " + pace + " minutes/km");
    }
}
