package pers.mihao.careerism.java.base.comparatorTest;

import java.util.Comparator;

/**
 * @author hspcadmin
 */
public class MyComparator implements Comparator<Student> {

    @Override
    public int compare(Student student, Student otherStudent) {
        return student.getScore().compareTo(otherStudent.getScore());
    }
}
