/*---------------------------------------------------------------------------------------
 * Object Name: RemsCalendarViewController.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          30/10/15  NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 <CapGemini>
 *---------------------------------------------------------------------------------------*/

package com.rems.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.rems.constant.AppConstant;
import com.rems.constant.MessageConstant;
import com.rems.exception.BaseException;
import com.rems.model.DTO.RemsCalendarDTO;
import com.rems.service.RemsBookingService;

/**
 * @author Seshadri Chowdary
 * @version 1.0
 */

@Controller
public class RemsCalendarViewController
{

	private static Logger logger = Logger.getLogger(RemsCalendarViewController.class);
	@Resource(name = "remsBookingService")
	RemsBookingService remsBookingService;

	@RequestMapping(value = "/remsCalendarView", method = RequestMethod.GET)
	public String manageHostServerGet(ModelMap modelmap, HttpServletRequest request, HttpServletResponse response) {
		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);

		return "remsCalendarView";
	}

	@RequestMapping(value = "/remsCalView", method = RequestMethod.GET)
	public @ResponseBody String manageCalendar(ModelMap modelmap, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		List<RemsCalendarDTO> remsCalendarDTOs = null;
		String json = null;
		boolean adminYn = false;
		try {
			if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
				adminYn = true;
			}
			remsCalendarDTOs = remsBookingService.calendarListAll(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn);

			if (null != remsCalendarDTOs && 0 < remsCalendarDTOs.size()) {
				json = new Gson().toJson(remsCalendarDTOs);
			}

			// Write JSON string.
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return json;
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return json;
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return json;
		}

	}

}
