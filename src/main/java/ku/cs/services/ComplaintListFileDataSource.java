package ku.cs.services;

import ku.cs.models.Complaint;
import ku.cs.models.ComplaintList;

import java.io.*;

public class ComplaintListFileDataSource implements DataSource<ComplaintList> {

    public ComplaintList readData(){
        ComplaintList complaintList = new ComplaintList();
        File file = new File("src/main/resources/ku/cs/data/complaint_list.csv");
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

    public void writeData(){

    }
}
