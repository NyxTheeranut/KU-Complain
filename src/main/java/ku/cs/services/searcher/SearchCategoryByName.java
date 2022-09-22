package ku.cs.services.searcher;

import ku.cs.models.category.Category;

public class SearchCategoryByName implements Searcher<Category> {

    @Override
    public boolean found(Category obj, String categoryName) {
        if (obj.getName().equals(categoryName)) return true;
        return false;
    }
}
