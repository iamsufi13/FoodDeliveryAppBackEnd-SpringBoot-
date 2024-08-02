package com.whizFortuneRestaurant.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }
    public void saveAddress(Address address){
        addressRepository.save(address);
    }
    public Address updateAddress(long id, Address address){
        Address address1 = getAddressById(id);
        address1.setName(address.getName());
        address1.setCity(address.getCity());
        address1.setAddress(address.getAddress());
        address1.setAddresstype(address.getAddresstype());
        address1.setLocality(address.getLocality());
        address1.setMobile(address.getMobile());
        address1.setState(address.getState());
        address1.setWeekenddelivery(address.getWeekenddelivery());
        return addressRepository.save(address);
    }

    public Address getAddressById(long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public void deleteAddressById(long id){
        addressRepository.deleteById(id);
    }
}
