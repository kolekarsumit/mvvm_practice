package com.example.mvvm.repository

import com.example.mvvm.data.ZipApi
import javax.inject.Inject

class ZipRepo @Inject constructor(
    private val api: ZipApi
) {
    suspend fun getZipDetails(zip: String) =
        api.getZipDetail(zip)
}