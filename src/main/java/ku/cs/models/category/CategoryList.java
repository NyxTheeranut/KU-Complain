package ku.cs.models.category;

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
}