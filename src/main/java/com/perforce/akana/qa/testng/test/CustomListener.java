package com.perforce.akana.qa.testng.test;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class CustomListener extends TestListenerAdapter {
	
    @Override
    public void onTestSuccess(ITestResult r) {
        Reporter.log(r.getMethod().getDescription());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log(result.getMethod().getDescription());
        Reporter.log("<br>");
        reportStackTrace(result);
        Reporter.log("</br>");
    }

    private void reportStackTrace(ITestResult result) {
        StringBuilder stackTrace = new StringBuilder("Throwable stack trace:\n");
        for (StackTraceElement element : result.getThrowable().getStackTrace()) {
            stackTrace.append(element);
            stackTrace.append(System.getProperty("line.separator"));
        }
        
        Reporter.log(stackTrace.toString());
    }
}
