package com.thetestingacademy.tests.integration.sample;

import com.thetestingacademy.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TCIntegration extends BaseTest {


    //Create A Booking, Create a Token
    //Get Booking
    //Update the booking
    //Delete the booking


    @Test(groups = "integeration", priority = 1)
    @Owner("Amaan")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be created")
    public void testCreateBooking() {
        Assert.assertTrue(true);

    }

    @Test(groups = "integeration", priority = 2)
    @Owner("Amaan")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingID() {

        Assert.assertTrue(true);

    }

    @Test(groups = "integeration", priority = 3)
    @Owner("Amaan")
    @Description("TC#INT1 - Step 3. Verify Updated Booking ID")
    public void testUpdateBookingById() {
        Assert.assertTrue(true);
    }

    @Test(groups = "integeration", priority = 4)
    @Owner("Amaan")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById() {
        Assert.assertTrue(true);
    }
}
