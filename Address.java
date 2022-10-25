/**
 * @author Tian Lan
 * @version 1.0
 * This class models an address for the property.
 */
public class Address
{
    private final String unitNumber;
    private final int    streetNumber;
    private final String streetName;
    private final String postalCode;
    private final String cityName;

    private static final int VALID_MIN_UNIT_NUMBER_LENGTH = 1;
    private static final int VALID_MAX_UNIT_NUMBER_LENGTH = 4;
    private static final int VALID_MIN_STREET_NUMBER = 0;
    private static final int VALID_MAX_STREET_NUMBER = 999999;
    private static final int VALID_MIN_STREET_NAME_LENGTH = 1;
    private static final int VALID_MAX_STREET_NAME_LENGTH = 20;
    private static final int VALID_MIN_POSTAL_CODE_LENGTH = 5;
    private static final int VALID_MAX_POSTAL_CODE_LENGTH = 6;
    private static final int VALID_MIN_CITY_NAME_LENGTH = 1;
    private static final int VALID_MAX_CITY_NAME_LENGTH = 30;

    /**
     * Constructor, valid all address information before initialize the object.
     * @param unitNumber The unit number of the address, has length limit.
     * @param streetNumber The street number of the address, has value limit.
     * @param streetName The street name of the address, has length limit.
     * @param postalCode The postal code of the address, has length limit.
     * @param cityName The city name of the address, has length limit.
     * @throws IllegalArgumentException if the parameters exceed the length or value limits.
     */
    public Address(final String unitNumber, final int streetNumber,
                   final String streetName, final String postalCode, final String cityName)
    {
        if(unitNumber != null)
        {
            if(unitNumber.length() < VALID_MIN_UNIT_NUMBER_LENGTH || unitNumber.length() > VALID_MAX_UNIT_NUMBER_LENGTH)
            {
                throw new IllegalArgumentException("Invalid unit number: " + unitNumber);
            }
        }

        if(streetNumber < VALID_MIN_STREET_NUMBER || streetNumber > VALID_MAX_STREET_NUMBER)
        {
            throw new IllegalArgumentException("Invalid street number: " + streetNumber);
        }

        if(streetName != null)
        {
            if (streetName.length() < VALID_MIN_STREET_NAME_LENGTH || streetName.length() > VALID_MAX_STREET_NAME_LENGTH)
            {
                throw new IllegalArgumentException("Invalid street name: " + streetName);
            }
        }
        else
        {
            throw new NullPointerException("Invalid street name: null");
        }

        if(postalCode != null)
        {
            if(postalCode.length() < VALID_MIN_POSTAL_CODE_LENGTH || postalCode.length() > VALID_MAX_POSTAL_CODE_LENGTH)
            {
                throw new IllegalArgumentException("Invalid postal code: " + postalCode);
            }
        }
        else
        {
            throw new NullPointerException("Invalid postal code: null");
        }

        if(cityName != null)
        {
            if (cityName.length() < VALID_MIN_CITY_NAME_LENGTH || cityName.length() > VALID_MAX_CITY_NAME_LENGTH)
            {
                throw new IllegalArgumentException("Invalid city: " + cityName);
            }
        }
        else
        {
            throw new NullPointerException("Invalid city: null");
        }

        this.unitNumber   = unitNumber;
        this.streetNumber = streetNumber;
        this.streetName   = streetName;
        this.postalCode   = postalCode;
        this.cityName     = cityName;
    }

    /**
     * @return The unit number of the address.
     */
    public String getUnitNumber()
    {
        return unitNumber;
    }

    /**
     * @return The street number of the address.
     */
    public int getStreetNumber()
    {
        return streetNumber;
    }

    /**
     * @return The street name of the address.
     */
    public String getStreetName()
    {
        return streetName;
    }

    /**
     * @return The postal code of the address.
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**
     * @return The city name of the address.
     */
    public String getCity()
    {
        return cityName;
    }

    /**
     * @return The formatted String of the unit number.
     */
    public String getFormattedUnitNumber()
    {
        if(unitNumber != null)
        {
            return "unit #" + unitNumber + " at ";
        }
        return "";
    }

    /**
     * @return The formatted String of the street name.
     */
    public String getFormattedStreetName()
    {
        String[] streetNameData;
        String formattedStreetName;
        streetNameData = streetName.split(" ");
        formattedStreetName = streetNameData[0].substring(0,1).toUpperCase() +
                streetNameData[0].substring(1).toLowerCase() + " " +
                streetNameData[1].substring(0,1).toUpperCase() +
                streetNameData[1].substring(1).toLowerCase();
        return formattedStreetName;
    }

    /**
     * @return The formatted String of the city name.
     */
    public String getFormattedCityName()
    {
        String[] cityNameData;
        String formattedCityName;
        formattedCityName = null;

        cityNameData = cityName.split(" ");

        if(cityNameData.length > 1)
        {
            formattedCityName = cityNameData[0].substring(0, 1).toUpperCase() +
                    cityNameData[0].substring(1).toLowerCase() + " " +
                    cityNameData[1].substring(0, 1).toUpperCase() +
                    cityNameData[1].substring(1).toLowerCase();
        }
        else if(cityNameData.length == 1)
        {
            formattedCityName = cityNameData[0].substring(0, 1).toUpperCase() +
                    cityNameData[0].substring(1).toLowerCase();
        }
        return formattedCityName;
    }
}
