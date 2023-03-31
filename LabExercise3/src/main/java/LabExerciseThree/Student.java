package LabExerciseThree;

public class Student {
    private String program;
    private int year;
    private double grade;

    public String getProgram() {
        return program;
    }
    public int getYear() {
        return year;
    }
    public double getGrade() {
        return grade;
    }

    public void setProgram(String program) {
        this.program = program;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return " Program: " + getProgram() + "\n Year: " + getYear() + "\n Average Grade: " + getGrade();
    } 
}
