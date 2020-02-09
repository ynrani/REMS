/*---------------------------------------------------------------------------------------
 * Object Name: RemsBookingController.Java
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
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.rems.model.DTO.RemsEnvBookingsDTO;
import com.rems.service.RemsBookingService;
import com.rems.util.PaginationUtil;

/**
 * @author Seshadri Chowdary
 * @version 1.0
 */

@Controller
public class RemsBookingController
{

	private static Logger logger = Logger.getLogger(RemsBookingController.class);

	@Resource(name = "remsBookingService")
	RemsBookingService remsBookingService;

	@RequestMapping(value = "/remsEnvBooking", method = RequestMethod.GET)
	public String manageBookingGet(@ModelAttribute("remsEnvBookingsDTO") RemsEnvBookingsDTO remsEnvBookingsDTO,
			@RequestParam(value = AppConstant.ID, required = false) String id, ModelMap modelmap,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			Map<String, List<String>> instanceNames = remsBookingService.getInstanceList();
			if (null != id) {
				remsEnvBookingsDTO = remsBookingService.selectBookingRec(id);
			}
			if (null != instanceNames) {
				remsEnvBookingsDTO.setAppNames(instanceNames.get("A"));
				remsEnvBookingsDTO.setEnvNames(instanceNames.get("E"));
			}

			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			modelmap.addAttribute("remsEnvBookingsDTO", remsEnvBookingsDTO);
			return "remsEnvBooking";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "remsEnvBooking";
				}
			}
			modelmap.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by
			// passing key as // baseEx.getErrorCode();
			modelmap.addAttribute("remsEnvBookingsDTO", remsEnvBookingsDTO);
			return "remsEnvBooking";
		}

	}

	@RequestMapping(value = "/remsEnvBooking", method = RequestMethod.POST)
	public String manageHostServerPost(@ModelAttribute("remsEnvBookingsDTO") RemsEnvBookingsDTO remsEnvBookingsDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			remsEnvBookingsDTO.setUserId((String) request.getSession().getAttribute(AppConstant.SESSION_UID));
			remsBookingService.createBooking(remsEnvBookingsDTO);
			model.addAttribute("remsEnvBookingsDTO", remsEnvBookingsDTO);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_USER_DRL
					+ MessageConstant.LOG_INFO_RETURN);
			return "redirect:remsEnvBookingListing";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_USER_DRL
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					model.addAttribute("remsEnvBookingsDTO", remsEnvBookingsDTO);
					return "remsEnvBooking";
				}
			}
			model.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			model.addAttribute("remsEnvBookingsDTO", remsEnvBookingsDTO);
			return "remsEnvBooking";
		}
	}

	@RequestMapping(value = "/remsEnvBookingListing")
	public String manageBookingListGet(@ModelAttribute("remsEnvBookingsDTO") RemsEnvBookingsDTO remsEnvBookingsDTO,
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
			totalRecords = remsBookingService.bookingListCnt(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn, remsEnvBookingsDTO,
					null);
			List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = remsBookingService.bookingList(offSet, recordsperpage, true,
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn, remsEnvBookingsDTO,
					null);

			Map<String, List<String>> instanceNames = remsBookingService.getInstanceList();
			if (null != instanceNames) {
				remsEnvBookingsDTO.setAppNames(instanceNames.get("A"));
				remsEnvBookingsDTO.setEnvNames(instanceNames.get("E"));
			}

			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(AppConstant.NO_OF_PAGES, noOfPages);
			modelmap.addAttribute("remsEnvBookingsDTOs", remsEnvBookingsDTOs);
			modelmap.addAttribute("adminYN", "N");
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "remsEnvBookingListing";
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
					return "remsEnvBookingListing";
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			modelmap.addAttribute("adminYN", "N");
			return "remsEnvBookingListing";
		}

	}

	@RequestMapping(value = "/remsEnvBookingListingAd")
	public String manageBookingListGetAd(@ModelAttribute("remsEnvBookingsDTO") RemsEnvBookingsDTO remsEnvBookingsDTO,
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
			totalRecords = remsBookingService.bookingListCnt(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn, remsEnvBookingsDTO,
					null);
			List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = remsBookingService.bookingList(offSet, recordsperpage, true,
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn, remsEnvBookingsDTO,
					null);

			Map<String, List<String>> instanceNames = remsBookingService.getInstanceList();
			if (null != instanceNames) {
				remsEnvBookingsDTO.setAppNames(instanceNames.get("A"));
				remsEnvBookingsDTO.setEnvNames(instanceNames.get("E"));
			}

			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(AppConstant.NO_OF_PAGES, noOfPages);
			modelmap.addAttribute("remsEnvBookingsDTOs", remsEnvBookingsDTOs);
			modelmap.addAttribute("adminYN", "Y");
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "remsEnvBookingListingAd";
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
					return "remsEnvBookingListingAd";
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			modelmap.addAttribute("adminYN", "Y");
			return "remsEnvBookingListingAd";
		}

	}

	@RequestMapping(value = "/remsDeleteEnvBooking")
	public String daleteBooking(@RequestParam(value = AppConstant.ID, required = false) String id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws BaseException {
		logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			remsBookingService.daleteBooking(id);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "redirect:remsEnvBookingListing";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "redirect:remsEnvBookingListing";
				}
			}
			model.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by passing key as
			// baseEx.getErrorCode();
			return "redirect:remsEnvBookingListing";
		}
	}

	@RequestMapping(value = "/remsDeleteEnvBookingAd")
	public String daleteBookingAd(@RequestParam(value = AppConstant.ID, required = false) String id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws BaseException {
		logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			remsBookingService.daleteBooking(id);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "redirect:remsEnvBookingListingAd";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DELETE_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "redirect:remsEnvBookingListingAd";
				}
			}
			model.addAttribute(AppConstant.ERROR, MessageConstant.EXCEPTION_ADMIN);
			// responseMsg = passcodes and get msg from properties file by passing key as
			// baseEx.getErrorCode();
			return "redirect:remsEnvBookingListingAd";
		}
	}

	@RequestMapping(value = "/remsEnvBookingListing", method = RequestMethod.POST, params = AppConstant.EXPORT)
	public ModelAndView envBookingDataExport(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		logger.info(MessageConstant.TDM_RESERVE_POLICY_CTRL + MessageConstant.TDM_RESV_POLICY_EXPORT
				+ MessageConstant.LOG_INFO_EXPORT);
		List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = null;
		boolean adminYn = false;
		try {
			if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
				adminYn = false;

			}
			remsEnvBookingsDTOs = remsBookingService.bookingListAll(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return new ModelAndView("remsBookingListExcelView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return new ModelAndView("remsEnvInstanceListExcelView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return new ModelAndView("remsEnvInstanceListExcelView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);

		}

	}

	@RequestMapping(value = "/remsEnvBookingListingAd", method = RequestMethod.POST, params = AppConstant.EXPORT)
	public ModelAndView envBookingDataExportAd(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		logger.info(MessageConstant.TDM_RESERVE_POLICY_CTRL + MessageConstant.TDM_RESV_POLICY_EXPORT
				+ MessageConstant.LOG_INFO_EXPORT);
		List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = null;
		boolean adminYn = false;
		try {
			if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
				adminYn = true;

			}
			remsEnvBookingsDTOs = remsBookingService.bookingListAll(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return new ModelAndView("remsBookingListExcelView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return new ModelAndView("remsEnvInstanceListExcelView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return new ModelAndView("remsEnvInstanceListExcelView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);

		}

	}

	@RequestMapping(value = "/remsReportDwonload", method = RequestMethod.GET)
	public ModelAndView remsReportDataExport(@RequestParam(value = "format") String format, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws BaseException {
		logger.info(MessageConstant.TDM_RESERVE_POLICY_CTRL + MessageConstant.TDM_RESV_POLICY_EXPORT
				+ MessageConstant.LOG_INFO_EXPORT);
		List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = null;

		try {
			remsEnvBookingsDTOs = remsBookingService.bookingListAll(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), true);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);

			if (null != format && "excel".equalsIgnoreCase(format)) {
				return new ModelAndView("remsReportsExcelView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);
			} else if (null != format && "pdf".equalsIgnoreCase(format)) {
				return new ModelAndView("remsReportsPdfView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);
			}

		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					if (null != format && "excel".equalsIgnoreCase(format)) {
						return new ModelAndView("remsReportsExcelView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);
					} else if (null != format && "pdf".equalsIgnoreCase(format)) {
						return new ModelAndView("remsReportsPdfView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);
					}
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			if (null != format && "excel".equalsIgnoreCase(format)) {
				return new ModelAndView("remsReportsExcelView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);
			} else if (null != format && "pdf".equalsIgnoreCase(format)) {
				return new ModelAndView("remsReportsPdfView", "remsEnvBookingsDTOs", remsEnvBookingsDTOs);
			}

		}
		return null;
	}

	@RequestMapping(value = "/remsReport", method = RequestMethod.GET)
	public String reportsGet(@RequestParam(value = AppConstant.PAGE, required = false) String page, ModelMap modelmap,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		Long totalRecords = 0L;
		PaginationUtil pagenation = new PaginationUtil();
		int recordsperpage = 10;
		try {
			int offSet = pagenation.getOffset(request, recordsperpage);
			totalRecords = remsBookingService.bookingListCnt(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), true, null, "Confirm");
			List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = remsBookingService.bookingList(offSet, recordsperpage, true,
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), true, null, "Confirm");
			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(AppConstant.NO_OF_PAGES, noOfPages);
			modelmap.addAttribute("remsReportsDTOs", remsEnvBookingsDTOs);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "remsReport";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "remsReport";
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return "remsReport";
		}

	}

	@RequestMapping(value = "/remsBookingRequests", method = RequestMethod.GET)
	public String confirmBooking(@RequestParam(value = AppConstant.PAGE, required = false) String page,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "ac", required = false) String action, ModelMap modelmap, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		Long totalRecords = 0L;
		PaginationUtil pagenation = new PaginationUtil();
		int recordsperpage = 10;
		boolean confirm = false;
		try {
			if (StringUtils.isNotEmpty(action) && StringUtils.isNotEmpty(id)) {
				confirm = remsBookingService.confirmDeclinebooking(id, action);
			}
			int offSet = pagenation.getOffset(request, recordsperpage);
			totalRecords = remsBookingService.bookingListCnt(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), true, null,
					"Confirm','Decline");
			List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = remsBookingService.bookingList(offSet, recordsperpage, true,
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), true, null,
					"Confirm','Decline");
			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(AppConstant.NO_OF_PAGES, noOfPages);
			modelmap.addAttribute("remsReportsDTOs", remsEnvBookingsDTOs);
			modelmap.addAttribute("confirm", confirm);

			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "remsBookingRequests";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith("")) {
					return "remsBookingRequests";
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			modelmap.addAttribute("confirm", confirm);
			return "remsBookingRequests";
		}

	}

}
