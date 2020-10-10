package com.automationpractice.utilities;

import static com.automationpractice.utilities.PageUtils.driver;
import static com.automationpractice.utilities.PageUtils.currentDirectory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * This class has variables and methods related to Extent Report
 */
public class ExtentReportNG {

    public static ExtentReports extent;
    public static ExtentTest logger;
    public static String location;

    public static String suiteFolder = currentDirectory + "//Reports";
    public static String imageLocation = "/images";
    public static String folderName = "TestCaseReport_"
            + (new SimpleDateFormat("MMddyyyy_HHmmss").format(Calendar.getInstance().getTime()));
    public static String reportFolder = suiteFolder + "/" + folderName;

    /**
     * This class has Variables/Methods related to Extent Report
     *
     * @param reportName complete name of report folder
     */
    public static void initReport(String reportName) {
        File strreportFolder = new File(reportFolder);
        if (!strreportFolder.exists()) {
            strreportFolder.mkdir();
            System.out.println("Created a test case directory " + folderName);
        }

        extent = new ExtentReports(reportFolder + "//" + reportName + ".html");
        location = reportFolder + "/" + reportName + ".html";
    }

    /**
     * Logs start test
     *
     * @param name        name of the test
     * @param description description of the test
     */
    public static void startTest(String name, String description) {
        logger = extent.startTest(name, description);
    }

    /**
     * Logs various test steps
     *
     * @param status  contains test result- passed , failed or informative
     * @param comment regarding the test result
     * @param imgName specific name of the image
     */
    public static void logTest(LogStatus status, String comment, String imgName) {
        logger.log(status, comment + logger.addScreenCapture(screenshots(imgName)));
    }

    /**
     * Logs steps with auto-generated image name
     *
     * @param status  contains test result- passed , failed or informative
     * @param comment regarding the test result
     */
    public static void logTest(LogStatus status, String comment) {
        logger.log(status, comment + logger.addScreenCapture(randomScreenshots()));
    }

    /**
     * Logs end test
     */
    public static void endTest() {
        extent.endTest(logger);
    }

    /**
     * writes report
     */
    public static void writeReport() {
        extent.flush();
    }

    /**
     * takes screenshot
     *
     * @param imageName - Name with which image will be saved
     * @return name of the image
     */
    public static String screenshots(String imageName) {
        try {
            if (driver != null) {
                File scrFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile1, new File(reportFolder + imageLocation + "_" + imageName + ".jpg"));
            }
        } catch (IOException e) {
            System.out.println("Error while generating screenshot:\n" + e.toString());
        }
        return (reportFolder+imageLocation + "_" + imageName + ".jpg");
    }

    /**
     * Takes screenshots with random names mainly to log failures in between
     *
     * @return image with unique name
     */
    public static String randomScreenshots() {
        UUID uuid = UUID.randomUUID();
        try {
            if (driver != null) {
                File scrFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile1, new File(reportFolder + imageLocation + "_" + uuid + ".jpg"));
            }
        } catch (IOException e) {
            System.out.println("Error while generating screenshot:\n" + e.toString());
        }
        return (reportFolder+imageLocation + "_" + uuid + ".jpg");
    }

    /**
     * Opens report in browser after execution
     */
    public static void openReport() {
        try {
            Desktop.getDesktop().open(new File(location));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}