package com.supretest.notice.sender.impl;

import com.supretest.base.domain.SystemParameter;
import com.supretest.base.domain.SystemParameterExample;
import com.supretest.base.mapper.SystemParameterMapper;
import com.supretest.commons.constants.ParamConstants;
import com.supretest.commons.utils.EncryptUtils;
import com.supretest.commons.utils.LogUtil;
import com.supretest.notice.domain.MessageDetail;
import com.supretest.notice.domain.Receiver;
import com.supretest.notice.domain.UserDetail;
import com.supretest.notice.sender.AbstractNoticeSender;
import com.supretest.notice.sender.NoticeModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Component
public class MailNoticeSender extends AbstractNoticeSender {
    @Resource
    private SystemParameterMapper systemParameterMapper;

    public void sendMail(String context, NoticeModel noticeModel) throws Exception {

        List<String> userIds = noticeModel.getReceivers().stream()
                .map(Receiver::getUserId)
                .distinct()
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userIds)) {
            return;
        }

        String[] users = super.getUserDetails(userIds).stream()
                .map(UserDetail::getEmail)
                .distinct()
                .toArray(String[]::new);

        send(noticeModel.getSubject(), context, users, new String[0]);
    }

    private void send(String subject, String context, String[] users, String[] cc) throws Exception {
        LogUtil.debug("发送邮件开始 ");
        SystemParameterExample example = new SystemParameterExample();
        example.createCriteria().andParamKeyLike(ParamConstants.Classify.MAIL.getValue() + "%");
        List<SystemParameter> paramList = systemParameterMapper.selectByExample(example);
        Map<String, String> paramMap = paramList.stream().collect(Collectors.toMap(SystemParameter::getParamKey, p -> {
            if (StringUtils.equals(p.getParamKey(), ParamConstants.MAIL.PASSWORD.getValue())) {
                return EncryptUtils.aesDecrypt(p.getParamValue()).toString();
            }
            if (StringUtils.isEmpty(p.getParamValue())) {
                return "";
            } else {
                return p.getParamValue();
            }
        }));
        JavaMailSenderImpl javaMailSender = getMailSender(paramMap);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        String username = javaMailSender.getUsername();
        String email;
        if (username.contains("@")) {
            email = username;
        } else {
            String mailHost = javaMailSender.getHost();
            String domainName = mailHost.substring(mailHost.indexOf(".") + 1);
            email = username + "@" + domainName;
        }
        InternetAddress from = new InternetAddress();
        String smtpFrom = paramMap.get(ParamConstants.MAIL.FROM.getValue());
        if (StringUtils.isBlank(smtpFrom)) {
            from.setAddress(email);
            from.setPersonal(username);
        } else {
            // 指定发件人后，address 应该是邮件服务器验证过的发件人
            if (smtpFrom.contains("@")) {
                from.setAddress(smtpFrom);
            } else {
                from.setAddress(email);
            }
            from.setPersonal(smtpFrom);
        }
        helper.setFrom(from);

        LogUtil.debug("发件人地址" + javaMailSender.getUsername());
        LogUtil.debug("helper" + helper);
        helper.setSubject("SupreTest " + subject);

        LogUtil.info("收件人地址: {}", users);
        helper.setText(context, true);
        helper.setTo(users);
        if (cc != null && cc.length > 0) {
            helper.setCc(cc);
        }
        javaMailSender.send(mimeMessage);
    }

    public void sendExternalMail(String context, NoticeModel noticeModel) throws Exception {

        List<String> userIds = noticeModel.getReceivers().stream()
                .map(Receiver::getUserId)
                .distinct()
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userIds)) {
            return;
        }

        List<String> recipients = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(noticeModel.getRecipients())) {
            recipients = noticeModel.getRecipients().stream()
                    .map(Receiver::getUserId)
                    .distinct()
                    .collect(Collectors.toList());
        }

        String[] users = userIds.stream()
                .distinct()
                .toArray(String[]::new);
        String[] ccArr = new String[0];
        if (CollectionUtils.isNotEmpty(recipients)) {
            ccArr = recipients.stream()
                    .distinct()
                    .toArray(String[]::new);
        }

        send(noticeModel.getSubject(), context, users, ccArr);
    }

    @Override
    public void send(MessageDetail messageDetail, NoticeModel noticeModel) {
        String context = super.getHtmlContext(messageDetail, noticeModel);
        try {
            sendMail(context, noticeModel);
            LogUtil.debug("发送邮件结束");
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }


    public JavaMailSenderImpl getMailSender(Map<String, String> paramMap) {
        Properties props = new Properties();
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setProtocol("smtp");
        javaMailSender.setHost(paramMap.get(ParamConstants.MAIL.SERVER.getValue()));
        javaMailSender.setPort(Integer.parseInt(paramMap.get(ParamConstants.MAIL.PORT.getValue())));
        javaMailSender.setUsername(paramMap.get(ParamConstants.MAIL.ACCOUNT.getValue()));
        javaMailSender.setPassword(paramMap.get(ParamConstants.MAIL.PASSWORD.getValue()));

        if (BooleanUtils.toBoolean(paramMap.get(ParamConstants.MAIL.SSL.getValue()))) {
            javaMailSender.setProtocol("smtps");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        if (BooleanUtils.toBoolean(paramMap.get(ParamConstants.MAIL.TLS.getValue()))) {
            String result = BooleanUtils.toString(BooleanUtils.toBoolean(paramMap.get(ParamConstants.MAIL.TLS.getValue())), "true", "false");
            props.put("mail.smtp.starttls.enable", result);
            props.put("mail.smtp.starttls.required", result);
        }

        props.put("mail.smtp.ssl.trust", javaMailSender.getHost());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.timeout", "30000");
        props.put("mail.smtp.connectiontimeout", "5000");
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }
}
