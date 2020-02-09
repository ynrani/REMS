package com.rems.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rems.constant.MessageConstant;
import com.rems.dao.RemsInstanceDAO;
import com.rems.exception.DAOException;
import com.rems.exception.ServiceException;
import com.rems.model.DO.RemsCrteInstanceDO;
import com.rems.model.DTO.RemsCreateEnvInstanceDTO;
import com.rems.model.mapper.RemsInstanceMapper;
import com.rems.service.RemsInstanceService;

@Component
@Service("remsInstanceService")
@Transactional(propagation = Propagation.REQUIRED)
public class RemsInstanceServiceImpl extends TdmBaseServiceImpl implements RemsInstanceService
{

	private static Logger logger = Logger.getLogger(RemsInstanceServiceImpl.class);

	@Autowired
	RemsInstanceDAO remsInstanceDAO;

	@Autowired
	RemsInstanceMapper remsInstanceMapper;

	@Override
	public String createInstance(RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
				+ MessageConstant.LOG_INFO_PARAMS_NO);

		String seq = null;
		try {
			EntityManager managerUser = openUserEntityManager();
			if (!StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getId())) {
				seq = remsInstanceDAO.reqRuningRecId(managerUser);
			}

			String str = remsInstanceDAO.createInstance(remsInstanceMapper
					.converRemsCreateEnvInstanceDTOToRemsCrteInstanceDO(remsCreateEnvInstanceDTO, seq), managerUser);

			closeUserEntityManager(managerUser);

			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.LOG_INFO_RETURN);
			return str;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public Long instanceListCnt(String userId, boolean adminYn, RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO)
			throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			Long cnt = remsInstanceDAO.instanceListCnt(userId, adminYn, remsCreateEnvInstanceDTO, managerUser);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT
					+ MessageConstant.LOG_INFO_RETURN);
			return cnt;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<RemsCreateEnvInstanceDTO> instanceList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();

			List<RemsCreateEnvInstanceDTO> remsCreateEnvInstanceDTOs = remsInstanceMapper
					.converRemsCrteInstanceDOListToRemsCreateEnvInstanceDTOList(remsInstanceDAO.instanceList(offSet,
							recordsperpage, b, userId, adminYn, remsCreateEnvInstanceDTO, managerUser));
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCreateEnvInstanceDTOs;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public RemsCreateEnvInstanceDTO selectInstanceRec(String id) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			RemsCrteInstanceDO remsCrteInstanceDO = remsInstanceDAO.selectInstanceRec(id, managerUser);
			RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO = remsInstanceMapper
					.converRemsCrteInstanceDOToRemsCreateEnvInstanceDTO(remsCrteInstanceDO);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCreateEnvInstanceDTO;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
					+ MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public String daleteInstance(String id) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			String str = remsInstanceDAO.daleteInstance(id, managerUser);
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
	public List<String> getHoatServerList() throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			List<String> hostServerNames = remsInstanceDAO.getHoatServerList(managerUser);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return hostServerNames;
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
	public List<RemsCreateEnvInstanceDTO> envInstanceListAll(String userId, boolean adminYn) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();

			List<RemsCreateEnvInstanceDTO> remsCreateEnvInstanceDTOs = remsInstanceMapper
					.converRemsCrteInstanceDOListToRemsCreateEnvInstanceDTOList(remsInstanceDAO.envInstanceListAll(
							userId, adminYn, managerUser));
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCreateEnvInstanceDTOs;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

}
