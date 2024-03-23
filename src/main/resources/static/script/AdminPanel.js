document.addEventListener('DOMContentLoaded', function() {
    function performSearch(inputId, resultsId, slideClass, searchEndpoint) {
        var userInput = document.getElementById(inputId);
        var results = document.getElementById(resultsId);

        userInput.addEventListener('input', function() {
            var query = userInput.value;
            var xhr = new XMLHttpRequest();
            xhr.open('GET', searchEndpoint + query);
            xhr.onload = function() {
                if (xhr.status === 200) {
                    var items = JSON.parse(xhr.responseText);
                    results.innerHTML = '';
                    if (items.length > 0) {
                        items.forEach(function(item) {
                            var div = document.createElement('div');
                            div.textContent = item.name; 
                            div.classList.add(slideClass);
                            results.appendChild(div);
                        });
                    } else {
                        var noResultsDiv = document.createElement('div');
                        noResultsDiv.textContent = 'No results found';
                        results.appendChild(noResultsDiv);
                    }
                }
            };
            xhr.send();
        });
    }

    performSearch('userInput', 'userResults', 'userSlide', '/search?user=');
    performSearch('authorInput', 'authorResults', 'authorSlide', '/search?author=');
    performSearch('publisherInput', 'publisherResults', 'publisherSlide', '/search?publisher=');
});

let saveButton = document.getElementById("saveButton");
let editButton = document.getElementById("editButton");
let submitButton = document.getElementById("submitButton");
let editable = true;
let listOfInputs = document.getElementsByTagName("input");

for (let i = listOfInputs.length - 1; i >= 0; i--) {
    if (i.endsWith("slide")) {
        sout(i)
        listOfInputs[i].parentNode.removeChild(listOfInputs[i]);
    }
}
function changeEditMode() {
    if (editable){
        sout("editable")
        editable = false;
        openInput(true)
        openEditMode()
    }
    else{
        sout("not editable")
        editable = true;
        openInput(false)
        closeEditMode()
    }
}
function isErrorExist(){

    let listOfErrors = document.querySelectorAll(".tooltip-text");
    for(let tooltipText of listOfErrors){
        let listOfP = tooltipText.querySelectorAll("p")
        for(let k of listOfP){
            if (k.textContent!=null) return true

        }
    }
    return false
}
function openEditMode() {
    saveButton.hidden = false;
    editButton.hidden = true;
}
function closeEditMode() {
    editButton.hidden = false;
    saveButton.hidden = true;
}
function openInput(mode) {
    for (let i = 0; i < listOfInputs.length; i++) {
        listOfInputs[i].readOnly = !mode;
    }
}

if(isErrorExist()){
    openInput(true)
    openEditMode()
}
else{
    closeEditMode();
    openInput(false)
}

function sout(text) {
    console.log(text)
}