package se.rishie.gmon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationException



class AuthCompletedActivity : AppCompatActivity() {
    var authorizationCode: String = "wat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("auth", "hej hej")

        val authorizationCode = AuthorizationResponse.fromIntent(intent)?.authorizationCode
        val exception = AuthorizationException.fromIntent(intent)

        if (authorizationCode != null) {
            Log.d("auth", "${authorizationCode}")
            this.authorizationCode = authorizationCode
        } else if (exception != null) {
            Log.d("auth", "Authorization flow failed: ${exception.message}")
        } else {
            Log.d("auth", "No authorization state retained - reauthorization required")
        }

        setContentView(R.layout.auth_completed)
    }

    override fun onStart() {
        super.onStart()
        findViewById<TextView>(R.id.textView).text = authorizationCode
    }
}
