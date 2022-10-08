package models

import android.os.Bundle

data class ActionsExtra(
        val action: String,
        //val data: String, // Data is the intent data  that specifies the specific object to operate on
        //val extras: List<String, String>()
        val Extras: Bundle
        // Action String, prepend with pacakge name and ACTION like so. : newActionStr
        // Should we have key values in the Parceable mode
        // Or we could add inifinite extra, with intent.putExtra(KEY, VALUE)
        //var Extras: listOf<String, Any> // Any needs to cast, see if there is a better way to do it
    ) : java.io.Serializable
