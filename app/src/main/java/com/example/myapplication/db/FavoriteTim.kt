package com.example.football2.db

data class FavoriteTim(var id: Long?, var teamId: String?, var nameTeam: String?,  var legueTeam: String?, var countryTeam: String?,
                       var stadiumTeamTim: String?, var imgTeam: String?) {

    companion object {
        const val TABLE_FAVORITE_TIM: String = "TABLE_FAVORITE_TIM"
        const val ID: String = "ID_"
        const val TEAM_ID_TIM: String = "TEAM_ID_TIM"
        const val NAME_TEAM_TIM: String = "NAME_TEAM_TIM"
        const val LEGUE_TEAM_TIM: String = "LEGUE_TEAM_TIM"
        const val COUNTRY_TEAM_TIM: String = "COUNTRY_TEAM_TIM"
        const val STADIUM_TEAM_TIM: String = "STADIUM_TEAM_TIM"
        const val IMG_TEAM_TIM: String = "IMG_TEAM_TIM"

        //var detail
        var teamIdTim: String = ""
        var stadiumTeamTim: String = ""
        var imgTeamTim: String = ""
        var legueTeamTim: String = ""
        var countryTeamTim: String = ""
        var nameTeamTim: String = ""
    }
}