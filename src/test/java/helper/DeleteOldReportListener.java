package helper;

import org.testng.IExecutionListener;

import java.io.File;
import java.util.Objects;

public class DeleteOldReportListener implements IExecutionListener {

    @Override
    public void onExecutionStart() {

        String reportDirectoryPath = "test-output";

        File reportDirectory = new File(reportDirectoryPath);

        if (reportDirectory.exists() && reportDirectory.isDirectory()) {

            for (File file : Objects.requireNonNull(reportDirectory.listFiles())) {
                file.delete();
            }
        }
    }

    @Override
    public void onExecutionFinish() {
    }
}
