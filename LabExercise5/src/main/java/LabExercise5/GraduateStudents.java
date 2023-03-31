package LabExercise5;

public class GraduateStudents extends Student {
    private String supervisor;
    private boolean isPhD;
    private String undergraduateSchool;

    public GraduateStudents ( String supervisor, boolean isPhD, String undergraduateSchool ) {
        try {
            this.supervisor = supervisor;
            this.isPhD = isPhD;
            this.undergraduateSchool = undergraduateSchool;
        } catch (Exception e) {
            System.exit(0);
        }
    }

    public GraduateStudents () {
        try {
            this.undergraduateSchool = null;
            this.supervisor = null;
            this.isPhD = false;
        } catch (Exception e) {
            System.exit(0);
        }
    }

    public String getSupervisor() {
        return supervisor;
    }
    public boolean getIsPhD() {
        return isPhD;
    }
    public String getUndergraduateSchool() {
        return undergraduateSchool;
    }

    public void setSupervisor(String supervisor) throws Exception {
        if ( !(supervisor.equals("") || supervisor.equals(null))) {
            this.supervisor = supervisor;
        }
        else{
            throw new Exception ("Fatal error: supervisor cannot be left blank");
        }
    }
    public void setIsPhD(boolean isPhD) throws Exception {
        this.isPhD = isPhD;
    }
    public void setUndergraduateSchool(String undergraduateSchool) throws Exception {
        if ( !(undergraduateSchool.equals("") || undergraduateSchool.equals(null))) {
            this.undergraduateSchool = undergraduateSchool;
        }
        else{
            throw new Exception ("Fatal error: undergraduateSchool cannot be left blank");
        }
    }

    @Override
    public String toString() {
        if ( getIsPhD() == true ) {
            return getProgram() + " " + getYear() + " " + getGrade() + " " + getSupervisor() + " " + "1" + " " + getUndergraduateSchool() + " " + getLastName();
        }
        return getProgram() + " " + getYear() + " " + getGrade() + " " + getSupervisor() + " " + "0" + " " + getUndergraduateSchool() + " " + getLastName();
    } 

    public String toString2() {
        if ( getIsPhD() == true ) {
            return getProgram() + "\n" + getYear() + "\n" + getGrade() + "\n" + getSupervisor() + "\n" + "1" + "\n" + getUndergraduateSchool() + "\n" + getLastName();
        }
        return getProgram() + "\n" + getYear() + "\n" + getGrade() + "\n" + getSupervisor() + "\n" + "0" + "\n" + getUndergraduateSchool() + "\n" + getLastName();
    } 
}
