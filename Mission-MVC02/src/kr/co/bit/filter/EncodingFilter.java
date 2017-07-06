package kr.co.bit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		urlPatterns={"*"},
		initParams={@WebInitParam(name="encoding", value="utf-8")}
		)
public class EncodingFilter implements Filter{
	private FilterConfig config;
	private String charset;
	
	@Override
	public void destroy() {
		System.out.println("필터소멸");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터작업시작");
		req.setCharacterEncoding(charset);
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("필터초기화");
		this.config = config;
		charset = config.getInitParameter("encoding");
	}
	
}
