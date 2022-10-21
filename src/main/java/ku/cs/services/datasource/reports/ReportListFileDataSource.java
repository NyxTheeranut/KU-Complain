package ku.cs.services.datasource.reports;

import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.datasource.DataSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
            buffer = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");

                report = new Report(data[0] , UUID.fromString(data[1]), data[2], data[3]);

                reportList.addReport(report);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                //reader.close();
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
//            writer = new FileWriter(file);
//            buffer = new BufferedWriter(writer);
            buffer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));

            for (Report report : reportList.getAllReport()) {
                String line = report.getType() + ","
                        + report.getId().toString() + ","
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
                //writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
