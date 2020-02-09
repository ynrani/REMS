package com.rems.model.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rems.exception.ServiceException;
import com.rems.model.DO.RemsBookEnviDO;
import com.rems.model.DTO.RemsCalendarDTO;
import com.rems.model.DTO.RemsEnvBookingsDTO;

public interface RemsBookingMapper
{

	public RemsBookEnviDO converRemsEnvBookingsDTOToRemsBookEnviDO(RemsEnvBookingsDTO remsEnvBookingsDTO, String seq)
			throws ServiceException;

	public RemsEnvBookingsDTO converRemsBookEnviDOToRemsEnvBookingsDTO(RemsBookEnviDO remsBookEnviDO)
			throws ServiceException;

	public List<RemsEnvBookingsDTO> converRemsBookEnviDOListToRemsEnvBookingsDTOList(
			List<RemsBookEnviDO> remsBookEnviDOs) throws ServiceException;

	public Map<String, List<String>> convertInstancetoDaopdown(List<Object[]> fromInstance) throws ServiceException;

	public List<RemsCalendarDTO> converRemsBookEnviDOListToRemsCalendarDTOList(List<RemsBookEnviDO> bookingList)
			throws ServiceException;

	public List<RemsCalendarDTO> converRemsBookEnviDOListToRemsEnvCalendarDTOList(List<RemsBookEnviDO> remsBookEnviDOs)
			throws ServiceException;

	Map<String, Map<String, Set<String>>> convertRemsBookEnviDOListToRemsEnvBookingsDTOFinalList(
			List<RemsBookEnviDO> remsBookEnviDOs, Map<String, Map<String, Set<String>>> mapFinalList)
			throws ServiceException;
}
