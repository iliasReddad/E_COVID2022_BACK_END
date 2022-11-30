package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.Services.ExcelGenerator;
import com.bezkoder.springjwt.Services.UserService;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @DeleteMapping(value = "delete/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
       public ResponseEntity<MessageResponse> delete(@ApiParam("Username") @PathVariable String username , HttpServletRequest req){
        System.out.println(username);

        System.out.println("methode call");

        boolean deleted = userService.delete(username , req);
        System.out.println(deleted);
        if ( deleted ) {
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse(" USER DELETED!"));
        }
        else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("cannot delete this USER!"));
        }
    }

    @GetMapping(value = "change/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<MessageResponse> changeStatue(@ApiParam("Username") @PathVariable String username , HttpServletRequest req){
        System.out.println(username);

        System.out.println("methode call");

        boolean changeStatue = userService.changeStatue(username , req);
        System.out.println(changeStatue);
        if ( changeStatue ) {
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse(" Statue change succssfuly !"));
        }
        else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Cannot Suspend   this USER!"));
        }
    }


    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public User search(@ApiParam("Username") @PathVariable String username) {
        return userService.search(username);
    }

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 403, message = "Access denied"), //
//            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<User> getAllUser() {
        return userService.getall();
    }








}
