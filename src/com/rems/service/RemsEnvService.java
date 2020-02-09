package com.rems.service;

import java.util.List;

import com.rems.exception.ServiceException;
import com.rems.model.DTO.RemsEnvBookingsDTO;

public interface RemsEnvService
{

	public List<RemsEnvBookingsDTO> bookingList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsEnvBookingsDTO remsEnvBookingsDTO, String action) throws ServiceException;
}
