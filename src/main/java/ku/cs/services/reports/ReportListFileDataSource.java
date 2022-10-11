package ku.cs.services.reports;

import ku.cs.models.accounts.User;
import ku.cs.models.reports.AccountReport;
import ku.cs.models.reports.ComplaintReport;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.datasource.DataSource;

import java.io.*;
import java.nio.Buffer;
import java.util.UUID;

public class ReportListFileDataSource implements DataSource<ReportList> {

    private  String directoryName = "data";
    private  String fileName = "report_list.csv";

    public ReportList readData() {
        ReportList reportList = new ReportList();
        File file = new File(directoryName + File.separator + fileName);
        FileReader reader = null;
        BufferedReader buffer = null;
        Report report = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals("Account")) {
                    report = new AccountReport(data[0] ,data[1] ,data[2], data[3]);
                }
                if (data[0].equals("Complaint")) {
                    report = new ComplaintReport(data[0] ,data[1],data[2], data[3]);
                }
                reportList.addReport(report);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return reportList;
    }


    public void writeData(ReportList reportList) {
        File file = new File(directoryName + File.separator + fileName);
        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (Report report : reportList.getAllReport()) {
                String line = report.getType() + ","
                        + report.getId() + ","
                        + report.getTopic() + ","
                        + report.getDescription();
                buffer.append(line);
                buffer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
