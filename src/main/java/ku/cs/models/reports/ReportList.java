package ku.cs.models.reports;

import java.util.ArrayList;

public class ReportList {
    private ArrayList<Report> reports;

    public ReportList() {
        reports = new ArrayList<>();
    }

    public void addReport(Report report) {
        reports.add(report);
    }

    public ArrayList<Report> getAllReport() {
        return reports;
    }

}
