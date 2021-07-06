package com.jbond.projects.spring_cource.employeesPkg;

public class ProjectManager extends Employee{
    @Override
    public String getCategory() {
        return super.category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }
}
