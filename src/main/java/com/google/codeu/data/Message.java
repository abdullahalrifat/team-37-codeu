/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.codeu.data;

import java.util.UUID;
import java.util.Set;

/** A single message posted by a user. */
public class Message {

  private UUID id;
  private String user;
  private String text;
  private String city;
  private double alat;
  private double along;
  private long timestamp;

  /**
   * Constructs a new {@link Message} posted by {@code user} with {@code text} content. Generates a
   * random ID and uses the current system time for the creation time.
   */
  public Message(String user, String text,String city,double alat,double along) {
    this(UUID.randomUUID(), user, text,city,alat,along, System.currentTimeMillis());
  }

  public Message(UUID id, String user, String text,String city,double alat,double along, long timestamp) {
    this.id = id;
    this.user = user;
    this.text = text;
    this.city=city;
    this.alat=alat;
    this.along=along;
    this.timestamp = timestamp;
  }
  
  public Message(String user, String text) {
    this(UUID.randomUUID(), user, text, System.currentTimeMillis());
  }

  public Message(UUID id, String user, String text, long timestamp) {
    this.id = id;
    this.user = user;
    this.text = text;
    this.timestamp = timestamp;
  }

  public UUID getId() {
    return id;
  }

  public String getUser() {
    return user;
  }

  public String getText() {
    return text;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public double getAlat() {
    return alat;
  }

  public void setAlat(double alat) {
    this.alat = alat;
  }

  public double getAlong() {
    return along;
  }

  public void setAlong(double along) {
    this.along = along;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
