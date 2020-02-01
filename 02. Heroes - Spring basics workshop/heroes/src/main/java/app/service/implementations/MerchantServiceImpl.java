package app.service.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import app.data.repositories.MerchantRepository;
import app.service.MerchantService;
import app.web.models.HeroItemsViewModel;


@Service
public class MerchantServiceImpl implements MerchantService {

	private MerchantRepository merchantRepository;
	private ModelMapper modelMapper;

	
	public MerchantServiceImpl(MerchantRepository merchantRepository, ModelMapper modelMapper) {
		this.merchantRepository = merchantRepository;
		this.modelMapper = modelMapper;
	}


	@Override
	public List<HeroItemsViewModel> getAllMerchants() {
		return this.merchantRepository.findAll().stream()
									  .map(heroItem -> this.modelMapper.map(heroItem, HeroItemsViewModel.class))
									  .collect(Collectors.toList());
	
	}

}
