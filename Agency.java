import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Tian Lan
 * @version 1.0
 * This class models an agency.
 */
public class Agency
{
    private final String                name;
    private final Map<String, Property> Properties;
    private static final int VALID_MIN_NAME_LENGTH = 1;
    private static final int VALID_MAX_NAME_LENGTH = 30;

    /**
     * Constructor, valid all agency information before initialize the object.
     * @param name The name of the agency, has length limit.
     * @throws IllegalArgumentException If the length of the name exceed the limit.
     */
    Agency(final String name)
    {
        Properties = new HashMap<>();

        if(name.length() < VALID_MIN_NAME_LENGTH || name.length() > VALID_MAX_NAME_LENGTH)
        {
            throw new IllegalArgumentException("Invalid name: " + name);
        }
        this.name = name;
    }

    /**
     * This method adds the non-null property to the HashMap Properties.
     * @param property The property to be added.
     */
    public void addProperty(final Property property)
    {
        if(property != null)
        {
            Properties.put(property.getPropertyId(), property);
        }
    }

    /**
     * This method removes the property whose ID matches the parameter, from the HashMap Properties.
     * @param propertyId The property ID to be removed.
     */
    public void removeProperty(final String propertyId)
    {
        Properties.remove(propertyId);
    }

    /**
     * @param propertyId The property ID to be matched.
     * @return The property whose ID matches the parameter from the HashMap, or null if there is no match.
     */
    public Property getProperty(final String propertyId)
    {
        return Properties.get(propertyId);
    }

    /**
     * @return The total amount in USD of all Properties.
     */
    public double getTotalPropertyValues()
    {
        double totalPropertyValues;
        Set<String> propertyIds;

        totalPropertyValues = 0;
        propertyIds = Properties.keySet();

        for(String propertyId : propertyIds)
        {
            totalPropertyValues += Properties.get(propertyId).getPriceUsd();
        }
        return totalPropertyValues;
    }

    /**
     * @return An ArrayList of the properties with the swimming pool, or null if there are none.
     */
    public ArrayList<Property> getPropertiesWithPools()
    {
        ArrayList<Property> propertiesWithPools;
        Set<String> propertyIds;

        propertiesWithPools = new ArrayList<>();
        propertyIds         = Properties.keySet();

        for(String propertyId : propertyIds)
        {
            if(Properties.get(propertyId).hasSwimmingPool())
            {
                propertiesWithPools.add(Properties.get(propertyId));
            }
        }

        if(propertiesWithPools.size() == 0)
        {
            return null;
        }
        return propertiesWithPools;
    }

    /**
     * @param minUsd The minimum price of the property, in USD.
     * @param maxUsd The maximum price of the property, in USD.
     * @return An array of properties whose price falls in the range specified by the parameters, or null if there are none.
     */
    public Property[] getPropertiesBetween(final double minUsd, final double maxUsd)
    {
        Property[] propertiesBetween;
        Set<String> propertyIds;
        int i;
        int counter;

        propertyIds = Properties.keySet();

        counter = 0;
        for(String propertyId : propertyIds)
        {
            double propertyPriceInUsd;
            propertyPriceInUsd = Properties.get(propertyId).getPriceUsd();

            if(propertyPriceInUsd >= minUsd && propertyPriceInUsd <= maxUsd)
            {
                counter++;
            }
        }

        // decide the Property[] length
        propertiesBetween = new Property[counter];

        i = 0;
        for(String propertyId : propertyIds)
        {
            double propertyPriceInUsd;
            propertyPriceInUsd = Properties.get(propertyId).getPriceUsd();

            if(propertyPriceInUsd >= minUsd && propertyPriceInUsd <= maxUsd)
            {
                propertiesBetween[i] = Properties.get(propertyId);
                i++;
            }
        }

        if(propertiesBetween.length == 0)
        {
            return null;
        }
        return propertiesBetween;
    }

    /**
     * @param streetName The street name to be matched.
     * @return An ArrayList of addresses which are on the specified street name, or null if there are none.
     */
    public ArrayList<Address> getPropertiesOn(final String streetName)
    {
        ArrayList<Address> propertiesOnStreet;
        Set<String> propertyIds;
        String propertyStreetName;

        propertiesOnStreet = new ArrayList<>();
        propertyIds        = Properties.keySet();

        for(String propertyId : propertyIds)
        {
            propertyStreetName = Properties.get(propertyId).getAddress().getStreetName();
            if(propertyStreetName.equalsIgnoreCase(streetName))
            {
                propertiesOnStreet.add(Properties.get(propertyId).getAddress());
            }
        }

        if(propertiesOnStreet.size() == 0)
        {
            return null;
        }
        return propertiesOnStreet;
    }

