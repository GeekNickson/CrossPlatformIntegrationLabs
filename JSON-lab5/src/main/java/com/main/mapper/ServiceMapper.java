package com.main.mapper;

import com.main.model.common.Service;
import com.main.service.dto.ServiceDto;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {
    public ServiceDto toServiceDto(Service service) {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(service.getId());
        serviceDto.setName(service.getName());
        serviceDto.setPrice(service.getPrice());
        return serviceDto;
    }
}
