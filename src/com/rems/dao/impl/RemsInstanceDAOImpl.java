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
import com.rems.dao.RemsInstanceDAO;
import com.rems.exception.DAOException;
import com.rems.model.DO.RemsCrteInstanceDO;
import com.rems.model.DTO.RemsCreateEnvInstanceDTO;

@Component("remsInstanceDAO")
public class RemsInstanceDAOImpl implements RemsInstanceDAO
{

	private static Logger logger = Logger.getLogger(RemsInstanceDAOImpl.class);

	@Override
	public String reqRuningRecId(EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_MSK_REC_ID
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			BigDecimal seqNo = (BigDecimal) managerUser.createNativeQuery("SELECT SEQ_CRTE_INSTANCE.NEXTVAL FROM DUAL")
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
	public String createInstance(RemsCrteInstanceDO remsCrteInstanceDO, EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_GET_SAVED_REQ_DTLS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			if (null != managerUser) {
				managerUser.getTransaction().begin();
				remsCrteInstanceDO = managerUser.merge(remsCrteInstanceDO);
				managerUser.getTransaction().commit();
			}
			logger.info(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_GET_SAVED_REQ_DTLS
					+ MessageConstant.LOG_INFO_RETURN);

			return String.valueOf(remsCrteInstanceDO.getId());
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
	public Long instanceListCnt(String userId, boolean adminYn, RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO,
			EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);

		try {
			StringBuffer query = new StringBuffer("SELECT count(*) FROM RemsCrteInstanceDO p Where 1=1");

			if (null != remsCreateEnvInstanceDTO) {
				if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getProjName())) {
					query.append(" AND p.projName ='" + remsCreateEnvInstanceDTO.getProjName() + "'");
				}
				if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getEnvName())) {
					query.append(" AND p.envName ='" + remsCreateEnvInstanceDTO.getEnvName() + "'");
				}

				if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getAppName())) {
					query.append(" AND p.applName ='" + remsCreateEnvInstanceDTO.getAppName() + "'");
				}

				if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getHostServerName())) {
					query.append(" AND p.hostServerName ='" + remsCreateEnvInstanceDTO.getHostServerName() + "'");
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
	public List<RemsCrteInstanceDO> instanceList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO, EntityManager managerUser)
			throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			StringBuffer query = new StringBuffer("SELECT p FROM RemsCrteInstanceDO p Where 1=1");

			if (null != remsCreateEnvInstanceDTO) {
				if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getProjName())) {
					query.append(" AND p.projName ='" + remsCreateEnvInstanceDTO.getProjName() + "'");
				}
				if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getEnvName())) {
					query.append(" AND p.envName ='" + remsCreateEnvInstanceDTO.getEnvName() + "'");
				}

				if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getAppName())) {
					query.append(" AND p.applName ='" + remsCreateEnvInstanceDTO.getAppName() + "'");
				}

				if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getHostServerName())) {
					query.append(" AND p.hostServerName ='" + remsCreateEnvInstanceDTO.getHostServerName() + "'");
				}

			}
			if (!adminYn) {
				query.append(" AND p.actionBy ='" + userId + "'");
			}

			@SuppressWarnings(AppConstant.UNCHECKED)
			List<RemsCrteInstanceDO> remsCrteInstanceDOs = managerUser.createQuery(query + "").setFirstResult(offSet)
					.setMaxResults(recordsperpage).getResultList();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCrteInstanceDOs;
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
	public RemsCrteInstanceDO selectInstanceRec(String id, EntityManager managerUser) throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			RemsCrteInstanceDO remsCrteInstanceDO = (RemsCrteInstanceDO) managerUser.createQuery(
					"SELECT p FROM RemsCrteInstanceDO p where p.id = " + Integer.parseInt(id)).getSingleResult();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCrteInstanceDO;
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
	public String daleteInstance(String id, EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_DELETE
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			managerUser.getTransaction().begin();
			Query q = managerUser.createQuery("DELETE FROM  RemsCrteInstanceDO p where p.id =:id");
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
	public List<String> getHoatServerList(EntityManager managerUser) throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			@SuppressWarnings("unchecked")
			List<String> hostServerNames = managerUser.createQuery(
					"SELECT p.hostServerName FROM RemsCrteHostSerDO p ORDER BY creActionDt DESC").getResultList();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return hostServerNames;
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
	public List<RemsCrteInstanceDO> envInstanceListAll(String userId, boolean adminYn, EntityManager managerUser)
			throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			StringBuilder query = new StringBuilder("SELECT p FROM RemsCrteInstanceDO p where 1=1 ");
			if (!adminYn) {
				query.append("AND p.actionBy = '" + userId + "' ");
			}
			@SuppressWarnings(AppConstant.UNCHECKED)
			List<RemsCrteInstanceDO> remsCrteInstanceDOs = managerUser.createQuery(query + "").getResultList();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return remsCrteInstanceDOs;
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
