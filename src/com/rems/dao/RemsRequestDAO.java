package com.rems.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.rems.exception.DAOException;
import com.rems.model.DO.RemsCrteHostSerDO;
import com.rems.model.DTO.RemsCreateHostServerDTO;

public interface RemsRequestDAO
{

	public String reqRuningRecId(EntityManager managerUser) throws DAOException;

	public String createHostServer(RemsCrteHostSerDO converRemsCreateHostServerDTOToRemsCrteHostSerDO,
			EntityManager managerUser) throws DAOException;

	public Long hostServerListCnt(String userId, boolean adminYn, RemsCreateHostServerDTO remsCreateHostServerDTO,
			EntityManager managerUser) throws DAOException;

	public List<RemsCrteHostSerDO> hostServerList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsCreateHostServerDTO remsCreateHostServerDTO, EntityManager managerUser)
			throws DAOException;

	public RemsCrteHostSerDO selectHostRec(String id, EntityManager managerUser) throws DAOException;

	public String deleteHostServer(String id, EntityManager managerUser) throws DAOException;

	public List<RemsCrteHostSerDO> hostServerListAll(String userId, boolean adminYn, EntityManager managerUser)
			throws DAOException;

}
