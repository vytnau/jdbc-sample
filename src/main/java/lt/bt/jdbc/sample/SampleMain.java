package lt.bt.jdbc.sample;

import lt.bt.jdbc.sample.db.DatabaseManager;
import lt.bt.jdbc.sample.entity.DepartmentsEntity;

import java.util.List;

public class SampleMain {

    public static void main(String[] args){
        DatabaseManager db = new DatabaseManager();
        List<DepartmentsEntity> data = db.getDepartmentns();
        for (DepartmentsEntity dp : data) {
            System.out.println(dp.toString());
        }

        db.getMinSalary();
        db.close();
    }
}
