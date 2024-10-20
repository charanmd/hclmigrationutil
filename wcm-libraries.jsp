<html>
<head>
    <title>Select a Library</title>
    <script src="wcm-app.js"></script>
</head>
<body>

<h1>Select a Library</h1>

<!-- Library Selection Dropdown -->
<select id="librarySelect" onchange="loadDocumentTypes()">
    <option value="">Select a library...</option>
</select>

<!-- Section for selecting document types, initially hidden -->
<div id="documentTypeSection" style="display:none;">
    <h2>Select a Document Type</h2>
    <select id="docTypeSelect" onchange="loadItems()">
        <option value="">Select a document type...</option>
    </select>
</div>

<!-- Section for selecting items, initially hidden -->
<div id="itemSection" style="display:none;">
    <h2>Select an Item</h2>
    <select id="itemSelect" onchange="loadItemMetadata()">
        <option value="">Select an item...</option>
    </select>
</div>

<!-- Section for displaying metadata, initially hidden -->
<div id="metadataSection" style="display:none;">
    <h2>Item Metadata</h2>
    <div id="metadata"></div>
</div>

</body>
</html>
