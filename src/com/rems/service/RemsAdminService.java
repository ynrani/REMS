package com.rems.service;

import java.util.List;

import com.rems.exception.ServiceException;
import com.rems.model.DO.TdmUserDO;
import com.rems.model.DTO.ForgotPassword;
import com.rems.model.DTO.TdmUserDTO;

public interface RemsAdminService
{
	public Boolean forgotPassword(ForgotPassword forgotPasswordDTO) throws ServiceException;

	public String saveUserDetails(TdmUserDTO userdo, boolean bEdit) throws ServiceException;

	public List<TdmUserDTO> getAllUser(TdmUserDTO userdo, int offSet, int recordsperpage, boolean b)
			throws ServiceException;

	public TdmUserDTO getEditUser(String userId) throws ServiceException;

	public String deleteUserByUserId(String userId) throws ServiceException;

	public Long searchUserRecordsCount(TdmUserDTO userdo) throws ServiceException;

	public boolean validateUserId(String userid) throws ServiceException;

	public String getUserRole(TdmUserDO userdo) throws ServiceException;
}
