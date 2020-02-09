package com.rems.model.mapper.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rems.model.DO.RemsCrteHostSerDO;
import com.rems.model.DTO.RemsCreateHostServerDTO;
import com.rems.model.mapper.RemsRequestMapper;
import com.rems.util.DateUtils;

@Component
@Service("remsRequestMapper")
public class RemsRequestMapperImpl implements RemsRequestMapper
{
	SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a zzz");

	@Override
	public RemsCrteHostSerDO converRemsCreateHostServerDTOToRemsCrteHostSerDO(
			RemsCreateHostServerDTO remsCreateHostServerDTO, String seq) {

		RemsCrteHostSerDO remsCrteHostSerDO = null;
		if (null != remsCreateHostServerDTO) {
			remsCrteHostSerDO = new RemsCrteHostSerDO();
			if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getId())) {
				remsCrteHostSerDO.setId(Integer.parseInt(remsCreateHostServerDTO.getId()));
				remsCrteHostSerDO.setUpActionDt(new Timestamp(new Date().getTime()));
			} else {
				remsCrteHostSerDO.setId(Integer.parseInt(seq));
				remsCrteHostSerDO.setCreActionDt(new Timestamp(new Date().getTime()));
			}
			if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getCreatedDate())) {
				remsCrteHostSerDO.setCreActionDt(DateUtils.converStringTimeStampToSqlTimeStamp(remsCreateHostServerDTO
						.getCreatedDate()));
			}
			// 1
			if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getHostServerName())) {
				remsCrteHostSerDO.setHostServerName(remsCreateHostServerDTO.getHostServerName());
			}
			// 2
			if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getServerPhyLoc())) {
				remsCrteHostSerDO.setServerPhyLoc(remsCreateHostServerDTO.getServerPhyLoc());
			}
			// 3
			if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getIp())) {
				remsCrteHostSerDO.setIp(remsCreateHostServerDTO.getIp());
			}
			// 4
			if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getAlloCPU())) {
				remsCrteHostSerDO.setAlloCpu(remsCreateHostServerDTO.getAlloCPU());
			}
			// 5
			if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getAlloDiskSpace())) {
				remsCrteHostSerDO.setAlloDiskSpace(remsCreateHostServerDTO.getAlloDiskSpace());
			}
			// 6
			if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getAlloMemory())) {
				remsCrteHostSerDO.setAlloMemory(remsCreateHostServerDTO.getAlloMemory());
			}

			// 7
			if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getSme())) {
				remsCrteHostSerDO.setSme(remsCreateHostServerDTO.getSme());
			}
			// 8
			if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getActive())) {
				if ("Yes".equalsIgnoreCase(remsCreateHostServerDTO.getActive())) {
					remsCrteHostSerDO.setActive("Y");
				} else {
					remsCrteHostSerDO.setActive("N");
				}
			}
			// 9
			if (StringUtils.isNotEmpty(remsCreateHostServerDTO.getUserId())) {
				remsCrteHostSerDO.setActionBy(remsCreateHostServerDTO.getUserId());
			}

		}
		return remsCrteHostSerDO;
	}

	@Override
	public RemsCreateHostServerDTO converRemsCrteHostSerDOToRemsCreateHostServerDTO(RemsCrteHostSerDO remsCrteHostSerDO) {

		RemsCreateHostServerDTO remsCreateHostServerDTO = null;
		if (null != remsCrteHostSerDO) {
			remsCreateHostServerDTO = new RemsCreateHostServerDTO();

			remsCreateHostServerDTO.setCreatedDate(format.format(remsCrteHostSerDO.getCreActionDt()));

			if (0 < remsCrteHostSerDO.getId()) {
				remsCreateHostServerDTO.setId(String.valueOf(remsCrteHostSerDO.getId()));
			}

			// 1
			if (StringUtils.isNotEmpty(remsCrteHostSerDO.getHostServerName())) {
				remsCreateHostServerDTO.setHostServerName(remsCrteHostSerDO.getHostServerName());
			}
			// 2
			if (StringUtils.isNotEmpty(remsCrteHostSerDO.getServerPhyLoc())) {
				remsCreateHostServerDTO.setServerPhyLoc(remsCrteHostSerDO.getServerPhyLoc());
			}
			// 3
			if (StringUtils.isNotEmpty(remsCrteHostSerDO.getIp())) {
				remsCreateHostServerDTO.setIp(remsCrteHostSerDO.getIp());
			}
			// 4
			if (StringUtils.isNotEmpty(remsCrteHostSerDO.getAlloCpu())) {
				remsCreateHostServerDTO.setAlloCPU(remsCrteHostSerDO.getAlloCpu());
			}
			// 5
			if (StringUtils.isNotEmpty(remsCrteHostSerDO.getAlloDiskSpace())) {
				remsCreateHostServerDTO.setAlloDiskSpace(remsCrteHostSerDO.getAlloDiskSpace());
			}
			// 6
			if (StringUtils.isNotEmpty(remsCrteHostSerDO.getAlloMemory())) {
				remsCreateHostServerDTO.setAlloMemory(remsCrteHostSerDO.getAlloMemory());
			}

			// 7
			if (StringUtils.isNotEmpty(remsCrteHostSerDO.getSme())) {
				remsCreateHostServerDTO.setSme(remsCrteHostSerDO.getSme());
			}
			// 8
			if (StringUtils.isNotEmpty(remsCrteHostSerDO.getActive())) {
				if ("Y".equalsIgnoreCase(remsCrteHostSerDO.getActive())) {
					remsCreateHostServerDTO.setActive("Yes");
				} else {
					remsCreateHostServerDTO.setActive("No");
				}
			}
			// 9
			if (StringUtils.isNotEmpty(remsCrteHostSerDO.getActionBy())) {
				remsCreateHostServerDTO.setUserId(remsCrteHostSerDO.getActionBy());
			}
			// 10
			if (null != remsCrteHostSerDO.getUpActionDt()) {
				remsCreateHostServerDTO.setUpActionDt(format.format(remsCrteHostSerDO.getUpActionDt()));
			}

		}
		return remsCreateHostServerDTO;
	}

	@Override
	public List<RemsCreateHostServerDTO> converRemsCrteHostSerDOListToTdmSearchRequestDTOList(
			List<RemsCrteHostSerDO> remsCrteHostSerDOs) {
		List<RemsCreateHostServerDTO> remsCreateHostServerDTOs = null;
		if (null != remsCrteHostSerDOs && 0 < remsCrteHostSerDOs.size()) {
			remsCreateHostServerDTOs = new ArrayList<RemsCreateHostServerDTO>();
			for (RemsCrteHostSerDO remsCrteHostSerDO : remsCrteHostSerDOs) {
				remsCreateHostServerDTOs.add(converRemsCrteHostSerDOToRemsCreateHostServerDTO(remsCrteHostSerDO));
			}
		}
		return remsCreateHostServerDTOs;
	}

}
