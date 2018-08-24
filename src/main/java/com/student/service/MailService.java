package com.student.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Component("shivam4848@gmail.com")
@Service
public class MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
	private static JavaMailSender javaMailSender;
	private static TemplateEngine templateEngine;

	@Autowired
	public MailService(JavaMailSender javaMailSender , TemplateEngine templateEngine) {

		MailService.javaMailSender = javaMailSender;
		MailService.templateEngine = templateEngine;
	}

	public String build(String message) {

		logger.info("In Mail Servicce build");

		Context context = new Context();
		context.setVariable("message", message);
		return templateEngine.process("mailTemplate", context);
	}

	public void sendMail(String from, String to, String subject, String body) {

		SimpleMailMessage mail = new SimpleMailMessage();

		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);

		logger.info("Sending...");
		javaMailSender.send(mail);
	}

	public void templateMail(String from, String to, String subject, String message) {

		logger.info("In template Mail");

		MimeMessagePreparator messagePreparator = mimeMessage -> {

			logger.info("In templete mail mimeMessagePrepatator");

			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			String content = build(message);

			messageHelper.setFrom(from);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setText(content, true);
		};
		try {
			logger.info("In templete mail try");

			javaMailSender.send(messagePreparator);

			logger.info("mail sent..");

		} catch (MailException e) {

			logger.info(" in templateMail catch", e);
		}

	}
}
