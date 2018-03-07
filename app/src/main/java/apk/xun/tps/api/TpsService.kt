package apk.xun.tps.api

import apk.xun.tps.model.resp.Resp
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by fengdianxun on 2017/12/18.
 */


interface TpsService {
    @FormUrlEncoded
    @POST("login")
    fun login(@Field("account") account: String, @Field("password") password: String): Deferred<Response<Resp<Any>>>


}
