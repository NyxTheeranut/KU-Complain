package ku.cs.services.datasource.complaints;

import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.models.complaints.Status;
import ku.cs.services.datasource.DataSource;
import ku.cs.util.Util;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.datasource.categorytlists.CategoryListFileDataSource;
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.services.filter.CategoryNameFilter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

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

            //Get Account list
            AccountListFileDataSource accountDataSource = new AccountListFileDataSource();
            AccountList accountList = accountDataSource.readData();

            //Get category list
            CategoryListFileDataSource categoryDataSource = new CategoryListFileDataSource();
            CategoryList categoryList = categoryDataSource.readData();

            while((line = buffer.readLine()) != null){
                // 0    1       2       3      4            5         6           7            8
                //id,authorid,topic,category,datepost,enumstatus,moderatorid,solving detail,field
                String[] data = line.split(",");

                //Get account object
                Account author = Util.search(data[1], accountList.getAllAccount(), new AccountIdFilter());

                //Get category object
                Category category = Util.search(data[3], categoryList.getAllCategory(), new CategoryNameFilter());
                ArrayList<String> fields = new ArrayList<>();

                //Get local date time object
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime datePosted = LocalDateTime.parse(data[4], formatter);

                //Get moderator
                Account moderator = Util.search(data[6], accountList.getAllAccount(), new AccountIdFilter());

                if (data[7].equals("-")) {
                    data[7] = "";
                }

                for(int i=8; i<data.length ;i++){
                    fields.add(data[i]);
                }

                complaintList.addComplaint(new Complaint(UUID.fromString(data[0]), author, data[2],
                        category, datePosted, Status.valueOf(data[5]), moderator, data[7], fields));

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
                //id,authorid,topic,category,datepost,moderator,solvingdetail,field

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


                String line = complaint.getId().toString() + "," +
                        complaint.getAuthor().getId() + "," +
                        complaint.getTopic() + "," +
                        complaint.getCategory().getName() + "," +
                        complaint.getDatePosted().format(formatter) + "," +
                        complaint.getStatus().name() + ",";
                        ;
                if (complaint.getModerator() == null) {
                    line += "-,";
                }
                else {
                    line += complaint.getModerator().getId() + ",";
                }
                if (complaint.getSolvingDetail().equals("")) {
                    line += "-";
                }
                line += complaint.getSolvingDetail() + "," +
                        String.join(",", complaint.getFields());


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
