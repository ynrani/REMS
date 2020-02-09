package com.rems.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rems.constant.MessageConstant;
import com.rems.dao.RemsBookingDAO;
import com.rems.exception.DAOException;
import com.rems.exception.ServiceException;
import com.rems.model.DO.RemsBookEnviDO;
import com.rems.model.DTO.RemsCalendarDTO;
import com.rems.model.DTO.RemsEnvBookingsDTO;
import com.rems.model.mapper.RemsBookingMapper;
import com.rems.service.RemsBookingService;

@Component
@Service("remsBookingService")
@Transactional(propagation = Propagation.REQUIRED)
public class RemsBookingServiceImpl extends TdmBaseServiceImpl implements RemsBookingService
{

	private static Logger logger = Logger.getLogger(RemsBookingServiceImpl.class);

	@Autowired
	RemsBookingDAO remsBookingDAO;

	@Autowired
	RemsBookingMapper remsBookingMapper;

	@Override
	public String createBooking(RemsEnvBookingsDTO remsEnvBookingsDTO) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SAVE_USER_DRL
				+ MessageConstant.LOG_INFO_PARAMS_NO);

		String seq = null;
		try {
			EntityManager managerUser = openUserEntityManager();
			if (!StringUtils.isNotEmpty(remsEnvBookingsDTO.getId())) {
				seq = remsBookingDAO.reqRuningRecId(managerUser);
			}

			String str = remsBookingDAO.createBooking(
					remsBookingMapper.converRemsEnvBookingsDTOToRemsBookEnviDO(remsEnvBookingsDTO, seq), managerUser);

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
	public Long bookingListCnt(String userId, boolean adminYn, RemsEnvBookingsDTO remsEnvBookingsDTO, String action)
			throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			Long cnt = remsBookingDAO.bookingListCnt(userId, adminYn, remsEnvBookingsDTO, action, managerUser);
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
	public List<RemsEnvBookingsDTO> bookingList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsEnvBookingsDTO remsEnvBookingsDTO, String action) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			// int iTotalSize = 0;
			/*
			 * List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = remsBookingMapper
			 * .converRemsBookEnviDOListToRemsEnvBookingsDTOList(remsBookingDAO.bookingList(offSet,
			 * recordsperpage, b, userId, adminYn, remsEnvBookingsDTO, action, managerUser));
			 */

			List<Object> listEnvironments = remsBookingDAO.bookingListEnvs(userId, adminYn, remsEnvBookingsDTO, action,
					managerUser);

			// Going to divide a logic as per pagination
			Map<String, Map<String, Set<String>>> mapResult = remsBookingMapper
					.convertRemsBookEnviDOListToRemsEnvBookingsDTOFinalList(remsBookingDAO.bookingList(offSet,
							recordsperpage, b, userId, adminYn, remsEnvBookingsDTO, action, managerUser), null);
			// iTotalSize = subMapSize(mapResult);
			while (mapResult.size() > 0 && mapResult.size() < recordsperpage
					&& listEnvironments.size() > subMapSize(mapResult)) {
				offSet += recordsperpage;
				List<RemsBookEnviDO> listResult = remsBookingDAO.bookingList(offSet, recordsperpage, b, userId,
						adminYn, remsEnvBookingsDTO, action, managerUser);
				if (listResult == null || listResult.isEmpty())
					break;
				mapResult = remsBookingMapper.convertRemsBookEnviDOListToRemsEnvBookingsDTOFinalList(listResult,
						mapResult);
			}

			// end pagination code
			List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = new ArrayList<RemsEnvBookingsDTO>();
			RemsEnvBookingsDTO dto = new RemsEnvBookingsDTO();
			dto.setMapFinalList(mapResult);
			dto.setPassedDates(remsEnvBookingsDTO.getPassedDates());
			remsEnvBookingsDTOs.add(dto);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_INFO_RETURN);
			return remsEnvBookingsDTOs;
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

	private int subMapSize(Map<String, Map<String, Set<String>>> mainMap) {
		int iCount = 0;
		if (mainMap != null) {
			for (String setKeys : mainMap.keySet()) {
				iCount += mainMap.get(setKeys).size();
			}
		}
		return iCount;
	}

	@Override
	public RemsEnvBookingsDTO selectBookingRec(String id) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			RemsBookEnviDO remsBookEnviDO = remsBookingDAO.selectBookingRec(id, managerUser);
			RemsEnvBookingsDTO remsEnvBookingsDTO = remsBookingMapper
					.converRemsBookEnviDOToRemsEnvBookingsDTO(remsBookEnviDO);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_SEARCH_USER_COUNT
					+ MessageConstant.LOG_INFO_RETURN);
			return remsEnvBookingsDTO;
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
	public String daleteBooking(String id) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			String str = remsBookingDAO.daleteBooking(id, managerUser);
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
	public Map<String, List<String>> getInstanceList() throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			List<Object[]> fromInstance = remsBookingDAO.getInstanceList(managerUser);
			Map<String, List<String>> instanceNames = remsBookingMapper.convertInstancetoDaopdown(fromInstance);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_DEL_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return instanceNames;
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
	public List<RemsEnvBookingsDTO> bookingListAll(String userId, boolean adminYn) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();

			List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = remsBookingMapper
					.converRemsBookEnviDOListToRemsEnvBookingsDTOList(remsBookingDAO.bookingList(userId, adminYn, "A",
							managerUser));
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_INFO_RETURN);
			return remsEnvBookingsDTOs;
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
	public List<RemsCalendarDTO> calendarListAll(String userId, boolean adminYn) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();

			List<RemsCalendarDTO> remsCalendarDTOs = remsBookingMapper
					.converRemsBookEnviDOListToRemsCalendarDTOList(remsBookingDAO.bookingList(userId, adminYn, "C",
							managerUser));
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCalendarDTOs;
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
	public boolean confirmDeclinebooking(String id, String action) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();

			boolean confirm = remsBookingDAO.confirmDeclinebooking(id, action, managerUser);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
					+ MessageConstant.LOG_INFO_RETURN);
			return confirm;
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
