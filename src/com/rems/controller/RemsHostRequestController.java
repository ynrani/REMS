/*---------------------------------------------------------------------------------------
 * Object Name: RemsHostRequestController.Java
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
import com.rems.model.DTO.RemsCreateHostServerDTO;
import com.rems.service.RemsRequestService;
import com.rems.util.PaginationUtil;

/**
 * @author Seshadri Chowdary
 * @version 1.0
 */

@Controller
public class RemsHostRequestController
{

	private static Logger logger = Logger.getLogger(RemsHostRequestController.class);

	@Resource(name = "remsRequestService")
	RemsRequestService remsRequestService;

	@RequestMapping(value = "/remsCreateHostServer", method = RequestMethod.GET)
	public String manageHostServerGet(
			@ModelAttribute("remsCreateHostServerDTO") RemsCreateHostServerDTO remsCreateHostServerDTO,
			@RequestParam(value = AppConstant.ID, required = false) String id, ModelMap modelmap,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			if (null != id) {
				remsCreateHostServerDTO = remsRequestService.selectHostRec(id);
			}
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			modelmap.addAttribute("remsCreateHostServerDTO", remsCreateHostServerDTO);
			return "remsCreateHostServer";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "remsCreateHostServer";
				}
			}
			modelmap.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by
			// passing key as // baseEx.getErrorCode();
			modelmap.addAttribute("remsCreateHostServerDTO", remsCreateHostServerDTO);
			return "remsCreateHostServer";
		}

	}

	@RequestMapping(value = "/remsCreateHostServer", method = RequestMethod.POST)
	public String manageHostServerPost(
			@ModelAttribute("remsCreateHostServerDTO") RemsCreateHostServerDTO remsCreateHostServerDTO, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			remsCreateHostServerDTO.setUserId((String) request.getSession().getAttribute(AppConstant.SESSION_UID));
			remsRequestService.createHostServer(remsCreateHostServerDTO);
			model.addAttribute("remsCreateHostServerDTO", remsCreateHostServerDTO);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_USER_DRL
					+ MessageConstant.LOG_INFO_RETURN);
			return "redirect:remsCreateHostServerListing";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_USER_DRL
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "remsCreateHostServer";
				}
			}
			model.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return "remsCreateHostServer";
		}
	}

	@RequestMapping(value = "/remsCreateHostServerListing")
	public String manageHostServerListGet(
			@ModelAttribute("remsCreateHostServerDTO") RemsCreateHostServerDTO remsCreateHostServerDTO,
			@RequestParam(value = AppConstant.PAGE, required = false) String page, ModelMap modelmap,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		Long totalRecords = 0L;
		PaginationUtil pagenation = new PaginationUtil();
		int recordsperpage = 10;
		boolean adminYn = false;
		List<RemsCreateHostServerDTO> remsCreateHostServerDTOs = null;
		try {
			if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
				adminYn = false;

			}
			int offSet = pagenation.getOffset(request, recordsperpage);

			totalRecords = remsRequestService.hostServerListCnt(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn,
					remsCreateHostServerDTO);
			remsCreateHostServerDTOs = remsRequestService.hostServerList(offSet, recordsperpage, true, (String) request
					.getSession().getAttribute(AppConstant.SESSION_UID), adminYn, remsCreateHostServerDTO);
			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(AppConstant.NO_OF_PAGES, noOfPages);
			modelmap.addAttribute("remsCreateHostServerDTOs", remsCreateHostServerDTOs);
			modelmap.addAttribute("adminYN", "N");
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "remsCreateHostServerListing";
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
					return "remsCreateHostServerListing";
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			modelmap.addAttribute("adminYN", "N");
			modelmap.addAttribute("remsCreateHostServerDTOs", remsCreateHostServerDTOs);
			return "remsCreateHostServerListing";

		}

	}

	@RequestMapping(value = "/remsCreateHostServerListingAd")
	public String manageHostServerListGetForAD(
			@ModelAttribute("remsCreateHostServerDTO") RemsCreateHostServerDTO remsCreateHostServerDTO,
			@RequestParam(value = AppConstant.PAGE, required = false) String page, ModelMap modelmap,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		Long totalRecords = 0L;
		PaginationUtil pagenation = new PaginationUtil();
		int recordsperpage = 10;
		boolean adminYn = false;
		List<RemsCreateHostServerDTO> remsCreateHostServerDTOs = null;
		try {
			if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
				adminYn = true;
			}
			int offSet = pagenation.getOffset(request, recordsperpage);

			totalRecords = remsRequestService.hostServerListCnt(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn,
					remsCreateHostServerDTO);
			remsCreateHostServerDTOs = remsRequestService.hostServerList(offSet, recordsperpage, true, (String) request
					.getSession().getAttribute(AppConstant.SESSION_UID), adminYn, remsCreateHostServerDTO);
			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(AppConstant.NO_OF_PAGES, noOfPages);
			modelmap.addAttribute("remsCreateHostServerDTOs", remsCreateHostServerDTOs);
			modelmap.addAttribute("adminYN", "Y");
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "remsCreateHostServerListingAd";
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

					return "remsCreateHostServerListingAd";
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			modelmap.addAttribute("adminYN", "Y");
			modelmap.addAttribute("remsCreateHostServerDTOs", remsCreateHostServerDTOs);
			return "remsCreateHostServerListingAd";

		}

	}

	@RequestMapping(value = "/remsDeleteHostServer")
	public String daleteHostServer(@RequestParam(value = AppConstant.ID, required = false) String id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws BaseException {
		logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			remsRequestService.deleteHostServer(id);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "redirect:remsCreateHostServerListing";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "redirect:remsCreateHostServerListing";
				}
			}
			model.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by passing key as
			// baseEx.getErrorCode();
			return "redirect:remsCreateHostServerListing";
		}
	}

	@RequestMapping(value = "/remsDeleteHostServerAd")
	public String daleteHostServerAd(@RequestParam(value = AppConstant.ID, required = false) String id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws BaseException {
		logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			remsRequestService.deleteHostServer(id);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "redirect:remsCreateHostServerListingAd";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "redirect:remsCreateHostServerListingAd";
				}
			}
			model.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by passing key as
			// baseEx.getErrorCode();
			return "redirect:remsCreateHostServerListingAd";
		}
	}

	@RequestMapping(value = "/remsCreateHostServerListing", method = RequestMethod.POST, params = AppConstant.EXPORT)
	public ModelAndView hostServerListDataExport(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		logger.info(MessageConstant.TDM_RESERVE_POLICY_CTRL + MessageConstant.TDM_RESV_POLICY_EXPORT
				+ MessageConstant.LOG_INFO_EXPORT);
		List<RemsCreateHostServerDTO> remsCreateHostServerDTOs = null;
		boolean adminYn = false;
		try {
			if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
				adminYn = false;

			}
			remsCreateHostServerDTOs = remsRequestService.hostServerListAll(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return new ModelAndView("remsHostRequestListExcelView", "remsCreateHostServerDTOs",
					remsCreateHostServerDTOs);
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return new ModelAndView("remsHostRequestListExcelView", "remsCreateHostServerDTOs",
							remsCreateHostServerDTOs);
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return new ModelAndView("remsHostRequestListExcelView", "remsCreateHostServerDTOs",
					remsCreateHostServerDTOs);

		}

	}

	@RequestMapping(value = "/remsCreateHostServerListingAd", method = RequestMethod.POST, params = AppConstant.EXPORT)
	public ModelAndView hostServerListDataExportAD(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		logger.info(MessageConstant.TDM_RESERVE_POLICY_CTRL + MessageConstant.TDM_RESV_POLICY_EXPORT
				+ MessageConstant.LOG_INFO_EXPORT);
		List<RemsCreateHostServerDTO> remsCreateHostServerDTOs = null;
		boolean adminYn = false;
		try {
			if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
				adminYn = true;

			}
			remsCreateHostServerDTOs = remsRequestService.hostServerListAll(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return new ModelAndView("remsHostRequestListExcelView", "remsCreateHostServerDTOs",
					remsCreateHostServerDTOs);
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return new ModelAndView("remsHostRequestListExcelView", "remsCreateHostServerDTOs",
							remsCreateHostServerDTOs);
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return new ModelAndView("remsHostRequestListExcelView", "remsCreateHostServerDTOs",
					remsCreateHostServerDTOs);

		}

	}

}
