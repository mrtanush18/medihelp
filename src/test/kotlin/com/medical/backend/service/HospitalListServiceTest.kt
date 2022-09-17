package com.medical.backend.service

import com.medical.backend.model.HospitalList
import com.medical.backend.repository.HospitalListRepository
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class HospitalListServiceTest {
    val Hospital1 = HospitalList("1", "Lilavati","981", "Sion")
    val Hospital2 = HospitalList("2", "Criticare","982", "Parle")

    private val HospitalListRepository = mockk<HospitalListRepository>(){

        every {
            findAll()
        } returns Flux.just(Hospital1,Hospital2)

        every {
            findById("1")
        } returns Mono.just(Hospital1)

//        every {
//            deleteById("1")
//        } returns Mono.just(Hospital1)

    }

    private val HospitalListService = HospitalListService(HospitalListRepository)

    @Test
    fun `should return all hospitals`()
    {
        val firstHospital = HospitalListService.findAll().blockFirst()
        val secondPatient = HospitalListService.findAll().blockLast()

        firstHospital shouldBe Hospital1
        secondPatient shouldBe Hospital2
    }

    @Test
    fun `should find hospital on the basis of id`() {

        val findHospital= HospitalListService.findById("1").block()

        findHospital shouldBe Hospital1
    }


//    @Test
//    fun `should delete the hospital on basis of id`() {
//
//        val result = HospitalListService.deleteById("1").block()
//
//    }


}

