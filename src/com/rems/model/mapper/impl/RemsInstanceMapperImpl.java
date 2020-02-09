package com.rems.model.mapper.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rems.model.DO.RemsCrteInstanceDO;
import com.rems.model.DTO.RemsCreateEnvInstanceDTO;
import com.rems.model.mapper.RemsInstanceMapper;
import com.rems.util.DateUtils;

@Component
@Service("remsInstanceMapper")
public class RemsInstanceMapperImpl implements RemsInstanceMapper
{
	SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a zzz");

	@Override
	public RemsCrteInstanceDO converRemsCreateEnvInstanceDTOToRemsCrteInstanceDO(
			RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO, String seq) {
		RemsCrteInstanceDO remsCrteInstanceDO = null;
		if (null != remsCreateEnvInstanceDTO) {
			remsCrteInstanceDO = new RemsCrteInstanceDO();

			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getId())) {
				remsCrteInstanceDO.setId(Long.parseLong(remsCreateEnvInstanceDTO.getId()));
				remsCrteInstanceDO.setUpActionDt(new Timestamp(new Date().getTime()));
			} else {
				remsCrteInstanceDO.setId(Long.parseLong(seq));
				remsCrteInstanceDO.setCreActionDt(new Timestamp(new Date().getTime()));
			}

			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getCreatedDate())) {
				remsCrteInstanceDO.setCreActionDt(DateUtils
						.converStringTimeStampToSqlTimeStamp(remsCreateEnvInstanceDTO.getCreatedDate()));
			}
			// 1
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getProjName())) {
				remsCrteInstanceDO.setProjName(remsCreateEnvInstanceDTO.getProjName());
			}
			// 2
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getEnvName())) {
				remsCrteInstanceDO.setEnvName(remsCreateEnvInstanceDTO.getProjName());
			}
			// 3
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getAppName())) {
				remsCrteInstanceDO.setApplName(remsCreateEnvInstanceDTO.getAppName());
			}
			// 4
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getSwLoadInfo())) {
				remsCrteInstanceDO.setSwLoadInfo(remsCreateEnvInstanceDTO.getSwLoadInfo());
			}
			// 5
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getHostServerName())) {
				remsCrteInstanceDO.setHostServerName(remsCreateEnvInstanceDTO.getHostServerName());
			}
			// 6
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getOsVer())) {
				remsCrteInstanceDO.setOsVer(remsCreateEnvInstanceDTO.getOsVer());
			}
			// 7
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getDbName())) {
				remsCrteInstanceDO.setDbName(remsCreateEnvInstanceDTO.getDbName());
			}
			// 8
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getNwConnInfo())) {
				remsCrteInstanceDO.setNwConnInfo(remsCreateEnvInstanceDTO.getNwConnInfo());
			}
			// 9
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getAlloMemory())) {
				remsCrteInstanceDO.setAlloMemory(remsCreateEnvInstanceDTO.getAlloMemory());
			}
			// 10
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getEnvType())) {
				remsCrteInstanceDO.setEnvType(remsCreateEnvInstanceDTO.getEnvType());
			}
			// 11
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getSme())) {
				remsCrteInstanceDO.setSme(remsCreateEnvInstanceDTO.getSme());
			}
			// 12
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getAnotherEnv())) {
				remsCrteInstanceDO.setAnotherEnv(remsCreateEnvInstanceDTO.getAnotherEnv());
			}
			// 13
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getUserId())) {
				remsCrteInstanceDO.setActionBy(remsCreateEnvInstanceDTO.getUserId());
			}
			// 14
			if (StringUtils.isNotEmpty(remsCreateEnvInstanceDTO.getActive())) {
				if ("Yes".equalsIgnoreCase(remsCreateEnvInstanceDTO.getActive())) {
					remsCrteInstanceDO.setActive("Y");
				} else {
					remsCrteInstanceDO.setActive("N");
				}
			}

		}
		return remsCrteInstanceDO;
	}

	@Override
	public RemsCreateEnvInstanceDTO converRemsCrteInstanceDOToRemsCreateEnvInstanceDTO(
			RemsCrteInstanceDO remsCrteInstanceDO) {
		RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO = null;
		if (null != remsCrteInstanceDO) {
			remsCreateEnvInstanceDTO = new RemsCreateEnvInstanceDTO();
			remsCreateEnvInstanceDTO.setCreatedDate(format.format(remsCrteInstanceDO.getCreActionDt()));
			if (0 < remsCrteInstanceDO.getId()) {
				remsCreateEnvInstanceDTO.setId(String.valueOf(remsCrteInstanceDO.getId()));

			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getProjName())) {
				remsCreateEnvInstanceDTO.setProjName(remsCrteInstanceDO.getProjName());
			}

			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getEnvName())) {
				remsCreateEnvInstanceDTO.setEnvName(remsCrteInstanceDO.getProjName());
			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getApplName())) {
				remsCreateEnvInstanceDTO.setAppName(remsCrteInstanceDO.getApplName());
			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getSwLoadInfo())) {
				remsCreateEnvInstanceDTO.setSwLoadInfo(remsCrteInstanceDO.getSwLoadInfo());
			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getHostServerName())) {
				remsCreateEnvInstanceDTO.setHostServerName(remsCrteInstanceDO.getHostServerName());
			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getOsVer())) {
				remsCreateEnvInstanceDTO.setOsVer(remsCrteInstanceDO.getOsVer());
			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getDbName())) {
				remsCreateEnvInstanceDTO.setDbName(remsCrteInstanceDO.getDbName());
			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getNwConnInfo())) {
				remsCreateEnvInstanceDTO.setNwConnInfo(remsCrteInstanceDO.getNwConnInfo());
			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getAlloMemory())) {
				remsCreateEnvInstanceDTO.setAlloMemory(remsCrteInstanceDO.getAlloMemory());
			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getEnvType())) {
				remsCreateEnvInstanceDTO.setEnvType(remsCrteInstanceDO.getEnvType());
			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getSme())) {
				remsCreateEnvInstanceDTO.setSme(remsCrteInstanceDO.getSme());
			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getAnotherEnv())) {
				remsCreateEnvInstanceDTO.setAnotherEnv(remsCrteInstanceDO.getAnotherEnv());
			}
			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getActionBy())) {
				remsCreateEnvInstanceDTO.setUserId(remsCrteInstanceDO.getActionBy());
			}

			if (StringUtils.isNotEmpty(remsCrteInstanceDO.getActive())) {
				if ("Y".equalsIgnoreCase(remsCrteInstanceDO.getActive())) {
					remsCreateEnvInstanceDTO.setActive("Yes");
				} else {
					remsCreateEnvInstanceDTO.setActive("No");
				}
			}

			if (null != remsCrteInstanceDO.getUpActionDt()) {
				remsCreateEnvInstanceDTO.setUpActionDt(format.format(remsCrteInstanceDO.getUpActionDt()));
			}

		}
		return remsCreateEnvInstanceDTO;
	}

	@Override
	public List<RemsCreateEnvInstanceDTO> converRemsCrteInstanceDOListToRemsCreateEnvInstanceDTOList(
			List<RemsCrteInstanceDO> remsCrteInstanceDOs) {
		List<RemsCreateEnvInstanceDTO> remsCreateEnvInstanceDTOs = null;
		if (null != remsCrteInstanceDOs && 0 < remsCrteInstanceDOs.size()) {
			remsCreateEnvInstanceDTOs = new ArrayList<RemsCreateEnvInstanceDTO>();
			for (RemsCrteInstanceDO remsCrteInstanceDO : remsCrteInstanceDOs) {
				remsCreateEnvInstanceDTOs.add(converRemsCrteInstanceDOToRemsCreateEnvInstanceDTO(remsCrteInstanceDO));
			}
		}
		return remsCreateEnvInstanceDTOs;
	}

}
