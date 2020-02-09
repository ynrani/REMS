package com.rems.service;

import java.util.List;

import com.rems.exception.ServiceException;
import com.rems.model.DTO.RemsCreateHostServerDTO;

public interface RemsRequestService
{

	public String createHostServer(RemsCreateHostServerDTO remsCreateHostServerDTO) throws ServiceException;

	public Long hostServerListCnt(String userId, boolean adminYn, RemsCreateHostServerDTO remsCreateHostServerDTO)
			throws ServiceException;

	public List<RemsCreateHostServerDTO> hostServerList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsCreateHostServerDTO remsCreateHostServerDTO) throws ServiceException;

	public RemsCreateHostServerDTO selectHostRec(String id) throws ServiceException;

	public String deleteHostServer(String id) throws ServiceException;

	public List<RemsCreateHostServerDTO> hostServerListAll(String userId, boolean adminYn) throws ServiceException;

}
