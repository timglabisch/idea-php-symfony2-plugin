package fr.adrienbrault.idea.symfony2plugin.tests.config.component.parser;

import fr.adrienbrault.idea.io.File.Filesystem;
import fr.adrienbrault.idea.symfony2plugin.config.component.parser.ParameterServiceParser;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.util.Map;

public class ParameterServiceParserTest extends Assert {

    @Test
    public void testParse() throws Exception {

        Filesystem testFile = new Filesystem(this.getClass().getResource("appDevDebugProjectContainer.xml").getFile());
        ParameterServiceParser parameterServiceParser = new ParameterServiceParser();
        parameterServiceParser.parser(testFile);

        Map<String, String> map = parameterServiceParser.getParameterMap();

        assertEquals("app", map.get("kernel.name"));
        assertEquals("Symfony\\Bundle\\FrameworkBundle\\Controller\\ControllerNameParser", map.get("controller_name_converter.class"));

        assertEquals("collection", map.get("kernel.bundles"));
        assertNull("collection", map.get("%kernel.bundles%"));
        assertNull(map.get("FrameworkBundle"));
    }

}
