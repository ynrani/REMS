package com.rems.service;

import java.util.List;

import com.rems.exception.ServiceException;
import com.rems.model.DTO.RemsCreateEnvInstanceDTO;

public interface RemsInstanceService
{

	public String createInstance(RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO) throws ServiceException;

	public Long instanceListCnt(String userId, boolean adminYn, RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO)
			throws ServiceException;

	public List<RemsCreateEnvInstanceDTO> instanceList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO) throws ServiceException;

	public RemsCreateEnvInstanceDTO selectInstanceRec(String id) throws ServiceException;

	public String daleteInstance(String id) throws ServiceException;

	public List<String> getHoatServerList() throws ServiceException;

	public List<RemsCreateEnvInstanceDTO> envInstanceListAll(String userId, boolean adminYn) throws ServiceException;

}
