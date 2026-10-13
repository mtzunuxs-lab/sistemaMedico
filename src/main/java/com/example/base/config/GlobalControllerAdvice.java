package com.example.base.config;

import com.example.base.model.HospitalInfo;
import com.example.base.repository.HospitalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private HospitalInfoRepository hospitalInfoRepository;

    @ModelAttribute("hospital")
    public HospitalInfo getHospitalInfo() {
        return hospitalInfoRepository.findFirstByActivo((short) 1).orElse(null);
    }
}