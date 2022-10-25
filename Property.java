/**
 * @author Tian Lan
 * @version 1.0
 * This class models a property for the agency.
 */
public class Property
{
    private double        priceInUsd;
    private final Address address;
    private final int     numOfBedrooms;
    private final boolean swimmingPool;
    private final String  propertyType;
    private final String  propertyId;
    private static final int VALID_MIN_PRICE_USD = 0;
    private static final int VALID_MIN_NUM_OF_BEDROOMS = 1;
    private static final int VALID_MAX_NUM_OF_BEDROOMS = 20;
    private static final int VALID_MIN_PROPERTY_ID_LENGTH = 1;
    private static final int VALID_MAX_PROPERTY_ID_LENGTH = 6;
    private static final String[] VALID_PROPERTY_TYPES = {"residence", "commercial", "retail"};

    /**
     * Constructor, valid all property information before initialize the object.
     * @param priceInUsd The price of the property, in USD, must be positive.
     * @param address The address of the property, cannot be null.
     * @param numOfBedrooms The number of bedrooms of the property, has value limit.
     * @param swimmingPool True if the property has a swimming pool, otherwise false.
     * @param propertyType The property type of the property, must be one of the valid types.
     * @param propertyId The ID of the property, has length limit.
     * @throws IllegalArgumentException If the parameter values not valid.
     */
    Property(final double priceInUsd, final Address address, final int numOfBedrooms, final boolean swimmingPool,
             final String propertyType, final String propertyId)
    {
        if(priceInUsd < VALID_MIN_PRICE_USD)
        {
            throw new IllegalArgumentException("Invalid price: " + priceInUsd);
        }

        if(address == null)
        {
            throw new NullPointerException("Invalid address: null");
        }

        if(numOfBedrooms < VALID_MIN_NUM_OF_BEDROOMS || numOfBedrooms > VALID_MAX_NUM_OF_BEDROOMS)
        {
            throw new IllegalArgumentException("Invalid number of bedrooms: " + numOfBedrooms);
        }

        if(propertyType != null)
        {
            boolean validPropertyType;
            validPropertyType = false;

            for(String type : VALID_PROPERTY_TYPES)
            {
                if(type.equalsIgnoreCase(propertyType))
                {
                    validPropertyType = true;
                }
            }

            if(!validPropertyType)
            {
                throw new IllegalArgumentException("Invalid property type: " + propertyType);
            }
            else
            {
                this.propertyType = propertyType;
            }
        }
        else
        {
            throw new NullPointerException("Invalid property type: null");
        }

        if(propertyId != null)
        {
            if (propertyId.length() < VALID_MIN_PROPERTY_ID_LENGTH || propertyId.length() > VALID_MAX_PROPERTY_ID_LENGTH)
            {
                throw new IllegalArgumentException("Invalid property id: " + propertyId);
            }
        }
        else
        {
            throw new NullPointerException("Invalid property id: null");
        }

        this.priceInUsd    = priceInUsd;
        this.address       = address;
        this.numOfBedrooms = numOfBedrooms;
        this.swimmingPool  = swimmingPool;
        this.propertyId    = propertyId;
    }

    /**
     * @return The price of the property, in USD.
     */
    public double getPriceUsd()
    {
        return priceInUsd;
    }

    /**
     * @return The address of the property.
     */
    public Address getAddress()
    {
        return address;
    }

    /**
     * @return The number of bedrooms of the property.
     */
    public int getNumberOfBedrooms()
    {
        return numOfBedrooms;
    }

    /**
     * @return True if the property has a swimming pool, otherwise false.
     */
    public boolean hasSwimmingPool()
    {
        return swimmingPool;
    }

    /**
     * @return The type of the property.
     */
    public String getType()
    {
        return propertyType;
    }

    /**
     * @return The ID of the property.
     */
    public String getPropertyId()
    {
        return propertyId;
    }

    /**
     * This method sets the price to the given parameter.
     * @param priceInUsdToSet The given price to be set, in USD.
     */
    public void setPriceUsd(final double priceInUsdToSet)
    {
        this.priceInUsd = priceInUsdToSet;
    }
}
