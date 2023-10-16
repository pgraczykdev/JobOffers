package pl.joboffers.domain.offersfetcher;

import pl.joboffers.domain.offersfetcher.dto.RemoteJobOfferDto;

import java.util.ArrayList;
import java.util.List;

public class OffersFetcherTestImplementation implements OfferFetchable {

    private final List<RemoteJobOfferDto> fetchedOffers;


    public OffersFetcherTestImplementation(List<RemoteJobOfferDto> fetchedOffers) {
        this.fetchedOffers = fetchedOffers;
    }

    public OffersFetcherTestImplementation() {
        this.fetchedOffers = new ArrayList<>();
    }

    @Override
    public List<RemoteJobOfferDto> fetchOffers() {
        return fetchedOffers;
    }
}