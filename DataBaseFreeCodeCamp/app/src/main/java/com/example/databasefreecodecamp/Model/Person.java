package com.example.databasefreecodecamp.Model;

public class Person {
    private int id;
    private String Name;
    private int age;
    private boolean isChecked;

    public Person(){}

    public Person(int id, String name, int age, boolean isChecked) {
        this.id = id;
        Name = name;
        this.age = age;
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", age=" + age +
                ", isChecked=" + isChecked +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
