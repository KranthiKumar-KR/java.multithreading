package bankChallenge1n2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package bankChallenge1n2 on 6/26/17.
 */
public class Challenge8 {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Tutor tutor = new Tutor(lock);
        Student student = new Student(tutor, lock);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });

        tutorThread.start();
        studentThread.start();
    }
}

class Tutor {
    private Student student;
    private Lock lock;

    Tutor(ReentrantLock lock) {
        this.lock = lock;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void studyTime() {
        try {
            if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                System.out.println("Tutor has arrived");
                try {
                    // wait for student to arrive and hand in assignment
                    student.startStudy();
                    System.out.println("Tutor is studying with student");
                } finally {

                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {

        }

    }

    public void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}

class Student {
    private Lock lock;

    private Tutor tutor;

    Student(Tutor tutor, ReentrantLock lock) {
        this.tutor = tutor;
        this.lock = lock;
    }

    public void startStudy() {
        // study
        System.out.println("Student is studying");

    }

    public void handInAssignment() {
        try {
            if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println("Student handed in assignment");
                    tutor.getProgressReport();
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("failed to obtain the lock");
            }
        } catch (InterruptedException e) {

        }

    }
}