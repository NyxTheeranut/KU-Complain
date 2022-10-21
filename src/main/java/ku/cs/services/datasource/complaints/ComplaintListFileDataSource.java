package ku.cs.services.datasource.complaints;

import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.Moderator;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.models.complaints.Status;
import ku.cs.services.datasource.DataSource;
import ku.cs.util.Data;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.datasource.categorytlists.CategoryListFileDataSource;
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.services.filter.CategoryNameFilter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
            buffer = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));

            String line = "";

            //Get Account list
            AccountListFileDataSource accountDataSource = new AccountListFileDataSource();
            AccountList accountList = accountDataSource.readData();

            //Get category list
            CategoryListFileDataSource categoryDataSource = new CategoryListFileDataSource();
            CategoryList categoryList = categoryDataSource.readData();

            while((line = buffer.readLine()) != null){
                // 0    1       2       3      4            5         6           7
                //id,authorid,topic,category,datepost,enumstatus,moderatorid,solving detail,
                //       8            9
                // field1-field2,voterUuid:vote/voterUuid:vote-vote2
                String[] data = line.split(","); //get line data

                //account
                Account author = Data.search(data[1], accountList.getAllAccount(), new AccountIdFilter());

                //category
                Category category = Data.search(data[3], categoryList.getAllCategory(), new CategoryNameFilter());

                //time post
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime datePosted = LocalDateTime.parse(data[4], formatter);

                //moderator
                Moderator moderator = (Moderator) Data.search(data[6], accountList.getAllAccount(), new AccountIdFilter());

                //fields
                ArrayList<String> fields = new ArrayList<>(Arrays.asList(data[8].split("-")));
                //votes
                ArrayList<UUID> votes = new ArrayList<>();
                if (data.length>9) {
                    for (String i : data[9].split("/")) {
                        votes.add(UUID.fromString(i));
                    }
                }


                complaintList.addComplaint(new Complaint(UUID.fromString(data[0]), author, data[2],
                        category, datePosted, Status.valueOf(data[5]), moderator, data[7], fields, votes));

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
//            writer = new FileWriter(file);
//            buffer = new BufferedWriter(writer);
            buffer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
            for(Complaint complaint : complaintList.getAllComplaints()){
                //id,authorid,topic,category,datepost,moderator,solvingdetail
                //field1-field2,voterUuid:vote/voterUuid:vote-vote2

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


                String line = complaint.getId().toString() + "," +
                        complaint.getAuthor().getId() + "," +
                        complaint.getTopic() + "," +
                        complaint.getCategory().getName() + "," +
                        complaint.getDatePosted().format(formatter) + "," +
                        complaint.getStatus().name() + ",";

                try {
                    line += complaint.getModerator().getId() + ",";
                }catch (NullPointerException e){
                    line += ",";
                }

                line += complaint.getSolvingDetail() + "," +
                        String.join("-", complaint.getFields());

                ArrayList<String> votes = new ArrayList<>();
                for (UUID i:complaint.getVotes()) {
                    votes.add(i.toString());
                }
                line += ","+String.join("/", votes);


                buffer.append(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                //writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
