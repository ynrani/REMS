package com.rems.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.rems.exception.DAOException;
import com.rems.model.DO.RemsBookEnviDO;
import com.rems.model.DTO.RemsEnvBookingsDTO;

public interface RemsBookingDAO
{

	public String reqRuningRecId(EntityManager managerUser) throws DAOException;

	public String createBooking(RemsBookEnviDO remsBookEnviDO, EntityManager managerUser) throws DAOException;

	public Long bookingListCnt(String userId, boolean adminYn, RemsEnvBookingsDTO remsEnvBookingsDTO, String action,
			EntityManager managerUser) throws DAOException;

	public List<Object> bookingListEnvs(String userId, boolean adminYn, RemsEnvBookingsDTO remsEnvBookingsDTO,
			String action, EntityManager managerUser) throws DAOException;

	public List<RemsBookEnviDO> bookingList(int offSet, int recordsperpage, boolean b, String userId, boolean adminYn,
			RemsEnvBookingsDTO remsEnvBookingsDTO, String action, EntityManager managerUser) throws DAOException;

	public RemsBookEnviDO selectBookingRec(String id, EntityManager managerUser) throws DAOException;

	public String daleteBooking(String id, EntityManager managerUser) throws DAOException;

	public List<Object[]> getInstanceList(EntityManager managerUser) throws DAOException;

	public List<RemsBookEnviDO> bookingList(String userId, boolean adminYn, String allOrConfirm,
			EntityManager managerUser) throws DAOException;

	public boolean confirmDeclinebooking(String id, String action, EntityManager managerUser) throws DAOException;

}
