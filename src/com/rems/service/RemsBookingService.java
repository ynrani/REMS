package com.rems.service;

import java.util.List;
import java.util.Map;

import com.rems.exception.ServiceException;
import com.rems.model.DTO.RemsCalendarDTO;
import com.rems.model.DTO.RemsEnvBookingsDTO;

public interface RemsBookingService
{

	public String createBooking(RemsEnvBookingsDTO remsEnvBookingsDTO) throws ServiceException;

	public Long bookingListCnt(String userId, boolean adminYn, RemsEnvBookingsDTO remsEnvBookingsDTO, String action)
			throws ServiceException;

	public List<RemsEnvBookingsDTO> bookingList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsEnvBookingsDTO remsEnvBookingsDTO, String action) throws ServiceException;

	public RemsEnvBookingsDTO selectBookingRec(String id) throws ServiceException;

	public String daleteBooking(String id) throws ServiceException;

	public Map<String, List<String>> getInstanceList() throws ServiceException;

	public List<RemsEnvBookingsDTO> bookingListAll(String userId, boolean adminYn) throws ServiceException;

	public List<RemsCalendarDTO> calendarListAll(String userId, boolean adminYn) throws ServiceException;

	public boolean confirmDeclinebooking(String id, String action) throws ServiceException;

}