    /**
     * @param minBedrooms The minimum number of bedrooms in the range.
     * @param maxBedrooms The maximum number of bedrooms in the range.
     * @return A HashMap of properties (key is property id, value is the Property)
     * whose number of bedrooms falls in the range specified by the parameters, or null if there are none.
     */
    public HashMap<String, Property> getPropertiesWithBedrooms(final int minBedrooms, final int maxBedrooms)
    {
        HashMap<String, Property> propertiesWithBedrooms;
        Set<String> propertyIds;
        int numOfBedrooms;

        propertiesWithBedrooms = new HashMap<>();
        propertyIds            = Properties.keySet();

        for(String propertyId : propertyIds)
        {
            numOfBedrooms = Properties.get(propertyId).getNumberOfBedrooms();
            if(numOfBedrooms >= minBedrooms && numOfBedrooms <= maxBedrooms)
            {
                propertiesWithBedrooms.put(propertyId, Properties.get(propertyId));
            }
        }

        if(propertiesWithBedrooms.size() == 0)
        {
            return null;
        }

        return propertiesWithBedrooms;
    }

    /**
     * @param propertyType The property type to be matched.
     * @return An ArrayList of Strings, with all of the information about every property that matches the specified type
     * (case-insensitive), with certain format.
     */
    public ArrayList<String> getPropertiesOfType(final String propertyType)
    {
        int i;
        ArrayList<String> propertiesOfType;
        Set<String> propertyIds;
        String printLine;
        String printLine1;

        i = 1;
        propertiesOfType = new ArrayList<>();
        propertyIds      = Properties.keySet();

        for(String propertyId : propertyIds)
        {
            String   unitNumber;
            int      streetNumber;
            String   streetName;
            String   postalCode;
            String   cityName;
            Property theProperty;
            int      numOfBedrooms;
            double   propertyPriceInUsd;

            unitNumber         = Properties.get(propertyId).getAddress().getFormattedUnitNumber();
            streetNumber       = Properties.get(propertyId).getAddress().getStreetNumber();
            streetName         = Properties.get(propertyId).getAddress().getFormattedStreetName();
            postalCode         = Properties.get(propertyId).getAddress().getPostalCode().toUpperCase();
            cityName           = Properties.get(propertyId).getAddress().getFormattedCityName();
            theProperty        = Properties.get(propertyId);
            numOfBedrooms      = theProperty.getNumberOfBedrooms();
            propertyPriceInUsd = theProperty.getPriceUsd();

            if(Properties.get(propertyId).getType() != null)
            {
                if(Properties.get(propertyId).getType().equalsIgnoreCase(propertyType))
                {
                    if (numOfBedrooms == 1 && theProperty.hasSwimmingPool())
                    {
                        printLine = String.format("%d) Property %s: %s%d %s %s in %s (%d bedroom plus pool): $%.0f.\n",
                                i, propertyId, unitNumber, streetNumber, streetName,
                                postalCode, cityName, numOfBedrooms, propertyPriceInUsd);
                        propertiesOfType.add(printLine);
                    }
                    else if (numOfBedrooms == 1 && !theProperty.hasSwimmingPool())
                    {
                        printLine = String.format("%d) Property %s: %s%d %s %s in %s (%d bedroom): $%.0f.\n",
                                i, propertyId, unitNumber, streetNumber, streetName,
                                postalCode, cityName, numOfBedrooms, propertyPriceInUsd);
                        propertiesOfType.add(printLine);
                    }
                    else if (numOfBedrooms > 1 && theProperty.hasSwimmingPool())
                    {
                        printLine = String.format("%d) Property %s: %s%d %s %s in %s (%d bedrooms plus pool): $%.0f.\n",
                                i, propertyId, unitNumber, streetNumber, streetName,
                                postalCode, cityName, numOfBedrooms, propertyPriceInUsd);
                        propertiesOfType.add(printLine);
                    }
                    else if (numOfBedrooms > 1 && !theProperty.hasSwimmingPool())
                    {
                        printLine = String.format("%d) Property %s: %s%d %s %s in %s (%d bedrooms): $%.0f.\n",
                                i, propertyId, unitNumber, streetNumber, streetName,
                                postalCode, cityName, numOfBedrooms, propertyPriceInUsd);
                        propertiesOfType.add(printLine);
                    }
                    i++;
                }
            }
        }

        //The first element in the ArrayList is the statement of type.
        printLine1 = "Type: " + propertyType.toUpperCase() + "\n";
        propertiesOfType.add(printLine1);

        if(propertiesOfType.size() == 1)
        {
            printLine = "<none found>\n";
            propertiesOfType.add(printLine);
        }
        return propertiesOfType;
    }

    public String getName()
    {
        return name;
    }
}
