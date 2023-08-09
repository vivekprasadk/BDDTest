package bddTest.steps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static bddTest.CommonUtils.configFileReader;

public class ShutdownHook extends Thread {
    private static void copyFolder(File sourceFolder, File destinationFolder) throws IOException {
        if (sourceFolder.isDirectory()) {
            if (!destinationFolder.exists())
                destinationFolder.mkdir();
            String files[] = sourceFolder.list();
            for (String file : files) {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);
                copyFolder(srcFile, destFile);
            }
        } else {
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
    private void createReportsCopy() {
        File sourceFolder = new File(System.getProperty("user.dir") + "/reports");
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        TimeZone tz = TimeZone.getTimeZone("Asia/Kolkata");
        sdf.setTimeZone(tz);
        String date = sdf.format(new Date());
        System.out.println(date);
        File destinationFolder = new File(System.getProperty("user.dir") + "/TestExecutionReports_" + date);
        try {
            copyFolder(sourceFolder, destinationFolder);
        } catch (IOException e) {
            System.out.println("Copying Reports Failed");
        }
    }
    @Override
    public void run() {
        String reportsCopy = configFileReader.getProperty("reports.history");
        if (reportsCopy.equalsIgnoreCase("true")) {
            createReportsCopy();
        }
    }
}
