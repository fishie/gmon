package se.rishie.gmon

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.view.*
import net.openid.appauth.AuthorizationRequest.Builder
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration

class MainActivity : AppCompatActivity() {
    var flag: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun swapLabelText(view: View) {
        view.label.text = if (flag) "Bye World!" else "Hello World!"
        flag = !flag
    }

    fun login(view: View) {
        val clientId = "686303665476-co84e2hf3p4tcjreiosio4l7reho36ra.apps.googleusercontent.com"
        val authorizationEndpoint = "https://accounts.google.com/o/oauth2/v2/auth"
        val tokenEndpoint = "https://www.googleapis.com/oauth2/v4/token"
        val scope = "https://www.googleapis.com/auth/drive.appdata"
        val redirectUri = Uri.parse("se.rishie.gmon:/oauth2redirect")
        val responseType = "code"

        val config = AuthorizationServiceConfiguration(Uri.parse(authorizationEndpoint), Uri.parse(tokenEndpoint))
        val authRequestBuilder = Builder(config, clientId, responseType, redirectUri)
        authRequestBuilder.setScope(scope)
        val request = authRequestBuilder.build()
        val service = AuthorizationService(view.context)
        val completionIntent = PendingIntent.getActivity(this, 0, Intent(this, AuthCompletedActivity::class.java), 0)
        service.performAuthorizationRequest(request, completionIntent)
    }
}
