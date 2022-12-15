package lab6;

import database.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ForeignLanguageSchoolRepository {
    private DBManager connector;

    public ForeignLanguageSchoolRepository(DBManager conn) {
        connector = conn;
    }

    public boolean addFLSchool(ForeignLanguageSchool foreignLanguageSchool){
        try{
            String sql = "INSERT INTO fl_schools " + " (capacity, address) " +
                        "VALUES (?, ?)";
                PreparedStatement statement = connector.getPreparedStatement(sql);

                statement.setInt(1, foreignLanguageSchool.getCapacity());
                statement.setString(2, foreignLanguageSchool.getAddress());
                statement.executeUpdate();
            return true;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    private ForeignLanguageSchool getForeignLanguageSchool(ResultSet set){
        try{
            return new ForeignLanguageSchool.Builder().setSchoolId(set.getInt("id"))
                    .setCapacity(set.getInt("capacity"))
                    .setAddress(set.getString("address")).build();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }


    public List<ForeignLanguageSchool> getFLSchoolByQuery(String query){
        List<ForeignLanguageSchool> result = new ArrayList<>();
        ResultSet set = connector.getData(query);
        try {
            while (set.next()){
                result.add(getForeignLanguageSchool(set));
            }
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return result;
    }

    public List<ForeignLanguageSchool> getFLSchools()
    {
        return getFLSchoolByQuery("SELECT * FROM fl_schools");
    }

    public List<ForeignLanguageSchool> getFLSchoolById(int id){
        return getFLSchoolByQuery("SELECT * FROM fl_schools WHERE id = " + id + "");
    }


    public List<ForeignLanguageSchool> sortFLSchoolByAddress(){
        return getFLSchoolByQuery("SELECT * FROM fl_schools ORDER BY address");
    }

    public boolean updateFLSchool(ForeignLanguageSchool foreignLanguageSchool){
        try{
            String sql = "UPDATE fl_schools SET capacity = ?," + "address = ? WHERE id = ?;";
            PreparedStatement statement = connector.getPreparedStatement(sql);

            statement.setInt(1, foreignLanguageSchool.getCapacity());
            statement.setString(2, foreignLanguageSchool.getAddress());
            statement.setInt(3, foreignLanguageSchool.getSchoolId());
            statement.executeUpdate();
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
    public void deleteFLSchool(int id) {
        getFLSchoolByQuery("DELETE FROM fl_schools WHERE id = " + id +"");
    }

}
