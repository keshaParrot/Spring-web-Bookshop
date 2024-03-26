document.addEventListener('DOMContentLoaded', function() {
    let listOfOutputs = Array.from(document.querySelectorAll("[id$='Results']"));
    listOfOutputs.forEach(item => item.hidden = true); // Приховуємо всі результати на початку

    function performSearch(inputId, resultsId, slideClass, searchEndpoint) {
        const userInput = document.getElementById(inputId);
        const results = document.getElementById(resultsId);
        const button = document.getElementById(inputId + "Btn");
        console.log(button); // Логування кнопки для перевірки

        userInput.addEventListener('input', function() {
            const query = userInput.value.trim();
            if (query !== '') {
                fetch(searchEndpoint + encodeURIComponent(query))
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.json();
                    })
                    .then(items => {
                        results.innerHTML = ''; // Очищаємо попередні результати
                        const keys = Object.keys(items);
                        if (keys.length > 0) {
                            sout("true")
                            keys.forEach(key => {
                                const div = document.createElement('div');
                                div.textContent = items[key];
                                div.classList.add(slideClass);

                                const a = document.createElement('a');
                                a.href = `/personalAccount/user/${key}/edit`;

                                a.appendChild(div);
                                results.appendChild(a);
                            });
                            openOutput(listOfOutputs.find(item => item.id.replace("Results", '').includes(userInput.id.replace("Input", ''))));
                        } else {
                            sout("false")
                            showNoResultsMessage();
                        }
                    })
                    .catch(error => console.error('There has been a problem with your fetch operation:', error));
            } else {
                hideRelatedOutput();
            }
        });

        button.addEventListener("click", function() {
            userInput.value = '';
            hideRelatedOutput();
        });

        function openOutput(outputElement) {
            if (outputElement) outputElement.hidden = false;
        }

        function hideRelatedOutput() {
            const outputElement = listOfOutputs.find(item => item.id.replace("Results", '').includes(userInput.id.replace("Input", '')));
            if (outputElement) outputElement.hidden = true;
        }

        function showNoResultsMessage() {
            const noResultsDiv = document.createElement('div');
            noResultsDiv.textContent = 'No results found';
            results.appendChild(noResultsDiv);
        }
    }

    // Ініціалізуємо пошук для різних введень
    performSearch('userInput', 'userResults', 'userSlide', '/search/User?user=');
    performSearch('authorInput', 'authorResults', 'authorSlide', '/search/Author?author=');
    performSearch('publisherInput', 'publisherResults', 'publisherSlide', '/search/Publisher?publisher=');
});


let saveButton = document.getElementById("saveButton");
let editButton = document.getElementById("editButton");
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