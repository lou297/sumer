package com.example.sumer.util

object Constants {
    //STT 함수
    const val END_VOICE = "javascript:endVoice()"
    const val PRE_VOICE = "javascript:preVoice()"
    fun SET_VOICE(voice : String) = "javascript:setVoice('${voice}')"

    //국문
    const val KR = "ko-KR"

    //Authentication Credential 설정
    const val AUTHENTICATION = "Authorization"
    const val AUTH_CREDENTIAL = "cred"
    const val SAVE_CREDENTIAL = "saveCredential"
    const val DELETE_CREDENTIAL = "deleteCredential"
    const val OPEN_BROWSER = "openBrowser"

    //SharedPreference Key
    const val SETTING = "setting"

    const val EMPTY_STRING = ""
}