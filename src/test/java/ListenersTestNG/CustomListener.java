package ListenersTestNG;

import org.testng.IExecutionListener;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;

public class CustomListener implements ITestListener, IExecutionListener, IInvokedMethodListener {

    String deleteJsonCommand = "cmd C/Users/ahlla/IdeaProjects/Swag Labs Automation Assignment/ cd target/allure-results && del *.json";
    String allureReportGenerate = "cmd C/Users/ahlla/IdeaProjects/Swag Labs Automation Assignment/ allure generate target/allure-results -o reports/ --clean --single-file";


    public void onExecutionStart() {
        // not implemented
        executeCommand(deleteJsonCommand);
    }

    /**
     * Invoked once all the suites have been run.
     */
    public void onExecutionFinish() {
        // not implemented
        executeCommand(allureReportGenerate);
    }

    private static void executeCommand(String CMD) {
        try {
            Process process = Runtime.getRuntime().exec(CMD);
            int extCode = process.waitFor();
            if (extCode == 0) {
                System.out.println("Execute Suecess");
            } else {
                System.out.println("Execute Falied");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
