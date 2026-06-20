package com.ccp.hotel.domain;
import org.junit.jupiter.api.Test; import org.junit.jupiter.params.ParameterizedTest; import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
class ValueObjectTest {
    @Test void createsValidName(){ Name name = new Name("Iqra"); assertEquals("Iqra", name.value()); }
    @ParameterizedTest @ValueSource(strings={"","   "}) void rejectsBlankName(String input){ assertThrows(IllegalArgumentException.class, () -> new Name(input)); }
    @ParameterizedTest @ValueSource(strings={"123", "abcd", "12345678901"}) void rejectsInvalidCreditCard(String number){ assertThrows(IllegalArgumentException.class, () -> new CreditCard(number)); }
    @Test void createsValidCreditCard(){ assertEquals("4111111111111111", new CreditCard("4111111111111111").number()); }
}
