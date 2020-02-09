/*---------------------------------------------------------------------------------------
 * Object Name: RemsInstanceController.Java
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rems.constant.AppConstant;
import com.rems.constant.MessageConstant;
import com.rems.exception.BaseException;
import com.rems.model.DTO.RemsCreateEnvInstanceDTO;
import com.rems.service.RemsInstanceService;
import com.rems.util.PaginationUtil;

/**
 * @author Seshadri Chowdary
 * @version 1.0
 */

@Controller
public class RemsInstanceController
{

	private static Logger logger = Logger.getLogger(RemsInstanceController.class);

	@Resource(name = "remsInstanceService")
	RemsInstanceService remsInstanceService;

	@RequestMapping(value = "/remsCreateEnvInstance", method = RequestMethod.GET)
	public String manageHostServerGet(
			@ModelAttribute("remsCreateEnvInstanceDTO") RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO,
			@RequestParam(value = AppConstant.ID, required = false) String id, ModelMap modelmap,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			remsCreateEnvInstanceDTO.setUserId((String) request.getSession().getAttribute(AppConstant.SESSION_UID));
			List<String> hostServerNames = remsInstanceService.getHoatServerList();
			if (null != id) {
				remsCreateEnvInstanceDTO = remsInstanceService.selectInstanceRec(id);
			}
			remsCreateEnvInstanceDTO.setHostServerNames(hostServerNames);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			modelmap.addAttribute("remsCreateEnvInstanceDTO", remsCreateEnvInstanceDTO);
			return "remsCreateEnvInstance";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "remsCreateEnvInstance";
				}
			}
			modelmap.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by
			// passing key as // baseEx.getErrorCode();
			modelmap.addAttribute("remsCreateEnvInstanceDTO", remsCreateEnvInstanceDTO);
			return "remsCreateEnvInstance";
		}
	}

	@RequestMapping(value = "/remsCreateEnvInstance", method = RequestMethod.POST)
	public String manageInstancePost(
			@ModelAttribute("remsCreateEnvInstanceDTO") RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			remsCreateEnvInstanceDTO.setUserId((String) request.getSession().getAttribute(AppConstant.SESSION_UID));
			remsInstanceService.createInstance(remsCreateEnvInstanceDTO);
			model.addAttribute("remsCreateEnvInstanceDTO", remsCreateEnvInstanceDTO);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_USER_DRL
					+ MessageConstant.LOG_INFO_RETURN);
			return "redirect:remsCreateEnvInstanceListing";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_USER_DRL
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					model.addAttribute("remsCreateEnvInstanceDTO", remsCreateEnvInstanceDTO);
					return "remsCreateEnvInstance";
				}
			}
			model.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			model.addAttribute("remsCreateEnvInstanceDTO", remsCreateEnvInstanceDTO);
			return "remsCreateEnvInstance";
		}
	}

	@RequestMapping(value = "/remsCreateEnvInstanceListing")
	public String manageEnvInstanceListGet(
			@ModelAttribute("remsCreateEnvInstanceDTO") RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO,
			@RequestParam(value = AppConstant.PAGE, required = false) String page, ModelMap modelmap,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		Long totalRecords = 0L;
		PaginationUtil pagenation = new PaginationUtil();
		int recordsperpage = 10;
		boolean adminYn = false;
		try {
			if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
				adminYn = false;

			}
			int offSet = pagenation.getOffset(request, recordsperpage);
			totalRecords = remsInstanceService.instanceListCnt(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn,
					remsCreateEnvInstanceDTO);
			List<RemsCreateEnvInstanceDTO> remsCreateEnvInstanceDTOs = remsInstanceService.instanceList(offSet,
					recordsperpage, true, (String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn,
					remsCreateEnvInstanceDTO);

			List<String> hostServerNames = remsInstanceService.getHoatServerList();
			remsCreateEnvInstanceDTO.setHostServerNames(hostServerNames);

			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(AppConstant.NO_OF_PAGES, noOfPages);
			modelmap.addAttribute("remsCreateEnvInstanceDTOs", remsCreateEnvInstanceDTOs);
			modelmap.addAttribute("adminYN", "N");
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "remsCreateEnvInstanceListing";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					modelmap.addAttribute("adminYN", "N");
					return "remsCreateEnvInstanceListing";
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			modelmap.addAttribute("adminYN", "N");
			return "remsCreateEnvInstanceListing";
		}
	}

	@RequestMapping(value = "/remsCreateEnvInstanceListingAd")
	public String manageEnvInstanceListGetAd(
			@ModelAttribute("remsCreateEnvInstanceDTO") RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO,
			@RequestParam(value = AppConstant.PAGE, required = false) String page, ModelMap modelmap,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		Long totalRecords = 0L;
		PaginationUtil pagenation = new PaginationUtil();
		int recordsperpage = 10;
		boolean adminYn = false;
		try {
			if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
				adminYn = true;

			}
			int offSet = pagenation.getOffset(request, recordsperpage);
			totalRecords = remsInstanceService.instanceListCnt(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn,
					remsCreateEnvInstanceDTO);
			List<RemsCreateEnvInstanceDTO> remsCreateEnvInstanceDTOs = remsInstanceService.instanceList(offSet,
					recordsperpage, true, (String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn,
					remsCreateEnvInstanceDTO);
			List<String> hostServerNames = remsInstanceService.getHoatServerList();
			remsCreateEnvInstanceDTO.setHostServerNames(hostServerNames);

			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(AppConstant.NO_OF_PAGES, noOfPages);
			modelmap.addAttribute("remsCreateEnvInstanceDTOs", remsCreateEnvInstanceDTOs);
			modelmap.addAttribute("adminYN", "Y");
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "remsCreateEnvInstanceListingAd";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					modelmap.addAttribute("adminYN", "Y");
					return "remsCreateEnvInstanceListingAd";
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			modelmap.addAttribute("adminYN", "Y");
			return "remsCreateEnvInstanceListingAd";
		}
	}

	@RequestMapping(value = "/remsDeleteEnvInstance")
	public String daleteInstance(@RequestParam(value = AppConstant.ID, required = false) String id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws BaseException {
		logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			remsInstanceService.daleteInstance(id);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "redirect:remsCreateEnvInstanceListing";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "redirect:remsCreateEnvInstanceListing";
				}
			}
			model.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by passing key as
			// baseEx.getErrorCode();
			return "redirect:remsCreateEnvInstanceListing";
		}
	}

	@RequestMapping(value = "/remsDeleteEnvInstanceAd")
	public String daleteInstanceAd(@RequestParam(value = AppConstant.ID, required = false) String id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws BaseException {
		logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			remsInstanceService.daleteInstance(id);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "redirect:remsCreateEnvInstanceListingAd";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "redirect:remsCreateEnvInstanceListingAd";
				}
			}
			model.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by passing key as
			// baseEx.getErrorCode();
			return "redirect:remsCreateEnvInstanceListingAd";
		}
	}

	@RequestMapping(value = "/remsCreateEnvInstanceListing", method = RequestMethod.POST, params = AppConstant.EXPORT)
	public ModelAndView envInstanceDataExport(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		logger.info(MessageConstant.TDM_RESERVE_POLICY_CTRL + MessageConstant.TDM_RESV_POLICY_EXPORT
				+ MessageConstant.LOG_INFO_EXPORT);
		List<RemsCreateEnvInstanceDTO> remsCreateEnvInstanceDTOs = null;

		boolean adminYn = false;
		try {
			if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
				adminYn = false;

			}
			remsCreateEnvInstanceDTOs = remsInstanceService.envInstanceListAll((String) request.getSession()
					.getAttribute(AppConstant.SESSION_UID), adminYn);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return new ModelAndView("remsEnvInstanceListExcelView", "remsCreateEnvInstanceDTOs",
					remsCreateEnvInstanceDTOs);
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return new ModelAndView("remsEnvInstanceListExcelView", "remsCreateEnvInstanceDTOs",
							remsCreateEnvInstanceDTOs);
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return new ModelAndView("remsEnvInstanceListExcelView", "remsCreateEnvInstanceDTOs",
					remsCreateEnvInstanceDTOs);

		}

	}

	@RequestMapping(value = "/remsCreateEnvInstanceListingAd", method = RequestMethod.POST, params = AppConstant.EXPORT)
	public ModelAndView envInstanceDataExportAd(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		logger.info(MessageConstant.TDM_RESERVE_POLICY_CTRL + MessageConstant.TDM_RESV_POLICY_EXPORT
				+ MessageConstant.LOG_INFO_EXPORT);
		List<RemsCreateEnvInstanceDTO> remsCreateEnvInstanceDTOs = null;
		boolean adminYn = false;
		try {
			if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
				adminYn = true;

			}
			remsCreateEnvInstanceDTOs = remsInstanceService.envInstanceListAll((String) request.getSession()
					.getAttribute(AppConstant.SESSION_UID), adminYn);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return new ModelAndView("remsEnvInstanceListExcelView", "remsCreateEnvInstanceDTOs",
					remsCreateEnvInstanceDTOs);
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return new ModelAndView("remsEnvInstanceListExcelView", "remsCreateEnvInstanceDTOs",
							remsCreateEnvInstanceDTOs);
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return new ModelAndView("remsEnvInstanceListExcelView", "remsCreateEnvInstanceDTOs",
					remsCreateEnvInstanceDTOs);

		}

	}

}
