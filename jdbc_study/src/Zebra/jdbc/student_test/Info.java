package Zebra.jdbc.student_test;

import java.math.BigDecimal;


/**
 * Created with IntelliJ IDEA.
 * User: 13498
 * Date: 2022/1/22
 * Time: 18:58
 * Description: No Description
 */
public class Info {
    private String className;
    private String studentName;
    private String courseName;
    private BigDecimal score;

    public Info(String className, String studentName, String courseName, BigDecimal score) {
        this.className = className;
        this.studentName = studentName;
        this.courseName = courseName;
        this.score = score;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Info{" +
                "className='" + className + '\'' +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", score=" + score +
                '}';
    }
}
