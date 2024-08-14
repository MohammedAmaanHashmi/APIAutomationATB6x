package com.thetestingacademy.tests.integration;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojo.Booking;
import com.thetestingacademy.pojo.BookingResponse;
import com.thetestingacademy.utils.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TCIntegrationFlow extends BaseTest {


    //Create A Booking, Create a Token
    //Get Booking
    //Update the booking
    //Delete the booking


    @Test(groups = "integeration", priority = 1)
    @Owner("Amaan")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be created")
    public void testCreateBooking(ITestContext iTestContext) {
        iTestContext.setAttribute("token", getToken());
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

        //Set the booking
        iTestContext.setAttribute("bookingid", bookingResponse.getbookingid());


    }

    @Test(groups = "integeration", priority = 2)
    @Owner("Amaan")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingID(ITestContext iTestContext) {
        //Get
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");


        //rest
        // Get Req
        String basePathGet = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathGet);
        response = RestAssured
                .given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        System.out.println(iTestContext.getAttribute("bookingid"));
        System.out.println("Token - " + iTestContext.getAttribute("token"));

        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJson(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        System.out.println(PropertyReader.readKey("booking.firstname"));
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.firstname"));
    }

    @Test(groups = "integeration", priority = 3)
    @Owner("Amaan")
    @Description("TC#INT1 - Step 3. Verify Updated Booking ID")
    public void testUpdateBookingById(ITestContext iTestContext) {
        System.out.println("Token - " + iTestContext.getAttribute("token"));
        String token = (String) iTestContext.getAttribute("token");

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathPutPatch = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPutPatch);

        requestSpecification.basePath(basePathPutPatch);
        response = RestAssured
                .given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


        Booking booking = payloadManager.getResponseFromJson(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.put.firstname"));
        assertThat(booking.getLastname()).isEqualTo("Kang");


    }

    @Test(groups = "integeration", priority = 4)
    @Owner("Amaan")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {
        System.out.println("Token - " + iTestContext.getAttribute("token"));
        String token = (String) iTestContext.getAttribute("token");

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDelete = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathDelete);
        requestSpecification.basePath(basePathDelete).cookie("token", token);
        validatableResponse = RestAssured.given(requestSpecification).when().delete().then().log().all();
        validatableResponse.statusCode(201);

    }
}
