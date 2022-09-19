package ku.cs.services.complaints;

import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.services.DataSource;
import ku.cs.services.categorytlists.CategoryListHardCodeDataSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ComplaintListFileDataSource implements DataSource<ComplaintList> {
    private final String directoryName = "data";
    private final String fileName = "complaint_list.csv";

    public ComplaintList readData(){
        ComplaintList complaintList = new ComplaintList();
        File file = new File(directoryName+File.separator+fileName);
        FileReader fileReader = null;
        BufferedReader buffer = null;
        try {
            fileReader = new FileReader(file);
            buffer = new BufferedReader(fileReader);
            String line = "";
            CategoryListHardCodeDataSource dataSource = new CategoryListHardCodeDataSource();
            CategoryList componentList = dataSource.readData();
            System.out.println(componentList.getAllCategory().get(0).getName());
            while((line = buffer.readLine()) != null){
                //topic,category,
                String[] data = line.split(",");
                Category category = componentList.search(data[1]);
                ArrayList<String> fields = new ArrayList<>();

                for(int i=2; i<data.length ;i++){
                    fields.add(data[i]);
                }

                complaintList.addComplaint(new Complaint(data[0],category, fields));
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
        File file = new File(directoryName + File.separator + fileName);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for(Complaint complaint : complaintList.getAllComplaints()){
                String line = complaint.getTopic()+","+complaint.getCategory();
                byte[] bytes = line.getBytes(StandardCharsets.UTF_8);
                String encodedLine = new String(bytes,StandardCharsets.UTF_8);
                buffer.append(encodedLine);
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
