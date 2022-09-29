package ku.cs.services.filter;

import ku.cs.models.category.Category;

public class CategoryNameFilter implements Filterer<Category> {

    @Override
    public boolean found(Category obj, String filter) {
        if (obj.getName().equals(filter)) return true;
        return false;
    }

    @Override
    public String getName() {
        return "name";
    }
}
