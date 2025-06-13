package ru.omsu.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.omsu.core.repository.project.IProjectRepository;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserInProjectFilter extends OncePerRequestFilter {
    private final JwtDecoder jwtDecoder;
    private final IProjectRepository projectRepository;

    public UserInProjectFilter(JwtDecoder jwtDecoder,IProjectRepository projectRepository) {
        this.jwtDecoder = jwtDecoder;
        this.projectRepository =projectRepository;
    }
    private static final Pattern PROJECT_URL_PATTERN =
            Pattern.compile("^/([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})/.*$");

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        Matcher matcher = PROJECT_URL_PATTERN.matcher(request.getRequestURI());
        if (!matcher.find()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            UUID projectId = UUID.fromString(matcher.group(1));

            UUID userId = extractUserIdFromToken(request);

            if(!projectRepository.isUserInProject(userId,projectId))
            {
                response.sendError(HttpStatus.FORBIDDEN.value(), "Access to project denied");
                return;
            }

            filterChain.doFilter(request, response);

        } catch (IllegalArgumentException e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid project ID format");
        } catch (AccessDeniedException e) {
            response.sendError(HttpStatus.FORBIDDEN.value(), e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error");
        }
    }

    private UUID extractUserIdFromToken(HttpServletRequest request) throws AccessDeniedException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AccessDeniedException("Missing or invalid authorization header");
        }

        Jwt jwt = jwtDecoder.decode(authHeader.substring(7));
        return UUID.fromString(jwt.getClaim("userId")); // Преобразуем строку в UUID
    }
}
