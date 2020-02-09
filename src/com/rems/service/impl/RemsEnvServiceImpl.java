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
import com.rems.dao.RemsBookingDAO;
import com.rems.exception.DAOException;
import com.rems.exception.ServiceException;
import com.rems.model.DTO.RemsEnvBookingsDTO;
import com.rems.model.mapper.RemsBookingMapper;
import com.rems.service.RemsEnvService;

@Component
@Transactional(propagation = Propagation.REQUIRED)
@Service(MessageConstant.SERVICE_ENV)
public class RemsEnvServiceImpl extends TdmBaseServiceImpl implements RemsEnvService
{

	private static Logger logger = Logger.getLogger(RemsBookingServiceImpl.class);

	@Autowired
	RemsBookingDAO remsBookingDAO;

	@Autowired
	RemsBookingMapper remsBookingMapper;

	@Override
	public List<RemsEnvBookingsDTO> bookingList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsEnvBookingsDTO remsEnvBookingsDTO, String action) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_SERVICE + MessageConstant.TDM_ADMIN_GET_ALL_USERS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();

			List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = remsBookingMapper
					.converRemsBookEnviDOListToRemsEnvBookingsDTOList(remsBookingDAO.bookingList(offSet,
							recordsperpage, b, userId, adminYn, remsEnvBookingsDTO, action, managerUser));
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
}
