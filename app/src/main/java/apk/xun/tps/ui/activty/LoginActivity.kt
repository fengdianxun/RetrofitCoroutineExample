package apk.xun.tps.ui.activty

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import apk.xun.tps.R
import apk.xun.tps.api.SimpleApi
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.sdk15.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = "登录"
        loginBtn.onClick { check() }
        async(UI) { check() }

    }

    suspend fun check() {
        if (accountEt.text.isEmpty()) {
            toast("账号不能为空")
        } else if (passwordEt.text.isEmpty()) {
            toast("密码不能为空")
        } else {

            loadApi()
        }
    }

    suspend fun loadApi() {
        loginBtn.text = "登录中..."
        val account = accountEt.text.toString()
        val password = passwordEt.text.toString()
        val response = SimpleApi.create(this@LoginActivity).login(account, password).await()
        loginBtn.text = "登录"
        if (response.isSuccessful) {
            if (response.body()?.errorCode == 0) {
                toast("登录成功")
            } else {
                toast(response.body()?.msg!!)
            }
        } else {
            toast(response.message())
        }

    }

}
