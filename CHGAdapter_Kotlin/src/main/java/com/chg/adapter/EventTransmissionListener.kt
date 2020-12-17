package com.chg.adapter

/**
 * 事件传输
 */
open interface EventTransmissionListener {

    /**
     * 统一的事件传输方法
     *
     * @param target   事件发生的场所（一般是某一个布局或者Holder）
     * @param params   事件需要向外部传递的参数
     * @param tag      当一个view中出现多个事件使用这个参数来区分
     * @param callBack 异步回调返回数据
     * @return 同步返回数据
     */
    open fun onEventTransmissionListener(
        target: Any?,
        params: Any?,
        tag: Int,
        callBack: CallBack?
    ): Any?

    /**
     * 回调
     *
     * @param object 回调传递的内容
     * @return
     */
    open interface CallBack {
        open fun callBack(any: Any?): Any?
    }
}