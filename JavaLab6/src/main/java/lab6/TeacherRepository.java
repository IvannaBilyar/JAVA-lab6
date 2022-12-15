package lab6;

import database.DBManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {
    private DBManager connector;

    public TeacherRepository(DBManager conn) {
        connector = conn;
    }

    public boolean addTeacher(Teacher teacher){
        try{
            String sql = "INSERT INTO teachers" + "(name, surname, birthday, date_of_employment, language) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connector.getPreparedStatement(sql);

            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getSurname());
            statement.setString(3, teacher.getBirthday().toString());
            statement.setString(4, teacher.getDateOfEmployment().toString());
            statement.setString(5, teacher.getLanguage());
            statement.executeUpdate();
            return true;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    private Teacher getTeacher(ResultSet set){
        try{
            return new Teacher.Builder().setTeacherId(set.getInt("id"))
                    .setName(set.getString("name"))
                    .setSurname(set.getString("surname"))
                    .setBirthday(set.getDate("birthday").toLocalDate())
                    .setLanguage(set.getString("language"))
                    .setDateOfEmployment(set.getDate("date_of_employment").toLocalDate()).build();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

        public List<Teacher> getTeacherByQuery(String query){
        List<Teacher> result = new ArrayList<>();
        ResultSet set = connector.getData(query);
        try {
            while (set.next()){
                result.add(getTeacher(set));
            }
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return result;
    }

    public List<Teacher> getTeacher()
    {
        return getTeacherByQuery("SELECT * FROM teachers");
    }

    public List<Teacher> getTeacherById(int id){
        return getTeacherByQuery("SELECT * FROM teachers WHERE id = " + id + "");
    }

    public List<Teacher> getTeacherByName(String name){
        List<Teacher> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM teachers WHERE name LIKE ?";
            PreparedStatement statement = connector.getPreparedStatement(sql);
            statement.setString(1,name);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                result.add(getTeacher(set));
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return result;
    }

    public List<Teacher> getTeacherBySurname(String surname){
        List<Teacher> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM teachers WHERE surname LIKE ?";
            PreparedStatement statement = connector.getPreparedStatement(sql);
            statement.setString(1, surname);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                result.add(getTeacher(set));
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return result;
    }

    public List<Teacher> getTeacherByBirthday(LocalDate birthday){
        List<Teacher> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM teachers WHERE birthday LIKE ?";
            PreparedStatement statement = connector.getPreparedStatement(sql);
            statement.setDate(1, Date.valueOf(birthday));
            ResultSet set = statement.executeQuery();
            while (set.next()){
                result.add(getTeacher(set));
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return result;
    }

    public List<Teacher> getTeacherByLanguage(String language){
        List<Teacher> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM teachers WHERE language LIKE ?";
            PreparedStatement statement = connector.getPreparedStatement(sql);
            statement.setString(1, language);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                result.add(getTeacher(set));
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return result;
    }

    public List<Teacher> sortTeacherByName(){
        return getTeacherByQuery("SELECT * FROM teachers ORDER BY name");
    }
    public List<Teacher> sortTeacherBySurname(){
        return getTeacherByQuery("SELECT * FROM teachers ORDER BY surname");
    }
    public List<Teacher> sortTeacherByBirthday(){
        return getTeacherByQuery("SELECT * FROM teachers ORDER BY birthday");
    }
    public List<Teacher> sortTeacherByLanguage(){
        return getTeacherByQuery("SELECT * FROM teachers ORDER BY language");
    }


    public boolean updateTeachers(Teacher teacher){
        try{
            String sql = "UPDATE teachers" +
                    "SET name = ?, surname = ?, birthday = ?, language = ?, date_of_employment = ? WHERE id = ?;";
            PreparedStatement statement = connector.getPreparedStatement(sql);

            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getSurname());
            statement.setString(3, teacher.getBirthday().toString());
            statement.setString(4, teacher.getDateOfEmployment().toString());
            statement.setString(5, teacher.getLanguage());
            statement.setInt(6, teacher.getTeacherId());
            statement.executeUpdate();
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
    public void deleteTeacher(int id) {
        getTeacherByQuery("DELETE FROM teachers WHERE id = " + id +"");
    }

}
