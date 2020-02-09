package com.rems.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rems.constant.AppConstant;
import com.rems.constant.MessageConstant;
import com.rems.dao.RemsBookingDAO;
import com.rems.exception.DAOException;
import com.rems.model.DO.RemsBookEnviDO;
import com.rems.model.DTO.RemsEnvBookingsDTO;

@Component("remsBookingDAO")
public class RemsBookingDAOImpl implements RemsBookingDAO
{

	private static Logger logger = Logger.getLogger(RemsBookingDAOImpl.class);

	@Override
	public String reqRuningRecId(EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_MSK_REC_ID
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			BigDecimal seqNo = (BigDecimal) managerUser.createNativeQuery("SELECT SEQ_BOOK_ENVI.NEXTVAL FROM DUAL")
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
	public String createBooking(RemsBookEnviDO remsBookEnviDO, EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_GET_SAVED_REQ_DTLS
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			if (null != managerUser) {
				managerUser.getTransaction().begin();
				remsBookEnviDO = managerUser.merge(remsBookEnviDO);
				managerUser.getTransaction().commit();
			}
			logger.info(MessageConstant.TDM_DT_MSK_DAO + MessageConstant.TDM_DT_MSK_DAO_GET_SAVED_REQ_DTLS
					+ MessageConstant.LOG_INFO_RETURN);

			return String.valueOf(remsBookEnviDO.getId());
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
	public List<Object> bookingListEnvs(String userId, boolean adminYn, RemsEnvBookingsDTO remsEnvBookingsDTO,
			String action, EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);

		try {
			List listResult = new ArrayList();
			if (adminYn != true && null != remsEnvBookingsDTO) {
				StringTokenizer appName = new StringTokenizer(remsEnvBookingsDTO.getAppName(), ",");
				Set<String> set = new HashSet<String>();
				List<String> list = new ArrayList<String>();
				while (appName.hasMoreTokens()) {
					set.add(appName.nextToken());
				}
				Iterator<String> it = set.iterator();
				while (it.hasNext()) {
					String appName1 = it.next();
					list.add(appName1);

					StringBuffer query = new StringBuffer("SELECT p.envName FROM RemsBookEnviDO p Where 1=1");

					if (null != remsEnvBookingsDTO) {
						if (StringUtils.isNotEmpty(appName1)) {
							query.append(" AND p.applName IN ('" + appName1 + "')");
						}

						/*
						 * if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEnvName())) {
						 * query.append(" AND p.envName ='" + remsEnvBookingsDTO.getEnvName() +
						 * "'"); } if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getStartDt())) {
						 * query.append("AND TRUNC(p.startDt) = To_date('"
						 * ).append(remsEnvBookingsDTO.getStartDt()) .append("', 'MM/DD/YYYY') "); }
						 * 
						 * if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEndDt())) {
						 * query.append("AND TRUNC(p.endDt) = To_date('"
						 * ).append(remsEnvBookingsDTO.getEndDt()) .append("', 'MM/DD/YYYY') "); }
						 */
						/*
						 * else if (adminYn == false) {
						 * 
						 * @SuppressWarnings(AppConstant.UNCHECKED) List<RemsBookEnviDO>
						 * remsBookEnviDOs = managerUser.createQuery(query + "").getResultList();
						 * for(int i=0; i<=remsBookEnviDOs.size(); i++) { String appName =
						 * remsBookEnviDOs.getApplName(); } }
						 */
						if (StringUtils.isNotEmpty(action)) {
							if (action.contains("','")) {
								query.append(" AND p.status NOT IN ('" + action + "')");
							} else {
								query.append(" AND p.status ='" + action + "'");
							}
						}
						query.append(" GROUP BY p.envName");
					}

					/*
					 * if (!adminYn) { query.append(" AND p.actionBy ='" + userId + "'"); }
					 */
					listResult.addAll(managerUser.createQuery(query + "").getResultList());
				}
				String lists = StringUtils.join(list, "','");
				// lists = "'" + lists + "'";
				remsEnvBookingsDTO.setAppName(lists);
			}

			/*
			 * StringBuffer query = new
			 * StringBuffer("SELECT p.envName FROM RemsBookEnviDO p Where 1=1");
			 * 
			 * if (null != remsEnvBookingsDTO) { if
			 * (StringUtils.isNotEmpty(remsEnvBookingsDTO.getAppName())) {
			 * query.append(" AND p.applName IN ('" + remsEnvBookingsDTO.getAppName() + "')"); }
			 * 
			 * 
			 * if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEnvName())) {
			 * query.append(" AND p.envName ='" + remsEnvBookingsDTO.getEnvName() + "'"); } if
			 * (StringUtils.isNotEmpty(remsEnvBookingsDTO.getStartDt())) {
			 * query.append("AND TRUNC(p.startDt) = To_date('"
			 * ).append(remsEnvBookingsDTO.getStartDt()) .append("', 'MM/DD/YYYY') "); }
			 * 
			 * if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEndDt())) {
			 * query.append("AND TRUNC(p.endDt) = To_date('" ).append(remsEnvBookingsDTO.getEndDt())
			 * .append("', 'MM/DD/YYYY') "); }
			 * 
			 * 
			 * else if (adminYn == false) {
			 * 
			 * @SuppressWarnings(AppConstant.UNCHECKED) List<RemsBookEnviDO> remsBookEnviDOs =
			 * managerUser.createQuery(query + "").getResultList(); for(int i=0;
			 * i<=remsBookEnviDOs.size(); i++) { String appName = remsBookEnviDOs.getApplName(); } }
			 * 
			 * if (StringUtils.isNotEmpty(action)) { if (action.contains("','")) {
			 * query.append(" AND p.status NOT IN ('" + action + "')"); } else {
			 * query.append(" AND p.status ='" + action + "'"); } }
			 * query.append(" GROUP BY p.envName"); }
			 * 
			 * 
			 * if (!adminYn) { query.append(" AND p.actionBy ='" + userId + "'"); }
			 * 
			 * List listResult = managerUser.createQuery(query + "").getResultList();
			 */
			return listResult;
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
	public Long bookingListCnt(String userId, boolean adminYn, RemsEnvBookingsDTO remsEnvBookingsDTO, String action,
			EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);

		return (long) bookingListEnvs(userId, adminYn, remsEnvBookingsDTO, action, managerUser).size();

		/*
		 * try {
		 * 
		 * if (adminYn != true && null != remsEnvBookingsDTO) { StringTokenizer appName = new
		 * StringTokenizer(remsEnvBookingsDTO.getAppName(), ","); Set<String> set = new
		 * HashSet<String>(); List<String> list = new ArrayList<String>(); while
		 * (appName.hasMoreTokens()) { set.add(appName.nextToken()); } Iterator<String> it =
		 * set.iterator(); while (it.hasNext()) { list.add(it.next()); } String lists =
		 * StringUtils.join(list, "','"); // lists = "'" + lists + "'";
		 * remsEnvBookingsDTO.setAppName(lists); }
		 * 
		 * StringBuffer query = new
		 * StringBuffer("SELECT p.envName FROM RemsBookEnviDO p Where 1=1");
		 * 
		 * if (null != remsEnvBookingsDTO) { if
		 * (StringUtils.isNotEmpty(remsEnvBookingsDTO.getAppName())) {
		 * query.append(" AND p.applName IN ('" + remsEnvBookingsDTO.getAppName() + "')"); }
		 * 
		 * 
		 * if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEnvName())) {
		 * query.append(" AND p.envName ='" + remsEnvBookingsDTO.getEnvName() + "'"); } if
		 * (StringUtils.isNotEmpty(remsEnvBookingsDTO.getStartDt())) {
		 * query.append("AND TRUNC(p.startDt) = To_date('" ).append(remsEnvBookingsDTO.getStartDt())
		 * .append("', 'MM/DD/YYYY') "); }
		 * 
		 * if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEndDt())) {
		 * query.append("AND TRUNC(p.endDt) = To_date('" ).append(remsEnvBookingsDTO.getEndDt())
		 * .append("', 'MM/DD/YYYY') "); }
		 * 
		 * 
		 * else if (adminYn == false) {
		 * 
		 * @SuppressWarnings(AppConstant.UNCHECKED) List<RemsBookEnviDO> remsBookEnviDOs =
		 * managerUser.createQuery(query + "").getResultList(); for(int i=0;
		 * i<=remsBookEnviDOs.size(); i++) { String appName = remsBookEnviDOs.getApplName(); } }
		 * 
		 * if (StringUtils.isNotEmpty(action)) { if (action.contains("','")) {
		 * query.append(" AND p.status NOT IN ('" + action + "')"); } else {
		 * query.append(" AND p.status ='" + action + "'"); } } query.append(" GROUP BY p.envName");
		 * }
		 * 
		 * 
		 * if (!adminYn) { query.append(" AND p.actionBy ='" + userId + "'"); }
		 * 
		 * List listResult = managerUser.createQuery(query + "").getResultList(); Long count = 0L;
		 * if (listResult != null) count = (long) listResult.size(); return count; } catch
		 * (IllegalStateException illegalStateEx) { logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
		 * + MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT + MessageConstant.LOG_ERROR_EXCEPTION);
		 * throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
		 * illegalStateEx); } catch (IllegalArgumentException illegalArgEx) {
		 * logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL +
		 * MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT + MessageConstant.LOG_ERROR_EXCEPTION);
		 * throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx); } catch
		 * (NullPointerException nullPointerEx) { logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL +
		 * MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT + MessageConstant.LOG_ERROR_EXCEPTION);
		 * throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx); } catch
		 * (Exception otherEx) { logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL +
		 * MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT + MessageConstant.LOG_ERROR_EXCEPTION);
		 * throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx); }
		 */
	}

	@Override
	public List<RemsBookEnviDO> bookingList(int offSet, int recordsperpage, boolean b, String userId, boolean adminYn,
			RemsEnvBookingsDTO remsEnvBookingsDTO, String action, EntityManager managerUser) throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			if (adminYn != true && null != remsEnvBookingsDTO) {

				StringTokenizer appName = new StringTokenizer(remsEnvBookingsDTO.getAppName(), ",");
				Set<String> set = new HashSet<String>();
				List<String> list = new ArrayList<String>();
				while (appName.hasMoreTokens()) {
					set.add(appName.nextToken());
				}
				Iterator<String> it = set.iterator();
				while (it.hasNext()) {
					list.add(it.next());
				}
				String lists = StringUtils.join(list, "','");
				lists = "'" + lists + "'";
				remsEnvBookingsDTO.setAppName(lists);
			}

			StringBuffer query = new StringBuffer("SELECT p FROM RemsBookEnviDO p Where 1=1");

			if (null != remsEnvBookingsDTO) {
				if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getAppName())) {
					query.append(" AND p.applName IN(" + remsEnvBookingsDTO.getAppName() + ")");
				}
				if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEnvName())) {
					query.append(" AND p.envName ='" + remsEnvBookingsDTO.getEnvName() + "'");
				}

				/*
				 * if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getStartDt())) {
				 * query.append(" AND TRUNC(p.startDt) >= To_date('"
				 * ).append(remsEnvBookingsDTO.getStartDt()) .append("', 'MM/DD/YYYY') "); }
				 * 
				 * if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEndDt())) {
				 * query.append(" AND TRUNC(p.endDt) <= To_date('"
				 * ).append(remsEnvBookingsDTO.getEndDt()) .append("', 'MM/DD/YYYY') "); }
				 */
			}

			if (StringUtils.isNotEmpty(action)) {
				if (action.contains("','")) {
					query.append(" AND p.status NOT IN ('" + action + "')");
				} else {
					query.append(" AND p.status ='" + action + "'");
				}
			}

			/*
			 * if (!adminYn) { query.append(" AND p.actionBy ='" + userId + "'"); }
			 */

			@SuppressWarnings(AppConstant.UNCHECKED)
			List<RemsBookEnviDO> remsBookEnviDOs = managerUser.createQuery(query + "").setFirstResult(offSet)
					.setMaxResults(recordsperpage).getResultList();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return remsBookEnviDOs;
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
	public RemsBookEnviDO selectBookingRec(String id, EntityManager managerUser) throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			RemsBookEnviDO remsBookEnviDO = (RemsBookEnviDO) managerUser.createQuery(
					"SELECT p FROM RemsBookEnviDO p where p.id = " + Integer.parseInt(id)).getSingleResult();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return remsBookEnviDO;
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
	public String daleteBooking(String id, EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_DELETE
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			managerUser.getTransaction().begin();
			Query q = managerUser.createQuery("DELETE FROM  RemsBookEnviDO p where p.id =:id");
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
	public List<Object[]> getInstanceList(EntityManager managerUser) throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			@SuppressWarnings("unchecked")
			List<Object[]> hostServerNames = managerUser.createQuery(
					"SELECT p.applName,p.envName FROM RemsCrteInstanceDO p ORDER BY creActionDt DESC").getResultList();
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
	public List<RemsBookEnviDO> bookingList(String userId, boolean adminYn, String allOrConfirm,
			EntityManager managerUser) throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			StringBuilder query = new StringBuilder("SELECT p FROM RemsBookEnviDO p where 1=1 ");
			if (!adminYn) {
				query.append("AND p.actionBy = '" + userId + "' ");
			}

			if (StringUtils.isNotEmpty(allOrConfirm) && "C".equalsIgnoreCase(allOrConfirm)) {
				query.append(" AND p.status ='Confirm'");
			}

			@SuppressWarnings(AppConstant.UNCHECKED)
			List<RemsBookEnviDO> remsBookEnviDOs = managerUser.createQuery(query + "").getResultList();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return remsBookEnviDOs;
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
	public boolean confirmDeclinebooking(String id, String action, EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			RemsBookEnviDO remsBookEnviDO = managerUser.find(RemsBookEnviDO.class, Long.parseLong(id));
			if (action.equalsIgnoreCase("C")) {
				remsBookEnviDO.setStatus("Confirm");
			} else if (action.equalsIgnoreCase("D")) {
				remsBookEnviDO.setStatus("Decline");
			}
			managerUser.getTransaction().begin();
			remsBookEnviDO = managerUser.merge(remsBookEnviDO);
			managerUser.getTransaction().commit();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return true;
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

	/*
	 * @Override public List<RemsEnvBookingsDTO> bookingList(String appName, RemsEnvBookingsDTO
	 * remsEnvBookingsDTO, String action) throws DAOException {
	 * logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL +
	 * MessageConstant.LOG_INFO_PARAMS_NO); try {
	 * 
	 * StringBuffer query = new StringBuffer("SELECT p FROM RemsBookEnviDO p Where 1=1");
	 * 
	 * if (null != remsEnvBookingsDTO) { if
	 * (StringUtils.isNotEmpty(remsEnvBookingsDTO.getAppName())) { query.append(" AND p.applName ='"
	 * + remsEnvBookingsDTO.getAppName() + "'"); }
	 * 
	 * }
	 * 
	 * if (StringUtils.isNotEmpty(action)) { if (action.contains("','")) {
	 * query.append(" AND p.status NOT IN ('" + action + "')"); } else {
	 * query.append(" AND p.status ='" + action + "'"); } }
	 * 
	 * @SuppressWarnings(AppConstant.UNCHECKED) List<RemsBookEnviDO> remsBookEnviDOs =
	 * managerUser.createQuery(query + "").setFirstResult(offSet)
	 * .setMaxResults(recordsperpage).getResultList();
	 * 
	 * } catch (IllegalStateException illegalStateEx) {
	 * logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_GET_ALL +
	 * MessageConstant.LOG_ERROR_EXCEPTION); throw new
	 * DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION, illegalStateEx); }
	 * return null;
	 * 
	 * }
	 */
}
