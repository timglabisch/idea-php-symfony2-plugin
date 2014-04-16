package fr.adrienbrault.idea.symfony2plugin.tests.templating.path.globals;

import fr.adrienbrault.idea.io.File.Filesystem;
import fr.adrienbrault.idea.symfony2plugin.templating.globals.TwigGlobalEnum;
import fr.adrienbrault.idea.symfony2plugin.templating.globals.TwigGlobalsServiceParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class TwigGlobalsServiceParserTest extends Assert {

    @Test
    public void testParse() throws Exception {

        Filesystem testFile = new Filesystem(this.getClass().getResource("appDevDebugProjectContainer.xml").getFile());

        TwigGlobalsServiceParser parser = new TwigGlobalsServiceParser();
        parser.parser(testFile);

        assertEquals("templating.globals", parser.getTwigGlobals().get("app").getValue());
        assertEquals(TwigGlobalEnum.SERVICE, parser.getTwigGlobals().get("app").getTwigGlobalEnum());
        assertEquals("1.2", parser.getTwigGlobals().get("version").getValue());

    }

}