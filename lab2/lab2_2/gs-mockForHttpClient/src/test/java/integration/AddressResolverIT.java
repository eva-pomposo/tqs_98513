package integration;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class AddressResolverIT {

    private ISimpleHttpClient iHttpClient;
    private AddressResolver resolver;

    @BeforeEach
    public void init(){
        iHttpClient = new TqsBasicHttpClient();
        resolver = new AddressResolver(iHttpClient);
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {
        Optional<Address> result = resolver.findAddressForLocation(40.640661, -8.656688);
        assertEquals( result, Optional.of(new Address( "Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null)) );
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {
        Optional<Address> result = resolver.findAddressForLocation(-300, -810);
        assertFalse(result.isPresent());
    }

}
