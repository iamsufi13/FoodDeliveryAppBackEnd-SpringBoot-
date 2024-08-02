package com.whizFortuneRestaurant.Address;

import com.whizFortuneRestaurant.Users.User;
import com.whizFortuneRestaurant.Users.UserRepository;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<ApiResponse<Address>>> getAllAddress(){
        List<Address> list = addressService.getAllAddress();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","true"));
    }
    @GetMapping("/{id}")
    public List<HashMap> getAddressById(@PathVariable long id, @RequestBody Address address){
       Address address1= addressService.getAddressById(id);
        HashMap hashMap = new HashMap<>();
        hashMap.put("data",address1);
        hashMap.put("status", "true");
        hashMap.put("message","SUCCESS");
        return Collections.singletonList(hashMap);
    }
    @PutMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Address>>> updateAddressById(@PathVariable long id,@RequestBody Address address){
        addressService.updateAddress(id,address);
        List<Address> list = addressService.getAllAddress();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","true"));
    }
    @PostMapping
    public ResponseEntity<List<ApiResponse<Address>>> saveAddress(@RequestBody Address address){
        System.out.println(address.toString());
        User user = userRepository.findById(address.getUser().getUserid())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + address.getUser().getUserid()));

        // Set the fetched user to the address
        address.setUser(user);
        addressService.saveAddress(address);
        List<Address> list = addressService.getAllAddress();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","true"));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Address>>> deleteById(@PathVariable long id){
        addressService.deleteAddressById(id);
        List<Address> list= addressService.getAllAddress();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","true"));
    }
}
