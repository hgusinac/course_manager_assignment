package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.*;


public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        Course course = new Course(CourseSequencer.nextCourseId(),courseName,startDate,weekDuration);
        courses.add(course);
        return course;

    }

    @Override
    public Course findById(int id) {
        for (Course c : courses){
            if (c.getId() == id){
                return c;
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {

        Collection<Course> found = new ArrayList<>();

        for (Course c: courses) {
            if (c.getCourseName().toLowerCase().contains(name.toLowerCase()));{

                found.add(c);

            }
        }
        return found;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        Collection<Course> result = new ArrayList<>();

        for (Course course: courses){
            if (course.getStartDate().isBefore(end)) result.add(course);
        }
        return result;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        Collection<Course> result = new ArrayList<>();

        for (Course course: courses){
            if (course.getStartDate().isAfter(start)) result.add(course);
        }
        return result;
    }

    @Override
    public Collection<Course> findAll() {
        return new ArrayList<>(courses);
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {

        Collection<Course> result = new ArrayList<>();

        for (Course course: courses) {
            for (Student student: course.getStudents()){
                if (student.getId() == studentId) result.add(course);
            }
        }

        return result;
    }

    @Override
    public boolean removeCourse(Course course) {
       return courses.remove(course);
    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }
}

