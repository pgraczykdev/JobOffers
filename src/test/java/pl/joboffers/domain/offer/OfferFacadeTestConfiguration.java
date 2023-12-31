package pl.joboffers.domain.offer;

import pl.joboffers.domain.offersfetcher.OffersFetcherFacade;

import java.util.Map;

class OfferFacadeTestConfiguration {

    public OfferFacadeTestConfiguration() {
    }

    public OfferFacade getOfferFacadeForTest(Map<String, Offer> testData) {
        OfferRepository repository = new OfferRepositoryTestImplementation(testData);
        OffersFetcherFacade fetcherFacade = new OffersFetcherFacade(new OfferFetcherInOfferFacadeTestImplementation());
        OfferSequenceGenerator sequenceGenerator = new OfferSequenceGeneratorTestImplementation(testData);
        return new OfferFacade(repository, fetcherFacade, sequenceGenerator);
    }
}
