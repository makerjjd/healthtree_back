package com.healthree.healthree_back.config.jwt;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.common.utils.TokenUtil;
import com.healthree.healthree_back.user.UserRepository;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;
    private TokenUtil tokenUtil;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository,
            TokenUtil tokenUtil) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String header = request.getHeader(JwtProperties.HEADER_ACCESS_STRING);
            if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
                chain.doFilter(request, response);
                return;
            }
            // access token
            String accessToken = request.getHeader(JwtProperties.HEADER_ACCESS_STRING).replace(
                    JwtProperties.TOKEN_PREFIX,
                    "");

            String tokenId = "";

            if (accessToken.startsWith("im_") && accessToken.endsWith("_si")) {
                accessToken = accessToken.substring(3, accessToken.length() - 3);
                tokenId = tokenUtil.decodeAccessTokenBeforeLogin(accessToken);

                if (StringUtils.isNotBlank(tokenId)) {
                    UserEntity userEntity = userRepository.findById(Long.parseLong(tokenId)).orElse(null);

                    if (!ObjectUtils.isEmpty(userEntity)) {
                        SecurityContextHolder.getContext().setAuthentication(tokenUtil.makeAuthentication(userEntity));
                    }
                }
            } else {
                tokenId = tokenUtil.decodeAccessToken(accessToken);

                if (StringUtils.isNotBlank(tokenId)) {
                    UserEntity userEntity = userRepository.findByEmail(tokenId).orElse(null);

                    if (!ObjectUtils.isEmpty(userEntity)) {
                        SecurityContextHolder.getContext().setAuthentication(tokenUtil.makeAuthentication(userEntity));
                    }
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            if (!(e instanceof HealthTreeApplicationExceptionHandler)) {
                log.error("JwtAuthorizationFilter doFilterInternal Error : " + e.toString());
                throw new HealthTreeApplicationExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR, e.toString());
            } else {
                throw e;
            }
        }

    }
}
