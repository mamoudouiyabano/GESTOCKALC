package com.alc.gestock.controller;


import com.alc.gestock.dto.auth.AuthenticationResponse;
import com.alc.gestock.dto.auth.AuthenticationResquest;
import com.alc.gestock.model.auth.ExtendedUser;
import com.alc.gestock.services.auth.ApplicationUserDetailsService;
import com.alc.gestock.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.alc.gestock.utils.Constants.APP_ROOT;


@RestController
@CrossOrigin("*")
@RequestMapping(APP_ROOT + "/auth" )
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/authenticate" )
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationResquest resquest)
    {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        resquest.getLogin(),
                        resquest.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(resquest.getLogin());
        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);
        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }
}
