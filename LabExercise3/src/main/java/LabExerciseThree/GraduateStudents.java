package LabExerciseThree;

public class GraduateStudents extends Student {
    private String supervisor;
    private boolean isPhD;
    private String undergraduateSchool;

    public String getSupervisor() {
        return supervisor;
    }
    public boolean getIsPhD() {
        return isPhD;
    }
    public String getUndergraduateSchool() {
        return undergraduateSchool;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
    public void setIsPhD(boolean isPhD) {
        this.isPhD = isPhD;
    }
    public void setUndergraduateSchool(String undergraduateSchool) {
        this.undergraduateSchool = undergraduateSchool;
    }

    @Override
    public String toString() {
        if ( getIsPhD() == true ) {
            return super.toString() + "\n Supervisor: " + getSupervisor() + "\n Degree: " + "PhD" + "\n Undergraduate School: " + getUndergraduateSchool();
        }
        return super.toString() + "\n Supervisor: " + getSupervisor() + "\n Degree: " + "Masters" + "\n Undergraduate School: " + getUndergraduateSchool();
    } 
}
