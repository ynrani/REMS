/*---------------------------------------------------------------------------------------
 * Object Name: RemsAdminServiceImpl.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          12/06/15  NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 <CapGemini>
 *---------------------------------------------------------------------------------------*/

package com.rems.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rems.constant.MessageConstant;
import com.rems.dao.RemsAdminDAO;
import com.rems.email.EmailNotificationService;
import com.rems.exception.DAOException;
import com.rems.exception.ServiceException;
import com.rems.model.DO.TdmUserDO;
import com.rems.model.DTO.ForgotPassword;
import com.rems.model.DTO.TdmUserDTO;
import com.rems.model.mapper.RemsUserMapper;
import com.rems.service.RemsAdminService;

/**
 * 
 * @author Seshadri Chowdary
 *
 */

@Component
@Service(MessageConstant.SERVICE_ADMIN)
@Transactional(propagation = Propagation.REQUIRED)
public class RemsAdminServiceImpl extends TdmBaseServiceImpl implements RemsAdminService
{
	private static Logger logger = Logger.getLogger(RemsAdminServiceImpl.class);

	@Autowired
	RemsAdminDAO remsAdminDAO;

	@Autowired
	RemsUserMapper tdmUserMapper;

	@Autowired
	EmailNotificationService emailNotificationService;

	@Override
	public String saveUserDetails(TdmUserDTO userdo, boolean bEdit) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			String str = remsAdminDAO.saveUserDetails(
					tdmUserMapper.converTdmUserDTOToUserDO(userdo), bEdit, managerUser);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.LOG_INFO_RETURN);
			return str;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_SAVE_USER_DRL + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_SAVE_USER_DRL + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_SAVE_USER_DRL + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<TdmUserDTO> getAllUser(TdmUserDTO userdo, int offSet, int recordsperpage, boolean b)
			throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			List<TdmUserDTO> userList = tdmUserMapper
					.converTdmUserDOToUserSearchResultListDTO(remsAdminDAO.getAllUser(
							tdmUserMapper.converTdmUserDTOToUserDO(userdo), offSet, recordsperpage,
							b, managerUser));
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_INFO_RETURN);
			return userList;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_GET_ALL_USERS + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_GET_ALL_USERS + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_GET_ALL_USERS + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmUserDTO getEditUser(String userId) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_EDIT_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			TdmUserDTO tdmUserDTO = tdmUserMapper.converTdmUserDOToUserSearchResultDTO(remsAdminDAO
					.getEditUser(userId, managerUser));
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_EDIT_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return tdmUserDTO;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_GET_EDIT_USER + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_GET_EDIT_USER + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_GET_EDIT_USER + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public String deleteUserByUserId(String userId) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			String str = remsAdminDAO.deleteUserByUserId(userId, managerUser);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return str;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
					+ MessageConstant.TDM_ADMIN_DEL_USER);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
					+ MessageConstant.TDM_ADMIN_DEL_USER);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
					+ MessageConstant.TDM_ADMIN_DEL_USER);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public Long searchUserRecordsCount(TdmUserDTO userdo) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			Long cnt = remsAdminDAO.searchUserRecordsCount(
					tdmUserMapper.converTdmUserDTOToUserDO(userdo), managerUser);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT + MessageConstant.LOG_INFO_RETURN);
			return cnt;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public boolean validateUserId(String userid) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_VALIDATE_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			boolean res = remsAdminDAO.validateUserId(userid, managerUser);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_VALIDATE_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return res;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_VALIDATE_USER);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_VALIDATE_USER);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_VALIDATE_USER);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public String getUserRole(TdmUserDO userdo) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_USER_ROLE
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			String str = remsAdminDAO.getUserRole(userdo, managerUser);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_USER_ROLE
					+ MessageConstant.LOG_INFO_RETURN);
			return str;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_GET_USER_ROLE + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_GET_USER_ROLE + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_ADMIN_GET_USER_ROLE + MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public Boolean forgotPassword(ForgotPassword forgotPasswordDTO) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_FTD_SER_FORGOT_PASS1
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		EntityManager managerUser = openUserEntityManager();
		try {
			TdmUserDO userDO = remsAdminDAO.checkAvailabilityOfUserId(forgotPasswordDTO.getUserId(),
					managerUser);
			if (null != userDO && null != userDO.getPassword() && null != userDO.getEmailId()) {
				forgotPasswordDTO.setPassword(userDO.getPassword());
				// emailNotificationService.sendEmailNotification(forgotPasswordDTO);
				logger.info(MessageConstant.TDM_ADMIN_SERVICE
						+ MessageConstant.TDM_FTD_SER_FORGOT_PASS1
						+ MessageConstant.LOG_INFO_RETURN);
				return true;
			}
			return false;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_FTD_SER_FORGOT_PASS1
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_FTD_SER_FORGOT_PASS1
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE
					+ MessageConstant.TDM_FTD_SER_FORGOT_PASS1
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		} finally {
			closeUserEntityManager(managerUser);
		}
	}

}
