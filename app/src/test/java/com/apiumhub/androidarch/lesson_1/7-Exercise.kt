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

object Solutions {
    fun getAverageGradeForEveryDepartment(students: List<Student>): Map<Student.Department, Double> =
        students.groupBy { it.department }.mapValues { value -> value.value.map { it.grade }.average() }


    fun getNumberOfStudentsByDepartment(students: List<Student>): Map<Student.Department, Int> =
        students.groupBy { it.department }.mapValues { value -> value.value.count() }

    fun getNameOfStudentsByDepartment(students: List<Student>): Map<Student.Department, List<String>> =
        students.groupBy { it.department }.mapValues { value -> value.value.map { it.name } }

    fun getNumberOfStudentsByGenderForEachDepartment(students: List<Student>): Map<Student.Department, Map<Student.Gender, Int>> {
        TODO()
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
        assertThat(Solutions.getAverageGradeForEveryDepartment(students)).isEqualTo(expected)
    }

    @Test
    fun getNumberOfStudentsByDepartment() {
        val expected = mapOf(
            Student.Department.COMPUTERS to 4,
            Student.Department.PHILOLOGY to 2,
            Student.Department.SCIENCES to 3
        )
        assertThat(Solutions.getNumberOfStudentsByDepartment(students)).isEqualTo(expected)
    }

    @Test
    fun getNameOfStudentsByDepartment() {
        val expected = mapOf(
            Student.Department.COMPUTERS to listOf("Joe", "Bob", "Alice", "Christine"),
            Student.Department.PHILOLOGY to listOf("Jane", "Robert"),
            Student.Department.SCIENCES to listOf("Phil", "Mike", "Michael")
        )
        assertThat(Solutions.getNameOfStudentsByDepartment(students)).isEqualTo(expected)
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
                Student.Gender.MALE to 3,
                Student.Gender.FEMALE to 0
            )
        )
        assertThat(Solutions.getNumberOfStudentsByGenderForEachDepartment(students)).isEqualTo(expected)

    }

}

/*
 val departments = students.map { it.department }.toSet()
        val baseMap = departments.map { it to 0 }.toMap().toMutableMap()
        return students.fold(baseMap, { acc, next ->
            acc.compute(next.department) { _, grade -> grade?.plus(next.grade)}
            return@fold acc
        })
 */