package apk.xun.tps.model.resp

class Resp<T>(val errorCode: Int,
        val msg: String?,
        val datas: List<T>,
        val data: T)