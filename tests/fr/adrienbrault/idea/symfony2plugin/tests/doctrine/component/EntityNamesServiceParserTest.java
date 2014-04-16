package fr.adrienbrault.idea.symfony2plugin.tests.doctrine.component;

import fr.adrienbrault.idea.io.File.Filesystem;
import fr.adrienbrault.idea.symfony2plugin.doctrine.component.EntityNamesServiceParser;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.util.Map;

/**
 * @author Daniel Espendiller <daniel@espendiller.net>
 */
public class EntityNamesServiceParserTest extends Assert {

    @Test
    public void testParse() throws Exception {

        Filesystem testFile = new Filesystem(this.getClass().getResource("appDevDebugProjectContainer.xml").getFile());
        EntityNamesServiceParser entityNamesServiceParser = new EntityNamesServiceParser();
        entityNamesServiceParser.parser(testFile);
        Map<String, String> map = entityNamesServiceParser.getEntityNameMap();

        assertEquals("\\My\\NiceBundle\\Entity", map.get("MyNiceBundle"));
        assertEquals("\\Your\\TestBundle\\Entity", map.get("YourTestBundle"));
    }

}

