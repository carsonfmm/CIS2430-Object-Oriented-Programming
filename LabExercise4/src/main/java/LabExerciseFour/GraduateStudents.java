package LabExerciseFour;

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
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public GraduateStudents () {
        try {
            this.undergraduateSchool = null;
            this.supervisor = null;
            this.isPhD = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            return super.toString() + " " + getSupervisor() + " " + "1" + " " + getUndergraduateSchool();
        }
        return super.toString() + " " + getSupervisor() + " " + "0" + " " + getUndergraduateSchool();
    } 
}
