package com.rems.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

import com.rems.constant.AppConstant;
import com.rems.constant.MessageConstant;
import com.rems.exception.BaseException;
import com.rems.model.DTO.RemsEnvBookingsDTO;
import com.rems.service.RemsBookingService;
import com.rems.service.RemsEnvService;
import com.rems.util.PaginationUtil;

@Controller
public class RemsEnvironmentController
{

	private static Logger logger = Logger.getLogger(RemsEnvironmentController.class);

	@Resource(name = MessageConstant.SERVICE_ENV)
	RemsEnvService remsEnvironmentService;

	@Resource(name = "remsBookingService")
	RemsBookingService remsBookingService;

	@RequestMapping(value = AppConstant.TDM_SEARCH_APP, method = RequestMethod.GET)
	public String displayEnvHome(ModelMap model,
			@ModelAttribute(MessageConstant.USER_DO) RemsEnvBookingsDTO remsEnvBookingsDTO, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(MessageConstant.TDM_ENV_CTRL + MessageConstant.TDM_ADMIN_DISPLAY
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		model.addAttribute("remsEnvBookingsDTO", remsEnvBookingsDTO);
		return "environmentSearch";
	}

	@RequestMapping(value = AppConstant.TDM_APP_LIST, method = RequestMethod.GET)
	public String displayAppSearch(ModelMap model,
			@ModelAttribute("RemsBookEnviDO") RemsEnvBookingsDTO remsEnvBookingsDTO) {
		logger.info(MessageConstant.TDM_ENV_CTRL + "App Search Page" + MessageConstant.LOG_INFO_PARAMS_NO);
		model.addAttribute("remsEnvSearchDTO", remsEnvBookingsDTO);
		return "displayApps";
	}

	@RequestMapping(value = "/displayApps", method = RequestMethod.POST)
	public String manageBookingListGet(@ModelAttribute("remsEnvBookingsDTO") RemsEnvBookingsDTO remsEnvBookingsDTO,
			@RequestParam(value = AppConstant.PAGE, required = false) String page, ModelMap modelmap,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info(MessageConstant.TDM_LOGIN_CTLR + MessageConstant.TDM_LOGIN_GET + MessageConstant.LOG_INFO_PARAMS_NO);
		Long totalRecords = 0L;
		PaginationUtil pagenation = new PaginationUtil();
		int recordsperpage = 10;
		boolean adminYn = false;
		if (request.getSession().getAttribute(AppConstant.ROLE).equals(AppConstant.ROLE_ADMIN)) {
			adminYn = true;

		}
		if (adminYn == false) {

			String startDt = remsEnvBookingsDTO.getStartDt();
			String endDt = remsEnvBookingsDTO.getEndDt();
			request.setAttribute("startDt", startDt);
			request.setAttribute("endDt", endDt);
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

			Date d1 = null;
			Date d2 = null;
			List<String> dateList = new ArrayList<String>();

			try {
				d1 = format.parse(startDt);
				d2 = format.parse(endDt);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			long diff = d2.getTime() - d1.getTime();
			diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			System.out.print("Diff : " + diff);
			for (int i = 1; i < diff; i++) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(d1);
				cal.add(Calendar.DATE, 1);
				Date x = cal.getTime();
				String yy = format.format(x);
				dateList.add(yy);
				/*
				 * try { d1 = format.parse(yy); } catch (Exception e) { e.printStackTrace(); }
				 */
				// request.setAttribute("dateList", dateList);
			}
			request.setAttribute("dateList", dateList);
			remsEnvBookingsDTO.setPassedDates(dateList);
		}
		try {

			int offSet = pagenation.getOffset(request, recordsperpage);
			totalRecords = remsBookingService.bookingListCnt(
					(String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn, remsEnvBookingsDTO,
					null);
			List<RemsEnvBookingsDTO> listRemsEnvBookingsDTOs = remsBookingService.bookingList(offSet, recordsperpage,
					true, (String) request.getSession().getAttribute(AppConstant.SESSION_UID), adminYn,
					remsEnvBookingsDTO, null);

			Map<String, List<String>> instanceNames = remsBookingService.getInstanceList();
			if (null != instanceNames) {
				remsEnvBookingsDTO.setAppNames(instanceNames.get("A"));
				remsEnvBookingsDTO.setEnvNames(instanceNames.get("E"));
			}

			/*
			 * List<Object> selList = new ArrayList<Object>(); for (int i = 0; i <
			 * listRemsEnvBookingsDTOs.size(); i++) { selList.add(listRemsEnvBookingsDTOs.get(i)); }
			 */

			/*
			 * List<Set<Object>> listSet = new ArrayList<Set<Object>>(); Set<Object> setObject = new
			 * HashSet<Object>(); for (int i = 0; i < listRemsEnvBookingsDTOs.size(); i++) { if
			 * (!setObject.contains(listRemsEnvBookingsDTOs.get(i).getAppName()) &&
			 * !setObject.contains(listRemsEnvBookingsDTOs.get(i).getEnvName())) {
			 * setObject.add(listRemsEnvBookingsDTOs.get(i).getAppName());
			 * setObject.add(listRemsEnvBookingsDTOs.get(i).getEnvName()); } else if
			 * (setObject.contains(listRemsEnvBookingsDTOs.get(i).getAppName()) &&
			 * !setObject.contains(listRemsEnvBookingsDTOs.get(i).getEnvName())) {
			 * 
			 * } selList.add(listRemsEnvBookingsDTOs.get(i)); }
			 */
			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(AppConstant.NO_OF_PAGES, noOfPages);
			modelmap.addAttribute("remsEnvBookingsDTOs", listRemsEnvBookingsDTOs);
			modelmap.addAttribute("adminYN", "N");
			logger.info(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_INFO_RETURN);
			return "displayApps";
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + MessageConstant.TDM_ADMIN_DISPLAY_USER
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null")) {

				if (baseEx.getErrorCode().startsWith("")) {
					modelmap.addAttribute("adminYN", "N");
					return "displayApps";
				}
			}

			modelmap.addAttribute("adminYN", "N");
			return "displayApps";
		}

	}

}
