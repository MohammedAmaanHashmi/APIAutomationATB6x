package com.thetestingacademy.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.thetestingacademy.pojo.*;

public class PayloadManager {

    Gson gson;

    public String createPayloadBookingAsString() {
        Booking booking = new Booking();
        Faker faker = new Faker();
        booking.setFirstname("Amaan");
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1000));
        booking.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2024-02-01");
        bookingDates.setCheckout("2024-02-01");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");
        gson = new Gson();
        String jsonPayload = gson.toJson(booking);
        return jsonPayload;
    }

    public String fullUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Song");
        booking.setLastname("Kang");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2024-02-01");
        bookingDates.setCheckout("2024-02-01");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");
        gson = new Gson();
        String jsonPayload = gson.toJson(booking);
        return jsonPayload;
    }


    public BookingResponse bookingResponseJava(String reponseString) {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(reponseString, BookingResponse.class);
        return bookingResponse;
    }

    public String createInvalidPayloadBookingAsString() {

        return "{}";
    }

    public String setAuthPayLoad() {
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println(" payload set to " + jsonPayloadString);
        return jsonPayloadString;
    }

    public String getTokenFromJson(String tokenResponse) {
        gson = new Gson();
        //Response(JSON) -> object TokenResponse
        //Deserialization

        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken();
    }

    public Booking getResponseFromJson(String getResponse) {
        gson = new Gson();
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;
    }
}
