package cn.Lee.ssm.category.pojo;

import java.util.List;

public class CustomCategory extends Category {

    private List<Categorysecond> categoryseconds;

    public List<Categorysecond> getCategoryseconds() {
        return categoryseconds;
    }

    public void setCategoryseconds(List<Categorysecond> categoryseconds) {
        this.categoryseconds = categoryseconds;
    }
}
