package org.example.runcomp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "runs")
public class Run {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double distance; // stored in KM
    private double time;

    @ManyToOne
    @JoinColumn(name = "user_id")   // this creates a user_id column in the runs table
    private User user;

    public Run() {}

    public Long getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // KM → Miles converter
    @Transient
    public double getMiles() {
        return distance * 0.621371;
    }
}
