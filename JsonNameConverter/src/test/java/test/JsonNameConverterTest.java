package test;

import com.util.Converter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for JsonNameConverterTest.
 */
public class JsonNameConverterTest 
    extends TestCase
{
	String validJson = "{\"updateItems\":[{\"aclName\":\"ACL Name 1\",\"pids\":[\"pid 1\",\"pid 2\",\"pid 3\"]},{\"aclName\":\"ACL Name 2\",\"pids\":[\"pid 4\",\"pid 5\",\"pid 6\"]},{\"aclName\":\"ACL Name 3\",\"pids\":[\"pid 7\",\"pid 8\",\"pid 9\"]}]}";

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JsonNameConverterTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( JsonNameConverterTest.class );
    }

    /**
     * Test conversion from Pascal Case to Camel Case
     */
    public void testFromPascalCase()
    {
    	String pascalCaseJson = "{\"UpdateItems\":[{\"AclName\":\"ACL Name 1\",\"Pids\":[\"pid 1\",\"pid 2\",\"pid 3\"]},{\"AclName\":\"ACL Name 2\",\"Pids\":[\"pid 4\",\"pid 5\",\"pid 6\"]},{\"AclName\":\"ACL Name 3\",\"Pids\":[\"pid 7\",\"pid 8\",\"pid 9\"]}]}";
    	
    	Converter converter = new Converter();
    	
    	String fromPascal = converter.toCamelCase(pascalCaseJson);
    	assertEquals(validJson, fromPascal);
    }

    /**
     * Test that Camel Case remains Camel Case
     */
    public void testFromCamelCase()
    {
    	String camelCaseJson = "{\"updateItems\":[{\"aclName\":\"ACL Name 1\",\"pids\":[\"pid 1\",\"pid 2\",\"pid 3\"]},{\"aclName\":\"ACL Name 2\",\"pids\":[\"pid 4\",\"pid 5\",\"pid 6\"]},{\"aclName\":\"ACL Name 3\",\"pids\":[\"pid 7\",\"pid 8\",\"pid 9\"]}]}";
    	
    	Converter converter = new Converter();
    	
    	String fromCamel = converter.toCamelCase(camelCaseJson);
    	assertEquals(validJson, fromCamel);
    }

    /**
     * Test that lower case remains unchanged
     */
    public void testFromLowerCase()
    {
    	String lowerCaseJson = "{\"updateitems\":[{\"aclname\":\"ACL Name 1\",\"pids\":[\"pid 1\",\"pid 2\",\"pid 3\"]},{\"aclname\":\"ACL Name 2\",\"pids\":[\"pid 4\",\"pid 5\",\"pid 6\"]},{\"aclname\":\"ACL Name 3\",\"pids\":[\"pid 7\",\"pid 8\",\"pid 9\"]}]}";
    	
    	Converter converter = new Converter();
    	
    	String fromLower = converter.toCamelCase(lowerCaseJson);
    	assertEquals(lowerCaseJson, fromLower);
    }
}
