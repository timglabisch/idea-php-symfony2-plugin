package fr.adrienbrault.idea.symfony2plugin.tests.templating.path;

import fr.adrienbrault.idea.io.File.Filesystem;
import fr.adrienbrault.idea.symfony2plugin.templating.path.TwigPathIndex;
import fr.adrienbrault.idea.symfony2plugin.templating.path.TwigPathServiceParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author Daniel Espendiller <daniel@espendiller.net>
 */
public class TwigPathServiceParserTest extends Assert {

    @Test
    public void testParse() throws Exception {

        Filesystem testFile = new Filesystem(this.getClass().getResource("appDevDebugProjectContainer.xml").getFile());

        TwigPathServiceParser parser = new TwigPathServiceParser();
        parser.parser(testFile);

        assertEquals("vendor\\symfony\\symfony\\src\\Symfony\\Bundle\\FrameworkBundle/Resources/views", parser.getTwigPathIndex().getNamespacePaths("Framework").get(0).getPath());
        assertEquals("vendor\\foo\\bar\\FooBundle/Resources/views", parser.getTwigPathIndex().getNamespacePaths("Framework").get(1).getPath());

        assertEquals("vendor\\symfony\\symfony\\src\\Symfony\\Bridge\\Twig/Resources/views/Form", parser.getTwigPathIndex().getNamespacePaths(TwigPathIndex.MAIN).get(0).getPath());
        assertEquals("app/Resources/views", parser.getTwigPathIndex().getNamespacePaths(TwigPathIndex.MAIN).get(1).getPath());

    }

}
