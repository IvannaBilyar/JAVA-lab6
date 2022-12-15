package database;

import java.sql.SQLException;

public class Service {
    private DBManager dbManager;

    public Service(DBManager dbManager){
        this.dbManager = dbManager;
    }

    public void createTableFLSchools() throws SQLException {
        dbManager.executeUpdate("CREATE TABLE if not exists fl_schools (" +
                "id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "address TEXT NOT NULL, " +
                "capacity INT NOT NULL);"
        );
    }
    public void createTableTeachers()  throws SQLException {
        dbManager.executeUpdate("CREATE TABLE if not exists teachers (" +
                "id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "surname TEXT NOT NULL, " +
                "birthday DATE NOT NULL, " +
                "language TEXT NOT NULL, " +
                "date_of_employment DATE NOT NULL, " +
                "fl_school_id INT NOT NULL, " +
                "FOREIGN KEY (fl_school_id) REFERENCES fl_schools(id));"
        );
    }

    public void dropTables() throws SQLException{
        dbManager.executeUpdate("DROP TABLE fl_schools;");
        dbManager.executeUpdate("DROP TABLE teachers;");
    }
}