/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.controller;

import com.Tienda.service.ProductoService;
import com.Tienda.service.impl.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payment")
@Slf4j
public class PaypalController {
    
    private ProductoService itemService;
    
    private final PaypalService paypalService;
    
    @Autowired
    public PaypalController(ProductoService itemService, PaypalService paypalService) {
        this.itemService = itemService;
        this.paypalService = paypalService;
    }
    
    @GetMapping("/facturar")
    public RedirectView createPayment(
            @RequestParam("total") double total
    ){
        
        if (total > 0){
            
            try{
                String urlCancel = "http://localhost/payment/cancel";
                String urlSuccess = "http://localhost/payment/success";
                Payment payment = paypalService.createPayment(
                        total,
                        "USD",
                        "paypal",
                        "sale",
                        "Facturacion en Coffelife", 
                        urlCancel, 
                        urlSuccess);
                
                for(Links links: payment.getLinks()){
                    if(links.getRel().equals("approval_url")){
                        return new RedirectView(links.getHref());
                    }
                }
                
            }catch(PayPalRESTException e){
                System.out.print("Error en pago: " + e);
                
            }
            
        }
        
        return new RedirectView("/payment/error");
        
    }
    
    @GetMapping("/success")
    public String paymentSuccess(
            @RequestParam("paymentID") String paymentId,
            @RequestParam("PayerID") String payerId
    ){
        
        try{
            Payment payment = paypalService.executePayment(paymentId, payerId);
            
            if(payment.getState().equals("approved")){
                //Aqui hay que agregar el metodo de facturar, ya que no esta
                //itemService.facturar();
                return "/paypal/pagoSuccess";
            }
            
        }catch(PayPalRESTException e){
            System.out.print("Error en :" +  e);
        }
        
        
        return "/paypal/pagoSuccess";
    }
    
    
    @GetMapping("/cancel")
    public String paymentCancel(){
        return "/paypal/pagoCancel";
    }
    
    @GetMapping("/error")
    public String paymentError(){
        return "/paypal/pagoError";
    }
    
    
    
}
