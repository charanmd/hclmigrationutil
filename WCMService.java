package com.example;

import com.ibm.workplace.wcm.api.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class WCMService {

    private static WCMService instance;
    private Workspace workspace;

    // String constants for document types (used in the frontend)
    public static final String AUTHORING_TEMPLATE = "AuthoringTemplate";
    public static final String PRESENTATION_TEMPLATE = "PresentationTemplate";
    public static final String LIBRARY_COMPONENT = "LibraryComponent";
    public static final String CONTENT = "Content";
    public static final String SITE_AREA = "SiteArea";
    public static final String WORKFLOW = "Workflow";
    public static final String WORKFLOW_STAGE = "WorkflowStage";

    private WCMService() {}

    public static synchronized WCMService getInstance(HttpServletRequest request) throws Exception {
        if (instance == null) {
            instance = new WCMService();
            instance.initWorkspace(request);
        }
        return instance;
    }

    private void initWorkspace(HttpServletRequest request) throws Exception {
        Repository repository = WCM_API.getRepository();
        this.workspace = repository.getWorkspace(request.getUserPrincipal());
    }

    // Method 1: Return string constants for document types (for frontend)
    public List<String> getDocumentTypeNames() {
        return Arrays.asList(
            AUTHORING_TEMPLATE,
            PRESENTATION_TEMPLATE,
            LIBRARY_COMPONENT,
            CONTENT,
            SITE_AREA,
            WORKFLOW,
            WORKFLOW_STAGE
        );
    }

    // Method 2: Map string constant to actual DocumentType object
    public DocumentType mapDocumentType(String documentTypeName) {
        switch (documentTypeName) {
            case AUTHORING_TEMPLATE:
                return DocumentTypes.AuthoringTemplate;
            case PRESENTATION_TEMPLATE:
                return DocumentTypes.PresentationTemplate;
            case LIBRARY_COMPONENT:
                return DocumentTypes.LibraryComponent;
            case CONTENT:
                return DocumentTypes.Content;
            case SITE_AREA:
                return DocumentTypes.SiteArea;
            case WORKFLOW:
                return DocumentTypes.Workflow;
            case WORKFLOW_STAGE:
                return DocumentTypes.WorkflowStage;
            default:
                throw new IllegalArgumentException("Invalid document type: " + documentTypeName);
        }
    }

    // Method to fetch items by document type (after mapping string to DocumentType)
    public List<String> getItemsByDocumentType(String documentTypeName) throws Exception {
        DocumentType documentType = mapDocumentType(documentTypeName);
        DocumentIdIterator iterator = workspace.findByType(documentType);
        List<String> items = new ArrayList<>();
        while (iterator.hasNext()) {
            DocumentId docId = iterator.nextId();
            Document doc = workspace.getById(docId);
            items.add(doc.getName());
        }
        return items;
    }

    public Map<String, String> getItemMetadata(String itemName, String documentTypeName) throws Exception {
        DocumentType documentType = mapDocumentType(documentTypeName);
        DocumentIdIterator iterator = workspace.findByName(documentType, itemName);
        Map<String, String> metadata = new HashMap<>();
        if (iterator.hasNext()) {
            DocumentId docId = iterator.nextId();
            Document doc = workspace.getById(docId);
            metadata.put("Name", doc.getName());
            metadata.put("ID", doc.getId().getId());
            metadata.put("Title", doc.getTitle());
            metadata.put("Description", doc.getDescription());
            metadata.put("Last Modified", doc.getLastModified().toString());
            metadata.put("Created By", doc.getCreatedBy().getName());
        }
        return metadata;
    }

    public List<String> getLibraries() throws Exception {
        DocumentLibraryIterator libraryIterator = workspace.getDocumentLibraries();
        List<String> libraries = new ArrayList<>();
        while (libraryIterator.hasNext()) {
            DocumentLibrary library = libraryIterator.next();
            libraries.add(library.getName());
        }
        return libraries;
    }

    public void setCurrentLibrary(String libraryName) throws Exception {
        DocumentLibrary library = workspace.getDocumentLibrary(libraryName);
        workspace.setCurrentDocumentLibrary(library);
    }
}
