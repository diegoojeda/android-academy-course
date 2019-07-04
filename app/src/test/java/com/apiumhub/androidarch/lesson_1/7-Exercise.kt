package com.apiumhub.androidarch.lesson_1

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.Test

data class Student(var name: String, var grade: Int, var department: Department, var gender: Gender) {

    enum class Department {
        SCIENCES,
        COMPUTERS,
        PHILOLOGY
    }

    enum class Gender {
        MALE, FEMALE
    }
}

/*
Given an array of the above student class, we want to gather some data out of it:
1 - Average grade for every department
2 - Number of students by department
3 - Name of students by department
4 - Number of students by gender for each department

Make the following tests green!
 */

object Implementation {
    fun getAverageGradeForEachDepartment(students: List<Student>): Map<Student.Department, Double> =
        students
            .groupBy { it.department }
            .mapValues {
                it.value
                    .map { it.grade }
                    .average()
            }

    fun getNumberOfStudentsByDepartment(students: List<Student>): Map<Student.Department, Int> =
        students
            .groupBy { it.department }
            .mapValues { it.value.count() }

    fun getNameOfStudentsByDepartment(students: List<Student>): Map<Student.Department, List<String>> =
        students
            .groupBy { it.department }
            .mapValues { it.value.map { it.name } }

    fun getNumberOfStudentsByGenderForEachDepartment(students: List<Student>): Map<Student.Department, Map<Student.Gender, Int>> =
        students
            .groupBy { it.department }
            .mapValues {
                it.value
                    .groupBy {
                        it.gender
                    }
                    .mapValues {
                        it.value.count()
                    }
            }
}

class Tests {

    private val students = listOf(
        Student("Joe", 78, Student.Department.COMPUTERS, Student.Gender.MALE),
        Student("Jane", 43, Student.Department.PHILOLOGY, Student.Gender.FEMALE),
        Student("Phil", 85, Student.Department.SCIENCES, Student.Gender.MALE),
        Student("Bob", 13, Student.Department.COMPUTERS, Student.Gender.MALE),
        Student("Alice", 95, Student.Department.COMPUTERS, Student.Gender.FEMALE),
        Student("Mike", 33, Student.Department.SCIENCES, Student.Gender.MALE),
        Student("Robert", 67, Student.Department.PHILOLOGY, Student.Gender.MALE),
        Student("Michael", 98, Student.Department.SCIENCES, Student.Gender.MALE),
        Student("Christine", 76, Student.Department.COMPUTERS, Student.Gender.FEMALE)
    )

    @Test
    fun getAverageGradeForEveryDepartment() {
        val expected = mapOf(
            Student.Department.COMPUTERS to 65.5,
            Student.Department.PHILOLOGY to 55.0,
            Student.Department.SCIENCES to 72.0
        )
        assertThat(Implementation.getAverageGradeForEachDepartment(students)).isEqualTo(expected)
    }

    @Test
    fun getNumberOfStudentsByDepartment() {
        val expected = mapOf(
            Student.Department.COMPUTERS to 4,
            Student.Department.PHILOLOGY to 2,
            Student.Department.SCIENCES to 3
        )
        assertThat(Implementation.getNumberOfStudentsByDepartment(students)).isEqualTo(expected)
    }

    @Test
    fun getNameOfStudentsByDepartment() {
        val expected = mapOf(
            Student.Department.COMPUTERS to listOf("Joe", "Bob", "Alice", "Christine"),
            Student.Department.PHILOLOGY to listOf("Jane", "Robert"),
            Student.Department.SCIENCES to listOf("Phil", "Mike", "Michael")
        )
        assertThat(Implementation.getNameOfStudentsByDepartment(students)).isEqualTo(expected)
    }

    @Test
    fun getNumberOfStudentsByGenderForEachDepartment() {
        val expected = mapOf(
            Student.Department.COMPUTERS to mapOf(
                Student.Gender.MALE to 2,
                Student.Gender.FEMALE to 2
            ),
            Student.Department.PHILOLOGY to mapOf(
                Student.Gender.MALE to 1,
                Student.Gender.FEMALE to 1
            ),
            Student.Department.SCIENCES to mapOf(
                Student.Gender.MALE to 3
            )
        )
        assertThat(Implementation.getNumberOfStudentsByGenderForEachDepartment(students)).isEqualTo(expected)
    }

}

//region solutions
object Solutions {
    fun getAverageGradeForEveryDepartment(students: List<Student>): Map<Student.Department, Double> =
        students.groupBy { it.department }.mapValues { it.value.map { it.grade }.average() }

    fun getNumberOfStudentsByDepartment(students: List<Student>): Map<Student.Department, Int> =
        students.groupBy { it.department }.mapValues { it.value.count() }

    fun getNameOfStudentsByDepartment(students: List<Student>): Map<Student.Department, List<String>> =
        students.groupBy { it.department }.mapValues { it.value.map { it.name } }
}

//endregion
