package com.rems.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.rems.exception.DAOException;
import com.rems.model.DO.RemsCrteInstanceDO;
import com.rems.model.DTO.RemsCreateEnvInstanceDTO;

public interface RemsInstanceDAO
{

	public String reqRuningRecId(EntityManager managerUser) throws DAOException;

	public String createInstance(RemsCrteInstanceDO remsCrteInstanceDO, EntityManager managerUser) throws DAOException;

	public Long instanceListCnt(String userId, boolean adminYn, RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO,
			EntityManager managerUser) throws DAOException;

	public List<RemsCrteInstanceDO> instanceList(int offSet, int recordsperpage, boolean b, String userId,
			boolean adminYn, RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO, EntityManager managerUser)
			throws DAOException;

	public RemsCrteInstanceDO selectInstanceRec(String id, EntityManager managerUser) throws DAOException;

	public String daleteInstance(String id, EntityManager managerUser) throws DAOException;

	public List<String> getHoatServerList(EntityManager managerUser) throws DAOException;

	public List<RemsCrteInstanceDO> envInstanceListAll(String userId, boolean adminYn, EntityManager managerUser)
			throws DAOException;

}
