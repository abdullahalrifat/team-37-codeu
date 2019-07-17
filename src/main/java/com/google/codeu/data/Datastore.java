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

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.FetchOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;
import java.io.*;

/** Provides access to the data stored in Datastore. */
public class Datastore {
  private DatastoreService datastore;
  public Datastore() {
  datastore = DatastoreServiceFactory.getDatastoreService();
  }
  /** Stores the Message in Datastore. */
  public void storeMessage(Message message) {
    Entity messageEntity = new Entity("Message", message.getId().toString());
    messageEntity.setProperty("user", message.getUser());
    messageEntity.setProperty("text", message.getText());
    messageEntity.setProperty("timestamp", message.getTimestamp());
    datastore.put(messageEntity);
  }
  /**
   * Gets messages posted by a specific user.
   *
   * @return a list of messages posted by the user, or empty list if user has never posted a
   *     message. List is sorted by time descending.
   */
  public List<Message> getMessages(String user) {
    List<Message> messages = new ArrayList<>();
    Query query =
        new Query("Message")
        .setFilter(new Query.FilterPredicate("user", FilterOperator.EQUAL, user))
        .addSort("timestamp", SortDirection.DESCENDING);
        PreparedQuery results = datastore.prepare(query);
    for (Entity entity : results.asIterable()) {
      try {
        String idString = entity.getKey().getName();
        UUID id = UUID.fromString(idString);
        String text = (String) entity.getProperty("text");
        long timestamp = (long) entity.getProperty("timestamp");
        Message message = new Message(id, user, text, timestamp);
        messages.add(message);
      } catch (Exception e) {
        System.err.println("Error reading message.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }
    return messages;
  } 
  /**
   * Gets messages posted by all users.
   *
   * @return a list of messages posted by all users, or empty list if no user has never posted a
   *     message. List is sorted by time descending.
   */
  public List<Message> timeline() {
  List<Message> messages = new ArrayList<>();
  Query query =
      new Query("Message")
          //.setFilter(new Query.FilterPredicate("user", FilterOperator.EQUAL, user))
           .addSort("timestamp", SortDirection.DESCENDING);
   PreparedQuery results = datastore.prepare(query);
   for (Entity entity : results.asIterable()) {
      try {
        String idString = entity.getKey().getName();
        UUID id = UUID.fromString(idString);
        String text = (String) entity.getProperty("text");
        String user = (String) entity.getProperty("user");
        long timestamp = (long) entity.getProperty("timestamp");
        Message message = new Message(id, user, text, timestamp);
        messages.add(message);
      } catch (Exception e) {
        System.err.println("Error reading message.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }
    return messages;
  }
    /**
     * Gets specific mate details
     *
     * @return a list of messages posted by the user, or empty list if user has never posted a
     *     message. List is sorted by time descending.
     */
    public List<Mates> getMate(String user) {
        List<Mates> mates = new ArrayList<>();
        Query query =
            new Query("Mates")
                .setFilter(new Query.FilterPredicate("user", FilterOperator.EQUAL, user))
                .addSort("timestamp", SortDirection.DESCENDING);
        PreparedQuery results = datastore.prepare(query);
        for (Entity entity : results.asIterable()) {
            try {
                String idString = entity.getKey().getName();
                UUID id = UUID.fromString(idString);
                double alat = (Double) entity.getProperty("alat");
                double along = (Double) entity.getProperty("along");
                String details = (String)entity.getProperty("details");
                long timestamp = (long) entity.getProperty("timestamp");


                Mates mate = new Mates(id, user, alat,along,details, timestamp);

                mates.add(mate);
            } catch (Exception e) {
                System.err.println("Error reading message.");
                System.err.println(entity.toString());
                e.printStackTrace();
            }
        }
        return mates;
    }
    /**
     * Gets all mates details
     *
     * @return a list of messages posted by all users, or empty list if no user has never posted a
     *     message. List is sorted by time descending.
     */
    public List<Mates> getAllMates() {
        List<Mates> mates = new ArrayList<>();
        Query query =
            new Query("Mates")
                //.setFilter(new Query.FilterPredicate("user", FilterOperator.EQUAL, user))
                  .addSort("timestamp", SortDirection.DESCENDING);
        PreparedQuery results = datastore.prepare(query);
        for (Entity entity : results.asIterable()) {
            try {
                String idString = entity.getKey().getName();
                UUID id = UUID.fromString(idString);
                //String text = (String) entity.getProperty("text");
                String user = (String) entity.getProperty("user");
                double alat = (Double) entity.getProperty("alat");
                double along = (Double) entity.getProperty("along");
                String details = (String)entity.getProperty("details");
                long timestamp = (long) entity.getProperty("timestamp");

                Mates mate = new Mates(id, user, alat,along,details, timestamp);

                mates.add(mate);
            } catch (Exception e) {
                System.err.println("Error reading message.");
                System.err.println(entity.toString());
                e.printStackTrace();
            }
        }
        return mates;
    }
    /** Stores the Mates in Datastore. */
    public void storeMates(Mates mate) {
        Entity messageEntity = new Entity("Mates", mate.getId().toString());
        messageEntity.setProperty("user", mate.getUser());
        messageEntity.setProperty("alat", mate.getLat());
        messageEntity.setProperty("along", mate.getLong());
        messageEntity.setProperty("details", mate.getDetails());
        messageEntity.setProperty("timestamp", mate.getTimestamp());
        datastore.put(messageEntity);
    }
    /**
   * Gets all users.
   *
   * @return a set of users, or an empty set if there is no user.
   */

    public List<Guides> getAllGuide() {
        List<Guides> guides = new ArrayList<>();

        Query query =
                new Query("Guides")
                        //.setFilter(new Query.FilterPredicate("user", FilterOperator.EQUAL, user))
                        .addSort("timestamp", SortDirection.DESCENDING);
        PreparedQuery results = datastore.prepare(query);

        for (Entity entity : results.asIterable()) {
            try {
                String idString = entity.getKey().getName();
                UUID id = UUID.fromString(idString);
                //String text = (String) entity.getProperty("text");
                String name = (String) entity.getProperty("name");
                String address = (String) entity.getProperty("address");
                double contact_no = (Double) entity.getProperty("contact_no");
                String gender = (String) entity.getProperty("gender");
                String location = (String) entity.getProperty("location");
                double charge = (Double) entity.getProperty("charge");
                double alat = (Double) entity.getProperty("alat");
                double along = (Double) entity.getProperty("along");
                long timestamp = (long) entity.getProperty("timestamp");

                Guides guide = new Guides(id, name, address, contact_no, gender, location, charge, alat, along, timestamp);
                guides.add(guide);
            } catch (Exception e) {
                System.err.println("Error reading message.");
                System.err.println(entity.toString());
                e.printStackTrace();
            }
        }

        return guides;
    }

    public void storeGuide(Guides guide) {
        Entity messageEntity = new Entity("Guides", guide.getId().toString());
        messageEntity.setProperty("name", guide.getName());
        messageEntity.setProperty("address", guide.getAddress());
        messageEntity.setProperty("contact_no", guide.getContactNo());
        messageEntity.setProperty("gender", guide.getGender());
        messageEntity.setProperty("location", guide.getLocation());
        messageEntity.setProperty("charge", guide.getCharge());
        messageEntity.setProperty("alat", guide.getLat());
        messageEntity.setProperty("along", guide.getLong());
        messageEntity.setProperty("timestamp", guide.getTimestamp());

        datastore.put(messageEntity);
    }
   
   public Set<String> getUsers(){
       Set<String> users = new HashSet<>();
       Query query = new Query("Message");
       PreparedQuery results = datastore.prepare(query);
       for(Entity entity : results.asIterable()) {
           users.add((String) entity.getProperty("user"));
       }
	return users;
 }
	/**
   * Gets total number of messages posted by all users.
   *
   * @return the number of total messages posted by all users, or 0 if no user has never posted a message.
   */   
  public int getTotalMessageCount() {
	  Query query = new Query("Message");
	  PreparedQuery results = datastore.prepare(query);
	  return results.countEntities(FetchOptions.Builder.withLimit(1000));
	}
	/**
   * Gets total number of users.
   *
   * @return the number of total users, or 0 if there's no user yet.
   */  
  public int getTotalUserCount() {
	  return getUsers().size();
	}
	/**
   * Gets average length of messages.
   *
   * @return the average message length, or 0 if no user has never posted a message.
   */
  public int getAverageMessageLength() {
	  Query query = new Query("Message");
	  PreparedQuery results = datastore.prepare(query);
	  int length = 0;
	  for(Entity entity : results.asIterable())
	    length +=  ((String) entity.getProperty("text")).length();
	  int average = (int) length / getTotalMessageCount();
	  return average;  
	}
	/**
   * Gets most active user(s).
   *
   * @return a list of the name(s) of the most active user(s), or empty list if there's no user.
   */ 
  public String getMostActiveUser() {  
	  Set<String> users = getUsers();
	  String mostActiveUser = new String();
	  int messages = 0;
	  for(String user : users) {
		int userMessagesCount = getMessages(user).size();
		if (userMessagesCount > messages) {
			messages = userMessagesCount;
			mostActiveUser = user;
		}
		else if (userMessagesCount != 0 && userMessagesCount == messages)
			mostActiveUser = mostActiveUser + ", " + user;
	  }	  
	  return mostActiveUser;
  }
	 /** Stores the User in Datastore. */
	 public void storeUser(User user) {
	  Entity userEntity = new Entity("User", user.getEmail());
	  userEntity.setProperty("email", user.getEmail());
	  userEntity.setProperty("aboutMe", user.getAboutMe());
	  datastore.put(userEntity);
	 }
	 /**
	  * Returns the User owned by the email address, or
	  * null if no matching User was found.
	  */
	 public User getUser(String email) {
	  Query query = new Query("User")
		.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, email));
	  PreparedQuery results = datastore.prepare(query);
	  Entity userEntity = results.asSingleEntity();
	  if(userEntity == null) {
	   return null;
	  }
	  String aboutMe = (String) userEntity.getProperty("aboutMe");
	  User user = new User(email, aboutMe);
	  return user;
	 }
}
