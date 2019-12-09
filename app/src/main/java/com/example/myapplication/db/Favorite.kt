package com.example.football2.db

data class Favorite(var id: Long?, var teamId: String?, var teamName: String?, var date: String?, var time: String?, var skor: String?, var sport: String?, var homeTeam: String?,
                    var goal: String?, var redCard: String?, var yellowCard: String?,
                    var idHome: String?, var imgTeam1: String?, var legueTeam1: String?, var countryTeam1: String?, var stadiumTeam1: String?, var nameTeam1: String?,
                    var idAway: String?, var imgTeam2: String?, var legueTeam2: String?, var countryTeam2: String?, var stadiumTeam2: String?, var nameTeam2: String?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val DATE: String = "DATE"
        const val TIME: String = "TIME"
        const val SKOR: String = "SKOR"
        const val SPORT: String = "SPORT"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val GOAL: String = "GOAL"
        const val RED_CARD: String = "RED_CARD"
        const val YELLOW_CARD: String = "YELLOW_CARD"
        //TEAM 1
        const val ID_HOME: String = "ID_HOME"
        const val IMG_TEAM_1: String = "IMG_TEAM_1"
        const val LEGUE_TEAM_1: String = "LEGUE_TEAM_1"
        const val COUNTRY_TEAM_1: String = "COUNTRY_TEAM_1"
        const val STADIUM_TEAM_1: String = "STADIUM_TEAM_1"
        const val NAME_TEAM_1: String = "NAME_TEAM_1"
        //TEAM 2
        const val ID_AWAY: String = "ID_AWAY"
        const val IMG_TEAM_2: String = "IMG_TEAM_2"
        const val LEGUE_TEAM_2: String = "LEGUE_TEAM_2"
        const val COUNTRY_TEAM_2: String = "COUNTRY_TEAM_2"
        const val STADIUM_TEAM_2: String = "STADIUM_TEAM_2"
        const val NAME_TEAM_2: String = "NAME_TEAM_2"

        //var detail
        var teamId: String = ""
        var teamName: String = ""
        var date: String = ""
        var time: String = ""
        var skor: String = ""
        var sport: String = ""
        var homeTeam: String = ""
        var goal: String = ""
        var redCard: String = ""
        var yellowCard: String = ""
        //TEAM 1
        var idHome: String = ""
        var imgTeam1: String = ""
        var legueTeam1: String = ""
        var countryTeam1: String = ""
        var stadiumTeam1: String = ""
        var nameTeam1: String = ""
        //TEAM 2
        var idAway: String = ""
        var imgTeam2: String = ""
        var legueTeam2: String = ""
        var countryTeam2: String = ""
        var stadiumTeam2: String = ""
        var nameTeam2: String = ""
    }
}