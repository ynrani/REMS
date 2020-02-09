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
import com.rems.dao.RemsRequestDAO;
import com.rems.exception.DAOException;
import com.rems.exception.ServiceException;
import com.rems.model.DO.RemsCrteHostSerDO;
import com.rems.model.DTO.RemsCreateHostServerDTO;
import com.rems.model.mapper.RemsRequestMapper;
import com.rems.service.RemsRequestService;

@Component
@Service("remsRequestService")
@Transactional(propagation = Propagation.REQUIRED)
public class RemsRequestServiceImpl extends TdmBaseServiceImpl implements RemsRequestService
{

	private static Logger logger = Logger.getLogger(RemsRequestServiceImpl.class);

	@Autowired
	RemsRequestDAO remsRequestDAO;

	@Autowired
	RemsRequestMapper remsRequestMapper;

	@Override
	public String createHostServer(RemsCreateHostServerDTO remsCreateHostServerDTO) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
				+ MessageConstant.LOG_INFO_PARAMS_NO);

		String seq = null;
		try {
			EntityManager managerUser = openUserEntityManager();
			if (!StringUtils.isNotEmpty(remsCreateHostServerDTO.getId())) {
				seq = remsRequestDAO.reqRuningRecId(managerUser);
			}

			String str = remsRequestDAO.createHostServer(
					remsRequestMapper.converRemsCreateHostServerDTOToRemsCrteHostSerDO(remsCreateHostServerDTO, seq),
					managerUser);

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
	public Long hostServerListCnt(String userId, boolean adminYn, RemsCreateHostServerDTO remsCreateHostServerDTO)
			throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			Long cnt = remsRequestDAO.hostServerListCnt(userId, adminYn, remsCreateHostServerDTO, managerUser);
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
	public List<RemsCreateHostServerDTO> hostServerList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsCreateHostServerDTO remsCreateHostServerDTO) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();

			List<RemsCreateHostServerDTO> remsCreateHostServerDTOs = remsRequestMapper
					.converRemsCrteHostSerDOListToTdmSearchRequestDTOList(remsRequestDAO.hostServerList(offSet,
							recordsperpage, b, userId, adminYn, remsCreateHostServerDTO, managerUser));
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCreateHostServerDTOs;
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
	public RemsCreateHostServerDTO selectHostRec(String id) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			RemsCrteHostSerDO remsCrteHostSerDO = remsRequestDAO.selectHostRec(id, managerUser);
			RemsCreateHostServerDTO remsCreateHostServerDTO = remsRequestMapper
					.converRemsCrteHostSerDOToRemsCreateHostServerDTO(remsCrteHostSerDO);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCreateHostServerDTO;
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
	public String deleteHostServer(String id) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			String str = remsRequestDAO.deleteHostServer(id, managerUser);
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
	public List<RemsCreateHostServerDTO> hostServerListAll(String userId, boolean adminYn) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();

			List<RemsCreateHostServerDTO> remsCreateHostServerDTOs = remsRequestMapper
					.converRemsCrteHostSerDOListToTdmSearchRequestDTOList(remsRequestDAO.hostServerListAll(userId,
							adminYn, managerUser));
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCreateHostServerDTOs;
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
