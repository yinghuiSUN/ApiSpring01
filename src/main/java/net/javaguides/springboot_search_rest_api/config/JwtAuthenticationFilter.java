package net.javaguides.springboot_search_rest_api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.javaguides.springboot_search_rest_api.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;


   // JwtAuthenticationFilter = “每次请求的身份检查站”
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

/*
 JWT 不是单纯字符串
 JWT 每次请求都要验证
 signature 防篡改
 Authentication 每次都要重建
 Spring Security 本质是 Filter Chain
 权限控制发生在 Authentication 之后
**/
        // 1. 获取 Authorization Header
        String authHeader =
                request.getHeader("Authorization");

        // 2. 判断是否存在 Bearer token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response); //   // 它的意思其实是：“把请求交给下一个 Filter” 或者：“继续往后执行”
            // 因为我们可以有很多个filtre  所以不能直接return
            // JwtAuthenticationFilter 真正职责是：
            // 如果有 token，我就帮你解析”
            // 如果没有：我就放行。因为有些接口没必要token 比如我们也不需要登录就可以看到一些公司介绍页面
            return;
        }

        // 3. 提取 token
        String token = authHeader.substring(7);

        // 4. 解析 username
        String username = jwtUtil.parseToken(token);

        // 5. SecurityContext 里还没有用户
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 6. 查询数据库
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 7. 验证 token
            if (jwtUtil.validateToken(token, userDetails)) {

                // 8. 创建 Authentication
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 9. 放入 SecurityContext
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(auth);
            }
            // TODO supprimer

            System.out.println(
                    SecurityContextHolder.getContext().getAuthentication()
            );
        }



        // 10. 放行请求
        filterChain.doFilter(request, response);
    }
}
