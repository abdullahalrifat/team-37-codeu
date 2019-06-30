package com.google.codeu.data;
import java.util.UUID;

public class Mates
{
    private UUID id;
    private String user;
    private double alat;
    private double along;
    private String details;
    private long timestamp;

    private static double defaultLat = 23.7474824;

    private static double defaultLong = 90.36420079999999;
    /**
     * Constructs a new {@link Message} posted by {@code user} with {@code text} content. Generates a
     * random ID and uses the current system time for the creation time.
     */
    public Mates(String user, double alat,double along,String details) {
        this(UUID.randomUUID(), user, alat,along,details, System.currentTimeMillis());
    }

    public Mates(UUID id, String user,double alat,double along, long timestamp) {
        this.id = id;
        this.user = user;
        this.alat = alat;
        this.along = along;
        this.timestamp = timestamp;
    }
    public Mates(UUID id, String user,double alat,double along, String details,long timestamp) {
        this.id = id;
        this.user = user;
        this.alat = alat;
        this.along = along;
        this.details = details;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public double getLat() {
        return alat;
    }

    public double getLong() {
        return along;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
