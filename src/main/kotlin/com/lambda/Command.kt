package com.lambda

import com.lambda.client.command.ClientCommand
import com.lambda.client.util.FolderUtils
import com.lambda.client.util.text.MessageSendHelper
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Files
import kotlin.math.roundToInt

object Command : ClientCommand(
    name = "size",
    description = "Calculate the disk size of the chunks you generated."
) {
    init {
        execute("") {
            try {
                val folderName = mc.currentServerData?.serverName + "-" + mc.currentServerData?.serverIP
                val rV = File(File(FolderUtils.newChunksFolder), "$folderName/logs").toPath()
                var lines = 0
                Files.walk(rV).forEach { item ->
                    if(item.toString().endsWith(".csv")){
                        val file = File(rV.toString() + "/" + item.fileName.toString())
                        val reader = BufferedReader(FileReader(file))
                        while (reader.readLine() != null) lines++
                        reader.close()
                    }
                }
                MessageSendHelper.sendRawChatMessage("YOU HAVE LOADED $lines CHUNKS OF SIZE ${(lines * 0.00584).roundToInt() } MB")
            }catch (e: Exception){
                MessageSendHelper.sendRawChatMessage("ERROR: $e")
            }

        }
    }
}