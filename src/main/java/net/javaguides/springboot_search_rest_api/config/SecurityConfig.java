package net.javaguides.springboot_search_rest_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity   //这是为了让：@PreAuthorize 生效
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> {}) // ✅ nouvelle syntaxe
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        // 登录接口不需要认证
                        auth.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(("/token")).permitAll()
                                .requestMatchers(("/parse")).permitAll()
                                .requestMatchers(("/error")).permitAll()
                                //TODO supprimer plus tard
                                .requestMatchers("/utilisateurs", "/departements").permitAll()


                        // 其它接口必须登录
                        .anyRequest().authenticated()
                ).exceptionHandling(ex -> ex

                // 未登录
                .authenticationEntryPoint((req, res, e) -> {
                    res.setStatus(401);
                    res.getWriter().write("password is not correct");
                })

                // 权限不足
                .accessDeniedHandler((req, res, e) -> {
                    res.setStatus(403);
                    res.getWriter().write("you are not authorized");
                }))
                // 把你的 JWT 过滤器，插到 Spring Security 默认登录过滤器之前
                //JWT 为什么需要 Filter
                // 因为你需要：每次请求都自动检查 token
                // 不能：每个 Controller 都手动写 解析 token
                // 应该：在请求进入 Controller 之前统一处理。这就是 Filter 的作用
        // 所有 HTTP 请求都必须经过 Filter  -| “在请求进入 Controller 前，提前做安全检查”

        .addFilterBefore( jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //配置 AuthenticationManager
    //因为现在 Spring 已经知道：
    //如何读取用户
    //但还不知道：如何执行认证
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}