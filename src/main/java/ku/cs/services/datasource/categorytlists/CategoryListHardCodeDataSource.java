package ku.cs.services.datasource.categorytlists;

import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;

public class CategoryListHardCodeDataSource {
    private CategoryList componentList;

    public CategoryListHardCodeDataSource() {
        componentList = new CategoryList();
        readData();
    }

    public CategoryList readData(){
        componentList.addCategory(new Category("defective building"));
        componentList.getAllCategory().get(0).addField("text", "test1");
        componentList.getAllCategory().get(0).addField("text", "test2");
        //System.out.println(componentList.getAllCategory().get(0).getFields().size());
        //componentList.getAllCategory().get(0).addField("text", "test3");
        //componentList.getAllCategory().get(0).addField("text", "test4");
        //componentList.addComponent(new Category("inappropriate event"));


        return componentList;
    }

    public CategoryList getAllComponents(){
        return componentList;
    }
}
