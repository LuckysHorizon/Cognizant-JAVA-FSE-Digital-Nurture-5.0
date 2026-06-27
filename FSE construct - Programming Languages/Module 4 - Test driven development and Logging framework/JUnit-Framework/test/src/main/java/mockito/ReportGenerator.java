package mockito;

public class ReportGenerator {

    private final FileStorage fileStorage;

    public ReportGenerator(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public boolean generateReport(String reportName, String data) {
        String content = "Report: " + reportName + "\nData: " + data;
        return fileStorage.saveFile(reportName + ".txt", content);
    }

    public String loadReport(String reportName) {
        String fileName = reportName + ".txt";
        if (!fileStorage.fileExists(fileName)) {
            throw new RuntimeException("Report not found: " + reportName);
        }
        return fileStorage.readFile(fileName);
    }

    public boolean deleteReport(String reportName) {
        return fileStorage.deleteFile(reportName + ".txt");
    }
}
