package net.javaguides.springboot_search_rest_api.controller;

import net.javaguides.springboot_search_rest_api.dto.ApiResponse;
import net.javaguides.springboot_search_rest_api.dto.ConnexionDto;
import net.javaguides.springboot_search_rest_api.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import static net.javaguides.springboot_search_rest_api.utils.Util.LOGIN_MSG_001;

@RestController
@RequestMapping("api/auth")
public class ConnexionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody ConnexionDto request) {

// 当你调用： authenticationManager.authenticate(...) Spring Security 会自动做：
        //1. 调用 CustomUserDetailsService.loadUserByUsername()
        //2. 从数据库加载用户
        //3. 比较密码（你现在是 NoOpPasswordEncoder）
        //4. 校验 role / authority
        //5. 返回 Authentication

        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getName(),
                                request.getPassword()
                        )
                );
        //把登录结果放进 SecurityContext
        //封装成：Authentication 对象 然后放进：SecurityContext
        // 实际上：登录成功以后：password 通常会被清空
        // 因为：后续请求已经不需要密码了。真正重要的是：用户身份 权限 登录状态

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //JWT 的核心思想   “登录状态不要存在服务器”  而是：“放到客户端自己保存”
        // 生成JWT    JWT 只是 Authentication 的“运输工具
        String token = JwtUtil.generateToken(authentication.getName());


        return ResponseEntity.ok(new ApiResponse<>(LOGIN_MSG_001, token, HttpStatus.OK.value()));

    }
}
