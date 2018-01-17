package com.example.chris.androidtraining.save_data.db.database.student;

/**
 * 学生对象
 *
 * Created by michael on 2017/9/21.
 */

public class Student
{
    public String id;
    public String name;
    public String age;

    public Student() {}

    public Student(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
