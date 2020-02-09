package com.rems.model.mapper;

import java.util.List;

import com.rems.exception.ServiceException;
import com.rems.model.DO.TdmUserDO;
import com.rems.model.DO.TdmUsersAuthDO;
import com.rems.model.DTO.TdmUserAuthDTO;
import com.rems.model.DTO.TdmUserDTO;

public interface RemsUserMapper
{

	public TdmUserDTO converTdmUserDOToUserSearchResultDTO(TdmUserDO tdmUserDo,
			TdmUsersAuthDO tdmUsersAuthDo) throws ServiceException;

	public TdmUserDTO converTdmUserDOToUserSearchResultDTO(TdmUserDO tdmUserDo)
			throws ServiceException;

	public List<TdmUserDTO> converTdmUserDOToUserSearchResultListDTO(List<TdmUserDO> listTdmUserDo)
			throws ServiceException;

	public TdmUserDO converTdmUserDTOToUserDO(TdmUserDTO tdmUserDo, TdmUserAuthDTO tdmUsersAuthDo)
			throws ServiceException;

	public TdmUserDO converTdmUserDTOToUserDO(TdmUserDTO tdmUserDo) throws ServiceException;

	public List<TdmUserDO> converTdmUserDTOToUserDO(List<TdmUserDTO> listTdmUserDo)
			throws ServiceException;
}
