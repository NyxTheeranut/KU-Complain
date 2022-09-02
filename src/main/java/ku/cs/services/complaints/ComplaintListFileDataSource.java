package ku.cs.services.complaints;

import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.services.DataSource;

import java.io.*;

public class ComplaintListFileDataSource implements DataSource<ComplaintList> {
    private final String directoryName = "src/main/resources/ku/cs/data/";
    private final String fileName = "complaint_list.csv";

    public ComplaintList readData(){
        ComplaintList complaintList = new ComplaintList();
        File file = new File(directoryName + fileName);
        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String line = "";
            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                complaintList.addComplaint(new Complaint(data[0],data[1]));
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                reader.close();
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
