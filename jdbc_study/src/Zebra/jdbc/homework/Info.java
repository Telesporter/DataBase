package Zebra.jdbc.homework;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: 13498
 * Date: 2022/1/24
 * Time: 10:36
 * Description: No Description
 */
public class Info {
    private String bookName;
    private String studentName;
    private Timestamp timeStart;
    private Timestamp timeEnd;

    public Info(String bookName, String studentName, Timestamp timeStart, Timestamp timeEnd) {
        this.bookName = bookName;
        this.studentName = studentName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "Info{" +
                "bookName='" + bookName + '\'' +
                ", studentName='" + studentName + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                '}';
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Timestamp getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Timestamp timeEnd) {
        this.timeEnd = timeEnd;
    }
}
