package cn.ahyc.yjz.security;
/**
 * CustomAuthenticationFailureHandler
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/28
 */

import cn.ahyc.yjz.service.LoginHistoryService;
import cn.ahyc.yjz.util.Constant;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sanlli on 15/9/28.
 */
public class LoginFailureHandler extends ExceptionMappingAuthenticationFailureHandler {


		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {


				super.onAuthenticationFailure(request, response, exception);
		}
}
