package com.thetestingacademy.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//open that FileInputStream
//Understand the workbook
//Sheet
//Row
//Column
//Cell
//Close the Stream
public class UtilsExcel {

    // It will be used who - DataProvide of the TestNG
    //Object[][]

    public static String FILE_NAME = "src/test/resources/TD.xlsx";
    static Workbook book;
    static Sheet sheet;

    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

                data[i][j] = sheet.getRow(i + 1).getCell(j).toString();

            }
        }


        return data;
    }


    @DataProvider
    public Object[][] getData() {
        // In future I can write logic to select which sheet I want to open
        //Ask user which sheet to open
        //Data.properties -> Sheet 1 or Sheet 2
        //Sheet 1 -u/p -QA
        //Sheet 2 ->u/p -prod
        return getTestData("Sheet1");
    }
}
