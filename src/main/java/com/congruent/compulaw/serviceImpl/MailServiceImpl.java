/*package com.congruent.compulaw.serviceImpl;

import com.congruent.compulaw.domain.Transaction;
import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.service.MailService;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service("mailService")
public class MailServiceImpl implements MailService{

  @Autowired
  JavaMailSender mailSender;

  @Autowired
  VelocityEngine velocityEngine;
  private static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

  public void sendSubscriberConfirmationAlert(final User user){
    logger.info("Sending subscriber confirmation alert email");

    MimeMessagePreparator preparator = new MimeMessagePreparator(){
      public void prepare(MimeMessage mimeMessage) throws Exception{
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

        message.setSubject("User Confirmation");
        message.setTo(user.getEmail());
        message.setFrom("no-reply@compulawonline.com");

		Map<String, Object> model = new HashMap<String, Object>();       
        model.put("user", user);

        @SuppressWarnings("deprecation")
		String text = VelocityEngineUtils.mergeTemplateIntoString(
          MailServiceImpl.this.velocityEngine, "/com/congruent/compulaw/web/util/userConfirmation.vm", model);
        message.setText(text, true);
      }
    };
    this.mailSender.send(preparator);
    logger.info("Mail Sent.....");
  }

  public void sendSalesAlert(final Transaction transaction){
    logger.info("Inform sales dept of new sales");

    MimeMessagePreparator preparator = new MimeMessagePreparator(){
      public void prepare(MimeMessage mimeMessage) throws Exception{
    	  
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setSubject("Subscription Approval");
        message.setTo(transaction.getSubscriber());
        message.setFrom("no-reply@compulawonline.com");

        //@SuppressWarnings("rawtypes")
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("transaction", transaction);

        @SuppressWarnings("deprecation")
		String text = VelocityEngineUtils.mergeTemplateIntoString(
          MailServiceImpl.this.velocityEngine, "com/congruent/compulaw/web/util/newSales.vm", model);
        message.setText(text, true);
      }
    };
    this.mailSender.send(preparator);
    logger.info("Mail Sent..... to sales");
  }

  public void sendSubcriptionApprovalAlert(final Transaction transaction){
    logger.info("Inform subscriber for subscribption approval");

    MimeMessagePreparator preparator = new MimeMessagePreparator(){
      public void prepare(MimeMessage mimeMessage) throws Exception{
    	  
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setSubject("Subscription Approval");
        message.setTo(transaction.getUser().getEmail());
        message.setFrom("no-reply@compulawonline.com");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("transaction", transaction);

        @SuppressWarnings("deprecation")
		String text = VelocityEngineUtils.mergeTemplateIntoString(
          MailServiceImpl.this.velocityEngine, "com/congruent/compulaw/web/util/subscrApproval.vm", model);
        message.setText(text, true);
      }
    };
    this.mailSender.send(preparator);
    logger.info("Mail Sent..... to subscriber");
  }
}*/