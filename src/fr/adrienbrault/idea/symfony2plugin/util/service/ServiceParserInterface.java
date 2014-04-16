package fr.adrienbrault.idea.symfony2plugin.util.service;

import fr.adrienbrault.idea.io.IFile;

public interface ServiceParserInterface {
    public String getXPathFilter();
    public void parser(IFile file);
}
