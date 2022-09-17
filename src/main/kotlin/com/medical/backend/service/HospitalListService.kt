package com.medical.backend.service

import com.medical.backend.model.HospitalList
import com.medical.backend.repository.HospitalListRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class HospitalListService(
    @Autowired
    val HospitalListRepository: HospitalListRepository

) {

    fun findAll() : Flux<HospitalList> {
        return HospitalListRepository.findAll()
    }

    fun addToHospitalList(HospitalList: HospitalList):Mono<HospitalList>{
        return HospitalListRepository.save(HospitalList)
    }


    fun updateById(id: String, HospitalList: HospitalList):Mono<HospitalList>{
        return HospitalListRepository.save(HospitalList)
    }

    fun deleteById(hospitalId: String): Mono<Void> {
        return HospitalListRepository.deleteById(hospitalId)
    }

    fun findById(hospitalId: String): Mono<HospitalList>{
        return HospitalListRepository.findById(hospitalId)
    }


}