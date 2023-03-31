package LabExerciseFour;

public class Student {
    private String program;
    private int year;
    private double grade;
    private String lastName;

    public Student ( String program, String lastName, int year, double grade ) {
        try {
            this.program = program;
            this.lastName = lastName;
            this.year = year;
            this.grade = grade;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public Student () {
        try {
            this.program = null;
            this.lastName = null;
            this.year = 0;
            this.grade = 0.0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public String getProgram() {
        return program;
    }
    public int getYear() {
        return year;
    }
    public double getGrade() {
        return grade;
    }

    public String getLastName() {
        return lastName;
    }

    public void setProgram(String program) throws Exception {
        if ( !(program.equals("") || program.equals(null))) {
            this.program = program;
        }
        else{
            throw new Exception ("Fatal error: program cannot be left blank");
        }
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setGrade(double grade) throws Exception {
        if ( grade >= 0.0 && grade <= 100.0 ) {
            this.grade = grade;
        }
        else {
            throw new Exception("Fatal error: grade must be between 0.0 and 100.0");
        }
    }
    public void setLastName(String lastName) throws Exception {
        if ( !(lastName.equals("") || lastName.equals(null))) {
            this.lastName = lastName;
        }
        else{
            throw new Exception ("Fatal error: lastName cannot be left blank");
        }
    }

    @Override
    public String toString() {
        return getProgram() + " " + getYear() + " " + getGrade() + " " + getLastName();
    } 
}
