package com.herbert.lojacep.service.mapper;

import com.herbert.lojacep.model.Loja;
import com.herbert.lojacep.service.dto.LojaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LojaMapper extends EntityMapper<LojaDto, Loja> {

}
