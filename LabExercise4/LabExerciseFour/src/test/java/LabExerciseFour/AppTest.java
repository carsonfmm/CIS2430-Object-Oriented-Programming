/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package LabExerciseFour;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    private Student yourStudents = new Student();
    
    @Test public void testSetGrade() throws Exception {
        System.out.println ( "Test setGrade method with a grade of double data type" );
        yourStudents.setGrade ( 97.56 );
        assertTrue ( yourStudents.getGrade() == 97.56 );
    }
    @Test public void testSetYear() {
        System.out.println ( "Test setYear method with a year of integer data type" );
        yourStudents.setYear ( 4 );
        assertTrue ( yourStudents.getYear() == 4 );
    }

    @Test public void testSetProgram() throws Exception {
        System.out.println ( "Test setProgram method with a program of String data type" );
        yourStudents.setProgram ( "CompSci" );
        assertTrue ( yourStudents.getProgram() == "CompSci" );
    }
}
