package apk.xun.tps.api

import android.content.Context

/**
 * Created by fengdianxun on 2017/12/18.
 */

object SimpleApi {
    private const val SERVER = "http://localhost/"


    fun create(context: Context): TpsService {
        return RetrofitApiClient(SERVER).create(context, TpsService::class.java)
    }
}
