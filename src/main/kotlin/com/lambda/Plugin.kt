package com.lambda

import com.lambda.client.plugin.api.Plugin


internal object Plugin : Plugin() {

    override fun onLoad() {
        commands.add(Command)
    }


}