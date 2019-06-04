package lt.bt.jdbc.sample.entity;

public class DepartmentsEntity {
    private String id;
    private String name;

    public DepartmentsEntity(){

    }

    public DepartmentsEntity(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DepartmentsEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
