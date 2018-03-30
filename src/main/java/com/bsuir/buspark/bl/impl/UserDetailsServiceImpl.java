//package com.bsuir.buspark.bl.impl;
//
//import com.bsuir.buspark.dal.UserRepository;
//import com.bsuir.buspark.entity.Role;
//import com.bsuir.buspark.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.HashSet;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(userName);
//        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
//        for (Role role : user.getRoles()){
//            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthoritySet);
//    }
//}
