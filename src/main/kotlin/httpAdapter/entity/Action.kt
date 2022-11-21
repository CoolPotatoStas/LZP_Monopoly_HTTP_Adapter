package httpAdapter.entity

import httpAdapter.dictionary.TypeOfAction

open class Action(
    var idPlayer: Int,
    var idRoom: Int,
    var typeOfAction: TypeOfAction
    )