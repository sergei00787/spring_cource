package com.jbond.projects.spring_cource.employeesPkg;

public abstract class Employee {
    public String category;
    public int salary;

    public abstract String getCategory();

    public abstract void setCategory(String category);

    public abstract int getSalary();

    public abstract void setSalary(int salary);
}
