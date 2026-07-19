//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ca.tetervak.coursedata.data;

import ca.tetervak.coursedata.model

        .Course;
import ca.tetervak.coursedata.model.Student;
import ca.tetervak.coursedata.repository.CourseRepository;
import ca.tetervak.coursedata.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    StudentRepository studentRepository;
    CourseRepository courseRepository;

    public DataInitializer(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @PostConstruct
    public void init() {
        Student bart = new Student("Bart", "Simpson");
        Student lisa = new Student("Lisa", "Simpson");
        Student marge = new Student("Marge", "Simpson");
        Student homer = new Student("Homer", "Simpson");
        Student maggie = new Student("Maggie", "Simpson");
        Course prog10004 = new Course("PROG10004", "Programming Principles");
        Course syst10082 = new Course("SYST10082", "Operating Systems Fundamentals");
        Course math10025 = new Course("MATH10025", "Mathematics for Computing");
        Course tele10025 = new Course("TELE10025", "Edge to Core: Network Foundations");
        Course prog32758 = new Course("PROG32758", "Enterprise Java Development");
        bart = (Student)this.studentRepository.save(bart);
        lisa = (Student)this.studentRepository.save(lisa);
        marge = (Student)this.studentRepository.save(marge);
        homer = (Student)this.studentRepository.save(homer);
        maggie = (Student)this.studentRepository.save(maggie);
        prog10004 = (Course)this.courseRepository.save(prog10004);
        syst10082 = (Course)this.courseRepository.save(syst10082);
        math10025 = (Course)this.courseRepository.save(math10025);
        tele10025 = (Course)this.courseRepository.save(tele10025);
        prog32758 = (Course)this.courseRepository.save(prog32758);
        bart.registerToCourse(prog10004);
        bart.registerToCourse(syst10082);
        this.studentRepository.save(bart);
        lisa.registerToCourse(math10025);
        lisa.registerToCourse(tele10025);
        this.studentRepository.save(lisa);
        marge.registerToCourse(prog10004);
        marge.registerToCourse(syst10082);
        marge.registerToCourse(math10025);
        this.studentRepository.save(marge);
        homer.registerToCourse(tele10025);
        this.studentRepository.save(homer);
    }
}
