package org.example.controller;

import org.example.request.ChallengeRequest;
import org.example.request.GetDidDocRequest;
import org.example.response.CreateEnvelopeResponse;
import org.example.response.DidDocResponse;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign_in")
@CrossOrigin
public class SignInController {
    @Autowired
    private AccountService  accountService;
    // 获取网站的post请求
    @PostMapping("/with_did")
    public ResponseEntity<?> createEnvelope(@RequestBody ChallengeRequest request) throws Exception {
        CreateEnvelopeResponse response = accountService.creatEnvelope(request);

        // 返回response信息
        return ResponseEntity.ok(response) ;
    }
    // to check if did exist, and get did document
    @PostMapping("/get_did_doc")
    public ResponseEntity<?> isDidExist(@RequestBody GetDidDocRequest request){
        DidDocResponse didDocResponse = accountService.getDidDoc(request);
        return ResponseEntity.ok(didDocResponse);
    }
}
