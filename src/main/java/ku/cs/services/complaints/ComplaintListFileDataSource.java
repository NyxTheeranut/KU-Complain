package ku.cs.services.complaints;

import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.services.DataSource;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ComplaintListFileDataSource implements DataSource<ComplaintList> {
    private final String directoryName = "src/main/resources/ku/cs/data/";
    private final String fileName = "complaint_list.csv";

    public ComplaintList readData(){
        ComplaintList complaintList = new ComplaintList();
        BufferedReader buffer = null;
        try {
            InputStream inputStream = getClass().getResourceAsStream("/ku/cs/data/complaint_list.csv");
            buffer = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");

                complaintList.addComplaint(new Complaint(new String(data[0].getBytes("UTF8"),"UTF8"),
                                                        new String(data[0].getBytes("UTF8"),"UTF8")));
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return complaintList;
    }

    public void writeData(ComplaintList complaintList) {
        File file = new File(directoryName + fileName);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for(Complaint complaint : complaintList.getAllComplaints()){
                String line = complaint.getTopic() + "," + complaint.getCategory();
                buffer.append(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
