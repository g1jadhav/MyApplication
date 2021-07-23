package com.temporary.myapplication.roomdatabase


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.temporary.myapplication.customClasses.Constant

@Database(entities = [RocketListDataEntity::class], version = Constant.DB_VERSION)
@TypeConverters(
    ImagesTypeConverter::class,
    IdTypeConverter::class,
    NameTypeConverter::class,
    CountryTypeConverter::class,
    EngineTypeConverter::class,
    StatusTypeConverter::class,
    CostTypeConverter::class,
    SuccessTypeConverter::class,
    DescTypeConverter::class,
    WikiTypeConverter::class,
    HeightTypeConverter::class,
    DiameterTypeConverter::class
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun mRocketListDao(): RocketListDao

    companion object {
        @Volatile
        private var mInstance: AppDataBase? = null

        fun getInstance(mContext: Context): AppDataBase =
            mInstance ?: synchronized(this) {
                mInstance ?: buildDatabaseInstance(mContext).also {
                    mInstance = it
                }
            }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext, AppDataBase::class.java, Constant.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }
}


