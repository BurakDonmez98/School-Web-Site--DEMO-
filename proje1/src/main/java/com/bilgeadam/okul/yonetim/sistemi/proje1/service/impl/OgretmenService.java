package com.bilgeadam.okul.yonetim.sistemi.proje1.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.OgrenciDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.OgretmenDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.UserSaveDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.UserUpdateDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogrenci;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogretmen;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.UserEntity;
import com.bilgeadam.okul.yonetim.sistemi.proje1.repo.OgretmenRepo;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.GateableById;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.Pageable;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.Readable;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.Updateable;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.Writeable;



@Service
public class OgretmenService implements Writeable<Ogretmen>, Updateable<Ogretmen>,
Readable<Ogretmen>, Pageable<Ogretmen>, GateableById<Ogretmen> {
	
	private OgretmenRepo ogretmenRepo;

	
	private ModelMapper modelMapper;


	
	public OgretmenService(OgretmenRepo ogretmenRepo, ModelMapper modelMapper) {
		super();
		this.ogretmenRepo = ogretmenRepo;
		this.modelMapper = modelMapper;
	}


	@Override
	public Ogretmen getById(long id) {
	    return ogretmenRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Öğretmen bulunamadı"));
	}
	  public void merge(OgretmenDto dto) {
	        Optional<Ogretmen> ogretmenOptional = ogretmenRepo.findById(dto.getId());
	        if (ogretmenOptional.isPresent()) {
	            Ogretmen ogretmenToUpdate = ogretmenOptional.get();
	            modelMapper.map(dto, ogretmenToUpdate); // Update fields from dto to entity
	            ogretmenRepo.save(ogretmenToUpdate); // Save the updated entity
	        } else {
	            throw new RuntimeException("Ogrenci bulunamadı: " + dto.getId());
	        }
	    }



 public void ogretmenAktifEt(long id) {
	 Ogretmen ogretmen = ogretmenRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aranan id ile memur bulunamadı"));

		ogretmen.setEnabled(true);

		ogretmenRepo.save(ogretmen);
 }



	
	@Override
	public List<Ogretmen> getList() {
	    return ogretmenRepo.findAll();
	}


	public boolean merge(OgretmenDto obj, long id) {
	Ogretmen guncellenecekOgretmen = getById(id);


	modelMapper.map(obj, guncellenecekOgretmen);

	ogretmenRepo.save(guncellenecekOgretmen);

	return true;
	}





	@Override
	public void delete(long id) {
		Ogretmen ogretmen = ogretmenRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aranan id ile memur bulunamadı"));

		ogretmen.setEnabled(false);

		ogretmenRepo.save(ogretmen);
		
	}




	

	public Ogretmen add(OgretmenDto obj) {
	    // OgretmenDto'yu Ogretmen nesnesine dönüştür
	    Ogretmen ogretmen = modelMapper.map(obj, Ogretmen.class);

	    // enabled durumunu true olarak ayarla
	    ogretmen.setEnabled(true);

	    // Ogrenciyi veritabanına ekle
	    ogretmenRepo.save(ogretmen);

	    // Ekleme işlemi tamamlandıktan sonra oluşturulan Ogretmen nesnesini geri döndür
	    return ogretmen;
	}

	public List<Ogretmen> getEnabledOgretmenById(Long id) {
	    return ogretmenRepo.findByEnabledAndId(true, id);
	}


	public List<Ogretmen> pasifOgretmenleriGetir(Long id) {
		// TODO Auto-generated method stub
		return ogretmenRepo.findByEnabledAndId(false, id);
	}


	  @Override
	    public Page<Ogretmen> getPage(int sayfaSayisi, int kayitSayisi, String sortBy, String direction) {
	        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
	        PageRequest pageable = PageRequest.of(sayfaSayisi, kayitSayisi, sort);
	        return ogretmenRepo.findAll(pageable);
	    }

	   @Override
	    public Page<Ogretmen> getPage(int sayfaSayisi, int kayitSayisi, String sortBy, String direction, String keyword) {
	        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
	        PageRequest pageable = PageRequest.of(sayfaSayisi, kayitSayisi, sort);
	        if (keyword != null && !keyword.trim().isEmpty()) {
	            return ogretmenRepo.findByKeyword(keyword, pageable);
	        } else {
	            return ogretmenRepo.findAll(pageable);
	        }
	   }



	@Override
	public boolean merge(Ogretmen obj, long id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Ogretmen add(Ogretmen obj) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UserEntity getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}









	






	 



}