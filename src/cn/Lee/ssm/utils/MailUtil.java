package cn.Lee.ssm.utils;

import cn.Lee.ssm.utils.mail.Mail;
import cn.Lee.ssm.utils.mail.MailUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.text.MessageFormat;
import java.util.Properties;

public class MailUtil {

    public static void sendMail(String email, String code) throws Exception {

        Properties props = new Properties();
        props.load(MailUtil.class.getClassLoader().getResourceAsStream("email_config.properties"));

        String host = props.getProperty("host");//获取服务器主机
        String uname = props.getProperty("uname");//获取用户名
        String pwd = props.getProperty("pwd");//获取密码
        String from = props.getProperty("from");//获取发件人
        String to = email;//获取收件人
        String subject = props.getProperty("subject");//获取主题
        String content = props.getProperty("content");//获取邮件内容
        content = MessageFormat.format(content, code, code);//替换{0}

        Session session = MailUtils.createSession(host, uname, pwd);
        Mail mail = new Mail(from, to, subject, content);

        try {

            MailUtils.send(session, mail);//发邮件！

        } catch (MessagingException e) {

            throw new RuntimeException(e);
        }


    }


}
