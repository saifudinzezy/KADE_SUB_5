package com.dicoding.kotlinacademy.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.football2.db.Favorite
import org.jetbrains.anko.db.*

/**
 * Created by root on 2/6/18.
 */
class MyDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.TEAM_ID to TEXT + UNIQUE,
            Favorite.TEAM_NAME to TEXT,
            Favorite.DATE to TEXT,
            Favorite.TIME to TEXT,
            Favorite.SKOR to TEXT,
            Favorite.SPORT to TEXT,
            Favorite.HOME_TEAM to TEXT,
            Favorite.GOAL to TEXT,
            Favorite.RED_CARD to TEXT,
            Favorite.YELLOW_CARD to TEXT,
            //TEAM 1
            Favorite.ID_HOME to TEXT,
            Favorite.IMG_TEAM_1 to TEXT,
            Favorite.LEGUE_TEAM_1 to TEXT,
            Favorite.COUNTRY_TEAM_1 to TEXT,
            Favorite.STADIUM_TEAM_1 to TEXT,
            Favorite.NAME_TEAM_1 to TEXT,
            //TEAM 2
            Favorite.ID_AWAY to TEXT,
            Favorite.IMG_TEAM_2 to TEXT,
            Favorite.LEGUE_TEAM_2 to TEXT,
            Favorite.COUNTRY_TEAM_2 to TEXT,
            Favorite.STADIUM_TEAM_2 to TEXT,
            Favorite.NAME_TEAM_2 to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)