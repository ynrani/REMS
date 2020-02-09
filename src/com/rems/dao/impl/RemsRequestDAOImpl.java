package com.rems.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rems.constant.AppConstant;
import com.rems.constant.MessageConstant;
import com.rems.dao.RemsRequestDAO;
import com.rems.exception.DAOException;
import com.rems.model.DO.RemsCrteHostSerDO;
import com.rems.model.DTO.RemsCreateHostServerDTO;

@Component("remsRequestDAO")
public class RemsRequestDAOImpl implements RemsRequestDAO
{

	private static Logger logger = Logger.getLogger(RemsRequestDAOImpl.class);

	@Override
	public String reqRuningRecId(EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_MSK_REC_ID
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			BigDecimal seqNo = (BigDecimal) managerUser.createNativeQuery("SELECT SEQ_CRTE_HOST.NEXTVAL FROM DUAL")
					.getSingleResult();
			logger.info(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_MSK_REC_ID
					+ MessageConstant.LOG_INFO_RETURN);
			return String.valueOf(seqNo);
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_MSK_REC_ID
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION, illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_MSK_REC_ID
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_MSK_REC_ID
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_MSK_REC_ID
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public String createHostServer(RemsCrteHostSerDO remsCrteHostSerDO, EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_GET_SAVED_REQ_DTLS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			if (null != managerUser) {
				managerUser.getTransaction().begin();
				remsCrteHostSerDO = managerUser.merge(remsCrteHostSerDO);
				managerUser.getTransaction().commit();
			}
			logger.info(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_GET_SAVED_REQ_DTLS
					+ MessageConstant.LOG_INFO_RETURN);

			return String.valueOf(remsCrteHostSerDO.getId());
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_GET_SAVED_REQ_DTLS
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION, illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_GET_SAVED_REQ_DTLS
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_GET_SAVED_REQ_DTLS
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_GET_SAVED_REQ_DTLS
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public Long hostServerListCnt(String userId, boolean adminYn, RemsCreateHostServerDTO remsCreateHostServerDTO,
			EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);

		try {
			StringBuffer query = new StringBuffer("SELECT count(*) FROM RemsCrteHostSerDO p Where 1=1");
			if (null != remsCreateHostServerDTO) {
				if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getHostServerName())) {
					query.append(" AND p.hostServerName ='" + remsCreateHostServerDTO.getHostServerName() + "'");
				}
				if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getServerPhyLoc())) {
					query.append(" AND p.serverPhyLoc ='" + remsCreateHostServerDTO.getServerPhyLoc() + "'");
				}

				if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getIp())) {
					query.append(" AND p.ip ='" + remsCreateHostServerDTO.getIp() + "'");
				}

			}
			if (!adminYn) {
				query.append(" AND p.actionBy ='" + userId + "'");
			}

			Long count = (Long) managerUser.createQuery(query + "").getSingleResult();
			return count;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION, illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<RemsCrteHostSerDO> hostServerList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsCreateHostServerDTO remsCreateHostServerDTO, EntityManager managerUser)
			throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			StringBuffer query = new StringBuffer("SELECT p FROM RemsCrteHostSerDO p Where 1=1");

			if (null != remsCreateHostServerDTO) {
				if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getHostServerName())) {
					query.append(" AND p.hostServerName ='" + remsCreateHostServerDTO.getHostServerName() + "'");
				}
				if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getServerPhyLoc())) {
					query.append(" AND p.serverPhyLoc ='" + remsCreateHostServerDTO.getServerPhyLoc() + "'");
				}

				if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getIp())) {
					query.append(" AND p.ip ='" + remsCreateHostServerDTO.getIp() + "'");
				}

			}

			if (!adminYn) {
				query.append(" AND p.actionBy ='" + userId + "'");
			}

			@SuppressWarnings(AppConstant.UNCHECKED)
			List<RemsCrteHostSerDO> remsCrteHostSerDOs = managerUser.createQuery(query + "").setFirstResult(offSet)
					.setMaxResults(recordsperpage).getResultList();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCrteHostSerDOs;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION, illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}

	}

	@Override
	public RemsCrteHostSerDO selectHostRec(String id, EntityManager managerUser) throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			RemsCrteHostSerDO remsCrteHostSerDO = (RemsCrteHostSerDO) managerUser.createQuery(
					"SELECT p FROM RemsCrteHostSerDO p where p.id = " + Integer.parseInt(id)).getSingleResult();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCrteHostSerDO;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION, illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}

	}

	@Override
	public String deleteHostServer(String id, EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_DELETE
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			managerUser.getTransaction().begin();
			Query q = managerUser.createQuery("DELETE FROM  RemsCrteHostSerDO p where p.id =:id");
			q.setParameter(AppConstant.ID, Long.parseLong(id));
			q.executeUpdate();
			managerUser.getTransaction().commit();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_DELETE
					+ MessageConstant.LOG_INFO_RETURN);
			return id;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_DELETE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION, illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_DELETE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_DELETE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_DELETE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<RemsCrteHostSerDO> hostServerListAll(String userId, boolean adminYn, EntityManager managerUser)
			throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			StringBuilder query = new StringBuilder("SELECT p FROM RemsCrteHostSerDO p where 1=1 ");
			if (!adminYn) {
				query.append("AND p.actionBy = '" + userId + "' ");
			}
			@SuppressWarnings(AppConstant.UNCHECKED)
			List<RemsCrteHostSerDO> remsCrteHostSerDOs = managerUser.createQuery(query + "").getResultList();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCrteHostSerDOs;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION, illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}

	}

}
