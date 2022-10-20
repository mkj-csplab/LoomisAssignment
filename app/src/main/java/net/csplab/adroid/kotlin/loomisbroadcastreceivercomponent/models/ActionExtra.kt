package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.models

//! Action Extras class
//! Descr: Each action has a set of STRING extra data.
class ActionExtra(
        var action: String,
        var extras: MutableList<Pair<String,String>>?, //! Can have empty action with no extras
        // Should we have key values in the Parceable mode
        //var Extras: listOf<String, Any> // Any needs to cast, see if there is a better way to do it
    ) : java.io.Serializable {
}

//! addAction, no that outside this class
//! addExtra: Maybe, or from outside: addExtra(actionExtras[x].extras.add(e))
