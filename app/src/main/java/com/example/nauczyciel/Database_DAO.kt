package com.example.nauczyciel
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.nauczyciel.entities.Student
import com.example.nauczyciel.entities.Subject
import com.example.nauczyciel.entities.Mark
import com.example.nauczyciel.entities.Student_Subject
@Dao
interface Database_DAO {
    @Insert
    fun insertStudent(student:Student)

    @Insert
    fun insertSubject(subject: Subject)

    @Insert
    fun insertMark(mark:Mark)

    @Insert
    fun insertStudentToSubject(student_Subject:Student_Subject)

    @Delete
    fun deleteStudent(student: Student)

    @Delete
    fun deleteMark(mark: Mark)

    @Delete
    fun deleteSubject(subject: Subject)

    @Query("SELECT * FROM students_table")
    fun getAllStudents():LiveData<List<Student>>

    @Query("DELETE FROM students_table")
    fun DeleteAllStudents()

    @Query("DELETE FROM marks_table")
    fun DeleteAllMarks()

    @Query("DELETE FROM studentsubject_table")
    fun DeleteAllSB()

    @Query("DELETE FROM subjects_table")
    fun DeleteAllSubject()


    @Query("SELECT * FROM subjects_table")
    fun getAllSubjects():LiveData<List<Subject>>

    @Query("SELECT * FROM marks_table")
    fun getAllMarks():LiveData<List<Mark>>

    @Query("SELECT * FROM marks_table WHERE idSubject=:idSubject AND idStudent=:idStudent  ")
    fun GetFromMarks(idStudent:String,idSubject:Long):LiveData<List<Mark>>

    @Query("SELECT * FROM marks_table m INNER JOIN subjects_table s ON m.idSubject=s.id ")
    fun getSubjectMarks():LiveData<List<Mark>>

    //@Query("SELECT * FROM students_table st INNER JOIN studentsubject_table stsb on st.id=stsb.idStudent WHERE stsb.idSubject=:subjectid")
    @Query("SELECT * FROM  studentsubject_table stsb INNER JOIN students_table st on st.id=stsb.idStudent WHERE stsb.idSubject=:subjectid")
    fun getStudentsOfSubject(subjectid:Long):LiveData<List<Student>>

    //@Query("SELECT * FROM students_table st LEFT OUTER JOIN studentsubject_table stsb on st.id=stsb.idStudent WHERE stsb.idSubject=:subjectid")

    @Query("SELECT * FROM studentsubject_table stsb INNER JOIN students_table st on stsb.idSubject!=:subjectid WHERE st.id=stsb.idStudent ")
    fun getStudentsNotOfSubject(subjectid:Long):LiveData<List<Student>>

    @Query("SELECT EXISTS(SELECT * FROM students_table WHERE id=:id)")
    fun isInStudentsTable(id:String):Boolean

  //  @Query("SELECT EXISTS(SELECT * FROM subjects_table WHERE id=:id)")
   // fun isInSubjectTable(id:String):Boolean
}