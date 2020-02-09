package com.rems.model.mapper;

import java.util.List;

import com.rems.model.DO.RemsCrteHostSerDO;
import com.rems.model.DTO.RemsCreateHostServerDTO;

public interface RemsRequestMapper
{

	public RemsCrteHostSerDO converRemsCreateHostServerDTOToRemsCrteHostSerDO(
			RemsCreateHostServerDTO remsCreateHostServerDTO, String seq);

	public RemsCreateHostServerDTO converRemsCrteHostSerDOToRemsCreateHostServerDTO(
			RemsCrteHostSerDO remsCrteHostSerDO);

	public List<RemsCreateHostServerDTO> converRemsCrteHostSerDOListToTdmSearchRequestDTOList(
			List<RemsCrteHostSerDO> remsCrteHostSerDOs);

}
