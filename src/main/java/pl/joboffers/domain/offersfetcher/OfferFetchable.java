package pl.joboffers.domain.offersfetcher;

import pl.joboffers.domain.offersfetcher.dto.RemoteJobOfferDto;

import java.util.List;

public interface OfferFetchable {
        List<RemoteJobOfferDto> fetchOffers();
}
