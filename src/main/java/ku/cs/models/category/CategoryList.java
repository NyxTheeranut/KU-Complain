package ku.cs.models.category;

import ku.cs.services.filter.CategoryNameFilter;
import ku.cs.util.Util;

import java.util.ArrayList;

public class CategoryList {
    private ArrayList<Category> categories;

    public CategoryList() {
        categories = new ArrayList<>();
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public ArrayList<Category> getAllCategory() {
        return categories;
    }
    public void removeAll(ArrayList<Category> list){
        for(int i=0; i<categories.size(); i++){
            //System.out.println(categories.get(i).getName());
            if(Util.search(categories.get(i).getName(),list,new CategoryNameFilter()) != null){
                //System.out.println("Remove : "+categories.get(i).getName());
                categories.remove(categories.get(i));
                i--;
            }

        }
    }
}
