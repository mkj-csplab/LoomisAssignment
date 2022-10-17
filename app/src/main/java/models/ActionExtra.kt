package models

//! Action Extras class
//! Descr: Each action has a set of STRING extra data.
data class ActionExtra(
        val action: String,
        val extras: List<Pair<String,String>>,
        // Should we have key values in the Parceable mode
        //var Extras: listOf<String, Any> // Any needs to cast, see if there is a better way to do it
    ) : java.io.Serializable
//! Chk: Data class or methods for.
//! addAction, no that outside this class
//! addExtra: Maybe, or from outside: addExtra(actionExtras[x].extras.add(e))
