package com.ashique.websocketsamplechat

import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.drafts.Draft
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import java.net.URISyntaxException
import java.nio.ByteBuffer


class EmptyClient : WebSocketClient {
    constructor(serverUri: URI?, draft: Draft?) : super(serverUri, draft) {}
    constructor(serverURI: URI?) : super(serverURI) {}

    override fun onOpen(handshakedata: ServerHandshake) {
        send("Hello, it is me. Mario :)")
        Log.d("hello", "new connection opened")
    }

    override fun onClose(code: Int, reason: String, remote: Boolean) {
        Log.d("hello", "closed with exit code $code additional info: $reason")
    }

    override fun onMessage(message: String) {
        Log.d("hello", "received message: $message")
    }

    override fun onMessage(message: ByteBuffer) {
        println("received ByteBuffer")
    }

    override fun onError(ex: Exception) {
        Log.d("hello", "an error occurred:$ex")
    }

    companion object {
        @Throws(URISyntaxException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val client: WebSocketClient = EmptyClient(URI("ws://localhost:8887"))
            client.connect()
        }
    }
}