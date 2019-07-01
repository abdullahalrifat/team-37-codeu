package com.google.codeu.data;
import java.util.UUID;

public class Guides
{
	private UUID id;
    private String name;
    private String address;
    private double contact_no;
    private String gender;
    private String location;
    private double charge;
    private long timestamp;

    /**
     * Constructs a new {@link Message} posted by {@code user} with {@code text} content. Generates a
     * random ID and uses the current system time for the creation time.
     */
    public Guides(String name, String address, double contact_no, String gender, String location, double charge ) {
        this(UUID.randomUUID(), name, address, contact_no, gender, location, charge, System.currentTimeMillis());
    }

    public Guides(UUID id, String name, String address, double contact_no, String gender, String location, double charge, long timestamp) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact_no = contact_no;
        this.gender = gender;
        this.location = location;
        this.charge = charge;
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

    public double getContactNo() {
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

    public long getTimestamp() {
        return timestamp;
    }
}