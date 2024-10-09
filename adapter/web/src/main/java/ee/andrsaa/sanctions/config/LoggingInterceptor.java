package ee.andrsaa.sanctions.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import java.util.UUID;

@Slf4j
public class LoggingInterceptor implements AsyncHandlerInterceptor {

  private static final String REQUEST_ID = "requestId";
  private static final String START_TIME_MILLIS = "startTimeMillis";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
      UUID requestId = UUID.randomUUID();
      long startTimeMillis = System.currentTimeMillis();

      request.setAttribute(REQUEST_ID, requestId.toString());
      request.setAttribute(START_TIME_MILLIS, startTimeMillis);
      String query = request.getQueryString() == null ? "" : "?" + request.getQueryString();

      log.debug("--> request ({}): {} {}", requestId, request.getMethod(), request.getRequestURI() + query);
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                              Object handler, Exception ex) {
      String requestId = (String) request.getAttribute(REQUEST_ID);
      long startTimeMillis = (Long) request.getAttribute(START_TIME_MILLIS);
      long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;

      log.debug("<-- response ({}): {}. Time elapsed {} ms.", requestId, response.getStatus(), elapsedTimeMillis);
  }
}
