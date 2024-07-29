package com.bilgeadam.okul.yonetim.sistemi.proje1.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.OgrenciDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogrenci;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.UserEntity;
import com.bilgeadam.okul.yonetim.sistemi.proje1.repo.OgrenciRepo;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.GateableById;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.Pageable;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.Readable;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.Updateable;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract.Writeable;

@Service
public class OgrenciService  implements Writeable<OgrenciDto> , Updateable<OgrenciDto>,
Readable<OgrenciDto>, GateableById<OgrenciDto> , Pageable<Ogrenci> {
	
	private OgrenciRepo ogrenciRepo;
	private ModelMapper modelMapper;

	public OgrenciService(OgrenciRepo ogrenciRepo, ModelMapper modelMapper) {
		super();
		this.ogrenciRepo = ogrenciRepo;
		this.modelMapper = modelMapper;
	}

	   @Override
	    public Page<Ogrenci> getPage(int sayfaSayisi, int kayitSayisi, String sortBy, String direction) {
	        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
	        PageRequest pageable = PageRequest.of(sayfaSayisi, kayitSayisi, sort);
	        return ogrenciRepo.findAll(pageable);
	    }

	   @Override
	    public Page<Ogrenci> getPage(int sayfaSayisi, int kayitSayisi, String sortBy, String direction, String keyword) {
	        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
	        PageRequest pageable = PageRequest.of(sayfaSayisi, kayitSayisi, sort);
	        if (keyword != null && !keyword.trim().isEmpty()) {
	            return ogrenciRepo.findByKeyword(keyword, pageable);
	        } else {
	            return ogrenciRepo.findAll(pageable);
	        }
	   }

	@Override
	public OgrenciDto getById(long id) {
		  Optional<Ogrenci> ogrenciOptional = ogrenciRepo.findById(id);
	        if (ogrenciOptional.isPresent()) {
	            Ogrenci ogrenci = ogrenciOptional.get();
	            return modelMapper.map(ogrenci, OgrenciDto.class);
	        }
	        return null;
	}

    public List<OgrenciDto> getList() {
        List<Ogrenci> ogrenciList = ogrenciRepo.findAll();
        return ogrenciList.stream()
                .map(ogrenci -> modelMapper.map(ogrenci, OgrenciDto.class))
                .collect(Collectors.toList());
    }
	@Override
	public boolean merge(OgrenciDto obj, long id) {
		  Optional<Ogrenci> ogrenciOptional = ogrenciRepo.findById(id);
	        if (ogrenciOptional.isPresent()) {
	            Ogrenci ogrenci = ogrenciOptional.get();
	            // Güncelleme işlemleri burada yapılabilir
	            // Örneğin:
	            ogrenci.setAd(obj.getAd());
	            ogrenci.setSoyad(obj.getSoyad());
	            ogrenci.setDogumTarihi(obj.getDogumTarihi());
	            ogrenci.setTcKimlikNo(obj.getTcKimlikNo());
	            ogrenci.setTelNo(obj.getTelNo());
	            ogrenci.setOgrenciNo(obj.getOgrenciNo());
	            ogrenci.setEnabled(obj.isEnabled());
	            
	            ogrenciRepo.save(ogrenci);
	            
	            return true;
	        }
	        return false;
	    }
	
    public void merge(OgrenciDto dto) {
        Optional<Ogrenci> ogrenciOptional = ogrenciRepo.findById(dto.getId());
        if (ogrenciOptional.isPresent()) {
            Ogrenci ogrenciToUpdate = ogrenciOptional.get();
            modelMapper.map(dto, ogrenciToUpdate); // Update fields from dto to entity
            ogrenciRepo.save(ogrenciToUpdate); // Save the updated entity
        } else {
            throw new RuntimeException("Ogrenci bulunamadı: " + dto.getId());
        }
    }
	
	@Override
	public void delete(long id) {
		Ogrenci ogrenci = ogrenciRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aranan id ile memur bulunamadı"));

		ogrenci.setEnabled(false);

		ogrenciRepo.save(ogrenci);
	}




	public OgrenciDto add(OgrenciDto ogrenciDto) {
        // DTO'dan Varlık Sınıfına Dönüşüm
        Ogrenci ogrenci = modelMapper.map(ogrenciDto, Ogrenci.class);

        // Veritabanına Kaydetme
        ogrenciRepo.save(ogrenci);
		return ogrenciDto;
    }
	public void updateOgrenciEnabled(long id, boolean b) {
		  Ogrenci ogrenci = ogrenciRepo.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Geçersiz öğrenci ID'si: " + id));
	        ogrenci.setEnabled(true);
	        ogrenciRepo.save(ogrenci);
		
	}

	public void merge(long id, boolean b) {
		 Ogrenci ogrenci = ogrenciRepo.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Geçersiz öğrenci ID'si: " + id));
	        ogrenci.setEnabled(true);
	        ogrenciRepo.save(ogrenci);
		
	}

	@Override
	public UserEntity getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}



	


}
