package com.example.nauczyciel
import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nauczyciel.entities.Mark
import com.example.nauczyciel.entities.Student
import com.example.nauczyciel.entities.Student_Subject
import com.example.nauczyciel.entities.Subject

@Database(entities=[Student::class,Subject::class,Mark::class,Student_Subject::class], version = 1, exportSchema = false)
abstract class Database_Helper: RoomDatabase() {
    abstract val databaseDao:Database_DAO

    companion object{
        @Volatile
        private var INSTANCE: Database_Helper?=null
        fun getInstance(context: Context):Database_Helper{
            synchronized(this){
                var instance= INSTANCE
                if(instance==null){
                    instance=Room.databaseBuilder(context.applicationContext,Database_Helper::class.java,"Database_Helper").fallbackToDestructiveMigration().build()
                    INSTANCE = instance

                }
                return instance
            }


        }


    }


}
