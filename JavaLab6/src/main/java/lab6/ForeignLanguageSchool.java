package lab6;

import java.util.*;

public class ForeignLanguageSchool {
    private int schoolId;
    private String Address;
    private int capacity;
    private List<Teacher> teachers;
    public int getSchoolId(){return schoolId;}

    public String getAddress() {
        return Address;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    /**
     * Class "Builder" with fields: school
     * @author User
     * @version 1.0
     */
    public static class Builder {
        private ForeignLanguageSchool school;

        public Builder() {
            school = new ForeignLanguageSchool();
        }
        public Builder setSchoolId(int schoolId){
            school.schoolId = schoolId;
            return this;
        }

        /**
         * Setter  address designation
         * @param address - school address
         * @return returns current object
         */
        public Builder setAddress(String address) {
            school.Address = address;
            return this;
        }

        /**
         * Setter  capacity designation
         * @param cp - capacity
         * @return returns current object
         */
        public Builder setCapacity(int cp) {
            school.capacity = cp;
            return this;
        }

        /**
         * Setter  teachers designation
         * @param teachers - school teachers
         * @return returns current object
         */
        public Builder setTeachersList(List<Teacher> teachers) {
            school.teachers = teachers;
            return this;
        }
        /**
         * Setter of creating an object of class "ForeignLanguageSchool"
         * @return returns new object of class "ForeignLanguageSchool"
         */

        public ForeignLanguageSchool build() {
            return school;
        }

    }




    /**
     *  Overridden function of obtaining a string representation of
     *  an instance of a class "ForeignLanguageSchool"
     *  @return returns the string representation
     */
    @Override
    public String toString() {
        return "Foreign language school: " +
                "Capacity = " + capacity + "; " +
                "Address = " + Address + "; " +
                "Teachers = " + teachers + ";";
    }

    /**
     * Overridden function of comparison an instance of
     * the class "ForeignLanguageSchool" and an instance of the class "Object"
     * @return returns the boolean value of the comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        ForeignLanguageSchool a = (ForeignLanguageSchool) obj;
        return Objects.equals(Address, a.Address) && capacity == a.capacity && teachers.equals(a.teachers);
    }

    /**
     * Overridden function of obtaining the hash code
     * @return returns the numeric value of the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Address, capacity, teachers);
    }

}
