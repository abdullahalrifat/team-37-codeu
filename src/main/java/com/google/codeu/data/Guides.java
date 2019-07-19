package com.google.codeu.data;
import java.util.UUID;

public class Guides
{
	private UUID id;
    private String name;
    private String address;
    private String contact_no;
    private String gender;
    private String location;
    private double charge;
    private double alat;
    private double along;
    private long timestamp;
    private static double defaultLat = 23.7474824;
    private static double defaultLong = 90.36420079999999;
    /**
     * Constructs a new {@link Message} posted by {@code user} with {@code text} content. Generates a
     * random ID and uses the current system time for the creation time.
     */
    public Guides(String name, String address, String contact_no, String gender, String location, double charge, double alat,double along ) {
        this(UUID.randomUUID(), name, address, contact_no, gender, location, charge, alat, along, System.currentTimeMillis());
    }

    public Guides(UUID id, String name, String address, String contact_no, String gender, String location, double charge, double alat, double along, long timestamp) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact_no = contact_no;
        this.gender = gender;
        this.location = location;
        this.charge = charge;
        this.alat = alat;
        this.along = along;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNo() {
        return contact_no;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public double getCharge() {
        return charge;
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
}