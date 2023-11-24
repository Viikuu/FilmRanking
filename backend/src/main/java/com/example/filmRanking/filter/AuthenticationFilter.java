package com.example.filmRanking.filter;

import com.example.filmRanking.domain.UserEntity;
import com.example.filmRanking.repository.UserRepository;
import com.example.filmRanking.service.UserService.UserService;
import com.example.filmRanking.utils.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;

    private final UserRepository userRepository;

    public AuthenticationFilter(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        String token = TokenService.getTokenFromBearerHeaderString(bearerToken);

        if (token != null && userService.existsByToken(token)) {
            UserEntity user = userRepository.findByToken(token);

            if (user != null) {
                request.setAttribute("authenticatedUser", user);
            }
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/auth/login") || path.startsWith("/auth/register");
    }
}