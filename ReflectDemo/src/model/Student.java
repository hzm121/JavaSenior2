package model;

import java.util.Date;

public class Student {
    private String stuCode;
    private String name;
    private Integer age;
    private Date birthDate;

    public Student() {
    }

    public void goToClass() {
        System.out.println("学生上课");
    }
    private void eat(){
        System.out.println("学生吃东西");
    }

    public Student(String stuCode, String name, Integer age, Date birthDate) {
        this.stuCode = stuCode;
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
    }

    public String getStuCode() {
        return stuCode;
    }

    public void setStuCode(String stuCode) {
        this.stuCode = stuCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
