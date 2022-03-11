package com.estados.data

import androidx.room.Room
import androidx.room.RoomDatabase

abstract class EstadoDatabase : RoomDatabase() {
    abstract fun estadoDao(): EstadoDao

    companion object {
        @Volatile
        private var INSTANCE: EstadoDatabase? = null

        fun getDatabase(context: android.content.Context) : EstadoDatabase {
            var instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                //Se crea el esquema de la base de datos -fisico-
                val basedatos = Room.databaseBuilder(
                    context.applicationContext,
                    EstadoDatabase::class.java,
                    "estado_database"
                ).build()
                INSTANCE = basedatos
                return basedatos
            }
        }
    }
}