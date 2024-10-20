// Show the "Loading..." message
function showLoading() {
    document.getElementById('loading').style.display = 'block';
}

// Hide the "Loading..." message
function hideLoading() {
    document.getElementById('loading').style.display = 'none';
}

// Load the list of libraries on page load
document.addEventListener('DOMContentLoaded', loadLibraries);

// Fetch and populate libraries dropdown
function loadLibraries() {
    showLoading(); // Show loading message
    fetch('/WCMServlet?action=selectLibrary')
        .then(response => response.json())
        .then(data => {
            let librarySelect = document.getElementById('librarySelect');
            data.forEach(library => {
                let option = document.createElement('option');
                option.value = library;
                option.text = library;
                librarySelect.add(option);
            });
            hideLoading(); // Hide loading message once data is loaded
        })
        .catch(error => {
            console.error('Error fetching libraries:', error);
            hideLoading(); // Hide loading message on error
        });
}

// Fetch document types when a library is selected
function loadDocumentTypes() {
    let library = document.getElementById('librarySelect').value;
    if (!library) return;

    showLoading(); // Show loading message
    fetch(`/WCMServlet?action=selectDocumentType&library=${library}`)
        .then(response => response.json())
        .then(data => {
            let docTypeSelect = document.getElementById('docTypeSelect');
            docTypeSelect.innerHTML = ''; // Clear previous options
            data.forEach(docType => {
                let option = document.createElement('option');
                option.value = docType;
                option.text = docType;
                docTypeSelect.add(option);
            });
            document.getElementById('documentTypeSection').style.display = 'block'; // Show document type section
            hideLoading(); // Hide loading message
        })
        .catch(error => {
            console.error('Error fetching document types:', error);
            hideLoading(); // Hide loading message on error
        });
}

// Fetch items when a document type is selected
function loadItems() {
    let documentType = document.getElementById('docTypeSelect').value;
    if (!documentType) return;

    showLoading(); // Show loading message
    fetch(`/WCMServlet?action=selectItems&documentType=${documentType}`)
        .then(response => response.json())
        .then(data => {
            let itemSelect = document.getElementById('itemSelect');
            itemSelect.innerHTML = ''; // Clear previous options
            data.forEach(item => {
                let option = document.createElement('option');
                option.value = item;
                option.text = item;
                itemSelect.add(option);
            });
            document.getElementById('itemSection').style.display = 'block'; // Show item section
            hideLoading(); // Hide loading message
        })
        .catch(error => {
            console.error('Error fetching items:', error);
            hideLoading(); // Hide loading message on error
        });
}

// Fetch and display item metadata when an item is selected
function loadItemMetadata() {
    let itemName = document.getElementById('itemSelect').value;
    let documentType = document.getElementById('docTypeSelect').value;
    if (!itemName || !documentType) return;

    showLoading(); // Show loading message
    fetch(`/WCMServlet?action=viewItemMetadata&itemName=${itemName}&documentType=${documentType}`)
        .then(response => response.json())
        .then(data => {
            let metadataDiv = document.getElementById('metadata');
            metadataDiv.innerHTML = ''; // Clear previous metadata
            for (let key in data) {
                let p = document.createElement('p');
                p.textContent = `${key}: ${data[key]}`;
                metadataDiv.appendChild(p);
            }
            document.getElementById('metadataSection').style.display = 'block'; // Show metadata section
            hideLoading(); // Hide loading message
        })
        .catch(error => {
            console.error('Error fetching metadata:', error);
            hideLoading(); // Hide loading message on error
        });
}
