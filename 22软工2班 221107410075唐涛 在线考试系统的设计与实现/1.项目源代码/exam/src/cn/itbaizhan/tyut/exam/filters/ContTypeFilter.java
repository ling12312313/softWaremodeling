package cn.itbaizhan.tyut.exam.filters;
//设置响应内容的MIME类型和字符编码的过滤器
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ContTypeFilter implements Filter {

	private String contenttype = "text/html;charset=utf-8";
	@Override
	public void destroy() {
		
	}
    @Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		response.setContentType(contenttype);
		chain.doFilter(request, response);
	}
   @Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		contenttype = filterConfig.getInitParameter("contenttype");
	}

}
