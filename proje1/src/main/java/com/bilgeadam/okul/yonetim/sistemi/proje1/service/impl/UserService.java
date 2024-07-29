package com.bilgeadam.okul.yonetim.sistemi.proje1.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.OgrenciDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.OgretmenDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.UserListDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.UserSaveDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.UserUpdateDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogrenci;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogretmen;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Role;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.UserEntity;
import com.bilgeadam.okul.yonetim.sistemi.proje1.repo.UserRepository;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.GateableById;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.Readable;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.Updateable;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.Writeable;

import jakarta.persistence.EntityManager;









@Service
public class UserService implements UserDetailsService, Writeable<UserSaveDto>, Updateable<UserUpdateDto>,
		Readable<UserListDto>, GateableById<UserEntity> {

	private final UserRepository userRepo;
	private final ModelMapper mapper;
	private final PasswordEncoder encoder;
	
	
	public UserService(UserRepository userRepo, ModelMapper mapper, PasswordEncoder encoder, EntityManager entityManager) {
		this.userRepo = userRepo;
		this.encoder = encoder;
		this.mapper = mapper;
	
	}
	
	
	@Override
	public UserEntity getById(long id) {
		
		return userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
	}

	@Override
	public List<UserListDto> getList() {

		List<UserEntity> list = userRepo.findAll();
		
		List<UserListDto> userListDto = list
				.stream()
				.map( user -> new UserListDto(user.getId(), user.getName(), user.getlName(), user.getEmail(), user.getbDate(), user.getRol(), user.getInsertedUser(),user.getLastUpdateUser(), user.isEnabled()))
				.collect(Collectors.toList());
		
		return userListDto;
	}

	
	@Override
	public boolean merge(UserUpdateDto userUpdateDto, long id) {
	    UserEntity updateableUser = getById(id);

	    // Version kontrolü
	    if (!updateableUser.getVersion().equals(userUpdateDto.getVersion())) {
	        return false;
	    }

	    // DTO'dan Entity'ye kopyalama (güncelleme)
	    mapper.map(userUpdateDto, updateableUser);

	    // Güncelleyeni belirleme
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        Optional<UserEntity> currentUserOptional = userRepo.findByEmail(userDetails.getUsername());
	        if (currentUserOptional.isPresent()) {
	            UserEntity currentUser = currentUserOptional.get();
	            updateableUser.setLastUpdateUser(currentUser);
	        }
	    }

	    
	    userRepo.save(updateableUser);

	    return true;
	}

    
	
	@Override
	public UserSaveDto add(UserSaveDto userSaveDto) {
	    // Şifreyi kodla
	    userSaveDto.setPASSWORD(encoder.encode(userSaveDto.getPASSWORD()));
	    UserEntity userEntity = new UserEntity();
	    mapper.map(userSaveDto, userEntity);	    	    
	    if (userSaveDto.getRol() != null) {
	        userEntity.setRol(userSaveDto.getRol());
	    } else {
	        userEntity.setRol(Role.ROLE_USER);
	    }
	       
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        Optional<UserEntity> currentUserOptional = userRepo.findByEmail(userDetails.getUsername());
	        if (currentUserOptional.isPresent()) {
	            UserEntity currentUser = currentUserOptional.get();
	            userEntity.setInsertedUser(currentUser);
	            
	        }
	    }
	    
	    
	    userRepo.save(userEntity);
	    
	    return userSaveDto;
	}

	@Override
	public void delete(long id) {
		
		UserEntity user =  userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı") );
		
		user.setEnabled(false);
		
		userRepo.save(user);
	}
	public void userAktifEt(long id) {
UserEntity user =  userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı") );
		
		user.setEnabled(true);
		
		userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}




	@Override
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return (UserEntity) authentication.getPrincipal();
        }
        return null;
    }

	  public UserEntity getCurrentUser2() {
	        // Mevcut kimlik doğrulama bilgisini al
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String currentUsername = authentication.getName();

	        // Kullanıcı adını kullanarak kullanıcıyı veritabanından bul
	        return userRepo.findByName(currentUsername)
	                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + currentUsername));
	    }

	    public void save(UserEntity user) {
	        userRepo.save(user);
	    }


		
	

	

	

	

	

}
