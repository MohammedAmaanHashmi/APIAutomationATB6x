package com.thetestingacademy.tests.ddt;

import com.thetestingacademy.utils.UtilsExcel;
import org.testng.annotations.Test;

public class vwoLoginApiTestTC {

    @Test(dataProvider = "getData", dataProviderClass = UtilsExcel.class)
    public void testVWOLogin(String email, String password) {
        System.out.println("----Login API Testing");
        System.out.println(email);
        System.out.println(password);

        vwo
    }
}
