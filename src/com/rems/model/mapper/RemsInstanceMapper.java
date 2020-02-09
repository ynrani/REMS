package com.rems.model.mapper;

import java.util.List;

import com.rems.model.DO.RemsCrteInstanceDO;
import com.rems.model.DTO.RemsCreateEnvInstanceDTO;

public interface RemsInstanceMapper
{

	public RemsCrteInstanceDO converRemsCreateEnvInstanceDTOToRemsCrteInstanceDO(
			RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO, String seq);

	public RemsCreateEnvInstanceDTO converRemsCrteInstanceDOToRemsCreateEnvInstanceDTO(
			RemsCrteInstanceDO remsCrteInstanceDO);

	public List<RemsCreateEnvInstanceDTO> converRemsCrteInstanceDOListToRemsCreateEnvInstanceDTOList(
			List<RemsCrteInstanceDO> remsCrteInstanceDOs);

}
