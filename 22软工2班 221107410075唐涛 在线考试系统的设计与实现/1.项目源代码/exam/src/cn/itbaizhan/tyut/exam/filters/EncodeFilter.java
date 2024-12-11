package cn.itbaizhan.tyut.exam.filters;
//设置请求和响应字符编码的过滤器
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodeFilter implements Filter {

	private String encode="";

	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);
	
		chain.doFilter(request, response);

	}//定义一个公共方法doFilter，接收三个参数：ServletRequest对象、ServletResponse对象和FilterChain对象。该方法可能抛出IOException和ServletException异常。

	public void init(FilterConfig config) throws ServletException {
	
		encode = config.getInitParameter("encode");//定义一个公共方法init，接收一个FilterConfig对象作为参数。该方法可能抛出ServletException异常
		
	}

}
