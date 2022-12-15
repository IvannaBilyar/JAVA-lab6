package lab6;

import database.DBManager;
import database.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//5 lab
public class Main {

    public static void main(String args[]) throws SQLException {

        Teacher t1 = new Teacher.Builder()
                .setTeacherId(1)
                .setName("Olha")
                .setSurname("Ivanko")
                .setBirthday(LocalDate.of(1994, 9, 12))
                .setDateOfEmployment(LocalDate.of(2017, 12, 5))
                .setLanguage("Chinese").build();
        Teacher t2 = new Teacher.Builder()
                .setTeacherId(2)
                .setName("Sergiy").setSurname("Maksymenko")
                .setBirthday(LocalDate.of(1982, 4, 23))
                .setDateOfEmployment(LocalDate.of(2012, 10, 16))
                .setLanguage("Italian").build();
        Teacher t3 = new Teacher.Builder()
                .setTeacherId(3)
                .setName("Maryna")
                .setSurname("Svitla")
                .setBirthday(LocalDate.of(1997, 11, 20))
                .setDateOfEmployment(LocalDate.of(1920, 9, 2))
                .setLanguage("Polish").build();
        Teacher t4 = new Teacher.Builder()
                .setTeacherId(4)
                .setName("Maksym")
                .setSurname("Hnatyuk")
                .setBirthday(LocalDate.of(1985, 6, 19))
                .setDateOfEmployment(LocalDate.of(2010, 8, 10))
                .setLanguage("Japanese").build();
        Teacher t5 = new Teacher.Builder()
                .setTeacherId(5)
                .setName("Inna")
                .setSurname("Shevchenko")
                .setBirthday(LocalDate.of(1996, 8, 18))
                .setDateOfEmployment(LocalDate.of(2018, 11, 25))
                .setLanguage("English").build();

        List<Teacher> teachers = new ArrayList<Teacher>();
        teachers.add(t1);
        teachers.add(t2);
        teachers.add(t3);
        teachers.add(t4);
        teachers.add(t5);

        ForeignLanguageSchool s1 = new ForeignLanguageSchool.Builder()
                .setSchoolId(1)
                .setCapacity(50)
                .setAddress("Melnikova Ul., bld. 47")
                .setTeachersList(teachers).build();
        ForeignLanguageSchool s2 = new ForeignLanguageSchool.Builder()
                .setSchoolId(2)
                .setCapacity(70)
                .setAddress("Ukrainka Ul., bld. 23")
                .setTeachersList(teachers).build();

        DBManager dbManager = new DBManager();
        Service service = new Service(dbManager);

        service.createTableFLSchools();
        service.createTableTeachers();
        service.dropTables();


        ForeignLanguageSchoolRepository flsRepo = new ForeignLanguageSchoolRepository(dbManager);
        flsRepo.addFLSchool(s1);
        flsRepo.addFLSchool(s2);

        List<ForeignLanguageSchool> foreignLanguageSchools = flsRepo.getFLSchools();
        System.out.println("SELECT ALL ForeignLanguageSchools");
        System.out.println(foreignLanguageSchools);

        System.out.println("\n");

        System.out.println("SELECT ForeignLanguageSchool BY ID = 2");
        System.out.println(flsRepo.getFLSchoolById(2));

        System.out.println("\n");

        System.out.println("SORT ForeignLanguageSchool BY ADDRESS");
        System.out.println(flsRepo.sortFLSchoolByAddress());


        TeacherRepository teacherRepo = new TeacherRepository(dbManager);
        teacherRepo.addTeacher(t1);
        teacherRepo.addTeacher(t2);
        teacherRepo.addTeacher(t3);
        teacherRepo.addTeacher(t4);
        teacherRepo.addTeacher(t5);

        List<Teacher> teacher = teacherRepo.getTeacher();
        System.out.println("SELECT ALL TEACHERS");
        System.out.println(teacher);

        System.out.println("\n");

        System.out.println("SELECT TEACHER BY ID = 4");
        System.out.println(teacherRepo.getTeacherById(4));

        System.out.println("\n");

        System.out.println("SELECT TEACHERS BY NAME - Maryna");
        System.out.println(teacherRepo.getTeacherByName("Maryna"));

        System.out.println("\n");

        System.out.println("SELECT TEACHERS BY SURNAME - Maksymenko");
        System.out.println(teacherRepo.getTeacherBySurname("Maksymenko"));

        System.out.println("\n");

        System.out.println("SELECT TEACHERS BY BIRTHDAY - 1996.8.18");
        System.out.println(teacherRepo.getTeacherByBirthday(LocalDate.of(1996, 8, 18)));

        System.out.println("\n");

        System.out.println("SELECT TEACHERS BY LANGUAGE - Chinese");
        System.out.println(teacherRepo.getTeacherByLanguage("Chinese"));

        System.out.println("\n");

        System.out.println("SORT TEACHERS BY NAME");
        System.out.println(teacherRepo.sortTeacherByName());

        System.out.println("\n");

        System.out.println("SORT TEACHERS BY SURNAME");
        System.out.println(teacherRepo.sortTeacherBySurname());

        System.out.println("\n");

        System.out.println("SORT TEACHERS BY BIRTHDAY");
        System.out.println(teacherRepo.sortTeacherByBirthday());

        System.out.println("\n");

        System.out.println("SORT TEACHERS BY LANGUAGE");
        System.out.println(teacherRepo.sortTeacherByLanguage());


    }
}



