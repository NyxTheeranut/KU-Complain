package ku.cs.models.category;

import java.util.ArrayList;

public class CategoryList {
    private ArrayList<Category> categories;

    public CategoryList() {
        categories = new ArrayList<>();
    }

    public void addComponent(Category category){
        categories.add(category);
    }

    public ArrayList<Category> getAllCategory() {
        return categories;
    }
    public Category search(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name))
                return category;
        }
        return null;
    }
}
