package me.tigrao.aegis.network.ui

interface UiTransformer<in PARAMETER, out RESPONSE> {

    fun map(parameter: PARAMETER): RESPONSE
}
