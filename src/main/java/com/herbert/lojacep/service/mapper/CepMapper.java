package com.herbert.lojacep.service.mapper;

import com.herbert.lojacep.model.Cep;
import com.herbert.lojacep.service.dto.CepDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LojaMapper.class})
public interface CepMapper extends EntityMapper<CepDto, Cep> {

}