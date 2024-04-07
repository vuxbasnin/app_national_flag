package com.base.basemvvm.data.network.service

import com.base.basemvvm.data.model.response.demo.DemoReponse
import com.base.basemvvm.data.model.response.flag.NationalFlagResponse
import com.base.basemvvm.data.network.DefaultRequest
import com.base.basemvvm.data.network.Endpoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface DemoService {
    @GET(Endpoint.DEMO)
    suspend fun getDemo(@Body body: DefaultRequest): Response<DemoReponse>

    @GET(Endpoint.NATION_FLAG)
    suspend fun getNationFlag(): Response<NationalFlagResponse>
}