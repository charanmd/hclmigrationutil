<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>WCM Libraries and Content</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to CSS file -->
    <script src="wcm-app.js"></script> <!-- Include the JavaScript file -->
</head>
<body>

<h1>WCM Content Management</h1>

<!-- Section to Select Libraries -->
<h2>Select a Library</h2>
<div id="loading" class="loading" style="display:none;">Loading...</div> <!-- Loading message -->
<select id="librarySelect" onchange="loadDocumentTypes()">
    <option value="">Select a library...</option>
</select>

<!-- Section to Select Document Types (Initially hidden) -->
<div id="documentTypeSection" style="display:none;">
    <h2>Select a Document Type</h2>
    <select id="docTypeSelect" onchange="loadItems()">
        <option value="">Select a document type...</option>
    </select>
</div>

<!-- Section to Select Items (Initially hidden) -->
<div id="itemSection" style="display:none;">
    <h2>Select an Item</h2>
    <select id="itemSelect" onchange="loadItemMetadata()">
        <option value="">Select an item...</option>
    </select>
</div>

<!-- Section to Display Metadata (Initially hidden) -->
<div id="metadataSection" style="display:none;">
    <h2>Item Metadata</h2>
    <div id="metadata"></div>
</div>

</body>
</html>
