package com.rems.email;

import com.rems.exception.ServiceException;
import com.rems.model.DTO.ForgotPassword;

public interface EmailNotificationService
{
	public void sendEmailNotification(ForgotPassword forgotPasswordDTO) throws ServiceException;
}
