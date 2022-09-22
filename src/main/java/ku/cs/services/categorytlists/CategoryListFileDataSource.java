package ku.cs.services.categorytlists;

import javafx.util.Pair;
import ku.cs.models.accounts.*;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.services.DataSource;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CategoryListFileDataSource implements DataSource<CategoryList> {
    private final String directoryName = "data";
    private final String fileName = "category_list.csv";

    public CategoryList readData() {

        CategoryList categoryList = new CategoryList();
        File file = new File(directoryName+File.separator + fileName);

        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            //open reader
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while((line = buffer.readLine()) != null){//not eof
                //format
                //name,text-name,pic-url
                List<String> data = Arrays.asList(line.split(","));

                Category category = new Category(data.get(0)); //Create new Category

                for (String i : data.subList(1, data.size())){
                    //Add field to category
                    String field[] = i.split("-");
                    category.addField(field[0], field[1]);
                }

                categoryList.addCategory(category); //add category to category list
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return categoryList;
    }

    public void writeData(CategoryList categoryList) {
        File file = new File(directoryName + File.separator + fileName);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for(Category category : categoryList.getAllCategory()) {
                //format
                //name,text-name,pic-url
                String line = category.getName() + ",";

                LinkedList<String> fields = new LinkedList<>() {
                };

                for (Pair<String, String> i : category.getFields()) {
                    fields.add(i.getKey()+"-"+i.getValue());
                }
                line += String.join(",", fields);

                buffer.append(line);
                buffer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Invalid file path");
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
