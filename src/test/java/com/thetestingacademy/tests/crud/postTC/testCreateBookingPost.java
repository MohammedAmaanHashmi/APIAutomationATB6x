package com.thetestingacademy.tests.crud.postTC;

import com.google.gson.Gson;
import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojo.Auth;
import com.thetestingacademy.pojo.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class testCreateBookingPost extends BaseTest {

    Gson gson;

    @Test
    @Owner("Amaan")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#1 - Verify that the Booking can be created")
    public void testCreateBooking() {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


        //DeSer - response
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        //AssertJ
        assertThat(bookingResponse.getBooking()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Amaan");

        assertActions.verifyStatusCode(response, 200);

    }


    @Test
    @Owner("Amaan")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#1 - Verify that the Booking can be created")
    public void testCreateBookingNegative() {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.createInvalidPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);
    }


    public String setPayload() {
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setUsername("password123");
        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to " + gson.toJson(auth));
        return gson.toJson(auth);
    }
}
