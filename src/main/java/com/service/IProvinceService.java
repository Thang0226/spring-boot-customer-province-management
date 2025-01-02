package com.service;

import com.model.DTO.ProvinceDTO;
import com.model.Province;

public interface IProvinceService extends IService<Province> {
    Iterable<ProvinceDTO> countCustomerByProvince();
}
