package pl.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.offer.dto.FindByIdRequestDto;
import pl.joboffers.domain.offer.dto.OfferRequestDto;
import pl.joboffers.domain.offer.dto.OfferResponseDto;
import pl.joboffers.domain.offersfetcher.OffersFetcherFacade;
import pl.joboffers.domain.offersfetcher.dto.RemoteJobOfferDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OfferFacade {

    private final static String OFFER_NOT_FOUND_MESSAGE = "Offer not found";
    private final OfferRepository repository;
    private final OffersFetcherFacade fetcherFacade;
    private final OfferService offerService;

    public List<OfferResponseDto> findAllOffers() {
        return repository.findAll()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public OfferResponseDto findOfferById(FindByIdRequestDto requestDto) {
        String requestString = requestDto.id();
        return repository.findById(requestString)
                .map(OfferMapper::mapFromOfferToOfferDto)
                .orElseThrow(() -> new OfferNotFoundException(OFFER_NOT_FOUND_MESSAGE));
    }

    public OfferResponseDto saveOffer(OfferRequestDto offerRequestDto) {
        Offer offer = OfferMapper.mapFromOfferDtoToOffer(offerRequestDto);
        Offer savedOffer = repository.save(offer);
        return OfferMapper.mapFromOfferToOfferDto(savedOffer);
    }

    public List<OfferResponseDto> fetchRemoteOffersAndSaveIfNotExists() {
        List<RemoteJobOfferDto> fetchedRemoteJobOffers = fetcherFacade.fetchRemoteJobOffers();
        return offerService.saveUniqueOffers(fetchedRemoteJobOffers);
    }
}
