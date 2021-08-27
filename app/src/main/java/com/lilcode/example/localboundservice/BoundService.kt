package com.lilcode.example.localboundservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

class BoundService : Service() {

    private val myBinder: IBinder = MyLocalBinder() // Binder 는 IBinder 의 서브 클래스

    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }

    // 현재 시간 반환. 클라이언트에서 호출할 함수임.
    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US)
        return dateFormat.format(Date())
    }

    inner class MyLocalBinder : Binder() { // inner 클래스로 바인더의 서브 클래스 생성
        fun getService() : BoundService { // 바운드 서비스의 인스턴스를 반환 (클라이언트에서 사용)
            return this@BoundService // 바운드 서비스의 현재 인스턴스 참조를 반환한다.
        }
    }
}