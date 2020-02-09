package com.rems.model.mapper.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rems.exception.ServiceException;
import com.rems.model.DO.RemsBookEnviDO;
import com.rems.model.DTO.RemsCalendarDTO;
import com.rems.model.DTO.RemsEnvBookingsDTO;
import com.rems.model.mapper.RemsBookingMapper;
import com.rems.util.DateUtils;

@Component
@Service("remsBookingMapper")
public class RemsBookingMapperImpl implements RemsBookingMapper
{
	SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a zzz");

	@Override
	public RemsBookEnviDO converRemsEnvBookingsDTOToRemsBookEnviDO(RemsEnvBookingsDTO remsEnvBookingsDTO, String seq) {

		RemsBookEnviDO remsBookEnviDO = null;
		if (null != remsEnvBookingsDTO) {
			remsBookEnviDO = new RemsBookEnviDO();
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getId())) {
				remsBookEnviDO.setId(Integer.parseInt(remsEnvBookingsDTO.getId()));
				remsBookEnviDO.setUpActionDt(new Timestamp(new Date().getTime()));
			} else {
				remsBookEnviDO.setId(Integer.parseInt(seq));
				remsBookEnviDO.setCreActionDt(new Timestamp(new Date().getTime()));
			}

			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getCreatedDate())) {
				remsBookEnviDO.setCreActionDt(DateUtils.converStringTimeStampToSqlTimeStamp(remsEnvBookingsDTO
						.getCreatedDate()));
			}
			// 1
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getAppName())) {
				remsBookEnviDO.setApplName(remsEnvBookingsDTO.getAppName());
			}
			// 2
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEnvName())) {
				remsBookEnviDO.setEnvName(remsEnvBookingsDTO.getEnvName());
			}
			// 3
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEnvUser())) {
				remsBookEnviDO.setEnvUser(remsEnvBookingsDTO.getEnvUser());
			}
			// 4
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getBookedTo())) {
				remsBookEnviDO.setBookedTo(remsEnvBookingsDTO.getBookedTo());
			}
			// 5
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getStartDt())) {
				remsBookEnviDO.setStartDt(DateUtils.converStringToDate(remsEnvBookingsDTO.getStartDt()));
			}
			// 6
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEndDt())) {
				remsBookEnviDO.setEndDt(DateUtils.converStringToDate(remsEnvBookingsDTO.getEndDt()));
			}
			// 7
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEnvConn())) {
				remsBookEnviDO.setEnvConn(remsEnvBookingsDTO.getEnvConn());
			}
			// 8
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getEnvBookedOut())) {
				remsBookEnviDO.setEnvBookedOut(remsEnvBookingsDTO.getEnvBookedOut());
			}
			// 9
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getSwLoadInfo())) {
				remsBookEnviDO.setSwLoadInfo(remsEnvBookingsDTO.getSwLoadInfo());
			}
			// 10
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getNote())) {
				remsBookEnviDO.setNote(remsEnvBookingsDTO.getNote());
			}
			// 11
			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getActive())) {
				if ("Yes".equalsIgnoreCase(remsEnvBookingsDTO.getActive())) {
					remsBookEnviDO.setActive("Y");
				} else {
					remsBookEnviDO.setActive("N");
				}
			}

			if (StringUtils.isNotEmpty(remsEnvBookingsDTO.getUserId())) {
				remsBookEnviDO.setActionBy(remsEnvBookingsDTO.getUserId());
			}

			remsBookEnviDO.setStatus("Initiated");
		}
		return remsBookEnviDO;
	}

	@Override
	public RemsEnvBookingsDTO converRemsBookEnviDOToRemsEnvBookingsDTO(RemsBookEnviDO remsBookEnviDO)
			throws ServiceException {

		RemsEnvBookingsDTO remsEnvBookingsDTO = null;
		if (null != remsBookEnviDO) {
			remsEnvBookingsDTO = new RemsEnvBookingsDTO();
			remsEnvBookingsDTO.setCreatedDate(format.format(remsBookEnviDO.getCreActionDt()));
			if (0 < remsBookEnviDO.getId()) {
				remsEnvBookingsDTO.setId(String.valueOf(remsBookEnviDO.getId()));
			}
			// 1
			if (StringUtils.isNotEmpty(remsBookEnviDO.getApplName())) {
				remsEnvBookingsDTO.setAppName(remsBookEnviDO.getApplName());
			}
			// 2
			if (StringUtils.isNotEmpty(remsBookEnviDO.getEnvName())) {
				remsEnvBookingsDTO.setEnvName(remsBookEnviDO.getEnvName());
			}
			// 3
			if (StringUtils.isNotEmpty(remsBookEnviDO.getEnvUser())) {
				remsEnvBookingsDTO.setEnvUser(remsBookEnviDO.getEnvUser());
			}
			// 4
			if (StringUtils.isNotEmpty(remsBookEnviDO.getBookedTo())) {
				remsEnvBookingsDTO.setBookedTo(remsBookEnviDO.getBookedTo());
			}
			// 5
			if (null != remsBookEnviDO.getStartDt()) {
				remsEnvBookingsDTO.setStartDt(DateUtils.converDateToString(remsBookEnviDO.getStartDt()));
			}
			// 6
			if (null != remsBookEnviDO.getEndDt()) {
				remsEnvBookingsDTO.setEndDt(DateUtils.converDateToString(remsBookEnviDO.getEndDt()));
			}
			// 7
			if (StringUtils.isNotEmpty(remsBookEnviDO.getEnvConn())) {
				remsEnvBookingsDTO.setEnvConn(remsBookEnviDO.getEnvConn());
			}
			// 8
			if (StringUtils.isNotEmpty(remsBookEnviDO.getEnvBookedOut())) {
				remsEnvBookingsDTO.setEnvBookedOut(remsBookEnviDO.getEnvBookedOut());
			}
			// 9
			if (StringUtils.isNotEmpty(remsBookEnviDO.getSwLoadInfo())) {
				remsEnvBookingsDTO.setSwLoadInfo(remsBookEnviDO.getSwLoadInfo());
			}
			// 10
			if (StringUtils.isNotEmpty(remsBookEnviDO.getNote())) {
				remsEnvBookingsDTO.setNote(remsBookEnviDO.getNote());
			}
			// 11
			if (StringUtils.isNotEmpty(remsBookEnviDO.getActive())) {
				if ("Y".equalsIgnoreCase(remsBookEnviDO.getActive())) {
					remsEnvBookingsDTO.setActive("Yes");
				} else {
					remsEnvBookingsDTO.setActive("No");
				}
			}
			// 12
			if (StringUtils.isNotEmpty(remsBookEnviDO.getActionBy())) {
				remsEnvBookingsDTO.setUserId(remsBookEnviDO.getActionBy());
			}
			// 13
			if (null != remsBookEnviDO.getUpActionDt()) {
				remsEnvBookingsDTO.setUpActionDt(format.format(remsBookEnviDO.getUpActionDt()));
			}

			if (null != remsBookEnviDO.getStatus()) {
				remsEnvBookingsDTO.setStatus(remsBookEnviDO.getStatus());
			}
		}
		return remsEnvBookingsDTO;
	}

	@Override
	public List<RemsEnvBookingsDTO> converRemsBookEnviDOListToRemsEnvBookingsDTOList(
			List<RemsBookEnviDO> remsBookEnviDOs) throws ServiceException {
		List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = null;
		if (null != remsBookEnviDOs && 0 < remsBookEnviDOs.size()) {
			remsEnvBookingsDTOs = new ArrayList<RemsEnvBookingsDTO>();
			for (RemsBookEnviDO remsBookEnviDO : remsBookEnviDOs) {
				remsEnvBookingsDTOs.add(converRemsBookEnviDOToRemsEnvBookingsDTO(remsBookEnviDO));
			}
		}
		return remsEnvBookingsDTOs;
	}

	@Override
	public Map<String, List<String>> convertInstancetoDaopdown(List<Object[]> fromInstance) throws ServiceException {

		Map<String, List<String>> finalDrop;
		List<String> appName = null;
		List<String> envName = null;
		if (null != fromInstance && 0 < fromInstance.size()) {
			appName = new ArrayList<String>();
			envName = new ArrayList<String>();

			for (Object[] objects : fromInstance) {

				if (null != objects[0]) {
					appName.add(String.valueOf(objects[0]));
				}

				if (null != objects[1]) {
					envName.add(String.valueOf(objects[1]));
				}
			}
		}
		finalDrop = new HashMap<String, List<String>>();
		finalDrop.put("A", appName);
		finalDrop.put("E", envName);

		return finalDrop;
	}

	@Override
	public List<RemsCalendarDTO> converRemsBookEnviDOListToRemsCalendarDTOList(List<RemsBookEnviDO> remsBookEnviDOs)
			throws ServiceException {
		List<RemsCalendarDTO> remsCalendarDTOs = null;
		RemsCalendarDTO remsCalendarDTO = null;
		if (null != remsBookEnviDOs && 0 < remsBookEnviDOs.size()) {
			remsCalendarDTOs = new ArrayList<RemsCalendarDTO>();
			for (RemsBookEnviDO remsBookEnviDO : remsBookEnviDOs) {
				remsCalendarDTO = new RemsCalendarDTO();

				// 1
				if (0 < remsBookEnviDO.getId()) {
					remsCalendarDTO.setId(String.valueOf(remsBookEnviDO.getId()));
				}
				// 2
				if (StringUtils.isNotEmpty(remsBookEnviDO.getApplName())) {
					remsCalendarDTO.setTitle(remsBookEnviDO.getApplName());
				}
				// 3
				if (null != remsBookEnviDO.getStartDt()) {
					remsCalendarDTO.setStart(DateUtils.converDateToStringToCal(remsBookEnviDO.getStartDt()));
				}
				// 4
				if (null != remsBookEnviDO.getEndDt()) {
					remsCalendarDTO.setEnd(DateUtils.converDateToStringToCal(remsBookEnviDO.getEndDt()));
				}
				// 5
				remsCalendarDTO.setUrl("./remsEnvBooking?id=" + remsBookEnviDO.getId());
				// 6
				remsCalendarDTO.setAllDay(true);

				remsCalendarDTOs.add(remsCalendarDTO);
			}
		}
		return remsCalendarDTOs;
	}

	@Override
	public List<RemsCalendarDTO> converRemsBookEnviDOListToRemsEnvCalendarDTOList(List<RemsBookEnviDO> remsBookEnviDOs)
			throws ServiceException {
		List<RemsCalendarDTO> remsEnvCalendarDTOs = null;
		RemsCalendarDTO remsCalendarDTO = null;
		if (null != remsBookEnviDOs && 0 < remsBookEnviDOs.size()) {
			remsEnvCalendarDTOs = new ArrayList<RemsCalendarDTO>();
			for (RemsBookEnviDO remsBookEnviDO : remsBookEnviDOs) {
				remsCalendarDTO = new RemsCalendarDTO();

				// 1
				if (0 < remsBookEnviDO.getId()) {
					remsCalendarDTO.setId(String.valueOf(remsBookEnviDO.getId()));
				}
				// 2
				if (StringUtils.isNotEmpty(remsBookEnviDO.getApplName())) {
					remsCalendarDTO.setTitle(remsBookEnviDO.getApplName());
				}
				// 3
				if (null != remsBookEnviDO.getStartDt()) {
					remsCalendarDTO.setStart(DateUtils.converDateToStringToCal(remsBookEnviDO.getStartDt()));
				}
				// 4
				if (null != remsBookEnviDO.getEndDt()) {
					remsCalendarDTO.setEnd(DateUtils.converDateToStringToCal(remsBookEnviDO.getEndDt()));
				}
				// 5
				/*
				 * remsCalendarDTO.setUrl("./remsEnvBooking?id=" + remsBookEnviDO.getId()); // 6
				 * remsCalendarDTO.setAllDay(true);
				 * 
				 * remsCalendarDTOs.add(remsCalendarDTO);
				 */
			}
		}
		return remsEnvCalendarDTOs;
	}

	@Override
	public Map<String, Map<String, Set<String>>> convertRemsBookEnviDOListToRemsEnvBookingsDTOFinalList(
			List<RemsBookEnviDO> remsBookEnviDOs, Map<String, Map<String, Set<String>>> mapFinalList)
			throws ServiceException {

		if (mapFinalList == null) {
			mapFinalList = new HashMap<String, Map<String, Set<String>>>();
		}
		if (null != remsBookEnviDOs && 0 < remsBookEnviDOs.size()) {
			for (RemsBookEnviDO remsBookEnviDO : remsBookEnviDOs) {
				if (!mapFinalList.containsKey(remsBookEnviDO.getApplName())) {
					Set<String> listDates = convertedDates(remsBookEnviDO.getStartDt(), remsBookEnviDO.getEndDt());
					/*
					 * if (null != remsBookEnviDO.getEndDt() && null != remsBookEnviDO.getStartDt())
					 * { int diff = (int) TimeUnit.DAYS.convert(remsBookEnviDO.getEndDt().getTime()
					 * - remsBookEnviDO.getStartDt().getTime(), TimeUnit.MILLISECONDS); for (int i =
					 * 0; i <= diff; i++) { Calendar cal = Calendar.getInstance();
					 * cal.setTime(remsBookEnviDO.getEndDt()); cal.add(Calendar.DATE, i);
					 * listDates.add(DateUtils.converDateToString(cal.getTime())); } }
					 */
					Map<String, Set<String>> subMap = new HashMap<String, Set<String>>();
					subMap.put(remsBookEnviDO.getEnvName(), listDates);
					// Map<String, Map<String, Set<String>>> mainMap = new HashMap<String,
					// Map<String, Set<String>>>();
					mapFinalList.put(remsBookEnviDO.getApplName(), subMap);
				} else if (mapFinalList.containsKey(remsBookEnviDO.getApplName())) {
					if (mapFinalList.get(remsBookEnviDO.getApplName()).containsKey(remsBookEnviDO.getEnvName())) {

						Set<String> listDates = mapFinalList.get(remsBookEnviDO.getApplName()).get(
								remsBookEnviDO.getEnvName());

						listDates.addAll(convertedDates(remsBookEnviDO.getStartDt(), remsBookEnviDO.getEndDt()));
						/*
						 * if (null != remsBookEnviDO.getEndDt() && null !=
						 * remsBookEnviDO.getStartDt()) { int diff = (int)
						 * TimeUnit.DAYS.convert(remsBookEnviDO.getEndDt().getTime() -
						 * remsBookEnviDO.getStartDt().getTime(), TimeUnit.MILLISECONDS); for (int i
						 * = 0; i <= diff; i++) { Calendar cal = Calendar.getInstance();
						 * cal.setTime(remsBookEnviDO.getEndDt()); cal.add(Calendar.DATE, i);
						 * listDates.add(DateUtils.converDateToString(cal.getTime())); } }
						 */
					} else {
						Map<String, Set<String>> subMap = mapFinalList.get(remsBookEnviDO.getApplName());
						// parsing the dates
						Set<String> listDates = convertedDates(remsBookEnviDO.getStartDt(), remsBookEnviDO.getEndDt());
						/*
						 * if (null != remsBookEnviDO.getEndDt() && null !=
						 * remsBookEnviDO.getStartDt()) { int diff = (int)
						 * TimeUnit.DAYS.convert(remsBookEnviDO.getEndDt().getTime() -
						 * remsBookEnviDO.getStartDt().getTime(), TimeUnit.MILLISECONDS); for (int i
						 * = 0; i <= diff; i++) { Calendar cal = Calendar.getInstance();
						 * cal.setTime(remsBookEnviDO.getEndDt()); cal.add(Calendar.DATE, i);
						 * listDates.add(DateUtils.converDateToString(cal.getTime())); } }
						 */
						// end parsing dates
						subMap.put(remsBookEnviDO.getEnvName(), listDates);
					}
					// remsEnvBookingsDTOs.add(converRemsBookEnviDOToRemsEnvBookingsDTO(remsBookEnviDO));
				}
			}
		}
		return mapFinalList;

	}

	private Set<String> convertedDates(Date startDate, Date endDate) throws ServiceException {
		Set<String> listResult = new HashSet<String>();
		if (null != startDate && null != endDate) {
			int diff = (int) TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);
			for (int i = 0; i <= diff; i++) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				cal.add(Calendar.DATE, i);
				listResult.add(DateUtils.converDateToString(cal.getTime()));
			}
		}
		return listResult;
	}
}
